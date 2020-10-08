package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.HashMap;
import java.util.Map;

public final class Deposit extends Resource {
    /**
     * Deposit object
     * <p>
     * Deposits represent passive cash-in received by your account from external transfers
     * <p>
     * Parameters:
     * id [string]: unique id associated with a Deposit when it is created. ex: "5656565656565656"
     * name [string]: payer name. ex: "Iron Bank S.A."
     * taxId [string]: payer tax ID (CPF or CNPJ). ex: "012.345.678-90" or "20.018.183/0001-80"
     * bankCode [string]: payer bank code in Brazil. ex: "20018183" or "341"
     * branchCode [string]: payer bank account branch. ex: "1357-9"
     * accountNumber [string]: payer bank account number. ex: "876543-2"
     * amount [long]: Deposit value in cents. ex: 1234 (= R$ 12.34)
     * type [string]: type of settlement that originated the deposit. ex: "pix" or "ted"
     * status [string]: current Deposit status. ex: "created"
     * tags [list of strings]: list of strings that are tagging the deposit. ex: ["reconciliationId", "txId"]
     * fee [integer]: fee charged when a deposit is created. ex: 50 (= R$ 0.50)
     * transactionIds [list of strings]: ledger transaction ids linked to this deposit (if there are more than one, all but first are reversals). ex: ["19827356981273"]
     * created [string]: creation datetime for the Deposit. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: latest update datetime for the Deposit. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(Deposit.class, "Deposit");
    
    public String name;
    public String taxId;
    public String bankCode;
    public String branchCode;
    public String accountNumber;
    public long amount;
    public String type;
    public String status;
    public String[] tags;
    public Integer fee;
    public String[] transactionIds;
    public String created;
    public String updated;
    
    /**
     * Deposit object
     * <p>
     * Deposits represent passive cash-in received by your account from external transfers
     * <p>
     * Parameters:
     * @param id [string]: unique id associated with a Deposit when it is created. ex: "5656565656565656"
     * @param name [string]: payer name. ex: "Iron Bank S.A."
     * @param taxId [string]: payer tax ID (CPF or CNPJ). ex: "012.345.678-90" or "20.018.183/0001-80"
     * @param bankCode [string]: payer bank code in Brazil. ex: "20018183" or "341"
     * @param branchCode [string]: payer bank account branch. ex: "1357-9"
     * @param accountNumber [string]: payer bank account number. ex: "876543-2"
     * @param amount [long]: Deposit value in cents. ex: 1234 (= R$ 12.34)
     * @param type [string]: type of settlement that originated the deposit. ex: "pix" or "ted"
     * @param status [string]: current Deposit status. ex: "created"
     * @param tags [list of strings]: list of strings that are tagging the deposit. ex: ["reconciliationId", "taxId"]
     * @param fee [integer]: fee charged when a deposit is created. ex: 50 (= R$ 0.50)
     * @param transactionIds [list of strings]: ledger transaction ids linked to this deposit (if there are more than one, all but first are reversals). ex: ["19827356981273"]
     * @param created [string]: creation datetime for the Deposit. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string]: latest update datetime for the Deposit. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public Deposit(String id, String name, String taxId, String bankCode, String branchCode, String accountNumber,
                    long amount, String type, String status, String[] tags, Integer fee, String[] transactionIds,
                    String created, String updated
    ) {
        super(id);
        this.name = name;
        this.taxId = taxId;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.status = status;
        this.tags = tags;
        this.fee = fee;
        this.transactionIds = transactionIds;
        this.created = created;
        this.updated = updated;
    }
    
    /**
     * Retrieve a specific Deposit
     * <p>
     * Receive a single Deposit object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Return:
     * @return Deposit object with updated attributes
     * @throws Exception error in the request 
     */
    public static Deposit get(String id) throws Exception {
        return Deposit.get(id, null);
    }

    /**
     * Retrieve a specific Deposit
     * <p>
     * Receive a single Deposit object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return Deposit object with updated attributes
     * @throws Exception error in the request 
     */
    public static Deposit get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Deposits
     * <p>
     * Receive a generator of Deposit objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created" or "-created".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of Deposit objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Deposit> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Retrieve Deposits
     * <p>
     * Receive a generator of Deposit objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "paid" or "registered"
     * sort [string, default "-created"]: sort order considered in response. Valid options are "created" or "-created".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of Deposit objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Deposit> query(Map<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    /**
     * Retrieve Deposits
     * <p>
     * Receive a generator of Deposit objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of Deposit objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Deposit> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    /**
     * Retrieve Deposits
     * <p>
     * Receive a generator of Deposit objects previously created in the Stark Bank API
     * Return:
     * @return generator of Deposit objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<Deposit> query() throws Exception {
        return Rest.getList(data, new HashMap<>(), null);
    }
    
    
    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "DepositLog");

        public String created;
        public String type;
        public String[] errors;
        public Deposit deposit;

        /**
         * Deposit Log object
         * <p>
         * Every time a Deposit entity is updated, a corresponding Deposit Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the Deposit.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param deposit [Deposit]: Deposit entity to which the log refers to.
         * @param errors [list of strings]: list of errors linked to this Deposit event
         * @param type [string]: type of the Deposit event which triggered the log creation. ex: "created" or "credited"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, String[] errors, Deposit deposit, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.deposit = deposit;
        }

        /**
         * Retrieve a specific Deposit Log
         * <p>
         * Receive a single Deposit Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return Deposit Log object with updated attributes
         * @throws Exception error in the request 
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific Deposit Log
         * <p>
         * Receive a single Deposit Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Deposit Log object with updated attributes
         * @throws Exception error in the request 
         */
        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve Deposit Logs
         * <p>
         * Receive a generator of Deposit Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "credited"
         * depositIds [list of strings, default null]: list of Deposit ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of Deposit Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve Deposit Logs
         * <p>
         * Receive a generator of Deposit Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
         * <p>
         * Return:
         * @return list of Deposit Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Project user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve Deposit Logs
         * <p>
         * Receive a generator of Deposit Log objects previously created in the Stark Bank API
         * <p>
         * Return:
         * @return list of Deposit Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve Deposit Logs
         * <p>
         * Receive a generator of Deposit Log objects previously created in the Stark Bank API
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created" or "credited"
         * depositIds [list of strings, default null]: list of Deposit ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
         * <p>
         * Return:
         * @return list of Deposit Log objects with updated attributes
         * @throws Exception error in the request 
         */
        public static Generator<Log> query(Map<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }
    }    
}