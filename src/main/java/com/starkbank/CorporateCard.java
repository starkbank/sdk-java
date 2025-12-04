package com.starkbank;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.starkbank.utils.*;
import com.starkcore.utils.GsonEvent;
import com.starkcore.utils.SubResource;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;


public final class CorporateCard extends Resource {
    /**
     * CorporateCard object
     * <p>
     * The CorporateCard object displays the information of the cards created in your Workspace.
     * Sensitive information will only be returned when the "expand" parameter is used, to avoid security concerns.
     * <p>
     * Parameters:
     * id [string]: unique id returned when CorporateCard is created. ex: "5656565656565656"
     * holderId [string]: card holder unique id. ex: "5656565656565656"
     * holderName [string]: card holder name. ex: "Tony Stark"
     * displayName [string, default ""]: card displayed name. ex: "ANTHONY STARK"
     * rules [list of CorporateRule or HashMap, default []]: [EXPANDABLE] list of card spending rules.
     * tags [list of strings, default []]: list of strings for tagging. ex: ["travel", "food"]
     * streetLine1 [string, default sub-issuer street line 1]: card holder main address. ex: "Av. Paulista, 200"
     * streetLine2 [string, default sub-issuer street line 2]: card holder address complement. ex: "Apto. 123"
     * district [string, default sub-issuer district]: card holder address district / neighbourhood. ex: "Bela Vista"
     * city [string, default sub-issuer city]: card holder address city. ex: "Rio de Janeiro"
     * stateCode [string, default sub-issuer state code]: card holder address state. ex: "GO"
     * zipCode [string, default sub-issuer zip code]: card holder address zip code. ex: "01311-200"
     * type [string]: card type. ex: "virtual"
     * status [string]: current CorporateCard status. ex: "active", "blocked", "canceled", "expired"
     * number [string]: [EXPANDABLE] masked card number. Expand to unmask the value. ex: "123".
     * securityCode [string]: [EXPANDABLE] masked card verification value (cvv). Expand to unmask the value. ex: "123".
     * expiration [string]: [EXPANDABLE] masked card expiration datetime. Expand to unmask the value. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: latest update datetime for the CorporateCard. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporateCard. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(CorporateCard.class, "CorporateCard");

    public String holderId;
    public String holderName;
    public String displayName;
    public List<CorporateRule> rules;
    public String[] tags;
    public String streetLine1;
    public String streetLine2;
    public String district;
    public String city;
    public String stateCode;
    public String zipCode;
    public String type;
    public String status;
    public String number;
    public String securityCode;
    public String expiration;
    public String updated;
    public String created;

    /**
     * CorporateCard object
     * <p>
     * The CorporateCard object displays the information of the cards created in your Workspace.
     * Sensitive information will only be returned when the "expand" parameter is used, to avoid security concerns.
     * <p>
     * When you initialize a CorporateCard, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the created object.
     * <p>
     * Parameters:
     * @param holderId [string]: card holder unique id. ex: "5656565656565656"
     * @param holderName [string]: card holder name. ex: "Tony Stark"
     * @param displayName [string]: card displayed name. ex: "ANTHONY STARK"
     * @param rules [list of CorporateRule or HashMap]: [EXPANDABLE] list of card spending rules.
     * @param tags [list of strings]: list of strings for tagging. ex: ["travel", "food"]
     * @param streetLine1 [string, default sub-issuer street line 1]: card holder main address. ex: "Av. Paulista, 200"
     * @param streetLine2 [string, default sub-issuer street line 2]: card holder address complement. ex: "Apto. 123"
     * @param district [string, default sub-issuer district]: card holder address district / neighbourhood. ex: "Bela Vista"
     * @param city [string, default sub-issuer city]: card holder address city. ex: "Rio de Janeiro"
     * @param stateCode [string, default sub-issuer state code]: card holder address state. ex: "GO"
     * @param zipCode [string, default sub-issuer zip code]: card holder address zip code. ex: "01311-200"
     * @param id [string]: unique id returned when CorporateCard is created. ex: "5656565656565656"
     * @param type [string]: card type. ex: "virtual"
     * @param status [string]: current CorporateCard status. ex: "active", "blocked", "canceled", "expired"
     * @param number [string]: [EXPANDABLE] masked card number. Expand to unmask the value. ex: "123".
     * @param securityCode [string]: [EXPANDABLE] masked card verification value (cvv). Expand to unmask the value. ex: "123".
     * @param expiration [string]: [EXPANDABLE] masked card expiration datetime. Expand to unmask the value. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string]: latest update datetime for the CorporateCard. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param created [string]: creation datetime for the CorporateCard. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public CorporateCard(String holderName, String displayName, List<CorporateRule> rules, String[] tags,
                         String streetLine1, String streetLine2, String district, String city, String stateCode,
                         String zipCode, String id, String holderId, String type, String status, String number,
                         String securityCode, String expiration, String updated, String created
    ) {
        super(id);
        this.holderId = holderId;
        this.holderName = holderName;
        this.displayName = displayName;
        this.rules = rules;
        this.tags = tags;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.district = district;
        this.city = city;
        this.stateCode = stateCode;
        this.zipCode = zipCode;
        this.type = type;
        this.status = status;
        this.number = number;
        this.securityCode = securityCode;
        this.expiration = expiration;
        this.updated = updated;
        this.created = created;
    }

    /**
     * CorporateCard object
     * <p>
     * The CorporateCard object displays the information of the cards created in your Workspace.
     * Sensitive information will only be returned when the "expand" parameter is used, to avoid security concerns.
     * <p>
     * Parameters (required):
     * @param data map of properties for the creation of the CorporateCard
     * holderId [string]: card holder unique id. ex: "5656565656565656"
     * <p>
     * Attributes (return-only):
     * holderName [string]: card holder name. ex: "Tony Stark"
     * displayName [string]: card displayed name. ex: "ANTHONY STARK"
     * rules [list of CorporateRule or HashMap]: [EXPANDABLE] list of card spending rules.
     * tags [list of strings]: list of strings for tagging. ex: ["travel", "food"]
     * streetLine1 [string, default sub-issuer street line 1]: card holder main address. ex: "Av. Paulista, 200"
     * streetLine2 [string, default sub-issuer street line 2]: card holder address complement. ex: "Apto. 123"
     * district [string, default sub-issuer district]: card holder address district / neighbourhood. ex: "Bela Vista"
     * city [string, default sub-issuer city]: card holder address city. ex: "Rio de Janeiro"
     * stateCode [string, default sub-issuer state code]: card holder address state. ex: "GO"
     * zipCode [string, default sub-issuer zip code]: card holder address zip code. ex: "01311-200"
     * id [string]: unique id returned when CorporateCard is created. ex: "5656565656565656"
     * type [string]: card type. ex: "virtual"
     * status [string]: current CorporateCard status. ex: "active", "blocked", "canceled", "expired"
     * number [string]: [EXPANDABLE] masked card number. Expand to unmask the value. ex: "123".
     * securityCode [string]: [EXPANDABLE] masked card verification value (cvv). Expand to unmask the value. ex: "123".
     * expiration [string]: [EXPANDABLE] masked card expiration datetime. Expand to unmask the value. ex: "2020-03-10 10:30:00.000000+00:00".
     * updated [string]: latest update datetime for the CorporateCard. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporateCard. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    public CorporateCard(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.holderId = (String) dataCopy.remove("holderId");
        this.tags = null;
        this.holderName = null;
        this.displayName = null;
        this.rules = null;
        this.streetLine1 = null;
        this.streetLine2 = null;
        this.district = null;
        this.city = null;
        this.stateCode = null;
        this.zipCode = null;
        this.type = null;
        this.status = null;
        this.number = null;
        this.securityCode = null;
        this.expiration = null;
        this.updated = null;
        this.created = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public CorporateCard() {
        super(null);
    }

    /**
     * Create CorporateCard
     * <p>
     * Send a CorporateCard object for creation in the Stark Bank API
     * If the CorporateCard was not used in the last purchase, this resource will return it.
     * <p>
     * Parameters:
     * @param card [CorporateCard object]: CorporateCard object to be created in the API
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules", "securityCode", "number", "expiration"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard create(CorporateCard card, Map<String, Object> params, User user) throws Exception {
        Gson gson = GsonEvent.getInstance();
        String path = Api.endpoint(data) + "/token";
        JsonObject payload = (JsonObject) new Gson().toJsonTree(card);

        Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
        Map<String, Object> map = new Gson().fromJson(payload, type);

        JsonObject raw = gson.fromJson(Rest.postRaw(path, map, params, user).content(), JsonObject.class);

        JsonObject jsonObject = raw.get(Api.getLastName(data)).getAsJsonObject();
        return gson.fromJson(jsonObject, (Type) data.cls);
    }

    /**
     * Create CorporateCard
     * <p>
     * Send a CorporateCard object for creation in the Stark Bank API
     * If the CorporateCard was not used in the last purchase, this resource will return it.
     * <p>
     * Parameters:
     * @param card [CorporateCard object]: CorporateCard object to be created in the API
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard create(CorporateCard card, User user) throws Exception {
        return CorporateCard.create(card, null, user);
    }

    /**
     * Create CorporateCard
     * <p>
     * Send a CorporateCard object for creation in the Stark Bank API
     * If the CorporateCard was not used in the last purchase, this resource will return it.
     * <p>
     * Parameters:
     * @param card [CorporateCard object]: CorporateCard object to be created in the API
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules", "securityCode", "number", "expiration"]
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard create(CorporateCard card, Map<String, Object> params) throws Exception {
        return CorporateCard.create(card, params, null);
    }

    /**
     * Create CorporateCard
     * <p>
     * Send a CorporateCard object for creation in the Stark Bank API
     * If the CorporateCard was not used in the last purchase, this resource will return it.
     * <p>
     * Parameters:
     * @param card [CorporateCard object]: CorporateCard object to be created in the API
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard create(CorporateCard card) throws Exception {
        return CorporateCard.create(card, null, null);
    }

    /**
     * Retrieve CorporateCards
     * <p>
     * Receive a generator of CorporateCard objects registered to your workspace in the Stark Bank API.
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [string, default ""]: filter for status of retrieved objects. ex: "active", "blocked", "expired" or "canceled"
     * types [list of strings, default null]: card type. ex: ["virtual"]
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default []]: fields to expand information. ex: ["rules", "securityCode", "number", "expiration"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateCard> query(Map<String, Object> params, User user) throws Exception{
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve CorporateCards
     * <p>
     * Receive a generator of CorporateCard objects registered to your workspace in the Stark Bank API.
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [string, default ""]: filter for status of retrieved objects. ex: "active", "blocked", "expired" or "canceled"
     * types [list of strings, default null]: card type. ex: ["virtual"]
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default []]: fields to expand information. ex: ["rules", "securityCode", "number", "expiration"]
     * <p>
     * Return:
     * @return generator of CorporateCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateCard> query(Map<String, Object> params) throws Exception{
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve CorporateCards
     * <p>
     * Receive a generator of CorporateCard objects registered to your workspace in the Stark Bank API.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateCard> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }


    /**
     * Retrieve CorporateCards
     * <p>
     * Receive a generator of CorporateCard objects registered to your workspace in the Stark Bank API.
     * <p>
     * Return:
     * @return generator of CorporateCard objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateCard> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<CorporateCard> cards;
        public String cursor;

        public Page(List<CorporateCard> cards, String cursor) {
            this.cards = cards;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged CorporateCards
     * <p>
     * Receive a list of up to 100 CorporateCard objects registered to your workspace in the Stark Bank API. and the cursor to the next page.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [string, default ""]: filter for status of retrieved objects. ex: "active", "blocked", "expired" or "canceled"
     * types [list of strings, default null]: card type. ex: ["virtual"]
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default []]: fields to expand information. ex: ["rules", "securityCode", "number", "expiration"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard.Page object:
     * CorporateCard.Page.cards: list of CorporateCard objects with updated attributes
     * CorporateCard.Page.cursor: cursor to retrieve the next page of CorporateCard objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String , Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<CorporateCard> cards = new ArrayList<>();
        for (SubResource card: page.entities) {
            cards.add((CorporateCard) card);
        }
        return new Page(cards, page.cursor);
    }

    /**
     * Retrieve paged CorporateCards
     * <p>
     * Receive a list of up to 100 CorporateCard objects registered to your workspace in the Stark Bank API. and the cursor to the next page.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [string, default ""]: filter for status of retrieved objects. ex: "active", "blocked", "expired" or "canceled"
     * types [list of strings, default null]: card type. ex: ["virtual"]
     * holderIds [list of strings, default null]: card holder IDs. ex: ["5656565656565656", "4545454545454545"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * expand [list of strings, default []]: fields to expand information. ex: ["rules", "securityCode", "number", "expiration"]
     * <p>
     * Return:
     * @return CorporateCard.Page object:
     * CorporateCard.Page.cards: list of CorporateCard objects with updated attributes
     * CorporateCard.Page.cursor: cursor to retrieve the next page of CorporateCard objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String , Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged CorporateCards
     * <p>
     * Receive a list of up to 100 CorporateCard objects registered to your workspace in the Stark Bank API. and the cursor to the next page.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard.Page object:
     * CorporateCard.Page.cards: list of CorporateCard objects with updated attributes
     * CorporateCard.Page.cursor: cursor to retrieve the next page of CorporateCard objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged CorporateCards
     * <p>
     * Receive a list of up to 100 CorporateCard objects registered to your workspace in the Stark Bank API. and the cursor to the next page.
     * <p>
     * Return:
     * @return CorporateCard.Page object:
     * CorporateCard.Page.cards: list of CorporateCard objects with updated attributes
     * CorporateCard.Page.cursor: cursor to retrieve the next page of CorporateCard objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve a specific CorporateCard
     * <p>
     * Receive a single CorporateCard object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules", "security_code", "number", "expiration"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard get(String id, Map<String, Object> params, User user) throws Exception{
        return Rest.getId(data, id, params, user);
    }

    /**
     * Retrieve a specific CorporateCard
     * <p>
     * Receive a single CorporateCard object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param params map of parameters
     * expand [list of strings, default null]: fields to expand information. ex: ["rules", "security_code", "number", "expiration"]
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard get(String id, Map<String, Object> params) throws Exception{
        return CorporateCard.get(id, params, null);
    }

    /**
     * Retrieve a specific CorporateCard
     * <p>
     * Receive a single CorporateCard object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard get(String id, User user) throws Exception{
        return CorporateCard.get(id, null, user);
    }

    /**
     * Retrieve a specific CorporateCard
     * <p>
     * Receive a single CorporateCard object previously created in the Stark Bank API by its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard get(String id) throws Exception{
        return CorporateCard.get(id, null, null);
    }

    /**
     * Update CorporateCard entity
     * <p>
     * Update a CorporateCard by passing id.
     * <p>
     * Parameters:
     * @param id [string]: CorporateCard id. ex: "5656565656565656"
     * @param patchData map of parameters
     * status [string]: You may block the CorporateCard by passing 'blocked' or activate by passing 'active' in the status
     * pin [string, default ""]: You may unlock your physical card by passing its PIN. This is also the PIN you use to authorize a purchase.
     * displayName [string, default ""]: card displayed name
     * rules [list of CorporateRule or HashMap, default null]: list of new CorporateRules. If the rule id isn't set, a new rule will be created.
     * tags [list of strings, default null]: list of strings for tagging
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard update(String id, Map<String, Object> patchData) throws Exception {
        return CorporateCard.update(id, patchData, null);
    }

    /**
     * Update CorporateCard entity
     * <p>
     * Update a CorporateCard by passing id.
     * <p>
     * Parameters:
     * @param id [string]: CorporateCard id. ex: "5656565656565656"
     * @param patchData map of parameters
     * status [string]: You may block the CorporateCard by passing 'blocked' or activate by passing 'active' in the status
     * pin [string, default ""]: You may unlock your physical card by passing its PIN. This is also the PIN you use to authorize a purchase.
     * displayName [string, default null]: card displayed name
     * rules [list of CorporateRule or HashMap, default null]: list of new CorporateRules. If the rule id isn't set, a new rule will be created.
     * tags [list of strings, default null]: list of strings for tagging
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateCard object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateCard update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }

    /**
     * Cancel a CorporateCard entity
     * <p>
     * Cancel a CorporateCard entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: CorporateCard unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return canceled CorporateCard object
     * @throws Exception error in the request
     */
    public static CorporateCard cancel(String id) throws Exception {
        return CorporateCard.cancel(id, null);
    }

