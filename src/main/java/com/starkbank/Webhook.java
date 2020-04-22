package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.HashMap;


public final class Webhook extends Resource {
    static ClassData data = new ClassData(Webhook.class, "Webhook");

    public String url;
    public String[] subscriptions;

    /**
     * Webhook subscription object
     * <p>
     * A Webhook is used to subscribe to notification events on a user-selected endpoint.
     * Currently available services for subscription are transfer, boleto, boleto-payment,
     * and utility-payment
     * <p>
     * Parameters:
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * Attributes:
     * id [string, default null]: unique id returned when the log is created. ex: "5656565656565656"
     */
    public Webhook(String url, String[] subscriptions, String id) {
        super(id);
        this.url = url;
        this.subscriptions = subscriptions;
    }

    /**
     * Webhook subscription object
     * <p>
     * A Webhook is used to subscribe to notification events on a user-selected endpoint.
     * Currently available services for subscription are transfer, boleto, boleto-payment,
     * and utility-payment
     * <p>
     * Parameters:
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * Attributes:
     * id [string, default null]: unique id returned when the log is created. ex: "5656565656565656"
     */
    public Webhook(HashMap<String, Object> data) {
        super(null);
        this.url = (String) data.get("url");
        this.subscriptions = (String[]) data.get("subscriptions");
    }

    /**
     * Retrieve a specific Webhook subscription
     * <p>
     * Receive a single Webhook subscription object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * Webhook object with updated attributes
     */
    public static Webhook get(String id) throws Exception {
        return Webhook.get(id, null);
    }

    /**
     * Retrieve a specific Webhook subscription
     * <p>
     * Receive a single Webhook subscription object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * Webhook object with updated attributes
     */
    public static Webhook get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Webhook subcriptions
     * <p>
     * Receive a generator of Webhook subcription objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * <p>
     * Return:
     * generator of Webhook objects with updated attributes
     */
    public static Generator<Webhook> query(HashMap<String, Object> params) throws Exception {
        return Webhook.query(params, null);
    }

    /**
     * Retrieve Webhook subcriptions
     * <p>
     * Receive a generator of Webhook subcription objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * generator of Webhook objects with updated attributes
     */
    public static Generator<Webhook> query(Project user) throws Exception {
        return Webhook.query(new HashMap<>(), user);
    }

    /**
     * Retrieve Webhook subcriptions
     * <p>
     * Receive a generator of Webhook subcription objects previously created in the Stark Bank API
     * <p>
     * Return:
     * generator of Webhook objects with updated attributes
     */
    public static Generator<Webhook> query() throws Exception {
        return Webhook.query(new HashMap<>(), null);
    }

    /**
     * Retrieve Webhook subcriptions
     * <p>
     * Receive a generator of Webhook subcription objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * generator of Webhook objects with updated attributes
     */
    public static Generator<Webhook> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Create Webhook subscription
     * <p>
     * Send a single Webhook subscription for creation in the Stark Bank API
     * <p>
     * Parameters:
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * Webhook object with updated attributes
     */
    public static Webhook create(HashMap<String, Object> webhookData) throws Exception {
        return Webhook.create(webhookData, null);
    }

    /**
     * Create Webhook subscription
     * <p>
     * Send a single Webhook subscription for creation in the Stark Bank API
     * <p>
     * Parameters:
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * Webhook object with updated attributes
     */
    public static Webhook create(HashMap<String, Object> webhookData, Project user) throws Exception {
        String url = (String) webhookData.get("url");
        String[] subscriptions = (String[]) webhookData.get("subscriptions");
        return Rest.postSingle(data, new Webhook(url, subscriptions, null), user);
    }

    /**
     * Delete a Webhook subscription entity
     * <p>
     * Delete a Webhook subscription entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * id [string]: Webhook unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * deleted Webhook with updated attributes
     */
    public static Webhook delete(String id) throws Exception {
        return Webhook.delete(id, null);
    }

    /**
     * Delete a Webhook subscription entity
     * <p>
     * Delete a Webhook subscription entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * id [string]: Webhook unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * deleted Webhook with updated attributes
     */
    public static Webhook delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }
}
