package com.starkbank.user;

import com.starkbank.utils.Resource;

public class Project extends User{
    public static ClassData data = new ClassData(Project.class, "Project");

    public Project(String id, String privateKey, String environment) throws Exception {
        super(id, privateKey, environment);
    }

    public String accessId() {
        return Resource.getName(data).toLowerCase() + '/' + this.id;
    }
}
