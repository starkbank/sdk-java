import com.starkbank.Settings;
import com.starkbank.VerifiedAccount;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


public class TestVerifiedAccount {

    @Before
    public void setUp() throws Exception {
        Settings.user = utils.User.defaultProject();
    }

    @Test
    public void testCreate() throws Exception {
        List<VerifiedAccount> verifiedAccounts = new ArrayList<>();

        verifiedAccounts.add(TestVerifiedAccount.bankInfoExample());
        verifiedAccounts.add(TestVerifiedAccount.pixKeyExample());

        List<VerifiedAccount> createdVerifiedAccounts = VerifiedAccount.create(verifiedAccounts);
        Assert.assertEquals(createdVerifiedAccounts.size(), verifiedAccounts.size());

        for (VerifiedAccount verifiedAccount : createdVerifiedAccounts) {
            Assert.assertNotNull(verifiedAccount.id);
        }
    }

    @Test
    public void testCreateAndGet() throws Exception {
        List<VerifiedAccount> verifiedAccounts = new ArrayList<>();
        
        verifiedAccounts.add(TestVerifiedAccount.bankInfoExample());

        VerifiedAccount createdVerifiedAccount = VerifiedAccount.create(verifiedAccounts).get(0);
        VerifiedAccount retrievedVerifiedAccount = VerifiedAccount.get(createdVerifiedAccount.id);

        Assert.assertEquals(createdVerifiedAccount.id, retrievedVerifiedAccount.id);
    }

    @Test
    public void testCreateAndCancel() throws Exception {
        List<VerifiedAccount> verifiedAccounts = new ArrayList<>();

        verifiedAccounts.add(TestVerifiedAccount.pixKeyExample());

        VerifiedAccount createdVerifiedAccount = VerifiedAccount.create(verifiedAccounts).get(0);
        VerifiedAccount canceledVerifiedAccount = VerifiedAccount.cancel(createdVerifiedAccount.id);

        Assert.assertEquals(canceledVerifiedAccount.id, createdVerifiedAccount.id);
        Assert.assertEquals("canceled", canceledVerifiedAccount.status);
    }

    @Test
    public void testPage() throws Exception {
        VerifiedAccount.Page page = VerifiedAccount.page();
        Assert.assertNotNull(page);
        for (VerifiedAccount verifiedAccount : page.verifiedAccounts) {
            Assert.assertNotNull(verifiedAccount.id);
        }
    }

    @Test 
    public void testQuery() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("status", "active");
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");

        Generator<VerifiedAccount> generator = VerifiedAccount.query(params);
        for (VerifiedAccount verifiedAccount : generator) {
            Assert.assertNotNull(verifiedAccount.id);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<VerifiedAccount.Log> logs = VerifiedAccount.Log.query(params);


        for (VerifiedAccount.Log log : logs) {
            log = VerifiedAccount.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.account.id);
        }
    }

    @Test
    public void testLogPage() throws Exception{
        VerifiedAccount.Log.Page page = VerifiedAccount.Log.page();
        Assert.assertNotNull(page);
        for (VerifiedAccount.Log log : page.logs) {
            Assert.assertNotNull(log.id);
        }
    }

    static VerifiedAccount bankInfoExample() throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("taxId", "911.544.440-66");
        data.put("number", "76543-8");
        data.put("bankCode", "341");
        data.put("branchCode", "2201");
        data.put("type", "checking");
        data.put("name", "Daenerys Targaryen Stormborn");
        data.put("tags", new String[]{"verified-account-test"});
        return new VerifiedAccount(data);
    }

    static VerifiedAccount pixKeyExample() throws Exception {
        HashMap<String, Object> data = new HashMap<>();
        data.put("taxId", "039.946.040-36");
        data.put("keyId", "arya.stark@starkbank.com");
        data.put("tags", new String[]{"verified-account-test"});
        return new VerifiedAccount(data);
    }
}
