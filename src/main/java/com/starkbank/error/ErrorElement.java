package com.starkbank.error;

public final class ErrorElement extends Error{
    public String code;
    public String message;

    public ErrorElement(String code, String message){
        super(code + ": " + message);
        this.code = code;
        this.message = message;
    }
}
