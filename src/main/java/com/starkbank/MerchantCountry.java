package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Generator;
import com.starkbank.utils.SubResource;

import java.util.Map;
import java.util.HashMap;

public final class MerchantCountry extends SubResource {
    /**
     * MerchantCountry object
     * <p>
     * MerchantCountry's codes are used to define countries filters in CorporateRules.
     * <p>
     * Parameters:
     * code [string]: country's code. ex: "BRA"
     * name [string]: country's name. ex: "Brazil"
     * number [string]: country's number. ex: "076"
     * shortCode [string]: country's short code. ex: "BR"
     *
     */
    static SubResource.ClassData data = new SubResource.ClassData(MerchantCountry.class, "MerchantCountry");

    public String code;
    public String name;
    public String number;
    public String shortCode;

    /**
     * MerchantCountry object
     * <p>
     * MerchantCountry's codes are used to define countries filters in CorporateRules.
     * <p>
     * Parameters:
     * @param code [string]: country's code. ex: "BRA"
     * @param name [string]: country's name. ex: "Brazil"
     * @param number [string]: country's number. ex: "076"
     * @param shortCode [string]: country's short code. ex: "81"
     */
    public MerchantCountry(String code, String name, String number, String shortCode){
        this.code = code;
        this.name = name;
        this.number = number;
        this.shortCode = shortCode;
    }

    /**
     * MerchantCountry object
     * <p>
     * MerchantCountry's codes are used to define countries filters in CorporateRules.
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the MerchantCountry
     * code [string]: country's code. ex: "BRA"
     * <p>
     * Attributes (return-only):
     * name [string]: country's name. ex: "Brazil"
     * number [string]: country's number. ex: "076"
     * shortCode [string]: country's short code. ex: "BR"
     * @throws Exception error in the request
     */
    public MerchantCountry(Map<String, Object> data) throws Exception {
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.code = (String) dataCopy.remove("code");
        this.name = null;
        this.number = null;
        this.shortCode = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Retrieve MerchantCountries
     * <p>
     * Receive a generator of MerchantCountry objects available in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * search [string, default null]: keyword to search for code, name, number or shortCode
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of MerchantCountry objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCountry> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve MerchantCountries
     * <p>
     * Receive a generator of MerchantCountry objects available in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * search [string, default null]: keyword to search for code, name, number or shortCode
     * <p>
     * Return:
     * @return generator of MerchantCountry objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCountry> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve MerchantCountries
     * <p>
     * Receive a generator of MerchantCountry objects available in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of MerchantCountry objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCountry> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve MerchantCountries
     * <p>
     * Receive a generator of MerchantCountry objects available in the Stark Bank API
     * <p>
     * Return:
     * @return generator of MerchantCountry objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCountry> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }
}
