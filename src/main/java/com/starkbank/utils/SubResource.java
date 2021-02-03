package com.starkbank.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class SubResource {
    protected static class ClassData {
        public String name;
        public Class<?> cls;

        public ClassData(Class<?> cls, String name){
            this.cls = cls;
            this.name = name;
        }
    }

    public String toString() {
        String name = this.getClass().getSimpleName();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return name + "(" + gson.toJson(this) + ")";
    }
}
