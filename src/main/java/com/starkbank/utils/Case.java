package com.starkbank.utils;

final class Case {
    public static String camelToKebab(String text) {
        if (text == null) {
            return null;
        }

        StringBuilder decamelized = new StringBuilder(text.length() + 10);
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i)) && decamelized.length() > 0) {
                decamelized.append("-");
            }
            decamelized.append(Character.toLowerCase(text.charAt(i)));
        }
        return decamelized.toString();
    }
}
