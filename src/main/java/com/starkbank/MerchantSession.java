package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MerchantSession extends Resource {

    /**
        # MerchantSession object
        Check out our API Documentation at https://starkbank.com/docs/api#merchant-session
    **/

    static ClassData data = new ClassData(MerchantSession.class, "MerchantSession");

    public List<String> allowedFundingTypes;
    public List<AllowedInstallment> allowedInstallments;
    public List<String> allowedIps;
    public String challengeMode;
    public String created;
    public Number expiration;
    public String status;
    public String[] tags;
    public String updated;
    public String uuid;

    public MerchantSession(String id, List<String> allowedFundingTypes, List<AllowedInstallment> allowedInstallments, List<String> allowedIps, String challengeMode, String created, Number expiration, String status, String[] tags, String updated, String uuid)  {
        super(id);
        this.allowedFundingTypes = allowedFundingTypes;
        this.allowedInstallments = allowedInstallments;
        this.allowedIps = allowedIps;
        this.challengeMode = challengeMode;
        this.created = created;
        this.expiration = expiration;
        this.status = status;
        this.tags = tags;
        this.updated = updated;
        this.uuid = uuid;
    }


    public MerchantSession(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.allowedFundingTypes = (List<String>) dataCopy.remove("allowedFundingTypes");
        this.allowedInstallments = parseAllowedInstallments((List<Object>) dataCopy.remove("allowedInstallments"));
        this.challengeMode = (String) dataCopy.remove("challengeMode");
        this.expiration = (Number) dataCopy.remove("expiration");
        this.tags = (String[]) dataCopy.remove("tags");
        this.uuid = null;
        this.status = null;
        this.allowedIps = null;
        this.created = null;
        this.updated = null;


        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }


    private List<AllowedInstallment> parseAllowedInstallments(List<Object> allowedInstallments) {
        if (allowedInstallments == null)
            return null;

        List<AllowedInstallment> parsed = new ArrayList<>();
        if (allowedInstallments.isEmpty() || allowedInstallments.get(0) instanceof AllowedInstallment) {
            for (Object allowedInstallment : allowedInstallments) {
                parsed.add((AllowedInstallment) allowedInstallment);
            }

            return parsed;
        }

        for (Object allowedInstallment : allowedInstallments) {
            AllowedInstallment allowedInstallmentObject = new AllowedInstallment(
                    (Long) ((Map<String, Object>) allowedInstallment).get("totalAmount"),
                    (Integer) ((Map<String, Object>) allowedInstallment).get("count")
            );
            parsed.add(allowedInstallmentObject);
        }

        return parsed;
    }

    public static MerchantSession create(MerchantSession merchantSession) throws Exception {
        return Rest.postSingle(data, merchantSession, null);
    }

    public static MerchantSession create(MerchantSession merchantSession, User user) throws Exception {
        return Rest.postSingle(data, merchantSession, user);
    }

    public static Purchase purchase(String uuid, Purchase purchase) throws Exception {
        return Rest.postSubResource(data, uuid, Purchase.data, null, purchase);
    }

    public static Purchase purchase(String uuid, Purchase purchase, User user) throws Exception {
        return Rest.postSubResource(data, uuid, Purchase.data, user, purchase);
    }


    public static MerchantSession get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static MerchantSession get(String id) throws Exception {
        return MerchantSession.get(id, null);
    }

    public static Generator<MerchantSession> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public static Generator<MerchantSession> query(Map<String, Object> params) throws Exception {
        return MerchantSession.query(params, null);
    }

    public static Generator<MerchantSession> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public static MerchantSession.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    public static MerchantSession.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    public static MerchantSession.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    public static MerchantSession.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantSession> merchantSessions = new ArrayList<>();
        for (com.starkcore.utils.SubResource merchantSession: page.entities) {
            merchantSessions.add((MerchantSession) merchantSession);
        }
        return new MerchantSession.Page(merchantSessions, page.cursor);
    }

    public final static class Page {
        public List<MerchantSession> merchantSessions;
        public String cursor;

        public Page(List<MerchantSession> merchantSessions, String cursor) {
            this.merchantSessions = merchantSessions;
            this.cursor = cursor;
        }
    }

    public static class AllowedInstallment extends SubResource {

        static SubResource.ClassData data = new SubResource.ClassData(AllowedInstallment.class, "AllowedInstallments");

        public long totalAmount;
        public int count;

        public AllowedInstallment(long totalAmount, int count){
            this.totalAmount = totalAmount;
            this.count = count;
        }
    }

    public final static class Purchase extends Resource {

        static ClassData data = new ClassData(Purchase.class, "Purchase");

        public Long amount;
        public Integer installmentCount;
        public String cardId;
        public String cardExpiration;
        public String cardNumber;
        public String cardSecurityCode;
        public String holderName;
        public String holderEmail;
        public String holderPhone;
        public String fundingType;
        public String billingCountryCode;
        public String billingCity;
        public String billingStateCode;
        public String billingStreetLine1;
        public String billingStreetLine2;
        public String billingZipCode;
        public String challengeMode;
        public Map<String, Object> metadata;
        public String network;
        public String source;
        public String status;
        public String[] tags;
        public String update;

        public Purchase(String id, Long amount, Integer installmentCount, String cardId, String cardExpiration, String cardNumber, String cardSecurityCode, String holderName, String holderEmail, String holderPhone, String fundingType, String billingCountryCode, String billingCity, String billingStateCode, String billingStreetLine1, String billingStreetLine2, String billingZipCode, String challengeMode, Map<String, Object> metadata) {
            super(id);
            this.amount = amount;
            this.installmentCount = installmentCount;
            this.cardId = cardId;
            this.cardExpiration = cardExpiration;
            this.cardNumber = cardNumber;
            this.cardSecurityCode = cardSecurityCode;
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
            this.challengeMode = challengeMode;
            this.metadata = metadata;
        }

        public Purchase(Map<String, Object> data) throws Exception  {

            super(null);
            HashMap<String, Object> dataCopy = new HashMap<>(data);

            this.amount = (Long) dataCopy.remove("amount");
            this.cardId = (String) dataCopy.remove("cardId");
            this.cardExpiration = (String) dataCopy.remove("cardExpiration");
            this.cardNumber = (String) dataCopy.remove("cardNumber");
            this.cardSecurityCode = (String) dataCopy.remove("cardSecurityCode");
            this.holderName = (String) dataCopy.remove("holderName");
            this.fundingType = (String) dataCopy.remove("fundingType");
            this.installmentCount = (Integer) dataCopy.remove("installmentCount");
            this.holderEmail = (String) dataCopy.remove("holderEmail");
            this.holderPhone = (String) dataCopy.remove("holderPhone");
            this.billingCountryCode = (String) dataCopy.remove("billingCountryCode");
            this.billingCity = (String) dataCopy.remove("billingCity");
            this.billingStateCode = (String) dataCopy.remove("billingStateCode");
            this.billingStreetLine1 = (String) dataCopy.remove("billingStreetLine1");
            this.billingStreetLine2 = (String) dataCopy.remove("billingStreetLine2");
            this.billingZipCode = (String) dataCopy.remove("billingZipCode");
            this.metadata =  (Map<String, Object>) dataCopy.remove("metadata");
            this.tags = (String[]) dataCopy.remove("tags");
            this.challengeMode = null;
            this.network = null;
            this.source = null;
            this.status = null;
            this.update = null;

            if (!dataCopy.isEmpty()) {
                throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
            }
        }

    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantSession.Log.class, "MerchantSessionLog");

        public String created;
        public String type;
        public String[] errors;
        public MerchantSession session;

        public Log(String created, String type, String[] errors, MerchantSession session, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.session = session;
        }


        public static MerchantSession.Log get(String id) throws Exception {
            return MerchantSession.Log.get(id, null);
        }

        public static MerchantSession.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        public static Generator<MerchantSession.Log> query(Map<String, Object> params) throws Exception {
            return MerchantSession.Log.query(params, null);
        }

        public static Generator<MerchantSession.Log> query(User user) throws Exception {
            return MerchantSession.Log.query(new HashMap<>(), user);
        }

        public static Generator<MerchantSession.Log> query() throws Exception {
            return MerchantSession.Log.query(new HashMap<>(), null);
        }

        public static Generator<MerchantSession.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantSession.Log> logs;
            public String cursor;

            public Page(List<MerchantSession.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        public static MerchantSession.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantSession.Log.page(params, null);
        }

        public static MerchantSession.Log.Page page(User user) throws Exception {
            return MerchantSession.Log.page(new HashMap<>(), user);
        }

        public static MerchantSession.Log.Page page() throws Exception {
            return MerchantSession.Log.page(new HashMap<>(), null);
        }

        public static MerchantSession.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantSession.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantSession.Log) log);
            }
            return new MerchantSession.Log.Page(logs, page.cursor);
        }

    }

}
