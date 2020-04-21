package com.starkbank;

public class Project extends User {
    static ClassData data = new ClassData(Project.class, "Project");

    public Project(String id, String privateKey, String environment) throws Exception {
        super(id, privateKey, environment);
    }

    public String accessId() {
        return data.name.toLowerCase() + "/" + this.id;
    }
}
