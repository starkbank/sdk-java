package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DynamicBrcode extends Resource {
    /**
     * DynamicBrcode object
     * <p>
     * When you initialize a DynamicBrcode, the entity will not be automatically
     * sent to the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     *
     * DynamicBrcodes are conciliated BR Codes that can be used to receive Pix transactions in a convenient way.
     * When a DynamicBrcode is paid, a Deposit is created with the tags parameter containing the character “dynamic-brcode/” followed by the DynamicBrcode’s uuid "dynamic-brcode/{uuid}" for conciliation.
     * Additionally, all tags passed on the DynamicBrcode will be transferred to the respective Deposit resource.
     * <p>
     * Parameters:
     * amount [integer]: DynamicBrcode value in cents. Minimum = 0 (any value will be accepted). ex: 1234 (= R$ 12.34)
     * expiration [integer, default 3600 (1 hour)]: time interval in seconds between due date and expiration date. ex 123456789
     * tags [list of strings, default []]: list of strings for tagging, these will be passed to the respective Deposit resource when paid
     * id [string]: id returned on creation, this is the BR code. ex: "00020126360014br.gov.bcb.pix0114+552840092118152040000530398654040.095802BR5915Jamie Lannister6009Sao Paulo620705038566304FC6C"
     * uuid [string]: unique uuid returned when the DynamicBrcode is created. ex: "4e2eab725ddd495f9c98ffd97440702d"
     * updated [string]: creation datetime for the DynamicBrcode. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the DynamicBrcode. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(DynamicBrcode.class, "DynamicBrcode");

    public Number amount;
    public Number expiration;
    public String[] tags;
    public String uuid;
    public String created;
    public String updated;

    /**
     * DynamicBrcode object
     * <p>
     * When you initialize a DynamicBrcode, the entity will not be automatically
     * sent to the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     *
     * DynamicBrcodes are conciliated BR Codes that can be used to receive Pix transactions in a convenient way.
     * When a DynamicBrcode is paid, a Deposit is created with the tags parameter containing the character “dynamic-brcode/” followed by the DynamicBrcode’s uuid "dynamic-brcode/{uuid}" for conciliation.
     * Additionally, all tags passed on the DynamicBrcode will be transferred to the respective Deposit resource.
     * <p>
     * Parameters:
     * @param amount [integer]: DynamicBrcode value in cents. Minimum = 0 (any value will be accepted). ex: 1234 (= R$ 12.34)
     * @param expiration [integer, default 3600 (1 hour)]: time interval in seconds between due date and expiration date. ex 123456789
     * @param tags [list of strings, default []]: list of strings for tagging, these will be passed to the respective Deposit resource when paid
     * @param id [string]: id returned on creation, this is the BR code. ex: "00020126360014br.gov.bcb.pix0114+552840092118152040000530398654040.095802BR5915Jamie Lannister6009Sao Paulo620705038566304FC6C"
     * @param uuid [string]: unique uuid returned when the DynamicBrcode is created. ex: "4e2eab725ddd495f9c98ffd97440702d"
     * @param updated [string]: creation datetime for the DynamicBrcode. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param created [string]: creation datetime for the DynamicBrcode. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public DynamicBrcode( 
        Number amount, Number expiration, String[] tags, String id, String uuid, 
        String created, String updated
    ) {
        super(id);
        this.amount = amount;
        this.expiration = expiration;
        this.tags = tags;
        this.uuid = uuid;
        this.created = created;
        this.updated = updated;
    }

    /**
     * DynamicBrcode object
     * <p>
     * When you initialize a DynamicBrcode, the entity will not be automatically
     * sent to the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     *
     * DynamicBrcodes are conciliated BR Codes that can be used to receive Pix transactions in a convenient way.
     * When a DynamicBrcode is paid, a Deposit is created with the tags parameter containing the character “dynamic-brcode/” followed by the DynamicBrcode’s uuid "dynamic-brcode/{uuid}" for conciliation.
     * Additionally, all tags passed on the DynamicBrcode will be transferred to the respective Deposit resource.
     * <p>
     * @param data map of parameters for the creation of the DynamicBrcode
     * Parameters:
     * amount [integer]: DynamicBrcode value in cents. Minimum = 0 (any value will be accepted). ex: 1234 (= R$ 12.34)
     * <p>
     * Parameters (optional):
     * expiration [integer, default 3600 (1 hour)]: time interval in seconds between due date and expiration date. ex 123456789
     * tags [list of strings, default []]: list of strings for tagging, these will be passed to the respective Deposit resource when paid
     * <p>
     * Attributes (return-only):
     * id [string]: id returned on creation, this is the BR code. ex: "00020126360014br.gov.bcb.pix0114+552840092118152040000530398654040.095802BR5915Jamie Lannister6009Sao Paulo620705038566304FC6C"
     * uuid [string]: unique uuid returned when the DynamicBrcode is created. ex: "4e2eab725ddd495f9c98ffd97440702d"
     * updated [string]: creation datetime for the DynamicBrcode. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the DynamicBrcode. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public DynamicBrcode(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = (Number) dataCopy.remove("amount");
        this.expiration = (Number) dataCopy.remove("expiration");
        this.tags = (String[]) dataCopy.remove("tags");
        this.uuid = null;
        this.created = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Create DynamicBrcodes
     * <p>
     * Send a list of DynamicBrcode objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param brcodes [list of DynamicBrcode objects or Maps]: list of DynamicBrcode objects to be created in the API
     * @param user [Project object]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return list of DynamicBrcode objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<DynamicBrcode> create(List<?> brcodes, User user) throws Exception {
        List<DynamicBrcode> brcodeList = new ArrayList<>();
        for (Object brcode : brcodes){
            if (brcode instanceof Map){
                brcodeList.add(new DynamicBrcode((Map<String, Object>) brcode));
                continue;
            }
            if (brcode instanceof DynamicBrcode){
                brcodeList.add((DynamicBrcode) brcode);
                continue;
            }
            throw new Exception("Unknown type \"" + brcode.getClass() + "\", use DynamicBrcode or HashMap");
        }
        return Rest.post(data, brcodeList, user);
    }

    /**
     * Create DynamicBrcodes
     * <p>
     * Send a list of DynamicBrcode objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param brcodes [list of DynamicBrcode objects or Maps]: list of DynamicBrcode objects to be created in the API
     * <p>
     * Return:
     * @return list of DynamicBrcode objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<DynamicBrcode> create(List<?> brcodes) throws Exception {
        return DynamicBrcode.create(brcodes, null);
    }

    /**
     * Retrieve a specific DynamicBrcode
     * <p>
     * Receive a single DynamicBrcode object previously created in the Stark Bank API by passing its uuid
     * <p>
     * Parameters:
     * @param uuid [string]: object unique uuid. ex: "901e71f2447c43c886f58366a5432c4b"
     * Return:
     * @return DynamicBrcode object with updated attributes
     * @throws Exception error in the request
     */
    public static DynamicBrcode get(String uuid) throws Exception {
        return DynamicBrcode.get(uuid, null);
    }

    /**
     * Retrieve a specific DynamicBrcode
     * <p>
     * Receive a single DynamicBrcode object previously created in the Stark Bank API by passing its uuid
     * <p>
     * Parameters:
     * @param uuid [string]: object unique uuid. ex: "901e71f2447c43c886f58366a5432c4b"
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return DynamicBrcode object with updated attributes
     * @throws Exception error in the request
     */
    public static DynamicBrcode get(String uuid, User user) throws Exception {
        return Rest.getId(data, uuid, user);
    }

    /**
     * Retrieve DynamicBrcodes
     * <p>
     * Receive a generator of DynamicBrcode objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * uuids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["901e71f2447c43c886f58366a5432c4b", "4e2eab725ddd495f9c98ffd97440702d"]
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of DynamicBrcode objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DynamicBrcode> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve DynamicBrcodes
     * <p>
     * Receive a generator of DynamicBrcode objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * uuids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["901e71f2447c43c886f58366a5432c4b", "4e2eab725ddd495f9c98ffd97440702d"]
     * <p>
     * Return:
     * @return generator of DynamicBrcode objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DynamicBrcode> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve DynamicBrcodes
     * <p>
     * Receive a generator of DynamicBrcode objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of DynamicBrcode objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DynamicBrcode> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve DynamicBrcodes
     * <p>
     * Receive a generator of DynamicBrcode objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * Return:
     * @return generator of DynamicBrcode objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DynamicBrcode> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<DynamicBrcode> brcodes;
        public String cursor;

        public Page(List<DynamicBrcode> brcodes, String cursor) {
            this.brcodes = brcodes;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged DynamicBrcodes
     * <p>
     * Receive a list of up to 100 DynamicBrcode objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * uuids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["901e71f2447c43c886f58366a5432c4b", "4e2eab725ddd495f9c98ffd97440702d"]
     * <p>
     * Return:
     * @return DynamicBrcode.Page object:
     * DynamicBrcode.Page.brcodes: list of DynamicBrcode objects with updated attributes
     * DynamicBrcode.Page.cursor: cursor to retrieve the next page of DynamicBrcode objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged DynamicBrcodes
     * <p>
     * Receive a list of up to 100 DynamicBrcode objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DynamicBrcode.Page object:
     * DynamicBrcode.Page.brcodes: list of DynamicBrcode objects with updated attributes
     * DynamicBrcode.Page.cursor: cursor to retrieve the next page of DynamicBrcode objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged DynamicBrcodes
     * <p>
     * Receive a list of up to 100 DynamicBrcode objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return DynamicBrcode.Page object:
     * DynamicBrcode.Page.brcodes: list of DynamicBrcode objects with updated attributes
     * DynamicBrcode.Page.cursor: cursor to retrieve the next page of DynamicBrcode objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged DynamicBrcodes
     * <p>
     * Receive a list of up to 100 DynamicBrcode objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * uuids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["901e71f2447c43c886f58366a5432c4b", "4e2eab725ddd495f9c98ffd97440702d"]
     * <p>
     * Return:
     * @return DynamicBrcode.Page object:
     * DynamicBrcode.Page.brcodes: list of DynamicBrcode objects with updated attributes
     * DynamicBrcode.Page.cursor: cursor to retrieve the next page of DynamicBrcode objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<DynamicBrcode> brcodes = new ArrayList<>();
        for (SubResource brcode: page.entities) {
            brcodes.add((DynamicBrcode) brcode);
        }
        return new Page(brcodes, page.cursor);
    }
}
