package com.starkbank;

import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.utils.Check;
import com.starkbank.utils.Resource;


public abstract class User extends Resource{
    static Resource.ClassData data = new Resource.ClassData(com.starkbank.User.class, "User");

    public static Project defaultUser = null;
    public static PublicKey StarkBankPublicKey = null;

    public String pem;
    public String environment;

    public User(String id, String privateKey, String environment) throws Exception {
        super(id);
        this.pem = Check.key(privateKey);
        this.environment = Check.environment(environment);
    }

    public PublicKey publicKey(){
        return this.privateKey().publicKey();
    }

    public PrivateKey privateKey(){
        return PrivateKey.fromPem(this.pem);
    }
}
