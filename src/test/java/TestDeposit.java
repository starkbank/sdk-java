import com.starkbank.Deposit;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestDeposit {

    @Test
    public void testQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Deposit> deposits = Deposit.query(params);
        System.out.println(deposits);
        int i = 0;
        for (Deposit deposit : deposits) {
            i += 1;
            System.out.println(deposit);
            String depositId = deposit.id;
            deposit = Deposit.get(depositId);
            Assert.assertNotNull(depositId);
            Assert.assertEquals(depositId, deposit.id);
            System.out.println(deposit);
        }
        Assert.assertTrue(i > 0);
    }
    
    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Deposit.Log> logs = Deposit.Log.query(params);

        int i = 0;
        for (Deposit.Log log : logs) {
            i += 1;
            log = Deposit.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.deposit.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
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
            Deposit.Page page = Deposit.page(params);
            for (Deposit deposit: page.deposits) {
                System.out.println(deposit);
                if (ids.contains(deposit.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(deposit.id);
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
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Deposit.Log.Page page = Deposit.Log.page(params);
            for (Deposit.Log log: page.logs) {
                System.out.println(log);
                if (ids.contains(log.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(log.id);
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
}
