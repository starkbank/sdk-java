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


public final class VerifiedAccount extends Resource {
    /**
     * VerifiedAccount object
     * <p>
     * When you initialize a VerifiedAccount, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a Pix transfer will be created, else a TED will be issued. ex: "20018183" or "341"
     * bankName [string]: bank name associated with the verified account. ex: "Stark Bank"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * created [string]: creation datetime for the verified account. ex: "2020-03-10 10:30:00.000000+00:00"
     * id [string]: unique id returned when the VerifiedAccount is created. ex: "5656565656565656"
     * keyId [string]: pix key identifier. ex: "tony@starkbank.com", "012.345.678-90"
     * name [string]: receiver full name. ex: "Anthony Edward Stark"
     * number [string]: receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * status [string]: current verified account status. ex: "creating", "created", "processing", "active", "failed" or "canceled"
     * tags [list of strings]: list of strings for reference when searching for verified accounts. ex: ["employees", "monthly"]
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * type [string]: verified account type. ex: "checking", "savings", "salary" or "payment"
     * updated [string]: update datetime for the verified account. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(VerifiedAccount.class, "VerifiedAccount");

    public String bankCode;
    public String bankName;
    public String branchCode;
    public String created;
    public String keyId;
    public String name;
    public String number;
    public String status;
    public String[] tags;
    public String taxId;
    public String type;
    public String updated;

    /**
     * VerifiedAccount object
     * <p>
     * When you initialize a VerifiedAccount, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a Pix transfer will be created, else a TED will be issued. ex: "20018183" or "341"
     * @param bankName [string]: bank name associated with the verified account. ex: "Stark Bank"
     * @param branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * @param created [string]: creation datetime for the verified account. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param keyId [string]: pix key identifier. ex: "tony@starkbank.com", "012.345.678-90"
     * @param name [string]: receiver full name. ex: "Anthony Edward Stark"
     * @param number [string]: receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * @param status [string]: current verified account status. ex: "creating", "created", "processing", "active", "failed" or "canceled"
     * @param tags [list of strings]: list of strings for reference when searching for verified accounts. ex: ["employees", "monthly"]
     * @param taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param type [string]: verified account type. ex: "checking", "savings", "salary" or "payment"
     * @param updated [string]: update datetime for the verified account. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public VerifiedAccount(String id, String bankCode, String bankName, String branchCode, String created,
                           String keyId, String name, String number, String status, String[] tags, String taxId,
                           String type, String updated) {
        super(id);
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.branchCode = branchCode;
        this.created = created;
        this.keyId = keyId;
        this.name = name;
        this.number = number;
        this.status = status;
        this.tags = tags;
        this.taxId = taxId;
        this.type = type;
        this.updated = updated;
    }

    /**
     * VerifiedAccount object
     * <p>
     * When you initialize a VerifiedAccount, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters (required):
     * @param data map of properties for the creation of the VerifiedAccount
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * <p>
     * Parameters (conditionally required):
     * bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a Pix transfer will be created, else a TED will be issued. The bankCode parameter is required if verifying with bank details. ex: "20018183" or "341"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9". The branchCode parameter is required if verifying with bank details.
     * keyId [string]: pix key identifier. ex: "tony@starkbank.com", "012.345.678-90". The keyId parameter is required if verifying with Pix key.
     * name [string]: receiver full name. ex: "Anthony Edward Stark". The name parameter is required if verifying with bank details.
     * number [string]: receiver bank account number. Use "-" before the verifier digit. ex: "876543-2". The number parameter is required if verifying with bank details.
     * type [string]: verified account type. ex: "checking", "savings", "salary" or "payment". The type parameter is required if verifying with bank details.
     * <p>
     * Parameters (optional):
     * tags [list of strings]: list of strings for reference when searching for verified accounts. ex: ["employees", "monthly"]
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when the VerifiedAccount is created. ex: "5656565656565656"
     * created [string]: creation datetime for the verified account. ex: "2020-03-10 10:30:00.000000+00:00"
     * status [string]: current verified account status. ex: "creating", "created", "processing", "active", "failed" or "canceled"
     * updated [string]: update datetime for the verified account. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public VerifiedAccount(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.bankCode = (String) dataCopy.remove("bankCode");
        this.bankName = (String) dataCopy.remove("bankName");
        this.branchCode = (String) dataCopy.remove("branchCode");
        this.keyId = (String) dataCopy.remove("keyId");
        this.name = (String) dataCopy.remove("name");
        this.number = (String) dataCopy.remove("number");
        this.tags = (String[]) dataCopy.remove("tags");
        this.taxId = (String) dataCopy.remove("taxId");
        this.type = (String) dataCopy.remove("type");
        this.created = null;
        this.status = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public VerifiedAccount(){
        super(null);
    }

    /**
     * Retrieve a specific VerifiedAccount
     * <p>
     * Receive a single VerifiedAccount object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return VerifiedAccount object with updated attributes
     * @throws Exception error in the request
     */
    public static VerifiedAccount get(String id) throws Exception {
        return VerifiedAccount.get(id, null);
    }

