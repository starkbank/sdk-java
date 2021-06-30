package com.starkbank.utils;


final class Api {
    static String endpoint(Resource.ClassData resource){
        return "/" + Case.camelToKebab(resource.name)
                         .replace("-log", "/log")
                         .replace("-attempt", "/attempt");
    }

    static String endpoint(Resource.ClassData resource, String id){
        return endpoint(resource) + "/" + id;
    }

    static String getLastNamePlural(Resource.ClassData resource){
        String lastName = getLastName(resource);
        if (lastName.endsWith("s"))
            return lastName;
        if (lastName.endsWith("ey"))
            return lastName + "s";
        if (lastName.endsWith("y"))
            return lastName.substring(0, lastName.length()-1) + "ies";
        return lastName + "s"; 
    }

    static String getLastName(Resource.ClassData resource){
        String kebabCase = Case.camelToKebab(resource.name);
        String[] kebabChunks = kebabCase.split("-");
        return kebabChunks[kebabChunks.length - 1];
    }
}
