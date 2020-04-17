package com.starkbank;

import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Key {
    public PrivateKey privateKey;
    public PublicKey publicKey;
    public String privatePem;
    public String publicPem;

    public Key(String privatePem){
        this.privatePem = privatePem;
        this.privateKey = PrivateKey.fromPem(this.privatePem);
        this.publicKey = this.privateKey.publicKey();
        this.publicPem = this.publicKey.toPem();
    }

    public static Key create() {
        PrivateKey privateKey = new PrivateKey();
        return new Key(privateKey.toPem());
    }

    public static Key create(String savePath) throws FileNotFoundException {
        Key key = Key.create();
        try (PrintWriter out = new PrintWriter(savePath + "/privateKey.pem")) {
            out.println(key.privatePem);
        }
        try (PrintWriter out = new PrintWriter(savePath + "/publicKey.pem")) {
            out.println(key.publicPem);
        }
        return key;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Key.create(".");
    }
}
