import org.junit.Test;
import org.junit.Assert;

import com.starkbank.Settings;
import com.starkbank.CorporateRule;
import com.starkbank.CorporateCard;
import com.starkbank.CorporateHolder;
import com.starkbank.utils.Generator;

import java.util.*;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public class TestCorporateCard {

    @Test
    public void testCreateGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        CorporateCard card = CorporateCard.create(example());
        Assert.assertNotNull(card.id);
        card = CorporateCard.get(card.id);
        String id = card.id;
        Assert.assertEquals(id, card.id);
        System.out.println(card);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporateCard.Page page = CorporateCard.page(params);
            for (CorporateCard card: page.cards) {
                System.out.println(card);
                if (ids.contains(card.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(card.id);
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
    public void testQueryGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporateCard> cards = CorporateCard.query(params);

        HashMap<String, Object> expand = new HashMap<>();
        expand.put("expand", Arrays.asList("rules", "number", "expiration", "securityCode"));

        for (CorporateCard card : cards) {
            CorporateCard expectedCard = CorporateCard.get(card.id, expand);
            Assert.assertNotNull(card.id, expectedCard.id);
            System.out.println(expectedCard);
        }
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Settings.user = utils.User.defaultProject();

        Map<String, Object> patchData = new HashMap<>();
        patchData.put("status", "blocked");
        patchData.put("pin", "1234");

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("status", "active");
        Generator<CorporateCard> cards = CorporateCard.query(params);
        for (CorporateCard card : cards) {
            CorporateCard updatedCorporateCard = CorporateCard.update(card.id, patchData);
            Assert.assertEquals("blocked", updatedCorporateCard.status);
        }
    }

    @Test
    public void testCancel() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("status", "active");
        Generator<CorporateCard> cards = CorporateCard.query(params);
        for (CorporateCard card : cards) {
            CorporateCard canceledCorporateCard = CorporateCard.cancel(card.id);
            Assert.assertEquals("canceled", canceledCorporateCard.status);
            System.out.println(canceledCorporateCard);
        }
    }

    @Test
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporateCard.Log.Page page = CorporateCard.Log.page(params);
            for (CorporateCard.Log log: page.logs) {
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

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporateCard.Log> logs = CorporateCard.Log.query(params);

        for (CorporateCard.Log log : logs) {
            log = CorporateCard.Log.get(log.id);
            Assert.assertNotNull(log);
        }
    }

    static CorporateCard example() throws Exception{
        List<CorporateHolder> holders = new ArrayList<>();
        holders.add(TestCorporateHolder.example());
        holders = CorporateHolder.create(holders);
        CorporateHolder holder = holders.get(0);
        HashMap<String, Object> data = new HashMap<>();
        data.put("holderId", holder.id);
        return new CorporateCard(data);
    }

}
