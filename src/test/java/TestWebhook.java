import com.starkbank.User;
import com.starkbank.Webhook;
import com.starkbank.utils.Generator;
import org.junit.Test;

import java.time.Instant;
import java.util.HashMap;

public class TestWebhook {

    @Test
    public void testPostAndDelete() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        Webhook webhook = Webhook.create(
            "https://winterfell.westeros.gov/events-from-stark-bank",
            new String[]{"boleto", "boleto-payment", "transfer", "utility-payment"}
        );
        webhook = Webhook.delete(webhook.id);
        System.out.println(webhook);
    }

    @Test
    public void testGet() throws Exception {
        User.defaultUser = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<Webhook> transactions = Webhook.query(params);

        int i = 0;
        for (Webhook transaction : transactions) {
            i += 1;
            transaction = Webhook.get(transaction.id);
            System.out.println(transaction);
        }
        System.out.println(i);
    }
}
