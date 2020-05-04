package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class BoletoPayment extends Resource {
    static ClassData data = new ClassData(BoletoPayment.class, "BoletoPayment");

    public final Integer amount;
    public final String taxId;
    public final String description;
    public final String line;
    public final String barCode;
    public final String scheduled;
    public final String[] tags;
    public final String status;
    public final Integer fee;
    public final String created;

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
     * @param status [string, default null]: current payment status. ex: "registered" or "paid"
     * @param amount [int, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * @param fee [integer, default null]: fee charged when boleto payment is created. ex: 200 (= R$ 2.00)
     * @param created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
     * @param taxId identification for tax purposes (CPF)
     * @param tags list of tags
     * @param description description of the BoletoPayment
     * @param scheduled the time of scheduled payment
     * @param line numeric code of the BoletoPayment
     * @param barCode bar code of the Boleto
     */
    public BoletoPayment(int amount, String taxId, String[] tags, String description, String scheduled,
                         String line, String barCode, String id, int fee, String status, String created
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
     * status [string, default null]: current payment status. ex: "registered" or "paid"
     * amount [int, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * fee [integer, default null]: fee charged when boleto payment is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
     */
    public BoletoPayment(Map<String, Object> data) {
        super(null);
        this.taxId = (String) data.get("taxId");
        this.description = (String) data.get("description");
        this.line = (String) data.get("line");
        this.barCode = (String) data.get("barCode");
        this.scheduled = (String) data.get("scheduled");
        this.tags = (String[]) data.get("tags");
        this.amount = null;
        this.created = null;
        this.fee = null;
        this.status = null;
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
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static BoletoPayment get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve BoletoPayments
     * <p>
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "paid"
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
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<BoletoPayment> query(Project user) throws Exception {
        return BoletoPayment.query(new HashMap<>(), user);
    }

    /**
     * Retrieve BoletoPayments
     * <p>
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API
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
     * Receive a generator of BoletoPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "paid"
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<BoletoPayment> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Create BoletoPayments
     * <p>
     * Send a list of BoletoPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param boletoPayments [list of BoletoPayment objects]: list of BoletoPayment objects to be created in the API
     * <p>
     * Return:
     * @return list of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<BoletoPayment> create(List<BoletoPayment> boletoPayments) throws Exception {
        return BoletoPayment.create(boletoPayments, null);
    }

    /**
     * Create BoletoPayments
     * <p>
     * Send a list of BoletoPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param boletoPayments [list of BoletoPayment objects]: list of BoletoPayment objects to be created in the API
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of BoletoPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<BoletoPayment> create(List<BoletoPayment> boletoPayments, Project user) throws Exception {
        return Rest.post(data, boletoPayments, user);
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
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
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
     * @return deleted BoletoPayment with updated attributes
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
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted BoletoPayment with updated attributes
     * @throws Exception error in the request
     */
    public static BoletoPayment delete(String id, Project user) throws Exception {
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
         * @param type [string]: type of the BoletoPayment event which triggered the log creation. ex: "registered" or "paid"
         * @param created [string]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
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
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return BoletoPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve BoletoPayment Logs
         * <p>
         * Receive a generator of BoletoPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "paid" or "registered"
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
         * Receive a generator of BoletoPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of BoletoPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve BoletoPayment Logs
         * <p>
         * Receive a generator of BoletoPayment Log objects previously created in the Stark Bank API
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
         * Receive a generator of BoletoPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "paid" or "registered"
         * paymentIds [list of strings, default null]: list of BoletoPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of BoletoPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }
    }
}
