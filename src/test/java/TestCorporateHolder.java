import org.junit.Test;
import org.junit.Assert;

import com.starkbank.Settings;
import com.starkbank.CorporateRule;
import com.starkbank.CorporateHolder;
import com.starkbank.utils.Generator;

import java.util.*;


public class TestCorporateHolder {

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporateHolder.Page page = CorporateHolder.page(params);
            for (CorporateHolder holder: page.holders) {
                System.out.println(holder);
                if (ids.contains(holder.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(holder.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        if (ids.size() != 4) {
            throw new Exception("ids.size() != 4");
        }
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporateHolder> holders = CorporateHolder.query(params);

        int i = 0;
        for (CorporateHolder holder : holders) {
            i += 1;
            System.out.println(holder);
            Assert.assertNotNull(holder.id);
        }
        System.out.println(i);
    }

    @Test
    public void testCreateAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<CorporateHolder> holders = new ArrayList<>();
        holders.add(example());
        holders = CorporateHolder.create(holders);

        HashMap<String, Object> expand = new HashMap<>();
        expand.put("expand", Collections.singletonList("rules"));

        for (CorporateHolder holder : holders) {
            Assert.assertNotNull(holder.id);
            holder = CorporateHolder.get(holder.id, expand);
            String id = holder.id;
            Assert.assertEquals(id, holder.id);
            System.out.println(holder);
        }
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<CorporateHolder.Permission> permissions = new ArrayList<>();
        permissions.add(permissionExample());

        HashMap<String, Object> patchData = new HashMap<>();
        patchData.put("status", "blocked");
        patchData.put("permissions", permissions);

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("status", "active");
        Generator<CorporateHolder> holders = CorporateHolder.query(params);

        for (CorporateHolder holder : holders) {
            CorporateHolder updatedCorporateHolder = CorporateHolder.update(holder.id, patchData);
            Assert.assertEquals("blocked", updatedCorporateHolder.status);
            System.out.println(updatedCorporateHolder);
        }
    }

    @Test
    public void testCancel() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("status", "active");
        Generator<CorporateHolder> holders = CorporateHolder.query(params);

        for (CorporateHolder holder : holders) {
            CorporateHolder canceledCorporateHolder = CorporateHolder.cancel(holder.id);
            Assert.assertEquals("canceled", canceledCorporateHolder.status);
            System.out.println(canceledCorporateHolder);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporateHolder.Log> logs = CorporateHolder.Log.query(params);

        for (CorporateHolder.Log log : logs) {
            log = CorporateHolder.Log.get(log.id);
            System.out.println(log);
        }
    }

    public static CorporateHolder example() throws Exception{
        List<CorporateHolder.Permission> permissions = new ArrayList<>();
        permissions.add(permissionExample());
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "Iron Bank S.A." + new Random().nextInt(100));
        data.put("permissions", permissions);
        data.put("tags", new String[]{"Traveler Employee"});
        data.put("rules", ruleExample());
        return new CorporateHolder(data);
    }

    private static CorporateHolder.Permission permissionExample() throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("ownerId", "5657068027510784");
        data.put("ownerType", "project");
        return new CorporateHolder.Permission(data);
    }

    private static List<CorporateRule> ruleExample() throws Exception {
        List<CorporateRule> rules = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("name", "test");
        data.put("amount", 10000);
        data.put("interval", "day");
        data.put("currencyCode", "BRL");
        data.put("schedule", "every monday, wednesday from 00:00 to 23:59 in America/Sao_Paulo");
        data.put("purposes", new String[]{ "purchase", "withdrawal" });
        rules.add(new CorporateRule(data));
        return rules;
    }
}
