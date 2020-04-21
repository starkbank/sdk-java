package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


public class Boleto extends Resource {
    /**
     * Boleto object
     * <p>
     * When you initialize a Boleto, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * amount [integer]: Boleto value in cents. Minimum = 200 (R$2,00). ex: 1234 (= R$ 12.34)
     * name [string]: payer full name. ex: "Anthony Edward Stark"
     * taxId [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * streetLine1 [string]: payer main address. ex: Av. Paulista, 200
     * streetLine2 [string]: payer address complement. ex: Apto. 123
     * district [string]: payer address district / neighbourhood. ex: Bela Vista
     * city [string]: payer address city. ex: Rio de Janeiro
     * stateCode [string]: payer address state. ex: GO
     * zipCode [string]: payer address zip code. ex: 01311-200
     * due [string, default today + 2 days]: Boleto due date in ISO format. ex: 2020-04-30
     * fine [float, default 0.0]: Boleto fine for overdue payment in %. ex: 2.5
     * interest [float, default 0.0]: Boleto monthly interest for overdue payment in %. ex: 5.2
     * overdueLimit [integer, default 59]: limit in days for automatic Boleto cancellation after due date. ex: 7 (max: 59)
     * descriptions [list of dictionaries, default null]: list of dictionaries with "text":string and (optional) "amount":int pairs
     * tags [list of strings]: list of strings for tagging
     * id [string, default null]: unique id returned when Boleto is created. ex: "5656565656565656"
     * fee [integer, default null]: fee charged when Boleto is paid. ex: 200 (= R$ 2.00)
     * line [string, default null]: generated Boleto line for payment. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string, default null]: generated Boleto bar-code for payment. ex: "34195819600000000621090063571277307144464000"
     * status [string, default null]: current Boleto status. ex: "registered" or "paid"
     * created [string, default null]: creation datetime for the Boleto. ex: "2020-03-10 10:30:00.000"
     */
    static ClassData data = new ClassData(Boleto.class, "Boleto");

    public Integer amount;
    public String name;
    public String taxId;
    public String streetLine1;
    public String streetLine2;
    public String district;
    public String city;
    public String stateCode;
    public String zipCode;
    public String due;
    public Double fine;
    public Double interest;
    public Integer overdueLimit;
    public String[] tags;
    public List<HashMap<String, Object>> descriptions;
    public List<HashMap<String, Object>> discounts;
    public Integer fee;
    public String line;
    public String barCode;
    public String status;
    public String created;

    /**
     * Boleto object
     * <p>
     * When you initialize a Boleto, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * All parameters are passed in a HashMap<String, Object> object.
     * <p>
     * Parameters (required):
     * amount [integer]: Boleto value in cents. Minimum = 200 (R$2,00). ex: 1234 (= R$ 12.34)
     * name [string]: payer full name. ex: "Anthony Edward Stark"
     * taxId [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * streetLine1 [string]: payer main address. ex: Av. Paulista, 200
     * streetLine2 [string]: payer address complement. ex: Apto. 123
     * district [string]: payer address district / neighbourhood. ex: Bela Vista
     * city [string]: payer address city. ex: Rio de Janeiro
     * stateCode [string]: payer address state. ex: GO
     * zipCode [string]: payer address zip code. ex: 01311-200
     * due [string, default today + 2 days]: Boleto due date in ISO format. ex: 2020-04-30
     * <p>
     * Parameters (optional):
     * fine [float, default 0.0]: Boleto fine for overdue payment in %. ex: 2.5
     * interest [float, default 0.0]: Boleto monthly interest for overdue payment in %. ex: 5.2
     * overdueLimit [integer, default 59]: limit in days for automatic Boleto cancellation after due date. ex: 7 (max: 59)
     * descriptions [list of HashMap, default null]: list of hashmaps with "text":string and (optional) "amount":int pairs
     * discounts [list of Hashmap, default null]: list of hashmaps with "percentage": Double and "date": string pairs
     * tags [list of strings]: list of strings for tagging
     */
    public Boleto(Integer amount, String name, String taxId, String streetLine1, String streetLine2,
                  String district, String city, String stateCode, String zipCode, String due, Double fine,
                  Double interest, Integer overdueLimit, String[] tags, List<HashMap<String, Object>> descriptions,
                  List<HashMap<String, Object>> discounts, String id, Integer fee, String line, String barCode,
                  String status, String created
    ) {
        super(id);
        this.amount = amount;
        this.name = name;
        this.taxId = taxId;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.district = district;
        this.city = city;
        this.stateCode = stateCode;
        this.zipCode = zipCode;
        this.due = due;
        this.fine = fine;
        this.interest = interest;
        this.overdueLimit = overdueLimit;
        this.tags = tags;
        this.descriptions = descriptions;
        this.discounts = discounts;
        this.fee = fee;
        this.line = line;
        this.barCode = barCode;
        this.status = status;
        this.created = created;
    }

