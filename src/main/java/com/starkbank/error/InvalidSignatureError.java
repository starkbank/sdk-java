package com.starkbank.error;

public class InvalidSignatureError extends Error{
    public InvalidSignatureError(String message){
        super(message);
    }
}
