import org.junit.Test;
import com.starkbank.*;
import org.junit.Assert;

import java.util.*;

public class TestVerifiedTransfer {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<VerifiedTransfer> transfers = new ArrayList<>();
        transfers.addAll(TestVerifiedTransfer.example(2));

        transfers = VerifiedTransfer.create(transfers);

        for (VerifiedTransfer transfer : transfers) {
            Assert.assertNotNull(transfer.id);
            Assert.assertNotNull(transfer.rules);
            Assert.assertEquals(1, transfer.rules.size());
            Transfer.Rule rule = transfer.rules.get(0);
            Assert.assertEquals("resendingLimit", rule.key);
            Assert.assertEquals(5, ((Number) rule.value).intValue());
            System.out.println(transfer);

            Transfer retrieved = Transfer.get(transfer.id);
            Assert.assertEquals(transfer.id, retrieved.id);
        }
    }

    static List<VerifiedTransfer> example(int n) throws Exception {
        List<VerifiedTransfer> transfers = new ArrayList<>();
        VerifiedAccount account = TestVerifiedTransfer.verifiedAccount();
        for (int i = 0; i < n; i++) {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", 1);
            params.put("accountId", account.id);
            params.put("accountType", "checking");
            params.put("externalId", UUID.randomUUID().toString());
            params.put("tags", new String[]{"employees", "monthly"});
            List<Transfer.Rule> rules = new ArrayList<>();
            rules.add(new Transfer.Rule("resendingLimit", 5));
            params.put("rules", rules);
            transfers.add(new VerifiedTransfer(params));
        }
        return transfers;
    }

    static VerifiedAccount verifiedAccount() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("taxId", "911.544.440-66");
        params.put("bankCode", "60701190");
        params.put("branchCode", "0001");
        params.put("number", "00000-0");
        params.put("name", "Stark Bank S.A.");
        params.put("type", "checking");

        List<VerifiedAccount> accounts = new ArrayList<>();
        accounts.add(new VerifiedAccount(params));
        accounts = VerifiedAccount.create(accounts);
        return accounts.get(0);
    }
}