    /**
     * Retrieve a specific VerifiedAccount
     * <p>
     * Receive a single VerifiedAccount object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return VerifiedAccount object with updated attributes
     * @throws Exception error in the request
     */
    public static VerifiedAccount get(String id, User user) throws Exception {
        return Rest.getId(data, id, null, user);
    }
    
    /**
     * Create VerifiedAccounts
     * <p>
     * Send a list of VerifiedAccount objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param verifiedAccounts [list of VerifiedAccount objects or HashMaps]: list of VerifiedAccount objects to be created in the API
     * <p>
     * Return:
     * @return list of VerifiedAccount objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<VerifiedAccount> create(List<?> verifiedAccounts) throws Exception {
        return VerifiedAccount.create(verifiedAccounts, null);
    }
    
    /**
     * Create VerifiedAccounts
     * <p>
     * Send a list of VerifiedAccount objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param verifiedAccounts [list of VerifiedAccount objects or HashMaps]: list of VerifiedAccount objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return list of VerifiedAccount objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<VerifiedAccount> create(List<?> verifiedAccounts, User user) throws Exception {
        List<VerifiedAccount> verifiedAccountList = new ArrayList<>();
        for (Object verifiedAccount : verifiedAccounts){
            if (verifiedAccount instanceof Map){
                verifiedAccountList.add(new VerifiedAccount((Map<String, Object>) verifiedAccount));
                continue;
            }
            if (verifiedAccount instanceof VerifiedAccount){
                verifiedAccountList.add((VerifiedAccount) verifiedAccount);
                continue;
            }
            throw new Exception("Unknown type \"" + verifiedAccount.getClass() + "\", use VerifiedAccount or HashMap");
        }
        return Rest.post(data, verifiedAccountList, user);
    }

    /**
     * Retrieve VerifiedAccounts
     * <p>
     * Receive a generator of VerifiedAccount objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "creating", "created", "processing", "active", "failed" or "canceled"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * <p>
     * Return:
     * @return generator of VerifiedAccount objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<VerifiedAccount> query(Map<String, Object> params) throws Exception {
        return VerifiedAccount.query(params, null);
    }

    /**
     * Retrieve VerifiedAccounts
     * <p>
     * Receive a generator of VerifiedAccount objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of VerifiedAccount objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<VerifiedAccount> query(User user) throws Exception {
        return VerifiedAccount.query(new HashMap<>(), user);
    }

    /**
     * Retrieve VerifiedAccounts
     * <p>
     * Receive a generator of VerifiedAccount objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of VerifiedAccount objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<VerifiedAccount> query() throws Exception {
        return VerifiedAccount.query(new HashMap<>(), null);
    }

    /**
     * Retrieve VerifiedAccounts
     * <p>
     * Receive a generator of VerifiedAccount objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "creating", "created", "processing", "active", "failed" or "canceled"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of VerifiedAccount objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<VerifiedAccount> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public final static class Page {
        public List<VerifiedAccount> verifiedAccounts;
        public String cursor;

        public Page(List<VerifiedAccount> verifiedAccounts, String cursor) {
            this.verifiedAccounts = verifiedAccounts;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged VerifiedAccounts
     * <p>
     * Receive a list of up to 100 VerifiedAccount objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "creating", "created", "processing", "active", "failed" or "canceled"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * <p>
     * Return:
     * @return VerifiedAccount.Page object:
     * VerifiedAccount.Page.verifiedAccounts: list of VerifiedAccount objects with updated attributes
     * VerifiedAccount.Page.cursor: cursor to retrieve the next page of VerifiedAccount objects
     * @throws Exception error in the request
     */
    public static VerifiedAccount.Page page(Map<String, Object> params) throws Exception {
        return VerifiedAccount.page(params, null);
    }

