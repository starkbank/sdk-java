package com.starkbank.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public abstract class SubResource {

    public String toString() {
        String name = this.getClass().getSimpleName();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return name + "(" + gson.toJson(this) + ")";
    }
}
