package com.starkbank.utils;

import com.starkbank.Event;
import com.starkbank.PaymentRequest;
import com.starkbank.Transfer;
import com.starkbank.error.ErrorElement;
import com.starkcore.utils.GsonEvent;

public class GsonAdapters {
    private static boolean initialized = false;

    public static synchronized void setup() {
        if (initialized) {
            return;
        }

        GsonEvent.registerTypeAdapter(ErrorElement.class, new ErrorElement.Deserializer());
        GsonEvent.registerTypeAdapter(Event.class, new Event.Deserializer());
        GsonEvent.registerTypeAdapter(PaymentRequest.class, new PaymentRequest.Deserializer());
        GsonEvent.registerTypeAdapter(Transfer.Rule.class, new Transfer.Rule.Deserializer());
        initialized = true;
    }
}
