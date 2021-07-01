package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class BoletoPayment extends Resource {
    static ClassData data = new ClassData(BoletoPayment.class, "BoletoPayment");

    public Long amount;
    public String taxId;
    public String description;
    public String line;
    public String barCode;
    public String scheduled;
    public String[] tags;
    public String status;
    public Integer fee;
    public String created;

    /**
     * BoletoPayment object
     * <p>
     * When you initialize a BoletoPayment, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters (conditionally required):
     * line [string, default null]: Number sequence that describes the payment. Either "line" or "bar_code" parameters are required. If both are sent, they must match. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string, default null]: Bar code number that describes the payment. Either "line" or "barCode" parameters are required. If both are sent, they must match. ex: "34195819600000000621090063571277307144464000"
     * <p>
     * Parameters:
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * description [string]: Text to be displayed in your statement (min. 10 characters). ex: "payment ABC"
     * <p>
     * Parameters:
     * scheduled [string, default today]: payment scheduled date. ex: "2020-03-10"
     * tags [list of strings]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * @param id [string, default null]: unique id returned when payment is created. ex: "5656565656565656"
     * @param status [string, default null]: current payment status. ex: "processing" or "success"
     * @param amount [int, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * @param fee [integer, default null]: fee charged when the boleto payment is created. ex: 200 (= R$ 2.00)
     * @param created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param taxId identification for tax purposes (CPF)
     * @param tags list of tags
     * @param description description of the BoletoPayment
     * @param scheduled the time of scheduled payment
     * @param line numeric code of the BoletoPayment
     * @param barCode bar code of the Boleto
     */
    public BoletoPayment(Long amount, String taxId, String[] tags, String description, String scheduled,
                         String line, String barCode, String id, Integer fee, String status, String created
    ) {
        super(id);
        this.taxId = taxId;
        this.description = description;
        this.line = line;
        this.barCode = barCode;
        this.scheduled = scheduled;
        this.tags = tags;
        this.amount = amount;
        this.status = status;
        this.fee = fee;
        this.created = created;
    }

    /**
     * BoletoPayment object
     * <p>
     * When you initialize a BoletoPayment, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param data map of parameters for the creation of the BoletoPayment
     * Parameters (conditionally required):
     * line [string, default null]: Number sequence that describes the payment. Either "line" or "bar_code" parameters are required. If both are sent, they must match. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string, default null]: Bar code number that describes the payment. Either "line" or "barCode" parameters are required. If both are sent, they must match. ex: "34195819600000000621090063571277307144464000"
     * <p>
     * Parameters:
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * description [string]: Text to be displayed in your statement (min. 10 characters). ex: "payment ABC"
     * <p>
     * Parameters (optional):
     * scheduled [string, default today]: payment scheduled date. ex: "2020-03-10"
     * tags [list of strings]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * id [string, default null]: unique id returned when payment is created. ex: "5656565656565656"
     * status [string, default null]: current payment status. ex: "processing" or "success"
     * amount [int, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * fee [integer, default null]: fee charged when the boleto payment is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public BoletoPayment(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.taxId = (String) dataCopy.remove("taxId");
        this.description = (String) dataCopy.remove("description");
        this.line = (String) dataCopy.remove("line");
        this.barCode = (String) dataCopy.remove("barCode");
        this.scheduled = (String) dataCopy.remove("scheduled");
        this.tags = (String[]) dataCopy.remove("tags");
        this.amount = null;
        this.created = null;
        this.fee = null;
        this.status = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve a specific BoletoPayment
     * <p>
     * Receive a single BoletoPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return BoletoPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static BoletoPayment get(String id) throws Exception {
        return BoletoPayment.get(id, null);
    }

    /**
     * Retrieve a specific BoletoPayment
     * <p>
     * Receive a single BoletoPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static BoletoPayment get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve BoletoPayments
     * <p>
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * <p>
     * Return:
     * @return generator of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<BoletoPayment> query(Map<String, Object> params) throws Exception {
        return BoletoPayment.query(params, null);
    }

    /**
     * Retrieve BoletoPayments
     * <p>
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<BoletoPayment> query(User user) throws Exception {
        return BoletoPayment.query(new HashMap<>(), user);
    }

    /**
     * Retrieve BoletoPayments
     * <p>
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<BoletoPayment> query() throws Exception {
        return BoletoPayment.query(new HashMap<>(), null);
    }

    /**
     * Retrieve BoletoPayments
     * <p>
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<BoletoPayment> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public final static class Page {
        public List<BoletoPayment> payments;
        public String cursor;

        public Page(List<BoletoPayment> payments, String cursor) {
            this.payments = payments;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged BoletoPayments
     * <p>
     * Receive a list of up to 100 BoletoPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * <p>
     * Return:
     * @return BoletoPayment.Page object:
     * BoletoPayment.Page.payments: list of BoletoPayment objects with updated attributes
     * BoletoPayment.Page.cursor: cursor to retrieve the next page of BoletoPayment objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged BoletoPayments
     * <p>
     * Receive a list of up to 100 BoletoPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoPayment.Page object:
     * BoletoPayment.Page.payments: list of BoletoPayment objects with updated attributes
     * BoletoPayment.Page.cursor: cursor to retrieve the next page of BoletoPayment objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged BoletoPayments
     * <p>
     * Receive a list of up to 100 BoletoPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return BoletoPayment.Page object:
     * BoletoPayment.Page.payments: list of BoletoPayment objects with updated attributes
     * BoletoPayment.Page.cursor: cursor to retrieve the next page of BoletoPayment objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged BoletoPayments
     * <p>
     * Receive a list of up to 100 BoletoPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoPayment.Page object:
     * BoletoPayment.Page.payments: list of BoletoPayment objects with updated attributes
     * BoletoPayment.Page.cursor: cursor to retrieve the next page of BoletoPayment objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<BoletoPayment> payments = new ArrayList<>();
        for (SubResource payment: page.entities) {
            payments.add((BoletoPayment) payment);
        }
        return new Page(payments, page.cursor);
    }

    /**
     * Create BoletoPayments
     * <p>
     * Send a list of BoletoPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of BoletoPayment objects or HashMaps]: list of BoletoPayment objects to be created in the API
     * <p>
     * Return:
     * @return list of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<BoletoPayment> create(List<?> payments) throws Exception {
        return BoletoPayment.create(payments, null);
    }

    /**
     * Create BoletoPayments
     * <p>
     * Send a list of BoletoPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of BoletoPayment objects or HashMaps]: list of BoletoPayment objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<BoletoPayment> create(List<?> payments, User user) throws Exception {
        List<BoletoPayment> paymentList = new ArrayList<>();
        for (Object payment : payments){
            if (payment instanceof Map){
                paymentList.add(new BoletoPayment((Map<String, Object>) payment));
                continue;
            }
            if (payment instanceof BoletoPayment){
                paymentList.add((BoletoPayment) payment);
                continue;
            }
            throw new Exception("Unknown type \"" + payment.getClass() + "\", use BoletoPayment or HashMap");
        }
        return Rest.post(data, paymentList, user);
    }

    /**
     * Retrieve a specific BoletoPayment pdf file
     * <p>
     * Receive a single BoletoPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for boleto payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return BoletoPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id) throws Exception {
        return BoletoPayment.pdf(id, null);
    }

    /**
     * Retrieve a specific BoletoPayment pdf file
     * <p>
     * Receive a single BoletoPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for boleto payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, User user) throws Exception {
        return Rest.getContent(data, id, "pdf", user, null);
    }

    /**
     * Delete a BoletoPayment entity
     * <p>
     * Delete a BoletoPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: BoletoPayment unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted BoletoPayment object
     * @throws Exception error in the request
     */
    public static BoletoPayment delete(String id) throws Exception {
        return BoletoPayment.delete(id, null);
    }

    /**
     * Delete a BoletoPayment entity
     * <p>
     * Delete a BoletoPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: BoletoPayment unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted BoletoPayment object
     * @throws Exception error in the request
     */
    public static BoletoPayment delete(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "BoletoPaymentLog");

        public String created;
        public String type;
        public String[] errors;
        public BoletoPayment payment;

        /**
         * BoletoPayment Log object
         * <p>
         * Every time a BoletoPayment entity is modified, a corresponding BoletoPayment Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the BoletoPayment.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param payment [BoletoPayment]: BoletoPayment entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this BoletoPayment event.
         * @param type [string]: type of the BoletoPayment event which triggered the log creation. ex: "processing" or "success"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, String[] errors, BoletoPayment payment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.payment = payment;
        }

        /**
         * Retrieve a specific BoletoPayment Log
         * <p>
         * Receive a single BoletoPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return BoletoPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific BoletoPayment Log
         * <p>
         * Receive a single BoletoPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return BoletoPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve BoletoPayment Logs
         * <p>
         * Receive a generator of BoletoPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of BoletoPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of BoletoPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve BoletoPayment Logs
         * <p>
         * Receive a generator of BoletoPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of BoletoPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve BoletoPayment Logs
         * <p>
         * Receive a generator of BoletoPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of BoletoPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve BoletoPayment Logs
         * <p>
         * Receive a generator of BoletoPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of BoletoPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of BoletoPayment Log objects with updated attributes
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
         * Retrieve paged BoletoPayment.Logs
         * <p>
         * Receive a list of up to 100 BoletoPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of BoletoPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return BoletoPayment.Log.Page object:
         * BoletoPayment.Log.Page.logs: list of BoletoPayment.Log objects with updated attributes
         * BoletoPayment.Log.Page.cursor: cursor to retrieve the next page of BoletoPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged BoletoPayment.Logs
         * <p>
         * Receive a list of up to 100 BoletoPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return BoletoPayment.Log.Page object:
         * BoletoPayment.Log.Page.logs: list of BoletoPayment.Log objects with updated attributes
         * BoletoPayment.Log.Page.cursor: cursor to retrieve the next page of BoletoPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged BoletoPayment.Logs
         * <p>
         * Receive a list of up to 100 BoletoPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return BoletoPayment.Log.Page object:
         * BoletoPayment.Log.Page.logs: list of BoletoPayment.Log objects with updated attributes
         * BoletoPayment.Log.Page.cursor: cursor to retrieve the next page of BoletoPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged BoletoPayment.Logs
         * <p>
         * Receive a list of up to 100 BoletoPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of BoletoPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return BoletoPayment.Log.Page object:
         * BoletoPayment.Log.Page.logs: list of BoletoPayment.Log objects with updated attributes
         * BoletoPayment.Log.Page.cursor: cursor to retrieve the next page of BoletoPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkbank.utils.Page page = Rest.getPage(data, params, user);
            List<Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((Log) log);
            }
            return new Log.Page(logs, page.cursor);
        }
    }
}
