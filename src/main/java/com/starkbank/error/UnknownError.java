package com.starkbank.error;

public final class UnknownError extends RuntimeException{

    public UnknownError(String message){
        super(message);
    }
}
