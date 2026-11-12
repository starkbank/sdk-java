package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MerchantCard extends Resource {

    /**
     * MerchantCard object
     * <p>
     * Stores information about cards used in approved purchases; these cards can be reused for new purchases without creating a new MerchantSession.
     * <p>
     * Attributes (return-only):
     * ending [string]: last 4 digits of the card number.
     * expiration [string]: card expiration date. ex: "2025-06"
     * holderName [string]: name of the card holder.
     * fundingType [string]: funding type. Options: "credit", "debit"
     * network [string]: card network.
     * status [string]: current card status. Options: "active", "expired", "canceled", "blocked"
     * tags [list of strings]: tags associated with the card.
     * created [string]: creation datetime.
     * updated [string]: latest update datetime.
     */

    static ClassData data = new ClassData(MerchantCard.class, "MerchantCard");

    public String ending;
    public String fundingType;
    public String holderName;
    public String network;
    public String status;
    public List<String> tags;
    public String expiration;
    public String created;
    public String updated;

    public MerchantCard(String id, String ending, String fundingType, String holderName, String network, String status, List<String> tags, String expiration, String created, String updated) {
        super(id);
        this.ending = ending;
        this.fundingType = fundingType;
        this.holderName = holderName;
        this.network = network;
        this.status = status;
        this.tags = tags;
        this.expiration = expiration;
        this.created = created;
        this.updated = updated;
    }

    /**
     * Retrieve MerchantCards
     * <p>
     * Receive a generator of MerchantCard objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "active", "expired", "canceled", "blocked"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of MerchantCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCard> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve MerchantCards
     * <p>
     * Receive a generator of MerchantCard objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "active", "expired", "canceled", "blocked"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * <p>
     * Return:
     * @return generator of MerchantCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCard> query(Map<String, Object> params) throws Exception {
        return MerchantCard.query(params, null);
    }

    /**
     * Retrieve MerchantCards
     * <p>
     * Receive a generator of MerchantCard objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of MerchantCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCard> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    /**
     * Retrieve a specific MerchantCard
     * <p>
     * Receive a single MerchantCard object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantCard object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantCard get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific MerchantCard
     * <p>
     * Receive a single MerchantCard object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * <p>
     * Return:
     * @return MerchantCard object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantCard get(String id) throws Exception {
        return MerchantCard.get(id, null);
    }

    /**
     * Retrieve paged MerchantCards
     * <p>
     * Receive a list of up to 100 MerchantCard objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "active", "expired", "canceled", "blocked"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * <p>
     * Return:
     * @return MerchantCard.Page object:
     * MerchantCard.Page.merchantCards: list of MerchantCard objects with updated attributes
     * MerchantCard.Page.cursor: cursor to retrieve the next page of MerchantCard objects
     * @throws Exception error in the request
     */
    public static MerchantCard.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged MerchantCards
     * <p>
     * Receive a list of up to 100 MerchantCard objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantCard.Page object:
     * MerchantCard.Page.merchantCards: list of MerchantCard objects with updated attributes
     * MerchantCard.Page.cursor: cursor to retrieve the next page of MerchantCard objects
     * @throws Exception error in the request
     */
    public static MerchantCard.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged MerchantCards
     * <p>
     * Receive a list of up to 100 MerchantCard objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return MerchantCard.Page object:
     * MerchantCard.Page.merchantCards: list of MerchantCard objects with updated attributes
     * MerchantCard.Page.cursor: cursor to retrieve the next page of MerchantCard objects
     * @throws Exception error in the request
     */
    public static MerchantCard.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }


    /**
     * Retrieve paged MerchantCards
     * <p>
     * Receive a list of up to 100 MerchantCard objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "active", "expired", "canceled", "blocked"
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantCard.Page object:
     * MerchantCard.Page.merchantCards: list of MerchantCard objects with updated attributes
     * MerchantCard.Page.cursor: cursor to retrieve the next page of MerchantCard objects
     * @throws Exception error in the request
     */
    public static MerchantCard.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantCard> MerchantCards = new ArrayList<>();
        for (com.starkcore.utils.SubResource MerchantCard: page.entities) {
            MerchantCards.add((MerchantCard) MerchantCard);
        }
        return new MerchantCard.Page(MerchantCards, page.cursor);
    }

    public final static class Page {
        public List<MerchantCard> merchantCards;
        public String cursor;

        public Page(List<MerchantCard> merchantCards, String cursor) {
            this.merchantCards = merchantCards;
            this.cursor = cursor;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantCard.Log.class, "MerchantCardLog");

        public String created;
        public String type;
        public String[] errors;
        public MerchantCard card;

        /**
         * MerchantCard Log object
         * <p>
         * Every time a MerchantCard entity is updated, a corresponding MerchantCard Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the MerchantCard.
         * <p>
         * Attributes:
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * card [MerchantCard]: MerchantCard entity to which the log refers to.
         * errors [list of strings]: list of errors linked to this MerchantCard event
         * type [string]: type of the MerchantCard event which triggered the log creation. ex: "created" or "blocked"
         * created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000"
         */
        public Log(String created, String type, String[] errors, MerchantCard card, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.card = card;
        }


        /**
         * Retrieve a specific MerchantCard Log
         * <p>
         * Receive a single MerchantCard Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return MerchantCard Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantCard.Log get(String id) throws Exception {
            return MerchantCard.Log.get(id, null);
        }

        /**
         * Retrieve a specific MerchantCard Log
         * <p>
         * Receive a single MerchantCard Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantCard Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantCard.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve MerchantCard Logs
         * <p>
         * Receive a generator of MerchantCard Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "blocked"
         * cardIds [list of strings, default null]: list of MerchantCard ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of MerchantCard Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantCard.Log> query(Map<String, Object> params) throws Exception {
            return MerchantCard.Log.query(params, null);
        }

        /**
         * Retrieve MerchantCard Logs
         * <p>
         * Receive a generator of MerchantCard Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantCard Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantCard.Log> query(User user) throws Exception {
            return MerchantCard.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve MerchantCard Logs
         * <p>
         * Receive a generator of MerchantCard Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of MerchantCard Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantCard.Log> query() throws Exception {
            return MerchantCard.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve MerchantCard Logs
         * <p>
         * Receive a generator of MerchantCard Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "blocked"
         * cardIds [list of strings, default null]: list of MerchantCard ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantCard Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantCard.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantCard.Log> logs;
            public String cursor;

            public Page(List<MerchantCard.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged MerchantCard Logs
         * <p>
         * Receive a list of up to 100 MerchantCard Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "blocked"
         * cardIds [list of strings, default null]: list of MerchantCard ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return MerchantCard.Log.Page object:
         * MerchantCard.Log.Page.logs: list of MerchantCard Log objects with updated attributes
         * MerchantCard.Log.Page.cursor: cursor to retrieve the next page of MerchantCard Log objects
         * @throws Exception error in the request
         */
        public static MerchantCard.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantCard.Log.page(params, null);
        }

        /**
         * Retrieve paged MerchantCard Logs
         * <p>
         * Receive a list of up to 100 MerchantCard Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantCard.Log.Page object:
         * MerchantCard.Log.Page.logs: list of MerchantCard Log objects with updated attributes
         * MerchantCard.Log.Page.cursor: cursor to retrieve the next page of MerchantCard Log objects
         * @throws Exception error in the request
         */
        public static MerchantCard.Log.Page page(User user) throws Exception {
            return MerchantCard.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged MerchantCard Logs
         * <p>
         * Receive a list of up to 100 MerchantCard Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return MerchantCard.Log.Page object:
         * MerchantCard.Log.Page.logs: list of MerchantCard Log objects with updated attributes
         * MerchantCard.Log.Page.cursor: cursor to retrieve the next page of MerchantCard Log objects
         * @throws Exception error in the request
         */
        public static MerchantCard.Log.Page page() throws Exception {
            return MerchantCard.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged MerchantCard Logs
         * <p>
         * Receive a list of up to 100 MerchantCard Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "blocked"
         * cardIds [list of strings, default null]: list of MerchantCard ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantCard.Log.Page object:
         * MerchantCard.Log.Page.logs: list of MerchantCard Log objects with updated attributes
         * MerchantCard.Log.Page.cursor: cursor to retrieve the next page of MerchantCard Log objects
         * @throws Exception error in the request
         */
        public static MerchantCard.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantCard.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantCard.Log) log);
            }
            return new MerchantCard.Log.Page(logs, page.cursor);
        }

    }
}
