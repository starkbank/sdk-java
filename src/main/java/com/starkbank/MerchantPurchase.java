package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MerchantPurchase extends Resource {

    /**
     # MerchantPurchase object
     Check out our API Documentation at https://starkbank.com/docs/api#merchant-purchase
     **/

    static ClassData data = new ClassData(MerchantPurchase.class, "MerchantPurchase");

    public long amount;
    public int installmentCount;
    public String cardId;
    public String holderName;
    public String holderEmail;
    public String holderPhone;
    public String fundingType;
    public String challengeMode;
    public String billingCountryCode;
    public String billingCity;
    public String billingStateCode;
    public String billingStreetLine1;
    public String billingStreetLine2;
    public String billingZipCode;
    public Map<String, Object> metadata;
    public String cardEnding;
    public String challengeUrl;
    public String created;
    public String currencyCode;
    public String endToEndId;
    public Integer fee;
    public String network;
    public String source;
    public String status;
    public String[] tags;
    public String updated;

    public MerchantPurchase(String id, long amount, int installmentCount, String holderName, String holderEmail, String holderPhone, String fundingType, String billingCountryCode, String billingCity, String billingStateCode, String billingStreetLine1, String billingStreetLine2, String billingZipCode, Map<String, Object> metadata, String cardEnding, String cardId, String challengeMode, String challengeUrl, String created, String currencyCode, String endToEndId, int fee, String network, String source, String status, String[] tags, String updated) {
        super(id);
        this.amount = amount;
        this.installmentCount = installmentCount;
        this.holderName = holderName;
        this.holderEmail = holderEmail;
        this.holderPhone = holderPhone;
        this.fundingType = fundingType;
        this.billingCountryCode = billingCountryCode;
        this.billingCity = billingCity;
        this.billingStateCode = billingStateCode;
        this.billingStreetLine1 = billingStreetLine1;
        this.billingStreetLine2 = billingStreetLine2;
        this.billingZipCode = billingZipCode;
        this.metadata = metadata;
        this.cardEnding = cardEnding;
        this.cardId = cardId;
        this.challengeMode = challengeMode;
        this.challengeUrl = challengeUrl;
        this.created = created;
        this.currencyCode = currencyCode;
        this.endToEndId = endToEndId;
        this.fee = fee;
        this.network = network;
        this.source = source;
        this.status = status;
        this.tags = tags;
        this.updated = updated;
    }

    public MerchantPurchase(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = (Long) dataCopy.remove("amount");
        this.installmentCount = (int) dataCopy.remove("installmentCount");
        this.cardId = (String) dataCopy.remove("cardId");
        this.holderName = (String) dataCopy.remove("holderName");
        this.holderEmail = (String) dataCopy.remove("holderEmail");
        this.holderPhone = (String) dataCopy.remove("holderPhone");
        this.fundingType = (String) dataCopy.remove("fundingType");
        this.billingCountryCode = (String) dataCopy.remove("billingCountryCode");
        this.billingCity = (String) dataCopy.remove("billingCity");
        this.billingStateCode = (String) dataCopy.remove("billingStateCode");
        this.billingStreetLine1 = (String) dataCopy.remove("billingStreetLine1");
        this.billingStreetLine2 = (String) dataCopy.remove("billingStreetLine2");
        this.billingZipCode = (String) dataCopy.remove("billingZipCode");
        this.metadata = (Map<String, Object>) dataCopy.remove("metadata");
        this.challengeMode = (String) dataCopy.remove("challengeMode");
        this.tags = (String[]) dataCopy.remove("tags");
        this.cardEnding = null;
        this.challengeUrl = null;
        this.currencyCode = null;
        this.endToEndId = null;
        this.fee = null;
        this.network = null;
        this.source = null;
        this.status = null;
        this.updated = null;
        this.created = null;


        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public static MerchantPurchase create(MerchantPurchase merchantPurchase, User user) throws Exception {
        return Rest.postSingle(data, merchantPurchase, user);
    }

    public static MerchantPurchase create(MerchantPurchase merchantPurchase) throws Exception {
        return MerchantPurchase.create(merchantPurchase, null);
    }

    public static MerchantPurchase update(String id, Map<String, Object> patchData) throws Exception {
        return MerchantPurchase.update(id, patchData, null);
    }

    public static MerchantPurchase update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }

    public static MerchantPurchase get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static MerchantPurchase get(String id) throws Exception {
        return MerchantPurchase.get(id, null);
    }

    public static Generator<MerchantPurchase> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public static Generator<MerchantPurchase> query(Map<String, Object> params) throws Exception {
        return MerchantPurchase.query(params, null);
    }

    public static Generator<MerchantPurchase> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public static MerchantPurchase.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    public static MerchantPurchase.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    public static MerchantPurchase.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }


    public static MerchantPurchase.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantPurchase> merchantPurchases = new ArrayList<>();
        for (com.starkcore.utils.SubResource merchantPurchase: page.entities) {
            merchantPurchases.add((MerchantPurchase) merchantPurchase);
        }
        return new MerchantPurchase.Page(merchantPurchases, page.cursor);
    }

    public final static class Page {
        public List<MerchantPurchase> merchantPurchases;
        public String cursor;

        public Page(List<MerchantPurchase> merchantPurchases, String cursor) {
            this.merchantPurchases = merchantPurchases;
            this.cursor = cursor;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantPurchase.Log.class, "MerchantPurchaseLog");

        public String created;
        public String type;
        public String[] errors;
        public MerchantPurchase purchase;

        public Log(String created, String type, String[] errors, MerchantPurchase purchase, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.purchase = purchase;
        }


        public static MerchantPurchase.Log get(String id) throws Exception {
            return MerchantPurchase.Log.get(id, null);
        }

        public static MerchantPurchase.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        public static Generator<MerchantPurchase.Log> query(Map<String, Object> params) throws Exception {
            return MerchantPurchase.Log.query(params, null);
        }

        public static Generator<MerchantPurchase.Log> query(User user) throws Exception {
            return MerchantPurchase.Log.query(new HashMap<>(), user);
        }

        public static Generator<MerchantPurchase.Log> query() throws Exception {
            return MerchantPurchase.Log.query(new HashMap<>(), null);
        }

        public static Generator<MerchantPurchase.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantPurchase.Log> logs;
            public String cursor;

            public Page(List<MerchantPurchase.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        public static MerchantPurchase.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantPurchase.Log.page(params, null);
        }

        public static MerchantPurchase.Log.Page page(User user) throws Exception {
            return MerchantPurchase.Log.page(new HashMap<>(), user);
        }

        public static MerchantPurchase.Log.Page page() throws Exception {
            return MerchantPurchase.Log.page(new HashMap<>(), null);
        }

        public static MerchantPurchase.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantPurchase.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantPurchase.Log) log);
            }
            return new MerchantPurchase.Log.Page(logs, page.cursor);
        }

    }

}
