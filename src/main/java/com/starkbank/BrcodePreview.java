package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.HashMap;
import java.util.Map;

public final class BrcodePreview extends Resource {
    /**
    * BrcodePreview object
    * <p>
    * A BrcodePreview is used to get information from a BR Code you received to check the informations before paying it.
    * <p>
    * Parameters:
    * status [string]: Payment status. ex: "active", "paid", "canceled" or "unknown"
    * name [string]: Payment receiver name. ex: "Tony Stark"
    * taxId [string]: Payment receiver tax ID. ex: "012.345.678-90"
    * bankCode [string]: Payment receiver bank code. ex: "20018183"
    * branchCode [string]: Payment receiver branch code. ex: "0001"
    * accountNumber [string]: Payment receiver account number. ex: "1234567"
    * accountType [string]: Payment receiver account type. ex: "checking"
    * allowChange [bool]: If True, the payment is able to receive amounts that are different from the nominal one. ex: True or False
    * amount [integer]: Value in cents that this payment is expecting to receive. If 0, any value is accepted. ex: 123 (= R$1,23)
    * reconciliationId [string]: Reconciliation ID linked to this payment. ex: "txId", "payment-123"
    */
    static ClassData data = new ClassData(BrcodePreview.class, "BrcodePreview");

    public String status;
    public String name;
    public String taxId;
    public String bankCode;
    public String branchCode;
    public String accountNumber;
    public String accountType;
    public Boolean allowChange;
    public long amount;
    public String reconciliationId;

    /**
    * BrcodePreview object
    * <p>
    * A BrcodePreview is used to get information from a BR Code you received to check the informations before paying it.
    * <p>
    * Parameters:
    * @param status [string]: Payment status. ex: "active", "paid", "canceled" or "unknown"
    * @param name [string]: Payment receiver name. ex: "Tony Stark"
    * @param taxId [string]: Payment receiver tax ID. ex: "012.345.678-90"
    * @param bankCode [string]: Payment receiver bank code. ex: "20018183"
    * @param branchCode [string]: Payment receiver branch code. ex: "0001"
    * @param accountNumber [string]: Payment receiver account number. ex: "1234567"
    * @param accountType [string]: Payment receiver account type. ex: "checking"
    * @param allowChange [bool]: If True, the payment is able to receive amounts that are different from the nominal one. ex: True or False
    * @param amount [integer]: Value in cents that this payment is expecting to receive. If 0, any value is accepted. ex: 123 (= R$1,23)
    * @param reconciliationId [string]: Reconciliation ID linked to this payment. ex: "txId", "payment-123"
    */
    public BrcodePreview(String status, String name, String taxId, String bankCode, String branchCode,
        String accountNumber, String accountType, boolean allowChange, long amount, String reconciliationId
    ) {
        super(null);
        this.status = status;
        this.name = name;
        this.taxId = taxId;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.allowChange = allowChange;
        this.amount = amount;
        this.reconciliationId = reconciliationId;
    }

    /**
    * BrcodePreview object
    * <p>
    * A BrcodePreview is used to get information from a BR Code you received to check the informations before paying it.
    * <p>
    * @param data map of parameters for the creation of the BrcodePreview
    * Attributes (return-only):
    * status [string]: Payment status. ex: "active", "paid", "canceled" or "unknown"
    * name [string]: Payment receiver name. ex: "Tony Stark"
    * taxId [string]: Payment receiver tax ID. ex: "012.345.678-90"
    * bankCode [string]: Payment receiver bank code. ex: "20018183"
    * branchCode [string]: Payment receiver branch code. ex: "0001"
    * accountNumber [string]: Payment receiver account number. ex: "1234567"
    * accountType [string]: Payment receiver account type. ex: "checking"
    * allowChange [bool]: If True, the payment is able to receive amounts that are different from the nominal one. ex: True or False
    * amount [integer]: Value in cents that this payment is expecting to receive. If 0, any value is accepted. ex: 123 (= R$1,23)
    * reconciliationId [string]: Reconciliation ID linked to this payment. ex: "txId", "payment-123"
    * @throws Exception error in the request
    */
    public BrcodePreview(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.status = (String) dataCopy.remove("status");
        this.name = (String) dataCopy.remove("name");
        this.taxId = (String) dataCopy.remove("taxId");
        this.bankCode = (String) dataCopy.remove("bankCode");
        this.branchCode = (String) dataCopy.remove("branchCode");
        this.accountNumber = (String) dataCopy.remove("accountNumber");
        this.accountType = (String) dataCopy.remove("accountType");
        this.allowChange = (boolean) dataCopy.remove("allowChange");
        this.amount = (long) dataCopy.remove("amount");
        this.reconciliationId = (String) dataCopy.remove("reconciliationId");
        
        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve BrcodePreviews
     * <p>
     * Receive a generator of BrcodePreview objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters
     * brcodes [list of strings]: List of BR Codes to preview. ex: ["00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A"]
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of BrcodePreview objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<BrcodePreview> query(Map<String, Object> params, Project user) throws Exception {
        return Rest.getSimpleList(data, params, user);
    }

    /**
     * Retrieve BrcodePreviews
     * <p>
     * Receive a generator of BrcodePreview objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters
     * brcodes [list of strings]: List of BR Codes to preview. ex: ["00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A"]
     * <p>
     * Return:
     * @return generator of BrcodePreview objects with updated attributes
     * @throws Exception error in the request 
     */
    public static Generator<BrcodePreview> query(Map<String, Object> params) throws Exception {
        return Rest.getSimpleList(data, params, null);
    }
}