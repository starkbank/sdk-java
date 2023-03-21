package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;
import javax.imageio.ImageIO;


public final class Workspace extends Resource {
    /**
     * Workspace object
     * <p>
     * Workspaces are bank accounts. They have independent balances, statements, operations and permissions.
     * The only property that is shared between your workspaces is that they are linked to your organization,
     * which carries your basic information, such as tax ID, name, etc..
     * <p>
     * Parameters:
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: "Stark Bank Workspace"
     * allowedTaxIds [list of strings]: list of tax IDs that will be allowed to send Deposits to this Workspace. ex: ["012.345.678-90", "20.018.183/0001-80"]
     * id [string]: unique id returned when the workspace is created. ex: "5656565656565656"
     * status [string]: current Workspace status. Options: "active", "closed", "frozen" or "blocked"
     * organizationId [string]: unique organization id returned when the organization is created. ex: "5656565656565656"
     * pictureUrl [string]: public workspace image (png) URL. ex: "https://storage.googleapis.com/api-ms-workspace-sbx.appspot.com/pictures/workspace/6284441752174592.png?20230208220551"
     * created [string]: creation datetime for the workspace. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(Workspace.class, "Workspace");

    public String username;
    public String name;
    public List<String> allowedTaxIds;
    public String status;
    public String organizationId;
    public String pictureUrl;
    public String created;

    /**
     * Workspace object
     * <p>
     * Workspaces are bank accounts. They have independent balances, statements, operations and permissions.
     * The only property that is shared between your workspaces is that they are linked to your organization,
     * which carries your basic information, such as tax ID, name, etc..
     * <p>
     * Parameters:
     * @param username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
     * @param name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: "Stark Bank Workspace"
     * @param allowedTaxIds [list of strings]: list of tax IDs that will be allowed to send Deposits to this Workspace. ex: ["012.345.678-90", "20.018.183/0001-80"]
     * @param id [string]: unique id returned when the workspace is created. ex: "5656565656565656"
     * @param status [string]: current Workspace status. Options: "active", "closed", "frozen" or "blocked"
     * @param organizationId [string]: unique organization id returned when the organization is created. ex: "5656565656565656"
     * @param pictureUrl [string]: public workspace image (png) URL. ex: "https://storage.googleapis.com/api-ms-workspace-sbx.appspot.com/pictures/workspace/6284441752174592.png?20230208220551"
     * @param created [string]: creation datetime for the workspace. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public Workspace(String username, String name, List<String> allowedTaxIds, String status, String id, String organizationId, String pictureUrl, String created) {
        super(id);
        this.username = username;
        this.name = name;
        this.allowedTaxIds = allowedTaxIds;
        this.status = status;
        this.organizationId = organizationId;
        this.pictureUrl = pictureUrl;
        this.created = created;
    }

    /**
     * Workspace object
     * <p>
     * Workspaces are bank accounts. They have independent balances, statements, operations and permissions.
     * The only property that is shared between your workspaces is that they are linked to your organization,
     * which carries your basic information, such as tax ID, name, etc..
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the WebHook
     * Parameters (required):
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: "Stark Bank Workspace"
     * <p>
     * Parameters (optional):
     * allowedTaxIds [list of strings]: list of tax IDs that will be allowed to send Deposits to this Workspace. ex: ["012.345.678-90", "20.018.183/0001-80"]
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when the workspace is created. ex: "5656565656565656"
     * status [string]: current Workspace status. Options: "active", "closed", "frozen" or "blocked"
     * organizationId [string]: unique organization id returned when the organization is created. ex: "5656565656565656"
     * pictureUrl [string]: public workspace image (png) URL. ex: "https://storage.googleapis.com/api-ms-workspace-sbx.appspot.com/pictures/workspace/6284441752174592.png?20230208220551"
     * created [string]: creation datetime for the workspace. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public Workspace(Map<String, Object> data) {
        super(null);
        this.username = (String) data.get("username");
        this.name = (String) data.get("name");
        this.allowedTaxIds = (List<String>) data.get("allowedTaxIds");
        this.status = (String) data.get("status");
        this.organizationId = (String) data.get("organizationId");
        this.pictureUrl = (String) data.get("pictureUrl");
        this.created = (String) data.get("created");
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
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
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
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
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
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
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
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
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
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: "Stark Bank Workspace"
     * allowedTaxIds [list of strings]: list of tax IDs that will be allowed to send Deposits to this Workspace. If null, all organizations in the same project are allowed. ex: ["012.345.678-90", "20.018.183/0001-80"]
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
     * username [string]: Simplified name to define the workspace URL. This name must be unique across all Stark Bank Workspaces. ex: "starkbankworkspace"
     * name [string]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: "Stark Bank Workspace"
     * allowedTaxIds [list of strings]: list of tax IDs that will be allowed to send Deposits to this Workspace. If null, all organizations in the same project are allowed. ex: ["012.345.678-90", "20.018.183/0001-80"]
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
        return Rest.postSingle(data, new Workspace(username, name, allowedTaxIds, null, null, null, null, null), user);
    }

    /**
     * Update Workspace
     * <p>
     * Update a Workspace by passing its ID.
     * </p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param patchData [map of parameters to patch]: Allowed parameters: "name", "username and "allowedTaxIds"
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. ex: 'starkbankworkspace'
     * name [string, default null]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: 'Stark Bank Workspace'
     * allowedTaxIds [list of strings, default null]: list of tax IDs that will be allowed to send Deposits to this Workspace. If empty, all are allowed. ex: ['012.345.678-90', '20.018.183/0001-80']
     * picture [BufferedImage, default null]: BufferedImage of the picture. ex: ImageIO.read("/path/to/file.png")
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
     * @param patchData [map of parameters to patch]: 
     * username [string, default null]: query by the simplified name that defines the workspace URL. This name is always unique across all Stark Bank Workspaces. ex: 'starkbankworkspace'
     * name [string, default null]: Full name that identifies the Workspace. This name will appear when people access the Workspace on our platform, for example. ex: 'Stark Bank Workspace'
     * allowedTaxIds [list of strings, default null]: list of tax IDs that will be allowed to send Deposits to this Workspace. If empty, all are allowed. ex: ['012.345.678-90', '20.018.183/0001-80']
     * picture [byte[], default null]: BufferedImage of the picture. ex: ImageIO.read("/path/to/file.png")
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if User was set before function call
     * @return target Workspace with updated attributes
     * @throws Exception error in the request
     */
    public static Workspace update(String id, Map<String, Object> patchData, User user) throws Exception {
        if(patchData.containsKey("picture")) {
            patchData.put("picture", "data:" + patchData.get("pictureType") + ";base64," + Base64.getEncoder().encodeToString((byte[]) patchData.get("picture")));
            patchData.remove("pictureType");
        }
        return Rest.patch(data, id, patchData, user);
    }
}
