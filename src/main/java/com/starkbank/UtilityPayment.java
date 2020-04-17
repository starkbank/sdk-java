package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

public class UtilityPayment extends Resource {
    public static ClassData data = new ClassData(UtilityPayment.class, "UtilityPayment");

    public String description;
    public String line;
    public String barCode;
    public String scheduled;
    public String[] tags;
    public String status;
    public Integer amount;
    public Integer fee;
    public String created;

    public UtilityPayment(Integer amount, String[] tags, String description, String scheduled,
                          String line, String barCode, String id, Integer fee, String status, String created
    ) {
        super(id);
        this.description = description;
        this.line = line;
        this.barCode = barCode;
        this.scheduled = scheduled;
        this.tags = tags;
        this.status = status;
        this.amount = amount;
        this.fee = fee;
        this.created = created;
    }

    public UtilityPayment(HashMap<String, Object> data) {
        super(null);
        this.description = (String) data.get("description");
        this.line = (String) data.get("line");
        this.barCode = (String) data.get("barCode");
        this.scheduled = (String) data.get("scheduled");
        this.tags = (String[]) data.get("tags");
    }

    public static UtilityPayment get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static UtilityPayment get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<UtilityPayment> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<UtilityPayment> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<UtilityPayment> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    public static List<UtilityPayment> create(List<UtilityPayment> utilityPayments, Project user) throws Exception {
        return Rest.post(data, utilityPayments, user);
    }

    public static List<UtilityPayment> create(List<UtilityPayment> utilityPayments) throws Exception {
        return Rest.post(data, utilityPayments, null);
    }

    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    public static InputStream pdf(String id) throws Exception {
        return Rest.getPdf(data, id, null);
    }

    public static UtilityPayment delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public static UtilityPayment delete(String id) throws Exception {
        return Rest.delete(data, id, null);
    }

    public static class Log extends Resource {
        public static ClassData data = new ClassData(Log.class, "UtilityPaymentLog");

        public String created;
        public String type;
        public String[] errors;
        public UtilityPayment payment;

        public Log(String created, String type, String[] errors, UtilityPayment payment, String id) {
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
