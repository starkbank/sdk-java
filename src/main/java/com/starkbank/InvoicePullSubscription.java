package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkcore.utils.SubResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class InvoicePullSubscription extends Resource {
    /**
     * InvoicePullSubscription object
     * When you initialize an InvoicePullSubscription, the entity will not be automatically
     * sent to the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * start [string]: subscription start date. ex: "2022-04-01"
     * interval [string]: subscription installment interval. Options: "week", "month", "quarter", "semester", "year"
     * pullMode [string]: subscription pull mode. Options: "manual", "automatic". Automatic mode will create the Invoice Pull Requests automatically
     * pullRetryLimit [integer]: subscription pull retry limit. Options: 0,
     * type [string]: subscription type. Options: "push", "qrcode", "qrcodeAndPayment", "paymentAndOrQrcode"
     * amount [integer, default 0]: subscription amount in cents. Required if an amountMinLimit is not informed. Minimum = 1 (R$ 0.01). ex: 100 (= R$ 1.00)
     * amountMinLimit [integer, 0 None]: subscription minimum amount in cents. Required if an amount is not informed. Minimum = 1 (R$ 0.01). ex: 100 (= R$ 1.00)
     * displayDescription [string, default None]: Invoice description to be shown to the payer. ex: "Subscription payment"
     * due [integer, default None]: subscription invoice due offset. Available only for type "push". ex: timedelta(days=7)
     * externalId [string, default None]: string that must be unique among all your subscriptions. Duplicated externalIds will cause failures. ex: "my-external-id"
     * referenceCode [string, default None]: reference code for reconciliation. ex: "REF123456"
     * end [string, default None]: subscription end date. ex: "2023-04-01"
     * data [dictionary, default None]: additional data for the subscription based on type
     * name [string, default None]: subscription debtor name. ex: "Iron Bank S.A."
     * taxId [string, default None]: subscription debtor tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * tags [list of strings, default []]: list of strings for tagging
     * id [string]: unique id returned when subscription is created. ex: "5656565656565656"
     * status [string]: current subscription status. ex: "active", "canceled"
     * bacenId [string]: unique authentication id at the Central Bank. ex: "ccf9bd9c-e99d-999e-bab9-b999ca999f99"
     * installmentId [string]: unique id of the installment related to this request. ex: "5656565656565656"
     * brcode [string]: Bacen brcode for the subscription. ex: "RR3990842720250702ws3mC6J0DHh"
     * created [string]: creation datetime for the Invoice. ex: '2020-03-10 10:30:00.000000+00:00'
     * updated [string]: creation datetime for the Invoice. ex: '2020-03-10 10:30:00.000000+00:00'
     */
    static ClassData classData = new ClassData(InvoicePullSubscription.class, "InvoicePullSubscription");

    public String start;
    public String interval;
    public String pullMode;
    public Long pullRetryLimit;
    public String type;
    public Long amount;
    public Long amountMinLimit;
    public String displayDescription;
    public String due;
    public String externalId;
    public String referenceCode;
    public String end;
    public Map<String, Object> data;
    public String name;
    public String taxId;
    public String[] tags;
    public String status;
    public String bacenId;
    public String installmentId;
    public String brcode;
    public String created;
    public String updated;

    /**
     * InvoicePullSubscription object
     * When you initialize an InvoicePullSubscription, the entity will not be automatically sent to the Stark Bank API.
     * The 'create' function sends the objects to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters (required):
     * @param start [string]: subscription start date. ex: "2022-04-01"
     * @param interval [string]: subscription installment interval. Options: "week", "month", "quarter", "semester", "year"
     * @param pullMode [string]: subscription pull mode. Options: "manual", "automatic"
     * @param pullRetryLimit [integer]: subscription pull retry limit. ex: 0
     * @param type [string]: subscription type. Options: "push", "qrcode", "qrcodeAndPayment", "paymentAndOrQrcode"
     * <p>
     * Parameters (conditionally required):
     * @param amount [integer, default 0]: subscription amount in cents. Required if an amountMinLimit is not informed. ex: 100
     * @param amountMinLimit [integer, default null]: subscription minimum amount in cents. Required if an amount is not informed. ex: 100
     * <p>
     * Parameters (optional):
     * @param displayDescription [string, default null]: Invoice description to be shown to the payer. ex: "Subscription payment"
     * @param due [string]: Due date for answering with an approval or denial.
     * @param externalId [string, default null]: unique string among all your subscriptions. ex: "my-external-id"
     * @param referenceCode [string, default null]: reference code for reconciliation. ex: "REF123456"
     * @param end [string, default null]: subscription end date. ex: "2023-04-01"
     * @param data [Map of String: Object, default null]: additional data for the subscription based on type
     * @param name [string, default null]: subscription debtor name. ex: "Iron Bank S.A."
     * @param taxId [string, default null]: subscription debtor tax ID. ex: "01234567890"
     * @param tags [list of strings, default null]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * @param id [string]: unique id returned when subscription is created. ex: "5656565656565656"
     * @param status [string]: current subscription status. ex: "active", "canceled"
     * @param bacenId [string]: unique authentication id at the Central Bank. ex: "ccf9bd9c-e99d-999e-bab9-b999ca999f99"
     * @param installmentId [string]: unique id of the installment related to this request. ex: "5656565656565656"
     * @param brcode [string]: Bacen brcode for the subscription. ex: "RR3990842720250702ws3mC6J0DHh"
     * @param created [string]: creation datetime for the Invoice. ex: '2020-03-10 10:30:00.000000+00:00'
     * @param updated [string]: creation datetime for the Invoice. ex: '2020-03-10 10:30:00.000000+00:00'
     */
    public InvoicePullSubscription(String start, String interval, String pullMode, Long pullRetryLimit, String type, Long amount, Long amountMinLimit, String displayDescription, String due, String externalId, String referenceCode, String end, Map<String, Object> data, String name, String taxId,
                       String[] tags, String status, String bacenId, String installmentId, String brcode, String created, String updated, String id) {
        super(id);
        this.start = start;
        this.interval = interval;
        this.pullMode = pullMode;
        this.pullRetryLimit = pullRetryLimit;
        this.type = type;
        this.amount = amount;
        this.amountMinLimit = amountMinLimit;
        this.displayDescription = displayDescription;
        this.due = due;
        this.externalId = externalId;
        this.referenceCode = referenceCode;
        this.end = end;
        this.data = data;
        this.name = name;
        this.taxId = taxId;
        this.tags = tags;
        this.status = status;
        this.bacenId = bacenId;
        this.installmentId = installmentId;
        this.brcode = brcode;
        this.created = created;
        this.updated = updated;
    }

    /**
     * InvoicePullSubscription object
     * When you initialize an InvoicePullSubscription, the entity will not be automatically sent to the Stark Bank API.
     * The 'create' function sends the objects to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param classData map of parameters for the creation of the subscription
     * Parameters (required): start, interval, pullMode, pullRetryLimit, type
     * Parameters (conditionally required): amount, amountMinLimit
     * Parameters (optional): displayDescription, due, externalId, referenceCode, end, data, name, taxId, tags
     * Parameters (return-only): id, status, bacenId, installmentId, created, updated
     */
    @SuppressWarnings("unchecked")
    public InvoicePullSubscription(Map<String, Object> classData) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(classData);

        this.start = (String) dataCopy.remove("start");
        this.interval = (String) dataCopy.remove("interval");
        this.pullMode = (String) dataCopy.remove("pullMode");
        this.pullRetryLimit = (Long) dataCopy.remove("pullRetryLimit");
        this.type = (String) dataCopy.remove("type");
        this.amount = (Long) dataCopy.remove("amount");
        this.amountMinLimit = (Long) dataCopy.remove("amountMinLimit");
        this.displayDescription = (String) dataCopy.remove("displayDescription");
        this.due = (String) dataCopy.remove("due");
        this.externalId = (String) dataCopy.remove("externalId");
        this.referenceCode = (String) dataCopy.remove("referenceCode");
        this.end = (String) dataCopy.remove("end");
        this.data = (Map<String, Object>) dataCopy.remove("data");
        this.name = (String) dataCopy.remove("name");
        this.taxId = (String) dataCopy.remove("taxId");
        this.tags = (String[]) dataCopy.remove("tags");
        this.status = (String) dataCopy.remove("status");
        this.bacenId = (String) dataCopy.remove("bacenId");
        this.installmentId = (String) dataCopy.remove("installmentId");
        this.brcode = (String) dataCopy.remove("brcode");
        this.created = (String) dataCopy.remove("created");
        this.updated = (String) dataCopy.remove("updated");

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public InvoicePullSubscription(){
        super(null);
    }

    /**
     * Retrieve a specific InvoicePullSubscription
     * <p>
     * Receive a single InvoicePullSubscription object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return InvoicePullSubscription object with updated attributes
     * @throws Exception error in the request
     */
    public static InvoicePullSubscription get(String id) throws Exception {
        return InvoicePullSubscription.get(id, null);
    }

    /**
     * Retrieve a specific InvoicePullSubscription
     * <p>
     * Receive a single InvoicePullSubscription object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return InvoicePullSubscription object with updated attributes
     * @throws Exception error in the request
     */
    public static InvoicePullSubscription get(String id, User user) throws Exception {
        return Rest.getId(classData, id, user);
    }

    /**
     * Retrieve InvoicePullSubscriptions
     * <p>
     * Receive a generator of InvoicePullSubscription objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * externalIds [list of strings, default null]: list of external ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of InvoicePullSubscription objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<InvoicePullSubscription> query(Map<String, Object> params) throws Exception {
        return InvoicePullSubscription.query(params, null);
    }

    /**
     * Retrieve InvoicePullSubscriptions
     * <p>
     * Receive a generator of InvoicePullSubscription objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of InvoicePullSubscription objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<InvoicePullSubscription> query(User user) throws Exception {
        return InvoicePullSubscription.query(new HashMap<>(), user);
    }

    /**
     * Retrieve InvoicePullSubscriptions
     * <p>
     * Receive a generator of InvoicePullSubscription objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of InvoicePullSubscription objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<InvoicePullSubscription> query() throws Exception {
        return InvoicePullSubscription.query(new HashMap<>(), null);
    }

    /**
     * Retrieve InvoicePullSubscriptions
     * <p>
     * Receive a generator of InvoicePullSubscription objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * externalIds [list of strings, default null]: list of external ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of InvoicePullSubscription objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<InvoicePullSubscription> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(classData, params, user);
    }

    public final static class Page {
        public List<InvoicePullSubscription> subscriptions;
        public String cursor;

        public Page(List<InvoicePullSubscription> subscriptions, String cursor) {
            this.subscriptions = subscriptions;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged InvoicePullSubscriptions
     * <p>
     * Receive a list of up to 100 InvoicePullSubscription objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * externalIds [list of strings, default null]: list of external ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return InvoicePullSubscription.Page object:
     * InvoicePullSubscription.Page.subscriptions: list of InvoicePullSubscription objects with updated attributes
     * InvoicePullSubscription.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return InvoicePullSubscription.page(params, null);
    }

    /**
     * Retrieve paged InvoicePullSubscriptions
     * <p>
     * Receive a list of up to 100 InvoicePullSubscription objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return InvoicePullSubscription.Page object:
     * InvoicePullSubscription.Page.subscriptions: list of InvoicePullSubscription objects with updated attributes
     * InvoicePullSubscription.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return InvoicePullSubscription.page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged InvoicePullSubscriptions
     * <p>
     * Receive a list of up to 100 InvoicePullSubscription objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return InvoicePullSubscription.Page object:
     * InvoicePullSubscription.Page.subscriptions: list of InvoicePullSubscription objects with updated attributes
     * InvoicePullSubscription.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return InvoicePullSubscription.page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged InvoicePullSubscriptions
     * <p>
     * Receive a list of up to 100 InvoicePullSubscription objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * externalIds [list of strings, default null]: list of external ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return InvoicePullSubscription.Page object:
     * InvoicePullSubscription.Page.subscriptions: list of InvoicePullSubscription objects with updated attributes
     * InvoicePullSubscription.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(classData, params, user);
        List<InvoicePullSubscription> subscriptions = new ArrayList<>();
        for (SubResource subscription: page.entities) {
            subscriptions.add((InvoicePullSubscription) subscription);
        }
        return new Page(subscriptions, page.cursor);
    }

    /**
     * Create InvoicePullSubscriptions
     * <p>
     * Send a list of InvoicePullSubscription objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param subscriptions [list of InvoicePullSubscription objects or HashMaps]: list of InvoicePullSubscription objects to be created in the API
     * <p>
     * Return:
     * @return list of InvoicePullSubscription objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<InvoicePullSubscription> create(List<?> subscriptions) throws Exception {
        return InvoicePullSubscription.create(subscriptions, null);
    }

    /**
     * Create InvoicePullSubscriptions
     * <p>
     * Send a list of InvoicePullSubscription objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param subscriptions [list of InvoicePullSubscription objects or HashMaps]: list of InvoicePullSubscription objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of InvoicePullSubscription objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<InvoicePullSubscription> create(List<?> subscriptions, User user) throws Exception {
        List<InvoicePullSubscription> subscriptionList = new ArrayList<>();
        for (Object subscription : subscriptions){
            if (subscription instanceof Map){
                subscriptionList.add(new InvoicePullSubscription((Map<String, Object>) subscription));
                continue;
            }
            if (subscription instanceof InvoicePullSubscription){
                subscriptionList.add((InvoicePullSubscription) subscription);
                continue;
            }
            throw new Exception("Unknown type \"" + subscription.getClass() + "\", use InvoicePullSubscription or HashMap");
        }
        return Rest.post(classData, subscriptionList, user);
    }

    public static InvoicePullSubscription cancel(String id) throws Exception {
        return InvoicePullSubscription.cancel(id, null);
    }

    public static InvoicePullSubscription cancel(String id, User user) throws Exception {
        return Rest.delete(classData, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "InvoicePullSubscriptionLog");

        public String created;
        public String type;
        public String[] errors;
        public InvoicePullSubscription subscription;

        /**
         * InvoicePullSubscription Log object
         * <p>
         * Every time a subscription entity is updated, a corresponding subscription Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the subscription.
         * <p>
         * Attributes (return-only):
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param subscription [InvoicePullSubscription]: subscription entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this subscription event
         * @param type [string]: type of the subscription event which triggered the log creation. ex: "registered" or "paid"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, String[] errors, InvoicePullSubscription subscription, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.subscription = subscription;
        }

        public Log(){
            super(null);
        }

        /**
         * Retrieve a specific InvoicePullSubscription Log
         * <p>
         * Receive a single InvoicePullSubscription Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return InvoicePullSubscription Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific InvoicePullSubscription Log
         * <p>
         * Receive a single InvoicePullSubscription Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return InvoicePullSubscription Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve InvoicePullSubscription Logs
         * <p>
         * Receive a generator of InvoicePullSubscription Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * subscriptionIds [list of strings, default null]: list of subscription ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of InvoicePullSubscription Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve InvoicePullSubscription Logs
         * <p>
         * Receive a generator of InvoicePullSubscription Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of InvoicePullSubscription Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve InvoicePullSubscription Logs
         * <p>
         * Receive a generator of InvoicePullSubscription Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of InvoicePullSubscription Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve InvoicePullSubscription Logs
         * <p>
         * Receive a generator of InvoicePullSubscription Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * subscriptionIds [list of strings, default null]: list of subscription ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of InvoicePullSubscription Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<Log> logs;
            public String cursor;

            public Page(List<Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged InvoicePullSubscription.Logs
         * <p>
         * Receive a list of up to 100 InvoicePullSubscription.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * subscriptionIds [list of strings, default null]: list of subscription ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return InvoicePullSubscription.Log.Page object:
         * InvoicePullSubscription.Log.Page.logs: list of InvoicePullSubscription.Log objects with updated attributes
         * InvoicePullSubscription.Log.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged InvoicePullSubscription.Logs
         * <p>
         * Receive a list of up to 100 InvoicePullSubscription.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return InvoicePullSubscription.Log.Page object:
         * InvoicePullSubscription.Log.Page.logs: list of InvoicePullSubscription.Log objects with updated attributes
         * InvoicePullSubscription.Log.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged InvoicePullSubscription.Logs
         * <p>
         * Receive a list of up to 100 InvoicePullSubscription.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return InvoicePullSubscription.Log.Page object:
         * InvoicePullSubscription.Log.Page.logs: list of InvoicePullSubscription.Log objects with updated attributes
         * InvoicePullSubscription.Log.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged InvoicePullSubscription.Logs
         * <p>
         * Receive a list of up to 100 InvoicePullSubscription.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * subscriptionIds [list of strings, default null]: list of subscription ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return InvoicePullSubscription.Log.Page object:
         * InvoicePullSubscription.Log.Page.logs: list of InvoicePullSubscription.Log objects with updated attributes
         * InvoicePullSubscription.Log.Page.cursor: cursor to retrieve the next page of InvoicePullSubscription.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((Log) log);
            }
            return new Log.Page(logs, page.cursor);
        }
    }
}
