package com.starkbank.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


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
        if (entryValue.getClass() != String[].class) {
            return encode(entryValue.toString());
        }
        StringBuilder value = new StringBuilder();
        String listSeparator = "";
        for (String string : (String[]) entryValue) {
            value.append(listSeparator).append(encode(string));
            listSeparator = ",";
        }
        return value;
    }

    private static String encode(String string) throws UnsupportedEncodingException {
        return URLEncoder.encode(string, StandardCharsets.UTF_8.toString());
    }


}
