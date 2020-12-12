import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

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


public class TestBoletoPayment {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<BoletoPayment> payments = new ArrayList<>();

        payments.add(TestBoletoPayment.example(true));

        payments = BoletoPayment.create(payments);

        for (BoletoPayment payment : payments) {
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
        Generator<BoletoPayment> payments = BoletoPayment.query(params);

        int i = 0;
        for (BoletoPayment payment : payments) {
            i += 1;
            payment = BoletoPayment.get(payment.id);
            System.out.println(payment);
            InputStream pdf = BoletoPayment.pdf(payment.id);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                    pdf,
                    new File("boleto-payment.pdf").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testCreateAndDelete() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<BoletoPayment> payments = new ArrayList<>();

        payments.add(TestBoletoPayment.example(true));

        payments = BoletoPayment.create(payments);

        for (BoletoPayment payment : payments) {
            payment = BoletoPayment.delete(payment.id);
            Assert.assertNotNull(payment.id);
            System.out.println(payment);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<BoletoPayment.Log> logs = BoletoPayment.Log.query(params);

        int i = 0;
        for (BoletoPayment.Log log : logs) {
            i += 1;
            log = BoletoPayment.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.payment.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    static BoletoPayment example(boolean scheduled) throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        List<Boleto> boletos = new ArrayList<>();
        boletos.add(TestBoleto.simpleExample());
        boletos =  Boleto.create(boletos);
        data.put("line", boletos.get(0).line);
        data.put("taxId", "38.435.677/0001-25");
        data.put("description", "Payment for killing white walkers");
        data.put("tags", new String[]{"little girl", "no one"});

        if(scheduled) {
            LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String boletoDay = dateFormat.format(tomorrow);
            data.put("scheduled", boletoDay);
        }

        return new BoletoPayment(data);
    }
}
