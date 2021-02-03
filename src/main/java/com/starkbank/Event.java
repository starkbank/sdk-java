package com.starkbank;

import com.google.gson.*;
import com.starkbank.ellipticcurve.Ecdsa;
import com.starkbank.ellipticcurve.PublicKey;
import com.starkbank.ellipticcurve.Signature;
import com.starkbank.ellipticcurve.utils.ByteString;
import com.starkbank.error.InvalidSignatureError;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Response;
import com.starkbank.utils.Rest;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class Event extends Resource {
    static ClassData data = new ClassData(Event.class, "Event");

    public String created;
    public Boolean isDelivered;
    public String subscription;

    /**
     * Webhook Event object
     * <p>
     * An Event is the notification received from the subscription to the Webhook.
     * Events cannot be created, but may be retrieved from the Stark Bank API to
     * list all generated updates on entities.
     * <p>
     * Attributes:
     * @param id [string]: unique id returned when the event is created. ex: "5656565656565656"
     * @param created [string]: creation datetime for the notification event. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param isDelivered [bool]: true if the event has been successfully delivered to the user url. ex: false
     * @param subscription [string]: service that triggered this event. ex: "transfer", "utility-payment"
     */
    public Event(String created, Boolean isDelivered, String subscription, String id) {
        super(id);
        this.created = created;
        this.isDelivered = isDelivered;
        this.subscription = subscription;
    }

    public static class Deserializer implements JsonDeserializer<Event> {
        @Override
        public Event deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
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
                    case "boleto-holmes":
                        return context.deserialize(jsonObject,
                                BoletoHolmesEvent.class);
                    case "invoice":
                        return context.deserialize(jsonObject,
                                InvoiceEvent.class);
                    case "deposit":
                        return context.deserialize(jsonObject,
                                DepositEvent.class);
                    case "brcode-payment":
                        return context.deserialize(jsonObject,
                                BrcodePaymentEvent.class);
                    default:
                        return context.deserialize(jsonObject,
                                UnknownEvent.class);
                }
            }
            return null;
        }
    }

    public final static class TransferEvent extends Event {
        public Transfer.Log log;

        public TransferEvent(Transfer.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class BoletoEvent extends Event {
        public Boleto.Log log;

        public BoletoEvent(Boleto.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class BoletoPaymentEvent extends Event {
        public BoletoPayment.Log log;

        public BoletoPaymentEvent(BoletoPayment.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class UtilityPaymentEvent extends Event {
        public UtilityPayment.Log log;

        public UtilityPaymentEvent(UtilityPayment.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class BoletoHolmesEvent extends Event {
        public BoletoHolmes.Log log;

        public BoletoHolmesEvent(BoletoHolmes.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class InvoiceEvent extends Event {
        public Invoice.Log log;

        public InvoiceEvent(Invoice.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class DepositEvent extends Event {
        public Deposit.Log log;

        public DepositEvent(Deposit.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    public final static class BrcodePaymentEvent extends Event {
        public BrcodePayment.Log log;

        public BrcodePaymentEvent(BrcodePayment.Log log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }
    
    public final static class UnknownEvent extends Event {
        public JsonObject log;

        public UnknownEvent(JsonObject log, String created, Boolean isDelivered, String subscription, String id) {
            super(created, isDelivered, subscription, id);
            this.log = log;
        }
    }

    /**
     * Retrieve a specific notification Event
     * <p>
     * Receive a single notification Event object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static Event get(String id) throws Exception {
        return Event.get(id, null);
    }

    /**
     * Retrieve a specific notification Event
     * <p>
     * Receive a single notification Event object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static Event get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve notification Events
     * <p>
     * Receive a generator of notification Event objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
     * isDelivered [bool, default null]: bool to filter successfully delivered events. ex: true or false
     * <p>
     * Return:
     * @return generator of Event objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Event> query(Map<String, Object> params) throws Exception {
        return Event.query(params, null);
    }

    /**
     * Retrieve notification Events
     * <p>
     * Receive a generator of notification Event objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Event objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Event> query(User user) throws Exception {
        return Event.query(new HashMap<>(), user);
    }

    /**
     * Retrieve notification Events
     * <p>
     * Receive a generator of notification Event objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of Event objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Event> query() throws Exception {
        return Event.query(new HashMap<>(), null);
    }

    /**
     * Retrieve notification Events
     * <p>
     * Receive a generator of notification Event objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
     * isDelivered [bool, default null]: bool to filter successfully delivered events. ex: true or false
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Event objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Event> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Delete notification Events
     * <p>
     * Delete a list of notification Event entities previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: Event unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted Event object
     * @throws Exception error in the request
     */
    public static Event delete(String id) throws Exception {
        return Event.delete(id, null);
    }

    /**
     * Delete notification Events
     * <p>
     * Delete a list of notification Event entities previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: Event unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted Event object
     * @throws Exception error in the request
     */
    public static Event delete(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    /**
     * Update notification Event entity
     * <p>
     * Update notification Event by passing id.
     * If isDelivered is True, the event will no longer be returned on queries with isDelivered=False.
     * <p>
     * Parameters:
     * @param id [string]: Event unique ids. ex: "5656565656565656"
     * @param patchData map of parameters to patch
     * isDelivered [bool]: If True and event hasn't been delivered already, event will be set as delivered. ex: true
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static Event update(String id, Map<String, Object> patchData) throws Exception {
        return Event.update(id, patchData, null);
    }

    /**
     * Update notification Event entity
     * <p>
     * Update notification Event by passing id.
     * If isDelivered is True, the event will no longer be returned on queries with isDelivered=false.
     * <p>
     * Parameters:
     * @param id [string]: Event unique ids. ex: "5656565656565656"
     * @param patchData map of properties to patch
     * isDelivered [bool]: If True and event hasn't been delivered already, event will be set as delivered. ex: true
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static Event update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }

    /**
     * Create single notification Event from a content string
     * <p>
     * Create a single Event object received from event listening at subscribed user endpoint.
     * If the provided digital signature does not check out with the StarkBank public key, a
     * starkbank.exception.InvalidSignatureException will be raised.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static Event parse(String content, String signature) throws Exception {
        return Event.parse(content, signature, Settings.user);
    }

    /**
     * Create single notification Event from a content string
     * <p>
     * Create a single Event object received from event listening at subscribed user endpoint.
     * If the provided digital signature does not check out with the StarkBank public key, a
     * starkbank.exception.InvalidSignatureException will be raised.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Event object with updated attributes
     * @throws Exception error in the request
     */
    public static Event parse(String content, String signature, User user) throws Exception {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Event.class, new Event.Deserializer())
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
                .create();
        Event parsedEvent = gson.fromJson(
                new Gson().fromJson(content, JsonObject.class).get("event").getAsJsonObject(),
                Event.class
        );

        Signature signatureObject;
        try {
            signatureObject = Signature.fromBase64(new ByteString(signature.getBytes()));
        } catch (Error | RuntimeException e) {
            throw new InvalidSignatureError("The provided signature is not valid");
        }

        if (verifySignature(user, content, signatureObject, false)) {
            return parsedEvent;
        }
        if (verifySignature(user, content, signatureObject, true)) {
            return parsedEvent;
        }

        throw new InvalidSignatureError("The provided signature and content do not match the Stark Bank public key");
    }

    private static boolean verifySignature(User user, String content, Signature signature, boolean refresh) throws Exception {
        PublicKey publicKey = Cache.starkBankPublicKey;
        if (publicKey == null || refresh) {
            publicKey = getStarkBankPublicKey(user);
            Cache.starkBankPublicKey = publicKey;
        }
        return Ecdsa.verify(content, signature, publicKey);
    }

    private static PublicKey getStarkBankPublicKey(User user) throws Exception {
        HashMap<String, Object> query = new HashMap<>();
        query.put("limit", "1");
        String content = Response.fetch(
                "/public-key",
                "GET",
                null,
                query,
                user
        ).content();
        JsonObject contentJson = new Gson().fromJson(content, JsonObject.class);
        JsonArray publicKeys = contentJson.get("publicKeys").getAsJsonArray();
        return PublicKey.fromPem(
                publicKeys.get(0).getAsJsonObject().get("content").getAsString()
        );
    }
}
