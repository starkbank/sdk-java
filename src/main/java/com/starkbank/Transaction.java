package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Transaction extends Resource {
    static ClassData data = new ClassData(Transaction.class, "Transaction");

    public final int amount;
    public final String description;
    public final String externalId;
    public final String senderId;
    public final String receiverId;
    public final String[] tags;
    public final Integer fee;
    public final String created;
    public final String source;

    /**
     * Transaction object
     * <p>
     * A Transaction is a transfer of funds between workspaces inside Stark Bank.
     * Transactions created by the user are only for internal transactions.
     * Other operations (such as transfer or charge-payment) will automatically
     * create a transaction for the user which can be retrieved for the statement.
     * When you initialize a Transaction, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param amount [integer]: amount in cents to be transferred. ex: 1234 (= R$ 12.34)
     * @param description [string]: text to be displayed in the receiver and the sender statements (Min. 10 characters). ex: "funds redistribution"
     * @param externalId [string]: unique id, generated by user, to avoid duplicated transactions. ex: "transaction ABC 2020-03-30"
     * @param receiverId [string]: unique id of the receiving workspace. ex: "5656565656565656"
     * @param tags [list of strings]: list of strings for reference when searching transactions (may be empty). ex: ["abc", "test"]
     * Attributes (return-only):
     * @param senderId [string]: unique id of the sending workspace. ex: "5656565656565656"
     * @param source [string, default null]: unique locator of the related entity in the API reference
     * @param id [string, default null]: unique id returned when Transaction is created. ex: "7656565656565656"
     * @param fee [integer, default null]: fee charged when transfer is created. ex: 200 (= R$ 2.00)
     * @param created [string, default null]: creation datetime for the boleto. ex: "2020-03-10 10:30:00.000"
     */
    public Transaction(int amount, String description, String externalId, String receiverId, String senderId,
                       String[] tags, int fee, String created, String source, String id) {
        super(id);
        this.amount = amount;
        this.description = description;
        this.externalId = externalId;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.tags = tags;
        this.fee = fee;
        this.created = created;
        this.source = source;
    }

    /**
     * Transaction object
     * <p>
     * A Transaction is a transfer of funds between workspaces inside Stark Bank.
     * Transactions created by the user are only for internal transactions.
     * Other operations (such as transfer or charge-payment) will automatically
     * create a transaction for the user which can be retrieved for the statement.
     * When you initialize a Transaction, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the Transaction
     * amount [integer]: amount in cents to be transferred. ex: 1234 (= R$ 12.34)
     * description [string]: text to be displayed in the receiver and the sender statements (Min. 10 characters). ex: "funds redistribution"
     * externalId [string]: unique id, generated by user, to avoid duplicated transactions. ex: "transaction ABC 2020-03-30"
     * receivedId [string]: unique id of the receiving workspace. ex: "5656565656565656"
     * <p>
     * Parameters (optional):
     * senderId [string]: unique id of the sending workspace. ex: "5656565656565656"
     * tags [list of strings]: list of strings for reference when searching transactions (may be empty). ex: ["abc", "test"]
     * Attributes (return-only):
     * source [string, default null]: unique locator of the related entity in the API reference
     * id [string, default null]: unique id returned when Transaction is created. ex: "7656565656565656"
     * fee [integer, default null]: fee charged when transfer is created. ex: 200 (= R$ 2.00)
     * created [string, default null]: creation datetime for the boleto. ex: "2020-03-10 10:30:00.000"
     */
    public Transaction(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = (int) dataCopy.remove("amount");
        this.description = (String) dataCopy.remove("description");
        this.externalId = (String) dataCopy.remove("externalId");
        this.receiverId = (String) dataCopy.remove("receiverId");
        this.tags = (String[]) dataCopy.remove("tags");
        this.created = null;
        this.fee = null;
        this.senderId = null;
        this.source = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve a specific Transaction
     * <p>
     * Receive a single Transaction object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Transaction object with updated attributes
     * @throws Exception error in the request
     */
    public static Transaction get(String id) throws Exception {
        return Transaction.get(id, null);
    }

    /**
     * Retrieve a specific Transaction
     * <p>
     * Receive a single Transaction object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Transaction object with updated attributes
     * @throws Exception error in the request
     */
    public static Transaction get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Transactions
     * <p>
     * Receive a generator of Transaction objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * externalIds [list of strings, default null]: list of external ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of Transaction objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transaction> query(Map<String, Object> params) throws Exception {
        return Transaction.query(params, null);
    }

    /**
     * Retrieve Transactions
     * <p>
     * Receive a generator of Transaction objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Transaction objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transaction> query(Project user) throws Exception {
        return Transaction.query(new HashMap<>(), user);
    }

    /**
     * Retrieve Transactions
     * <p>
     * Receive a generator of Transaction objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of Transaction objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transaction> query() throws Exception {
        return Transaction.query(new HashMap<>(), null);
    }

    /**
     * Retrieve Transactions
     * <p>
     * Receive a generator of Transaction objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * externalIds [list of strings, default null]: list of external ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Project object, default null]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Transaction objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Transaction> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    /**
     * Create Transactions
     * <p>
     * Send a list of Transaction objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param transactions [list of Transaction objects or HashMaps]: list of Transaction objects to be created in the API
     * <p>
     * Return:
     * @return list of Transaction objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<Transaction> create(List<?> transactions) throws Exception {
        return Transaction.create(transactions, null);
    }

    /**
     * Create Transactions
     * <p>
     * Send a list of Transaction objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param transactions [list of Transaction objects or HashMaps]: list of Transaction objects to be created in the API
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of Transaction objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<Transaction> create(List<?> transactions, Project user) throws Exception {
        List<Transaction> transactionList = new ArrayList<>();
        for (Object transaction : transactions){
            if (transaction.getClass() == HashMap.class){
                transactionList.add(new Transaction((Map<String, Object>) transaction));
                continue;
            }
            if (transaction.getClass() == Transaction.class){
                transactionList.add((Transaction) transaction);
                continue;
            }
            throw new Exception("Unknown type \"" + transaction.getClass() + "\", use Transaction or HashMap");
        }
        return Rest.post(data, transactionList, user);
    }
}
