package com.starkbank.error;

public final class InvalidSignatureError extends StarkBankError {

    public InvalidSignatureError(String message) {
        super(message);
    }
}
