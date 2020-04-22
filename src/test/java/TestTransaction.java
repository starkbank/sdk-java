import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestTransaction {

    @Test
    public void testPost() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<Transaction> transactions = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 10000);
        data.put("description", "A Lannister always pays his debts");
        data.put("externalId", String.valueOf(Instant.now().getEpochSecond()));
        data.put("receiverId", "5768064935133184");
        data.put("tags", new String[]{"lannister", "debts"});
        transactions.add(new Transaction(data));

        transactions = Transaction.create(transactions);

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    @Test
    public void testGet() throws Exception {
        User.defaultUser = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Transaction> transactions = Transaction.query(params);

        int i = 0;
        for (Transaction transaction : transactions) {
            i += 1;
            transaction = Transaction.get(transaction.id);
            System.out.println(transaction);
        }
        System.out.println(i);
    }
}
