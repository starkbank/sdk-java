package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Generator;
import com.starkcore.utils.SubResource;

import java.util.Map;
import java.util.HashMap;

public final class MerchantCategory extends SubResource {
    /**
     * MerchantCategory object
     * <p>
     * MerchantCategory's codes and types are used to define categories filters in CorporateRules.
     * A MerchantCategory filter must define exactly one parameter between code and type.
     * A type, such as "food", "services", etc., defines an entire group of merchant codes,
     * whereas a code only specifies a specific MCC.
     * <p>
     * Parameters:
     * code [string, default null]: category's code. ex: "veterinaryServices", "fastFoodRestaurants"
     * type [string, default null]: category's type. ex: "pets", "food"
     * name [string]: category's name. ex: "Veterinary services", "Fast food restaurants"
     * number [string]: category's number. ex: "742", "5814"
     *
     */
    static SubResource.ClassData data = new SubResource.ClassData(MerchantCategory.class, "MerchantCategory");

    public String code;
    public String type;
    public String name;
    public String number;

    /**
     * MerchantCategory object
     * <p>
     * MerchantCategory's codes and types are used to define categories filters in CorporateRules.
     * A MerchantCategory filter must define exactly one parameter between code and type.
     * A type, such as "food", "services", etc., defines an entire group of merchant codes,
     * whereas a code only specifies a specific MCC.
     * <p>
     * Parameters:
     * @param code [string, default null]: category's code. ex: "veterinaryServices", "fastFoodRestaurants"
     * @param type [string, default null]: category's type. ex: "pets", "food"
     * @param name [string]: category's name. ex: "Veterinary services", "Fast food restaurants"
     * @param number [string]: category's number. ex: "742", "5814"
     */
    public MerchantCategory(String code, String type, String name, String number){
        this.code = code;
        this.type = type;
        this.name = name;
        this.number = number;
    }

    /**
     * MerchantCategory object
     * <p>
     * MerchantCategory's codes and types are used to define categories filters in CorporateRules.
     * A MerchantCategory filter must define exactly one parameter between code and type.
     * A type, such as "food", "services", etc., defines an entire group of merchant codes,
     * whereas a code only specifies a specific MCC.
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the MerchantCategory
     * code [string, default null]: category's code. ex: "veterinaryServices", "fastFoodRestaurants"
     * type [string, default null]: category's type. ex: "pets", "food"
     * <p>
     * Attributes (return-only):
     * name [string]: category's name. ex: "Veterinary services", "Fast food restaurants"
     * number [string]: category's number. ex: "742", "5814"
     * @throws Exception error in the request
     */
    public MerchantCategory(Map<String, Object> data) throws Exception {
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.code = (String) dataCopy.remove("code");
        this.type = (String) dataCopy.remove("type");
        this.name = null;
        this.number = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public MerchantCategory(){
    }

    /**
     * Retrieve MerchantCategories
     * <p>
     * Receive a generator of MerchantCategory objects available in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * search [string, default null]: keyword to search for code, type, name or number
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of MerchantCategory objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCategory> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve MerchantCategories
     * <p>
     * Receive a generator of MerchantCategory objects available in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * search [string, default null]: keyword to search for code, type, name or number
     * <p>
     * Return:
     * @return generator of MerchantCategory objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCategory> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve MerchantCategories
     * <p>
     * Receive a generator of MerchantCategory objects available in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of MerchantCategory objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCategory> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve MerchantCategories
     * <p>
     * Receive a generator of MerchantCategory objects available in the Stark Bank API
     * <p>
     * Return:
     * @return generator of MerchantCategory objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<MerchantCategory> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }
}
