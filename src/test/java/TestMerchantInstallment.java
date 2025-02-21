import com.starkbank.MerchantInstallment;
import com.starkbank.Settings;
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
            System.out.println(retrievedInstallment);
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
            System.out.println(installment);
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
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

}
