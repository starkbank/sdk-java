package com.starkbank.error;

public final class InvalidSignatureError extends RuntimeException{
    private static final long serialVersionUID = 1802886978614069344L;

    public InvalidSignatureError(String message){
        super(message);
    }
}
