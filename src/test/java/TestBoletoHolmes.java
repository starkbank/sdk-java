import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestBoletoHolmes {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
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

        boletos.add(new Boleto(data));

        boletos = Boleto.create(boletos);

        List<BoletoHolmes> holmes = new ArrayList<>();
        HashMap<String, Object> dataHolmes = new HashMap<>(); 
        for (Boleto boleto : boletos){
            dataHolmes.put("boletoId", boleto.id);
            holmes.add(new BoletoHolmes(dataHolmes));
        }

        holmes = BoletoHolmes.create(holmes);

        for (BoletoHolmes sherlock : holmes) {
            Assert.assertNotNull(sherlock.id);
            System.out.println(sherlock);
        }
    }

    @Test
    public void testGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("status", "solved");
        Generator<BoletoHolmes> holmes = BoletoHolmes.query(params);

        int i = 0;
        for (BoletoHolmes sherlock : holmes) {
            i += 1;
            Assert.assertEquals(sherlock.id, BoletoHolmes.get(sherlock.id).id);
            Assert.assertNotNull(sherlock.id);
            System.out.println(sherlock);
        }
        Assert.assertTrue(i == 3);
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<BoletoHolmes.Log> logs = BoletoHolmes.Log.query(params);

        int i = 0;
        for (BoletoHolmes.Log log : logs) {
            i += 1;
            log = BoletoHolmes.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.holmes.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }
}
