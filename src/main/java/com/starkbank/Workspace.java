package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public final class Workspace extends Resource {
    static ClassData data = new ClassData(Workspace.class, "Workspace");

    public String username;
    public String name;
    public List<String> allowedTaxIds;

    /**
     * Workspace object
     * <p>
     * Workspaces are bank accounts. They have independent balances, statements, operations and permissions.
     * The only property that is shared between your workspaces is that they are linked to your organization,
     * which carries your basic informations, such as tax ID, name, etc..
     * <p>
     * Parameters:
     * @param username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * @param name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. Ex: "Stark Bank Workspace"
     * @param allowedTaxIds [list of strings]: list of tax IDs that will be allowed to send Deposits to this Workspace. ex: ["012.345.678-90", "20.018.183/0001-80"]
     * Attributes:
     * @param id [string, default null]: unique id returned when the workspace is created. ex: "5656565656565656"
     */
    public Workspace(String username, String name, List<String> allowedTaxIds, String id) {
        super(id);
        this.username = username;
        this.name = name;
        this.allowedTaxIds = allowedTaxIds;
    }

    /**
     * Workspace object
     * <p>
     * Workspaces are bank accounts. They have independent balances, statements, operations and permissions.
     * The only property that is shared between your workspaces is that they are linked to your organization,
     * which carries your basic informations, such as tax ID, name, etc..
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the WebHook
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. Ex: "Stark Bank Workspace"
     * Attributes:
     * id [string, default null]: unique id returned when the workspace is created. ex: "5656565656565656"
     */
    public Workspace(Map<String, Object> data) {
        super(null);
        this.username = (String) data.get("username");
        this.name = (String) data.get("name");
    }

    /**
     * Retrieve a specific Workspace
     * <p>
     * Receive a single Workspace object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Workspace object with updated attributes
     * @throws Exception error in the request
     */
    public static Workspace get(String id) throws Exception {
        return Workspace.get(id, null);
    }

    /**
     * Retrieve a specific Workspace
     * <p>
     * Receive a single Workspace object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Workspace object with updated attributes
     * @throws Exception error in the request
     */
    public static Workspace get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Workspaces
     * <p>
     * Receive a generator of Workspace objects previously created in the Stark Bank API.
     * If no filters are passed and the user is an Organization, all of the Organization Workspaces
     * will be retrieved.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of Workspace objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Workspace> query(Map<String, Object> params) throws Exception {
        return Workspace.query(params, null);
    }

    /**
     * Retrieve Workspaces
     * <p>
     * Receive a generator of Workspace objects previously created in the Stark Bank API.
     * If no filters are passed and the user is an Organization, all of the Organization Workspaces
     * will be retrieved.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Workspace objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Workspace> query(User user) throws Exception {
        return Workspace.query(new HashMap<>(), user);
    }

    /**
     * Retrieve Workspace
     * <p>
     * Receive a generator of Workspace objects previously created in the Stark Bank API.
     * If no filters are passed and the user is an Organization, all of the Organization Workspaces
     * will be retrieved.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of Workspace objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Workspace> query() throws Exception {
        return Workspace.query(new HashMap<>(), null);
    }

    /**
     * Retrieve Workspaces
     * <p>
     * Receive a generator of Workspace objects previously created in the Stark Bank API.
     * If no filters are passed and the user is an Organization, all of the Organization Workspaces
     * will be retrieved.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of Workspace objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Workspace> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public final static class Page {
        public List<Workspace> workspaces;
        public String cursor;

        public Page(List<Workspace> workspaces, String cursor) {
            this.workspaces = workspaces;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged Workspaces
     * <p>
     * Receive a list of up to 100 Workspace objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return Workspace.Page object:
     * Workspace.Page.workspaces: list of Workspace objects with updated attributes
     * Workspace.Page.cursor: cursor to retrieve the next page of Workspace objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged Workspaces
     * <p>
     * Receive a list of up to 100 Workspace objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Workspace.Page object:
     * Workspace.Page.workspaces: list of Workspace objects with updated attributes
     * Workspace.Page.cursor: cursor to retrieve the next page of Workspace objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged Workspaces
     * <p>
     * Receive a list of up to 100 Workspace objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return Workspace.Page object:
     * Workspace.Page.workspaces: list of Workspace objects with updated attributes
     * Workspace.Page.cursor: cursor to retrieve the next page of Workspace objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged Workspaces
     * <p>
     * Receive a list of up to 100 Workspace objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Workspace.Page object:
     * Workspace.Page.workspaces: list of Workspace objects with updated attributes
     * Workspace.Page.cursor: cursor to retrieve the next page of Workspace objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<Workspace> workspaces = new ArrayList<>();
        for (SubResource workspace: page.entities) {
            workspaces.add((Workspace) workspace);
        }
        return new Page(workspaces, page.cursor);
    }

    /**
     * Create Workspace
     * <p>
     * Send a single Workspace for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param workspaceData parameters for the creation of the workspace
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. Ex: "Stark Bank Workspace"
     * <p>
     * Return:
     * @return Workspace object with updated attributes
     * @throws Exception error in the request
     */
    public static Workspace create(Map<String, Object> workspaceData) throws Exception {
        return Workspace.create(workspaceData, null);
    }

    /**
     * Create Workspace
     * <p>
     * Send a single Workspace for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param workspaceData parameters for the creation of the workspace
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. Ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. Ex: "Stark Bank Workspace"
     * @param user [Organization object]: Organization object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Workspace object with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static Workspace create(Map<String, Object> workspaceData, Organization user) throws Exception {
        String username = (String) workspaceData.get("username");
        String name = (String) workspaceData.get("name");
        List<String> allowedTaxIds = (List<String>) workspaceData.get("allowedTaxIds");
        return Rest.postSingle(data, new Workspace(username, name, allowedTaxIds, null), user);
    }

    /**
     * Update Workspace
     * <p>
     * Update a Workspace by passing its ID.
     * </p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param patchData [map of parameters to patch]: Allowed parameters: "name", "username and "allowedTaxIds"
     * @return target Workspace with updated attributes
     * @throws Exception error in the request
     */
    public static Workspace update(String id, Map<String, Object> patchData) throws Exception {
        return update(id, patchData, null);
    }

    /**
     * Update Workspace
     * <p>
     * Update a Workspace by passing its ID.
     * </p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param patchData [map of parameters to patch]: Allowed parameters: "name", "username and "allowedTaxIds"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if User was set before function call
     * @return target Workspace with updated attributes
     * @throws Exception error in the request
     */
    public static Workspace update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }
}
