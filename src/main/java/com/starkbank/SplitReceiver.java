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


public class SplitReceiver extends Resource {
    /**
     * SplitReceiver object
     * <p>
     * When you initialize a SplitReceiver, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * name [string]: receiver full name. ex: "Anthony Edward Stark"
     * taxId [string]: receiver account tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a Pix SplitReceiver will be created, else a TED will be issued. ex: "20018183" or "341"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * accountNumber [string]: receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * accountType [string]: Receiver bank account type. This parameter only has effect on Pix SplitReceivers. ex: "checking", "savings", "salary" or "payment"
     * id [string]: unique id returned when the SplitReceiver is created. ex: "5656565656565656"
     * tags [list of strings, default []]: list of strings for reference when searching for receivers. ex: ["seller/123456"]
     * status [string]: current payment status. ex: "success" or "failed"
     * created [string]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: update datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(SplitReceiver.class, "SplitReceiver");

    public String name;
    public String taxId;
    public String bankCode;
    public String branchCode;
    public String accountNumber;
    public String accountType;
    public String[] tags;
    public String status;
    public String created;
    public String updated;

    /**
     * SplitReceiver object
     * <p>
     * The SplitReceiver objects
     * <p>
     * When you initialize a SplitReceiver, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param name [string]: receiver full name. ex: "Anthony Edward Stark"
     * @param taxId [string]: receiver account tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a Pix SplitReceiver will be created, else a TED will be issued. ex: "20018183" or "341"
     * @param branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * @param accountNumber [string]: receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * @param accountType [string]: Receiver bank account type. This parameter only has effect on Pix SplitReceivers. ex: "checking", "savings", "salary" or "payment"
     * @param id [string]: unique id returned when the SplitReceiver is created. ex: "5656565656565656"
     * @param tags [list of strings, default []]: list of strings for reference when searching for receivers. ex: ["seller/123456"]
     * @param status [string]: current payment status. ex: "success" or "failed"
     * @param created [string]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string]: update datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public SplitReceiver(String name, String taxId, String bankCode, String branchCode, String accountNumber,
                 String accountType, String id, String[] tags, String status, String created, String updated
    ) {
        super(id);
        this.name = name;
        this.taxId = taxId;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.tags = tags;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    /**
     * SplitReceiver object
     * <p>
     * When you initialize a SplitReceiver, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param data map of parameters for the creation of the SplitReceiver
     * Parameters:
     * name [string]: receiver full name. ex: "Anthony Edward Stark"
     * taxId [string]: receiver account tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a Pix SplitReceiver will be created, else a TED will be issued. ex: "20018183" or "341"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * accountNumber [string]: receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * accountType [string]: Receiver bank account type. This parameter only has effect on Pix SplitReceivers. ex: "checking", "savings", "salary" or "payment"
     * id [string]: unique id returned when the SplitReceiver is created. ex: "5656565656565656"
     * <p>
     * Parameters (optional):
     * tags [list of strings, default []]: list of strings for reference when searching for receivers. ex: ["seller/123456"]
     * <p>
     * Attributes (return-only):
     * status [string]: current payment status. ex: "success" or "failed"
     * created [string]: creation datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: update datetime for the payment. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public SplitReceiver(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.name = (String) dataCopy.remove("name");
        this.taxId = (String) dataCopy.remove("taxId");
        this.bankCode = (String) dataCopy.remove("bankCode");
        this.branchCode = (String) dataCopy.remove("branchCode");
        this.accountNumber = (String) dataCopy.remove("accountNumber");
        this.accountType = (String) dataCopy.remove("accountType");
        this.tags = (String[]) dataCopy.remove("tags");
        this.status = null;
        this.created = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Create SplitReceivers
     * <p>
     * Send a list of SplitReceiver objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param receivers [list of SplitReceivers objects or HashMaps]: list of SplitReceiver objects to be created in the API
     * <p>
     * Return:
     * @return list of SplitReceiver objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<SplitReceiver> create(List<?> receivers) throws Exception {
        return SplitReceiver.create(receivers, null);
    }

    /**
     * Create SplitReceivers
     * <p>
     * Send a list of SplitReceiver objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param receivers [list of SplitReceivers objects or HashMaps]: list of SplitReceiver objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<SplitReceiver> create(List<?> receivers, User user) throws Exception {
        List<SplitReceiver> receiverList = new ArrayList<>();
        for (Object receiver : receivers){
            if (receiver instanceof Map){
                receiverList.add(new SplitReceiver((Map<String, Object>) receiver));
                continue;
            }
            if (receiver instanceof SplitReceiver){
                receiverList.add((SplitReceiver) receiver);
                continue;
            }
            throw new Exception("Unknown type \"" + receiver.getClass() + "\", use SplitReceiver or HashMap");
        }
        return Rest.post(data, receiverList, user);
    }

    /**
     * Retrieve a specific SplitReceiver
     * <p>
     * Receive a single SplitReceiver object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: unique id returned when SplitReceiver is created. ex: "5656565656565656"
     * <p>
     * Return:
     * @return SplitReceiver object with updated attributes
     * @throws Exception error in the request
     */
    public static SplitReceiver get(String id) throws Exception {
        return SplitReceiver.get(id, null);
    }

