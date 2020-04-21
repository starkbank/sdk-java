package com.starkbank.error;

public class InternalServerError extends Error{
    public InternalServerError(String message){
        super(message);
    }
}
