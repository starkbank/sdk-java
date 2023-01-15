import com.starkbank.DynamicBrcode;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;
import utils.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestDynamicBrcode {

    @Test
    public void testCreateAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<DynamicBrcode> brcodes = new ArrayList<>();

        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 400000);
        data.put("expiration", 5000);
        data.put("tags", new String[] {"tags1", "tags2"});
        brcodes.add(new DynamicBrcode(data));
        
        brcodes = DynamicBrcode.create(brcodes);

        for (DynamicBrcode brcode : brcodes) {
            Assert.assertEquals(brcode.uuid, DynamicBrcode.get(brcode.uuid).uuid);
            Assert.assertNotNull(brcode.uuid);
            System.out.println(brcode);
        }
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<DynamicBrcode> brcodes = DynamicBrcode.query(params);

        for (DynamicBrcode brcode : brcodes) {
            Assert.assertEquals(brcode.uuid, DynamicBrcode.get(brcode.uuid).uuid);
            Assert.assertNotNull(brcode.uuid);
            System.out.println(brcode);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> uuids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            DynamicBrcode.Page page = DynamicBrcode.page(params);
            for (DynamicBrcode brcode: page.brcodes) {
                System.out.println(brcode);
                if (uuids.contains(brcode.uuid)) {
                    throw new Exception("repeated uuid");
                }
                uuids.add(brcode.uuid);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        if (uuids.size() != 4) {
            throw new Exception("uuids.size() != 4");
        }
    }
}
