package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class IssPayment extends Resource {
    static ClassData data = new ClassData(IssPayment.class, "IssPayment");

    public final String description;
    public final String line;
    public final String barCode;
    public final String scheduled;
    public final String[] tags;
    public final String status;
    public final Long amount;
    public final Integer fee;
    public final String created;

    /**
     * IssPayment object
     * <p>
     * When you initialize a IssPayment, the entity will not be automatically
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
     * @param fee [Integer, default null]: fee charged when the ISS payment is created. ex: 200 (= R$ 2.00)
     * @param created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
     */
    public IssPayment(Long amount, String[] tags, String description, String scheduled,
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
     * IssPayment object
     * <p>
     * When you initialize a IssPayment, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param data map of properties for the creation of the IssPayment
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
     * fee [Integer, default null]: fee charged when the ISS payment is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
     */
    public IssPayment(Map<String, Object> data) throws Exception {
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
     * Retrieve a specific IssPayment
     * <p>
     * Receive a single IssPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return IssPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static IssPayment get(String id) throws Exception {
        return IssPayment.get(id, null);
    }

    /**
     * Retrieve a specific IssPayment
     * <p>
     * Receive a single IssPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return IssPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static IssPayment get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve IssPayments
     * <p>
     * Receive a generator of IssPayment objects previously created in the Stark Bank API
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
     * @return generator of IssPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<IssPayment> query(Map<String, Object> params) throws Exception {
        return IssPayment.query(params, null);
    }

    /**
     * Retrieve IssPayments
     * <p>
     * Receive a generator of IssPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of IssPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<IssPayment> query(Project user) throws Exception {
        return IssPayment.query(new HashMap<>(), user);
    }

    /**
     * Retrieve IssPayments
     * <p>
     * Receive a generator of IssPayment objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of IssPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<IssPayment> query() throws Exception {
        return IssPayment.query(new HashMap<>(), null);
    }

    /**
     * Retrieve IssPayments
     * <p>
     * Receive a generator of IssPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params paremeters of the query
     * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of IssPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<IssPayment> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Create IssPayments
     * <p>
     * Send a list of IssPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of IssPayment objects or HashMaps]: list of IssPayment objects to be created in the API
     * <p>
     * Return:
     * @return list of IssPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<IssPayment> create(List<?> payments) throws Exception {
        return IssPayment.create(payments, null);
    }

    /**
     * Create IssPayments
     * <p>
     * Send a list of IssPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of IssPayment objects or HashMaps]: list of IssPayment objects to be created in the API
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return list of IssPayment objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<IssPayment> create(List<?> payments, Project user) throws Exception {
        List<IssPayment> paymentList = new ArrayList<>();
        for (Object payment : payments){
            if (payment.getClass() == HashMap.class){
                paymentList.add(new IssPayment((Map<String, Object>) payment));
                continue;
            }
            if (payment.getClass() == IssPayment.class){
                paymentList.add((IssPayment) payment);
                continue;
            }
            throw new Exception("Unknown type \"" + payment.getClass() + "\", use IssPayment or HashMap");
        }
        return Rest.post(data, paymentList, user);
    }

    /**
     * Retrieve a specific IssPayment pdf file
     * <p>
     * Receive a single IssPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for ISS payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return IssPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id) throws Exception {
        return IssPayment.pdf(id, null);
    }

    /**
     * Retrieve a specific IssPayment pdf file
     * <p>
     * Receive a single IssPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for ISS payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return IssPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user, null);
    }

    /**
     * Delete a IssPayment entity
     * <p>
     * Delete a IssPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: IssPayment unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted IssPayment with updated attributes
     * @throws Exception error in the request
     */
    public static IssPayment delete(String id) throws Exception {
        return IssPayment.delete(id, null);
    }

    /**
     * Delete a IssPayment entity
     * <p>
     * Delete a IssPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: IssPayment unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted IssPayment with updated attributes
     * @throws Exception error in the request
     */
    public static IssPayment delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "IssPaymentLog");

        public String created;
        public String type;
        public String[] errors;
        public IssPayment payment;

        /**
         * IssPayment Log object
         * <p>
         * Every time a IssPayment entity is modified, a corresponding IssPayment Log
         * is generated for the entity. This log is never generated by the user, but it can
         * be retrieved to check additional information on the IssPayment.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param payment [IssPayment]: IssPayment entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this IssPayment event.
         * @param type [string]: type of the IssPayment event which triggered the log creation. ex: "processing" or "success"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000"
         */
        public Log(String created, String type, String[] errors, IssPayment payment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.payment = payment;
        }

        /**
         * Retrieve a specific IssPayment Log
         * <p>
         * Receive a single IssPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return IssPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific IssPayment Log
         * <p>
         * Receive a single IssPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return IssPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve IssPayment Logs
         * <p>
         * Receive a generator of IssPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of IssPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of IssPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve IssPayment Logs
         * <p>
         * Receive a generator of IssPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of IssPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve IssPayment Logs
         * <p>
         * Receive a generator of IssPayment Log objects previously created in the Stark Bank API
         * <p>
         * Return:
         * @return list of IssPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve IssPayment Logs
         * <p>
         * Receive a generator of IssPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of IssPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of IssPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }
    }
}
