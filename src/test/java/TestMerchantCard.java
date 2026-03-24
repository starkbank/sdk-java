import com.starkbank.MerchantCard;
import com.starkbank.Event;
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
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testMerchantCardEventParse() throws Exception {
        Settings.user = utils.User.defaultProject();

        String content = "{\"event\": {\"created\": \"2025-10-16T19:33:33.957137+00:00\", \"id\": \"4824539051589632\", \"log\": {\"card\": {\"created\": \"2025-10-16T19:33:33.667314+00:00\", \"ending\": \"9733\", \"expiration\": \"2035-02-01T02:59:59.999999+00:00\", \"fundingType\": \"debit\", \"holderName\": \"Kaladin Stormblessed\", \"id\": \"5596404638547968\", \"network\": \"mastercard\", \"status\": \"active\", \"tags\": [], \"updated\": \"2025-10-16T19:33:33.672774+00:00\"}, \"created\": \"2025-10-16T19:33:33.672780+00:00\", \"errors\": [], \"id\": \"6722304545390592\", \"type\": \"active\"}, \"subscription\": \"merchant-card\", \"workspaceId\": \"6314371953197056\"}}";
        String validSignature = "MEQCIFj9Vg+QkC+oXYXirS0j2ZoLFChRw7khSWrfpOud7/q7AiBPD7aPWYbpT6t3qSfyj2ol8b0cFQwtUHXu0iBkp4zGTQ==";
        Event parsedEvent = Event.parse(content, validSignature);

        Assert.assertEquals("merchant-card", parsedEvent.subscription);
        Assert.assertEquals(Event.MerchantCardEvent.class, parsedEvent.getClass());
        Assert.assertEquals(MerchantCard.Log.class, ((Event.MerchantCardEvent) parsedEvent).log.getClass());
    }
}
