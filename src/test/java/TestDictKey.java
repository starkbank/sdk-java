import com.starkbank.DictKey;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestDictKey {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        String pixKey = "valid@sandbox.com";
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

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            DictKey.Page page = DictKey.page(params);
            for (DictKey key: page.keys) {
                System.out.println(key);
                if (ids.contains(key.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(key.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        if (ids.size() > 4) {
            throw new Exception("ids.size() != 4");
        }
    }
}
