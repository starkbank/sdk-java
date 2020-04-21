package com.starkbank.utils;

import com.google.gson.*;
import com.starkbank.Event;
import com.starkbank.Project;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Rest {
    public static <T extends Resource> T getId(Resource.ClassData resource, String id, Project user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id), "GET", null, null, user, "v2").content;
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return new Gson().fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> List<T> post(Resource.ClassData resource, List<T> entities, Project user) throws Exception {
        JsonObject payload = new JsonObject();
        payload.add(Api.getLastNamePlural(resource), new Gson().toJsonTree(entities).getAsJsonArray());
        String content = Response.fetch(Api.endpoint(resource), "POST", payload, null, user, "v2").content;
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        List<T> postEntities = new ArrayList<>();
        JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(resource)).getAsJsonArray();
        for (JsonElement resourceElement : jsonArray) {
            JsonObject jsonObject = resourceElement.getAsJsonObject();
            postEntities.add(new Gson().fromJson(jsonObject, (Type) resource.cls));
        }
        return postEntities;
    }

    public static <T extends Resource> Generator<T> getList(Resource.ClassData resource, HashMap<String, Object> params, Project user) {
        return new Generator<T>() {
            public void run() throws Exception {
            Integer limit = (Integer) params.get("limit");
            String cursor = "";
            do {
                params.put("cursor", cursor);
                if (limit != null){
                    params.put("limit", limit > 100 ? "100" : limit.toString());
                    limit -= 100;
                };
                String content = Response.fetch(Api.endpoint(resource), "GET", null, params, user, "v2").content;
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Event.class, new Event.Deserializer())
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
                        .create();
                JsonObject contentJson = gson.fromJson(content, JsonObject.class);
                JsonElement cursorJson = contentJson.get("cursor");
                cursor = cursorJson.isJsonNull() ? "" : cursorJson.getAsString();
                JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(resource)).getAsJsonArray();
                for (JsonElement resourceElement : jsonArray) {
                    JsonObject jsonObject = resourceElement.getAsJsonObject();
                    yield(gson.fromJson(jsonObject, (Type) resource.cls));
                }
            } while(!cursor.isEmpty() && (limit == null || limit > 0));
            }
        };
    }

    public static InputStream getPdf(Resource.ClassData resource, String id, Project user) throws Exception {
        return Response.fetchStream(Api.endpoint(resource, id) + "/pdf", "GET", null, null, user, "v2");
    }

    public static <T extends Resource> T delete(Resource.ClassData resource, String id, Project user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id), "DELETE", null, null, user, "v2").content;
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Event.class, new Event.Deserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
                .create();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> T postSingle(Resource.ClassData resource, Resource entity, Project user) throws Exception {
        JsonObject payload = (JsonObject) new Gson().toJsonTree((entity));
        String content = Response.fetch(Api.endpoint(resource), "POST", payload, null, user, "v2").content;
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Event.class, new Event.Deserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
                .create();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }
}
