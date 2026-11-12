package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MerchantSession extends Resource {

    /**
        # MerchantSession object
        Check out our API Documentation at https://starkbank.com/docs/api#merchant-session
    **/

    static ClassData data = new ClassData(MerchantSession.class, "MerchantSession");

    public List<String> allowedFundingTypes;
    public List<AllowedInstallment> allowedInstallments;
    public List<String> allowedIps;
    public String challengeMode;
    public String confirmationMode;
    public String holderId;
    public String softDescriptor;
    public String created;
    public Number expiration;
    public String status;
    public String[] tags;
    public String updated;
    public String uuid;

    public MerchantSession(String id, List<String> allowedFundingTypes, List<AllowedInstallment> allowedInstallments, List<String> allowedIps, String challengeMode, String confirmationMode, String holderId, String softDescriptor, String created, Number expiration, String status, String[] tags, String updated, String uuid)  {
        super(id);
        this.allowedFundingTypes = allowedFundingTypes;
        this.allowedInstallments = allowedInstallments;
        this.allowedIps = allowedIps;
        this.challengeMode = challengeMode;
        this.confirmationMode = confirmationMode;
        this.holderId = holderId;
        this.softDescriptor = softDescriptor;
        this.created = created;
        this.expiration = expiration;
        this.status = status;
        this.tags = tags;
        this.updated = updated;
        this.uuid = uuid;
    }


    public MerchantSession(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.allowedFundingTypes = (List<String>) dataCopy.remove("allowedFundingTypes");
        this.allowedInstallments = parseAllowedInstallments((List<Object>) dataCopy.remove("allowedInstallments"));
        this.challengeMode = (String) dataCopy.remove("challengeMode");
        this.confirmationMode = (String) dataCopy.remove("confirmationMode");
        this.holderId = (String) dataCopy.remove("holderId");
        this.softDescriptor = (String) dataCopy.remove("softDescriptor");
        this.expiration = (Number) dataCopy.remove("expiration");
        this.tags = (String[]) dataCopy.remove("tags");
        this.uuid = null;
        this.status = null;
        this.allowedIps = null;
        this.created = null;
        this.updated = null;


        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }


    private List<AllowedInstallment> parseAllowedInstallments(List<Object> allowedInstallments) {
        if (allowedInstallments == null)
            return null;

        List<AllowedInstallment> parsed = new ArrayList<>();
        if (allowedInstallments.isEmpty() || allowedInstallments.get(0) instanceof AllowedInstallment) {
            for (Object allowedInstallment : allowedInstallments) {
                parsed.add((AllowedInstallment) allowedInstallment);
            }

            return parsed;
        }

        for (Object allowedInstallment : allowedInstallments) {
            AllowedInstallment allowedInstallmentObject = new AllowedInstallment(
                    (Long) ((Map<String, Object>) allowedInstallment).get("totalAmount"),
                    (Integer) ((Map<String, Object>) allowedInstallment).get("count")
            );
            parsed.add(allowedInstallmentObject);
        }

        return parsed;
    }

    public static MerchantSession create(MerchantSession merchantSession) throws Exception {
        return Rest.postSingle(data, merchantSession, null);
    }

    public static MerchantSession create(MerchantSession merchantSession, User user) throws Exception {
        return Rest.postSingle(data, merchantSession, user);
    }

    /**
     * @deprecated Function deprecated since v2.27.0
     * <p>
     * Create a MerchantSession Purchase
     * <p>
     * Send the card data of a Purchase to the Stark Bank API through a MerchantSession
     * <p>
     * Parameters:
     * @param uuid [string]: unique identifier of the MerchantSession
     * @param purchase [Purchase object]: Purchase object to be created in the API
     * <p>
     * Return:
     * @return Purchase object with updated attributes
     * @throws Exception error in the request
     */
    @Deprecated
    public static Purchase purchase(String uuid, Purchase purchase) throws Exception {
        return MerchantSession.purchase(uuid, purchase, null);
    }

    /**
     * @deprecated Function deprecated since v2.27.0
     * <p>
     * Create a MerchantSession Purchase
     * <p>
     * Send the card data of a Purchase to the Stark Bank API through a MerchantSession
     * <p>
     * Parameters:
     * @param uuid [string]: unique identifier of the MerchantSession
     * @param purchase [Purchase object]: Purchase object to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Purchase object with updated attributes
     * @throws Exception error in the request
     */
    @Deprecated
    public static Purchase purchase(String uuid, Purchase purchase, User user) throws Exception {
        throw new Exception("Function deprecated since v2.27.0");
    }

    /**
     * Retrieve a specific MerchantSession
     * <p>
     * Receive a single MerchantSession object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantSession object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantSession get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific MerchantSession
     * <p>
     * Receive a single MerchantSession object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * <p>
     * Return:
     * @return MerchantSession object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantSession get(String id) throws Exception {
        return MerchantSession.get(id, null);
    }

    /**
     * Retrieve MerchantSessions
     * <p>
     * Receive a generator of MerchantSession objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "success", "voided"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for sessions linked to a specific MerchantCard's holder id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of MerchantSession objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantSession> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve MerchantSessions
     * <p>
     * Receive a generator of MerchantSession objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "success", "voided"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for sessions linked to a specific MerchantCard's holder id.
     * <p>
     * Return:
     * @return generator of MerchantSession objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantSession> query(Map<String, Object> params) throws Exception {
        return MerchantSession.query(params, null);
    }

    /**
     * Retrieve MerchantSessions
     * <p>
     * Receive a generator of MerchantSession objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of MerchantSession objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantSession> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    /**
     * Retrieve paged MerchantSessions
     * <p>
     * Receive a list of up to 100 MerchantSession objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "success", "voided"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for sessions linked to a specific MerchantCard's holder id.
     * <p>
     * Return:
     * @return MerchantSession.Page object:
     * MerchantSession.Page.merchantSessions: list of MerchantSession objects with updated attributes
     * MerchantSession.Page.cursor: cursor to retrieve the next page of MerchantSession objects
     * @throws Exception error in the request
     */
    public static MerchantSession.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged MerchantSessions
     * <p>
     * Receive a list of up to 100 MerchantSession objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantSession.Page object:
     * MerchantSession.Page.merchantSessions: list of MerchantSession objects with updated attributes
     * MerchantSession.Page.cursor: cursor to retrieve the next page of MerchantSession objects
     * @throws Exception error in the request
     */
    public static MerchantSession.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged MerchantSessions
     * <p>
     * Receive a list of up to 100 MerchantSession objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return MerchantSession.Page object:
     * MerchantSession.Page.merchantSessions: list of MerchantSession objects with updated attributes
     * MerchantSession.Page.cursor: cursor to retrieve the next page of MerchantSession objects
     * @throws Exception error in the request
     */
    public static MerchantSession.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged MerchantSessions
     * <p>
     * Receive a list of up to 100 MerchantSession objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "success", "voided"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * holderId [string, default null]: filter for sessions linked to a specific MerchantCard's holder id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantSession.Page object:
     * MerchantSession.Page.merchantSessions: list of MerchantSession objects with updated attributes
     * MerchantSession.Page.cursor: cursor to retrieve the next page of MerchantSession objects
     * @throws Exception error in the request
     */
    public static MerchantSession.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantSession> merchantSessions = new ArrayList<>();
        for (com.starkcore.utils.SubResource merchantSession: page.entities) {
            merchantSessions.add((MerchantSession) merchantSession);
        }
        return new MerchantSession.Page(merchantSessions, page.cursor);
    }

    public final static class Page {
        public List<MerchantSession> merchantSessions;
        public String cursor;

        public Page(List<MerchantSession> merchantSessions, String cursor) {
            this.merchantSessions = merchantSessions;
            this.cursor = cursor;
        }
    }

    public static class AllowedInstallment extends SubResource {

        static SubResource.ClassData data = new SubResource.ClassData(AllowedInstallment.class, "AllowedInstallments");

        public long totalAmount;
        public int count;

        public AllowedInstallment(long totalAmount, int count){
            this.totalAmount = totalAmount;
            this.count = count;
        }
    }

    public final static class Purchase extends Resource {

        static ClassData data = new ClassData(Purchase.class, "Purchase");

        public Long amount;
        public Integer installmentCount;
        public String cardId;
        public String cardExpiration;
        public String cardNumber;
        public String cardSecurityCode;
        public String holderName;
        public String holderEmail;
        public String holderPhone;
        public String holderId;
        public String softDescriptor;
        public String fundingType;
        public String billingCountryCode;
        public String billingCity;
        public String billingStateCode;
        public String billingStreetLine1;
        public String billingStreetLine2;
        public String billingZipCode;
        public String challengeMode;
        public String confirmationMode;
        public Map<String, Object> metadata;
        public String network;
        public String source;
        public String status;
        public String[] tags;
        public String created;
        public String updated;

        public Purchase(String id, Long amount, Integer installmentCount, String cardId, String cardExpiration, String cardNumber, String cardSecurityCode, String holderName, String holderEmail, String holderPhone, String holderId, String softDescriptor, String fundingType, String billingCountryCode, String billingCity, String billingStateCode, String billingStreetLine1, String billingStreetLine2, String billingZipCode, String challengeMode, String confirmationMode, Map<String, Object> metadata) {
            super(id);
            this.amount = amount;
            this.installmentCount = installmentCount;
            this.cardId = cardId;
            this.cardExpiration = cardExpiration;
            this.cardNumber = cardNumber;
            this.cardSecurityCode = cardSecurityCode;
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
            this.challengeMode = challengeMode;
            this.confirmationMode = confirmationMode;
            this.metadata = metadata;
        }

        public Purchase(Map<String, Object> data) throws Exception  {

            super(null);
            HashMap<String, Object> dataCopy = new HashMap<>(data);

            this.amount = (Long) dataCopy.remove("amount");
            this.cardId = (String) dataCopy.remove("cardId");
            this.cardExpiration = (String) dataCopy.remove("cardExpiration");
            this.cardNumber = (String) dataCopy.remove("cardNumber");
            this.cardSecurityCode = (String) dataCopy.remove("cardSecurityCode");
            this.holderName = (String) dataCopy.remove("holderName");
            this.fundingType = (String) dataCopy.remove("fundingType");
            this.installmentCount = (Integer) dataCopy.remove("installmentCount");
            this.holderEmail = (String) dataCopy.remove("holderEmail");
            this.holderPhone = (String) dataCopy.remove("holderPhone");
            this.billingCountryCode = (String) dataCopy.remove("billingCountryCode");
            this.billingCity = (String) dataCopy.remove("billingCity");
            this.billingStateCode = (String) dataCopy.remove("billingStateCode");
            this.billingStreetLine1 = (String) dataCopy.remove("billingStreetLine1");
            this.billingStreetLine2 = (String) dataCopy.remove("billingStreetLine2");
            this.billingZipCode = (String) dataCopy.remove("billingZipCode");
            this.metadata =  (Map<String, Object>) dataCopy.remove("metadata");
            this.tags = (String[]) dataCopy.remove("tags");
            this.holderId = (String) dataCopy.remove("holderId");
            this.softDescriptor = (String) dataCopy.remove("softDescriptor");
            this.challengeMode = null;
            this.confirmationMode = null;
            this.network = null;
            this.source = null;
            this.status = null;
            this.created = null;
            this.updated = null;

            if (!dataCopy.isEmpty()) {
                throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
            }
        }

    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantSession.Log.class, "MerchantSessionLog");

        public String created;
        public String type;
        public String[] errors;
        public MerchantSession session;

        /**
         * MerchantSession Log object
         * <p>
         * Every time a MerchantSession entity is updated, a corresponding MerchantSession Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the MerchantSession.
         * <p>
         * Attributes:
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * session [MerchantSession]: MerchantSession entity to which the log refers to.
         * errors [list of strings]: list of errors linked to this MerchantSession event
         * type [string]: type of the MerchantSession event which triggered the log creation. ex: "expired" or "success"
         * created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000"
         */
        public Log(String created, String type, String[] errors, MerchantSession session, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.session = session;
        }

        /**
         * Retrieve a specific MerchantSession Log
         * <p>
         * Receive a single MerchantSession Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return MerchantSession Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantSession.Log get(String id) throws Exception {
            return MerchantSession.Log.get(id, null);
        }

        /**
         * Retrieve a specific MerchantSession Log
         * <p>
         * Receive a single MerchantSession Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantSession Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantSession.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve MerchantSession Logs
         * <p>
         * Receive a generator of MerchantSession Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "expired" or "success"
         * sessionIds [list of strings, default null]: list of MerchantSession ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of MerchantSession Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantSession.Log> query(Map<String, Object> params) throws Exception {
            return MerchantSession.Log.query(params, null);
        }

        /**
         * Retrieve MerchantSession Logs
         * <p>
         * Receive a generator of MerchantSession Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantSession Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantSession.Log> query(User user) throws Exception {
            return MerchantSession.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve MerchantSession Logs
         * <p>
         * Receive a generator of MerchantSession Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of MerchantSession Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantSession.Log> query() throws Exception {
            return MerchantSession.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve MerchantSession Logs
         * <p>
         * Receive a generator of MerchantSession Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "expired" or "success"
         * sessionIds [list of strings, default null]: list of MerchantSession ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantSession Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantSession.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantSession.Log> logs;
            public String cursor;

            public Page(List<MerchantSession.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged MerchantSession Logs
         * <p>
         * Receive a list of up to 100 MerchantSession Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "expired" or "success"
         * sessionIds [list of strings, default null]: list of MerchantSession ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return MerchantSession.Log.Page object:
         * MerchantSession.Log.Page.logs: list of MerchantSession Log objects with updated attributes
         * MerchantSession.Log.Page.cursor: cursor to retrieve the next page of MerchantSession Log objects
         * @throws Exception error in the request
         */
        public static MerchantSession.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantSession.Log.page(params, null);
        }

        /**
         * Retrieve paged MerchantSession Logs
         * <p>
         * Receive a list of up to 100 MerchantSession Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantSession.Log.Page object:
         * MerchantSession.Log.Page.logs: list of MerchantSession Log objects with updated attributes
         * MerchantSession.Log.Page.cursor: cursor to retrieve the next page of MerchantSession Log objects
         * @throws Exception error in the request
         */
        public static MerchantSession.Log.Page page(User user) throws Exception {
            return MerchantSession.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged MerchantSession Logs
         * <p>
         * Receive a list of up to 100 MerchantSession Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return MerchantSession.Log.Page object:
         * MerchantSession.Log.Page.logs: list of MerchantSession Log objects with updated attributes
         * MerchantSession.Log.Page.cursor: cursor to retrieve the next page of MerchantSession Log objects
         * @throws Exception error in the request
         */
        public static MerchantSession.Log.Page page() throws Exception {
            return MerchantSession.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged MerchantSession Logs
         * <p>
         * Receive a list of up to 100 MerchantSession Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "expired" or "success"
         * sessionIds [list of strings, default null]: list of MerchantSession ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantSession.Log.Page object:
         * MerchantSession.Log.Page.logs: list of MerchantSession Log objects with updated attributes
         * MerchantSession.Log.Page.cursor: cursor to retrieve the next page of MerchantSession Log objects
         * @throws Exception error in the request
         */
        public static MerchantSession.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantSession.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantSession.Log) log);
            }
            return new MerchantSession.Log.Page(logs, page.cursor);
        }

    }

}
