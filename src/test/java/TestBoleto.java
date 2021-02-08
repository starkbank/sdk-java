import com.starkbank.Boleto;
import com.starkbank.Settings;
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


public class TestBoleto {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<Boleto> boletos = new ArrayList<>();

        boletos.add(TestBoleto.example());

        boletos = Boleto.create(boletos);

        for (Boleto boleto : boletos) {
            Assert.assertNotNull(boleto.id);
            System.out.println(boleto);
            System.out.println(boleto.descriptions);
            System.out.println(boleto.discounts);
        }
    }

    @Test
    public void testQueryGetAndPdf() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Boleto> boletos = Boleto.query(params);

        int i = 0;
        for (Boleto boleto : boletos) {
            i += 1;
            boleto = Boleto.get(boleto.id);
            System.out.println(boleto);
            HashMap<String, Object> options = new HashMap<>();
            options.put("layout", "booklet");
            options.put("hiddenFields", new String[]{"customerAddress"});
            InputStream pdf = Boleto.pdf(boleto.id, options);
            Assert.assertNotNull(pdf);
            java.nio.file.Files.copy(
                pdf,
                new File("boleto.pdf").toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testCreateAndDelete() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<Boleto> boletos = new ArrayList<>();

        boletos.add(TestBoleto.example());

        boletos.add(TestBoleto.example());

        boletos = Boleto.create(boletos);

        for (Boleto boleto : boletos) {
            boleto = Boleto.delete(boleto.id);
            Assert.assertNotNull(boleto.id);
            System.out.println(boleto);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Boleto.Log> logs = Boleto.Log.query(params);

        int i = 0;
        for (Boleto.Log log : logs) {
            i += 1;
            log = Boleto.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.boleto.id);
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
            Boleto.Page page = Boleto.page(params);
            for (Boleto boleto: page.boletos) {
                System.out.println(boleto);
                if (ids.contains(boleto.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(boleto.id);
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
            Boleto.Log.Page page = Boleto.Log.page(params);
            for (Boleto.Log log: page.logs) {
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

    static Boleto example() throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 4000);
        data.put("name", "Iron Bank S.A.");
        data.put("taxId", "20.018.183/0001-80");
        data.put("streetLine1", "Av. Faria Lima, 1844");
        data.put("streetLine2", "CJ 13");
        data.put("district", "Itaim Bibi");
        data.put("city", "São Paulo");
        data.put("stateCode", "SP");
        data.put("zipCode", "01500-000");
        data.put("due", getDateString(14));
        data.put("fine", 2);
        data.put("interest", 1.3);
        data.put("overdueLimit", 5);
        data.put("receiverName", "Iron Bank Receiver S.A.");
        data.put("receiverTaxId", "123.456.789-09");
        data.put("tags", new String[]{"War supply", "Invoice #1234"});

        List<Boleto.Description> descriptions = new ArrayList<>();
        descriptions.add(new Boleto.Description("first part of amount", 3000));
        descriptions.add(new Boleto.Description("some other explanation"));
        data.put("descriptions", descriptions);

        List<Boleto.Discount> discounts = new ArrayList<>();
        discounts.add(new Boleto.Discount(getDateString(1), 3));
        discounts.add(new Boleto.Discount(getDateString(2), 2.0));
        data.put("discounts", discounts);

        return new Boleto(data);
    }

    static String getDateString(int delta) {
        LocalDateTime datetime = LocalDateTime.now().plusDays(delta);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(datetime);
    }
}
