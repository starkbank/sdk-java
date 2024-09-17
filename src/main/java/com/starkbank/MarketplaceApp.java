package com.starkbank;

public final class MarketplaceApp extends User{
    static ClassData data = new ClassData(MarketplaceApp.class, "MarketplaceApp");

    public final String authorizationId;

    /**
     * MarketplaceApp object
     * <p>
     * The MarketplaceApp object is an authentication entity for the SDK that
     * represents your entire MarketplaceApp, being able to access any Workspace
     * underneath it and even create new Workspaces. Only a legal representative
     * of your MarketplaceApp can register or change the MarketplaceApp credentials.
     * All requests to the Stark Bank API must be authenticated via an SDK user,
     * which must have been previously created at the Stark Bank website
     * [https://web.sandbox.starkbank.com] or [https://web.starkbank.com]
     * before you can use it in this SDK. MarketplaceApps may be passed as the user parameter on
     * each request or may be defined as the default user at the start (See README).
     * If you are accessing a specific Workspace using MarketplaceApp credentials, you should
     * specify the workspace ID when building the MarketplaceApp object or by request, using
     * the MarketplaceApp.set_workspace(workspace_id) method, which creates a copy of the MarketplaceApp
     * object with the altered workspace ID. If you are listing or creating new Workspaces, the
     * workspace_id should be null.
     * <p>
     * Parameters (required):
     * @param environment [string]: environment where the MarketplaceApp is being used. ex: "sandbox" or "production"
     * @param id [string]: unique id required to identify MarketplaceApp. ex: "5656565656565656"
     * @param privateKey [EllipticCurve.MarketplaceApp()]: PEM string of the private key linked to the MarketplaceApp. ex: "-----BEGIN PUBLIC KEY-----\nMFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEyTIHK6jYuik6ktM9FIF3yCEYzpLjO5X/\ntqDioGM+R2RyW0QEo+1DG8BrUf4UXHSvCjtQ0yLppygz23z0yPZYfw==\n-----END PUBLIC KEY-----"
     * <p>
     * Return:
     * @throws Exception error in the request
     */
    public MarketplaceApp(String environment, String id, String privateKey) throws Exception {
        super(environment, id, privateKey);
        this.authorizationId = null;
    }

    /**
     * MarketplaceApp object
     * <p>
     * The MarketplaceApp object is an authentication entity for the SDK that
     * represents your entire MarketplaceApp, being able to access any Workspace
     * underneath it and even create new Workspaces. Only a legal representative
     * of your MarketplaceApp can register or change the MarketplaceApp credentials.
     * All requests to the Stark Bank API must be authenticated via an SDK user,
     * which must have been previously created at the Stark Bank website
     * [https://web.sandbox.starkbank.com] or [https://web.starkbank.com]
     * before you can use it in this SDK. MarketplaceApps may be passed as the user parameter on
     * each request or may be defined as the default user at the start (See README).
     * If you are accessing a specific Workspace using MarketplaceApp credentials, you should
     * specify the workspace ID when building the MarketplaceApp object or by request, using
     * the MarketplaceApp.set_workspace(workspace_id) method, which creates a copy of the MarketplaceApp
     * object with the altered workspace ID. If you are listing or creating new Workspaces, the
     * workspace_id should be null.
     * <p>
     * Parameters (required):
     * @param environment [string]: environment where the MarketplaceApp is being used. ex: "sandbox" or "production"
     * @param id [string]: unique id required to identify MarketplaceApp. ex: "5656565656565656"
     * @param privateKey [EllipticCurve.MarketplaceApp()]: PEM string of the private key linked to the MarketplaceApp. ex: "-----BEGIN PUBLIC KEY-----\nMFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEyTIHK6jYuik6ktM9FIF3yCEYzpLjO5X/\ntqDioGM+R2RyW0QEo+1DG8BrUf4UXHSvCjtQ0yLppygz23z0yPZYfw==\n-----END PUBLIC KEY-----"
     * @param authorizationId [string]: unique id of the accessed Workspace, if any. ex: null or "4848484848484848"
     * <p>
     * Return:
     * @throws Exception error in the request
     */
    public MarketplaceApp(String environment, String id, String privateKey, String authorizationId) throws Exception {
        super(environment, id, privateKey);
        this.authorizationId = authorizationId;
    }

    public String accessId() {
        if (this.authorizationId != null)
            return "marketplace-app-authorization/" + this.authorizationId;
        return "marketplace-app/" + this.id;
    }

    public static MarketplaceApp replace(MarketplaceApp MarketplaceApp, String authorizationId) throws Exception {
        return new MarketplaceApp(
            MarketplaceApp.environment,
            MarketplaceApp.id,
            MarketplaceApp.pem,
            authorizationId
        );
    }

}
