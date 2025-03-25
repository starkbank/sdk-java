package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;
import com.starkcore.utils.SubResource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class CorporateHolder extends Resource {
    /**
     * CorporateHolder object
     * <p>
     * The CorporateHolder describes a card holder that may group several cards.
     * <p>
     * When you initialize a CorporateHolder, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the created object.
     * <p>
     * Parameters:
     * name [string]: card holder name. ex: "Tony Stark"
     * centerId [string, default null]: target cost center ID. ex: "5656565656565656"
     * permissions [list of CorporateHolder.Permission or HashMap, default []]: list of Permission object representing access granted to an user for a particular cardholder.
     * rules [list of CorporateRule or HashMap, default []]: [EXPANDABLE] list of holder spending rules
     * tags [list of strings, default []]: list of strings for tagging. ex: ["travel", "food"]
     * id [string]: unique id returned when CorporateHolder is created. ex: "5656565656565656"
     * status [string]: current CorporateHolder status. ex: "active", "blocked" or "canceled"
     * updated [string]: latest update datetime for the CorporateHolder. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporateHolder. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(CorporateHolder.class, "CorporateHolder");

    public String name;
    public String centerId;
    public List<Permission> permissions;
    public List<CorporateRule> rules;
    public String[] tags;
    public String status;
    public String updated;
    public String created;

    /**
     * CorporateHolder object
     * <p>
     * The CorporateHolder describes a card holder that may group several cards.
     * <p>
     * Parameters:
     * @param name [string]: card holder name. ex: "Tony Stark"
     * @param centerId [string, default null]: target cost center ID. ex: "5656565656565656"
     * @param permissions [list of CorporateHolder.Permission or HashMap, default []]: list of Permission object representing access granted to an user for a particular cardholder.
     * @param rules [list of CorporateRule or HashMap, default []]: [EXPANDABLE] list of holder spending rules
     * @param tags [list of strings, default []]: list of strings for tagging. ex: ["travel", "food"]
     * @param id [string]: unique id returned when CorporateHolder is created. ex: "5656565656565656"
     * @param status [string]: current CorporateHolder status. ex: "active", "blocked" or "canceled"
     * @param updated [string]: latest update datetime for the CorporateHolder. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param created [string]: creation datetime for the CorporateHolder. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public CorporateHolder(String id, String name, String centerId, List<Permission> permissions, String status, List<CorporateRule> rules,
                         String[] tags, String updated, String created
    ) {
        super(id);
        this.name = name;
        this.centerId = centerId;
        this.permissions = permissions;
        this.rules = rules;
        this.tags = tags;
        this.status = status;
        this.updated = updated;
        this.created = created;
    }

    /**
     * CorporateHolder object
     * <p>
     * The CorporateHolder describes a card holder that may group several cards.
     * <p>
     * Parameters (required):
     * @param data map of properties for the creation of the CorporateHolder
     * name [string]: card holder name. ex: "Tony Stark"
     * <p>
     * Parameters (optional):
     * centerId [string, default null]: target cost center ID. ex: "5656565656565656"
     * permissions [list of CorporateHolder.Permission or HashMap, default []]: list of Permission object representing access granted to an user for a particular cardholder.
     * rules [list of CorporateRule or HashMap, default []]: [EXPANDABLE] list of holder spending rules
     * tags [list of strings, default []]: list of strings for tagging. ex: ["travel", "food"]
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when CorporateHolder is created. ex: "5656565656565656"
     * status [string]: current CorporateHolder status. ex: "active", "blocked" or "canceled"
     * updated [string]: latest update datetime for the CorporateHolder. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporateHolder. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public CorporateHolder(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.name = (String) dataCopy.remove("name");
        this.centerId = (String) dataCopy.remove("centerId");
        this.permissions = parsePermissions((List<Object>) dataCopy.remove("permissions"));
        this.rules = CorporateRule.parseRules((List<Object>) dataCopy.remove("rules"));
        this.tags = (String[]) dataCopy.remove("tags");
        this.status = null;
        this.updated = null;
        this.created = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public CorporateHolder(){
        super(null);
    }

    /**
     * Create CorporateHolders
     * <p>
     * Send a list of CorporateHolder objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param holders [list of CorporateHolder objects]: list of CorporateHolder objects to be created in the API
     * <p>
     * Return:
     * @return list of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<CorporateHolder> create(List<?> holders) throws Exception {
        return CorporateHolder.create(holders, null, null);
    }

    /**
     * Create CorporateHolders
     * <p>
     * Send a list of CorporateHolder objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param holders [list of CorporateHolder objects]: list of CorporateHolder objects to be created in the API
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * <p>
     * Return:
     * @return list of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<CorporateHolder> create(List<?> holders, Map<String, Object> params) throws Exception {
        return CorporateHolder.create(holders, params, null);
    }

    /**
     * Create CorporateHolders
     * <p>
     * Send a list of CorporateHolder objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param holders [list of CorporateHolder objects]: list of CorporateHolder objects to be created in the API
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return list of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<CorporateHolder> create(List<?> holders, User user) throws Exception {
        return CorporateHolder.create(holders, null, user);
    }

    /**
     * Create CorporateHolders
     * <p>
     * Send a list of CorporateHolder objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param holders [list of CorporateHolder objects]: list of CorporateHolder objects to be created in the API
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return list of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<CorporateHolder> create(List<?> holders, Map<String, Object> params, User user) throws Exception {
        List<CorporateHolder> holderList = new ArrayList<>();
        for (Object holder : holders){
            if (holder instanceof Map){
                holderList.add(new CorporateHolder((Map<String, Object>) holder));
                continue;
            }
            if (holder instanceof CorporateHolder){
                holderList.add((CorporateHolder) holder);
                continue;
            }
            throw new Exception("Unknown type \"" + holder.getClass() + "\", use CorporateHolder or HashMap");
        }
        return Rest.post(data, holderList, params, user);
    }

    /**
     * Retrieve CorporateHolders
     * <p>
     * Receive a generator of CorporateHolder objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["active", "blocked", "canceled"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateHolder> query(Map<String, Object> params, User user) throws Exception{
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve CorporateHolders
     * <p>
     * Receive a generator of CorporateHolder objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateHolder> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve CorporateHolders
     * <p>
     * Receive a generator of CorporateHolder objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["active", "blocked", "canceled"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * <p>
     * Return:
     * @return generator of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateHolder> query(Map<String, Object> params) throws Exception{
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve CorporateHolders
     * <p>
     * Receive a generator of CorporateHolder objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of CorporateHolder objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateHolder> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<CorporateHolder> holders;
        public String cursor;

        public Page(List<CorporateHolder> holders, String cursor) {
            this.holders = holders;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged CorporateHolders
     * <p>
     * Receive a list of up to 100 CorporateHolder objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your holders.
     * <p>
     * Return:
     * @return CorporateHolder.Page object:
     * CorporateHolder.Page.holders: list of CorporateHolder objects with updated attributes
     * CorporateHolder.Page.cursor: cursor to retrieve the next page of CorporateHolder objects
     * @throws Exception error in the request
     */
    public static CorporateHolder.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged CorporateHolders
     * <p>
     * Receive a list of up to 100 CorporateHolder objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your holders.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["active", "blocked", "canceled"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * <p>
     * Return:
     * @return CorporateHolder.Page object:
     * CorporateHolder.Page.holders: list of CorporateHolder objects with updated attributes
     * CorporateHolder.Page.cursor: cursor to retrieve the next page of CorporateHolder objects
     * @throws Exception error in the request
     */
    public static CorporateHolder.Page page(Map<String , Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged CorporateHolders
     * <p>
     * Receive a list of up to 100 CorporateHolder objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your holders.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateHolder.Page object:
     * CorporateHolder.Page.holders: list of CorporateHolder objects with updated attributes
     * CorporateHolder.Page.cursor: cursor to retrieve the next page of CorporateHolder objects
     * @throws Exception error in the request
     */
    public static CorporateHolder.Page page(User user) throws Exception {

        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged CorporateHolders
     * <p>
     * Receive a list of up to 100 CorporateHolder objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your holders.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["active", "blocked", "canceled"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateHolder.Page object:
     * CorporateHolder.Page.holders: list of CorporateHolder objects with updated attributes
     * CorporateHolder.Page.cursor: cursor to retrieve the next page of CorporateHolder objects
     * @throws Exception error in the request
     */
    public static CorporateHolder.Page page(Map<String , Object> params, User user) throws Exception {

        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<CorporateHolder> holders = new ArrayList<>();
        for (SubResource holder: page.entities) {
            holders.add((CorporateHolder) holder);
        }
        return new Page(holders, page.cursor);

    }

    /**
     * Retrieve a specific CorporateHolder
     * <p>
     * Receive a single CorporateHolder object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateHolder object with updated attributes
     * @throws Exception error in the holder
     */
    public static CorporateHolder get(String id, Map<String, Object> params, User user) throws Exception {
        return Rest.getId(data, id, params, user);
    }

    /**
     * Retrieve a specific CorporateHolder
     * <p>
     * Receive a single CorporateHolder object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules"]
     * <p>
     * Return:
     * @return CorporateHolder object with updated attributes
     * @throws Exception error in the holder
     */
    public static CorporateHolder get(String id, Map<String, Object> params) throws Exception {
        return CorporateHolder.get(id, params, null);
    }

    /**
     * Retrieve a specific CorporateHolder
     * <p>
     * Receive a single CorporateHolder object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateHolder object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateHolder get(String id, User user) throws Exception{
        return CorporateHolder.get(id, null, user);
    }

    /**
     * Retrieve a specific CorporateHolder
     * <p>
     * Receive a single CorporateHolder object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return CorporateHolder object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateHolder get(String id) throws Exception{
        return CorporateHolder.get(id, null, null);
    }

    /**
     * Update CorporateHolder entity
     * <p>
     * Update a CorporateHolder by passing id, if it hasn't been paid yet.
     * <p>
     * Parameters:
     * @param id [string]: CorporateHolder id. ex: "5656565656565656"
     * @param patchData map of parameters
     * centerId [string, default null]: target cost center ID. ex: "5656565656565656"
     * permissions [list of CorporateHolder.Permission object, default null]: list of Permission object representing access granted to an user for a particular cardholder.
     * status [string, default null]: You may block the CorporateHolder by passing 'blocked' in the status
     * name [string, default null]: card holder name. ex: "Jaime Lannister"
     * tags [list of strings, default null]: list of strings for tagging
     * rules [list of dictionaries, default null]: list of dictionaries with "amount": int, "currencyCode": string, "id": string, "interval": string, "name": string pairs
     * <p>
     * Return:
     * @return target CorporateHolder with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateHolder update(String id, Map<String, Object> patchData) throws Exception {
        return CorporateHolder.update(id, patchData, null);
    }

    /**
     * Update CorporateHolder entity
     * <p>
     * Update a CorporateHolder by passing its id, if it hasn't been paid yet.
     * <p>
     * Parameters:
     * @param id [string]: CorporateHolder id. ex: "5656565656565656"
     * @param patchData map of parameters
     * centerId [string, default null]: target cost center ID. ex: "5656565656565656"
     * permissions [list of CorporateHolder.Permission object, default null]: list of Permission object representing access granted to an user for a particular cardholder.
     * status [string, default null]: You may block the CorporateHolder by passing 'blocked' in the status
     * name [string, default null]: card holder name. ex: "Jaime Lannister"
     * tags [list of strings, default null]: list of strings for tagging
     * rules [list of dictionaries, default null]: list of dictionaries with "amount": int, "currencyCode": string, "id": string, "interval": string, "name": string pairs
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return target CorporateHolder with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateHolder update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }

    /**
     * Cancel a CorporateHolder entity
     * <p>
     * Cancel a CorporateHolder entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: CorporateHolder id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return canceled CorporateHolder object
     * @throws Exception error in the request
     */
    public static CorporateHolder cancel(String id) throws Exception {
        return CorporateHolder.cancel(id, null);
    }

    /**
     * Cancel a CorporateHolder entity
     * <p>
     * Cancel a CorporateHolder entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: CorporateHolder id. ex: "5656565656565656"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return canceled CorporateHolder object
     * @throws Exception error in the request
     */
    public static CorporateHolder cancel(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(CorporateHolder.Log.class, "CorporateHolderLog");

        public String created;
        public String type;
        public CorporateHolder holder;

        /**
         * CorporateHolder Log object
         * <p>
         * Every time a CorporateHolder entity is updated, a corresponding CorporateHolder.Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the CorporateHolder.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param holder [CorporateHolder]: CorporateHolder entity to which the log refers to.
         * @param type [string]: type of the CorporateHolder event which triggered the log creation. ex: "blocked", "canceled", "created", "unblocked", "updated"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, CorporateHolder holder, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.holder = holder;
        }

        public Log(){
            super(null);
        }

        /**
         * Retrieve a specific CorporateHolder Log
         * <p>
         * Receive a single CorporateHolder Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return CorporateHolder Log object with updated attributes
         * @throws Exception error in the holder
         */
        public static CorporateHolder.Log get(String id) throws Exception {
            return CorporateHolder.Log.get(id, null);
        }

        /**
         * Retrieve a specific CorporateHolder Log
         * <p>
         * Receive a single CorporateHolder Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporateHolder Log object with updated attributes
         * @throws Exception error in the holder
         */
        public static CorporateHolder.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve CorporateHolder Logs
         * <p>
         * Receive a generator of CorporateHolder.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "blocked"
         * holderIds [list of strings, default null]: list of CorporateHolder ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of CorporateHolder Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<CorporateHolder.Log> query(Map<String, Object> params) throws Exception {
            return CorporateHolder.Log.query(params, null);
        }

        /**
         * Retrieve CorporateHolder Logs
         * <p>
         * Receive a generator of CorporateHolder.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of CorporateHolder Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<CorporateHolder.Log> query(User user) throws Exception {
            return CorporateHolder.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve CorporateHolder Logs
         * <p>
         * Receive a generator of CorporateHolder.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of CorporateHolder Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<CorporateHolder.Log> query() throws Exception {
            return CorporateHolder.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve CorporateHolder Logs
         * <p>
         * Receive a generator of CorporateHolder.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "blocked"
         * holderIds [list of strings, default null]: list of CorporateHolder ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of CorporateHolder Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<CorporateHolder.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<CorporateHolder.Log> logs;
            public String cursor;

            public Page(List<CorporateHolder.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged CorporateHolder.Logs
         * <p>
         * Receive a list of up to 100 CorporateHolder.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your holders.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "blocked"
         * holderIds [list of strings, default null]: list of CorporateHolder ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return CorporateHolder.Log.Page object:
         * CorporateHolder.Log.Page.logs: list of CorporateHolder.Log objects with updated attributes
         * CorporateHolder.Log.Page.cursor: cursor to retrieve the next page of CorporateHolder.Log objects
         * @throws Exception error in the holder
         */
        public static CorporateHolder.Log.Page page(Map<String, Object> params) throws Exception {
            return CorporateHolder.Log.page(params, null);
        }

        /**
         * Retrieve paged CorporateHolder.Logs
         * <p>
         * Receive a list of up to 100 CorporateHolder.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your holders.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporateHolder.Log.Page object:
         * CorporateHolder.Log.Page.logs: list of CorporateHolder.Log objects with updated attributes
         * CorporateHolder.Log.Page.cursor: cursor to retrieve the next page of CorporateHolder.Log objects
         * @throws Exception error in the holder
         */
        public static CorporateHolder.Log.Page page(User user) throws Exception {
            return CorporateHolder.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged CorporateHolder.Logs
         * <p>
         * Receive a list of up to 100 CorporateHolder.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your holders.
         * <p>
         * Return:
         * @return CorporateHolder.Log.Page object:
         * CorporateHolder.Log.Page.logs: list of CorporateHolder.Log objects with updated attributes
         * CorporateHolder.Log.Page.cursor: cursor to retrieve the next page of CorporateHolder.Log objects
         * @throws Exception error in the holder
         */
        public static CorporateHolder.Log.Page page() throws Exception {
            return CorporateHolder.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged CorporateHolder.Logs
         * <p>
         * Receive a list of up to 100 CorporateHolder.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your holders.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "blocked"
         * holderIds [list of strings, default null]: list of CorporateHolder ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporateHolder.Log.Page object:
         * CorporateHolder.Log.Page.logs: list of CorporateHolder.Log objects with updated attributes
         * CorporateHolder.Log.Page.cursor: cursor to retrieve the next page of CorporateHolder.Log objects
         * @throws Exception error in the holder
         */
        public static CorporateHolder.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<CorporateHolder.Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((CorporateHolder.Log) log);
            }
            return new Page(logs, page.cursor);
        }
    }

    private List<CorporateHolder.Permission> parsePermissions(List<Object> permissions) throws Exception {
        if (permissions == null)
            return null;

        List<CorporateHolder.Permission> parsed = new ArrayList<>();
        if (permissions.size() == 0 || permissions.get(0) instanceof CorporateHolder.Permission) {
            for (Object permission : permissions) {
                parsed.add((CorporateHolder.Permission) permission);
            }
            return parsed;
        }

        for (Object permission : permissions) {
            CorporateHolder.Permission permissionObject = new CorporateHolder.Permission((Map<String, Object>) permission);
            parsed.add(permissionObject);
        }
        return parsed;
    }

    public final static class Permission extends SubResource{
        public String ownerId;
        public String ownerType;
        public String ownerEmail;
        public String ownerName;
        public String ownerPictureUrl;
        public String ownerStatus;
        public String created;


        /**
         * CorporateHolder.Permission object
         * <p>
         * Permission object represents access granted to an user for a particular cardholder
         * <p>
         * Parameters:
         * @param ownerId [string, default null]: owner unique id. ex: "5656565656565656"
         * @param ownerType [string, default null]: owner type. ex: "project"
         * @param ownerEmail [string]: email address of the owner. ex: "tony@starkbank.com
         * @param ownerName [string]: name of the owner. ex: "Tony Stark"
         * @param ownerPictureUrl [string]: Profile picture Url of the owner. ex: "https://storage.googleapis.com/api-ms-workspace-sbx.appspot.com/pictures/member/6227829385592832?20230404164942"
         * @param ownerStatus [string]: current owner status. ex: "active", "blocked", "canceled"
         * @param created [string]: creation datetime for the Permission. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Permission(String ownerId, String ownerType, String ownerEmail, String ownerName,
                          String ownerPictureUrl, String ownerStatus, String created){
            this.ownerId = ownerId;
            this.ownerType = ownerType;
            this.ownerEmail = ownerEmail;
            this.ownerName = ownerName;
            this.ownerPictureUrl = ownerPictureUrl;
            this.ownerStatus = ownerStatus;
            this.created = created;
        }

        /**
         * CorporateHolder.Permission object
         * <p>
         * The CorporateHolder.Permission object modifies the behavior of CorporateHolder objects when passed as an argument upon their creation.
         * <p>
         * Parameters:
         * @param data map of properties for the creation of the CorporateHolder.Permission
         * ownerId [string, default null]: owner unique id. ex: "5656565656565656"
         * ownerType [string, default null]: owner type. ex: "project"
         * ownerEmail [string]: email address of the owner. ex: "tony@starkbank.com
         * ownerName [string]: name of the owner. ex: "Tony Stark"
         * ownerPictureUrl [string]: Profile picture Url of the owner. ex: ""
         * ownerStatus [string]: current owner status. ex: "active", "blocked", "canceled"
         * created [string]: creation datetime for the Permission. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Permission(Map<String, Object> data) throws Exception {
            HashMap<String, Object> dataCopy = new HashMap<>(data);

            this.ownerId = (String) dataCopy.remove("ownerId");
            this.ownerType = (String) dataCopy.remove("ownerType");
            this.ownerEmail = null;
            this.ownerName = null;
            this.ownerPictureUrl = null;
            this.ownerStatus = null;
            this.created = null;

            if (!dataCopy.isEmpty()) {
                throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
            }
        }
    }
}
