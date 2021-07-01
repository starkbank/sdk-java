package com.starkbank.utils;

import java.util.List;

public class Page {
    public List<SubResource> entities;
    public String cursor;

    public Page(List<SubResource> entities, String cursor) {
        this.entities = entities;
        this.cursor = cursor;
    }
}
