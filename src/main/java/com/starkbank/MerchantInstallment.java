package com.starkbank;

import com.starkbank.error.ErrorElement;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantInstallment extends Resource {

    /**
     * MerchantInstallment object
     * <p>
     * Created for every installment in a purchase; tracks its own due date and settlement lifecycle.
     * <p>
     * Attributes (return-only):
     * amount [long]: installment amount in cents.
     * fee [integer]: fee charged in cents.
     * fundingType [string]: Options: "credit", "debit"
     * network [string]: card network.
     * purchaseId [string]: id of the MerchantPurchase linked to the installment.
     * status [string]: Options: "created", "paid", "canceled", "voided"
     * transactionIds [list of strings]: ledger transaction ids linked to this installment.
     * tags [list of strings]
     * due [string]: expected settlement date.
     * created [string]
     * updated [string]
     */

    static ClassData data = new ClassData(MerchantInstallment.class, "MerchantInstallment");

    public long amount;
    public String created;
    public String due;
    public int fee;
    public String fundingType;
    public String network;
    public String purchaseId;
    public String status;
    public String[] tags;
    public List<String> transactionIds;
    public String updated;

    public MerchantInstallment(String id, long amount, String created, String due, int fee, String fundingType, String network, String purchaseId, String status, String[] tags, List<String> transactionIds, String updated) {
        super(id);
        this.amount = amount;
        this.created = created;
        this.due = due;
        this.fee = fee;
        this.fundingType = fundingType;
        this.network = network;
        this.purchaseId = purchaseId;
        this.status = status;
        this.tags = tags;
        this.transactionIds = transactionIds;
        this.updated = updated;
    }

    /**
     * Retrieve MerchantInstallments
     * <p>
     * Receive a generator of MerchantInstallment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "paid", "canceled", "voided"
     * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter installments.
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of MerchantInstallment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantInstallment> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve MerchantInstallments
     * <p>
     * Receive a generator of MerchantInstallment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "paid", "canceled", "voided"
     * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter installments.
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * <p>
     * Return:
     * @return generator of MerchantInstallment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantInstallment> query(Map<String, Object> params) throws Exception {
        return MerchantInstallment.query(params, null);
    }

    /**
     * Retrieve MerchantInstallments
     * <p>
     * Receive a generator of MerchantInstallment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of MerchantInstallment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantInstallment> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    /**
     * Retrieve a specific MerchantInstallment
     * <p>
     * Receive a single MerchantInstallment object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantInstallment object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantInstallment get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific MerchantInstallment
     * <p>
     * Receive a single MerchantInstallment object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id.
     * <p>
     * Return:
     * @return MerchantInstallment object with updated attributes
     * @throws Exception error in the request
     */
    public static MerchantInstallment get(String id) throws Exception {
        return MerchantInstallment.get(id, null);
    }

    /**
     * Retrieve paged MerchantInstallments
     * <p>
     * Receive a list of up to 100 MerchantInstallment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "paid", "canceled", "voided"
     * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter installments.
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * <p>
     * Return:
     * @return MerchantInstallment.Page object:
     * MerchantInstallment.Page.merchantInstallments: list of MerchantInstallment objects with updated attributes
     * MerchantInstallment.Page.cursor: cursor to retrieve the next page of MerchantInstallment objects
     * @throws Exception error in the request
     */
    public static MerchantInstallment.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged MerchantInstallments
     * <p>
     * Receive a list of up to 100 MerchantInstallment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantInstallment.Page object:
     * MerchantInstallment.Page.merchantInstallments: list of MerchantInstallment objects with updated attributes
     * MerchantInstallment.Page.cursor: cursor to retrieve the next page of MerchantInstallment objects
     * @throws Exception error in the request
     */
    public static MerchantInstallment.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged MerchantInstallments
     * <p>
     * Receive a list of up to 100 MerchantInstallment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return MerchantInstallment.Page object:
     * MerchantInstallment.Page.merchantInstallments: list of MerchantInstallment objects with updated attributes
     * MerchantInstallment.Page.cursor: cursor to retrieve the next page of MerchantInstallment objects
     * @throws Exception error in the request
     */
    public static MerchantInstallment.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }


    /**
     * Retrieve paged MerchantInstallments
     * <p>
     * Receive a list of up to 100 MerchantInstallment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null.
     * after [string, default null]: date filter for objects created only after specified date.
     * before [string, default null]: date filter for objects created only before specified date.
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "paid", "canceled", "voided"
     * purchaseIds [list of strings, default null]: list of MerchantPurchase ids to filter installments.
     * tags [list of strings, default null]: tags to filter retrieved objects.
     * ids [list of strings, default null]: list of ids to filter retrieved objects.
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return MerchantInstallment.Page object:
     * MerchantInstallment.Page.merchantInstallments: list of MerchantInstallment objects with updated attributes
     * MerchantInstallment.Page.cursor: cursor to retrieve the next page of MerchantInstallment objects
     * @throws Exception error in the request
     */
    public static MerchantInstallment.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantInstallment> MerchantInstallments = new ArrayList<>();
        for (com.starkcore.utils.SubResource MerchantInstallment: page.entities) {
            MerchantInstallments.add((MerchantInstallment) MerchantInstallment);
        }
        return new MerchantInstallment.Page(MerchantInstallments, page.cursor);
    }

    public final static class Page {
        public List<MerchantInstallment> merchantInstallments;
        public String cursor;

        public Page(List<MerchantInstallment> MerchantInstallments, String cursor) {
            this.merchantInstallments = MerchantInstallments;
            this.cursor = cursor;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantInstallment.Log.class, "MerchantInstallmentLog");

        public String created;
        public String type;
        public List<ErrorElement> errors;
        public MerchantInstallment installment;

        /**
         * MerchantInstallment Log object
         * <p>
         * Every time a MerchantInstallment entity is updated, a corresponding MerchantInstallment Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the MerchantInstallment.
         * <p>
         * Attributes:
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * installment [MerchantInstallment]: MerchantInstallment entity to which the log refers to.
         * errors [list of ErrorElement]: list of errors linked to this MerchantInstallment event
         * type [string]: type of the MerchantInstallment event which triggered the log creation. ex: "created" or "paid"
         * created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000"
         */
        public Log(String created, String type, List<ErrorElement> errors, MerchantInstallment merchantInstallment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.installment = merchantInstallment;
        }


        /**
         * Retrieve a specific MerchantInstallment Log
         * <p>
         * Receive a single MerchantInstallment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return MerchantInstallment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantInstallment.Log get(String id) throws Exception {
            return MerchantInstallment.Log.get(id, null);
        }

        /**
         * Retrieve a specific MerchantInstallment Log
         * <p>
         * Receive a single MerchantInstallment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantInstallment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static MerchantInstallment.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve MerchantInstallment Logs
         * <p>
         * Receive a generator of MerchantInstallment Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "paid"
         * installmentIds [list of strings, default null]: list of MerchantInstallment ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of MerchantInstallment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantInstallment.Log> query(Map<String, Object> params) throws Exception {
            return MerchantInstallment.Log.query(params, null);
        }

        /**
         * Retrieve MerchantInstallment Logs
         * <p>
         * Receive a generator of MerchantInstallment Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantInstallment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantInstallment.Log> query(User user) throws Exception {
            return MerchantInstallment.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve MerchantInstallment Logs
         * <p>
         * Receive a generator of MerchantInstallment Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of MerchantInstallment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantInstallment.Log> query() throws Exception {
            return MerchantInstallment.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve MerchantInstallment Logs
         * <p>
         * Receive a generator of MerchantInstallment Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "paid"
         * installmentIds [list of strings, default null]: list of MerchantInstallment ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return generator of MerchantInstallment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<MerchantInstallment.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantInstallment.Log> logs;
            public String cursor;

            public Page(List<MerchantInstallment.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged MerchantInstallment Logs
         * <p>
         * Receive a list of up to 100 MerchantInstallment Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "paid"
         * installmentIds [list of strings, default null]: list of MerchantInstallment ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return MerchantInstallment.Log.Page object:
         * MerchantInstallment.Log.Page.logs: list of MerchantInstallment Log objects with updated attributes
         * MerchantInstallment.Log.Page.cursor: cursor to retrieve the next page of MerchantInstallment Log objects
         * @throws Exception error in the request
         */
        public static MerchantInstallment.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantInstallment.Log.page(params, null);
        }

        /**
         * Retrieve paged MerchantInstallment Logs
         * <p>
         * Receive a list of up to 100 MerchantInstallment Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantInstallment.Log.Page object:
         * MerchantInstallment.Log.Page.logs: list of MerchantInstallment Log objects with updated attributes
         * MerchantInstallment.Log.Page.cursor: cursor to retrieve the next page of MerchantInstallment Log objects
         * @throws Exception error in the request
         */
        public static MerchantInstallment.Log.Page page(User user) throws Exception {
            return MerchantInstallment.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged MerchantInstallment Logs
         * <p>
         * Receive a list of up to 100 MerchantInstallment Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return MerchantInstallment.Log.Page object:
         * MerchantInstallment.Log.Page.logs: list of MerchantInstallment Log objects with updated attributes
         * MerchantInstallment.Log.Page.cursor: cursor to retrieve the next page of MerchantInstallment Log objects
         * @throws Exception error in the request
         */
        public static MerchantInstallment.Log.Page page() throws Exception {
            return MerchantInstallment.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged MerchantInstallment Logs
         * <p>
         * Receive a list of up to 100 MerchantInstallment Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "paid"
         * installmentIds [list of strings, default null]: list of MerchantInstallment ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return MerchantInstallment.Log.Page object:
         * MerchantInstallment.Log.Page.logs: list of MerchantInstallment Log objects with updated attributes
         * MerchantInstallment.Log.Page.cursor: cursor to retrieve the next page of MerchantInstallment Log objects
         * @throws Exception error in the request
         */
        public static MerchantInstallment.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantInstallment.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantInstallment.Log) log);
            }
            return new MerchantInstallment.Log.Page(logs, page.cursor);
        }

    }
}
