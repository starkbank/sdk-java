package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;
import com.starkcore.utils.SubResource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class CorporateTransaction extends Resource {
    /**
     * CorporateTransaction object
     * <p>
     * The CorporateTransaction objects created in your Workspace to represent each balance shift.
     * <p>
     * Parameters:
     * id [string]: unique id returned when CorporateTransaction is created. ex: "5656565656565656"
     * amount [Long]: CorporateTransaction value in cents. ex: 1234 (= R$ 12.34)
     * balance [Long]: balance amount of the workspace at the instant of the Transaction in cents. ex: 200 (= R$ 2.00)
     * description [string]: CorporateTransaction description. ex: "Buying food"
     * source [string]: source of the transaction. ex: "corporate-purchase/5656565656565656"
     * tags [list of strings]: list of strings inherited from the source resource. ex: ["tony", "stark"]
     * created [string]: creation datetime for the CorporateTransaction. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(CorporateTransaction.class, "CorporateTransaction");

    public Long amount;
    public Long balance;
    public String description;
    public String source;
    public String[] tags;
    public String created;

    /**
     * CorporateTransaction object
     * <p>
     * The CorporateTransaction objects created in your Workspace to represent each balance shift.
     * <p>
     * Parameters:
     * @param id [string]: unique id returned when CorporateTransaction is created. ex: "5656565656565656"
     * @param amount [Long]: CorporateTransaction value in cents. ex: 1234 (= R$ 12.34)
     * @param balance [Long]: balance amount of the workspace at the instant of the Transaction in cents. ex: 200 (= R$ 2.00)
     * @param description [string]: CorporateTransaction description. ex: "Buying food"
     * @param source [string]: source of the transaction. ex: "corporate-purchase/5656565656565656"
     * @param tags [list of strings]: list of strings inherited from the source resource. ex: ["tony", "stark"]
     * @param created [string]: creation datetime for the CorporateTransaction. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public CorporateTransaction(String id, Long amount, Long balance, String description,
                              String source, String[] tags, String created
    ) {
        super(id);
        this.amount = amount;
        this.balance = balance;
        this.description = description;
        this.source = source;
        this.tags = tags;
        this.created = created;
    }

    /**
     * CorporateTransaction object
     * <p>
     * The CorporateTransaction objects created in your Workspace to represent each balance shift.
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when CorporateTransaction is created. ex: "5656565656565656"
     * amount [Long]: CorporateTransaction value in cents. ex: 1234 (= R$ 12.34)
     * balance [Long]: balance amount of the workspace at the instant of the Transaction in cents. ex: 200 (= R$ 2.00)
     * description [string]: CorporateTransaction description. ex: "Buying food"
     * source [string]: source of the transaction. ex: "corporate-purchase/5656565656565656"
     * tags [list of strings]: list of strings inherited from the source resource. ex: ["tony", "stark"]
     * created [string]: creation datetime for the CorporateTransaction. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public CorporateTransaction(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = null;
        this.balance = null;
        this.description = null;
        this.source = null;
        this.tags = null;
        this.created = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve a specific CorporateTransaction
     * <p>
     * Receive a single CorporateTransaction object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateTransaction object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateTransaction get(String id, User user) throws Exception{
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific CorporateTransaction
     * <p>
     * Receive a single CorporateTransaction object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return CorporateTransaction object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateTransaction get(String id) throws Exception{
        return Rest.getId(data, id, null);
    }

    /**
     * Retrieve a specific CorporateTransaction
     * <p>
     * Receive a single CorporateTransaction object previously created in the Stark Bank API by its id
     * <p>
     * Return:
     * @return CorporateTransaction object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateTransaction get() throws Exception{
        return Rest.getId(data, null, null);
    }

    /**
     * Retrieve CorporateTransactions
     * <p>
     * Receive a generator of CorporateTransaction objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * externalIds [list of strings, default []]: external IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default ""]: filter for status of retrieved objects. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * ids [list of strings, default []]: transaction IDs
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateTransaction objects with updated attributes
     * @throws Exception error in the transaction
     */
    public static Generator<CorporateTransaction> query(Map<String, Object> params, User user) throws Exception{
        return Rest.getStream(data, params, user);
    }
    /**
     * Retrieve CorporateTransactions
     * <p>
     * Receive a generator of CorporateTransaction objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * externalIds [list of strings, default []]: external IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default ""]: filter for status of retrieved objects. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * ids [list of strings, default []]: transaction IDs
     * <p>
     * Return:
     * @return generator of CorporateTransaction objects with updated attributes
     * @throws Exception error in the transaction
     */
    public static Generator<CorporateTransaction> query(Map<String, Object> params) throws Exception{
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve CorporateTransactions
     * <p>
     * Receive a generator of CorporateTransaction objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateTransaction objects with updated attributes
     * @throws Exception error in the transaction
     */
    public static Generator<CorporateTransaction> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve CorporateTransactions
     * <p>
     * Receive a generator of CorporateTransaction objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of CorporateTransaction objects with updated attributes
     * @throws Exception error in the transaction
     */
    public static Generator<CorporateTransaction> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<CorporateTransaction> corporateTransactions;
        public String cursor;

        public Page(List<CorporateTransaction> corporateTransactions, String cursor) {
            this.corporateTransactions = corporateTransactions;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged CorporateTransactions
     * <p>
     * Receive a list of up to 100 CorporateTransaction objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page transactions.
     * <p>
     * Return:
     * CorporateTransaction.Page.transactions: list of CorporateTransaction objects with updated attributes
     * CorporateTransaction.Page.cursor: cursor to retrieve the next page of CorporateTransaction objects
     * @throws Exception error in the request
     */
    public static CorporateTransaction.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged CorporateTransactions
     * <p>
     * Receive a list of up to 100 CorporateTransaction objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page transactions.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * externalIds [list of strings, default []]: external IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default ""]: filter for status of retrieved objects. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * ids [list of strings, default []]: transaction IDs
     * <p>
     * Return:
     * CorporateTransaction.Page.transactions: list of CorporateTransaction objects with updated attributes
     * CorporateTransaction.Page.cursor: cursor to retrieve the next page of CorporateTransaction objects
     * @throws Exception error in the request
     */
    public static CorporateTransaction.Page page(Map<String , Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged CorporateTransactions
     * <p>
     * Receive a list of up to 100 CorporateTransaction objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page transactions.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * CorporateTransaction.Page.transactions: list of CorporateTransaction objects with updated attributes
     * CorporateTransaction.Page.cursor: cursor to retrieve the next page of CorporateTransaction objects
     * @throws Exception error in the request
     */
    public static CorporateTransaction.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged CorporateTransactions
     * <p>
     * Receive a list of up to 100 CorporateTransaction objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page transactions.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * externalIds [list of strings, default []]: external IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default ""]: filter for status of retrieved objects. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * ids [list of strings, default []]: transaction IDs
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * CorporateTransaction.Page.transactions: list of CorporateTransaction objects with updated attributes
     * CorporateTransaction.Page.cursor: cursor to retrieve the next page of CorporateTransaction objects
     * @throws Exception error in the request
     */
    public static CorporateTransaction.Page page(Map<String , Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<CorporateTransaction> corporateTransactions = new ArrayList<>();
        for (SubResource corporateTransaction: page.entities) {
            corporateTransactions.add((CorporateTransaction) corporateTransaction);
        }
        return new Page(corporateTransactions, page.cursor);

    }
}
