package com.starkbank.utils;

import com.google.gson.JsonObject;
import com.starkbank.User;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.Project;
import com.starkbank.error.InputErrors;
import com.starkbank.error.InternalServerError;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;


public class Response {

    public int status;
    public String content;

    public Response(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public static Response fetch(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user, String version) throws Exception {
        HttpURLConnection connection = prepareFetch(path, method, payload, query, user, version);
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

    public static InputStream fetchStream(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user, String version) throws Exception {
        HttpURLConnection connection = prepareFetch(path, method, payload, query, user, version);
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

    private static HttpURLConnection prepareFetch(String path, String method, JsonObject payload, HashMap<String, Object> query, Project user, String version) throws Exception {
        if (user == null) {
            user = User.defaultUser;
        }
        String urlString = host(user, version) + path;
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

    private static StringBuilder queryBuild(HashMap<String, Object> query){
        StringBuilder queryString = new StringBuilder();
        String separator = "?";
        for (HashMap.Entry<String, Object> entry : query.entrySet()) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
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
