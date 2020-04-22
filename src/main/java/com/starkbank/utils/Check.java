package com.starkbank.utils;

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
}
