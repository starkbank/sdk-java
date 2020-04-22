package com.starkbank.error;

public final class InvalidSignatureError extends Error{
    public InvalidSignatureError(String message){
        super(message);
    }
}
