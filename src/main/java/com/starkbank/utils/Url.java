package com.starkbank.utils;

import java.util.HashMap;
import java.util.Map;

final class Url {
    static StringBuilder encode(Map<String, Object> query){
        StringBuilder queryString = new StringBuilder();
        String separator = "?";
        for (HashMap.Entry<String, Object> entry : query.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            if (value != null) {
                queryString.append(separator).append(key).append("=").append(value);
                separator = "&";
            }
        }
        return queryString;
    }
}
