import com.starkbank.*;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;


public class TestDeposit {

    @Test
    public void testQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Deposit> deposits = Deposit.query(params);
        System.out.println(deposits);
        int i = 0;
        for (Deposit deposit : deposits) {
            i += 1;
            System.out.println(deposit);
            String depositId = deposit.id;
            deposit = Deposit.get(depositId);
            Assert.assertNotNull(depositId);
            Assert.assertEquals(depositId, deposit.id);
            System.out.println(deposit);
        }
        Assert.assertTrue(i > 0);
    }
    
    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Deposit.Log> logs = Deposit.Log.query(params);

        int i = 0;
        for (Deposit.Log log : logs) {
            i += 1;
            log = Deposit.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.deposit.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    public String getDateString(int delta) {
        LocalDateTime datetime = LocalDateTime.now().plusDays(delta);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        return dateFormat.format(datetime);
    }
}
