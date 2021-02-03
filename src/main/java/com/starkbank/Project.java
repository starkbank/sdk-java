package com.starkbank;

public final class Project extends User {
    static ClassData data = new ClassData(Project.class, "Project");

    /**
     * Project object
     * <p>
     * The Project object is an authentication entity for the SDK that is permanently
     * linked to a specific Workspace.
     * All requests to the Stark Bank API must be authenticated via an SDK user,
     * which must have been previously created at the Stark Bank website
     * [https://web.sandbox.starkbank.com] or [https://web.starkbank.com]
     * before you can use it in this SDK. Projects may be passed as the user parameter on
     * each request or may be defined as the default user at the start (See README).
     * <p>
     * Parameters (required):
     * @param id [string]: unique id required to identify project. ex: "5656565656565656"
     * @param privateKey [EllipticCurve.Organization()]: PEM string of the private key linked to the project. ex: "-----BEGIN PUBLIC KEY-----\nMFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEyTIHK6jYuik6ktM9FIF3yCEYzpLjO5X/\ntqDioGM+R2RyW0QEo+1DG8BrUf4UXHSvCjtQ0yLppygz23z0yPZYfw==\n-----END PUBLIC KEY-----"
     * @param environment [string]: environment where the project is being used. ex: "sandbox" or "production"
     * <p>
     * Return:
     * @throws Exception error in the request
     */
    public Project(String environment, String id, String privateKey) throws Exception {
        super(environment, id, privateKey);
    }

    public String accessId() {
        return "project/" + this.id;
    }
}
