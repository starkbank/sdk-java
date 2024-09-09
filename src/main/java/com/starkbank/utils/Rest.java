package com.starkbank.utils;

import com.starkcore.utils.SubResource;
import com.starkbank.utils.Generator;
import com.starkbank.utils.GeneratorRelay;
import com.starkcore.utils.Page;
import com.starkbank.utils.Response;

import com.google.gson.*;
import com.starkbank.User;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.InputStream;
import java.lang.reflect.Type;


public final class Rest {

    static String host = "bank";
    static String sdkVersion = "2.18.1";
    static String apiVersion = "v2";
    static String language = "pt-BR";
    static Integer timeout = 5;

    public static <T extends Resource> T getId(Resource.ClassData resource, String id, User user) throws Exception {
        return getId(resource, id, null, user);
    }

    public static <T extends Resource> T getId(Resource.ClassData resource, String id, Map<String, Object> query, User user) throws Exception {
        return com.starkcore.utils.Rest.getId(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                id,
                language,
                timeout,
                query
        );
    }

    public static <T extends SubResource> List<T> post(SubResource.ClassData resource, List<T> entities, User user) throws Exception {
        return post(resource, entities, null, user);
    }

    public static <T extends SubResource> List<T> post(SubResource.ClassData resource, List<T> entities, Map<String, Object> data, User user) throws Exception {
        return com.starkcore.utils.Rest.post(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                entities,
                data,
                language,
                timeout
        );
    }

    public static <T extends Resource> T patch(Resource.ClassData resource, String id, Map<String, Object> data, User user) throws Exception {
        return com.starkcore.utils.Rest.patch(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                id,
                language,
                timeout,
                data
        );
    }

    public static Page getPage(Resource.ClassData resource, Map<String, Object> params, User user) throws Exception {
        return com.starkcore.utils.Rest.getPage(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                language,
                timeout,
                params
        );
    }

    public static <T extends SubResource> Generator<T> getStream(Resource.ClassData resource, Map<String, Object> params, User user) {
        return new GeneratorRelay<>(com.starkcore.utils.Rest.getStream(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                language,
                timeout,
                params
        ));
    }

    public static <T extends SubResource> Generator<T> getSimpleList(Resource.ClassData resource, Map<String, Object> params, User user) {
        return new GeneratorRelay<>(com.starkcore.utils.Rest.getSimpleList(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                language,
                timeout,
                params
        ));
    }

    public static InputStream getContent(Resource.ClassData resource, String id, String subResourceName ,User user, Map<String, Object> options) throws Exception {
        return com.starkcore.utils.Rest.getContent(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                subResourceName,
                id,
                language,
                timeout,
                options
        );
    }

    public static <T extends SubResource> T getSubResource(Resource.ClassData resource, String id, SubResource.ClassData subResource, User user, Map<String, Object> options) throws Exception {
        return com.starkcore.utils.Rest.getSubResource(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                id,
                subResource,
                language,
                timeout,
                options
        );
    }

    public static <T extends SubResource> List<T> getSubResources(Resource.ClassData resource, String id, SubResource.ClassData subResource, User user, Map<String, Object> options) throws Exception {
        return com.starkcore.utils.Rest.getSubResources(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                id,
                subResource,
                language,
                timeout,
                options
        );
    }

    public static <T extends Resource> T delete(Resource.ClassData resource, String id, User user) throws Exception {
        return com.starkcore.utils.Rest.delete(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                id,
                language,
                timeout
        );
    }

    public static <T extends SubResource> T postSingle(SubResource.ClassData resource, SubResource entity, User user) throws Exception {
        return com.starkcore.utils.Rest.postSingle(
                sdkVersion,
                host,
                apiVersion,
                user,
                resource,
                entity,
                language,
                timeout
        );
    }

    public static Response getRaw(String path, User user ) throws Exception {
        return Rest.getRaw(path, null, user);
    }

