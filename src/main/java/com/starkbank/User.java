package com.starkbank;

import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.utils.Check;
import com.starkbank.utils.Resource;


public abstract class User extends Resource{
    public static Project defaultUser = null;

    public String pem;
    public String environment;

    public User(String environment, String id, String privateKey) throws Exception {
        super(id);
        this.environment = Check.environment(environment);
        this.pem = Check.key(privateKey);
    }

    public PrivateKey privateKey(){
        return PrivateKey.fromPem(this.pem);
    }
}
