package com.starkbank.utils;


public abstract class Resource extends com.starkcore.utils.Resource {

    protected static Resource.ClassData data;
    protected Resource(String id){
        super(id);
    }
}
