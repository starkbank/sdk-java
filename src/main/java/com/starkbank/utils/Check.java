package com.starkbank.utils;

import com.starkbank.ellipticcurve.PrivateKey;

import java.util.Arrays;
import java.util.List;

public class Check {
    public static String key(String key) throws Exception {
        try {
            PrivateKey.fromPem(key);
        } catch (Exception e) {
            throw new Exception("Invalid private key, try another one");
        }
        return key;
    }

    public static String environment(String environment) throws Exception {
        String[] validEnviroments = {"sandbox", "production"};
        List<String> list = Arrays.asList(validEnviroments);
        if (list.contains(environment)){
            return environment;
        }
        throw new Exception(String.format("Invalid environment, please choose among %s", Arrays.toString(validEnviroments)));
    }
}
