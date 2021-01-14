import com.starkbank.Settings;
import com.starkbank.Webhook;
import com.starkbank.error.ErrorElement;
import com.starkbank.error.InputErrors;
import com.starkbank.utils.Generator;
import org.junit.AssumptionViolatedException;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.UUID;


public class TestWebhook {

    @Test
    public void testCreateAndDelete() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        params.put("url", "https://winterfell.westeros.gov/events-from-stark-bank/" + uuid);
        params.put("subscriptions", new String[]{"boleto", "boleto-payment", "transfer", "utility-payment", "boleto-holmes", "brcode-payment", "deposit", "invoice"});
        Webhook webhook = Webhook.create(params);
        webhook = Webhook.delete(webhook.id);
        Assert.assertNotNull(webhook.id);
        Assert.assertNotNull(webhook.url);
        Assert.assertNotNull(webhook.subscriptions);
        System.out.println(webhook);
    }

    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        Generator<Webhook> webhooks = Webhook.query();

        int i = 0;
        for (Webhook webhook : webhooks) {
            i += 1;
            try{
                webhook = Webhook.get(webhook.id);
            } catch (InputErrors e)
            {
                if(e.errors.get(0).code.equals("invalidWebhookId"))
                    throw new AssumptionViolatedException("Inconclusive");
            }
            Assert.assertNotNull(webhook.id);
            Assert.assertNotNull(webhook.url);
            Assert.assertNotNull(webhook.subscriptions);
            System.out.println(webhook);
        }
        System.out.println(i);
    }
}
