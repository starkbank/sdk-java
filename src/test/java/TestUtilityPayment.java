import com.starkbank.User;
import com.starkbank.UtilityPayment;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class TestUtilityPayment {

    @Test
    public void testCreate() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<UtilityPayment> payments = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);
        data.put("barCode", "8364000" + String.format("%08d", randomNum) + "01380076105302611108067159411");
        data.put("scheduled", "2020-04-30");
        data.put("description", "Electricity for the Long Night");
        data.put("tags", new String[]{"Energy", "Winterfell"});
        payments.add(new UtilityPayment(data));

        payments = UtilityPayment.create(payments);

        for (UtilityPayment payment : payments) {
            Assert.assertNotNull(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testQueryGetAndPdf() throws Exception {
        User.defaultUser = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("status", "success");
        Generator<UtilityPayment> payments = UtilityPayment.query(params);

        int i = 0;
        for (UtilityPayment payment : payments) {
            i += 1;
            payment = UtilityPayment.get(payment.id);
            System.out.println(payment);
            InputStream pdf = UtilityPayment.pdf(payment.id);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                    pdf,
                    new File("utility-payment.pdf").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testCreateAndDelete() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<UtilityPayment> payments = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);
        data.put("barCode", "8364000" + String.format("%08d", randomNum) + "01380076105302611108067159411");
        data.put("scheduled", "2020-04-30");
        data.put("description", "Electricity for the Long Night");
        data.put("tags", new String[]{"Energy", "Winterfell"});
        payments.add(new UtilityPayment(data));

        payments = UtilityPayment.create(payments);

        for (UtilityPayment payment : payments) {
            payment = UtilityPayment.delete(payment.id);
            Assert.assertNotNull(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<UtilityPayment.Log> logs = UtilityPayment.Log.query(params);

        int i = 0;
        for (UtilityPayment.Log log : logs) {
            i += 1;
            log = UtilityPayment.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.payment.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }
}
