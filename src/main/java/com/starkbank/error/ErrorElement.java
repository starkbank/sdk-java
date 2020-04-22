package com.starkbank.error;

public final class ErrorElement extends Error{
    public String code;

    public ErrorElement(String code, String message){
        super(message);
        this.code = code;
    }
}