    /**
     * Cancel a CorporateCard entity
     * <p>
     * Cancel a CorporateCard entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: CorporateCard unique id. ex: "5656565656565656"
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return canceled CorporateCard object
     * @throws Exception error in the request
     */
    public static CorporateCard cancel(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(CorporateCard.Log.class, "CorporateCardLog");

        public String created;
        public String type;
        public CorporateCard card;

        /**
         * CorporateCard Log object
         * <p>
         * Every time a CorporateCard entity is updated, a corresponding corporatecard.Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the CorporateCard.
         * <p>
         * Attributes:
         * @param id [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param card [CorporateCard]: CorporateCard entity to which the log refers to.
         * @param type [string]: type of the CorporateCard event which triggered the log creation. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * @param created [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, CorporateCard card, String id) {
            super(id);
            this.card = card;
            this.type = type;
            this.created = created;
        }

        public Log(){
            super(null);
        }

        /**
         * Retrieve a specific CorporateCard Log
         * <p>
         * Receive a single CorporateCard Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return CorporateCard Log object with updated attributes
         * @throws Exception error in the card
         */
        public static CorporateCard.Log get(String id) throws Exception {
            return CorporateCard.Log.get(id, null);
        }

        /**
         * Retrieve a specific CorporateCard Log
         * <p>
         * Receive a single CorporateCard Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporateCard Log object with updated attributes
         * @throws Exception error in the card
         */
        public static CorporateCard.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve CorporateCard Logs
         * <p>
         * Receive a generator of CorporateCard.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * cardIds [list of strings, default null]: list of CorporateCard ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return generator of CorporateCard Log objects with updated attributes
         * @throws Exception error in the card
         */
        public static Generator<CorporateCard.Log> query(Map<String, Object> params) throws Exception {
            return CorporateCard.Log.query(params, null);
        }

        /**
         * Retrieve CorporateCard Logs
         * <p>
         * Receive a generator of CorporateCard.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of CorporateCard Log objects with updated attributes
         * @throws Exception error in the card
         */
        public static Generator<CorporateCard.Log> query(User user) throws Exception {
            return CorporateCard.Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve CorporateCard Logs
         * <p>
         * Receive a generator of CorporateCard.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return generator of CorporateCard Log objects with updated attributes
         * @throws Exception error in the card
         */
        public static Generator<CorporateCard.Log> query() throws Exception {
            return CorporateCard.Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve CorporateCard Logs
         * <p>
         * Receive a generator of CorporateCard.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * cardIds [list of strings, default null]: list of CorporateCard ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return generator of CorporateCard Log objects with updated attributes
         * @throws Exception error in the card
         */
        public static Generator<CorporateCard.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<CorporateCard.Log> logs;
            public String cursor;

            public Page(List<CorporateCard.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged CorporateCard.Logs
         * <p>
         * Receive a list of up to 100 CorporateCard.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * cardIds [list of strings, default null]: list of CorporateCard ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return CorporateCard.Log.Page object:
         * CorporateCard.Log.Page.logs: list of CorporateCard.Log objects with updated attributes
         * CorporateCard.Log.Page.cursor: cursor to retrieve the next page of CorporateCard.Log objects
         * @throws Exception error in the card
         */
        public static CorporateCard.Log.Page page(Map<String, Object> params) throws Exception {
            return CorporateCard.Log.page(params, null);
        }

        /**
         * Retrieve paged CorporateCard.Logs
         * <p>
         * Receive a list of up to 100 CorporateCard.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Parameters:
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporateCard.Log.Page object:
         * CorporateCard.Log.Page.logs: list of CorporateCard.Log objects with updated attributes
         * CorporateCard.Log.Page.cursor: cursor to retrieve the next page of CorporateCard.Log objects
         * @throws Exception error in the card
         */
        public static CorporateCard.Log.Page page(User user) throws Exception {
            return CorporateCard.Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged CorporateCard.Logs
         * <p>
         * Receive a list of up to 100 CorporateCard.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Return:
         * @return CorporateCard.Log.Page object:
         * CorporateCard.Log.Page.logs: list of CorporateCard.Log objects with updated attributes
         * CorporateCard.Log.Page.cursor: cursor to retrieve the next page of CorporateCard.Log objects
         * @throws Exception error in the card
         */
        public static CorporateCard.Log.Page page() throws Exception {
            return CorporateCard.Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged CorporateCard.Logs
         * <p>
         * Receive a list of up to 100 CorporateCard.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your cards.
         * <p>
         * Parameters:
         * @param params map of parameters for the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by types. ex: "blocked", "canceled", "created", "expired", "unblocked", "updated"
         * cardIds [list of strings, default null]: list of CorporateCard ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
         * <p>
         * Return:
         * @return CorporateCard.Log.Page object:
         * CorporateCard.Log.Page.logs: list of CorporateCard.Log objects with updated attributes
         * CorporateCard.Log.Page.cursor: cursor to retrieve the next page of CorporateCard.Log objects
         * @throws Exception error in the card
         */
        public static CorporateCard.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<CorporateCard.Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((CorporateCard.Log) log);
            }
            return new Page(logs, page.cursor);
        }
    }
}
