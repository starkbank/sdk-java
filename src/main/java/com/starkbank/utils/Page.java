package com.starkbank.utils;

import java.util.List;

public class Page {
    public List<Resource> entities;
    public String cursor;

    public Page(List<Resource> entities, String cursor) {
        this.entities = entities;
        this.cursor = cursor;
    }
}
