package utils;

import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.starkbank.Project;

public class User {

    public static Project defaultProject() throws Exception {
        try (FileReader reader = new FileReader("projectCredentials.json")) {
            JsonObject obj = JsonParser.parseReader(reader).getAsJsonObject();
            String environment = obj.get("environment").getAsString();
            String id = obj.get("id").getAsString();
            String privateKey = obj.get("privateKey").getAsString();

            return new Project(environment, id, privateKey);
        }
    }
}
