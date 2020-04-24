package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Transfer extends Resource {
    static ClassData data = new ClassData(Transfer.class, "Transfer");

    public Integer amount;
    public String name;
    public String taxId;
    public String bankCode;
    public String branchCode;
    public String accountNumber;
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
     * amount [integer]: amount in cents to be transferred. ex: 1234 (= R$ 12.34)
     * name [string]: receiver full name. ex: "Anthony Edward Stark"
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * bankCode [string]: receiver 1 to 3 digits of the bank institution in Brazil. ex: "200" or "341"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * accountNumber [string]: Receiver Bank Account number. Use "-" before the verifier digit. ex: "876543-2"
     * tags [list of strings]: list of strings for reference when searching for transfers. ex: ["employees", "monthly"]
     * <p>
     * Attributes (return-only):
     * id [string, default null]: unique id returned when Transfer is created. ex: "5656565656565656"
     * fee [integer, default null]: fee charged when transfer is created. ex: 200 (= R$ 2.00)
     * status [string, default null]: current boleto status. ex: "registered" or "paid"
     * transactionIds [list of strings, default null]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * created [string, default null]: creation datetime for the transfer. ex: "2020-03-10 10:30:00.000"
     * updated [string, default null]: latest update datetime for the transfer. ex: "2020-03-10 10:30:00.000"
     */
    public Transfer(String id, int amount, String name, String taxId, String bankCode, String branchCode,
                    String accountNumber, String[] tags, int fee, String status, String created,
                    String updated, String[] transactionIds) {
        super(id);
        this.amount = amount;
        this.name = name;
        this.taxId = taxId;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
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
     * amount [integer]: amount in cents to be transferred. ex: 1234 (= R$ 12.34)
     * name [string]: receiver full name. ex: "Anthony Edward Stark"
     * taxId [string]: receiver tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * bankCode [string]: receiver 1 to 3 digits of the bank institution in Brazil. ex: "200" or "341"
     * branchCode [string]: receiver bank account branch. Use "-" in case there is a verifier digit. ex: "1357-9"
     * accountNumber [string]: Receiver Bank Account number. Use "-" before the verifier digit. ex: "876543-2"
     * <p>
     * Parameters (optional):
     * tags [list of strings]: list of strings for reference when searching for transfers. ex: ["employees", "monthly"]
     * <p>
     * Attributes (return-only):
     * id [string, default null]: unique id returned when Transfer is created. ex: "5656565656565656"
     * fee [integer, default null]: fee charged when transfer is created. ex: 200 (= R$ 2.00)
     * status [string, default null]: current boleto status. ex: "registered" or "paid"
     * transactionIds [list of strings, default null]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * created [string, default null]: creation datetime for the transfer. ex: "2020-03-10 10:30:00.000"
     * updated [string, default null]: latest update datetime for the transfer. ex: "2020-03-10 10:30:00.000"
     */
    public Transfer(Map<String, Object> data) {
        super(null);
        this.amount = (Integer) data.get("amount");
        this.name = (String) data.get("name");
        this.taxId = (String) data.get("taxId");
        this.bankCode = (String) data.get("bankCode");
        this.branchCode = (String) data.get("branchCode");
        this.accountNumber = (String) data.get("accountNumber");
        this.tags = (String[]) data.get("tags");
    }

    /**
     * Retrieve a specific Transfer
     * <p>
     * Receive a single Transfer object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * Transfer object with updated attributes
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
     * id [string]: object unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * Transfer object with updated attributes
     */
    public static Transfer get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects only before specified date. ex: "2020-03-10"
     * transactionIds [list of strings, default null]: list of transaction IDs linked to the desired transfers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * <p>
     * Return:
     * generator of Transfer objects with updated attributes
     */
    public static Generator<Transfer> query(Map<String, Object> params) throws Exception {
        return Transfer.query(params, null);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * generator of Transfer objects with updated attributes
     */
    public static Generator<Transfer> query(Project user) throws Exception {
        return Transfer.query(new HashMap<>(), user);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API
     * <p>
     * Return:
     * generator of Transfer objects with updated attributes
     */
    public static Generator<Transfer> query() throws Exception {
        return Transfer.query(new HashMap<>(), null);
    }

    /**
     * Retrieve Transfers
     * <p>
     * Receive a generator of Transfer objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects only before specified date. ex: "2020-03-10"
     * transactionIds [list of strings, default null]: list of transaction IDs linked to the desired transfers. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created", "-created", "updated" or "-updated".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * generator of Transfer objects with updated attributes
     */
    public static Generator<Transfer> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Create Transfers
     * <p>
     * Send a list of Transfer objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * transfers [list of Transfer objects]: list of Transfer objects to be created in the API
     * <p>
     * Return:
     * list of Transfer objects with updated attributes
     */
    public static List<Transfer> create(List<Transfer> transfers) throws Exception {
        return Transfer.create(transfers, null);
    }

    /**
     * Create Transfers
     * <p>
     * Send a list of Transfer objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * transfers [list of Transfer objects]: list of Transfer objects to be created in the API
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * list of Transfer objects with updated attributes
     */
    public static List<Transfer> create(List<Transfer> transfers, Project user) throws Exception {
        return Rest.post(data, transfers, user);
    }

    /**
     * Retrieve a specific Transfer pdf file
     * <p>
     * Receive a single Transfer pdf receipt file generated in the Stark Bank API by passing its id.
     * Only valid for transfers with "processing" and "success" status.
     * <p>
     * Parameters:
     * id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * Transfer pdf file
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
     * id [string]: object unique id. ex: "5656565656565656"
     * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * Transfer pdf file
     */
    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
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
         * id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * transfer [Transfer]: Transfer entity to which the log refers to.
         * errors [list of strings]: list of errors linked to this BoletoPayment event.
         * type [string]: type of the Transfer event which triggered the log creation. ex: "processing" or "success"
         * created [string]: creation datetime for the transfer. ex: "2020-03-10 10:30:00.000"
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
         * id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * Transfer Log object with updated attributes
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
         * id [string]: object unique id. ex: "5656565656565656"
         * user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * Transfer Log object with updated attributes
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * transferIds [list of strings, default null]: list of Transfer ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * list of Transfer Log objects with updated attributes
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * list of Transfer Log objects with updated attributes
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer Log objects previously created in the Stark Bank API
         * <p>
         * Return:
         * list of Transfer Log objects with updated attributes
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve Transfer Logs
         * <p>
         * Receive a generator of Transfer Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "success" or "failed"
         * transferIds [list of strings, default null]: list of Transfer ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * list of Transfer Log objects with updated attributes
         */
        public static Generator<Log> query(Map<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }
    }
}
