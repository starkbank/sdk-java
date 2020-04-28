package com.starkbank.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class Url {
    static StringBuilder encode(Map<String, Object> query) {
        StringBuilder queryString = new StringBuilder();
        String separator = "?";
        for (HashMap.Entry<String, Object> entry : query.entrySet()) {
            if (entry.getValue() == null) {
                continue;
            }
            String key = entry.getKey();
            String value;
            try {
                List<Object> list = (List<Object>) entry.getValue();
                entry.setValue(list.toArray(new Object[0]));
            } catch (Exception ignored) {
            }
            try {
                String[] stringArray = (String[]) entry.getValue();
                entry.setValue(stringArray);
            } catch (Exception ignored) {
            }
            try {
                value = "";
                String listSeparator = "";
                for (Object string : (Object[]) entry.getValue()) {
                    value += listSeparator + String.valueOf(string);
                    listSeparator = ",";
                }
            } catch (Exception e) {
                value = String.valueOf(entry.getValue());
            }
            if (value != null) {
                queryString.append(separator).append(key).append("=").append(value);
                separator = "&";
            }
        }
        return queryString;
    }
}
