import org.junit.Test;
import com.starkbank.*;
import org.junit.Assert;
import com.starkbank.utils.Generator;

import java.util.*;

public class TestSplitReceiver {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<SplitReceiver> receivers = new ArrayList<>();
        receivers.addAll(TestSplitReceiver.example(2));

        receivers = SplitReceiver.create(receivers);

        for (SplitReceiver receiver : receivers) {
            Assert.assertNotNull(receiver.id);
            System.out.println(receiver);
        }
    }

    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<SplitReceiver> receivers = SplitReceiver.query(params);

        int i = 0;
        for (SplitReceiver receiver : receivers) {
            i += 1;
            receiver = SplitReceiver.get(receiver.id);
            Assert.assertNotNull(receiver.id);
            System.out.println(receiver);
        }
        Assert.assertTrue(i > 0);
    }


    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SplitReceiver.Page page = SplitReceiver.page(params);
            for (SplitReceiver receiver : page.splitReceivers) {
                System.out.println(receiver);
                if (ids.contains(receiver.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(receiver.id);
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
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<SplitReceiver.Log> logs = SplitReceiver.Log.query(params);

        int i = 0;
        for (SplitReceiver.Log log : logs) {
            i += 1;
            log = SplitReceiver.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.receiver.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SplitReceiver.Log.Page page = SplitReceiver.Log.page(params);
            for (SplitReceiver.Log log: page.logs) {
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

    static List<SplitReceiver> example(int n)  throws Exception{
        List<SplitReceiver> receivers = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < n; i++){
            Map<String, Object> params = new HashMap<>();
            params.put("name", "João");
            params.put("branchCode", Integer.toString(random.nextInt(1000)));
            params.put("bankCode", "60701190");
            params.put("taxId", "123.456.789-09");
            params.put("accountType", "checking");
            params.put("accountNumber", String.format("%s-%s", random.nextInt(99999999) + 10000, random.nextInt(9)));
            receivers.add(new SplitReceiver(params));
        }
        return receivers;
    }
}
