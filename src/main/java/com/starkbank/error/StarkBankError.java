package com.starkbank.error;

public abstract class StarkBankError extends com.starkcore.error.StarkError {

    public StarkBankError(String message) {
        super(message);
    }
}
