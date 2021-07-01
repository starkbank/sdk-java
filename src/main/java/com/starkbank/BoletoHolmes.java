package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BoletoHolmes extends Resource {
    
    static ClassData data = new ClassData(BoletoHolmes.class, "BoletoHolmes");

    public final String[] tags;
    public final String boletoId;
    public final String status;
    public final String result;
    public final String created;
    public final String updated;
    
    /**
     * BoletoHolmes object
     * <p>
     * When you initialize a BoletoHolmes, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters (required):
     * @param boletoId [string]: investigated boleto entity ID. ex: "5656565656565656"
     * <p>
     * Parameters (optional):
     * @param tags [list of strings]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * @param id [string, default null]: unique id returned when holmes is created. ex: "5656565656565656"
     * @param status [string, default null]: current holmes status. ex: "solving" or "solved"
     * @param result [string, default null]: result of boleto status investigation. ex: "paid" or "cancelled"
     * @param created [string, default null]: creation datetime for the holmes. ex: datetime.datetime(2020, 3, 10, 10, 30, 0, 0)
     * @param updated [string, default null]: latest update datetime for the holmes. ex: datetime.datetime(2020, 3, 10, 10, 30, 0, 0)
     */
    public BoletoHolmes(String boletoId, String[] tags, String id, String status, String result, String created, String updated) {
        
        super(id);

        this.tags = tags;
        this.boletoId = boletoId;
        this.status = status;
        this.result = result;
        this.created = created;
        this.updated = updated;
    }
    

    /**
     * BoletoHolmes object
     * <p>
     * When you initialize a BoletoHolmes, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param data map of parameters for the creation of the BoletoHolmes
     * Parameters (required):
     * boletoId [string]: investigated boleto entity ID. ex: "5656565656565656"
     * <p>
     * Parameters (optional):
     * tags [list of strings]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * id [string, default null]: unique id returned when holmes is created. ex: "5656565656565656"
     * status [string, default null]: current holmes status. ex: "solving" or "solved"
     * result [string, default null]: result of boleto status investigation. ex: "paid" or "cancelled"
     * created [string, default null]: creation datetime for the holmes. ex: datetime.datetime(2020, 3, 10, 10, 30, 0, 0)
     * updated [string, default null]: latest update datetime for the holmes. ex: datetime.datetime(2020, 3, 10, 10, 30, 0, 0)
     * @throws Exception error in the request
     */

    public BoletoHolmes(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.tags = (String[]) dataCopy.remove("tags");
        this.boletoId = (String) dataCopy.remove("boletoId");
        this.status = (String) dataCopy.remove("status");
        this.result = (String) dataCopy.remove("result");
        this.created = (String) dataCopy.remove("created");
        this.updated = (String) dataCopy.remove("updated");

        if (!dataCopy.isEmpty()){
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve a specific BoletoHolmes
     * <p>
     * Receive a single BoletoHolmes object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters (required):
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Return:
     * @return BoletoHolmes object with updated attributes
     * @throws Exception error in the request 
     */
    public static BoletoHolmes get(String id) throws Exception {
        return BoletoHolmes.get(id, null);
    }

    /**
     * Retrieve a specific BoletoHolmes
     * <p>
     * Receive a single BoletoHolmes object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters (required):
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Parameters (optional):
     * @param user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * Return:
     * @return BoletoHolmes object with updated attributes
     * @throws Exception error in the request 
     */
    public static BoletoHolmes get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Create BoletoHolmes
     * <p>
     * Send a list of BoletoHolmes objects for creation in the Stark Bank API
     * <p>
     * Parameters (required):
     * @param holmes [list of BoletoHolmes objects]: list of BoletoHolmes objects to be created in the API
     * <p>
     * Parameters (optional):
     * @param user [Project object]: Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * @return list of BoletoHolmes objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<BoletoHolmes> create(List<?> holmes, User user) throws Exception {
        List<BoletoHolmes> holmesList = new ArrayList<>();
        for (Object sherlock : holmes){
            if (sherlock instanceof Map){
                holmesList.add(new BoletoHolmes((Map<String, Object>) sherlock));
                continue;
            }
            if (sherlock instanceof BoletoHolmes){
                holmesList.add((BoletoHolmes) sherlock);
                continue;
            }
            throw new Exception("Unknown type \"" + sherlock.getClass() + "\", use Boleto or HashMap");
        }
        return Rest.post(data, holmesList, user);
    }

    /**
     * Create BoletoHolmes
     * <p>
     * Send a list of BoletoHolmes objects for creation in the Stark Bank API
     * <p>
     * Parameters (required):
     * @param holmes [list of BoletoHolmes objects]: list of BoletoHolmes objects to be created in the API
     * <p>
     * Return:
     * @return list of BoletoHolmes objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<BoletoHolmes> create(List<?> holmes) throws Exception {
        return BoletoHolmes.create(holmes, null);
    }

    /**
     * Retrieve BoletoHolmes
     * <p>
     * Receive a generator of BoletoHolmes objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters (optional):
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
     * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "solving"
     * boletoId [string, default null]: filter for holmes that investigate a specific boleto by its ID. ex: "5656565656565656"
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of BoletoHolmes objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<BoletoHolmes> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve BoletoHolmes
     * <p>
     * Receive a generator of BoletoHolmes objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters (optional):
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
     * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "solving"
     * boletoId [string, default null]: filter for holmes that investigate a specific boleto by its ID. ex: "5656565656565656"
     * <p>
     * Return:
     * @return generator of BoletoHolmes objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<BoletoHolmes> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve BoletoHolmes
     * <p>
     * Receive a generator of BoletoHolmes objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters (optional):
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of BoletoHolmes objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<BoletoHolmes> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve BoletoHolmes
     * <p>
     * Receive a generator of BoletoHolmes objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * Return:
     * @return generator of BoletoHolmes objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<BoletoHolmes> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<BoletoHolmes> holmes;
        public String cursor;

        public Page(List<BoletoHolmes> holmes, String cursor) {
            this.holmes = holmes;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged BoletoHolmess
     * <p>
     * Receive a list of up to 100 BoletoHolmes objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
     * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "solving"
     * boletoId [string, default null]: filter for holmes that investigate a specific boleto by its ID. ex: "5656565656565656"
     * <p>
     * Return:
     * @return BoletoHolmes.Page object:
     * BoletoHolmes.Page.holmes: list of BoletoHolmes objects with updated attributes
     * BoletoHolmes.Page.cursor: cursor to retrieve the next page of BoletoHolmes objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged BoletoHolmess
     * <p>
     * Receive a list of up to 100 BoletoHolmes objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoHolmes.Page object:
     * BoletoHolmes.Page.holmes: list of BoletoHolmes objects with updated attributes
     * BoletoHolmes.Page.cursor: cursor to retrieve the next page of BoletoHolmes objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged BoletoHolmess
     * <p>
     * Receive a list of up to 100 BoletoHolmes objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return BoletoHolmes.Page object:
     * BoletoHolmes.Page.holmes: list of BoletoHolmes objects with updated attributes
     * BoletoHolmes.Page.cursor: cursor to retrieve the next page of BoletoHolmes objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged BoletoHolmess
     * <p>
     * Receive a list of up to 100 BoletoHolmes objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
     * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "solving"
     * boletoId [string, default null]: filter for holmes that investigate a specific boleto by its ID. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return BoletoHolmes.Page object:
     * BoletoHolmes.Page.holmes: list of BoletoHolmes objects with updated attributes
     * BoletoHolmes.Page.cursor: cursor to retrieve the next page of BoletoHolmes objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<BoletoHolmes> holmes = new ArrayList<>();
        for (SubResource sherlock: page.entities) {
            holmes.add((BoletoHolmes) sherlock);
        }
        return new Page(holmes, page.cursor);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "BoletoHolmesLog");

        public String created;
        public String type;
        public BoletoHolmes holmes;

        /**
         * BoletoHolmes.Log object
         * <p>
         * Every time a BoletoHolmes entity is modified, a corresponding BoletoHolmes.Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the BoletoHolmes.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param holmes [BoletoHolmes]: BoletoHolmes entity to which the log refers to.
         * @param type [string]: type of the BoletoHolmes event which triggered the log creation. ex: "solving" or "solved"
         * @param created [string]: creation datetime for the log. ex: datetime.datetime(2020, 3, 10, 10, 30, 0, 0)
         */
        public Log(String created, String type, BoletoHolmes holmes, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.holmes = holmes;
        }

        /**
         * Retrieve BoletoHolmes.Log's
         * <p>
         * Receive a generator of BoletoHolmes.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return BoletoHolmes.Log object with updated attributes
         * @throws Exception error in the request 
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve BoletoHolmes.Log's
         * <p>
         * Receive a generator of BoletoHolmes.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * @return BoletoHolmes.Log object with updated attributes
         * @throws Exception error in the request 
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve BoletoHolmes.Log's
         * <p>
         * Receive a generator of BoletoHolmes.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
         * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
         * types [list of strings, default null]: filter retrieved objects by event type. ex: ["solving", "solved"]
         * holmesIds [list of strings, default null]: list of BoletoHolmes ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of BoletoHolmes.Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve BoletoHolmes.Log's
         * <p>
         * Receive a generator of BoletoHolmes.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.user was set before function call
         * <p>
         * Return:
         * @return list of BoletoHolmes.Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve BoletoHolmes.Log's
         * <p>
         * Receive a generator of BoletoHolmes.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of BoletoHolmes.Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve BoletoHolmes.Log's
         * <p>
         * Receive a generator of BoletoHolmes.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
         * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
         * types [list of strings, default null]: filter retrieved objects by event type. ex: ["solving", "solved"]
         * holmesIds [list of strings, default null]: list of BoletoHolmes ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
         * <p>
         * Return:
         * @return list of BoletoHolmes.Log objects with updated attributes
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
         * Retrieve paged BoletoHolmes.Logs
         * <p>
         * Receive a list of up to 100 BoletoHolmes.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
         * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
         * types [list of strings, default null]: filter retrieved objects by event type. ex: ["solving", "solved"]
         * holmesIds [list of strings, default null]: list of BoletoHolmes ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return BoletoHolmes.Log.Page object:
         * BoletoHolmes.Log.Page.logs: list of BoletoHolmes.Log objects with updated attributes
         * BoletoHolmes.Log.Page.cursor: cursor to retrieve the next page of BoletoHolmes.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged BoletoHolmes.Logs
         * <p>
         * Receive a list of up to 100 BoletoHolmes.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return BoletoHolmes.Log.Page object:
         * BoletoHolmes.Log.Page.logs: list of BoletoHolmes.Log objects with updated attributes
         * BoletoHolmes.Log.Page.cursor: cursor to retrieve the next page of BoletoHolmes.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged BoletoHolmes.Logs
         * <p>
         * Receive a list of up to 100 BoletoHolmes.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return BoletoHolmes.Log.Page object:
         * BoletoHolmes.Log.Page.logs: list of BoletoHolmes.Log objects with updated attributes
         * BoletoHolmes.Log.Page.cursor: cursor to retrieve the next page of BoletoHolmes.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged BoletoHolmes.Logs
         * <p>
         * Receive a list of up to 100 BoletoHolmes.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [datetime.date or string, default null] date filter for objects created only after specified date. ex: datetime.date(2020, 3, 10)
         * before [datetime.date or string, default null] date filter for objects created only before specified date. ex: datetime.date(2020, 3, 10)
         * types [list of strings, default null]: filter retrieved objects by event type. ex: ["solving", "solved"]
         * holmesIds [list of strings, default null]: list of BoletoHolmes ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return BoletoHolmes.Log.Page object:
         * BoletoHolmes.Log.Page.logs: list of BoletoHolmes.Log objects with updated attributes
         * BoletoHolmes.Log.Page.cursor: cursor to retrieve the next page of BoletoHolmes.Log objects
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
