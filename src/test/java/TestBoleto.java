import com.starkbank.*;
import io.herrmann.generator.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestBoleto {

    @Test
    public void testCreate() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<Boleto> boletos = new ArrayList<>();
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
        data.put("due", "2020-05-20");
        data.put("fine", 2.5);
        data.put("interest", 1.3);
        data.put("overdueLimit", 5);
        data.put("tags", new String[]{"War supply", "Invoice #1234"});

        List<HashMap<String, Object>> descriptions = new ArrayList<>();
        descriptions.add(new HashMap<>());
        descriptions.add(new HashMap<>());
        descriptions.get(0).put("text", "first part of amount");
        descriptions.get(0).put("amount", 3000);
        descriptions.get(1).put("text", "some other explanation");
        data.put("descriptions", descriptions);

        List<HashMap<String, Object>> discounts = new ArrayList<>();
        discounts.add(new HashMap<>());
        discounts.get(0).put("percentage", 5);
        discounts.get(0).put("date", "2020-05-17");
        discounts.add(new HashMap<>());
        discounts.get(1).put("percentage", 3.5);
        discounts.get(1).put("date", "2020-05-18");
        discounts.add(new HashMap<>());
        discounts.get(2).put("percentage", 1.5);
        discounts.get(2).put("date", "2020-05-19");
        data.put("discounts", discounts);

        boletos.add(new Boleto(data));

        boletos = Boleto.create(boletos);

        for (Boleto boleto : boletos) {
            Assert.assertNotNull(boleto.id);
            System.out.println(boleto);
        }
    }

    @Test
    public void testQueryGetAndPdf() throws Exception {
        User.defaultUser = utils.User.defaultProject();

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
            InputStream pdf = Boleto.pdf(boleto.id);
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
        User.defaultUser = utils.User.defaultProject();
        List<Boleto> boletos = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 1234);
        data.put("name", "Iron Bank S.A.");
        data.put("taxId", "20.018.183/0001-80");
        data.put("streetLine1", "Av. Faria Lima, 1844");
        data.put("streetLine2", "CJ 13");
        data.put("district", "Itaim Bibi");
        data.put("city", "São Paulo");
        data.put("stateCode", "SP");
        data.put("zipCode", "01500-000");
        data.put("fine", 2.5);
        data.put("interest", 1.3);
        data.put("overdueLimit", 5);
        data.put("tags", new String[]{"War supply", "Invoice #1234"});
        boletos.add(new Boleto(data));

        boletos = Boleto.create(boletos);

        for (Boleto boleto : boletos) {
            boleto = Boleto.delete(boleto.id);
            Assert.assertNotNull(boleto.id);
            System.out.println(boleto);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        User.defaultUser = utils.User.defaultProject();
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
}
