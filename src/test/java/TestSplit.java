import org.junit.Test;
import org.junit.Assert;

import java.util.*;

import com.starkbank.Split;
import com.starkbank.Invoice;
import com.starkbank.Settings;
import com.starkbank.BrcodePayment;
import com.starkbank.utils.Generator;


public class TestSplit {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<Invoice> invoices = generateExampleSplittedInvoices(1);
        for(Invoice invoice : invoices){
            System.out.println(invoice);
        }

        List<BrcodePayment> payments = paySplittedInvoices(invoices);
        for (BrcodePayment payment : payments){
            System.out.println(payment);
        }

        boolean isInvoicePaid = false;
        while(!isInvoicePaid){
            for(Invoice invoice : invoices){
                if ((Invoice.get(invoice.id).status).equals("paid")) {
                    isInvoicePaid = true;
                }
                Thread.sleep(2000);
            }
        }

        List<String> tags = new ArrayList<>();
        for(Invoice invoice : invoices){
            tags.add(String.format("invoice/%s", invoice.id));
        }

        Map<String, Object> params = new HashMap<>();
        params.put("tags", tags);
        Generator<Split> splits = Split.query(params);
        for(Split split : splits){
            System.out.println(split);
            Assert.assertNotNull(split.id);
        }
    }

    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<Split> splits = Split.query(params);

        int i = 0;
        for (Split split : splits) {
            i += 1;
            split = Split.get(split.id);
            Assert.assertNotNull(split.id);
            System.out.println(split);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Split.Page page = Split.page(params);
            for (Split split : page.splits) {
                System.out.println(split);
                if (ids.contains(split.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(split.id);
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
    public void testLogQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<Split.Log> logs = Split.Log.query(params);

        int i = 0;
        for (Split.Log log : logs) {
            i += 1;
            log = Split.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.split.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Split.Log.Page page = Split.Log.page(params);
            for (Split.Log log : page.logs) {
                System.out.println(log);
                if (ids.contains(log.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(log.id);
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

    static List<Invoice> generateExampleSplittedInvoices(int n) throws Exception {
        List<Invoice> invoices = TestInvoice.example(n, true, false, true);
        return Invoice.create(invoices);
    }

    static List<BrcodePayment> paySplittedInvoices(List<Invoice> invoices) throws Exception {
        List<BrcodePayment> payments = new ArrayList<>();
        for(Invoice invoice : invoices){
            BrcodePayment payment = new BrcodePayment(TestSplit.examplePayment());
            payment.brcode = invoice.brcode;
            payments.add(payment);
        }
        return BrcodePayment.create(payments);
    }

    static Map<String, Object> examplePayment()  throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("brcode", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A");
        params.put("description", "Split Test");
        params.put("taxId", "20.018.183/0001-80");
        return params;
    }
}