    /**
     * Retrieve paged VerifiedAccounts
     * <p>
     * Receive a list of up to 100 VerifiedAccount objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return VerifiedAccount.Page object:
     * VerifiedAccount.Page.verifiedAccounts: list of VerifiedAccount objects with updated attributes
     * VerifiedAccount.Page.cursor: cursor to retrieve the next page of VerifiedAccount objects
     * @throws Exception error in the request
     */
    public static VerifiedAccount.Page page(User user) throws Exception {
        return VerifiedAccount.page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged VerifiedAccounts
     * <p>
     * Receive a list of up to 100 VerifiedAccount objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return VerifiedAccount.Page object:
     * VerifiedAccount.Page.verifiedAccounts: list of VerifiedAccount objects with updated attributes
     * VerifiedAccount.Page.cursor: cursor to retrieve the next page of VerifiedAccount objects
     * @throws Exception error in the request
     */
    public static VerifiedAccount.Page page() throws Exception {
        return VerifiedAccount.page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged VerifiedAccounts
     * <p>
    * Receive a list of up to 100 VerifiedAccount objects previously created in the Stark Bank API and the cursor to the next page.
    * Use this function instead of query if you want to manually page your requests.
    * <p>
    * Parameters:
    * @param params parameters of the query
    * cursor [string, default null]: cursor returned on the previous page function call
    * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
    * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
    * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
    * status [string, default null]: filter for status of retrieved objects. ex: "creating", "created", "processing", "active", "failed" or "canceled"
    * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
    * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
    * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
    * <p>
    * Return:
    * @return VerifiedAccount.Page object:
    * VerifiedAccount.Page.verifiedAccounts: list of VerifiedAccount objects with updated attributes
    * VerifiedAccount.Page.cursor: cursor to retrieve the next page of VerifiedAccount objects
    * @throws Exception error in the request
    */
    public static VerifiedAccount.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<VerifiedAccount> verifiedAccounts = new ArrayList<>();
        for (SubResource verifiedAccount: page.entities) {
            verifiedAccounts.add((VerifiedAccount) verifiedAccount);
        }
        return new VerifiedAccount.Page(verifiedAccounts, page.cursor);
    }
    
    /**
     * Cancel a VerifiedAccount
     * <p>
     * Cancel a VerifiedAccount entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: VerifiedAccount unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return canceled VerifiedAccount object
     * @throws Exception error in the request
     */
    public static VerifiedAccount cancel(String id) throws Exception {
        return VerifiedAccount.cancel(id, null);
    }
    
    /**
     * Cancel a VerifiedAccount
     * <p>
     * Cancel a VerifiedAccount entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: VerifiedAccount unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return canceled VerifiedAccount object
     * @throws Exception error in the request
     */
    public static VerifiedAccount cancel(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "VerifiedAccountLog");

        public String created;
        public String type;
        public List<ErrorElement> errors;
        public VerifiedAccount account;
        
        /**
         * VerifiedAccount Log object
         * <p>
         * Every time a VerifiedAccount entity is modified, a corresponding VerifiedAccount Log
         * is generated for the entity. This log is never generated by the user, but it can
         * be retrieved to check additional information on the VerifiedAccount.
         * <p>
         * Attributes (return-only):
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param account [VerifiedAccount]: VerifiedAccount entity to which the log refers to.
         * @param errors [list of ErrorElement]: list of errors linked to the VerifiedAccount event.
         * @param type [string]: type of the VerifiedAccount event which triggered the log creation. ex: "created" or "processing"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, List<ErrorElement> errors, VerifiedAccount account, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.account = account;
        }

        public Log() {
            super(null);
        }

        /**
         * Retrieve a specific VerifiedAccount Log
         * <p>
         * Receive a single VerifiedAccount Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return VerifiedAccount Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific VerifiedAccount Log
         * <p>
         * Receive a single VerifiedAccount Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return VerifiedAccount Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve VerifiedAccount Logs
         * <p>
         * Receive a generator of VerifiedAccount.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "created" or "processing"
         * accountIds [list of strings, default null]: list of VerifiedAccount ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of VerifiedAccount Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve VerifiedAccount Logs
         * <p>
         * Receive a generator of VerifiedAccount.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of VerifiedAccount Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }
        
        /**
         * Retrieve VerifiedAccount Logs
         * <p>
         * Receive a generator of VerifiedAccount.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of VerifiedAccount Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }
        
        /**
         * Retrieve VerifiedAccount Logs
         * <p>
         * Receive a generator of VerifiedAccount.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * accountIds [list of strings, default null]: list of VerifiedAccount ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of VerifiedAccount Log objects with updated attributes
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
         * Retrieve paged VerifiedAccount Logs
         * <p>
         * Receive a list of up to 100 VerifiedAccount.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * accountIds [list of strings, default null]: list of VerifiedAccount ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return VerifiedAccount.Log.Page object:
         * VerifiedAccount.Log.Page.logs: list of VerifiedAccount.Log objects with updated attributes
         * VerifiedAccount.Log.Page.cursor: cursor to retrieve the next page of VerifiedAccount.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }
        
        /**
         * Retrieve paged VerifiedAccount Logs
         * <p>
         * Receive a list of up to 100 VerifiedAccount.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return VerifiedAccount.Log.Page object:
         * VerifiedAccount.Log.Page.logs: list of VerifiedAccount.Log objects with updated attributes
         * VerifiedAccount.Log.Page.cursor: cursor to retrieve the next page of VerifiedAccount.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }
        
        /**
         * Retrieve paged VerifiedAccount Logs
         * <p>
         * Receive a list of up to 100 VerifiedAccount.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return VerifiedAccount.Log.Page object:
         * VerifiedAccount.Log.Page.logs: list of VerifiedAccount.Log objects with updated attributes
         * VerifiedAccount.Log.Page.cursor: cursor to retrieve the next page of VerifiedAccount.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }
        
        
        /**
         * Retrieve paged VerifiedAccount Logs
         * <p>
         * Receive a list of up to 100 VerifiedAccount.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * accountIds [list of strings, default null]: list of VerifiedAccount ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return VerifiedAccount.Log.Page object:
         * VerifiedAccount.Log.Page.logs: list of VerifiedAccount.Log objects with updated attributes
         * VerifiedAccount.Log.Page.cursor: cursor to retrieve the next page of VerifiedAccount.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((Log) log);
            }
            return new Log.Page(logs, page.cursor);
        }
    }
}
