package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DictKey extends Resource {
    
    /**
     * DictKey object
     * <p>
     * DictKey represents a PIX key registered in Bacen's DICT system.
     * <p>
     * Parameters:
     * id [string, default null]: DICT key identifier. ex: "tony@starkbank.com", "012.345.678-90"
     * type [string, default null]: DICT key type. ex: "email", "cpf", "cnpj", "phone" or "evp"
     * name [string, default null]: key owner full name. ex: "Tony Stark"
     * taxId [string, default null]: key owner tax ID (CNPJ or masked CPF). ex: "***.345.678-**" or "20.018.183/0001-80"
     * ownerType [string, default null]: DICT key owner type. ex "naturalPerson" or "legalPerson"
     * bankName [string, default null]: bank name associated with the DICT key. ex: "Stark Bank"
     * ispb [string, default null]: bank ISPB associated with the DICT key. ex: "20018183"
     * branchCode [string, default null]: bank account branch code associated with the DICT key. ex: "9585"
     * accountNumber [string, default null]: bank account number associated with the DICT key. ex: "9828282578010513"
     * accountType [string, default null]: bank account type associated with the DICT key. ex: "checking", "saving", "salary" or "payment"
     * status [string, default null]: current DICT key status. ex: "created", "registered", "canceled" or "failed"
     * accountCreated [datetime.datetime, default null]: creation datetime of the bank account associated with the DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     * owned [datetime.datetime, default null]: datetime since when the current owner holds this DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     * created [datetime.datetime, default null]: creation datetime for the DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     */
    static ClassData data = new ClassData(DictKey.class, "DictKey");
    
    public String type;
    public String accountCreated;
    public String accountType;
    public String name;
    public String taxId;
    public String ownerType;
    public String bankName;
    public String ispb;
    public String branchCode;
    public String accountNumber;
    public String status;
    public String owned;
    public String created;
    
    /**
     * DictKey object
     * <p>
     * DictKey represents a PIX key registered in Bacen's DICT system.
     * <p>
     * Parameters:
     * @param id [string, default null]: DICT key identifier. ex: "tony@starkbank.com", "012.345.678-90"
     * @param type [string, default null]: DICT key type. ex: "email", "cpf", "cnpj", "phone" or "evp"
     * @param name [string, default null]: key owner full name. ex: "Tony Stark"
     * @param taxId [string, default null]: key owner tax ID (CNPJ or masked CPF). ex: "***.345.678-**" or "20.018.183/0001-80"
     * @param ownerType [string, default null]: DICT key owner type. ex "naturalPerson" or "legalPerson"
     * @param bankName [string, default null]: bank name associated with the DICT key. ex: "Stark Bank"
     * @param ispb [string, default null]: bank ISPB associated with the DICT key. ex: "20018183"
     * @param branchCode [string, default null]: bank account branch code associated with the DICT key. ex: "9585"
     * @param accountNumber [string, default null]: bank account number associated with the DICT key. ex: "9828282578010513"
     * @param accountType [string, default null]: bank account type associated with the DICT key. ex: "checking", "saving", "salary" or "payment"
     * @param status [string, default null]: current DICT key status. ex: "created", "registered", "canceled" or "failed"
     * @param accountCreated [datetime.datetime, default null]: creation datetime of the bank account associated with the DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     * @param owned [datetime.datetime, default null]: datetime since when the current owner holds this DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     * @param created [datetime.datetime, default null]: creation datetime for the DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     */
    public DictKey(String id, String type, String accountCreated, String accountType, String name, String taxId,
                   String ownerType, String bankName, String ispb, String branchCode, String accountNumber,
                   String status, String owned, String created) {
        super(id);
        this.type = type;
        this.accountCreated = accountCreated;
        this.accountType = accountType;
        this.name = name;
        this.taxId = taxId;
        this.ownerType = ownerType;
        this.bankName = bankName;
        this.ispb = ispb;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.status = status;
        this.owned = owned;
        this.created = created;        
    }
    
    /**
     * Retrieve a specific DictKey
     * <p>
     * Receive a single DictKey object by passing its id
     * <p>
     * Parameters:
     * @param id [string]: DictKey object unique id and PIX key itself. ex: "tony@starkbank.com", "722.461.430-04", "20.018.183/0001-80", "+5511988887777", "b6295ee1-f054-47d1-9e90-ee57b74f60d9"
     * Return:
     * @return DictKey object with updated attributes
     * @throws Exception error in the request 
     */
    public static DictKey get(String id) throws Exception {
        return DictKey.get(id, null);
    }

    /**
     * Retrieve a specific DictKey
     * <p>
     * Receive a single DictKey object by passing its id
     * <p>
     * Parameters:
     * @param id [string]: DictKey object unique id and PIX key itself. ex: "tony@starkbank.com", "722.461.430-04", "20.018.183/0001-80", "+5511988887777", "b6295ee1-f054-47d1-9e90-ee57b74f60d9"
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return DictKey object with updated attributes
     * @throws Exception error in the request 
     */
    public static DictKey get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve DictKeys
     * <p>
     * Receive a generator of DictKey objects associated with your Stark Bank Workspace.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * type [string, default null]: DictKey type. ex: "cpf", "cnpj", "phone", "email" or "evp"
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of DictKey objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DictKey> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve DictKeys
     * <p>
     * Receive a generator of DictKey objects associated with your Stark Bank Workspace.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * type [string, default null]: DictKey type. ex: "cpf", "cnpj", "phone", "email" or "evp"
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * <p>
     * Return:
     * @return generator of DictKey objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DictKey> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve DictKeys
     * <p>
     * Receive a generator of DictKey objects associated with your Stark Bank Workspace.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of DictKey objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DictKey> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve DictKeys
     * <p>
     * Receive a generator of DictKey objects associated with your Stark Bank Workspace.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * Return:
     * @return generator of DictKey objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DictKey> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<DictKey> keys;
        public String cursor;

        public Page(List<DictKey> keys, String cursor) {
            this.keys = keys;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged DictKeys
     * <p>
     * Receive a list of up to 100 DictKey objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * type [string, default null]: DictKey type. ex: "cpf", "cnpj", "phone", "email" or "evp"
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * <p>
     * Return:
     * @return DictKey.Page object:
     * DictKey.Page.keys: list of DictKey objects with updated attributes
     * DictKey.Page.cursor: cursor to retrieve the next page of DictKey objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged DictKeys
     * <p>
     * Receive a list of up to 100 DictKey objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DictKey.Page object:
     * DictKey.Page.keys: list of DictKey objects with updated attributes
     * DictKey.Page.cursor: cursor to retrieve the next page of DictKey objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged DictKeys
     * <p>
     * Receive a list of up to 100 DictKey objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return DictKey.Page object:
     * DictKey.Page.keys: list of DictKey objects with updated attributes
     * DictKey.Page.cursor: cursor to retrieve the next page of DictKey objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged DictKeys
     * <p>
     * Receive a list of up to 100 DictKey objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * type [string, default null]: DictKey type. ex: "cpf", "cnpj", "phone", "email" or "evp"
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DictKey.Page object:
     * DictKey.Page.keys: list of DictKey objects with updated attributes
     * DictKey.Page.cursor: cursor to retrieve the next page of DictKey objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<DictKey> keys = new ArrayList<>();
        for (SubResource key: page.entities) {
            keys.add((DictKey) key);
        }
        return new Page(keys, page.cursor);
    }
}
