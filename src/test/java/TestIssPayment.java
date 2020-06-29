import com.starkbank.IssPayment;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class TestIssPayment {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<IssPayment> payments = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String paymentDay = dateFormat.format(tomorrow);

        data.put("barCode", "8364000" + String.format("%08d", randomNum) + "01380076105302611108067159411");
        data.put("scheduled", paymentDay);
        data.put("description", "Payment my taxes");
        data.put("tags", new String[]{"Government", "Winterfell"});
        payments.add(new IssPayment(data));

        payments = IssPayment.create(payments);

        for (IssPayment payment : payments) {
            Assert.assertNotNull(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testQueryGetAndPdf() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("status", "success");
        Generator<IssPayment> payments = IssPayment.query(params);

        int i = 0;
        for (IssPayment payment : payments) {
            i += 1;
            payment = IssPayment.get(payment.id);
            System.out.println(payment);
            InputStream pdf = IssPayment.pdf(payment.id);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                    pdf,
                    new File("iss-payment.pdf").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testCreateAndDelete() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<IssPayment> payments = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);

        LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String paymentDay = dateFormat.format(tomorrow);

        data.put("barCode", "8364000" + String.format("%08d", randomNum) + "01380076105302611108067159411");
        data.put("scheduled", paymentDay);
        data.put("description", "Payment my taxes");
        data.put("tags", new String[]{"Government", "Winterfell"});
        payments.add(new IssPayment(data));

        payments = IssPayment.create(payments);

        for (IssPayment payment : payments) {
            payment = IssPayment.delete(payment.id);
            Assert.assertNotNull(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<IssPayment.Log> logs = IssPayment.Log.query(params);

        int i = 0;
        for (IssPayment.Log log : logs) {
            i += 1;
            log = IssPayment.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.payment.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }
}
