package com.starkbank.error;

public class InternalServerError extends Error{
    public Integer status;

    public InternalServerError(String message){
        super(message);
        this.status = 500;
    }

    public InternalServerError(String message, Integer status){
        super(message);
        this.status = status;
    }
}
