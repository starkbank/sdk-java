package com.starkbank.utils;


final class Api {
    static String endpoint(Resource.ClassData resource){
        return "/" + Case.camelToKebab(resource.name).replace("-log", "/log");
    }

    static String endpoint(Resource.ClassData resource, String id){
        return endpoint(resource) + "/" + id;
    }

    static String getLastNamePlural(Resource.ClassData resource){
        return getLastName(resource) + "s";
    }

    static String getLastName(Resource.ClassData resource){
        String kebabCase = Case.camelToKebab(resource.name);
        String[] kebabChunks = kebabCase.split("-");
        return kebabChunks[kebabChunks.length - 1];
    }
}
