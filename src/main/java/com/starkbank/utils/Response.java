package com.starkbank.utils;

import com.google.gson.JsonObject;
import com.starkbank.User;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.Project;
import com.starkbank.error.InputErrors;
import com.starkbank.error.InternalServerError;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;


public final class Response {

    public int status;
    public String content;

    public Response(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public static Response fetch(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user) throws Exception {
        HttpURLConnection connection = prepareFetch(path, method, payload, query, user);
        int status = connection.getResponseCode();
        Reader streamReader;
        if (status >= 300) {
            streamReader = new InputStreamReader(connection.getErrorStream());
        } else {
            streamReader = new InputStreamReader(connection.getInputStream());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        if (status >= 500) {
            throw new InternalServerError(content.toString());
        }
        if (status >= 300) {
            throw new InputErrors(content.toString());
        }
        return new Response(status, content.toString());
    }

    public static Response fetchNew(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user) throws Exception {
        HttpResponse response = prepareFetchNew(path, method, payload, query, user);
        int status = response.getStatusLine().getStatusCode();
        Reader streamReader;
        if (status >= 300) {
            streamReader = new InputStreamReader(response.getEntity().getContent());
        } else {
            streamReader = new InputStreamReader(response.getEntity().getContent());
        }

        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content.toString());
        if (status >= 500) {
            throw new InternalServerError(content.toString());
        }
        if (status >= 300) {
            throw new InputErrors(content.toString());
        }
        return new Response(status, content.toString());
    }

    public static InputStream fetchStream(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user) throws Exception {
        HttpURLConnection connection = prepareFetch(path, method, payload, query, user);
        int status = connection.getResponseCode();
        InputStream streamReader;
        if (status >= 300) {
            streamReader = connection.getErrorStream();
        } else {
            streamReader = connection.getInputStream();
        }
        if (status >= 300) {
            BufferedReader in = new BufferedReader(new InputStreamReader(streamReader));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            throw new InputErrors(content.toString());
        }
        return streamReader;
    }

    private static HttpURLConnection prepareFetch(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user) throws Exception {
        if (user == null) {
            user = User.defaultUser;
        }
        String urlString = host(user, "v2") + path;
        if (query != null) {
            urlString += queryBuild(query);
        }
        String accessTime = String.valueOf(Math.round(Instant.now().getEpochSecond()));

        String message = user.accessId() + ':' + accessTime + ':';
        if (payload != null) {
            String body = payload.toString();
            message += body;
        }
        URL url = new URL(urlString);
        Http.allowMethods("PATCH");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        Signature signature = Ecdsa.sign(message, user.privateKey());
        setHeaders(connection, user.accessId(), accessTime, signature.toBase64());

        connection.setRequestMethod(method);
        if (method.equals("POST") || method.equals("PATCH")){
            connection.setDoOutput(true);
            try (OutputStream out = connection.getOutputStream()) {
                assert payload != null;
                byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                out.write(input, 0, input.length);
            }
        }

        return connection;
    }


    private static HttpResponse prepareFetchNew(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user) throws IOException {
        if (user == null) {
            user = User.defaultUser;
        }
        String urlString = host(user, "v2") + path;
        if (query != null) {
            urlString += queryBuild(query);
        }
        String accessTime = String.valueOf(Math.round(Instant.now().getEpochSecond()));

        String message = user.accessId() + ':' + accessTime + ':';
        if (payload != null) {
            String body = payload.toString();
            message += body;
        }
        System.out.println(message);
        Signature signature = Ecdsa.sign(message, user.privateKey());
        RequestBuilder requestBuilder = RequestBuilder.create(method)
                .setUri(urlString)
                .setHeader("Access-Id", user.accessId())
                .setHeader("Access-Time", accessTime)
                .setHeader("Access-Signature", signature.toBase64())
                .setHeader("User-Agent", "SDK-Java-" + System.getProperty("java.version"))
                .setHeader("Content-Type", "application/json");

        if (method.equals("POST") || method.equals("PATCH")){
            requestBuilder = requestBuilder
                    .setEntity(new StringEntity(message, ContentType.APPLICATION_JSON))
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        }

        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = requestBuilder.build();
        System.out.println(Arrays.toString(request.getAllHeaders()));
        return client.execute(request);
    }

    private static StringBuilder queryBuild(HashMap<String, Object> query){
        StringBuilder queryString = new StringBuilder();
        String separator = "?";
        for (HashMap.Entry<String, Object> entry : query.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            if (value != null) {
                queryString.append(separator).append(key).append("=").append(value);
                separator = "&";
            }
        }
        return queryString;
    }

    private static String host(Project user, String version){
        switch (user.environment) {
            case "production":
                return "https://api.starkbank.com/" + version;
            case "sandbox":
                return "https://sandbox.api.starkbank.com/" + version;
            default:
                throw new IllegalStateException("Unexpected value: " + user.environment);
        }
    }

    private static void setHeaders(HttpURLConnection connection, String accessId, String accessTime, String signature) {
        connection.setRequestProperty("Access-Id", accessId);
        connection.setRequestProperty("Access-Time", accessTime);
        connection.setRequestProperty("Access-Signature", signature);
        connection.setRequestProperty("User-Agent", "SDK Java" + System.getProperty("java.version"));
        connection.setRequestProperty("Content-Type", "application/json");
    }
}
