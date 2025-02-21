import com.starkbank.MerchantCard;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestMerchantCard {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        Generator<MerchantCard> cards = MerchantCard.query(params);

        for (MerchantCard card : cards) {
            MerchantCard retrievedCard = MerchantCard.get(card.id);
            Assert.assertNotNull(retrievedCard.id);
            System.out.println(retrievedCard);
        }
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantCard> cards = MerchantCard.query(params);

        for (MerchantCard card : cards) {
            Assert.assertNotNull(card.id);
            System.out.println(card);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            MerchantCard.Page page = MerchantCard.page(params);
            for (MerchantCard card : page.merchantCards) {
                Assert.assertNotNull(card.id);
                Assert.assertFalse(ids.contains(card.id));
                ids.add(card.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        Assert.assertEquals(20, ids.size());
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<MerchantCard.Log> logs = MerchantCard.Log.query(params);

        int i = 0;
        for (MerchantCard.Log log : logs) {
            i += 1;
            log = MerchantCard.Log.get(log.id);
            Assert.assertNotNull(log.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

}
