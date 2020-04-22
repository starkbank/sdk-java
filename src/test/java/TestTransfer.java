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

public class TestTransfer {

    @Test
    public void testPost() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        List<Transfer> transfers = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 100000000);
        data.put("bankCode", "341");
        data.put("branchCode", "2201");
        data.put("accountNumber", "76543-8");
        data.put("taxId", "594.739.480-42");
        data.put("name", "Daenerys Targaryen Stormborn");
        data.put("tags", new String[]{"daenerys", "invoice/1234"});
        transfers.add(new Transfer(data));

        transfers = Transfer.create(transfers);

        for (Transfer transfer : transfers) {
            System.out.println(transfer);
        }
    }

    @Test
    public void testGetAndGetInfoAndPdf() throws Exception {
        User.defaultUser = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("status", "success");
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Transfer> transfers = Transfer.query(params);

        int i = 0;
        for (Transfer transfer : transfers) {
            i += 1;
            transfer = Transfer.get(transfer.id);
            System.out.println(transfer);
            InputStream pdf = Transfer.pdf(transfer.id);
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
    public void testLogGet() throws Exception{
        User.defaultUser = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Transfer.Log> logs = Transfer.Log.query(params);

        int i = 0;
        for (Transfer.Log log : logs) {
            i += 1;
            log = Transfer.Log.get(log.id);
            System.out.println(log.transfer.id);
        }
        Assert.assertTrue(i > 0);
    }
}
