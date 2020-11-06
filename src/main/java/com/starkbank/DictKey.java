package com.starkbank;

import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

public class DictKey extends Resource {
    
    /**
     * DictKey object
     * <p>
     * @description DictKey represents a PIX key registered in Bacen's DICT system.
     * <p>
     * Parameters:
     * id [string]: DictKey object unique id and PIX key itself. ex: "tony@starkbank.com", "722.461.430-04", "20.018.183/0001-80", "+5511988887777", "b6295ee1-f054-47d1-9e90-ee57b74f60d9"
     * type [string, default null]: PIX key type. ex: "email", "cpf", "cnpj", "phone" or "evp"
     * accountCreated [string, default null]: creation datetime of the bank account associated with the PIX key. ex: "2020-11-05T14:55:08.812665+00:00"
     * accountType [string, default null]: bank account type associated with the PIX key. ex: "checking", "saving" e "salary"
     * name [string, default null]: account owner full name. ex: "Tony Stark"
     * taxId [string, default null]: tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * ownerType [string, default null]: PIX key owner type. ex "naturalPerson" or "legalPerson"
     * ispb [string, default null]: ISPB code used for transactions. ex: "20018183"
     * branchCode [string, default null]: bank account branch code associated with the PIX key. ex: "9585"
     * accountNumber [string, default null]: bank account number associated with the PIX key. ex: "9828282578010513"
     * status [string, default null]: current PIX key status. ex: "created", "registered", "canceled" or "failed"
     * owned [string, default null]: datetime since when the current owner hold this PIX key. ex : "2020-11-05T14:55:08.812665+00:00"     
     * created [string, default null]: creation datetime for the PIX key. ex: "2020-03-10 10:30:00.000"
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
     * @description DictKey represents a PIX key registered in Bacen's DICT system.
     * <p>
     * Parameters:
     * @param id [string]: DictKey object unique id and PIX key itself. ex: "tony@starkbank.com", "722.461.430-04", "20.018.183/0001-80", "+5511988887777", "b6295ee1-f054-47d1-9e90-ee57b74f60d9"
     * @param type [string, default null]: PIX key type. ex: "email", "cpf", "cnpj", "phone" or "evp"
     * @param accountCreated [string, default null]: creation datetime of the bank account associated with the PIX key. ex: "2020-11-05T14:55:08.812665+00:00"
     * @param accountType [string, default null]: bank account type associated with the PIX key. ex: "checking", "saving" e "salary"
     * @param name [string, default null]: account owner full name. ex: "Tony Stark"
     * @param taxId [string, default null]: tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param ownerType [string, default null]: PIX key owner type. ex "naturalPerson" or "legalPerson"
     * @param ispb [string, default null]: ISPB code used for transactions. ex: "20018183"
     * @param branchCode [string, default null]: bank account branch code associated with the PIX key. ex: "9585"
     * @param accountNumber [string, default null]: bank account number associated with the PIX key. ex: "9828282578010513"
     * @param status [string, default null]: current PIX key status. ex: "created", "registered", "canceled" or "failed"
     * @param owned [string, default null]: datetime since when the current owner hold this PIX key. ex : "2020-11-05T14:55:08.812665+00:00"     
     * @param created [string, default null]: creation datetime for the PIX key. ex: "2020-03-10 10:30:00.000"
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
     * @param @param id [string]: DictKey object unique id and PIX key itself. ex: "tony@starkbank.com", "722.461.430-04", "20.018.183/0001-80", "+5511988887777", "b6295ee1-f054-47d1-9e90-ee57b74f60d9"
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
     * @param @param id [string]: DictKey object unique id and PIX key itself. ex: "tony@starkbank.com", "722.461.430-04", "20.018.183/0001-80", "+5511988887777", "b6295ee1-f054-47d1-9e90-ee57b74f60d9"
     * Parameters:
     * @param user [Project object]: Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return DictKey object with updated attributes
     * @throws Exception error in the request 
     */
    public static DictKey get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }
}
