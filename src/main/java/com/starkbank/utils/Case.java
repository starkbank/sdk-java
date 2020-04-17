package com.starkbank.utils;

public class Case {
    public static String camel2snake(String text) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return (text.replaceAll(regex, replacement).toLowerCase());
    }

    public static String decamelize(String text, String separator) {
        if (text == null) {
            return null;
        }

        StringBuilder decamelized = new StringBuilder(text.length() + 10);
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i)) && decamelized.length() > 0) {
                decamelized.append(separator);
            }
            decamelized.append(Character.toLowerCase(text.charAt(i)));
        }
        return decamelized.toString();
    }
}
