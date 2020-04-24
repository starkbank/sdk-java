import com.starkbank.User;
import com.starkbank.Webhook;
import io.herrmann.generator.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;


public class TestWebhook {

    @Test
    public void testCreateAndDelete() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("url", "https://winterfell.westeros.gov/events-from-stark-bank");
        params.put("subscriptions", new String[]{"boleto", "boleto-payment", "transfer", "utility-payment"});
        Webhook webhook = Webhook.create(params);
        webhook = Webhook.delete(webhook.id);
        Assert.assertNotNull(webhook.id);
        Assert.assertNotNull(webhook.url);
        Assert.assertNotNull(webhook.subscriptions);
        System.out.println(webhook);
    }

    @Test
    public void testQueryAndGet() throws Exception {
        User.defaultUser = utils.User.defaultProject();

        Generator<Webhook> webhooks = Webhook.query();

        int i = 0;
        for (Webhook webhook : webhooks) {
            i += 1;
            webhook = Webhook.get(webhook.id);
            Assert.assertNotNull(webhook.id);
            Assert.assertNotNull(webhook.url);
            Assert.assertNotNull(webhook.subscriptions);
            System.out.println(webhook);
        }
        System.out.println(i);
    }
}
