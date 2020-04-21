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

    public Key(String privatePem) {
        this.privatePem = privatePem;
        this.privateKey = PrivateKey.fromPem(this.privatePem);
        this.publicKey = this.privateKey.publicKey();
        this.publicPem = this.publicKey.toPem();
    }

    /**
     * Create Key pair
     * <p>
     * Generates a secp256k1 ECDSA private/public key pair to be used in the API authentications
     * <p>
     * Return:
     * new Key object
     */
    public static Key create() {
        PrivateKey privateKey = new PrivateKey();
        return new Key(privateKey.toPem());
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of Boleto objects for creation in the Stark Bank API. Saves private and
     * public key PEMs in specified folder path
     * <p>
     * Parameters:
     * savePath [string]: path to save the keys .pem files. No files will be saved if this parameter isn't provided
     * <p>
     * Return:
     * new Key object
     */
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
