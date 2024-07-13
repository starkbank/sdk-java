import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.starkbank.Request;
import com.starkbank.utils.Response;
import org.junit.Assert;
import org.junit.Test;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;

import java.util.*;

import com.google.gson.*;

public class TestRequest {
    @Test
    public void testRequestGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        String path = "/invoice";
        Map<String, Object> query = new HashMap<>();
        query.put("limit", 10);
        String request = Request.get(path, query).content();

        System.out.println(request);

        Gson gson = new Gson();
        JsonObject contentJson = gson.fromJson(request, JsonObject.class);

        JsonArray requests = contentJson.get("invoices").getAsJsonArray();
        JsonObject requestElement = requests.get(0).getAsJsonObject();

        Assert.assertNotNull(requestElement.get("id"));
    }

    @Test
    public void testRequestPost() throws Exception {
        Settings.user = utils.User.defaultProject();

        String path = "/invoice";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Jaime Lannister" + UUID.randomUUID().toString());
        payload.put("amount", 100);
        payload.put("taxId", "20.018.183/0001-80");

        List<Object> invoice = new ArrayList<Object>();
        invoice.add(payload);

        Map<String, Object> data = new HashMap<>();
        data.put("invoices", invoice);

        String request = Request.post(path, data).content();

        System.out.println(request);

        Gson gson = new Gson();
        JsonObject contentJson = gson.fromJson(request, JsonObject.class);

        JsonArray requests = contentJson.get("invoices").getAsJsonArray();
        JsonObject requestElement = requests.get(0).getAsJsonObject();

        Assert.assertNotNull(requestElement.get("id"));
    }

    @Test
    public void testRequestPatch() throws Exception {
        Settings.user = utils.User.defaultProject();

        String path = "/invoice";

        Map<String, Object> query = new HashMap<>();
        query.put("limit", 1);

        String request = Request.get(path, query).content();

        Gson gson = new Gson();
        JsonObject contentJson = gson.fromJson(request, JsonObject.class);

        JsonArray requests = contentJson.get("invoices").getAsJsonArray();
        JsonObject requestElement = requests.get(0).getAsJsonObject();

        String requestId = requestElement.get("id").getAsString();

        path += "/" + requestId;

        HashMap<String, Object> data = new HashMap<>();;
        data.put("amount", 0);

        request = Request.patch(path, data).content();

        System.out.println(request);

        contentJson = gson.fromJson(request, JsonObject.class);

        JsonObject requestObject = contentJson.get("invoice").getAsJsonObject();

        Assert.assertNotNull(requestObject.get("id"));
    }

    @Test
    public void testRequestPut() throws Exception {
        Settings.user = utils.User.defaultProject();

        String path = "/split-profile";
        Map<String, Object> payload = new HashMap<>();
        payload.put("interval", "day");
        payload.put("delay", 0);

        List<Object> profiles = new ArrayList<Object>();
        profiles.add(payload);

        Map<String, Object> data = new HashMap<>();
        data.put("profiles", profiles);

        String request = Request.put(path, data).content();

        System.out.println(request);

        Gson gson = new Gson();
        JsonObject contentJson = gson.fromJson(request, JsonObject.class);

        JsonArray requests = contentJson.get("profiles").getAsJsonArray();
        JsonObject requestElement = requests.get(0).getAsJsonObject();

        Assert.assertNotNull(requestElement.get("delay"));
        Assert.assertNotNull(requestElement.get("interval"));
    }

    @Test
    public void testRequestDelete() throws Exception {
        Settings.user = utils.User.defaultProject();

        String path = "/transfer";
        Map<String, Object> payload = new HashMap<>();
        payload.put("name", "Jaime Lannister" + UUID.randomUUID().toString());
        payload.put("amount", 100);
        payload.put("taxId", "20.018.183/0001-80");
        payload.put("bankCode", "001");
        payload.put("branchCode", "1234");
        payload.put("accountNumber", "123456-0");
        payload.put("accountType", "checking");
        payload.put("externalId", UUID.randomUUID().toString());

        List<Object> transfers = new ArrayList<Object>();
        transfers.add(payload);

        Map<String, Object> data = new HashMap<>();
        data.put("transfers", transfers);

        String request = Request.post(path, data).content();

        System.out.println(request);

        Gson gson = new Gson();
        JsonObject contentJson = gson.fromJson(request, JsonObject.class);

        JsonArray requests = contentJson.get("transfers").getAsJsonArray();
        JsonObject requestElement = requests.get(0).getAsJsonObject();

        String requestElementId = requestElement.get("id").getAsString();

        path += "/" + requestElementId;

        request = Request.delete(path).content();

        contentJson = gson.fromJson(request, JsonObject.class);

        requestElement = contentJson.get("transfer").getAsJsonObject();

        Assert.assertNotNull(requestElement.get("id"));
    }
}