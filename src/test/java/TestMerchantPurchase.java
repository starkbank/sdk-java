import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utils.MerchantPurchase.exampleMerchantPurchase;
import static utils.MerchantSession.*;

public class TestMerchantPurchase {


    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        Settings.timeout = 20;

        MerchantSession merchantSession = MerchantSession.create(exampleMerchantSession("disabled"));
        MerchantSession.Purchase purchase = MerchantSession.purchase(merchantSession.uuid, examplePurchaseChallengeModeDisable());

        MerchantPurchase merchantPurchase = MerchantPurchase.create(exampleMerchantPurchase(purchase.cardId));
        Assert.assertNotNull(merchantPurchase.id);
    }

    @Test
    public void testUpdate() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("status", "created");

        Generator<MerchantPurchase> purchases = MerchantPurchase.query(params);

        HashMap<String, Object> patchData = new HashMap<>();
        patchData.put("amount", 0);
        patchData.put("status", "reversed");

        for (MerchantPurchase merchantPurchase : purchases) {
            MerchantPurchase.update(merchantPurchase.id, patchData);
            Assert.assertNotNull(merchantPurchase.id);
        }

    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantPurchase> purchases = MerchantPurchase.query(params);

        for (MerchantPurchase purchase : purchases) {
            Assert.assertNotNull(purchase.id);
        }
    }

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantPurchase> purchases = MerchantPurchase.query(params);

        for (MerchantPurchase purchase : purchases) {
            MerchantPurchase retrievedPurchase = MerchantPurchase.get(purchase.id);
            Assert.assertNotNull(retrievedPurchase.id);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<MerchantPurchase.Log> logs = MerchantPurchase.Log.query(params);

        int i = 0;
        for (MerchantPurchase.Log log : logs) {
            i += 1;
            log = MerchantPurchase.Log.get(log.id);
            Assert.assertNotNull(log.id);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 5);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MerchantPurchase.Page page = MerchantPurchase.page(params);
            for (MerchantPurchase purchase : page.merchantPurchases) {
                Assert.assertNotNull(purchase.id);
                Assert.assertFalse(ids.contains(purchase.id));
                ids.add(purchase.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        Assert.assertEquals(10, ids.size());
    }

    @Test
    public void testMerchantPurchaseEventParse() throws Exception {
        Settings.user = utils.User.defaultProject();

        String content = "{\"event\": {\"created\": \"2025-10-14T20:46:00.300285+00:00\", \"id\": \"5454126009810944\", \"log\": {\"created\": \"2025-10-14T20:45:58.347434+00:00\", \"description\": \"Purchase approved.\", \"errors\": [], \"id\": \"5669171517980672\", \"purchase\": {\"amount\": 1000, \"billingCity\": \"\", \"billingCountryCode\": \"\", \"billingStateCode\": \"\", \"billingStreetLine1\": \"\", \"billingStreetLine2\": \"\", \"billingZipCode\": \"\", \"cardEnding\": \"1625\", \"cardId\": \"5113758527520768\", \"challengeMode\": \"disabled\", \"challengeUrl\": \"\", \"created\": \"2025-10-14T20:45:56.936238+00:00\", \"currencyCode\": \"BRL\", \"endToEndId\": \"f02b36d6-7872-4a81-b2ce-cd1a0b89da69\", \"fee\": 0, \"fundingType\": \"credit\", \"holderEmail\": \"\", \"holderName\": \"Margaery Tyrell\", \"holderPhone\": \"\", \"id\": \"5903823029665792\", \"installmentCount\": 1, \"metadata\": {}, \"network\": \"diners\", \"softDescriptor\": \"\", \"source\": \"merchant-session/5047053356892160\", \"status\": \"approved\", \"tags\": [\"yourtags\"], \"transactionIds\": [], \"updated\": \"2025-10-14T20:45:58.347478+00:00\"}, \"transactionId\": \"\", \"type\": \"approved\"}, \"subscription\": \"merchant-purchase\", \"workspaceId\": \"6341320293482496\"}}";
        String validSignature = "MEYCIQCQ7cDmcaRxVpEwTbmGCiTKE6RiWHgtXvVs3sSITgF4wwIhAInXEEeRKoi3UuOm4BexLAG05RQiSG5iSZJW3UZ3jBE3";
        Event parsedEvent = Event.parse(content, validSignature);

        Assert.assertEquals("merchant-purchase", parsedEvent.subscription);
        Assert.assertEquals(Event.MerchantPurchaseEvent.class, parsedEvent.getClass());
        Assert.assertEquals(MerchantPurchase.Log.class, ((Event.MerchantPurchaseEvent) parsedEvent).log.getClass());
    }
}