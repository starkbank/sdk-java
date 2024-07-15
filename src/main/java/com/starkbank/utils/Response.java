package com.starkbank.utils;

import com.google.gson.JsonObject;
import com.starkbank.User;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.error.InputErrors;
import com.starkbank.error.InternalServerError;
import com.starkbank.error.UnknownError;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.starkbank.Settings.userAgentOverride;
import static java.lang.System.currentTimeMillis;


public final class Response extends com.starkcore.utils.Response {

    public Response(int status, InputStream stream) {
        super(status, stream);
    }
}
