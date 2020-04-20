package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;


public class Transfer extends Resource {
    static ClassData data = new ClassData(Transfer.class, "Transfer");

    public Integer amount;
    public String name;
    public String taxId;
    public String bankCode;
    public String branchCode;
    public String accountNumber;
    public String[] tags;
    public Integer fee;
    public String status;
    public String created;
    public String updated;
    public String[] transactionIds;

    public Transfer(String id, int amount, String name, String taxId, String bankCode, String branchCode,
                    String accountNumber, String[] tags, int fee, String status, String created,
                    String updated, String[] transactionIds) {
        super(id);
        this.amount = amount;
        this.name = name;
        this.taxId = taxId;
        this.bankCode = bankCode;
        this.branchCode = branchCode;
        this.accountNumber = accountNumber;
        this.tags = tags;
        this.fee = fee;
        this.status = status;
        this.created = created;
        this.updated = updated;
        this.transactionIds = transactionIds;
    }


    public Transfer(HashMap<String, Object> data) {
        super(null);
        this.amount = (Integer) data.get("amount");
        this.name = (String) data.get("name");
        this.taxId = (String) data.get("taxId");
        this.bankCode = (String) data.get("bankCode");
        this.branchCode = (String) data.get("branchCode");
        this.accountNumber = (String) data.get("accountNumber");
        this.tags = (String[]) data.get("tags");
    }

    public static Transfer get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static Transfer get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<Transfer> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<Transfer> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<Transfer> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    public static List<Transfer> create(List<Transfer> transfers, Project user) throws Exception {
        return Rest.post(data, transfers, user);
    }

    public static List<Transfer> create(List<Transfer> transfers) throws Exception {
        return Rest.post(data, transfers, null);
    }

    public static InputStream pdf(String id, Project user) throws Exception {
        return Rest.getPdf(data, id, user);
    }

    public static InputStream pdf(String id) throws Exception {
        return Rest.getPdf(data, id, null);
    }

    public static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "TransferLog");

        public String created;
        public String type;
        public String[] errors;
        public Transfer transfer;

        public Log(String created, String type, String[] errors, Transfer transfer, String id){
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.transfer = transfer;
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