    /**
     * Retrieve a specific SplitReceiver
     * <p>
     * Receive a single SplitReceiver object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return SplitReceiver object with updated attributes
     * @throws Exception error in the request
     */
    public static SplitReceiver get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve SplitReceivers
     * <p>
     * Receive a generator of SplitReceiver objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if none. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * transactionIds [list of strings, default null]: list of transaction IDs linked to the desired splitReceivers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success" or "failed"
     * taxId [string, default null]: filter for splitReceivers sent to the specified tax ID. ex: "012.345.678-90"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of SplitReceiver objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitReceiver> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve SplitReceivers
     * <p>
     * Receive a generator of SplitReceiver objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if none. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * transactionIds [list of strings, default null]: list of transaction IDs linked to the desired splitReceivers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success" or "failed"
     * taxId [string, default null]: filter for splitReceivers sent to the specified tax ID. ex: "012.345.678-90"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of SplitReceiver objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitReceiver> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve SplitReceivers
     * <p>
     * Receive a generator of SplitReceiver objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkinfra.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of SplitReceiver objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitReceiver> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve SplitReceivers
     * <p>
     * Receive a generator of SplitReceiver objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of SplitReceiver objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<SplitReceiver> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<SplitReceiver> splitReceivers;
        public String cursor;

