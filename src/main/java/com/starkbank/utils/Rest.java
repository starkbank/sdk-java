package com.starkbank.utils;

import com.google.gson.*;
import com.starkbank.Project;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.DefaultEditorKit.CopyAction;

public final class Rest {

    public static <T extends Resource> T getId(Resource.ClassData resource, String id, Project user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id), "GET", null, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> List<T> post(Resource.ClassData resource, List<T> entities, Project user) throws Exception {
        JsonObject payload = new JsonObject();
        payload.add(Api.getLastNamePlural(resource), new Gson().toJsonTree(entities).getAsJsonArray());
        String content = Response.fetch(Api.endpoint(resource), "POST", payload, null, user).content();
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        List<T> postEntities = new ArrayList<>();
        JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(resource)).getAsJsonArray();
        for (JsonElement resourceElement : jsonArray) {
            JsonObject jsonObject = resourceElement.getAsJsonObject();
            postEntities.add(GsonEvent.getInstance().fromJson(jsonObject, (Type) resource.cls));
        }
        return postEntities;
    }

    public static <T extends Resource> T patch(Resource.ClassData resource, String id, Map<String, Object> data, Project user) throws Exception {
        JsonObject payload = new Gson().fromJson(new Gson().toJson(data), JsonObject.class);
        String content = Response.fetch(Api.endpoint(resource, id), "PATCH", payload, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> Generator<T> getList(Resource.ClassData resource, Map<String, Object> params, Project user) {
        return new Generator<T>() {
            public void run() throws Exception {
                Map<String, Object> paramsCopy = new HashMap<>();
                for (Map.Entry<String, Object> entry: params.entrySet()) {
                    paramsCopy.put(entry.getKey(), entry.getValue());
                }
                Integer limit = (Integer) paramsCopy.get("limit");
                String cursor = "";
                do {
                    paramsCopy.put("cursor", cursor);
                    if (limit != null) {
                        paramsCopy.put("limit", limit > 100 ? "100" : limit.toString());
                        limit -= 100;
                    }
                    String content = Response.fetch(Api.endpoint(resource), "GET", null, paramsCopy, user).content();
                    Gson gson = GsonEvent.getInstance();
                    JsonObject contentJson = gson.fromJson(content, JsonObject.class);
                    JsonElement cursorJson = contentJson.get("cursor");
                    cursor = cursorJson.isJsonNull() ? "" : cursorJson.getAsString();
                    JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(resource)).getAsJsonArray();
                    for (JsonElement resourceElement : jsonArray) {
                        JsonObject jsonObject = resourceElement.getAsJsonObject();
                        T element = gson.fromJson(jsonObject, (Type) resource.cls);
                        if(element == null)
                            break;
                        this.yield(element);
                    }
                } while (!cursor.isEmpty() && (limit == null || limit > 0));
            }
        };
    }

    public static InputStream getPdf(Resource.ClassData resource, String id, Project user, Map<String, Object> options) throws Exception {
        return Response.fetch(Api.endpoint(resource, id) + "/pdf", "GET", null, options, user).stream;
    }

    public static <T extends Resource> T delete(Resource.ClassData resource, String id, Project user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id), "DELETE", null, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> T postSingle(Resource.ClassData resource, Resource entity, Project user) throws Exception {
        JsonObject payload = (JsonObject) new Gson().toJsonTree((entity));
        String content = Response.fetch(Api.endpoint(resource), "POST", payload, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }
}
