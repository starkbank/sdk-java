package com.starkbank;

import com.starkbank.ellipticcurve.PrivateKey;
import com.starkbank.ellipticcurve.PublicKey;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class Key {
    public String privatePem;
    public String publicPem;

    public Key(String privatePem) {
        PrivateKey privateKey = PrivateKey.fromPem(privatePem);
        this.privatePem = privatePem;
        this.publicPem = privateKey.publicKey().toPem();
    }

    /**
     * Create a key pair
     * <p>
     * Generates a secp256k1 ECDSA private/public key pair to be used in the API authentications
     * <p>
     * Return:
     * new Key object
     */
    public static Key create() {
        return new Key((new PrivateKey()).toPem());
    }

    /**
     * Create a key pair
     * <p>
     * Generates a secp256k1 ECDSA private/public key pair to be used in the API authentications
     * <p>
     * Parameters:
     * savePath [string]: path to save the keys .pem files. No files will be saved if this parameter isn't provided
     * <p>
     * Return:
     * new Key object
     */
    public static Key create(String savePath) throws FileNotFoundException {
        Key key = Key.create();
        File directory = new File(savePath);
        if (! directory.exists()){
            directory.mkdirs();
        }
        try (PrintWriter out = new PrintWriter(savePath + "/privateKey.pem")) {
            out.println(key.privatePem);
        }
        try (PrintWriter out = new PrintWriter(savePath + "/publicKey.pem")) {
            out.println(key.publicPem);
        }
        return key;
    }
}
