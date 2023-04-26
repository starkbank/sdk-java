import org.junit.Test;
import org.junit.Assert;

import com.starkbank.Settings;
import com.starkbank.CorporateInvoice;
import com.starkbank.utils.Generator;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public class TestCorporateInvoice {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();

        CorporateInvoice invoice = CorporateInvoice.create(example());
        Assert.assertNotNull(invoice.id);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporateInvoice.Page page = CorporateInvoice.page(params);
            for (CorporateInvoice invoice: page.invoices) {
                Assert.assertNotNull(invoice);
                if (ids.contains(invoice.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(invoice.id);
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
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporateInvoice> invoices = CorporateInvoice.query(params);

        for (CorporateInvoice invoice : invoices) {
            Assert.assertNotNull(invoice.id);
        }
    }

    static CorporateInvoice example() throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 1000L);

        return new CorporateInvoice(data);
    }
}
