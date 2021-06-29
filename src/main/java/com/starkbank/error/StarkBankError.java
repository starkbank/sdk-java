package com.starkbank.error;

public abstract class StarkBankError extends RuntimeException {

    public StarkBankError(String message) {
        super(message);
    }
}
