package com.starkbank.utils;
import java.util.HashMap;
import java.util.Map;


final class Url {

    static StringBuilder encode(Map<String, Object> query) {
        StringBuilder queryString = new StringBuilder("?");
        String separator = "&";
        for (HashMap.Entry<String, Object> entry : query.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }

            if (value.getClass() == String[].class) {
                value = "";
                String listSeparator = "";
                for (String string : (String[]) entry.getValue()) {
                    value += listSeparator + string;
                    listSeparator = ",";
                }
            }

            value = String.valueOf(value);
            queryString.append(separator).append(key).append("=").append(value);
            separator = "&";
        }
        return queryString;
    }
}
