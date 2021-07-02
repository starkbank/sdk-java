package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Transfer extends Resource {
    static ClassData data = new ClassData(Transfer.class, "Transfer");

    public long amount;
    public String name;
    public String taxId;
    public String bankCode;
    public String branchCode;
    public String accountNumber;
    public String accountType;
    public String externalId;
    public String scheduled;
    public String description;
    public String[] tags;
    public Integer fee;
    public String status;
    public String created;
    public String updated;
    public String[] transactionIds;

    /**
     * Transfer object
     * <p>
     * When you initialize a Transfer, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param amount [long]: amount in cents to be transferred. ex: 1234 (= R$ 12.34)
     * @param name [string]: receiver full name. ex: "Anthony Edward Stark"
     * @param taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param bankCode [string]: code of the receiver bank institution in Brazil. If an ISPB (8 digits) is informed, a PIX transfer will be created, else a TED will be issued. ex: "20018183" or "341"
     * @param branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * @param accountNumber [string]: Receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * @param accountType [string]: Receiver bank account type. This parameter only has effect on Pix Transfers. ex: "checking", "savings", "salary" or "payment"
     * @param externalId [string]: url safe string that must be unique among all your transfers. Duplicated external_ids will cause failures. By default, this parameter will block any transfer that repeats amount and receiver information on the same date. ex: "my-internal-id-123456"
     * @param scheduled [string]: date or datetime when the transfer will be processed. May be pushed to next business day if necessary. ex: "2020-03-11 08:00:00.000"
     * @param description [string]: optional description to override default description to be shown in the bank statement. ex: "Payment for service #1234"
     * @param tags [list of strings]: list of strings for reference when searching for transfers. ex: ["employees", "monthly"]
     * <p>
     * Attributes (return-only):
     * @param id [string, default null]: unique id returned when transfer is created. ex: "5656565656565656"
     * @param fee [integer, default null]: fee charged when the transfer is created. ex: 200 (= R$ 2.00)
     * @param status [string, default null]: current transfer status. ex: "processing" or "success"
     * @param transactionIds [list of strings, default null]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * @param created [string, default null]: creation datetime for the transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string, default null]: latest update datetime for the transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public Transfer(String id, long amount, String name, String taxId, String bankCode, String branchCode,
                    String accountNumber, String accountType, String externalId, String scheduled, String description,
                    String[] tags, Integer fee, String status, String created,  String updated, String[] transactionIds) {
        super(id);
        this.amount = amount;
        this.name = name;
        this.taxId = taxId;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.externalId = externalId;
        this.scheduled = scheduled;
        this.description = description;
        this.tags = tags;
        this.fee = fee;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.transactionIds = transactionIds;
    }

    /**
     * Transfer object
     * <p>
     * When you initialize a Transfer, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the Transfer
     * amount [long]: amount in cents to be transferred. ex: 1234 (= R$ 12.34)
     * name [string]: receiver full name. ex: "Anthony Edward Stark"
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * bankCode [string]: 1 to 3 digits of the receiver bank institution in Brazil. ex: "200" or "341"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * accountNumber [string]: Receiver bank account number. Use "-" before the verifier digit. ex: "876543-2"
     * <p>
     * Parameters (optional):
     * accountType [string, default "checking"]: Receiver bank account type. This parameter only has effect on Pix Transfers. ex: "checking", "savings", "salary" or "payment"
     * externalId [string, default null]: url safe string that must be unique among all your transfers. Duplicated external_ids will cause failures. By default, this parameter will block any transfer that repeats amount and receiver information on the same date. ex: "my-internal-id-123456"
     * scheduled [string, default now]: datetime when the transfer will be processed. May be pushed to next business day if necessary. ex: "2020-03-11 08:00:00.000"
     * description [string]: optional description to override default description to be shown in the bank statement. ex: "Payment for service #1234"
     * tags [list of strings]: list of strings for reference when searching for transfers. ex: ["employees", "monthly"]
     * <p>
     * Attributes (return-only):
     * id [string, default null]: unique id returned when transfer is created. ex: "5656565656565656"
     * fee [integer, default null]: fee charged when the transfer is created. ex: 200 (= R$ 2.00)
     * status [string, default null]: current transfer status. ex: "processing" or "success"
     * transactionIds [list of strings, default null]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * created [string, default null]: creation datetime for the transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string, default null]: latest update datetime for the transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public Transfer(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = ((Number) dataCopy.remove("amount")).longValue();
        this.name = (String) dataCopy.remove("name");
        this.taxId = (String) dataCopy.remove("taxId");
        this.bankCode = (String) dataCopy.remove("bankCode");
        this.branchCode = (String) dataCopy.remove("branchCode");
        this.accountNumber = (String) dataCopy.remove("accountNumber");
        this.accountType = (String) dataCopy.remove("accountType");
        this.externalId = (String) dataCopy.remove("externalId");
        this.scheduled = (String) dataCopy.remove("scheduled");
        this.description = (String) dataCopy.remove("description");
        this.tags = (String[]) dataCopy.remove("tags");
        this.created = null;
        this.fee = null;
        this.status = null;
        this.transactionIds = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve a specific Transfer
     * <p>
     * Receive a single Transfer object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Transfer object with updated attributes
     * @throws Exception error in the request
     */
    public static Transfer get(String id) throws Exception {
        return Transfer.get(id, null);
    }

    /**
     * Retrieve a specific Transfer
     * <p>
     * Receive a single Transfer object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Transfer object with updated attributes
     * @throws Exception error in the request
     */
    public static Transfer get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Delete a Transfer entity
     * <p>
     * Delete a Transfer entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: Transfer unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted Transfer object
     * @throws Exception error in the request
     */
    public static Transfer delete(String id) throws Exception {
        return Transfer.delete(id, null);
    }

    /**
     * Delete a Transfer entity
     * <p>
     * Delete a Transfer entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: Transfer unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted Transfer object
     * @throws Exception error in the request
     */
    public static Transfer delete(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * transactionIds [list of strings, default null]: list of transaction IDs linked to the desired transfers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "processing" or "success"
     * taxId [string, default null]: filter for transfers sent to the specified tax ID. ex: "012.345.678-90"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transfer> query(Map<String, Object> params) throws Exception {
        return Transfer.query(params, null);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transfer> query(User user) throws Exception {
        return Transfer.query(new HashMap<>(), user);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transfer> query() throws Exception {
        return Transfer.query(new HashMap<>(), null);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of properties for the creation of the Transfer
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * transactionIds [list of strings, default null]: list of transaction IDs linked to the desired transfers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "processing" or "success"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transfer> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public final static class Page {
        public List<Transfer> transfers;
        public String cursor;

        public Page(List<Transfer> transfers, String cursor) {
            this.transfers = transfers;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged Transfers
     * <p>
     * Receive a list of up to 100 Transfer objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * transferIds [list of strings, default null]: list of transfer IDs linked to the desired transfers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "processing" or "success"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return Transfer.Page object:
     * Transfer.Page.transfers: list of Transfer objects with updated attributes
     * Transfer.Page.cursor: cursor to retrieve the next page of Transfer objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged Transfers
     * <p>
     * Receive a list of up to 100 Transfer objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Transfer.Page object:
     * Transfer.Page.transfers: list of Transfer objects with updated attributes
     * Transfer.Page.cursor: cursor to retrieve the next page of Transfer objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged Transfers
     * <p>
     * Receive a list of up to 100 Transfer objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return Transfer.Page object:
     * Transfer.Page.transfers: list of Transfer objects with updated attributes
     * Transfer.Page.cursor: cursor to retrieve the next page of Transfer objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged Transfers
     * <p>
     * Receive a list of up to 100 Transfer objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null]: date filter for objects created or updated only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created or updated only before specified date. ex: "2020-03-10"
     * transferIds [list of strings, default null]: list of transfer IDs linked to the desired transfers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "processing" or "success"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Transfer.Page object:
     * Transfer.Page.transfers: list of Transfer objects with updated attributes
     * Transfer.Page.cursor: cursor to retrieve the next page of Transfer objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<Transfer> transfers = new ArrayList<>();
        for (SubResource transfer: page.entities) {
            transfers.add((Transfer) transfer);
        }
        return new Page(transfers, page.cursor);
    }

    /**
     * Create Transfers
     * <p>
     * Send a list of Transfer objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param transfers [list of Transfer objects or HashMaps]: list of Transfer objects to be created in the API
     * <p>
     * Return:
     * @return list of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<Transfer> create(List<?> transfers) throws Exception {
        return Transfer.create(transfers, null);
    }

    /**
     * Create Transfers
     * <p>
     * Send a list of Transfer objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param transfers [list of Transfer objects or HashMaps]: list of Transfer objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of Transfer objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<Transfer> create(List<?> transfers, User user) throws Exception {
        List<Transfer> transferList = new ArrayList<>();
        for (Object transfer : transfers){
            if (transfer instanceof Map){
                transferList.add(new Transfer((Map<String, Object>) transfer));
                continue;
            }
            if (transfer instanceof Transfer){
                transferList.add((Transfer) transfer);
                continue;
            }
            throw new Exception("Unknown type \"" + transfer.getClass() + "\", use Transfer or HashMap");
        }
        return Rest.post(data, transferList, user);
    }

    /**
     * Retrieve a specific Transfer pdf file
     * <p>
     * Receive a single Transfer pdf receipt file generated in the Stark Bank API by passing its id.
     * Only valid for transfers with "processing" and "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Transfer pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id) throws Exception {
        return Transfer.pdf(id, null);
    }

    /**
     * Retrieve a specific Transfer pdf file
     * <p>
     * Receive a single Transfer pdf receipt file generated in the Stark Bank API by passing its id.
     * Only valid for transfers with "processing" and "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Transfer pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, User user) throws Exception {
        return Rest.getContent(data, id, "pdf", user, null);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "TransferLog");

        public String created;
        public String type;
        public String[] errors;
        public Transfer transfer;

        /**
         * Transfer Log object
         * <p>
         * Every time a Transfer entity is modified, a corresponding Transfer Log
         * is generated for the entity. This log is never generated by the
         * user.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param transfer [Transfer]: Transfer entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to the Transfer event.
         * @param type [string]: type of the Transfer event which triggered the log creation. ex: "processing" or "success"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, String[] errors, Transfer transfer, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.transfer = transfer;
        }

        /**
         * Retrieve a specific Transfer Log
         * <p>
         * Receive a single Transfer Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return Transfer Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific Transfer Log
         * <p>
         * Receive a single Transfer Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Transfer Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * transferIds [list of strings, default null]: list of Transfer ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of Transfer Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of Transfer Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of Transfer Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * transferIds [list of strings, default null]: list of Transfer ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of Transfer Log objects with updated attributes
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
         * Retrieve paged Transfer.Logs
         * <p>
         * Receive a list of up to 100 Transfer.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * transferIds [list of strings, default null]: list of Transfer ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return Transfer.Log.Page object:
         * Transfer.Log.Page.logs: list of Transfer.Log objects with updated attributes
         * Transfer.Log.Page.cursor: cursor to retrieve the next page of Transfer.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged Transfer.Logs
         * <p>
         * Receive a list of up to 100 Transfer.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Transfer.Log.Page object:
         * Transfer.Log.Page.logs: list of Transfer.Log objects with updated attributes
         * Transfer.Log.Page.cursor: cursor to retrieve the next page of Transfer.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged Transfer.Logs
         * <p>
         * Receive a list of up to 100 Transfer.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return Transfer.Log.Page object:
         * Transfer.Log.Page.logs: list of Transfer.Log objects with updated attributes
         * Transfer.Log.Page.cursor: cursor to retrieve the next page of Transfer.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged Transfer.Logs
         * <p>
         * Receive a list of up to 100 Transfer.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * transferIds [list of strings, default null]: list of Transfer ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Transfer.Log.Page object:
         * Transfer.Log.Page.logs: list of Transfer.Log objects with updated attributes
         * Transfer.Log.Page.cursor: cursor to retrieve the next page of Transfer.Log objects
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
