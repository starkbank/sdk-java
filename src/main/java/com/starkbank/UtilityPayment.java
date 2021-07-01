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


public final class UtilityPayment extends Resource {
    static ClassData data = new ClassData(UtilityPayment.class, "UtilityPayment");

    public String description;
    public String line;
    public String barCode;
    public String scheduled;
    public String[] tags;
    public String status;
    public Long amount;
    public Integer fee;
    public String created;

    /**
     * UtilityPayment object
     * <p>
     * When you initialize a UtilityPayment, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters (conditionally required):
     * @param line [string, default null]: Number sequence that describes the payment. Either "line" or "bar_code" parameters are required. If both are sent, they must match. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * @param barCode [string, default null]: Bar code number that describes the payment. Either "line" or "barCode" parameters are required. If both are sent, they must match. ex: "34195819600000000621090063571277307144464000"
     * <p>
     * Parameters:
     * @param description [string]: Text to be displayed in your statement (min. 10 characters). ex: "payment ABC"
     * @param scheduled [string, default today]: payment scheduled date. ex: "2020-03-10"
     * @param tags [list of strings]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * @param id [string, default null]: unique id returned when payment is created. ex: "5656565656565656"
     * @param status [string, default null]: current payment status. ex: "processing" or "success"
     * @param amount [long, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * @param fee [Integer, default null]: fee charged when the utility payment is created. ex: 200 (= R$ 2.00)
     * @param created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public UtilityPayment(Long amount, String[] tags, String description, String scheduled,
                          String line, String barCode, String id, Integer fee, String status, String created
    ) {
        super(id);
        this.description = description;
        this.line = line;
        this.barCode = barCode;
        this.scheduled = scheduled;
        this.tags = tags;
        this.status = status;
        this.amount = amount;
        this.fee = fee;
        this.created = created;
    }

    /**
     * UtilityPayment object
     * <p>
     * When you initialize a UtilityPayment, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param data map of properties for the creation of the UtilityPayment
     * Parameters (conditionally required):
     * line [string, default null]: Number sequence that describes the payment. Either "line" or "bar_code" parameters are required. If both are sent, they must match. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string, default null]: Bar code number that describes the payment. Either "line" or "barCode" parameters are required. If both are sent, they must match. ex: "34195819600000000621090063571277307144464000"
     * <p>
     * Parameters:
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
     * fee [Integer, default null]: fee charged when the utility payment is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public UtilityPayment(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

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
     * Retrieve a specific UtilityPayment
     * <p>
     * Receive a single UtilityPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return UtilityPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static UtilityPayment get(String id) throws Exception {
        return UtilityPayment.get(id, null);
    }

    /**
     * Retrieve a specific UtilityPayment
     * <p>
     * Receive a single UtilityPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return UtilityPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static UtilityPayment get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * <p>
     * Return:
     * @return generator of UtilityPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<UtilityPayment> query(Map<String, Object> params) throws Exception {
        return UtilityPayment.query(params, null);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of UtilityPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<UtilityPayment> query(User user) throws Exception {
        return UtilityPayment.query(new HashMap<>(), user);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of UtilityPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<UtilityPayment> query() throws Exception {
        return UtilityPayment.query(new HashMap<>(), null);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params paremeters of the query
     * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of UtilityPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<UtilityPayment> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public final static class Page {
        public List<UtilityPayment> payments;
        public String cursor;

        public Page(List<UtilityPayment> payments, String cursor) {
            this.payments = payments;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged UtilityPayments
     * <p>
     * Receive a list of up to 100 UtilityPayment objects previously created in the Stark Bank API and the cursor to the next page.
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
     * @return UtilityPayment.Page object:
     * UtilityPayment.Page.payments: list of UtilityPayment objects with updated attributes
     * UtilityPayment.Page.cursor: cursor to retrieve the next page of UtilityPayment objects
     * @throws Exception error in the request
     */
    public static UtilityPayment.Page page(Map<String, Object> params) throws Exception {
        return UtilityPayment.page(params, null);
    }

