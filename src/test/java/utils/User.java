package utils;

import com.starkbank.Project;

public class User {

    public static Project defaultProject() throws Exception {
        return new Project(
            "sandbox",
            "5690398416568320",
            "-----BEGIN EC PARAMETERS-----\nBgUrgQQACg==\n-----END EC PARAMETERS-----\n-----BEGIN EC PRIVATE KEY-----\nMHQCAQEEIIoYWZ2OGwqX6n1EVvj1C1YvWHSGqqhZJzfsZZnk0SVgoAcGBSuBBAAK\noUQDQgAEGS1jWJXoK9RUk+qoNNFquO7X4JzRf5ZA5UDJUfPCbbKe5KwtrBKTJC1/\nvRGIpAM5gNsxdfKgmoXNriiuY4LEPQ==\n-----END EC PRIVATE KEY-----\n"
        );
    }
}
