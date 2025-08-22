package com.starkbank;

import com.starkbank.error.ErrorElement;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantInstallment extends Resource {

    /**
     # MerchantInstallment object
     Check out our API Documentation at https://starkbank.com/docs/api#merchant-installment
     **/

    static ClassData data = new ClassData(MerchantInstallment.class, "MerchantInstallment");

    public long amount;
    public String created;
    public String due;
    public int fee;
    public String fundingType;
    public String network;
    public String purchaseId;
    public String status;
    public String[] tags;
    public List<String> transactionIds;
    public String updated;

    public MerchantInstallment(String id, long amount, String created, String due, int fee, String fundingType, String network, String purchaseId, String status, String[] tags, List<String> transactionIds, String updated) {
        super(id);
        this.amount = amount;
        this.created = created;
        this.due = due;
        this.fee = fee;
        this.fundingType = fundingType;
        this.network = network;
        this.purchaseId = purchaseId;
        this.status = status;
        this.tags = tags;
        this.transactionIds = transactionIds;
        this.updated = updated;
    }

    public static Generator<MerchantInstallment> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public static Generator<MerchantInstallment> query(Map<String, Object> params) throws Exception {
        return MerchantInstallment.query(params, null);
    }

    public static Generator<MerchantInstallment> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public static MerchantInstallment get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static MerchantInstallment get(String id) throws Exception {
        return MerchantInstallment.get(id, null);
    }

    public static MerchantInstallment.Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    public static MerchantInstallment.Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    public static MerchantInstallment.Page page() throws Exception {
        return page(new HashMap<>(), null);
    }


    public static MerchantInstallment.Page page(Map<String, Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<MerchantInstallment> MerchantInstallments = new ArrayList<>();
        for (com.starkcore.utils.SubResource MerchantInstallment: page.entities) {
            MerchantInstallments.add((MerchantInstallment) MerchantInstallment);
        }
        return new MerchantInstallment.Page(MerchantInstallments, page.cursor);
    }

    public final static class Page {
        public List<MerchantInstallment> merchantInstallments;
        public String cursor;

        public Page(List<MerchantInstallment> MerchantInstallments, String cursor) {
            this.merchantInstallments = MerchantInstallments;
            this.cursor = cursor;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(MerchantInstallment.Log.class, "MerchantInstallmentLog");

        public String created;
        public String type;
        public List<ErrorElement>  errors;
        public MerchantInstallment installment;

        public Log(String created, String type, List<ErrorElement>  errors, MerchantInstallment merchantInstallment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.installment = merchantInstallment;
        }


        public static MerchantInstallment.Log get(String id) throws Exception {
            return MerchantInstallment.Log.get(id, null);
        }

        public static MerchantInstallment.Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        public static Generator<MerchantInstallment.Log> query(Map<String, Object> params) throws Exception {
            return MerchantInstallment.Log.query(params, null);
        }

        public static Generator<MerchantInstallment.Log> query(User user) throws Exception {
            return MerchantInstallment.Log.query(new HashMap<>(), user);
        }

        public static Generator<MerchantInstallment.Log> query() throws Exception {
            return MerchantInstallment.Log.query(new HashMap<>(), null);
        }

        public static Generator<MerchantInstallment.Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<MerchantInstallment.Log> logs;
            public String cursor;

            public Page(List<MerchantInstallment.Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        public static MerchantInstallment.Log.Page page(Map<String, Object> params) throws Exception {
            return MerchantInstallment.Log.page(params, null);
        }

        public static MerchantInstallment.Log.Page page(User user) throws Exception {
            return MerchantInstallment.Log.page(new HashMap<>(), user);
        }

        public static MerchantInstallment.Log.Page page() throws Exception {
            return MerchantInstallment.Log.page(new HashMap<>(), null);
        }

        public static MerchantInstallment.Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkcore.utils.Page page = Rest.getPage(data, params, user);
            List<MerchantInstallment.Log> logs = new ArrayList<>();
            for (com.starkcore.utils.SubResource log: page.entities) {
                logs.add((MerchantInstallment.Log) log);
            }
            return new MerchantInstallment.Log.Page(logs, page.cursor);
        }

    }
    
}
