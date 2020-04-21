package com.starkbank.error;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class InputErrors extends Error {
    public Integer status;
    public List<InputError> errors;

    public InputErrors(String content, Integer status) {
        super(content);
        this.status = status;
        this.errors = new ArrayList<>();

        JsonObject errorsJson = new Gson().fromJson(content, JsonObject.class);
        for (JsonElement error : errorsJson.get("errors").getAsJsonArray()) {
            JsonObject errorJson = error.getAsJsonObject();
            errors.add(new InputError(errorJson.get("code").getAsString(), errorJson.get("message").getAsString(), status));
        }
    }
}
