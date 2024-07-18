package com.starkbank;

import com.starkbank.utils.Check;
import com.starkbank.utils.Resource;
import com.starkbank.ellipticcurve.PrivateKey;


public abstract class User extends com.starkcore.user.User {
    public User(String environment, String id, String privateKey) throws Exception {
        super(environment, id, privateKey);
    }
}
