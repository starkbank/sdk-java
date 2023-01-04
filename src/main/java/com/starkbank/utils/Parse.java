package com.starkbank.utils;

import com.starkcore.user.User;
import com.starkcore.utils.Resource;

public class Parse {
    public static <T extends Resource> T parseAndVerify(String content, String signature, Resource.ClassData resource, User user) throws Exception {
        return Relay.parseAndVerify(content, signature, resource, user);
    }
}
