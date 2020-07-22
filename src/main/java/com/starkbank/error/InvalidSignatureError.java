package com.starkbank.error;

public final class InvalidSignatureError extends RuntimeException{

    public InvalidSignatureError(String message){
        super(message);
    }
}
