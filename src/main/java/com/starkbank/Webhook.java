package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.HashMap;
import java.util.Map;


public final class Webhook extends Resource {
    static ClassData data = new ClassData(Webhook.class, "Webhook");

    public final String url;
    public final String[] subscriptions;

    /**
     * Webhook subscription object
     * <p>
     * A Webhook is used to subscribe to notification events on a user-selected endpoint.
     * Currently available services for subscription are transfer, boleto, boleto-payment,
     * and utility-payment
     * <p>
     * Parameters:
     * @param url [string]: Url that will be notified when an event occurs.
     * @param subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * Attributes:
     * @param id [string, default null]: unique id returned when the log is created. ex: "5656565656565656"
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
     * @param data map of properties for the creation of the WebHook
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * Attributes:
     * id [string, default null]: unique id returned when the log is created. ex: "5656565656565656"
     */
    public Webhook(Map<String, Object> data) {
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
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Webhook object with updated attributes
     * @throws Exception error in the request
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
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Webhook object with updated attributes
     * @throws Exception error in the request
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
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * <p>
     * Return:
     * @return generator of Webhook objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Webhook> query(Map<String, Object> params) throws Exception {
        return Webhook.query(params, null);
    }

    /**
     * Retrieve Webhook subcriptions
     * <p>
     * Receive a generator of Webhook subcription objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Webhook objects with updated attributes
     * @throws Exception error in the request
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
     * @return generator of Webhook objects with updated attributes
     * @throws Exception error in the request
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
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Webhook objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Webhook> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Create Webhook subscription
     * <p>
     * Send a single Webhook subscription for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param webhookData parameters for the creation of the webhook
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * <p>
     * Return:
     * @return Webhook object with updated attributes
     * @throws Exception error in the request
     */
    public static Webhook create(Map<String, Object> webhookData) throws Exception {
        return Webhook.create(webhookData, null);
    }

    /**
     * Create Webhook subscription
     * <p>
     * Send a single Webhook subscription for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param webhookData parameters for the creation of the webhook
     * url [string]: Url that will be notified when an event occurs.
     * subscriptions [list of strings]: list of any non-empty combination of the available services. ex: ["transfer", "boleto-payment"]
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Webhook object with updated attributes
     * @throws Exception error in the request
     */
    public static Webhook create(Map<String, Object> webhookData, Project user) throws Exception {
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
     * @param id [string]: Webhook unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted Webhook with updated attributes
     * @throws Exception error in the request
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
     * @param id [string]: Webhook unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted Webhook with updated attributes
     * @throws Exception error in the request
     */
    public static Webhook delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }
}
