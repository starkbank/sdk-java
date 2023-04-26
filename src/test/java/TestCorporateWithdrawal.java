import org.junit.Test;
import org.junit.Assert;

import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import com.starkbank.CorporateWithdrawal;

import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;


public class TestCorporateWithdrawal {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();

        CorporateWithdrawal withdrawal = CorporateWithdrawal.create(example());
        System.out.println(withdrawal);
        Assert.assertNotNull(withdrawal.id);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporateWithdrawal.Page page = CorporateWithdrawal.page(params);
            for (CorporateWithdrawal withdrawal: page.withdrawals) {
                System.out.println(withdrawal);
                if (ids.contains(withdrawal.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(withdrawal.id);
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
        Generator<CorporateWithdrawal> withdrawals = CorporateWithdrawal.query(params);

        int i = 0;
        for (CorporateWithdrawal withdrawal : withdrawals) {
            i += 1;
            CorporateWithdrawal withdrawalExpected = CorporateWithdrawal.get(withdrawal.id);
            Assert.assertNotNull(withdrawal.id, withdrawalExpected.id);
            System.out.println(withdrawal);
        }
        System.out.println(i);
    }

    static CorporateWithdrawal example() throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 1000L);
        data.put("externalId", UUID.randomUUID().toString());

        return new CorporateWithdrawal(data);
    }
}
