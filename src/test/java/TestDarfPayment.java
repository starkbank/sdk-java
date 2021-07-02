import com.starkbank.Settings;
import com.starkbank.DarfPayment;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestDarfPayment {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<DarfPayment> payments = new ArrayList<>();

        payments.add(TestDarfPayment.example(true));

        System.out.println(payments.get(0));
        payments = DarfPayment.create(payments);

        for (DarfPayment payment : payments) {
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
        Generator<DarfPayment> payments = DarfPayment.query(params);

        int i = 0;
        for (DarfPayment payment : payments) {
            i += 1;
            payment = DarfPayment.get(payment.id);
            System.out.println(payment);
            InputStream pdf = DarfPayment.pdf(payment.id);
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
        List<DarfPayment> payments = new ArrayList<>();

        payments.add(TestDarfPayment.example(true));

        payments = DarfPayment.create(payments);

        for (DarfPayment payment : payments) {
            payment = DarfPayment.delete(payment.id);
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
        Generator<DarfPayment.Log> logs = DarfPayment.Log.query(params);

        int i = 0;
        for (DarfPayment.Log log : logs) {
            i += 1;
            log = DarfPayment.Log.get(log.id);
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
            DarfPayment.Page page = DarfPayment.page(params);
            for (DarfPayment payment: page.payments) {
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
            DarfPayment.Log.Page page = DarfPayment.Log.page(params);
            for (DarfPayment.Log log: page.logs) {
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


    static DarfPayment example(boolean scheduled) throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);
        data.put("description", "Darf Payment Example");
        data.put("tags", new String[]{"Darf"});
        data.put("due", LocalDate.from(LocalDate.now().plusDays(1)).toString());
        data.put("competence", "2020-04-03");
        data.put("fineAmount", 100);
        data.put("interestAmount", "100");
        data.put("nominalAmount", "1000");
        data.put("revenueCode", "0201");
        data.put("taxId", "45678350005");

        if(scheduled) {
            LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String paymentDay = dateFormat.format(tomorrow);
            data.put("scheduled", paymentDay);
        }
        return new DarfPayment(data);
    }
}
