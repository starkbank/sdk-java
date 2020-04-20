package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.HashMap;
import java.util.List;


public class Transaction extends Resource {
    static ClassData data = new ClassData(Transaction.class, "Transaction");

    public int amount;
    public String description;
    public String externalId;
    public String receiverId;
    public String[] tags;
    public Integer fee;
    public String created;
    public String source;

    public Transaction(int amount, String description, String externalId, String receiverId, String[] tags,
                       int fee, String created, String source, String id){
        super(id);
        this.amount = amount;
        this.description = description;
        this.externalId = externalId;
        this.receiverId = receiverId;
        this.tags = tags;
        this.fee = fee;
        this.created = created;
        this.source = source;
    }

    public Transaction(HashMap<String, Object> input){
        super(null);
        this.amount = (int) input.get("amount");
        this.description = (String) input.get("description");
        this.externalId = (String) input.get("externalId");
        this.receiverId = (String) input.get("receiverId");
        this.tags = (String[]) input.get("tags");
    }

    public static Transaction get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static Transaction get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<Transaction> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<Transaction> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<Transaction> query(Project project) throws Exception {
        return Rest.getList(data, new HashMap<>(), project);
    }

    public static List<Transaction> create(List<Transaction> transactions) throws Exception {
        return Rest.post(data, transactions, null);
    }
}
