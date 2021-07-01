package com.starkbank.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


final class Url {

    static StringBuilder encode(Map<String, Object> query) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder("?");
        String separator = "&";
        for (HashMap.Entry<String, Object> entry : query.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() == null) {
                continue;
            }
            queryString.append(separator).append(key).append("=").append(encode(entry.getValue()));
            separator = "&";
        }
        return queryString;
    }

    private static Object encode(Object entryValue) throws UnsupportedEncodingException {
        if (!(entryValue.getClass().isArray()) && !(entryValue instanceof Collection)) {
            return encode(entryValue.toString());
        }
        StringBuilder value = new StringBuilder();
        String listSeparator = "";
        List<?> values = convertObjectToList(entryValue);
        for (Object object : values) {
            value.append(listSeparator).append(encode(object));
            listSeparator = ",";
        }
        return value;
    }

    private static String encode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, StandardCharsets.UTF_8.toString());
    }

    private static List<?> convertObjectToList(Object obj) {
        if (obj.getClass().isArray()) {
            return Arrays.asList((Object[])obj);
        }
        return new ArrayList<>((Collection<?>)obj);
    }
}
