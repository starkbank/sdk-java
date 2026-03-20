package com.starkbank.error;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public final class ErrorElement extends StarkBankError {

	public String code;
    public String message;

    public ErrorElement(String code, String message) {
        super(code + ": " + message);
        this.code = code;
        this.message = message;
    }

    public static class Deserializer implements JsonDeserializer<ErrorElement> {
        @Override
        public ErrorElement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctw) throws JsonParseException {
            return new ErrorElement(json.getAsJsonObject().get("code").getAsString(), json.getAsJsonObject().get("message").getAsString());
        }
    }
}
