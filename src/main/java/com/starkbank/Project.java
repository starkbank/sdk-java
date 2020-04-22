package com.starkbank;

public final class Project extends User {
    static ClassData data = new ClassData(Project.class, "Project");

    public Project(String environment, String id, String privateKey) throws Exception {
        super(environment, id, privateKey);
    }

    public String accessId() {
        return "project/" + this.id;
    }
}
