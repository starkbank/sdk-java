import com.starkbank.DictKey;
import com.starkbank.DictKey;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;


public class TestDictKey {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        String pixKey = "tony@starkbank.com";
        DictKey dictKey = DictKey.get(pixKey);
        System.out.println(dictKey);
        Assert.assertNotNull(dictKey.id);
        Assert.assertEquals(dictKey.id, pixKey);
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("type", "evp");
        params.put("status", "registered");
        Generator<DictKey> dictKeys = DictKey.query(params);

        int i = 0;
        for (DictKey dictKey : dictKeys) {
            i += 1;
            System.out.println(dictKey);
        }
        System.out.println(i);
    }
}
