package com.starkbank.utils;

import com.google.gson.*;
import com.starkbank.*;

import java.lang.reflect.Type;

public abstract class Resource {
    public static ClassData data = new Resource.ClassData(Resource.class, "Resource");
    public String id;
    public Resource(String id){
        this.id = id;
    }

    public static String endpoint(Resource.ClassData resource){
        return "/" + Case.decamelize(getName(resource), "-").replace("-log", "/log");
    }

    public static String endpoint(Resource.ClassData resource, String id){
        return endpoint(resource) + "/" + id;
    }

    public static String getName(Resource.ClassData resource){
        return resource.name;
    }

    public static String getLastName(Resource.ClassData resource){
        String kebabCase = Case.decamelize(getName(resource), "-");
        String[] kebabChunks = kebabCase.split("-");
        return kebabChunks[kebabChunks.length - 1];
    }

    public static String getLastPlural(Resource.ClassData resource){
        return getLastName(resource) + "s";
    }

    public String toString() {
        String name = this.getClass().getSimpleName();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return name + "(" + gson.toJson(this) + ")";
    }

    public static class ClassData {
        public String name;
        public Class cls;

        public ClassData(Class cls, String name){
            this.cls = cls;
            this.name = name;
        }
    }
}