        public Page(List<SplitReceiver> splitReceivers, String cursor) {
            this.splitReceivers = splitReceivers;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged SplitReceivers
     * <p>
     * Receive a list of up to 100 SplitReceiver objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * receiverIds [list of strings, default null]: receiver IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default ""]: filter for status of retrieved objects. ex: "success" or "failed"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return SplitReceiver.Page object:
     * SplitReceiver.Page.SplitReceivers: list of SplitReceiver objects with updated attributes
     * SplitReceiver.Page.cursor: cursor to retrieve the next page of SplitReceiver objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String , Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<SplitReceiver> SplitReceivers = new ArrayList<>();
        for (SubResource SplitReceiver: page.entities) {
            SplitReceivers.add((SplitReceiver) SplitReceiver);
        }
        return new Page(SplitReceivers, page.cursor);
    }

    /**
     * Retrieve paged SplitReceivers
     * <p>
     * Receive a list of up to 100 SplitReceiver objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * receiverIds [list of strings, default null]: receiver IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default ""]: filter for status of retrieved objects. ex: "active", "blocked", "expired" or "canceled"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * <p>
     * Return:
     * @return SplitReceiver.Page object:
     * SplitReceiver.Page.SplitReceivers: list of SplitReceiver objects with updated attributes
     * SplitReceiver.Page.cursor: cursor to retrieve the next page of SplitReceiver objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String , Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged SplitReceivers
     * <p>
     * Receive a list of up to 100 SplitReceiver objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return SplitReceiver.Page object:
     * SplitReceiver.Page.SplitReceivers: list of SplitReceiver objects with updated attributes
     * SplitReceiver.Page.cursor: cursor to retrieve the next page of SplitReceiver objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged SplitReceivers
     * <p>
     * Receive a list of up to 100 SplitReceiver objects registered to your workspace in the Stark Bank API and the cursor to the next page.
     * <p>
     * Return:
     * @return SplitReceiver.Page object:
     * SplitReceiver.Page.SplitReceivers: list of SplitReceiver objects with updated attributes
     * SplitReceiver.Page.cursor: cursor to retrieve the next page of SplitReceiver objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(SplitReceiver.Log.class, "SplitReceiverLog");

        public SplitReceiver receiver;
        public List<ErrorElement> errors;
        public String type;
        public String created;

        /**
         * SplitReceiver Log object
         * <p>
         * Every time a SplitReceiver entity is updated, a corresponding SplitReceiver.Log
         * is generated for the entity. This log is never generated by the user,
         * but it can be retrieved to check additional information on the SplitReceiver.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param receiver [SplitReceiver]: SplitReceiver entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this Split event
         * @param type [string]: type of the SplitReceiver event which triggered the log creation. ex: "success" or "failed"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, List<ErrorElement> errors, SplitReceiver receiver, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.receiver = receiver;
        }

        /**
         * Retrieve a specific SplitReceiver Log
         * <p>
         * Receive a single SplitReceiver Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return SplitReceiver Log object with updated attributes
         * @throws Exception error in the card
         */
        public static SplitReceiver.Log get(String id) throws Exception {
            return SplitReceiver.Log.get(id, null);
        }

        /**
         * Retrieve a specific SplitReceiver Log
         * <p>
         * Receive a single SplitReceiver Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return SplitReceiver Log object with updated attributes
         * @throws Exception error in the card
         */
        public static SplitReceiver.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve SplitReceiver Logs
         * <p>
         * Receive a generator of SplitReceiver.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "blocked"
         * splitIds [list of strings, default null]: list of SplitReceiver ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of SplitReceiver Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<SplitReceiver.Log> query(Map<String, Object> params) throws Exception {
            return SplitReceiver.Log.query(params, null);
        }

        /**
         * Retrieve SplitReceiver Logs
         * <p>
         * Receive a generator of SplitReceiver.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of SplitReceiver Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<SplitReceiver.Log> query(User user) throws Exception {
            return SplitReceiver.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve SplitReceiver Logs
         * <p>
         * Receive a generator of SplitReceiver.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of SplitReceiver Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<SplitReceiver.Log> query() throws Exception {
            return SplitReceiver.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve SplitReceiver Logs
         * <p>
         * Receive a generator of SplitReceiver.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "blocked"
         * splitIds [list of strings, default null]: list of SplitReceiver ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of SplitReceiver Log objects with updated attributes
         * @throws Exception error in the holder
         */
        public static Generator<SplitReceiver.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<SplitReceiver.Log> logs;
            public String cursor;

            public Page(List<SplitReceiver.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged SplitReceiver.Logs
         * <p>
         * Receive a list of up to 100 SplitReceiver.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * splitIds [list of strings, default null]: list of SplitReceiver ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return SplitReceiver.Log.Page object:
         * SplitReceiver.Log.Page.logs: list of SplitReceiver.Log objects with updated attributes
         * SplitReceiver.Log.Page.cursor: cursor to retrieve the next page of SplitReceiver.Log objects
         * @throws Exception error in the card
         */
        public static SplitReceiver.Log.Page page(Map<String, Object> params) throws Exception {
            return SplitReceiver.Log.page(params, null);
        }

        /**
         * Retrieve paged SplitReceiver.Logs
         * <p>
         * Receive a list of up to 100 SplitReceiver.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return SplitReceiver.Log.Page object:
         * SplitReceiver.Log.Page.logs: list of SplitReceiver.Log objects with updated attributes
         * SplitReceiver.Log.Page.cursor: cursor to retrieve the next page of SplitReceiver.Log objects
         * @throws Exception error in the card
         */
        public static SplitReceiver.Log.Page page(User user) throws Exception {
            return SplitReceiver.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged SplitReceiver.Logs
         * <p>
         * Receive a list of up to 100 SplitReceiver.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Return:
         * @return SplitReceiver.Log.Page object:
         * SplitReceiver.Log.Page.logs: list of SplitReceiver.Log objects with updated attributes
         * SplitReceiver.Log.Page.cursor: cursor to retrieve the next page of SplitReceiver.Log objects
         * @throws Exception error in the card
         */
        public static SplitReceiver.Log.Page page() throws Exception {
            return SplitReceiver.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged SplitReceiver.Logs
         * <p>
         * Receive a list of up to 100 SplitReceiver.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * splitIds [list of strings, default null]: list of SplitReceiver ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return SplitReceiver.Log.Page object:
         * SplitReceiver.Log.Page.logs: list of SplitReceiver.Log objects with updated attributes
         * SplitReceiver.Log.Page.cursor: cursor to retrieve the next page of SplitReceiver.Log objects
         * @throws Exception error in the card
         */
        public static SplitReceiver.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<SplitReceiver.Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((SplitReceiver.Log) log);
            }
            return new SplitReceiver.Log.Page(logs, page.cursor);
        }
    }
}