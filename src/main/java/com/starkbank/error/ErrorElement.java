package com.starkbank.error;

public final class ErrorElement extends Error{
	private static final long serialVersionUID = 5417470047486626244L;
	public String code;
    public String message;

    public ErrorElement(String code, String message){
        super(code + ": " + message);
        this.code = code;
        this.message = message;
    }
}
