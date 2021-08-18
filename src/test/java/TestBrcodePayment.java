import com.starkbank.BrcodePayment;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class TestBrcodePayment {
    
    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<BrcodePayment> payments = new ArrayList<>();

        payments.add(TestBrcodePayment.example(true));

        payments = BrcodePayment.create(payments);

        for (BrcodePayment payment : payments) {
            Assert.assertNotNull(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testQueryGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<BrcodePayment> payments = BrcodePayment.query(params);

        int i = 0;
        for (BrcodePayment payment : payments) {
            i += 1;
            String paymentId = payment.id;
            payment = BrcodePayment.get(paymentId);
            Assert.assertEquals(paymentId, payment.id);
            System.out.println(payment);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testCancel() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("status", "created");
        params.put("limit", 1);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<BrcodePayment> invoices = BrcodePayment.query(params);
        for (BrcodePayment invoice : invoices) {
            HashMap<String, Object> patchData = new HashMap<>();
            patchData.put("status", "canceled");
            BrcodePayment updatedBrcodePayment = BrcodePayment.update(invoice.id, patchData);
            Assert.assertEquals(updatedBrcodePayment.status, "canceled");
            System.out.println(updatedBrcodePayment);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<BrcodePayment.Log> logs = BrcodePayment.Log.query(params);

        int i = 0;
        for (BrcodePayment.Log log : logs) {
            i += 1;
            log = BrcodePayment.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.payment.id);
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
            BrcodePayment.Page page = BrcodePayment.page(params);
            for (BrcodePayment payment: page.payments) {
                System.out.println(payment);
                if (ids.contains(payment.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(payment.id);
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
            BrcodePayment.Log.Page page = BrcodePayment.Log.page(params);
            for (BrcodePayment.Log log: page.logs) {
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

    static BrcodePayment example(boolean scheduled) throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        data.put("brcode", "00020126580014br.gov.bcb.pix013635719950-ac93-4bab-8ad6-56d7fb63afd252040000530398654040.005802BR5915Stark Bank S.A.6009Sao Paulo62070503***6304AA26");
        data.put("taxId", "20.018.183/0001-80");
        data.put("description", "Tony Stark's Suit");
        data.put("amount", 7654321);
        
        if (scheduled)
            data.put("scheduled", getDateString(3));
        data.put("tags", new String[]{"Stark", "Suit"});

        return new BrcodePayment(data);
    }

    static String getDateString(int delta) {
        LocalDateTime datetime = LocalDateTime.now().plusDays(delta);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(datetime);
    }
}
