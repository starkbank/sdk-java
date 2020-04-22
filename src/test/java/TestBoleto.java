import com.starkbank.*;
import com.starkbank.utils.Generator;
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
    public void testPost() throws Exception {
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
        boletos.add(new Boleto(data));

        boletos = Boleto.create(boletos);

        for (Boleto boleto : boletos) {
            System.out.println(boleto);
        }
    }

    @Test
    public void testGetAndGetInfoAndPdf() throws Exception {
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
    public void testPostAndDelete() throws Exception {
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
        data.put("due", "2020-05-20");
        data.put("fine", 2.5);
        data.put("interest", 1.3);
        data.put("overdueLimit", 5);
        data.put("tags", new String[]{"War supply", "Invoice #1234"});
        boletos.add(new Boleto(data));

        boletos = Boleto.create(boletos);

        for (Boleto boleto : boletos) {
            boleto = Boleto.delete(boleto.id);
            System.out.println(boleto);
        }
    }

    @Test
    public void testLogGet() throws Exception{
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
            System.out.println(log.boleto.id);
        }
        Assert.assertTrue(i > 0);
    }
}
