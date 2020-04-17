package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.HashMap;

public class Webhook extends Resource {
    public static ClassData data = new ClassData(Webhook.class, "Webhook");

    public String url;
    public String[] subscriptions;

    public Webhook(String url, String[] subscriptions, String id){
        super(id);
        this.url = url;
        this.subscriptions = subscriptions;
    }

    public Webhook(HashMap<String, Object> data){
        super(null);
        this.url = (String) data.get("url");
        this.subscriptions = (String[]) data.get("subscriptions");
    }

    public static Webhook get(String id, Project user) throws Exception {
        return Rest.getId(data, id, user);
    }

    public static Webhook get(String id) throws Exception {
        return Rest.getId(data, id, null);
    }

    public static Generator<Webhook> query(HashMap<String, Object> params, Project user) throws Exception {
        return Rest.getList(data, params, user);
    }

    public static Generator<Webhook> query(HashMap<String, Object> params) throws Exception {
        return Rest.getList(data, params, null);
    }

    public static Generator<Webhook> query(Project user) throws Exception {
        return Rest.getList(data, new HashMap<>(), user);
    }

    public static Webhook create(Webhook webhook, Project user) throws Exception {
        return Rest.postSingle(data, webhook, user);
    }

    public static Webhook create(Webhook webhook) throws Exception {
        System.out.println(webhook.url);
        return Rest.postSingle(data, webhook, null);
    }

    public static Webhook delete(String id, Project user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public static Webhook delete(String id) throws Exception {
        return Rest.delete(data, id, null);
    }
}
