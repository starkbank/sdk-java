package com.starkbank.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.starkbank.Event;
import com.starkbank.PaymentRequest;
import com.starkbank.Settings;
import com.starkcore.user.User;
import com.starkcore.utils.*;
import com.starkcore.utils.Parse;
import com.starkcore.utils.Rest;


final class Relay {

    final static String host = "bank";
    final static String apiVersion = "v2";

    public static <T extends Resource> T getId(Resource.ClassData resource, String id, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.getId(
            Settings.version, 
            host, 
            apiVersion, 
            user, 
            resource, 
            id, 
            Settings.language, 
            Settings.timeout
        );
    }

    public static <T extends Resource> List<T> post(Resource.ClassData resource, List<T> entities, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.post(
            Settings.version, 
            host, 
            apiVersion, 
            user, 
            resource, 
            entities, 
            Settings.language, 
            Settings.timeout
        );
    }

    public static <T extends Resource> T patch(Resource.ClassData resource, String id, Map<String, Object> data, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.patch(
            Settings.version, 
            host, 
            apiVersion, 
            user, 
            resource, 
            id, 
            Settings.language, 
            Settings.timeout,
            data
        );
    }

    public static Page getPage(Resource.ClassData resource, Map<String, Object> params, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.getPage(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            Settings.language, 
            Settings.timeout,
            params
        );
    }

    public static <T extends SubResource> Generator<T> getStream(Resource.ClassData resource, Map<String, Object> params, User user) {
        if(user == null) user = Settings.user;
        return Rest.getStream(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            Settings.language, 
            Settings.timeout,
            params
        );
    }

    public static <T extends SubResource> Generator<T> getSimpleList(Resource.ClassData resource, Map<String, Object> params, User user) {
        if(user == null) user = Settings.user;
        return Rest.getSimpleList(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            Settings.language, 
            Settings.timeout,
            params
        );
    }

    public static InputStream getContent(Resource.ClassData resource, String id, String subResourceName ,User user, Map<String, Object> options) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.getContent(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            subResourceName,
            id,
            Settings.language, 
            Settings.timeout,
            options
        );
    }

    public static <T extends SubResource> T getSubResource(Resource.ClassData resource, String id, SubResource.ClassData subResource, User user, Map<String, Object> options) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.getSubResource(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            id,
            subResource,
            Settings.language, 
            Settings.timeout,
            options
        );
    }

    public static <T extends SubResource> List<T> getSubResources(Resource.ClassData resource, String id, SubResource.ClassData subResource, User user, Map<String, Object> options) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.getSubResources(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            id,
            subResource,
            Settings.language, 
            Settings.timeout,
            options
        );
    }

    public static <T extends Resource> T delete(Resource.ClassData resource, String id, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.delete(
            Settings.version,
            host,
            apiVersion, 
            user, 
            resource,
            id,
            Settings.language, 
            Settings.timeout
        );
    }

    public static <T extends Resource> T postSingle(Resource.ClassData resource, Resource entity, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Rest.postSingle(
            Settings.version, 
            host, 
            apiVersion, 
            user, 
            resource, 
            entity, 
            Settings.language, 
            Settings.timeout
        );
    }

    public static <T extends Resource> T parseAndVerify(String content, String signature, Resource.ClassData resource, User user) throws Exception {
        if(user == null) user = Settings.user;
        return Parse.parseAndVerify(
            content,
            signature,
            Settings.version, 
            apiVersion, 
            host, 
            resource, 
            user, 
            Settings.language, 
            Settings.timeout
        );
    }

    static {
        GsonEvent.registerTypeAdapter(Event.class, new Event.Deserializer());
        GsonEvent.registerTypeAdapter(PaymentRequest.class, new PaymentRequest.Deserializer());
    }

}
