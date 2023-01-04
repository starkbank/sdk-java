package com.starkbank;

public final class Organization extends com.starkcore.user.Organization {

    public Organization(String environment, String id, String privateKey) throws Exception {
        super(environment, id, privateKey);
    }

    public Organization(String environment, String id, String privateKey, String workspaceId) throws Exception {
        super(environment, id, privateKey, workspaceId);
    }
}
