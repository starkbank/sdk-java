package com.starkbank;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class PaymentRequest extends Resource {
    static ClassData data = new ClassData(PaymentRequest.class, "PaymentRequest");
    /**
     * PaymentRequest object
     * <p>
     *     A PaymentRequest is an indirect request to access a specific cash-out service
     *     (such as Transfer, BoletoPayments, etc.) which goes through the cost center
     *     approval flow on our website. To emit a PaymentRequest, you must direct it to
     *     a specific cost center by its ID, which can be retrieved on our website at the
     *     cost center page.
     * </p>
     *
     * Parameters:
     * id [string]: unique id returned when PaymentRequest is created. ex: "5656565656565656"
     * centerId [string]: target cost center ID. ex: "5656565656565656"
     * payment [Transfer, BoletoPayment, BrcodePayment, UtilityPayment, Transaction or map]: payment entity that should be approved and executed.
     * type [string]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * due [string]: Payment target date in ISO format. ex: 2020-12-31
     * tags [list of strings]: list of strings for tagging
     * amount [long integer]: PaymentRequest amount. ex: 100000 = R$1.000,00
     * status [string]: current PaymentRequest status.ex: "pending" or "approved"
     * actions [list of maps]: list of actions that are affecting this PaymentRequest.ex: [{"type": "member", "id": "56565656565656, "action": "requested"}]
     * updated [String]: latest update datetime for the PaymentRequest. ex: 2020-12-31
     * created [String]: creation datetime for the PaymentRequest. ex: 2020-12-31
     */

    public String centerId;
    public Resource payment;
    public String type;
    public String due;
    public String[] tags;
    public Long amount;
    public String status;
    public List<PaymentRequest.Action> actions;
    public String updated;
    public String created;

    /**
     * PaymentRequest object
     * <p>
     * When you initialize a PaymentRequest, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * All parameters are passed in a Map of String and Object object.
     * <p>
     *
     * Parameters:
     * @param centerId [string]: unique id returned when PaymentRequest is created. ex: "5656565656565656"
     * @param payment [Transfer, BoletoPayment, BrcodePayment, UtilityPayment, Transaction or map]: payment entity that should be approved and executed.
     * @param type [string]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * @param due [string]: Payment target date in ISO format.
     * @param tags [list of strings]: list of strings for tagging
     * 
     * Attributes (return-only):
     * @param id [string]: id of the object
     * @param amount [long]: PaymentRequest amount. ex: 100000 = R$1.000,00
     * @param status [string]: current PaymentRequest status.ex: "pending" or "approved"
     * @param actions [list of PaymentRequest.Action, default null]: list of actions that are affecting this PaymentRequest.ex: [{"type": "member", "id": "56565656565656, "action": "requested"}]
     * @param updated [string]: latest update datetime for the PaymentRequest. ex: 2020-12-31
     * @param created [string]: creation datetime for the PaymentRequest. ex: 2020-12-31
     * @throws Exception error in the request
     */
    public PaymentRequest(String id, String centerId, Resource payment, String type, String due, String[] tags, Long amount,
                          String status, List<PaymentRequest.Action> actions, String updated, String created) throws Exception{
        super(id);
        this.centerId = centerId;
        this.payment = payment;
        this.due = due;
        this.tags = tags;
        this.amount = amount;
        this.status = status;
        this.actions = actions;
        this.updated = updated;
        this.created = created;

        this.type = type;
        if(type == null) {
            this.type = PaymentRequest.getType(payment);
        }
    }

    /**
     * PaymentRequest object
     * <p>
     * When you initialize a PaymentRequest, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * All parameters are passed in a Map of String and Object object.
     * <p>
     * @param data map of parameters for the creation of the PaymentRequest
     * Parameters:
     * centerId [string]: target cost center ID. ex: "5656565656565656"
     * payment [Transfer, BoletoPayment, BrcodePayment, UtilityPayment, Transaction or map]: payment entity that should be approved and executed.
     * <p>
     * Parameters (conditionally required):
     * type [string]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * <p>
     * Parameters (optional):
     * due [string]: Payment target date in ISO format. ex: 2020-12-31
     * tags [list of strings]: list of strings for tagging
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public PaymentRequest(Map<String, Object> data) throws Exception{
        super(null);

        HashMap<String, Object> dataCopy = new HashMap<>(data);
        this.centerId = (String) dataCopy.remove("centerId");
        this.payment = (Resource) dataCopy.remove("payment");
        this.due = (String) dataCopy.remove("due");
        this.tags = (String[]) dataCopy.remove("tags");
        this.amount = (Long) dataCopy.remove("amount");
        this.status = (String) dataCopy.remove("status");
        this.actions = (List<PaymentRequest.Action>) dataCopy.remove("actions");
        this.updated = (String) dataCopy.remove("updated");
        this.created = (String) dataCopy.remove("created");

        this.type = (String) dataCopy.remove("type");
        if(this.type == null) {
            this.type = PaymentRequest.getType(payment);
        }

        if(!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor : [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    @SuppressWarnings("unchecked")
    public static class Deserializer implements JsonDeserializer<PaymentRequest> {
        @Override
        public PaymentRequest deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctw) throws JsonParseException {
            JsonElement resourceElement = json.getAsJsonObject().get("payment");
            json.getAsJsonObject().remove("payment");
            PaymentRequest request = new Gson().fromJson(json, PaymentRequest.class);
            Resource resource = null;
            switch (request.type) {
                case "transfer":
                    resource = new Gson().fromJson(resourceElement, Transfer.class);
                    break;
                case "transaction":
                    resource = new Gson().fromJson(resourceElement, Transaction.class);
                    break;
                case "boleto-payment":
                    resource = new Gson().fromJson(resourceElement, BoletoPayment.class);
                    break;
                case "utility-payment":
                    resource = new Gson().fromJson(resourceElement, UtilityPayment.class);
                    break;
                case "tax-payment":
                    resource = new Gson().fromJson(resourceElement, TaxPayment.class);
                    break;
                case "darf-payment":
                    resource = new Gson().fromJson(resourceElement, DarfPayment.class);
                    break;
                case "brcode-payment":
                    resource = new Gson().fromJson(resourceElement, BrcodePayment.class);
                    break;
                default:
                    break;
            }

            request.payment = resource;

            resourceElement = json.getAsJsonObject().get("actions");
            for (LinkedTreeMap<Object, Object> action : (List<LinkedTreeMap<Object, Object>>) new Gson().fromJson(resourceElement, List.class)){
                request.actions.add(new PaymentRequest.Action(
                    (String) action.get("name"),
                    (String) action.get("action"),
                    (String) action.get("type"),
                    (String) action.get("id")
                ));
            }

            return request;
        }
    }

    /**
     * Retrieve PaymentRequests
     * <p>
     * Receive a generator of PaymentRequest objects previously created in the Stark Bank API
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "-created" or "-due".
     * status [string, default null]: filter for status of retrieved objects. ex: "success" or "failed"
     * type [string, default null]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of PaymentRequest objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<PaymentRequest> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve PaymentRequests
     * <p>
     * Receive a generator of PaymentRequest objects previously created in the Stark Bank API
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "-created" or "-due".
     * status [string, default null]: filter for status of retrieved objects. ex: "success" or "failed"
     * type [string, default null]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of PaymentRequest objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<PaymentRequest> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve PaymentRequests
     * <p>
     * Receive a generator of PaymentRequest objects previously created in the Stark Bank API
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of PaymentRequest objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<PaymentRequest> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve PaymentRequests
     * <p>
     * Receive a generator of PaymentRequest objects previously created in the Stark Bank API
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * Return:
     * @return generator of PaymentRequest objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<PaymentRequest> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<PaymentRequest> requests;
        public String cursor;

        public Page(List<PaymentRequest> requests, String cursor) {
            this.requests = requests;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged PaymentRequests
     * <p>
     * Receive a list of up to 100 PaymentRequest objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "-created" or "-due".
     * status [string, default null]: filter for status of retrieved objects. ex: "success" or "failed"
     * type [string, default null]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return PaymentRequest.Page object:
     * PaymentRequest.Page.requests: list of PaymentRequest objects with updated attributes
     * PaymentRequest.Page.cursor: cursor to retrieve the next page of PaymentRequest objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged PaymentRequests
     * <p>
     * Receive a list of up to 100 PaymentRequest objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return PaymentRequest.Page object:
     * PaymentRequest.Page.requests: list of PaymentRequest objects with updated attributes
     * PaymentRequest.Page.cursor: cursor to retrieve the next page of PaymentRequest objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged PaymentRequests
     * <p>
     * Receive a list of up to 100 PaymentRequest objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return PaymentRequest.Page object:
     * PaymentRequest.Page.requests: list of PaymentRequest objects with updated attributes
     * PaymentRequest.Page.cursor: cursor to retrieve the next page of PaymentRequest objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged PaymentRequests
     * <p>
     * Receive a list of up to 100 PaymentRequest objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "-created" or "-due".
     * status [string, default null]: filter for status of retrieved objects. ex: "success" or "failed"
     * type [string, default null]: payment type, inferred from the payment parameter if it is not a map. ex: "transfer", "boleto-payment"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return PaymentRequest.Page object:
     * PaymentRequest.Page.requests: list of PaymentRequest objects with updated attributes
     * PaymentRequest.Page.cursor: cursor to retrieve the next page of PaymentRequest objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<PaymentRequest> requests = new ArrayList<>();
        for (SubResource request: page.entities) {
            requests.add((PaymentRequest) request);
        }
        return new Page(requests, page.cursor);
    }

    /**
     * Create paymentRequests
     * Sends a list of PaymentRequests objects for creation in the Stark Bank API
     *
     * Parameters (required):
     * @param paymentRequests [list of PaymentRequest objects]: list of PaymentRequest objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * @return list of PaymentRequest objects with updated attributes
     * @throws Exception When list contains unknown objects
     */
    @SuppressWarnings("unchecked")
    public static List<PaymentRequest> create(List<?> paymentRequests, User user) throws Exception {
        List<PaymentRequest> paymentRequestList = new ArrayList<>();
        for(Object paymentRequest : paymentRequests) {
            if(paymentRequest.getClass() == HashMap.class) {
                paymentRequestList.add(new PaymentRequest((Map<String, Object>) paymentRequest));
                continue;
            }
            if(paymentRequest.getClass() == PaymentRequest.class) {
                paymentRequestList.add((PaymentRequest) paymentRequest);
                continue;
            }
            throw new Exception("Unknown type \"" + paymentRequest.getClass() + "\", use PaymentRequest or HashMap.");
        }

        return Rest.post(data, paymentRequestList, user);
    }

    /**
     * Create paymentRequests
     * Sends a list of PaymentRequests objects for creation in the Stark Bank API
     *
     * Parameters (required):
     * @param paymentRequests [list of PaymentRequest objects]: list of PaymentRequest objects to be created in the API
     * @return list of PaymentRequest objects with updated attributes
     * @throws Exception When list contains unknown objects
     */
    @SuppressWarnings("unchecked")
    public static List<PaymentRequest> create(List<?> paymentRequests) throws Exception {
        List<PaymentRequest> paymentRequestList = new ArrayList<>();
        for(Object paymentRequest : paymentRequests) {
            if(paymentRequest.getClass() == HashMap.class) {
                paymentRequestList.add(new PaymentRequest((Map<String, Object>) paymentRequest));
                continue;
            }
            if(paymentRequest.getClass() == PaymentRequest.class) {
                paymentRequestList.add((PaymentRequest) paymentRequest);
                continue;
            }
            throw new Exception("Unknown type \"" + paymentRequest.getClass() + "\", use PaymentRequest or HashMap.");
        }

        return Rest.post(data, paymentRequestList, null);
    }

    private static String getType(Resource payment) throws Exception{
        if(payment instanceof Transfer)
            return "transfer";
        if(payment instanceof Transaction)
            return "transaction";
        if(payment instanceof BoletoPayment)
            return "boleto-payment";
        if(payment instanceof UtilityPayment)
            return "utility-payment";
        if(payment instanceof TaxPayment)
            return "tax-payment";
        if(payment instanceof DarfPayment)
            return "darf-payment";
        if(payment instanceof BrcodePayment)
            return "brcode-payment";

        throw new Exception("Payment must either be a Transfer, a Transaction, a BoletoPayment, a BrcodePayment, " +
                "a UtilityPayment, a TaxPayment or a DarfPayment.");
    }

    /**
     * PaymentRequest.Action object
     * <p>
     * Gives information about an action taken on the PaymentRequest
     * <p>
     * Parameters:
     * name [string]: name of the user that took the action. ex: "Stark Project"
     * action [string]: action type. ex "requested", "approved"
     * type [string]: type of the user that took the action. ex: "project", "member"
     * id [string]: ID of the user that took the action. ex: "5129086980587520"
     */
    public final static class Action extends SubResource{
        public String name;
        public String action;
        public String type;
        public String id;

        /**
         * PaymentRequest.Action object
         * 
         * Used to define a action in the payment request
         * 
         * Parameters:
         * @param name [string]: name of the user that took the action. ex: "Stark Project"
         * @param action [string]: action type. ex "requested", "approved"
         * @param type [string]: type of the user that took the action. ex: "project", "member"
         * @param id [string]: ID of the user that took the action. ex: "5129086980587520"
         */
        public Action(String name, String action, String type, String id){
            this.name = name;
            this.action = action;
            this.type = type;
            this.id = id;
        }
    }
}
