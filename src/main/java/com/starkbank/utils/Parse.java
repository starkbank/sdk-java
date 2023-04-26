package com.starkbank.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.starkbank.User;
import com.starkbank.Settings;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.error.InvalidSignatureError;
import com.starkbank.ellipticcurve.utils.ByteString;

import java.lang.reflect.Type;
import java.util.HashMap;

public final class Parse{

    /**
     * Create single notification Event from a content string
     * <p>
     * Create a single Event object received from event listening at subscribed user endpoint.
     * If the provided digital signature does not check out with the Stark public key, a
     * com.starkbank.error.InvalidSignatureError will be raised.
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
     * com.starkbank.error.InvalidSignatureError will be raised.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static <T extends Resource> T parseAndVerify(Resource.ClassData resource, String content, String signature, User user) throws Exception {
        String verifiedContent = verify(content, signature, user);

        Gson gson = GsonEvent.getInstance();
        JsonObject contentJson = gson.fromJson(verifiedContent, JsonObject.class);
        JsonObject jsonObject = contentJson.getAsJsonObject();

        if (Api.getLastName(resource).equals("event")){
            jsonObject = contentJson.get(Api.getLastName(resource)).getAsJsonObject();
        }

        return gson.fromJson(jsonObject, (Type) resource.cls);
    }

    public static <T extends Resource> String verify (String content, String signature, User user) throws Exception {
        Signature signatureObject;
        try {
            signatureObject = Signature.fromBase64(new ByteString(signature.getBytes()));
        } catch (Error | RuntimeException e) {
            throw new InvalidSignatureError("The provided signature is not valid");
        }

        if (verifySignature(user, content, signatureObject, false)) {
            return content;
        }
        if (verifySignature(user, content, signatureObject, true)) {
            return content;
        }

        throw new InvalidSignatureError("The provided signature and content do not match the Stark Infra public key");
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
        HashMap<String, Object> query = new HashMap<>();
        query.put("limit", "1");
        String content = Response.fetch(
                "/public-key",
                "GET",
                null,
                query,
                user
        ).content();
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        JsonArray publicKeys = contentJson.get("publicKeys").getAsJsonArray();
        return PublicKey.fromPem(
                publicKeys.get(0).getAsJsonObject().get("content").getAsString()
        );
    }
}

abstract class Cache {
    public static PublicKey starkPublicKey = null;
}
