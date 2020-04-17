package com.starkbank.user;

import com.starkbank.Boleto;
import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.utils.Check;
import com.starkbank.utils.Resource;

public class User extends Resource {
    public static ClassData data = new ClassData(User.class, "User");

    public String pem;
    public String environment;
    public User(String id, String privateKey, String environment) throws Exception {
        super(id);
        this.pem = Check.key(privateKey);
        this.environment = Check.environment(environment);
    }

    public String accessId() {
        return Resource.getName(data).toLowerCase() + '/' + this.id;
    }

    public PrivateKey privateKey(){
        return PrivateKey.fromPem(this.pem);
    }
}
