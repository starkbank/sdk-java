package com.starkbank.utils;
import com.starkcore.utils.SubResource;

import java.util.List;

public class Page extends com.starkcore.utils.Page {

    public Page(List<SubResource> entities, String cursor) {
        super(entities, cursor);
    }
}
