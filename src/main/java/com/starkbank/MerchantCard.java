package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MerchantCard extends Resource {

    /**
     # MerchantCard object
     Check out our API Documentation at https://starkbank.com/docs/api#merchant-card
     **/

    static ClassData data = new ClassData(MerchantCard.class, "MerchantCard");

    public String ending;
    public String fundingType;
    public String holderName;
    public String network;
    public String status;
    public List<String> tags;
    public String expiration;
    public String created;
    public String updated;

    public MerchantCard(String id, String ending, String fundingType, String holderName, String network, String status, List<String> tags, String expiration, String created, String updated) {
        super(id);
        this.ending = ending;
        this.fundingType = fundingType;
        this.holderName = holderName;
        this.network = network;
        this.status = status;
        this.tags = tags;
        this.expiration = expiration;
        this.created = created;
        this.updated = updated;
    }

    public static Generator<MerchantCard> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public static Generator<MerchantCard> query(Map<String, Object> params) throws Exception {
        return MerchantCard.query(params, null);
    }

    public static Generator<MerchantCard> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public static MerchantCard get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static MerchantCard get(String id) throws Exception {
        return MerchantCard.get(id, null);
    }

    public static MerchantCard.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    public static MerchantCard.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    public static MerchantCard.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }


    public static MerchantCard.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantCard> MerchantCards = new ArrayList<>();
        for (com.starkcore.utils.SubResource MerchantCard: page.entities) {
            MerchantCards.add((MerchantCard) MerchantCard);
        }
        return new MerchantCard.Page(MerchantCards, page.cursor);
    }

    public final static class Page {
        public List<MerchantCard> merchantCards;
        public String cursor;

        public Page(List<MerchantCard> merchantCards, String cursor) {
            this.merchantCards = merchantCards;
            this.cursor = cursor;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantCard.Log.class, "MerchantCardLog");

        public String created;
        public String type;
        public String[] errors;
        public MerchantCard card;

        public Log(String created, String type, String[] errors, MerchantCard card, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.card = card;
        }


        public static MerchantCard.Log get(String id) throws Exception {
            return MerchantCard.Log.get(id, null);
        }

        public static MerchantCard.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        public static Generator<MerchantCard.Log> query(Map<String, Object> params) throws Exception {
            return MerchantCard.Log.query(params, null);
        }

        public static Generator<MerchantCard.Log> query(User user) throws Exception {
            return MerchantCard.Log.query(new HashMap<>(), user);
        }

        public static Generator<MerchantCard.Log> query() throws Exception {
            return MerchantCard.Log.query(new HashMap<>(), null);
        }

        public static Generator<MerchantCard.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantCard.Log> logs;
            public String cursor;

            public Page(List<MerchantCard.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        public static MerchantCard.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantCard.Log.page(params, null);
        }

        public static MerchantCard.Log.Page page(User user) throws Exception {
            return MerchantCard.Log.page(new HashMap<>(), user);
        }

        public static MerchantCard.Log.Page page() throws Exception {
            return MerchantCard.Log.page(new HashMap<>(), null);
        }

        public static MerchantCard.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantCard.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantCard.Log) log);
            }
            return new MerchantCard.Log.Page(logs, page.cursor);
        }

    }
    
}
