import org.junit.Test;
import org.junit.Assert;

import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import com.starkbank.CorporateTransaction;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public class TestCorporateTransaction {

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporateTransaction.Page page = CorporateTransaction.page(params);
            for (CorporateTransaction transaction: page.corporateTransactions) {
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
    public void testQueryGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporateTransaction> transactions = CorporateTransaction.query(params);

        int i = 0;
        for (CorporateTransaction transaction : transactions) {
            i += 1;
            CorporateTransaction transactionExpected = CorporateTransaction.get(transaction.id);
            Assert.assertNotNull(transaction.id, transactionExpected.id);
            System.out.println(transaction);
        }
        System.out.println(i);
    }
}
