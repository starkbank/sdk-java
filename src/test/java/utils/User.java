package utils;

import com.starkbank.Project;

public class User {

    public static Project defaultProject() throws Exception {
        return new Project(
            "sandbox",
            System.getenv("SANDBOX_ID"), // "9999999999999999",
            System.getenv("SANDBOX_PRIVATE_KEY") // "-----BEGIN EC PRIVATE KEY-----\nMHQCAQEEIBEcEJZLk/DyuXVsEjz0w4vrE7plPXhQxODvcG1Jc0WToAcGBSuBBAAK\noUQDQgAE6t4OGx1XYktOzH/7HV6FBukxq0Xs2As6oeN6re1Ttso2fwrh5BJXDq75\nmSYHeclthCRgU8zl6H1lFQ4BKZ5RCQ==\n-----END EC PRIVATE KEY-----"
        );
    }
}
