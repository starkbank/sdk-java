package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


public class BoletoPayment extends Resource {
    static ClassData data = new ClassData(BoletoPayment.class, "BoletoPayment");

    public Integer amount;
    public String taxId;
    public String description;
    public String line;
    public String barCode;
    public String scheduled;
    public String[] tags;
    public String status;
    public Integer fee;
    public String created;

    public BoletoPayment(int amount, String taxId, String[] tags, String description, String scheduled,
                         String line, String barCode, String id, int fee, String status, String created
    ) {
        super(id);
        this.taxId = taxId;
        this.description = description;
        this.line = line;
        this.barCode = barCode;
        this.scheduled = scheduled;
        this.tags = tags;
        this.amount = amount;
        this.status = status;
        this.fee = fee;
        this.created = created;
    }

    public BoletoPayment(HashMap<String, Object> data) {
        super(null);
        this.taxId = (String) data.get("taxId");
        this.description = (String) data.get("description");
        this.line = (String) data.get("line");
        this.barCode = (String) data.get("barCode");
        this.scheduled = (String) data.get("scheduled");
        this.tags = (String[]) data.get("tags");
    }

    public static BoletoPayment get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static BoletoPayment get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<BoletoPayment> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<BoletoPayment> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<BoletoPayment> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    public static List<BoletoPayment> create(List<BoletoPayment> boletoPayments, Project user) throws Exception {
        return Rest.post(data, boletoPayments, user);
    }

    public static List<BoletoPayment> create(List<BoletoPayment> boletoPayments) throws Exception {
        return Rest.post(data, boletoPayments, null);
    }

    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    public static InputStream pdf(String id) throws Exception {
        return Rest.getPdf(data, id, null);
    }

    public static BoletoPayment delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public static BoletoPayment delete(String id) throws Exception {
        return Rest.delete(data, id, null);
    }

    public static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "BoletoPaymentLog");

        public String created;
        public String type;
        public String[] errors;
        public BoletoPayment payment;

        public Log(String created, String type, String[] errors, BoletoPayment payment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.payment = payment;
        }

        public static Log get(String id, Project user) throws Exception {
            return Rest.getId(data, id, user);
        }

        public static Log get(String id) throws Exception {
            return Rest.getId(data, id, null);
        }

        public static Generator<Log> query(HashMap<String, Object> params, Project user) throws Exception {
            return Rest.getList(data, params, user);
        }

        public static Generator<Log> query(HashMap<String, Object> params) throws Exception {
            return Rest.getList(data, params, null);
        }

        public static Generator<Log> query(Project user) throws Exception {
            return Rest.getList(data, new HashMap<>(), user);
        }
    }
}
