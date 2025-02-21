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
        MerchantSession merchantSession = MerchantSession.create(exampleMerchantSession("disabled"));
        MerchantSession.Purchase purchase = MerchantSession.purchase(merchantSession.uuid, examplePurchaseChallengeModeDisable());

        System.out.println(exampleMerchantPurchase(purchase.cardId));
        MerchantPurchase merchantPurchase = MerchantPurchase.create(exampleMerchantPurchase(purchase.cardId));
        System.out.println(merchantPurchase);
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
            System.out.println(merchantPurchase);
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
            System.out.println(purchase);
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
            System.out.println(retrievedPurchase);
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
            System.out.println(log);
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


}