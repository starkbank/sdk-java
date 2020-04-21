package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


public class UtilityPayment extends Resource {
    static ClassData data = new ClassData(UtilityPayment.class, "UtilityPayment");

    public String description;
    public String line;
    public String barCode;
    public String scheduled;
    public String[] tags;
    public String status;
    public Integer amount;
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
     * line [string, default null]: Number sequence that describes the payment. Either "line" or "bar_code" parameters are required. If both are sent, they must match. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string, default null]: Bar code number that describes the payment. Either "line" or "barCode" parameters are required. If both are sent, they must match. ex: "34195819600000000621090063571277307144464000"
     * <p>
     * Parameters:
     * description [string]: Text to be displayed in your statement (min. 10 characters). ex: "payment ABC"
     * scheduled [string, default today]: payment scheduled date. ex: "2020-03-10"
     * tags [list of strings]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * id [string, default null]: unique id returned when payment is created. ex: "5656565656565656"
     * status [string, default null]: current payment status. ex: "registered" or "paid"
     * amount [int, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * fee [integer, default null]: fee charged when utility payment is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
     */
    public UtilityPayment(Integer amount, String[] tags, String description, String scheduled,
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
     * status [string, default null]: current payment status. ex: "registered" or "paid"
     * amount [int, default null]: amount automatically calculated from line or bar_code. ex: 23456 (= R$ 234.56)
     * fee [integer, default null]: fee charged when utility payment is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
     */
    public UtilityPayment(HashMap<String, Object> data) {
        super(null);
        this.description = (String) data.get("description");
        this.line = (String) data.get("line");
        this.barCode = (String) data.get("barCode");
        this.scheduled = (String) data.get("scheduled");
        this.tags = (String[]) data.get("tags");
    }

    /**
     * Retrieve a specific UtilityPayment
     * <p>
     * Receive a single UtilityPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * UtilityPayment object with updated attributes
     */
    public static UtilityPayment get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific UtilityPayment
     * <p>
     * Receive a single UtilityPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * UtilityPayment object with updated attributes
     */
    public static UtilityPayment get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "paid"
     * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * generator of UtilityPayment objects with updated attributes
     */
    public static Generator<UtilityPayment> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "paid"
     * <p>
     * Return:
     * generator of UtilityPayment objects with updated attributes
     */
    public static Generator<UtilityPayment> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    /**
     * Retrieve UtilityPayments
     * <p>
     * Receive a generator of UtilityPayment objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * generator of UtilityPayment objects with updated attributes
     */
    public static Generator<UtilityPayment> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    /**
     * Create UtilityPayments
     * <p>
     * Send a list of UtilityPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * payments [list of UtilityPayment objects]: list of UtilityPayment objects to be created in the API
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * Return:
     * list of UtilityPayment objects with updated attributes
     */
    public static List<UtilityPayment> create(List<UtilityPayment> utilityPayments, Project user) throws Exception {
        return Rest.post(data, utilityPayments, user);
    }

    /**
     * Create UtilityPayments
     * <p>
     * Send a list of UtilityPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * payments [list of UtilityPayment objects]: list of UtilityPayment objects to be created in the API
     * <p>
     * Return:
     * list of UtilityPayment objects with updated attributes
     */
    public static List<UtilityPayment> create(List<UtilityPayment> utilityPayments) throws Exception {
        return Rest.post(data, utilityPayments, null);
    }

    /**
     * Retrieve a specific UtilityPayment pdf file
     * <p>
     * Receive a single UtilityPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for utility payments with "success" status.
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * UtilityPayment pdf file
     */
    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    /**
     * Retrieve a specific UtilityPayment pdf file
     * <p>
     * Receive a single UtilityPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for utility payments with "success" status.
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * UtilityPayment pdf file
     */
    public static InputStream pdf(String id) throws Exception {
        return Rest.getPdf(data, id, null);
    }

    /**
     * Delete a UtilityPayment entity
     * <p>
     * Delete a UtilityPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * id [string]: UtilityPayment unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * deleted UtilityPayment with updated attributes
     */
    public static UtilityPayment delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    /**
     * Delete a UtilityPayment entity
     * <p>
     * Delete a UtilityPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * id [string]: UtilityPayment unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * deleted UtilityPayment with updated attributes
     */
    public static UtilityPayment delete(String id) throws Exception {
        return Rest.delete(data, id, null);
    }

    public static class Log extends Resource {
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
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * payment [UtilityPayment]: UtilityPayment entity to which the log refers to.
         * errors [list of strings]: list of errors linked to this BoletoPayment event.
         * type [string]: type of the UtilityPayment event which triggered the log creation. ex: "registered" or "paid"
         * created [string]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000"
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
         * id [string]: object unique id. ex: "5656565656565656"
         * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * UtilityPayment Log object with updated attributes
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve a specific UtilityPayment Log
         * <p>
         * Receive a single UtilityPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * UtilityPayment Log object with updated attributes
         */
        public static Log get(String id) throws Exception {
            return Rest.getId(data, id, null);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "paid" or "registered"
         * paymentIds [list of strings, default null]: list of UtilityPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * list of UtilityPayment Log objects with updated attributes
         */
        public static Generator<Log> query(HashMap<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "paid" or "registered"
         * paymentIds [list of strings, default null]: list of UtilityPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * list of UtilityPayment Log objects with updated attributes
         */
        public static Generator<Log> query(HashMap<String, Object> params) throws Exception {
            return Rest.getList(data, params, null);
        }

        /**
         * Retrieve UtilityPayment Logs
         * <p>
         * Receive a generator of UtilityPayment Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * list of UtilityPayment Log objects with updated attributes
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Rest.getList(data, new HashMap<>(), user);
        }

    }
}
