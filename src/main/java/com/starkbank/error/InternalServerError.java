package com.starkbank.error;

public final class InternalServerError extends Error{
    public InternalServerError(String message){
        super(message);
    }
}
