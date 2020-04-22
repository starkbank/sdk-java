package utils;

import com.starkbank.Project;

public class User {

    public static Project defaultProject() throws Exception {
        return new Project(
            "sandbox",
            "9999999999999999",
            "-----BEGIN EC PRIVATE KEY-----\n" +
                    "MHQCAQEEIBEcEJZLk/DyuXVsEjz0w4vrE7plPXhQxODvcG1Jc0WToAcGBSuBBAAK\n" +
                    "oUQDQgAE6t4OGx1XYktOzH/7HV6FBukxq0Xs2As6oeN6re1Ttso2fwrh5BJXDq75\n" +
                    "mSYHeclthCRgU8zl6H1lFQ4BKZ5RCQ==\n" +
                    "-----END EC PRIVATE KEY-----"
        );
    }
}
