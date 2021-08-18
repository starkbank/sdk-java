import com.starkbank.Invoice;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;
import utils.User;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestInvoice {

    @Test
    public void testCreateAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<Invoice> invoices = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 400000);
        data.put("due", getDatetimeString(3));
        data.put("taxId", "20.018.183/0001-80");
        data.put("name", "Iron Bank S.A.");
        data.put("expiration", 123456789);
        data.put("fine", 2);
        data.put("interest", 1.3);

        List<HashMap<String, Object>> descriptions = new ArrayList<>();
        HashMap<String, Object> description = new HashMap<>();
        description.put("key", "Some supplies");
        description.put("value", "100000");
        descriptions.add(description);
        data.put("descriptions", descriptions);

        invoices.add(new Invoice(data));

        HashMap<String, Object> simpleExample = new HashMap<>();
        simpleExample.put("amount", 400000);
        simpleExample.put("due", getDatetimeString(3));
        simpleExample.put("taxId", "20.018.183/0001-80");
        simpleExample.put("name", "Iron Bank S.A.");
        simpleExample.put("expiration", 123456789);
        simpleExample.put("fine", 2);
        simpleExample.put("interest", 1.3);
        invoices.add(new Invoice(simpleExample));

        List<HashMap<String, Object>> discounts = new ArrayList<>();
        HashMap<String, Object> discount = new HashMap<>();
        discount.put("due", getDateString(2));
        discount.put("percentage", 2.5);
        discounts.add(discount);
        invoices.add(new Invoice(new HashMap<String, Object>(){{
            put("amount", 400000);
            put("taxId", "012.345.678-90");
            put("name", "Iron Bank S.A.");
            put("expiration", 123456789);
            put("fine", 2);
            put("interest", 1.3);
            put("discounts", discounts);
            put("due", getDateString(2));
        }}));

        invoices = Invoice.create(invoices);

        for (Invoice invoice : invoices) {
            Assert.assertEquals(invoice.id, Invoice.get(invoice.id).id);
            Assert.assertNotNull(invoice.id);
            System.out.println(invoice);
        }
    }

    @Test
    public void testQueryGetPdfAndQrcode() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Invoice> invoices = Invoice.query(params);

        int i = 0;
        for (Invoice invoice : invoices) {
            i += 1;
            invoice = Invoice.get(invoice.id);
            System.out.println(invoice);
            InputStream png = Invoice.qrcode(invoice.id);
            Assert.assertNotNull(png);
            java.nio.file.Files.copy(
                png,
                new File("qrcode.png").toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );
            InputStream pdf = Invoice.pdf(invoice.id);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                pdf,
                new File("invoice.pdf").toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", "created");
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Invoice> invoices = Invoice.query(params);
        for (Invoice invoice : invoices) {
            HashMap<String, Object> patchData = new HashMap<>();
            patchData.put("status", "canceled");
            Invoice updatedInvoice = Invoice.update(invoice.id, patchData);
            Assert.assertEquals(updatedInvoice.status, "canceled");
            System.out.println(updatedInvoice);
        }
    }

    @Test
    public void testUpdateAmount() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", "created");
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Invoice> invoices = Invoice.query(params);
        for (Invoice invoice : invoices) {
            HashMap<String, Object> patchData = new HashMap<>();
            patchData.put("amount", 4321);
            Invoice updatedInvoice = Invoice.update(invoice.id, patchData);
            Assert.assertEquals(updatedInvoice.amount.longValue(), 4321);
            System.out.println(updatedInvoice);
        }
    }

    @Test
    public void testUpdateDue() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", "created");
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Invoice> invoices = Invoice.query(params);
        for (Invoice invoice : invoices) {
            HashMap<String, Object> patchData = new HashMap<>();
            patchData.put("due", getDatetimeString(7));
            Invoice updatedInvoice = Invoice.update(invoice.id, patchData);
            Assert.assertNotEquals(invoice.due, updatedInvoice.due);
            System.out.println(updatedInvoice);
        }
    }

    @Test
    public void testUpdateExpiration() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", "created");
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Invoice> invoices = Invoice.query(params);
        for (Invoice invoice : invoices) {
            HashMap<String, Object> patchData = new HashMap<>();
            patchData.put("expiration", 123456);
            Invoice updatedInvoice = Invoice.update(invoice.id, patchData);
            System.out.println(updatedInvoice);
            Assert.assertEquals(123456, updatedInvoice.expiration.longValue());
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Invoice.Log> logs = Invoice.Log.query(params);

        int i = 0;
        for (Invoice.Log log : logs) {
            i += 1;
            log = Invoice.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.invoice.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
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
            Invoice.Page page = Invoice.page(params);
            for (Invoice invoice: page.invoices) {
                System.out.println(invoice);
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
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Invoice.Log.Page page = Invoice.Log.page(params);
            for (Invoice.Log log: page.logs) {
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

    @Test
    public void testInvoicePayment() throws Exception{
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("status", "paid");
        Generator<Invoice> invoices = Invoice.query(params);

        int i = 0;
        for (Invoice invoice : invoices) {
            i += 1;
            Invoice.Payment payment = Invoice.payment(invoice.id);
            Assert.assertNotNull(payment.amount);
            System.out.println(payment);
        }
        System.out.println(i);
    }

    @Test
    public void testInvoiceLogPdfGet() throws Exception {
        Settings.user = User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("types", "reversed");
        params.put("limit", 1);
        Generator<Invoice.Log> queries = Invoice.Log.query(params);
        for (Invoice.Log log : queries) {
            String logId = log.id;
            InputStream pdf = Invoice.Log.pdf(logId);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                pdf,
                new File("invoice.pdf").toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );
            System.out.println(log);
        }
    }

    public String getDatetimeString(int delta) {
        ZonedDateTime datetime = ZonedDateTime.now().plusDays(delta);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        return dateFormat.format(datetime).concat("+00:00");
    }

    public String getDateString(int delta) {
        return LocalDate.now().plusDays(delta).toString();
    }
}
