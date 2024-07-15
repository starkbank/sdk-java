package com.starkbank;

import com.google.gson.JsonObject;
import com.starkbank.utils.Response;
import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkcore.utils.SubResource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class Request {

    static String prefix = "Joker";

    /**
     * Retrieve any StarkBank resource.
     * <p>
     * Receive a JSON of resources previously created in StarkBank's API.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     */

    public static Response get(String path) throws Exception {
        return Rest.getRaw(path, null, null, prefix, false);
    }

    /**
     * Retrieve any StarkBank resource.
     * <p>
     * Receive a JSON of resources previously created in StarkBank's API.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response get(String path, User user) throws Exception {
        return Rest.getRaw(path, null, user, prefix, false);
    }

    /**
     * Retrieve any StarkBank resource.
     * <p>
     * Receive a JSON of resources previously created in StarkBank's API.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * query [Map<String, Object>]: Query parameters. Example: new HashMap<String, Object>() {{ put("limit", 1); put("status", "paid"); }}
     */

    public static Response get(String path, Map<String, Object> query) throws Exception {
        return Rest.getRaw(path, query, null, prefix, false);
    }

    /**
     * Retrieve any StarkBank resource.
     * <p>
     * Receive a JSON of resources previously created in StarkBank's API.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * query [Map<String, Object>]: Query parameters. Example: new HashMap<String, Object>() {{ put("limit", 1); put("status", "paid"); }}
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response get(String path, Map<String, Object> query, User user ) throws Exception {
        return Rest.getRaw(path, query, user, prefix, false);
    }

    /**
     * Create any StarkBank resource.
     * <p>
     * Send a list of JSONs and create any StarkBank resource objects.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("invoices", new ArrayList<Map<String, Object>>() {{
     *           add(new HashMap<String, Object>() {{
     *               put("amount", 100);
     *               put("name", "Iron Bank S.A.");
     *               put("taxId", "20.018.183/0001-80");
     *           }});
     *       }});
     *   }});
     *   </pre>
     * query [Map<String, Object>]: Query parameters. Example: new HashMap<String, Object>() {{ put("limit", 1); put("status", "paid"); }}
     */

    public static Response post(String path, Map<String, Object> payload) throws Exception {
        return Rest.postRaw(path, payload, null, prefix, false);
    }

    /**
     * Create any StarkBank resource.
     * <p>
     * Send a list of JSONs and create any StarkBank resource objects.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("invoices", new ArrayList<Map<String, Object>>() {{
     *           add(new HashMap<String, Object>() {{
     *               put("amount", 100);
     *               put("name", "Iron Bank S.A.");
     *               put("taxId", "20.018.183/0001-80");
     *           }});
     *       }});
     *   }});
     *   </pre>
     * query [Map<String, Object>]: Query parameters. Example: new HashMap<String, Object>() {{ put("limit", 1); put("status", "paid"); }}
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response post(String path, Map<String, Object> payload, User user ) throws Exception {
        return Rest.postRaw(path, payload, user, prefix, false);
    }

    /**
     * Update any StarkBank resource.
     * <p>
     * Send a JSON with parameters of a single StarkBank resource object and update it.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * <br>
     * - body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("invoices", new HashMap<String, Object>() {{
     *           put("amount", 100);
     *       }});
     *   }});
     *   </pre>
     */

    public static Response patch(String path, Map<String, Object> payload) throws Exception {
        return Rest.patchRaw(path, payload, null, prefix, false);
    }

    /**
     * Update any StarkBank resource.
     * <p>
     * Send a JSON with parameters of a single StarkBank resource object and update it.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * <br>
     * - body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("invoices", new HashMap<String, Object>() {{
     *           put("amount", 100);
     *           put("name", "Iron Bank S.A.");
     *           put("taxId", "20.018.183/0001-80");
     *       }});
     *   }});
     *   </pre>
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response patch(String path, Map<String, Object> payload, User user ) throws Exception {
        return Rest.patchRaw(path, payload, user, prefix, false);
    }

    /**
     * Put any StarkBank resource.
     * <p>
     * Send a JSON with parameters of a single StarkBank resource object and create it.
     * If the resource already exists, it will be updated.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/split-profie/"
     * body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("profiles", new HashMap<String, Object>() {{
     *           put("interval", "day");
     *           put("delay", 0);
     *       }});
     *   }});
     *   </pre>
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response put(String path, Map<String, Object> payload) throws Exception {
        return Rest.putRaw(path, payload, null, prefix, false);
    }

    /**
     * Put any StarkBank resource.
     * <p>
     * Send a JSON with parameters of a single StarkBank resource object and create it.
     * If the resource already exists, it will be updated.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/split-profie/"
     * body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("profiles", new HashMap<String, Object>() {{
     *           put("interval", "day");
     *           put("delay", 0);
     *       }});
     *   }});
     *   </pre>
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response put(String path, Map<String, Object> payload, User user ) throws Exception {
        return Rest.putRaw(path, payload, user, prefix, false);
    }

    /**
     * Delete any StarkBank resource.
     * <p>
     * Send a JSON with parameters of a single StarkBank resource object and delete it.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/invoice/"
     * body [Map<String, Object>]: Request parameters. Example:
     *   <pre>
     *   new HashMap<String, Object>() {{
     *       put("invoices", new HashMap<String, Object>() {{
     *           put("amount", 100);
     *           put("name", "Iron Bank S.A.");
     *           put("taxId", "20.018.183/0001-80");
     *       }});
     *   }});
     *   </pre>
     */

    public static Response delete(String path) throws Exception {
        return Request.delete(path, null);
    }

    /**
     * Delete any StarkBank resource.
     * <p>
     * Send a JSON with parameters of a single StarkBank resource object and delete it.
     * <p>
     * Parameters:
     * path [String]: StarkBank resource's route. Example: "/transfer/"
     * user [Organization/Project object]: Organization or Project object. Not necessary if StarkBank.Settings.User was set before function call.
     */

    public static Response delete(String path, User user) throws Exception {
        return Rest.deleteRaw(path, null, user, prefix, false);
    }
}