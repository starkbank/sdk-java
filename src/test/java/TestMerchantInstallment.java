import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestMerchantInstallment {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantInstallment> installments = MerchantInstallment.query(params);

        for (MerchantInstallment installment : installments) {
            MerchantInstallment retrievedInstallment = MerchantInstallment.get(installment.id);
            Assert.assertNotNull(retrievedInstallment.id);
        }
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<MerchantInstallment> installments = MerchantInstallment.query(params);

        for (MerchantInstallment installment : installments) {
            Assert.assertNotNull(installment.id);
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
            MerchantInstallment.Page page = MerchantInstallment.page(params);
            for (MerchantInstallment installment : page.merchantInstallments) {
                Assert.assertNotNull(installment.id);
                Assert.assertFalse(ids.contains(installment.id));
                ids.add(installment.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        Assert.assertEquals(10, ids.size());
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<MerchantInstallment.Log> logs = MerchantInstallment.Log.query(params);

        int i = 0;
        for (MerchantInstallment.Log log : logs) {
            i += 1;
            log = MerchantInstallment.Log.get(log.id);
            Assert.assertNotNull(log.id);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testMerchantInstallmentEventParse() throws Exception {
        Settings.user = utils.User.defaultProject();

        String content = "{\"event\": {\"created\": \"2025-10-14T20:45:55.357314+00:00\", \"id\": \"6007986671583232\", \"log\": {\"created\": \"2025-10-14T20:45:53.697574+00:00\", \"description\": \"Installment created with a nominal amount of R$ 10,00.\", \"errors\": [], \"id\": \"6655369694674944\", \"installment\": {\"amount\": 1000, \"created\": \"2025-10-14T20:45:53.610204+00:00\", \"due\": \"2025-11-17T03:00:00+00:00\", \"fee\": 24, \"fundingType\": \"credit\", \"id\": \"5529469787832320\", \"isProtected\": false, \"network\": \"diners\", \"nominalDue\": \"2025-11-17T03:00:00+00:00\", \"purchaseId\": \"5022074565296128\", \"status\": \"created\", \"tags\": [\"yourtags\"], \"transactionIds\": [], \"updated\": \"2025-10-14T20:45:53.697608+00:00\"}, \"type\": \"created\"}, \"subscription\": \"merchant-installment\", \"workspaceId\": \"6341320293482496\"}}";
        String validSignature = "MEUCIQD8azmNmlsG+baoqAh4xmX9G538cGrDhTT0VvU85rz8bwIgBEdIr6SSW/7vfxOv4ZET+LsHU0TpNpTrGmBxjs8Y5o0=";
        Event parsedEvent = Event.parse(content, validSignature);

        Assert.assertEquals("merchant-installment", parsedEvent.subscription);
        Assert.assertEquals(Event.MerchantInstallmentEvent.class, parsedEvent.getClass());
        Assert.assertEquals(MerchantInstallment.Log.class, ((Event.MerchantInstallmentEvent) parsedEvent).log.getClass());
    }
}