    /**
     * Boleto object
     * <p>
     * When you initialize a Boleto, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * All parameters are passed in a HashMap<String, Object> object.
     * <p>
     * Parameters (required):
     * amount [integer]: Boleto value in cents. Minimum = 200 (R$2,00). ex: 1234 (= R$ 12.34)
     * name [string]: payer full name. ex: "Anthony Edward Stark"
     * taxId [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * streetLine1 [string]: payer main address. ex: Av. Paulista, 200
     * streetLine2 [string]: payer address complement. ex: Apto. 123
     * district [string]: payer address district / neighbourhood. ex: Bela Vista
     * city [string]: payer address city. ex: Rio de Janeiro
     * stateCode [string]: payer address state. ex: GO
     * zipCode [string]: payer address zip code. ex: 01311-200
     * due [string, default today + 2 days]: Boleto due date in ISO format. ex: 2020-04-30
     * <p>
     * Parameters (optional):
     * fine [float, default 0.0]: Boleto fine for overdue payment in %. ex: 2.5
     * interest [float, default 0.0]: Boleto monthly interest for overdue payment in %. ex: 5.2
     * overdueLimit [integer, default 59]: limit in days for automatic Boleto cancellation after due date. ex: 7 (max: 59)
     * descriptions [list of HashMap, default null]: list of hashmaps with "text":string and (optional) "amount":int pairs
     * discounts [list of Hashmap, default null]: list of hashmaps with "percentage": Double and "date": string pairs
     * tags [list of strings]: list of strings for tagging
     */
    public Boleto(HashMap<String, Object> data) {
        super(null);
        this.amount = (Integer) data.get("amount");
        this.name = (String) data.get("name");
        this.taxId = (String) data.get("taxId");
        this.streetLine1 = (String) data.get("streetLine1");
        this.streetLine2 = (String) data.get("streetLine2");
        this.district = (String) data.get("district");
        this.city = (String) data.get("city");
        this.stateCode = (String) data.get("stateCode");
        this.zipCode = (String) data.get("zipCode");
        this.due = (String) data.get("due");
        this.fine = (Double) data.get("fine");
        this.interest = (Double) data.get("interest");
        this.overdueLimit = (Integer) data.get("overdueLimit");
        this.tags = (String[]) data.get("tags");
        this.descriptions = (List<HashMap<String, Object>>) data.get("descriptions");
        this.discounts = (List<HashMap<String, Object>>) data.get("discounts");
    }

    public static Boleto get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific Boleto
     * <p>
     * Receive a single Boleto object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters (required):
     * id [string]: object unique id. ex: "5656565656565656"
     * Parameters (optional):
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * Return:
     * Boleto object with updated attributes
     */
    public static Boleto get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * <p>
     * Parameters (optional):
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * generator of Boleto objects with updated attributes
     */
    public static Generator<Boleto> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * <p>
     * Parameters (optional):
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * generator of Boleto objects with updated attributes
     */
    public static Generator<Boleto> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * generator of Boleto objects with updated attributes
     */
    public static Generator<Boleto> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of Boleto objects for creation in the Stark Bank API
     * <p>
     * Parameters (required):
     * boletos [list of Boleto objects]: list of Boleto objects to be created in the API
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * list of Boleto objects with updated attributes
     */
    public static List<Boleto> create(List<Boleto> boletos, Project user) throws Exception {
        return Rest.post(data, boletos, user);
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of Boleto objects for creation in the Stark Bank API
     * <p>
     * Parameters (required):
     * boletos [list of Boleto objects]: list of Boleto objects to be created in the API
     * <p>
     * Return:
     * list of Boleto objects with updated attributes
     */
    public static List<Boleto> create(List<Boleto> boletos) throws Exception {
        return Rest.post(data, boletos, null);
    }

    /**
     * Retrieve a specific Boleto pdf file
     * <p>
     * Receive a single Boleto pdf file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters (required):
     * id [string]: object unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * Boleto pdf file
     */
    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    /**
     * Retrieve a specific Boleto pdf file
     * <p>
     * Receive a single Boleto pdf file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters (required):
     * id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * Boleto pdf file
     */
    public static InputStream pdf(String id) throws Exception {
        return Rest.getPdf(data, id, null);
    }

    /**
     * Delete a Boleto entity
     * <p>
     * Delete a Boleto entity previously created in the Stark Bank API
     * <p>
     * Parameters (required):
     * id [string]: Boleto unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * deleted Boleto with updated attributes
     */
    public static Boleto delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    /**
     * Delete a Boleto entity
     * <p>
     * Delete a Boleto entity previously created in the Stark Bank API
     * <p>
     * Parameters (required):
     * id [string]: Boleto unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * deleted Boleto with updated attributes
     */
    public static Boleto delete(String id) throws Exception {
        return Rest.delete(data, id, null);
    }

    public static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "BoletoLog");

        public String created;
        public String type;
        public String[] errors;
        public Boleto boleto;

        /**
         * Boleto Log object
         * <p>
         * Every time a Boleto entity is updated, a corresponding Boleto Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the Boleto.
         * <p>
         * Attributes:
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * boleto [Boleto]: Boleto entity to which the log refers to.
         * errors [list of strings]: list of errors linked to this Boleto event
         * type [string]: type of the Boleto event which triggered the log creation. ex: "registered" or "paid"
         * created [string]: creation datetime for the boleto. ex: "2020-03-10 10:30:00.000"
         */
        public Log(String created, String type, String[] errors, Boleto boleto, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.boleto = boleto;
        }

        /**
         * Retrieve a specific Boleto Log
         * <p>
         * Receive a single Boleto Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters (required):
         * id [string]: object unique id. ex: "5656565656565656"
         * user [Project object]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * Boleto Log object with updated attributes
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve a specific Boleto Log
         * <p>
         * Receive a single Boleto Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters (required):
         * id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * Boleto Log object with updated attributes
         */
        public static Log get(String id) throws Exception {
            return Rest.getId(data, id, null);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Parameters (optional):
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * boletoIds [list of strings, default null]: list of Boleto ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * list of Boleto Log objects with updated attributes
         */
        public static Generator<Log> query(HashMap<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Parameters (optional):
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * boletoIds [list of strings, default null]: list of Boleto ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * list of Boleto Log objects with updated attributes
         */
        public static Generator<Log> query(HashMap<String, Object> params) throws Exception {
            return Rest.getList(data, params, null);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * user [Project object, default null]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * list of Boleto Log objects with updated attributes
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Rest.getList(data, new HashMap<>(), user);
        }
    }
}
