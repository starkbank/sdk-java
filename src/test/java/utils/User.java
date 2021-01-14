package utils;

import com.starkbank.Project;
import com.starkbank.Organization;


public class User {

    public static Project defaultProject() throws Exception {
        return new Project(
            "sandbox",
            System.getenv("SANDBOX_ID"), // "9999999999999999",
            System.getenv("SANDBOX_PRIVATE_KEY") // "-----BEGIN EC PRIVATE KEY-----\nMHQCAQEEIBEcEJZLk/DyuXVsEjz0w4vrE7plPXhQxODvcG1Jc0WToAcGBSuBBAAK\noUQDQgAE6t4OGx1XYktOzH/7HV6FBukxq0Xs2As6oeN6re1Ttso2fwrh5BJXDq75\nmSYHeclthCRgU8zl6H1lFQ4BKZ5RCQ==\n-----END EC PRIVATE KEY-----"
        );
    }

    public static Organization defaultOrganization() throws Exception {
        return new Organization(
            "sandbox",
            System.getenv("SANDBOX_ORGANIZATION_ID"), // "8888888888888888",
            System.getenv("SANDBOX_ORGANIZATION_PRIVATE_KEY") // "-----BEGIN EC PRIVATE KEY-----\nMHQCAQEEIBEcEJZLk/DyuXVsEjz0w4vrE7plPXhQxODvcG1Jc0WToAcGBSuBBAAK\noUQDQgAE6t4OGx1XYktOzH/7HV6FBukxq0Xs2As6oeN6re1Ttso2fwrh5BJXDq75\nmSYHeclthCRgU8zl6H1lFQ4BKZ5RCQ==\n-----END EC PRIVATE KEY-----"
        );
    }
}
