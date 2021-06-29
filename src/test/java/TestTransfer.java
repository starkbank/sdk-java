import com.starkbank.Transfer;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class TestTransfer {

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<Transfer> transfers = new ArrayList<>();

        transfers.add(TestTransfer.example(false));

        transfers = Transfer.create(transfers);

        for (Transfer transfer : transfers) {
            Assert.assertNotNull(transfer.id);
            System.out.println(transfer);
        }
    }

    @Test
    public void testDelete() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<Transfer> transfers = new ArrayList<>();

        transfers.add(TestTransfer.example(true));

        transfers = Transfer.create(transfers);
        Transfer createdTransfer = transfers.get(0);
        Transfer deletedTransfer = Transfer.delete(transfers.get(0).id);

        Assert.assertEquals("canceled", deletedTransfer.status);
        Assert.assertEquals(createdTransfer.id, deletedTransfer.id);

        System.out.println(deletedTransfer);
    }

    @Test
    public void testQueryGetAndPdf() throws Exception {
        Settings.user = utils.User.defaultProject();

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
                new File("transfer.pdf").toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
        System.out.println(i);
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Transfer.Log> logs = Transfer.Log.query(params);

        int i = 0;
        for (Transfer.Log log : logs) {
            i += 1;
            log = Transfer.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.transfer.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testQueryIds() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 10);
        Generator<Transfer> transfers = Transfer.query(params);

        ArrayList<String> transfersIdsExpected = new ArrayList<>();
        for (Transfer transfer : transfers) {
            Assert.assertNotNull(transfer.id);
            transfersIdsExpected.add(transfer.id);
        }

        params.put("ids", transfersIdsExpected.toArray(new String[0]));
        Generator<Transfer> transfersResult = Transfer.query(params);
    
        ArrayList<String> transfersIdsResult = new ArrayList<>();
        for (Transfer transfer : transfersResult){
            Assert.assertNotNull(transfer.id);
            transfersIdsResult.add(transfer.id);
        }
        
        Collections.sort(transfersIdsExpected);
        Collections.sort(transfersIdsResult);
        Assert.assertEquals(transfersIdsExpected, transfersIdsResult);
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
            Transfer.Page page = Transfer.page(params);
            for (Transfer transfer: page.transfers) {
                System.out.println(transfer);
                if (ids.contains(transfer.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(transfer.id);
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
            Transfer.Log.Page page = Transfer.Log.page(params);
            for (Transfer.Log log: page.logs) {
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

    static Transfer example(boolean scheduled) throws Exception{
        HashMap<String, Object> data = new HashMap<>();
        data.put("amount", 100000000);
        data.put("bankCode", "341");
        data.put("branchCode", "2201");
        data.put("accountNumber", "76543-8");
        data.put("accountType", "checking");
        data.put("externalId", "java-" + UUID.randomUUID().toString());
        data.put("taxId", "594.739.480-42");
        data.put("name", "Daenerys Targaryen Stormborn");
        data.put("description", "Test description");
        data.put("tags", new String[]{"daenerys", "invoice/1234"});

        if(scheduled) {
            LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String tomorrowString = dateFormat.format(tomorrow);
            data.put("scheduled", tomorrowString);
        }

        return new Transfer(data);
    }
}
