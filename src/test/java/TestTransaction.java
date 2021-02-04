import com.starkbank.Transaction;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TestTransaction {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(TestTransaction.example());

        transactions = Transaction.create(transactions);

        for (Transaction transaction : transactions) {
            Assert.assertNotNull(transaction.id);
            System.out.println(transaction);
        }
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Transaction> transactions = Transaction.query(params);

        int i = 0;
        for (Transaction transaction : transactions) {
            i += 1;
            transaction = Transaction.get(transaction.id);
            Assert.assertNotNull(transaction.id);
            System.out.println(transaction);
        }
        System.out.println(i);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Transaction.Page page = Transaction.page(params);
            for (Transaction transaction: page.transactions) {
                System.out.println(transaction);
                if (ids.contains(transaction.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(transaction.id);
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
    public void testQueryIds() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        Generator<Transaction> transactions = Transaction.query(params);

        ArrayList<String> transactionsIdsExpected = new ArrayList<>();
        for (Transaction transaction : transactions) {
            Assert.assertNotNull(transaction.id);
            transactionsIdsExpected.add(transaction.id);
        }

        params.put("ids", transactionsIdsExpected.toArray(new String[0]));
        Generator<Transaction> transactionsResult = Transaction.query(params);
    
        ArrayList<String> transactionsIdsResult = new ArrayList<>();
        for (Transaction transaction : transactionsResult){
            Assert.assertNotNull(transaction.id);
            transactionsIdsResult.add(transaction.id);
        }
        
        Collections.sort(transactionsIdsExpected);
        Collections.sort(transactionsIdsResult);
        Assert.assertEquals(transactionsIdsExpected, transactionsIdsResult);
    }

    static Transaction example() throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 1);
        data.put("description", "A Lannister always pays his debts");
        data.put("externalId", String.valueOf(Instant.now().getEpochSecond()));
        data.put("receiverId", "5768064935133184");
        data.put("tags", new String[]{"lannister", "debts"});

        return new Transaction(data);
    }
}
