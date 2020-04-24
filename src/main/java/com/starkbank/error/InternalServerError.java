package com.starkbank.error;

public final class InternalServerError extends Error{
	private static final long serialVersionUID = 4458611990624094212L;

	public InternalServerError(String message){
        super(message);
    }
}
