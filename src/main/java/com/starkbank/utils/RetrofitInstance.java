package com.starkbank.utils;

import com.starkbank.Project;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import java.util.concurrent.TimeUnit;

final class RetrofitInstance {
    private static ClientService productionInstance;
    private static ClientService sandboxInstance;
    private static final String version = "v2/";

    private RetrofitInstance()
    {

    }

    private static synchronized ClientService getProductionInstance()
    {
        if(productionInstance == null)
        {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.starkbank.com/" + version)
                    .client(client).build();
            productionInstance = retrofit.create(ClientService.class);
        }

        return productionInstance;
    }

    private static synchronized ClientService getSandboxInstance()
    {
        if(sandboxInstance == null)
        {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://sandbox.api.starkbank.com/" + version)
                    .client(client).build();
            sandboxInstance = retrofit.create(ClientService.class);
        }

        return sandboxInstance;
    }

    public static ClientService getProjectInstance(Project user) throws Exception {
        switch (user.environment)
        {
            case "production":
                return getProductionInstance();
            case "sandbox":
                return getSandboxInstance();
            default:
                throw new  Exception("Unexpected value: " + user.environment);
        }
    }
}
