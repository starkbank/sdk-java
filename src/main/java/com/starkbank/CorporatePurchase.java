package com.starkbank;


import com.google.gson.Gson;
import com.starkbank.utils.Rest;
import com.starkbank.utils.Parse;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;
import com.starkcore.utils.SubResource;
import com.starkbank.error.ErrorElement;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class CorporatePurchase extends Resource {
    /**
     * CorporatePurchase object
     * <p>
     * Displays the CorporatePurchase objects created in your Workspace.
     * <p>
     * Parameters:
     * id [string]: unique id returned when CorporatePurchase is created. ex: "5656565656565656"
     * holderId [string]: card holder unique id. ex: "5656565656565656"
     * holderName [string]: card holder name. ex: "Tony Stark"
     * centerId [string]: target cost center ID. ex: "5656565656565656"
     * cardId [string]: unique id returned when CorporateCard is created. ex: "5656565656565656"
     * cardEnding [string]: last 4 digits of the card number. ex: "1234"
     * description [string]: purchase descriptions. ex: "my_description"
     * amount [Long]: CorporatePurchase value in cents. Minimum = 0. ex: 1234 (= R$ 12.34)
     * tax [Long]: IOF amount taxed for international purchases. ex: 1234 (= R$ 12.34)
     * issuerAmount [Long]: issuer amount. ex: 1234 (= R$ 12.34)
     * issuerCurrencyCode [string]: issuer currency code. ex: "USD"
     * issuerCurrencySymbol [string]: issuer currency symbol. ex: "$"
     * merchantAmount [Long]: merchant amount. ex: 1234 (= R$ 12.34)
     * merchantCurrencyCode [string]: merchant currency code. ex: "USD"
     * merchantCurrencySymbol [string]: merchant currency symbol. ex: "$"
     * merchantCategoryCode [string]: merchant category code. ex: "fastFoodRestaurants"
     * merchantCategoryType [string]: merchant category type. ex: "health"
     * merchantCountryCode [string]: merchant country code. ex: "USA"
     * merchantName [string]: merchant name. ex: "Google Cloud Platform"
     * merchantDisplayName [string]: merchant name. ex: "Google Cloud Platform"
     * merchantDisplayUrl [string]: public merchant icon (png image). ex: "https://sandbox.api.starkbank.com/v2/corporate-icon/merchant/ifood.png"
     * merchantFee [Long]: fee charged by the merchant to cover specific costs, such as ATM withdrawal logistics, etc. ex: 200 (= R$ 2.00)
     * methodCode [string]: method code. Options: "chip", "token", "server", "manual", "magstripe" or "contactless"
     * tags [list of strings]: list of strings for tagging returned by the sub-issuer during the authorization. ex: ["travel", "food"]
     * corporateTransactionIds [list of strings]: list of ledger transaction ids linked to this Purchase
     * status [string]: current CorporatePurchase status. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * updated [string]: latest update datetime for the CorporatePurchase. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporatePurchase. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(CorporatePurchase.class, "CorporatePurchase");

    public String holderId;
    public String holderName;
    public String centerId;
    public String cardId;
    public String cardEnding;
    public String description;
    public Long amount;
    public Long tax;
    public Long issuerAmount;
    public String issuerCurrencyCode;
    public String issuerCurrencySymbol;
    public Long merchantAmount;
    public String merchantCurrencyCode;
    public String merchantCurrencySymbol;
    public String merchantCategoryCode;
    public String merchantCategoryType;
    public String merchantCountryCode;
    public String merchantName;
    public String merchantDisplayName;
    public String merchantDisplayUrl;
    public Long merchantFee;
    public String methodCode;
    public String[] tags;
    public String[] corporateTransactionIds;
    public String status;
    public String updated;
    public String created;


    /**
     * CorporatePurchase object
     * <p>
     * Displays the CorporatePurchase objects created in your Workspace.
     * <p>
     * Parameters:
     * @param id [string]: unique id returned when CorporatePurchase is created. ex: "5656565656565656"
     * @param holderId [string]: card holder unique id. ex: "5656565656565656"
     * @param holderName [string]: card holder name. ex: "Tony Stark"
     * @param centerId [string]: target cost center ID. ex: "5656565656565656"
     * @param cardId [string]: unique id returned when CorporateCard is created. ex: "5656565656565656"
     * @param cardEnding [string]: last 4 digits of the card number. ex: "1234"
     * @param description [string]: purchase descriptions. ex: "my_description"
     * @param amount [Long]: CorporatePurchase value in cents. Minimum = 0. ex: 1234 (= R$ 12.34)
     * @param tax [Long]: IOF amount taxed for international purchases. ex: 1234 (= R$ 12.34)
     * @param issuerAmount [Long]: issuer amount. ex: 1234 (= R$ 12.34)
     * @param issuerCurrencyCode [string]: issuer currency code. ex: "USD"
     * @param issuerCurrencySymbol [string]: issuer currency symbol. ex: "$"
     * @param merchantAmount [Long]: merchant amount. ex: 1234 (= R$ 12.34)
     * @param merchantCurrencyCode [string]: merchant currency code. ex: "USD"
     * @param merchantCurrencySymbol [string]: merchant currency symbol. ex: "$"
     * @param merchantCategoryCode [string]: merchant category code. ex: "fastFoodRestaurants"
     * @param merchantCategoryType [string]: merchant category type. ex: "health"
     * @param merchantCountryCode [string]: merchant country code. ex: "USA"
     * @param merchantName [string]: merchant name. ex: "Google Cloud Platform"
     * @param merchantDisplayName [string]: merchant name. ex: "Google Cloud Platform"
     * @param merchantDisplayUrl [string]: public merchant icon (png image). ex: "https://sandbox.api.starkbank.com/v2/corporate-icon/merchant/ifood.png"
     * @param merchantFee [Long]: fee charged by the merchant to cover specific costs, such as ATM withdrawal logistics, etc. ex: 200 (= R$ 2.00)
     * @param methodCode [string]: method code. Options: "chip", "token", "server", "manual", "magstripe" or "contactless"
     * @param tags [list of strings]: list of strings for tagging returned by the sub-issuer during the authorization. ex: ["travel", "food"]
     * @param corporateTransactionIds [list of strings]: list of ledger transaction ids linked to this Purchase
     * @param status [string]: current CorporatePurchase status. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * @param created [string]: creation datetime for the CorporatePurchase. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string]: latest update datetime for the CorporatePurchase. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public CorporatePurchase(String id, String holderId, String holderName, String centerId, String cardId, String cardEnding,
                             String description, Long amount, Long tax, Long issuerAmount, String issuerCurrencyCode,
                             String issuerCurrencySymbol, Long merchantAmount, String merchantCurrencyCode,
                             String merchantCurrencySymbol, String merchantCategoryCode, String merchantCategoryType,
                             String merchantCountryCode, String merchantName, String merchantDisplayName,
                             String merchantDisplayUrl, Long merchantFee, String methodCode, String[] tags,
                             String[] corporateTransactionIds, String status, String updated, String created
    ) {
        super(id);
        this.holderId = holderId;
        this.holderName = holderName;
        this.centerId = centerId;
        this.cardId = cardId;
        this.cardEnding = cardEnding;
        this.description = description;
        this.amount = amount;
        this.tax = tax;
        this.issuerAmount = issuerAmount;
        this.issuerCurrencyCode = issuerCurrencyCode;
        this.issuerCurrencySymbol = issuerCurrencySymbol;
        this.merchantAmount = merchantAmount;
        this.merchantCurrencyCode = merchantCurrencyCode;
        this.merchantCurrencySymbol = merchantCurrencySymbol;
        this.merchantCategoryCode = merchantCategoryCode;
        this.merchantCategoryType = merchantCategoryType;
        this.merchantCountryCode = merchantCountryCode;
        this.merchantName = merchantName;
        this.merchantDisplayName = merchantDisplayName;
        this.merchantDisplayUrl = merchantDisplayUrl;
        this.merchantFee = merchantFee;
        this.methodCode = methodCode;
        this.tags = tags;
        this.corporateTransactionIds = corporateTransactionIds;
        this.status = status;
        this.updated = updated;
        this.created = created;
    }

    /**
     * CorporatePurchase object
     * <p>
     * Displays the CorporatePurchase objects created in your Workspace.
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when CorporatePurchase is created. ex: "5656565656565656"
     * holderId [string]: card holder unique id. ex: "5656565656565656"
     * holderName [string]: card holder name. ex: "Tony Stark"
     * centerId [string]: target cost center ID. ex: "5656565656565656"
     * cardId [string]: unique id returned when CorporateCard is created. ex: "5656565656565656"
     * cardEnding [string]: last 4 digits of the card number. ex: "1234"
     * description [string]: purchase descriptions. ex: "my_description"
     * amount [Long]: CorporatePurchase value in cents. Minimum = 0. ex: 1234 (= R$ 12.34)
     * tax [Long]: IOF amount taxed for international purchases. ex: 1234 (= R$ 12.34)
     * issuerAmount [Long]: issuer amount. ex: 1234 (= R$ 12.34)
     * issuerCurrencyCode [string]: issuer currency code. ex: "USD"
     * issuerCurrencySymbol [string]: issuer currency symbol. ex: "$"
     * merchantAmount [Long]: merchant amount. ex: 1234 (= R$ 12.34)
     * merchantCurrencyCode [string]: merchant currency code. ex: "USD"
     * merchantCurrencySymbol [string]: merchant currency symbol. ex: "$"
     * merchantCategoryCode [string]: merchant category code. ex: "fastFoodRestaurants"
     * merchantCategoryType [string]: merchant category type. ex: "health"
     * merchantCountryCode [string]: merchant country code. ex: "USA"
     * merchantName [string]: merchant name. ex: "Google Cloud Platform"
     * merchantDisplayName [string]: merchant name. ex: "Google Cloud Platform"
     * merchantDisplayUrl [string]: public merchant icon (png image). ex: "https://sandbox.api.starkbank.com/v2/corporate-icon/merchant/ifood.png"
     * merchantFee [Long]: fee charged by the merchant to cover specific costs, such as ATM withdrawal logistics, etc. ex: 200 (= R$ 2.00)
     * methodCode [string]: method code. Options: "chip", "token", "server", "manual", "magstripe" or "contactless"
     * tags [list of strings]: list of strings for tagging returned by the sub-issuer during the authorization. ex: ["travel", "food"]
     * corporateTransactionIds [list of strings]: list of ledger transaction ids linked to this Purchase
     * status [string]: current CorporatePurchase status. ex: "approved", "canceled", "denied", "confirmed" or "voided"
     * updated [string]: latest update datetime for the CorporatePurchase. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporatePurchase. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public CorporatePurchase(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.holderId = null;
        this.holderName = null;
        this.centerId = null;
        this.cardId = null;
        this.cardEnding = null;
        this.description = null;
        this.amount = null;
        this.tax = null;
        this.issuerAmount = null;
        this.issuerCurrencyCode = null;
        this.issuerCurrencySymbol = null;
        this.merchantAmount = null;
        this.merchantCurrencyCode = null;
        this.merchantCurrencySymbol = null;
        this.merchantCategoryCode = null;
        this.merchantCategoryType = null;
        this.merchantCountryCode = null;
        this.merchantName = null;
        this.merchantDisplayName = null;
        this.merchantDisplayUrl = null;
        this.merchantFee = null;
        this.methodCode = null;
        this.tags = null;
        this.corporateTransactionIds = null;
        this.status = null;
        this.updated = null;
        this.created = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public CorporatePurchase(){
        super(null);
    }

    /**
     * Retrieve a specific CorporatePurchase
     * <p>
     * Receive a single CorporatePurchase object previously created in the Stark Bank API by its id
     * <p>
     * Parameters (required):
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporatePurchase object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporatePurchase get(String id, User user) throws Exception{
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific CorporatePurchase
     * <p>
     * Receive a single CorporatePurchase object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return CorporatePurchase object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporatePurchase get(String id) throws Exception{
        return Rest.getId(data, id, null);
    }

    /**
     * Retrieve CorporatePurchase
     * <p>
     * Receive a generator of CorporatePurchase objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * merchantCategoryTypes [list of strings, default null]: merchant category type. ex: "health"
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * cardIds [list of strings, default null]: card  IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["approved", "canceled", "denied", "confirmed", "voided"],
     * ids [list of strings, default null]: purchase IDs
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporatePurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporatePurchase> query(Map<String, Object> params, User user) throws Exception{
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve CorporatePurchases
     * <p>
     * Receive a generator of CorporatePurchase objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * merchantCategoryTypes [list of strings, default null]: merchant category type. ex: "health"
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * cardIds [list of strings, default null]: card  IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["approved", "canceled", "denied", "confirmed", "voided"],
     * ids [list of strings, default null]: purchase IDs
     * <p>
     * Return:
     * @return generator of CorporatePurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporatePurchase> query(Map<String, Object> params) throws Exception{
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve CorporatePurchases
     * <p>
     * Receive a generator of CorporatePurchase objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporatePurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporatePurchase> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve CorporatePurchases
     * <p>
     * Receive a generator of CorporatePurchase objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of CorporatePurchase objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporatePurchase> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<CorporatePurchase> corporatePurchases;
        public String cursor;

        public Page(List<CorporatePurchase> corporatePurchases, String cursor) {
            this.corporatePurchases = corporatePurchases;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged CorporatePurchases
     * <p>
     * Receive a list of up to 100 CorporatePurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page purchases.
     * <p>
     * Return:
     * CorporatePurchase.Page.purchases: list of CorporatePurchase objects with updated attributes
     * CorporatePurchase.Page.cursor: cursor to retrieve the next page of CorporatePurchase objects
     * @throws Exception error in the request
     */
    public static CorporatePurchase.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged CorporatePurchases
     * <p>
     * Receive a list of up to 100 CorporatePurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page purchases.
     * * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * merchantCategoryTypes [list of strings, default null]: merchant category type. ex: "health"
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * cardIds [list of strings, default null]: card  IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["approved", "canceled", "denied", "confirmed", "voided"],
     * ids [list of strings, default null]: purchase IDs
     * <p>
     * Return:
     * CorporatePurchase.Page.purchases: list of CorporatePurchase objects with updated attributes
     * CorporatePurchase.Page.cursor: cursor to retrieve the next page of CorporatePurchase objects
     * @throws Exception error in the request
     */
    public static CorporatePurchase.Page page(Map<String , Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged CorporatePurchases
     * <p>
     * Receive a list of up to 100 CorporatePurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page purchases.
     * <p>
     * Return:
     * CorporatePurchase.Page.purchases: list of CorporatePurchase objects with updated attributes
     * CorporatePurchase.Page.cursor: cursor to retrieve the next page of CorporatePurchase objects
     * @throws Exception error in the request
     */
    public static CorporatePurchase.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged CorporatePurchases
     * <p>
     * Receive a list of up to 100 CorporatePurchase objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page purchases.
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2022-03-22"
     * before [string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * merchantCategoryTypes [list of strings, default null]: merchant category type. ex: "health"
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * cardIds [list of strings, default null]: card  IDs. ex: ["5656565656565656", "4545454545454545"]
     * status [list of strings, default null]: filter for status of retrieved objects. ex: ["approved", "canceled", "denied", "confirmed", "voided"],
     * ids [list of strings, default null]: purchase IDs
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * CorporatePurchase.Page.purchases: list of CorporatePurchase objects with updated attributes
     * CorporatePurchase.Page.cursor: cursor to retrieve the next page of CorporatePurchase objects
     * @throws Exception error in the request
     */
    public static CorporatePurchase.Page page(Map<String , Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<CorporatePurchase> corporatePurchases = new ArrayList<>();
        for (SubResource corporatePurchase: page.entities) {
            corporatePurchases.add((CorporatePurchase) corporatePurchase);
        }
        return new CorporatePurchase.Page(corporatePurchases, page.cursor);
    }

    /**
     * Create a single verified CorporatePurchase authorization request from a content string
     * <p>
     * Use this method to parse and verify the authenticity of the authorization request received at the informed endpoint.
     * Authorization requests are posted to your registered endpoint whenever CorporatePurchases are received.
     * They present CorporatePurchase data that must be analyzed and answered with approval or declination.
     * If the provided digital signature does not check out with the StarkBank public key, a com.starkbank.error.InvalidSignatureError will be raised.
     * If the authorization request is not answered within 2 seconds or is not answered with an HTTP status code 200 the CorporatePurchase will go through the pre-configured stand-in validation.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.user was set before function call
     * <p>
     * Return:
     * @return Parsed CorporatePurchase object
     * @throws Exception error in the request
     */
    public static CorporatePurchase parse(String content, String signature, User user) throws Exception{
        return Parse.parseAndVerify(data, content, signature, user);
    }

    /**
     * Create a single verified CorporatePurchase authorization request from a content string
     * <p>
     * Use this method to parse and verify the authenticity of the authorization request received at the informed endpoint.
     * Authorization requests are posted to your registered endpoint whenever CorporatePurchases are received.
     * They present CorporatePurchase data that must be analyzed and answered with approval or declination.
     * If the provided digital signature does not check out with the StarkBank public key, a com.starkbank.error.InvalidSignatureError will be raised.
     * If the authorization request is not answered within 2 seconds or is not answered with an HTTP status code 200 the CorporatePurchase will go through the pre-configured stand-in validation.
     * <p>
     * Parameters:
     * @param content [string]: response content from request received at user endpoint (not parsed)
     * @param signature [string]: base-64 digital signature received at response header "Digital-Signature"
     * <p>
     * Return:
     * @return Parsed CorporatePurchase object
     * @throws Exception error in the request
     */
    public static CorporatePurchase parse(String content, String signature) throws Exception{
        return Parse.parseAndVerify(data, content, signature, null);
    }

    /**
     * Helps you respond CorporatePurchase requests
     * <p>
     * Parameters:
     * @param params to be returned on a CorporatePurchase read.
     * status [string]: sub-issuer response to the authorization. ex: "approved" or "denied"
     * reason [string]: denial reason. Options: "other", "blocked", "lostCard", "stolenCard", "invalidPin", "invalidCard", "cardExpired", "issuerError", "concurrency", "standInDenial", "subIssuerError", "invalidPurpose", "invalidZipCode", "invalidWalletId", "inconsistentCard", "settlementFailed", "cardRuleMismatch", "invalidExpiration", "prepaidInstallment", "holderRuleMismatch", "insufficientBalance", "tooManyTransactions", "invalidSecurityCode", "invalidPaymentMethod", "confirmationDeadline", "withdrawalAmountLimit", "insufficientCardLimit", "insufficientHolderLimit"
     * amount [integer, default 0]: amount in cents that was authorized. ex: 1234 (= R$ 12.34)
     * tags [list of strings, default []]: tags to filter retrieved object. ex: ["tony", "stark"]
     * <p>
     * Return:
     * @return Dumped JSON string that must be returned to us on the CorporatePurchase request
     */
    public static String response(Map<String, Object> params){
        Gson gson = new Gson();
        return gson.toJson(params);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(CorporatePurchase.Log.class, "CorporatePurchaseLog");

        public CorporatePurchase purchase;
        public String description;
        public String corporateTransactionId;
        public List<ErrorElement> errors;
        public String type;
        public String created;

        /**
         * CorporatePurchase Log object
         * <p>
         * Every time a CorporatePurchase entity is modified, a corresponding CorporatePurchase Log
         * is generated for the entity. This log is never generated by the
         * user.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param purchase [CorporatePurchase]: CorporatePurchase entity to which the log refers to.
         * @param description [string]: purchase descriptions. ex: "my_description"
         * @param corporateTransactionId [string]: transaction ID related to the CorporateCard.
         * @param errors [list of strings]: list of errors linked to the CorporatePurchase event.
         * @param type [string]: type of the CorporatePurchase event which triggered the log creation. Options: "approved", "canceled", "confirmed", "denied", "reversed", "voided"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, List<ErrorElement> errors, CorporatePurchase purchase, String description,
                   String id, String corporateTransactionId) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.purchase = purchase;
            this.description = description;
            this.corporateTransactionId = corporateTransactionId;
        }

        public Log(){
            super(null);
        }

        /**
         * Retrieve a specific CorporatePurchase Log
         * <p>
         * Receive a single CorporatePurchase Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return CorporatePurchase Log object with updated attributes
         * @throws Exception error in the purchase
         */
        public static CorporatePurchase.Log get(String id) throws Exception {
            return CorporatePurchase.Log.get(id, null);
        }

        /**
         * Retrieve a specific CorporatePurchase Log
         * <p>
         * Receive a single CorporatePurchase Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporatePurchase Log object with updated attributes
         * @throws Exception error in the purchase
         */
        public static CorporatePurchase.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve CorporatePurchase Logs
         * <p>
         * Receive a generator of CorporatePurchase.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: ["approved", "canceled", "confirmed", "denied", "reversed", "voided"]
         * purchaseIds [list of strings, default null]: list of CorporatePurchase ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of CorporatePurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of CorporatePurchase Log objects with updated attributes
         * @throws Exception error in the purchase
         */
        public static Generator<CorporatePurchase.Log> query(Map<String, Object> params) throws Exception {
            return CorporatePurchase.Log.query(params, null);
        }

        /**
         * Retrieve CorporatePurchase Logs
         * <p>
         * Receive a generator of CorporatePurchase.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of CorporatePurchase Log objects with updated attributes
         * @throws Exception error in the purchase
         */
        public static Generator<CorporatePurchase.Log> query(User user) throws Exception {
            return CorporatePurchase.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve CorporatePurchase Logs
         * <p>
         * Receive a generator of CorporatePurchase.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of CorporatePurchase Log objects with updated attributes
         * @throws Exception error in the purchase
         */
        public static Generator<CorporatePurchase.Log> query() throws Exception {
            return CorporatePurchase.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve CorporatePurchase Logs
         * <p>
         * Receive a generator of CorporatePurchase.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: ["approved", "canceled", "confirmed", "denied", "reversed", "voided"]
         * purchaseIds [list of strings, default null]: list of CorporatePurchase ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of CorporatePurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of CorporatePurchase Log objects with updated attributes
         * @throws Exception error in the purchase
         */
        public static Generator<CorporatePurchase.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<CorporatePurchase.Log> logs;
            public String cursor;

            public Page(List<CorporatePurchase.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged CorporatePurchase.Logs
         * <p>
         * Receive a list of up to 100 CorporatePurchase.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your purchases.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: ["approved", "canceled", "confirmed", "denied", "reversed", "voided"]
         * purchaseIds [list of strings, default null]: list of CorporatePurchase ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of CorporatePurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return CorporatePurchase.Log.Page object:
         * CorporatePurchase.Log.Page.logs: list of CorporatePurchase.Log objects with updated attributes
         * CorporatePurchase.Log.Page.cursor: cursor to retrieve the next page of CorporatePurchase.Log objects
         * @throws Exception error in the purchase
         */
        public static CorporatePurchase.Log.Page page(Map<String, Object> params) throws Exception {
            return CorporatePurchase.Log.page(params, null);
        }

        /**
         * Retrieve paged CorporatePurchase.Logs
         * <p>
         * Receive a list of up to 100 CorporatePurchase.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your purchases.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporatePurchase.Log.Page object:
         * CorporatePurchase.Log.Page.logs: list of CorporatePurchase.Log objects with updated attributes
         * CorporatePurchase.Log.Page.cursor: cursor to retrieve the next page of CorporatePurchase.Log objects
         * @throws Exception error in the purchase
         */
        public static CorporatePurchase.Log.Page page(User user) throws Exception {
            return CorporatePurchase.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged CorporatePurchase.Logs
         * <p>
         * Receive a list of up to 100 CorporatePurchase.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your purchases.
         * <p>
         * Return:
         * @return CorporatePurchase.Log.Page object:
         * CorporatePurchase.Log.Page.logs: list of CorporatePurchase.Log objects with updated attributes
         * CorporatePurchase.Log.Page.cursor: cursor to retrieve the next page of CorporatePurchase.Log objects
         * @throws Exception error in the purchase
         */
        public static CorporatePurchase.Log.Page page() throws Exception {
            return CorporatePurchase.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged CorporatePurchase.Logs
         * <p>
         * Receive a list of up to 100 CorporatePurchase.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your purchases.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: ["approved", "canceled", "confirmed", "denied", "reversed", "voided"]
         * purchaseIds [list of strings, default null]: list of CorporatePurchase ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of CorporatePurchase ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporatePurchase.Log.Page object:
         * CorporatePurchase.Log.Page.logs: list of CorporatePurchase.Log objects with updated attributes
         * CorporatePurchase.Log.Page.cursor: cursor to retrieve the next page of CorporatePurchase.Log objects
         * @throws Exception error in the purchase
         */
        public static CorporatePurchase.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<CorporatePurchase.Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((CorporatePurchase.Log) log);
            }
            return new Page(logs, page.cursor);
        }
    }
}
