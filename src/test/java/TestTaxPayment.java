import com.starkbank.Settings;
import com.starkbank.TaxPayment;
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

public class TestTaxPayment {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<TaxPayment> payments = new ArrayList<>();

        payments.add(TestTaxPayment.example(true));

        payments = TaxPayment.create(payments);

        for (TaxPayment payment : payments) {
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
        Generator<TaxPayment> payments = TaxPayment.query(params);

        int i = 0;
        for (TaxPayment payment : payments) {
            i += 1;
            payment = TaxPayment.get(payment.id);
            System.out.println(payment);
            InputStream pdf = TaxPayment.pdf(payment.id);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                    pdf,
                    new File("tax-payment.pdf").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testCreateAndDelete() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<TaxPayment> payments = new ArrayList<>();

        payments.add(TestTaxPayment.example(true));

        payments = TaxPayment.create(payments);

        for (TaxPayment payment : payments) {
            payment = TaxPayment.delete(payment.id);
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
        Generator<TaxPayment.Log> logs = TaxPayment.Log.query(params);

        int i = 0;
        for (TaxPayment.Log log : logs) {
            i += 1;
            log = TaxPayment.Log.get(log.id);
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
            TaxPayment.Page page = TaxPayment.page(params);
            for (TaxPayment payment: page.payments) {
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
            TaxPayment.Log.Page page = TaxPayment.Log.page(params);
            for (TaxPayment.Log log: page.logs) {
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


    static TaxPayment example(boolean scheduled) throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);

        data.put("barCode", "8164000" + String.format("%08d", randomNum) + "05110076105302611108067159411");
        data.put("description", "Tax Payment Example");
        data.put("tags", new String[]{"City Hall", "Tax"});

        if(scheduled) {
            LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String paymentDay = dateFormat.format(tomorrow);
            data.put("scheduled", paymentDay);
        }
        return new TaxPayment(data);
    }
}