    /**
     * Retrieve paged UtilityPayments
     * <p>
     * Receive a list of up to 100 UtilityPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return UtilityPayment.Page object:
     * UtilityPayment.Page.payments: list of UtilityPayment objects with updated attributes
     * UtilityPayment.Page.cursor: cursor to retrieve the next page of UtilityPayment objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged UtilityPayments
     * <p>
     * Receive a list of up to 100 UtilityPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return UtilityPayment.Page object:
     * UtilityPayment.Page.payments: list of UtilityPayment objects with updated attributes
     * UtilityPayment.Page.cursor: cursor to retrieve the next page of UtilityPayment objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged UtilityPayments
     * <p>
     * Receive a list of up to 100 UtilityPayment objects previously created in the Stark Bank API and the cursor to the next page.
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
     * @return UtilityPayment.Page object:
     * UtilityPayment.Page.payments: list of UtilityPayment objects with updated attributes
     * UtilityPayment.Page.cursor: cursor to retrieve the next page of UtilityPayment objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<UtilityPayment> payments = new ArrayList<>();
        for (SubResource payment: page.entities) {
            payments.add((UtilityPayment) payment);
        }
        return new Page(payments, page.cursor);
    }

    /**
     * Create UtilityPayments
     * <p>
     * Send a list of UtilityPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of UtilityPayment objects or HashMaps]: list of UtilityPayment objects to be created in the API
     * <p>
     * Return:
     * @return list of UtilityPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<UtilityPayment> create(List<?> payments) throws Exception {
        return UtilityPayment.create(payments, null);
    }

    /**
     * Create UtilityPayments
     * <p>
     * Send a list of UtilityPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of UtilityPayment objects or HashMaps]: list of UtilityPayment objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return list of UtilityPayment objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<UtilityPayment> create(List<?> payments, User user) throws Exception {
        List<UtilityPayment> paymentList = new ArrayList<>();
        for (Object payment : payments){
            if (payment instanceof Map){
                paymentList.add(new UtilityPayment((Map<String, Object>) payment));
                continue;
            }
            if (payment instanceof UtilityPayment){
                paymentList.add((UtilityPayment) payment);
                continue;
            }
            throw new Exception("Unknown type \"" + payment.getClass() + "\", use UtilityPayment or HashMap");
        }
        return Rest.post(data, paymentList, user);
    }

    /**
     * Retrieve a specific UtilityPayment pdf file
     * <p>
     * Receive a single UtilityPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for utility payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return UtilityPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id) throws Exception {
        return UtilityPayment.pdf(id, null);
    }

    /**
     * Retrieve a specific UtilityPayment pdf file
     * <p>
     * Receive a single UtilityPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for utility payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return UtilityPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, User user) throws Exception {
        return Rest.getContent(data, id, "pdf", user, null);
    }

    /**
     * Delete a UtilityPayment entity
     * <p>
     * Delete a UtilityPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: UtilityPayment unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted UtilityPayment object
     * @throws Exception error in the request
     */
    public static UtilityPayment delete(String id) throws Exception {
        return UtilityPayment.delete(id, null);
    }

    /**
     * Delete a UtilityPayment entity
     * <p>
     * Delete a UtilityPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: UtilityPayment unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted UtilityPayment object
     * @throws Exception error in the request
     */
    public static UtilityPayment delete(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "UtilityPaymentLog");

        public String created;
        public String type;
        public String[] errors;
        public UtilityPayment payment;

        /**
         * UtilityPayment Log object
         * <p>
         * Every time a UtilityPayment entity is modified, a corresponding UtilityPayment Log
         * is generated for the entity. This log is never generated by the user, but it can
         * be retrieved to check additional information on the UtilityPayment.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param payment [UtilityPayment]: UtilityPayment entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this UtilityPayment event.
         * @param type [string]: type of the UtilityPayment event which triggered the log creation. ex: "processing" or "success"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, String[] errors, UtilityPayment payment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.payment = payment;
        }

        /**
         * Retrieve a specific UtilityPayment Log
         * <p>
         * Receive a single UtilityPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return UtilityPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific UtilityPayment Log
         * <p>
         * Receive a single UtilityPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return UtilityPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of UtilityPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of UtilityPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of UtilityPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of UtilityPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of UtilityPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of UtilityPayment Log objects with updated attributes
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
         * Retrieve paged UtilityPayment.Logs
         * <p>
         * Receive a list of up to 100 UtilityPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of UtilityPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return UtilityPayment.Log.Page object:
         * UtilityPayment.Log.Page.logs: list of UtilityPayment.Log objects with updated attributes
         * UtilityPayment.Log.Page.cursor: cursor to retrieve the next page of UtilityPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged UtilityPayment.Logs
         * <p>
         * Receive a list of up to 100 UtilityPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return UtilityPayment.Log.Page object:
         * UtilityPayment.Log.Page.logs: list of UtilityPayment.Log objects with updated attributes
         * UtilityPayment.Log.Page.cursor: cursor to retrieve the next page of UtilityPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged UtilityPayment.Logs
         * <p>
         * Receive a list of up to 100 UtilityPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return UtilityPayment.Log.Page object:
         * UtilityPayment.Log.Page.logs: list of UtilityPayment.Log objects with updated attributes
         * UtilityPayment.Log.Page.cursor: cursor to retrieve the next page of UtilityPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged UtilityPayment.Logs
         * <p>
         * Receive a list of up to 100 UtilityPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of UtilityPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return UtilityPayment.Log.Page object:
         * UtilityPayment.Log.Page.logs: list of UtilityPayment.Log objects with updated attributes
         * UtilityPayment.Log.Page.cursor: cursor to retrieve the next page of UtilityPayment.Log objects
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
