package com.starkbank;

import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

public class DictKey extends Resource {
    
    /**
     * DictKey object
     * <p>
     * DictKey represents a PIX key registered in Bacen's DICT system.
     * <p>
     * Parameters:
     * type [string, default null]: DICT key type. ex: "email", "cpf", "cnpj", "phone" or "evp"
     * name [string, default null]: key owner full name. ex: "Tony Stark"
     * taxId [string, default null]: key owner tax ID (CNPJ or masked CPF). ex: "***.345.678-**" or "20.018.183/0001-80"
     * ownerType [string, default null]: DICT key owner type. ex "naturalPerson" or "legalPerson"
     * ispb [string, default null]: bank ISPB associated with the DICT key. ex: "20018183"
     * branchCode [string, default null]: bank account branch code associated with the DICT key. ex: "9585"
     * accountNumber [string, default null]: bank account number associated with the DICT key. ex: "9828282578010513"
     * accountType [string, default null]: bank account type associated with the DICT key. ex: "checking", "saving" e "salary"
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
     * @param type [string, default null]: DICT key type. ex: "email", "cpf", "cnpj", "phone" or "evp"
     * @param name [string, default null]: key owner full name. ex: "Tony Stark"
     * @param taxId [string, default null]: key owner tax ID (CNPJ or masked CPF). ex: "***.345.678-**" or "20.018.183/0001-80"
     * @param ownerType [string, default null]: DICT key owner type. ex "naturalPerson" or "legalPerson"
     * @param ispb [string, default null]: bank ISPB associated with the DICT key. ex: "20018183"
     * @param branchCode [string, default null]: bank account branch code associated with the DICT key. ex: "9585"
     * @param accountNumber [string, default null]: bank account number associated with the DICT key. ex: "9828282578010513"
     * @param accountType [string, default null]: bank account type associated with the DICT key. ex: "checking", "saving" e "salary"
     * @param status [string, default null]: current DICT key status. ex: "created", "registered", "canceled" or "failed"
     * @param accountCreated [datetime.datetime, default null]: creation datetime of the bank account associated with the DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     * @param owned [datetime.datetime, default null]: datetime since when the current owner holds this DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     * @param created [datetime.datetime, default null]: creation datetime for the DICT key. ex: "2020-11-05T14:55:08.812665+00:00"
     */
    public DictKey(String id, String type, String accountCreated, String accountType, String name,
                    String taxId, String ownerType, String ispb, String branchCode, String accountNumber,
                    String status, String owned, String created
    ) {
        super(id);
        this.type = type;
        this.accountCreated = accountCreated;
        this.accountType = accountType;
        this.name = name;
        this.taxId = taxId;
        this.ownerType = ownerType;
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
     * @param user [Project object]: Project object. Not necessary if StarkBank.User.defaultUser was set before function call
     * Return:
     * @return DictKey object with updated attributes
     * @throws Exception error in the request 
     */
    public static DictKey get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }
}
