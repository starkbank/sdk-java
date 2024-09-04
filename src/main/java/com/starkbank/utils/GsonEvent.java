package com.starkbank.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.starkbank.Event;
import com.starkbank.PaymentRequest;


public class GsonEvent {
     public static Gson instance;

     public GsonEvent() {}

     public static synchronized Gson getInstance()
     {
         if(instance == null)
             instance = new GsonBuilder()
                     .registerTypeAdapter(Event.class, new Event.Deserializer())
                     .registerTypeAdapter(PaymentRequest.class, new PaymentRequest.Deserializer())
                     .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
                     .create();
         return instance;
     }
}
