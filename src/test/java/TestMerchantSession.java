import com.starkbank.MerchantSession;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.*;

import static utils.MerchantSession.*;

public class TestMerchantSession {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();

        MerchantSession merchantSession = MerchantSession.create(exampleMerchantSession("disabled"));
        System.out.println(merchantSession);
        Assert.assertNotNull(merchantSession.id);
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantSession> sessions = MerchantSession.query(params);

        for (MerchantSession session : sessions) {
            Assert.assertNotNull(session.id);
            System.out.println(session);
        }
    }

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantSession> sessions = MerchantSession.query(params);

        for (MerchantSession session : sessions) {
            MerchantSession retrievedSession = MerchantSession.get(session.id);
            Assert.assertNotNull(retrievedSession.id);
            System.out.println(retrievedSession);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 5);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MerchantSession.Page page = MerchantSession.page(params);
            for (MerchantSession session : page.merchantSessions) {
                Assert.assertNotNull(session.id);
                Assert.assertFalse(ids.contains(session.id));
                ids.add(session.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        Assert.assertEquals(10, ids.size());
    }

    @Test
    public void testPageWithoutParams() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<String> ids = new ArrayList<>();

        MerchantSession.Page page = MerchantSession.page();
        for (MerchantSession session : page.merchantSessions) {
            System.out.println(session);
            Assert.assertNotNull(session.id);
            ids.add(session.id);
        }

        Assert.assertEquals(100, ids.size());
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<MerchantSession.Log> logs = MerchantSession.Log.query(params);

        int i = 0;
        for (MerchantSession.Log log : logs) {
            i += 1;
            log = MerchantSession.Log.get(log.id);
            Assert.assertNotNull(log.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testLogs() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<String> ids = new ArrayList<>();

        MerchantSession.Log.Page page = MerchantSession.Log.page();
        for (MerchantSession.Log session : page.logs) {
            System.out.println(session);
            Assert.assertNotNull(session.id);
            ids.add(session.id);
        }

        Assert.assertEquals(100, ids.size());
    }


    @Test
    public void testMerchantSessionPurchaseChallengeModeDisabled() throws Exception {
        Settings.user = utils.User.defaultProject();

        MerchantSession merchantSession = MerchantSession.create(exampleMerchantSession("disabled"));

        MerchantSession.Purchase purchaseResponse = MerchantSession.purchase(
                merchantSession.uuid, examplePurchaseChallengeModeDisable());
        System.out.println(purchaseResponse);
        Assert.assertNotNull(purchaseResponse.id);
    }

    @Test
    public void testMerchantSessionPurchaseChallengeModeEnabled() throws Exception {
        Settings.user = utils.User.defaultProject();

        MerchantSession merchantSession = MerchantSession.create(exampleMerchantSession("enabled"));

        MerchantSession.Purchase purchaseResponse = MerchantSession.purchase(
                merchantSession.uuid, examplePurchaseChallengeModeEnable());

        Assert.assertNotNull(purchaseResponse.id);
    }

}