package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


public class Boleto extends Resource {
    static ClassData data = new ClassData(Boleto.class, "Boleto");

    public Integer amount;
    public String name;
    public String taxId;
    public String streetLine1;
    public String streetLine2;
    public String district;
    public String city;
    public String stateCode;
    public String zipCode;
    public String due;
    public Double fine;
    public Double interest;
    public Integer overdueLimit;
    public String[] tags;
    public List<HashMap<String, String>> descriptions;
    public Integer fee;
    public String line;
    public String barCode;
    public String status;
    public String created;

    public Boleto(Integer amount, String name, String taxId, String streetLine1, String streetLine2,
                  String district, String city, String stateCode, String zipCode, String due, Double fine,
                  Double interest, Integer overdueLimit, String[] tags, List<HashMap<String, String>> descriptions, String id,
                  Integer fee, String line, String barCode, String status, String created
    ) {
        super(id);
        this.amount = amount;
        this.name = name;
        this.taxId = taxId;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.district = district;
        this.city = city;
        this.stateCode = stateCode;
        this.zipCode = zipCode;
        this.due = due;
        this.fine = fine;
        this.interest = interest;
        this.overdueLimit = overdueLimit;
        this.tags = tags;
        this.descriptions = descriptions;
        this.fee = fee;
        this.line = line;
        this.barCode = barCode;
        this.status = status;
        this.created = created;
    }

    public Boleto(HashMap<String, Object> data) {
        super(null);
        this.amount = (Integer) data.get("amount");
        this.name = (String) data.get("name");
        this.taxId = (String) data.get("taxId");
        this.streetLine1 = (String) data.get("streetLine1");
        this.streetLine2 = (String) data.get("streetLine2");
        this.district = (String) data.get("district");
        this.city = (String) data.get("city");
        this.stateCode = (String) data.get("stateCode");
        this.zipCode = (String) data.get("zipCode");
        this.due = (String) data.get("due");
        this.fine = (Double) data.get("fine");
        this.interest = (Double) data.get("interest");
        this.overdueLimit = (Integer) data.get("overdueLimit");
        this.tags = (String[]) data.get("tags");
        this.descriptions = (List<HashMap<String, String>>) data.get("descriptions");
    }

    public static Boleto get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static Boleto get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<Boleto> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<Boleto> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<Boleto> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    public static List<Boleto> create(List<Boleto> boletos, Project user) throws Exception {
        return Rest.post(data, boletos, user);
    }

    public static List<Boleto> create(List<Boleto> boletos) throws Exception {
        return Rest.post(data, boletos, null);
    }

    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    public static InputStream pdf(String id) throws Exception {
        return Rest.getPdf(data, id, null);
    }

    public static Boleto delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public static Boleto delete(String id) throws Exception {
        return Rest.delete(data, id, null);
    }

    public static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "BoletoLog");

        public String created;
        public String type;
        public String[] errors;
        public Boleto boleto;

        public Log(String created, String type, String[] errors, Boleto boleto, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.boleto = boleto;
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
