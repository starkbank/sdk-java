package com.starkbank.error;

public class InputError extends Error{
    public String code;
    public Integer status;

    public InputError(String code, String message){
        super(message);
        this.code = code;
        this.status = 400;
    }

    public InputError(String code, String message, Integer status){
        super(message);
        this.code = code;
        this.status = status;
    }
}
