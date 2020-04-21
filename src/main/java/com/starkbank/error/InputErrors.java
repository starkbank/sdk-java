package com.starkbank.error;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class InputErrors extends Error {
    public List<ErrorElement> errors;

    public InputErrors(String content) {
        super(content);
        this.errors = new ArrayList<>();

        JsonObject errorsJson = new Gson().fromJson(content, JsonObject.class);
        System.out.println(errorsJson.toString());
        if (!errorsJson.get("errors").isJsonNull()) {
            for (JsonElement error : errorsJson.get("errors").getAsJsonArray()) {
                JsonObject errorJson = error.getAsJsonObject();
                errors.add(
                        new ErrorElement(
                                errorJson.get("code").getAsString(),
                                errorJson.get("message").getAsString()
                        )
                );
            }
        }
    }
}
