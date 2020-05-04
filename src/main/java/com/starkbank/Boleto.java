package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Boleto extends Resource {
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
     * descriptions [list of Hashmaps, default null]: list of hashmaps with "text":string and (optional) "amount":int pairs
     * discounts [list of Hashmaps, default null]: list of hashmaps with "percentage": Double and "date": string pairs
     * tags [list of strings]: list of strings for tagging
     * id [string, default null]: unique id returned when Boleto is created. ex: "5656565656565656"
     * fee [integer, default null]: fee charged when Boleto is paid. ex: 200 (= R$ 2.00)
     * line [string, default null]: generated Boleto line for payment. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string, default null]: generated Boleto bar-code for payment. ex: "34195819600000000621090063571277307144464000"
     * status [string, default null]: current Boleto status. ex: "registered" or "paid"
     * created [string, default null]: creation datetime for the Boleto. ex: "2020-03-10 10:30:00.000"
     */
    static ClassData data = new ClassData(Boleto.class, "Boleto");

    public final Integer amount;
    public final String name;
    public final String taxId;
    public final String streetLine1;
    public final String streetLine2;
    public final String district;
    public final String city;
    public final String stateCode;
    public final String zipCode;
    public final String due;
    public final Double fine;
    public final Double interest;
    public final Integer overdueLimit;
    public final String[] tags;
    public final List<Map<String, Object>> descriptions;
    public final List<Map<String, Object>> discounts;
    public final Integer fee;
    public final String line;
    public final String barCode;
    public final String status;
    public final String created;

    /**
     * Boleto object
     * When you initialize a Boleto, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * All parameters are passed in a Map of String and Object object.

     * Parameters:
     * @param amount [integer]: Boleto value in cents. Minimum = 200 (R$2,00). ex: 1234 (= R$ 12.34)
     * @param name [string]: payer full name. ex: "Anthony Edward Stark"
     * @param taxId [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param streetLine1 [string]: payer main address. ex: Av. Paulista, 200
     * @param streetLine2 [string]: payer address complement. ex: Apto. 123
     * @param district [string]: payer address district / neighbourhood. ex: Bela Vista
     * @param city [string]: payer address city. ex: Rio de Janeiro
     * @param stateCode [string]: payer address state. ex: GO
     * @param zipCode [string]: payer address zip code. ex: 01311-200
     * @param due [string, default today + 2 days]: Boleto due date in ISO format. ex: 2020-04-30
     * @param fine [float, default 0.0]: Boleto fine for overdue payment in %. ex: 2.5
     * @param interest [float, default 0.0]: Boleto monthly interest for overdue payment in %. ex: 5.2
     * @param overdueLimit [integer, default 59]: limit in days for automatic Boleto cancellation after due date. ex: 7 (max: 59)
     * @param descriptions [list of HashMaps, default null]: list of hashmaps with "text":string and (optional) "amount":int pairs
     * @param discounts [list of Hashmaps, default null]: list of hashmaps with "percentage": Double and "date": string pairs
     * @param tags [list of strings]: list of strings for tagging
     * @param id id of the object
     * @param fee fee to be charged in the Boleto
     * @param line numeric line of the boleto
     * @param barCode numeric barcode of the boleto
     * @param status status of the boleto
     * @param created date the boleto was created
     */
    public Boleto(Integer amount, String name, String taxId, String streetLine1, String streetLine2,
                  String district, String city, String stateCode, String zipCode, String due, Double fine,
                  Double interest, Integer overdueLimit, String[] tags, List<Map<String, Object>> descriptions,
                  List<Map<String, Object>> discounts, String id, Integer fee, String line, String barCode,
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
     * All parameters are passed in a Map of String and Object object.
     * <p>
     * @param data map of parameters for the creation of the Boleto
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
     * <p>
     * Parameters (optional):
     * fine [float, default 0.0]: Boleto fine for overdue payment in %. ex: 2.5
     * interest [float, default 0.0]: Boleto monthly interest for overdue payment in %. ex: 5.2
     * overdueLimit [integer, default 59]: limit in days for automatic Boleto cancellation after due date. ex: 7 (max: 59)
     * descriptions [list of HashMap, default null]: list of hashmaps with "text":string and "amount":int pairs
     * discounts [list of Hashmap, default null]: list of hashmaps with "percentage": Double and "date": string pairs
     * tags [list of strings]: list of strings for tagging
     */
    @SuppressWarnings("unchecked")
    public Boleto(Map<String, Object> data) {
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
        this.descriptions = (List<Map<String, Object>>) data.get("descriptions");
        this.discounts = (List<Map<String, Object>>) data.get("discounts");
        this.barCode = null;
        this.created = null;
        this.fee = null;
        this.line = null;
        this.status = null;
    }

    /**
     * Retrieve a specific Boleto
     * <p>
     * Receive a single Boleto object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Return:
     * @return Boleto object with updated attributes
     * @throws Exception error in the request 
     */
    public static Boleto get(String id) throws Exception {
        return Boleto.get(id, null);
    }

    /**
     * Retrieve a specific Boleto
     * <p>
     * Receive a single Boleto object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Parameters:
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return Boleto object with updated attributes
     * @throws Exception error in the request 
     */
    public static Boleto get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Boleto objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Boleto> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of Boleto objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Boleto> query(Map<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Boleto objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Boleto> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    /**
     * Retrieve Boletos
     * <p>
     * Receive a generator of Boleto objects previously created in the Stark Bank API
     * Return:
     * @return generator of Boleto objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Boleto> query() throws Exception {
        return Rest.getList(data, new HashMap<>(), null);
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of Boleto objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param boletos [list of Boleto objects]: list of Boleto objects to be created in the API
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of Boleto objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<Boleto> create(List<Boleto> boletos, Project user) throws Exception {
        return Rest.post(data, boletos, user);
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of Boleto objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param boletos [list of Boleto objects]: list of Boleto objects to be created in the API
     * <p>
     * Return:
     * @return list of Boleto objects with updated attributes
     * @throws Exception error in the request 
     */
    public static List<Boleto> create(List<Boleto> boletos) throws Exception {
        return Rest.post(data, boletos, null);
    }

    /**
     * Retrieve a specific Boleto pdf file
     * <p>
     * Receive a single Boleto pdf file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Boleto pdf file
     * @throws Exception error in the request 
     */
    public static InputStream pdf(String id) throws Exception {
        return Boleto.pdf(id, null);
    }

    /**
     * Retrieve a specific Boleto pdf file
     * <p>
     * Receive a single Boleto pdf file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Boleto pdf file
     * @throws Exception error in the request 
     */
    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    /**
     * Delete a Boleto entity
     * <p>
     * Delete a Boleto entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: Boleto unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted Boleto with updated attributes
     * @throws Exception error in the request 
     */
    public static Boleto delete(String id) throws Exception {
        return Boleto.delete(id, null);
    }

    /**
     * Delete a Boleto entity
     * <p>
     * Delete a Boleto entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: Boleto unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted Boleto with updated attributes
     * @throws Exception error in the request 
     */
    public static Boleto delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
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
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param boleto [Boleto]: Boleto entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this Boleto event
         * @param type [string]: type of the Boleto event which triggered the log creation. ex: "registered" or "paid"
         * @param created [string]: creation datetime for the boleto. ex: "2020-03-10 10:30:00.000"
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
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return Boleto Log object with updated attributes
         * @throws Exception error in the request 
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific Boleto Log
         * <p>
         * Receive a single Boleto Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Boleto Log object with updated attributes
         * @throws Exception error in the request 
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * boletoIds [list of strings, default null]: list of Boleto ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of Boleto Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of Boleto Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Return:
         * @return list of Boleto Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve Boleto Logs
         * <p>
         * Receive a generator of Boleto Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "paid" or "registered"
         * boletoIds [list of strings, default null]: list of Boleto ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of Boleto Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Map<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }
    }
}
