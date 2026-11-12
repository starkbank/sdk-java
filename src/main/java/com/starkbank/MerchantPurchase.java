package com.starkbank;

import com.starkbank.error.ErrorElement;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MerchantPurchase extends Resource {

    /**
     # MerchantPurchase object
     Check out our API Documentation at https://starkbank.com/docs/api#merchant-purchase
     **/

    static ClassData data = new ClassData(MerchantPurchase.class, "MerchantPurchase");

    public long amount;
    public int installmentCount;
    public String cardId;
    public String holderName;
    public String holderEmail;
    public String holderPhone;
    public String holderId;
    public String softDescriptor;
    public String fundingType;
    public String challengeMode;
    public String confirmationMode;
    public String billingCountryCode;
    public String billingCity;
    public String billingStateCode;
    public String billingStreetLine1;
    public String billingStreetLine2;
    public String billingZipCode;
    public Map<String, Object> metadata;
    public String cardEnding;
    public String challengeUrl;
    public String created;
    public String currencyCode;
    public String endToEndId;
    public Integer fee;
    public String network;
    public String source;
    public String status;
    public String[] tags;
    public String updated;

    public MerchantPurchase(String id, long amount, int installmentCount, String holderName, String holderEmail, String holderPhone, String holderId, String softDescriptor, String fundingType, String billingCountryCode, String billingCity, String billingStateCode, String billingStreetLine1, String billingStreetLine2, String billingZipCode, Map<String, Object> metadata, String cardEnding, String cardId, String challengeMode, String confirmationMode, String challengeUrl, String created, String currencyCode, String endToEndId, int fee, String network, String source, String status, String[] tags, String updated) {
        super(id);
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.holderName = holderName;
        this.holderEmail = holderEmail;
        this.holderPhone = holderPhone;
        this.holderId = holderId;
        this.softDescriptor = softDescriptor;
        this.fundingType = fundingType;
        this.billingCountryCode = billingCountryCode;
        this.billingCity = billingCity;
        this.billingStateCode = billingStateCode;
        this.billingStreetLine1 = billingStreetLine1;
        this.billingStreetLine2 = billingStreetLine2;
        this.billingZipCode = billingZipCode;
        this.metadata = metadata;
        this.cardEnding = cardEnding;
        this.cardId = cardId;
        this.challengeMode = challengeMode;
        this.confirmationMode = confirmationMode;
        this.challengeUrl = challengeUrl;
        this.created = created;
        this.currencyCode = currencyCode;
        this.endToEndId = endToEndId;
        this.fee = fee;
        this.network = network;
        this.source = source;
        this.status = status;
        this.tags = tags;
        this.updated = updated;
    }

    public MerchantPurchase(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = (Long) dataCopy.remove("amount");
        this.installmentCount = (int) dataCopy.remove("installmentCount");
        this.cardId = (String) dataCopy.remove("cardId");
        this.holderName = (String) dataCopy.remove("holderName");
        this.holderEmail = (String) dataCopy.remove("holderEmail");
        this.holderPhone = (String) dataCopy.remove("holderPhone");
        this.holderId = (String) dataCopy.remove("holderId");
        this.softDescriptor = (String) dataCopy.remove("softDescriptor");
        this.fundingType = (String) dataCopy.remove("fundingType");
        this.billingCountryCode = (String) dataCopy.remove("billingCountryCode");
        this.billingCity = (String) dataCopy.remove("billingCity");
        this.billingStateCode = (String) dataCopy.remove("billingStateCode");
        this.billingStreetLine1 = (String) dataCopy.remove("billingStreetLine1");
        this.billingStreetLine2 = (String) dataCopy.remove("billingStreetLine2");
        this.billingZipCode = (String) dataCopy.remove("billingZipCode");
        this.metadata = (Map<String, Object>) dataCopy.remove("metadata");
        this.challengeMode = (String) dataCopy.remove("challengeMode");
        this.confirmationMode = (String) dataCopy.remove("confirmationMode");
        this.tags = (String[]) dataCopy.remove("tags");
        this.cardEnding = null;
        this.challengeUrl = null;
        this.currencyCode = null;
        this.endToEndId = null;
        this.fee = null;
        this.network = null;
        this.source = null;
        this.status = null;
        this.updated = null;
        this.created = null;


        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Create a MerchantPurchase
     * <p>
     * Charge a card that has been previously saved via an approved MerchantSession Purchase.
     * The card must already have an approved MerchantSessionPurchase before it can be charged here.
     * <p>
     * Parameters (required):
     * @param merchantPurchase [MerchantPurchase object]: MerchantPurchase object to be created in the API. Required fields:
     * cardId [string]: id of the MerchantCard to charge.
     * amount [long]: amount in cents to be received. ex: 100 (R$1.00)
     * fundingType [string]: "credit" or "debit"
     * <p>
     * Parameters (conditionally required):
     * billingCity, billingCountryCode, billingStateCode, billingStreetLine1, billingStreetLine2, billingZipCode, holderEmail, holderPhone [string]: required when challengeMode is "enabled" (the default), optional otherwise.
     * metadata [map]: when challengeMode is "enabled", must include userAgent, timezoneOffset, userIp and language for the 3DS challenge.
     * <p>
     * Parameters (optional):
     * challengeMode [string, default "enabled"]: whether 3DS holder verification is used. Options: "enabled", "disabled"
     * installmentCount [int, default 1]: number of purchase installments.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantPurchase object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantPurchase create(MerchantPurchase merchantPurchase, User user) throws Exception {
        return Rest.postSingle(data, merchantPurchase, user);
    }

    /**
     * Create a MerchantPurchase
     * <p>
     * Charge a card that has been previously saved via an approved MerchantSession Purchase.
     * The card must already have an approved MerchantSessionPurchase before it can be charged here.
     * <p>
     * Parameters (required):
     * @param merchantPurchase [MerchantPurchase object]: MerchantPurchase object to be created in the API. Required fields:
     * cardId [string]: id of the MerchantCard to charge.
     * amount [long]: amount in cents to be received. ex: 100 (R$1.00)
     * fundingType [string]: "credit" or "debit"
     * <p>
     * Parameters (conditionally required):
     * billingCity, billingCountryCode, billingStateCode, billingStreetLine1, billingStreetLine2, billingZipCode, holderEmail, holderPhone [string]: required when challengeMode is "enabled" (the default), optional otherwise.
     * metadata [map]: when challengeMode is "enabled", must include userAgent, timezoneOffset, userIp and language for the 3DS challenge.
     * <p>
     * Parameters (optional):
     * challengeMode [string, default "enabled"]: whether 3DS holder verification is used. Options: "enabled", "disabled"
     * installmentCount [int, default 1]: number of purchase installments.
     * <p>
     * Return:
     * @return MerchantPurchase object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantPurchase create(MerchantPurchase merchantPurchase) throws Exception {
        return MerchantPurchase.create(merchantPurchase, null);
    }

    /**
     * Update a MerchantPurchase entity
     * <p>
     * Update a MerchantPurchase by passing its id.
     * If the purchase is "approved", only amount=0 with status="canceled" is allowed, which cancels the authorization.
     * If the purchase is "confirmed", status can be set to "reversed" with a lower amount, which debits and reverses the difference (partially or totally);
     * a partial reversal leaves status "confirmed", a full reversal moves it to "voided".
     * <p>
     * Parameters (required):
     * @param id [string]: MerchantPurchase id.
     * <p>
     * Parameters (optional):
     * @param patchData map of parameters to be updated
     * amount [long]: new amount; 0 to cancel an approved purchase, or a lower value to partially/fully reverse a confirmed one. ex: 200 (R$2.00)
     * status [string]: "canceled" (from approved) or "reversed" (from confirmed)
     * <p>
     * Return:
     * @return target MerchantPurchase with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantPurchase update(String id, Map<String, Object> patchData) throws Exception {
        return MerchantPurchase.update(id, patchData, null);
    }

    /**
     * Update a MerchantPurchase entity
     * <p>
     * Update a MerchantPurchase by passing its id.
     * If the purchase is "approved", only amount=0 with status="canceled" is allowed, which cancels the authorization.
     * If the purchase is "confirmed", status can be set to "reversed" with a lower amount, which debits and reverses the difference (partially or totally);
     * a partial reversal leaves status "confirmed", a full reversal moves it to "voided".
     * <p>
     * Parameters (required):
     * @param id [string]: MerchantPurchase id.
     * <p>
     * Parameters (optional):
     * @param patchData map of parameters to be updated
     * amount [long]: new amount; 0 to cancel an approved purchase, or a lower value to partially/fully reverse a confirmed one. ex: 200 (R$2.00)
     * status [string]: "canceled" (from approved) or "reversed" (from confirmed)
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return target MerchantPurchase with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantPurchase update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }

    /**
     * Delete a MerchantPurchase entity
     * <p>
     * Send a delete request for a single MerchantPurchase by passing its id.
     * <p>
     * Parameters (required):
     * @param id [string]: MerchantPurchase id.
     * <p>
     * Return:
     * @return MerchantPurchase object returned by the API for the deleted entity
     * @throws Exception error in the request
     */
    public static MerchantPurchase delete(String id) throws Exception {
        return MerchantPurchase.delete(id, null);
    }

    /**
     * Delete a MerchantPurchase entity
     * <p>
     * Send a delete request for a single MerchantPurchase by passing its id.
     * <p>
     * Parameters (required):
     * @param id [string]: MerchantPurchase id.
     * <p>
     * Parameters (optional):
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantPurchase object returned by the API for the deleted entity
     * @throws Exception error in the request
     */
    public static MerchantPurchase delete(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    /**
     * Retrieve a specific MerchantPurchase
     * <p>
     * Receive a single MerchantPurchase object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantPurchase object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantPurchase get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific MerchantPurchase
     * <p>
     * Receive a single MerchantPurchase object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * <p>
     * Return:
     * @return MerchantPurchase object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantPurchase get(String id) throws Exception {
        return MerchantPurchase.get(id, null);
    }

    /**
     * Retrieve MerchantPurchases
     * <p>
     * Receive a generator of MerchantPurchase objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "approved", "denied", "confirmed", "paid", "pending", "canceled", "voided", "failed"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for purchases made with a specific MerchantCard's holder id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of MerchantPurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantPurchase> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve MerchantPurchases
     * <p>
     * Receive a generator of MerchantPurchase objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "approved", "denied", "confirmed", "paid", "pending", "canceled", "voided", "failed"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for purchases made with a specific MerchantCard's holder id.
     * <p>
     * Return:
     * @return generator of MerchantPurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantPurchase> query(Map<String, Object> params) throws Exception {
        return MerchantPurchase.query(params, null);
    }

    /**
     * Retrieve MerchantPurchases
     * <p>
     * Receive a generator of MerchantPurchase objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of MerchantPurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantPurchase> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    /**
     * Retrieve paged MerchantPurchases
     * <p>
     * Receive a list of up to 100 MerchantPurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "approved", "denied", "confirmed", "paid", "pending", "canceled", "voided", "failed"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for purchases made with a specific MerchantCard's holder id.
     * <p>
     * Return:
     * @return MerchantPurchase.Page object:
     * MerchantPurchase.Page.merchantPurchases: list of MerchantPurchase objects with updated attributes
     * MerchantPurchase.Page.cursor: cursor to retrieve the next page of MerchantPurchase objects
     * @throws Exception error in the request
     */
    public static MerchantPurchase.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged MerchantPurchases
     * <p>
     * Receive a list of up to 100 MerchantPurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantPurchase.Page object:
     * MerchantPurchase.Page.merchantPurchases: list of MerchantPurchase objects with updated attributes
     * MerchantPurchase.Page.cursor: cursor to retrieve the next page of MerchantPurchase objects
     * @throws Exception error in the request
     */
    public static MerchantPurchase.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged MerchantPurchases
     * <p>
     * Receive a list of up to 100 MerchantPurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return MerchantPurchase.Page object:
     * MerchantPurchase.Page.merchantPurchases: list of MerchantPurchase objects with updated attributes
     * MerchantPurchase.Page.cursor: cursor to retrieve the next page of MerchantPurchase objects
     * @throws Exception error in the request
     */
    public static MerchantPurchase.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }


    /**
     * Retrieve paged MerchantPurchases
     * <p>
     * Receive a list of up to 100 MerchantPurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "approved", "denied", "confirmed", "paid", "pending", "canceled", "voided", "failed"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for purchases made with a specific MerchantCard's holder id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantPurchase.Page object:
     * MerchantPurchase.Page.merchantPurchases: list of MerchantPurchase objects with updated attributes
     * MerchantPurchase.Page.cursor: cursor to retrieve the next page of MerchantPurchase objects
     * @throws Exception error in the request
     */
    public static MerchantPurchase.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantPurchase> merchantPurchases = new ArrayList<>();
        for (com.starkcore.utils.SubResource merchantPurchase: page.entities) {
            merchantPurchases.add((MerchantPurchase) merchantPurchase);
        }
        return new MerchantPurchase.Page(merchantPurchases, page.cursor);
    }

    public final static class Page {
        public List<MerchantPurchase> merchantPurchases;
        public String cursor;

        public Page(List<MerchantPurchase> merchantPurchases, String cursor) {
            this.merchantPurchases = merchantPurchases;
            this.cursor = cursor;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantPurchase.Log.class, "MerchantPurchaseLog");

        public String created;
        public String type;
        public List<ErrorElement> errors;
        public MerchantPurchase purchase;

        /**
         * MerchantPurchase Log object
         * <p>
         * Every time a MerchantPurchase entity is updated, a corresponding MerchantPurchase Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the MerchantPurchase.
         * <p>
         * Attributes:
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * purchase [MerchantPurchase]: MerchantPurchase entity to which the log refers to.
         * errors [list of ErrorElement]: list of errors linked to this MerchantPurchase event
         * type [string]: type of the MerchantPurchase event which triggered the log creation. ex: "approved" or "confirmed"
         * created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000"
         */
        public Log(String created, String type, List<ErrorElement> errors, MerchantPurchase purchase, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.purchase = purchase;
        }


        /**
         * Retrieve a specific MerchantPurchase Log
         * <p>
         * Receive a single MerchantPurchase Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return MerchantPurchase Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantPurchase.Log get(String id) throws Exception {
            return MerchantPurchase.Log.get(id, null);
        }

        /**
         * Retrieve a specific MerchantPurchase Log
         * <p>
         * Receive a single MerchantPurchase Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantPurchase Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantPurchase.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve MerchantPurchase Logs
         * <p>
         * Receive a generator of MerchantPurchase Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "approved" or "confirmed"
         * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of MerchantPurchase Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantPurchase.Log> query(Map<String, Object> params) throws Exception {
            return MerchantPurchase.Log.query(params, null);
        }

        /**
         * Retrieve MerchantPurchase Logs
         * <p>
         * Receive a generator of MerchantPurchase Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantPurchase Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantPurchase.Log> query(User user) throws Exception {
            return MerchantPurchase.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve MerchantPurchase Logs
         * <p>
         * Receive a generator of MerchantPurchase Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of MerchantPurchase Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantPurchase.Log> query() throws Exception {
            return MerchantPurchase.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve MerchantPurchase Logs
         * <p>
         * Receive a generator of MerchantPurchase Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "approved" or "confirmed"
         * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantPurchase Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantPurchase.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantPurchase.Log> logs;
            public String cursor;

            public Page(List<MerchantPurchase.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged MerchantPurchase Logs
         * <p>
         * Receive a list of up to 100 MerchantPurchase Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "approved" or "confirmed"
         * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return MerchantPurchase.Log.Page object:
         * MerchantPurchase.Log.Page.logs: list of MerchantPurchase Log objects with updated attributes
         * MerchantPurchase.Log.Page.cursor: cursor to retrieve the next page of MerchantPurchase Log objects
         * @throws Exception error in the request
         */
        public static MerchantPurchase.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantPurchase.Log.page(params, null);
        }

        /**
         * Retrieve paged MerchantPurchase Logs
         * <p>
         * Receive a list of up to 100 MerchantPurchase Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantPurchase.Log.Page object:
         * MerchantPurchase.Log.Page.logs: list of MerchantPurchase Log objects with updated attributes
         * MerchantPurchase.Log.Page.cursor: cursor to retrieve the next page of MerchantPurchase Log objects
         * @throws Exception error in the request
         */
        public static MerchantPurchase.Log.Page page(User user) throws Exception {
            return MerchantPurchase.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged MerchantPurchase Logs
         * <p>
         * Receive a list of up to 100 MerchantPurchase Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return MerchantPurchase.Log.Page object:
         * MerchantPurchase.Log.Page.logs: list of MerchantPurchase Log objects with updated attributes
         * MerchantPurchase.Log.Page.cursor: cursor to retrieve the next page of MerchantPurchase Log objects
         * @throws Exception error in the request
         */
        public static MerchantPurchase.Log.Page page() throws Exception {
            return MerchantPurchase.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged MerchantPurchase Logs
         * <p>
         * Receive a list of up to 100 MerchantPurchase Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "approved" or "confirmed"
         * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantPurchase.Log.Page object:
         * MerchantPurchase.Log.Page.logs: list of MerchantPurchase Log objects with updated attributes
         * MerchantPurchase.Log.Page.cursor: cursor to retrieve the next page of MerchantPurchase Log objects
         * @throws Exception error in the request
         */
        public static MerchantPurchase.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantPurchase.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantPurchase.Log) log);
            }
            return new MerchantPurchase.Log.Page(logs, page.cursor);
        }

    }

}
