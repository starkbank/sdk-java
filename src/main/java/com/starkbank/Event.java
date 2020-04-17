package com.starkbank;

import com.google.gson.*;
import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import javax.print.attribute.standard.RequestingUserName;
import java.lang.reflect.Type;
import java.util.HashMap;

public class Event extends Resource {
    public static ClassData data = new ClassData(Event.class, "Event");

    public String created;
    public String delivered;
    public String subscription;
//    public Resource log;

    public Event(String created, String delivered, String subscription, String id){
        super(id);
        this.created = created;
        this.delivered = delivered;
        this.subscription = subscription;
//        this.log = log;
    }

    public static class Deserializer implements JsonDeserializer<Event> {
        @Override
        public Event deserialize(JsonElement json, Type typeOfT,
                                 JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement type = jsonObject.get("subscription");
            if (type != null) {
                switch (type.getAsString()) {
                    case "transfer":
                        return context.deserialize(jsonObject,
                                TransferEvent.class);
                    case "boleto":
                        return context.deserialize(jsonObject,
                                BoletoEvent.class);
                    case "boleto-payment":
                        return context.deserialize(jsonObject,
                                BoletoPaymentEvent.class);
                    case "utility-payment":
                        return context.deserialize(jsonObject,
                                UtilityPaymentEvent.class);
                }
            }
            return null;
        }
    }

    public static class TransferEvent extends Event{
        public Transfer.Log log;

        public TransferEvent(Transfer.Log log, String created, String delivered, String subscription, String id) {
            super(created, delivered, subscription, id);
            this.log = log;
        }
    }

    public static class BoletoEvent extends Event{
        public Boleto.Log log;

        public BoletoEvent(Boleto.Log log, String created, String delivered, String subscription, String id) {
            super(created, delivered, subscription, id);
            this.log = log;
        }
    }

    public static class BoletoPaymentEvent extends Event{
        public BoletoPayment.Log log;

        public BoletoPaymentEvent(BoletoPayment.Log log, String created, String delivered, String subscription, String id) {
            super(created, delivered, subscription, id);
            this.log = log;
        }
    }

    public static class UtilityPaymentEvent extends Event{
        public UtilityPayment.Log log;

        public UtilityPaymentEvent(UtilityPayment.Log log, String created, String delivered, String subscription, String id) {
            super(created, delivered, subscription, id);
            this.log = log;
        }
    }

    public static Event get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static Event get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<Event> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<Event> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<Event> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    public static Event delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }
}
