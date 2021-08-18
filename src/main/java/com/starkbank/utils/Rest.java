package com.starkbank.utils;

import com.google.gson.*;
import com.starkbank.User;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;


public final class Rest {

    public static <T extends Resource> T getId(Resource.ClassData resource, String id, User user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id), "GET", null, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> List<T> post(Resource.ClassData resource, List<T> entities, User user) throws Exception {
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

    public static <T extends Resource> T patch(Resource.ClassData resource, String id, Map<String, Object> data, User user) throws Exception {
        JsonObject payload = new Gson().fromJson(new Gson().toJson(data), JsonObject.class);
        String content = Response.fetch(Api.endpoint(resource, id), "PATCH", payload, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static Page getPage(Resource.ClassData resource, Map<String, Object> params, User user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource), "GET", null, params, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonElement cursorJson = contentJson.get("cursor");
        String cursor = cursorJson.isJsonNull() ? null : cursorJson.getAsString();

        List<SubResource> entities = new ArrayList<>();
        JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(resource)).getAsJsonArray();
        for (JsonElement resourceElement : jsonArray) {
            JsonObject jsonObject = resourceElement.getAsJsonObject();
            entities.add(GsonEvent.getInstance().fromJson(jsonObject, (Type) resource.cls));
        };

        return new Page(entities, cursor);
    }

    public static <T extends SubResource> Generator<T> getStream(Resource.ClassData resource, Map<String, Object> params, User user) {
        return new Generator<T>() {
            public void run() throws Exception {
                Map<String, Object> paramsCopy = new HashMap<>();
                for (Map.Entry<String, Object> entry: params.entrySet()) {
                    paramsCopy.put(entry.getKey(), entry.getValue());
                }
                Integer limit = (Integer) paramsCopy.get("limit");
                String cursor = null;
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

    public static <T extends SubResource> Generator<T> getSimpleList(Resource.ClassData resource, Map<String, Object> params, User user) {
        return new Generator<T>() {
            public void run() throws Exception {
                Map<String, Object> paramsCopy = new HashMap<>();
                for (Map.Entry<String, Object> entry: params.entrySet()) {
                    paramsCopy.put(entry.getKey(), entry.getValue());
                }
                String content = Response.fetch(Api.endpoint(resource), "GET", null, paramsCopy, user).content();
                Gson gson = GsonEvent.getInstance();
                JsonObject contentJson = gson.fromJson(content, JsonObject.class);
                JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(resource)).getAsJsonArray();
                for (JsonElement resourceElement : jsonArray) {
                    JsonObject jsonObject = resourceElement.getAsJsonObject();
                    T element = gson.fromJson(jsonObject, (Type) resource.cls);
                    if(element == null)
                        break;
                    this.yield(element);
                }
            }
        };
    }

    public static InputStream getContent(Resource.ClassData resource, String id, String subResourceName ,User user, Map<String, Object> options) throws Exception {
        return Response.fetch(Api.endpoint(resource, id) + "/" + subResourceName, "GET", null, options, user).stream;
    }

    public static <T extends SubResource> T getSubResource(Resource.ClassData resource, String id, SubResource.ClassData subResource, User user, Map<String, Object> options) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id) + "/" + Api.endpoint(subResource), "GET", null, options, user).content();
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(subResource)).getAsJsonObject();
        Gson gson = GsonEvent.getInstance();
        return gson.fromJson(jsonObject, (Type) subResource.cls);
    }

    public static <T extends SubResource> List<T> getSubResources(Resource.ClassData resource, String id, SubResource.ClassData subResource, User user, Map<String, Object> options) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id) + "/" + Api.endpoint(subResource), "GET", null, options, user).content();
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        JsonArray jsonArray = contentJson.get(Api.getLastNamePlural(subResource)).getAsJsonArray();
        List<T> entities = new ArrayList<>();
        for (JsonElement resourceElement : jsonArray) {
            JsonObject jsonObject = resourceElement.getAsJsonObject();
            entities.add(GsonEvent.getInstance().fromJson(jsonObject, (Type) subResource.cls));
        }
        return entities;
    }

    public static <T extends Resource> T delete(Resource.ClassData resource, String id, User user) throws Exception {
        String content = Response.fetch(Api.endpoint(resource, id), "DELETE", null, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> T postSingle(Resource.ClassData resource, Resource entity, User user) throws Exception {
        JsonObject payload = (JsonObject) new Gson().toJsonTree((entity));
        String content = Response.fetch(Api.endpoint(resource), "POST", payload, null, user).content();
        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(content, JsonObject.class);
        JsonObject jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) resource.cls);
    }
}
