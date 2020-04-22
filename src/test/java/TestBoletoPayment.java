import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestBoletoPayment {

    @Test
    public void testPost() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<BoletoPayment> payments = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);
        data.put("line", "34191.09107 05447.947309 71444.640008 8 846600" + String.format("%08d", randomNum));
        data.put("taxId", "38.435.677/0001-25");
        data.put("scheduled", "2020-04-30");
        data.put("description", "Payment for killing white walkers");
        data.put("tags", new String[]{"little girl", "no one"});
        payments.add(new BoletoPayment(data));

        payments = BoletoPayment.create(payments);

        for (BoletoPayment payment : payments) {
            System.out.println(payment);
        }
    }

    @Test
    public void testGetAndGetInfoAndPdf() throws Exception {
        User.defaultUser = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("status", "success");
        Generator<BoletoPayment> payments = BoletoPayment.query(params);

        int i = 0;
        for (BoletoPayment payment : payments) {
            i += 1;
            payment = BoletoPayment.get(payment.id);
            System.out.println(payment);
            Assert.assertNotNull(BoletoPayment.pdf(payment.id)); // TODO: write pdf file
        }
        System.out.println(i);
    }

    @Test
    public void testPostAndDelete() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<BoletoPayment> payments = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000000);
        data.put("line", "34191.09107 05447.947309 71444.640008 8 846600" + String.format("%08d", randomNum));
        data.put("taxId", "38.435.677/0001-25");
        data.put("scheduled", "2020-04-30");
        data.put("description", "Payment for killing white walkers");
        data.put("tags", new String[]{"little girl", "no one"});
        payments.add(new BoletoPayment(data));

        payments = BoletoPayment.create(payments);

        for (BoletoPayment payment : payments) {
            payment = BoletoPayment.delete(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testLogGet() throws Exception{
        User.defaultUser = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<BoletoPayment.Log> logs = BoletoPayment.Log.query(params);

        int i = 0;
        for (BoletoPayment.Log log : logs) {
            i += 1;
            log = BoletoPayment.Log.get(log.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }
}
