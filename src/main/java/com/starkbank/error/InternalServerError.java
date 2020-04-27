package com.starkbank.error;

public final class InternalServerError extends RuntimeException{
    public InternalServerError(String message){
        super(message);
    }
}
