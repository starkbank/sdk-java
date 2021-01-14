package com.starkbank.utils;

import com.starkbank.Settings;
import com.starkbank.User;
import com.starkbank.ellipticcurve.PrivateKey;

import java.util.Arrays;


public final class Check {
    public static String key(String key) throws Exception {
        try {
            PrivateKey privateKey = PrivateKey.fromPem(key);
            if (!privateKey.curve.name.equals("secp256k1")) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("private_key must be valid secp256k1 ECDSA string in pem format");
        }
        return key;
    }

    public static String environment(String environment) throws Exception {
        String[] validEnvironments = {"sandbox", "production"};
        if (Arrays.asList(validEnvironments).contains(environment)){
            return environment;
        }
        throw new Exception(
            String.format("Invalid environment, please choose one among %s", Arrays.toString(validEnvironments))
        );
    }

    static User user(User user) throws Error {
        if (user == null) {
            user = Settings.user;
        }
        if (user == null) {
            throw new Error("A user is required to access our API. Check our README: https://github.com/starkbank/sdk-java/");
        }
        return user;
    }

    public static String language() throws Exception {
        String[] validLanguages = {"en-US", "pt-BR"};
        String language = Settings.language;
        if (Arrays.asList(validLanguages).contains(language)){
            return language;
        }
        throw new Exception(
            String.format("Invalid language, please choose one among %s", Arrays.toString(validLanguages))
        );
    }
}
