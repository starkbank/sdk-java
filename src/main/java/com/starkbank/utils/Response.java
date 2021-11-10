package com.starkbank.utils;

import com.google.gson.JsonObject;
import com.starkbank.User;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.error.InputErrors;
import com.starkbank.error.InternalServerError;
import com.starkbank.error.UnknownError;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.starkbank.Settings.userAgentOverride;
import static java.lang.System.currentTimeMillis;


public final class Response {

    public int status;
    public InputStream stream;

    public Response(int status, InputStream stream) {
        this.status = status;
        this.stream = stream;
    }

    public String content() throws java.io.IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (stream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

    public static Response fetch(String path, String method, JsonObject payload, Map<String, Object> query, User user) throws Exception {
        user = Check.user(user);
        String language = Check.language();

        if (query != null) {
            path += Url.encode(query);
        }
        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        String accessTime = String.valueOf(currentTimeMillis() / 1000L);
        String message = user.accessId() + ':' + accessTime + ':';
        String body = "";
        if (payload != null) {
            body = payload.toString();
            message += body;
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Id", user.accessId());
        headers.put("Access-Time", accessTime);
        headers.put("Access-Signature", Ecdsa.sign(message, user.privateKey()).toBase64());
        headers.put("User-Agent", getUserAgent());
        headers.put("Content-Type", "application/json");
        headers.put("Accept-Language", language);

        Response response = executeMethod(user, path, method, body, headers);
        if (response.status == 400) {
            throw new InputErrors(response.content());
        }
        if (response.status == 500) {
            throw new InternalServerError(response.content());
        }
        if (response.status != 200) {
            throw new UnknownError(response.content());
        }
        return response;
    }

    private static Response executeMethod(User user, String path, String method, String body, Map<String, String> headers) throws Exception {
        ClientService service = HttpClient.getProjectInstance(user);
        retrofit2.Response<ResponseBody> response;
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), body);
        switch (method) {
            case "GET":
                response = service.get(path, headers).execute();
                break;
            case "POST":
                response = service.post(path, requestBody, headers).execute();
                break;
            case "PATCH":
                response = service.patch(path, requestBody, headers).execute();
                break;
            case "PUT":
                response = service.put(path, requestBody, headers).execute();
                break;
            case "DELETE":
                response = service.delete(path, headers).execute();
                break;
            default:
                throw new Exception("unknown HTTP method");
        }

        int status = response.code();

        InputStream contentStream;
        if (status == 200) {
            try (ResponseBody responseBody = response.body()) {
                assert responseBody != null;
                contentStream = responseBody.byteStream();
            }
        } else {
            try (ResponseBody responseBody = response.errorBody()) {
                assert responseBody != null;
                contentStream = responseBody.byteStream();
            }
        }

        return new Response(status, contentStream);
    }

    private static String getUserAgent() {
        return (userAgentOverride == null) ? "Java-" + System.getProperty("java.version") + "-SDK-2.8.2" : userAgentOverride;
    }
}
