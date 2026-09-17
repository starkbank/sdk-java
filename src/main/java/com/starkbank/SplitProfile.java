package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;
import com.starkcore.utils.SubResource;
import com.starkbank.error.ErrorElement;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class SplitProfile extends Resource {
    /**
     * SplitProfile object
     * <p>
     * When you create a Split, the entity SplitProfile will be automatically created.
     * If you haven't created a Split yet, you can use the "put" function to create your SplitProfile.
     * <p>
     * Parameters (optional):
     * interval [string, default "week"]: frequency of transfer. ex: "day", "week" or "month"
     * delay [integer]: how long the amount will stay at the workspace in milliseconds. ex: 604800
     * tags [list of strings, default []]: list of strings for reference when searching for profiles. ex: ["tony", "stark"]
     * id [string]: unique id returned when the SplitProfile is created. ex: "5656565656565656"
     * status [string]: current SplitProfile status. ex: "created"
     * created [string]: creation datetime for the SplitProfile. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: update datetime for the SplitProfile. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(SplitProfile.class, "SplitProfile");

    public String interval;
    public Integer delay;
    public String[] tags;
    public String status;
    public String created;
    public String updated;

    /**
     * SplitProfile object
     * <p>
     * When you create a Split, the entity SplitProfile will be automatically created.
     * If you haven't created a Split yet, you can use the "put" function to create your SplitProfile.
     * <p>
     * Parameters:
     * @param interval [string, default "week"]: frequency of transfer. ex: "day", "week" or "month"
     * @param delay [integer]: how long the amount will stay at the workspace in milliseconds. ex: 604800
     * @param tags [list of strings, default []]: list of strings for reference when searching for profiles. ex: ["tony", "stark"]
     * @param id [string]: unique id returned when the SplitProfile is created. ex: "5656565656565656"
     * @param status [string]: current SplitProfile status. ex: "created"
     * @param created [string]: creation datetime for the SplitProfile. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string]: update datetime for the SplitProfile. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public SplitProfile(String interval, Integer delay, String[] tags, String id, String status, String created, String updated) {
        super(id);
        this.interval = interval;
        this.delay = delay;
        this.tags = tags;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    /**
     * SplitProfile object
     * <p>
     * When you create a Split, the entity SplitProfile will be automatically created.
     * If you haven't created a Split yet, you can use the "put" function to create your SplitProfile.
     * <p>
     * @param data map of parameters for the creation of the SplitProfile
     * Parameters (optional):
     * interval [string, default "week"]: frequency of transfer. ex: "day", "week" or "month"
     * delay [integer]: how long the amount will stay at the workspace in milliseconds. ex: 604800
     * tags [list of strings, default []]: list of strings for reference when searching for profiles. ex: ["tony", "stark"]
     * <p>
     * Attributes (return-only):
     * status [string]: current SplitProfile status. ex: "created"
     * created [string]: creation datetime for the SplitProfile. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: update datetime for the SplitProfile. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public SplitProfile(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.interval = (String) dataCopy.remove("interval");
        Number delay = (Number) dataCopy.remove("delay");
        this.delay = delay == null ? null : delay.intValue();
        this.tags = (String[]) dataCopy.remove("tags");
        this.status = null;
        this.created = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public SplitProfile() {
        super(null);
    }

    /**
     * Create or update a SplitProfile
     * <p>
     * Send a list containing a single SplitProfile object for creation in the Stark Bank API.
     * If a SplitProfile already exists for the workspace, this updates its rules instead of creating a new one.
     * <p>
     * Parameters:
     * @param profiles [list of SplitProfile objects or HashMaps]: list of SplitProfile objects to be created or updated in the API
     * <p>
     * Return:
     * @return list of SplitProfile objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<SplitProfile> put(List<?> profiles) throws Exception {
        return SplitProfile.put(profiles, null);
    }

    /**
     * Create or update a SplitProfile
     * <p>
     * Send a list containing a single SplitProfile object for creation in the Stark Bank API.
     * If a SplitProfile already exists for the workspace, this updates its rules instead of creating a new one.
     * <p>
     * Parameters:
     * @param profiles [list of SplitProfile objects or HashMaps]: list of SplitProfile objects to be created or updated in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of SplitProfile objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<SplitProfile> put(List<?> profiles, User user) throws Exception {
        List<SplitProfile> profileList = new ArrayList<>();
        for (Object profile : profiles) {
            if (profile instanceof Map) {
                profileList.add(new SplitProfile((Map<String, Object>) profile));
                continue;
            }
            if (profile instanceof SplitProfile) {
                profileList.add((SplitProfile) profile);
                continue;
            }
            throw new Exception("Unknown type \"" + profile.getClass() + "\", use SplitProfile or HashMap");
        }
        return Rest.put(data, profileList, user);
    }

    /**
     * Retrieve a specific SplitProfile
     * <p>
     * Receive a single SplitProfile object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: unique id returned when SplitProfile is created. ex: "5656565656565656"
     * <p>
     * Return:
     * @return SplitProfile object with updated attributes
     * @throws Exception error in the request
     */
    public static SplitProfile get(String id) throws Exception {
        return SplitProfile.get(id, null);
    }

    /**
     * Retrieve a specific SplitProfile
     * <p>
     * Receive a single SplitProfile object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return SplitProfile object with updated attributes
     * @throws Exception error in the request
     */
    public static SplitProfile get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve SplitProfiles
     * <p>
     * Receive a generator of SplitProfile objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if none. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of SplitProfile objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitProfile> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve SplitProfiles
     * <p>
     * Receive a generator of SplitProfile objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if none. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of SplitProfile objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitProfile> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve SplitProfiles
     * <p>
     * Receive a generator of SplitProfile objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of SplitProfile objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitProfile> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve SplitProfiles
     * <p>
     * Receive a generator of SplitProfile objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of SplitProfile objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitProfile> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<SplitProfile> splitProfiles;
        public String cursor;

        public Page(List<SplitProfile> splitProfiles, String cursor) {
            this.splitProfiles = splitProfiles;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged SplitProfiles
     * <p>
     * Receive a list of up to 100 SplitProfile objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return SplitProfile.Page object:
     * SplitProfile.Page.splitProfiles: list of SplitProfile objects with updated attributes
     * SplitProfile.Page.cursor: cursor to retrieve the next page of SplitProfile objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<SplitProfile> splitProfiles = new ArrayList<>();
        for (SubResource splitProfile : page.entities) {
            splitProfiles.add((SplitProfile) splitProfile);
        }
        return new Page(splitProfiles, page.cursor);
    }

    /**
     * Retrieve paged SplitProfiles
     * <p>
     * Receive a list of up to 100 SplitProfile objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return SplitProfile.Page object:
     * SplitProfile.Page.splitProfiles: list of SplitProfile objects with updated attributes
     * SplitProfile.Page.cursor: cursor to retrieve the next page of SplitProfile objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged SplitProfiles
     * <p>
     * Receive a list of up to 100 SplitProfile objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return SplitProfile.Page object:
     * SplitProfile.Page.splitProfiles: list of SplitProfile objects with updated attributes
     * SplitProfile.Page.cursor: cursor to retrieve the next page of SplitProfile objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged SplitProfiles
     * <p>
     * Receive a list of up to 100 SplitProfile objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Return:
     * @return SplitProfile.Page object:
     * SplitProfile.Page.splitProfiles: list of SplitProfile objects with updated attributes
     * SplitProfile.Page.cursor: cursor to retrieve the next page of SplitProfile objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(SplitProfile.Log.class, "SplitProfileLog");

        public SplitProfile profile;
        public List<ErrorElement> errors;
        public String type;
        public String created;

        /**
         * SplitProfile Log object
         * <p>
         * Every time a SplitProfile entity is updated, a corresponding SplitProfile.Log
         * is generated for the entity. This log is never generated by the user,
         * but it can be retrieved to check additional information on the SplitProfile.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param profile [SplitProfile]: SplitProfile entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this SplitProfile event
         * @param type [string]: type of the SplitProfile event which triggered the log creation. ex: "created" or "updated"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, List<ErrorElement> errors, SplitProfile profile, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.profile = profile;
        }

        public Log() {
            super(null);
        }

        /**
         * Retrieve a specific SplitProfile Log
         * <p>
         * Receive a single SplitProfile Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return SplitProfile Log object with updated attributes
         * @throws Exception error in the request
         */
        public static SplitProfile.Log get(String id) throws Exception {
            return SplitProfile.Log.get(id, null);
        }

        /**
         * Retrieve a specific SplitProfile Log
         * <p>
         * Receive a single SplitProfile Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return SplitProfile Log object with updated attributes
         * @throws Exception error in the request
         */
        public static SplitProfile.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve SplitProfile Logs
         * <p>
         * Receive a generator of SplitProfile.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "updated"
         * profileIds [list of strings, default null]: list of SplitProfile ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of SplitProfile Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<SplitProfile.Log> query(Map<String, Object> params) throws Exception {
            return SplitProfile.Log.query(params, null);
        }

        /**
         * Retrieve SplitProfile Logs
         * <p>
         * Receive a generator of SplitProfile.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of SplitProfile Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<SplitProfile.Log> query(User user) throws Exception {
            return SplitProfile.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve SplitProfile Logs
         * <p>
         * Receive a generator of SplitProfile.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of SplitProfile Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<SplitProfile.Log> query() throws Exception {
            return SplitProfile.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve SplitProfile Logs
         * <p>
         * Receive a generator of SplitProfile.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "updated"
         * profileIds [list of strings, default null]: list of SplitProfile ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of SplitProfile Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<SplitProfile.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<SplitProfile.Log> logs;
            public String cursor;

            public Page(List<SplitProfile.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged SplitProfile.Logs
         * <p>
         * Receive a list of up to 100 SplitProfile.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your logs.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "updated"
         * profileIds [list of strings, default null]: list of SplitProfile ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return SplitProfile.Log.Page object:
         * SplitProfile.Log.Page.logs: list of SplitProfile.Log objects with updated attributes
         * SplitProfile.Log.Page.cursor: cursor to retrieve the next page of SplitProfile.Log objects
         * @throws Exception error in the request
         */
        public static SplitProfile.Log.Page page(Map<String, Object> params) throws Exception {
            return SplitProfile.Log.page(params, null);
        }

        /**
         * Retrieve paged SplitProfile.Logs
         * <p>
         * Receive a list of up to 100 SplitProfile.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your logs.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return SplitProfile.Log.Page object:
         * SplitProfile.Log.Page.logs: list of SplitProfile.Log objects with updated attributes
         * SplitProfile.Log.Page.cursor: cursor to retrieve the next page of SplitProfile.Log objects
         * @throws Exception error in the request
         */
        public static SplitProfile.Log.Page page(User user) throws Exception {
            return SplitProfile.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged SplitProfile.Logs
         * <p>
         * Receive a list of up to 100 SplitProfile.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your logs.
         * <p>
         * Return:
         * @return SplitProfile.Log.Page object:
         * SplitProfile.Log.Page.logs: list of SplitProfile.Log objects with updated attributes
         * SplitProfile.Log.Page.cursor: cursor to retrieve the next page of SplitProfile.Log objects
         * @throws Exception error in the request
         */
        public static SplitProfile.Log.Page page() throws Exception {
            return SplitProfile.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged SplitProfile.Logs
         * <p>
         * Receive a list of up to 100 SplitProfile.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your logs.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "updated"
         * profileIds [list of strings, default null]: list of SplitProfile ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return SplitProfile.Log.Page object:
         * SplitProfile.Log.Page.logs: list of SplitProfile.Log objects with updated attributes
         * SplitProfile.Log.Page.cursor: cursor to retrieve the next page of SplitProfile.Log objects
         * @throws Exception error in the request
         */
        public static SplitProfile.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<SplitProfile.Log> logs = new ArrayList<>();
            for (SubResource log : page.entities) {
                logs.add((SplitProfile.Log) log);
            }
            return new SplitProfile.Log.Page(logs, page.cursor);
        }
    }
}
