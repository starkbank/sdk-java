package utils;

import com.starkbank.user.Project;

public class User {

    public static Project defaultProject() throws Exception {
        return new Project(
                "5690398416568320",
//                testKey.privatePem,
                "-----BEGIN EC PARAMETERS-----\n" +
                        "    BgUrgQQACg==\n" +
                        "    -----END EC PARAMETERS-----\n" +
                        "    -----BEGIN EC PRIVATE KEY-----\n" +
                        "    MHQCAQEEIIoYWZ2OGwqX6n1EVvj1C1YvWHSGqqhZJzfsZZnk0SVgoAcGBSuBBAAK\n" +
                        "    oUQDQgAEGS1jWJXoK9RUk+qoNNFquO7X4JzRf5ZA5UDJUfPCbbKe5KwtrBKTJC1/\n" +
                        "    vRGIpAM5gNsxdfKgmoXNriiuY4LEPQ==\n" +
                        "    -----END EC PRIVATE KEY-----",
                "sandbox");
    }
}
