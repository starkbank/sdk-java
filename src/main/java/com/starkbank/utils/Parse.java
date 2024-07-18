package com.starkbank.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.starkbank.Settings;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.error.InvalidSignatureError;
import com.starkbank.ellipticcurve.utils.ByteString;

import com.starkcore.utils.SubResource;
import com.starkcore.user.User;

import java.lang.reflect.Type;
import java.util.HashMap;

public final class Parse {

    static String host = "bank";
    static String sdkVersion = "0.11.4";
    static String apiVersion = "v2";
    static String language = "pt-BR";
    static Integer timeout = 5;

    /**
     * Create single notification Event from a content string
     * <p>
     * Create a single Event object received from event listening at subscribed user endpoint.
     * If the provided digital signature does not check out with the Stark public key, a
     * com.starkinfra.error.InvalidSignatureError will be raised.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static<T extends Resource> T parse(Resource.ClassData resource, String content, String signature) throws Exception {
        return Parse.parseAndVerify(resource, content, signature, Settings.user);
    }

    /**
     * Create single notification Event from a content string
     * <p>
     * Create a single Event object received from event listening at subscribed user endpoint.
     * If the provided digital signature does not check out with the Stark public key, a
     * com.starkinfra.error.InvalidSignatureError will be raised.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkinfra.Settings.user was set before function call
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static <T extends SubResource> T parseAndVerify(SubResource.ClassData resource, String content, String signature, User user) throws Exception {

        return com.starkcore.utils.Parse.parseAndVerify(
                content,
                signature,
                sdkVersion,
                apiVersion,
                host,
                resource,
                user,
                language,
                timeout
        );
    }

    public static <T extends Resource> String verify (String content, String signature, User user) throws Exception {
        return com.starkcore.utils.Parse.verify(
                content,
                signature,
                sdkVersion,
                apiVersion,
                host,
                user,
                language,
                timeout
        );
    }

    private static boolean verifySignature(User user, String content, Signature signature, boolean refresh) throws Exception {
        PublicKey publicKey = Cache.starkPublicKey;
        if (publicKey == null || refresh) {
            publicKey = getStarkPublicKey(user);
            Cache.starkPublicKey = publicKey;
        }
        return Ecdsa.verify(content, signature, publicKey);
    }

    private static PublicKey getStarkPublicKey(User user) throws Exception {
        return com.starkcore.utils.Parse.getPublicKey(
                sdkVersion,
                apiVersion,
                host,
                user,
                language,
                timeout
        );
    }
}

abstract class Cache {
    public static PublicKey starkPublicKey = null;
}
