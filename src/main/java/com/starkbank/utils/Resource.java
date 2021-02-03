package com.starkbank.utils;


public abstract class Resource extends SubResource {

    protected static Resource.ClassData data;

    public String id;
    protected Resource(String id){
        this.id = id;
    }
}
