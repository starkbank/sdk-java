import com.starkbank.*;
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
        HashMap<String, Object> data = new HashMap<>();
        data.put("brcode", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A");
        data.put("taxId", "20.018.183/0001-80");
        data.put("description", "Tony Stark's Suit");
        data.put("amount", 7654321);
        data.put("scheduled", getDateString(3));
        data.put("tags", new String[]{"Stark", "Suit"});

        payments.add(new BrcodePayment(data));

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

    public String getDateString(int delta) {
        LocalDateTime datetime = LocalDateTime.now().plusDays(delta);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(datetime);
    }
}