    public static Response getRaw(String path, Map<String, Object> query, User user ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.getRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                null,
                null,
                true
        );
        return new Response(response.status, response.stream);
    }

    public static Response getRaw(String path, User user, String prefix, Boolean raiseException) throws Exception {
        return Rest.getRaw(path, null, user, prefix, raiseException);
    }

    public static Response getRaw(String path, Map<String, Object> query, User user, String prefix, Boolean raiseException) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.getRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                null,
                prefix,
                raiseException
        );
        return new Response(response.status, response.stream);
    }

    public static Response postRaw(String path, Map<String, Object> payload, User user ) throws Exception {
        return Rest.postRaw(path, payload, null, user);
    }
    public static Response postRaw(String path, Map<String, Object> data,  Map<String, Object> query, User user ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.postRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                data,
                null,
                true
        );
        return new Response(response.status, response.stream);
    }

    public static Response postRaw(String path, Map<String, Object> data, User user, String prefix, Boolean raiseException) throws Exception {
        return Rest.postRaw(path, data, null, user, prefix, raiseException);
    }

    public static Response postRaw(String path, Map<String, Object> data,  Map<String, Object> query, User user, String prefix, Boolean raiseException ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.postRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                data,
                prefix,
                raiseException
        );

        return new Response(response.status, response.stream);
    }

    public static Response patchRaw(String path, Map<String, Object> data, User user ) throws Exception {
        return Rest.patchRaw(path, data, null, user);
    }

    public static Response patchRaw(String path, Map<String, Object> data,  Map<String, Object> query, User user ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.patchRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                data,
                null,
                true
        );

        return new Response(response.status, response.stream);
    }

    public static Response patchRaw(String path, Map<String, Object> data, User user, String prefix, Boolean raiseException ) throws Exception {
        return Rest.patchRaw(path, data, null, user, prefix, raiseException);
    }

    public static Response patchRaw(String path, Map<String, Object> data,  Map<String, Object> query, User user, String prefix, Boolean raiseException ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.patchRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                data,
                prefix,
                raiseException
        );

        return new Response(response.status, response.stream);
    }

    public static Response putRaw(String path, Map<String, Object> data, User user ) throws Exception {
        return Rest.putRaw(path, data, null, user);
    }
    public static Response putRaw(String path, Map<String, Object> data,  Map<String, Object> query, User user ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.putRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                data,
                null,
                true
        );

        return new Response(response.status, response.stream);
    }

    public static Response putRaw(String path, Map<String, Object> data, User user, String prefix, Boolean raiseException ) throws Exception {
        return Rest.putRaw(path, data, null, user, prefix, raiseException);
    }
    public static Response putRaw(String path, Map<String, Object> data,  Map<String, Object> query, User user, String prefix, Boolean raiseException ) throws Exception {
        JsonObject payload = new Gson().fromJson(new Gson().toJson(data), JsonObject.class);
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.putRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                data,
                prefix,
                raiseException
        );

        return new Response(response.status, response.stream);
    }

    public static Response deleteRaw(String path, User user ) throws Exception {
        return Rest.deleteRaw(path,null, user);
    }
    public static Response deleteRaw(String path, Map<String, Object> query, User user ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.deleteRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                null,
                null,
                true
        );

        return new Response(response.status, response.stream);
    }

    public static Response deleteRaw(String path, User user, String prefix, Boolean raiseException ) throws Exception {
        return Rest.deleteRaw(path, null, user, prefix, raiseException);
    }
    public static Response deleteRaw(String path, Map<String, Object> query, User user, String prefix, Boolean raiseException ) throws Exception {
        com.starkcore.utils.Response response = com.starkcore.utils.Rest.deleteRaw(
                sdkVersion,
                host,
                apiVersion,
                path,
                user,
                language,
                timeout,
                query,
                null,
                prefix,
                raiseException
        );

        return new Response(response.status, response.stream);
    }
}
