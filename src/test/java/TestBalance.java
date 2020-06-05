import com.starkbank.Balance;
import com.starkbank.Settings;
import org.junit.Test;
import org.junit.Assert;


public class TestBalance {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        Balance balance = Balance.get();
        System.out.println(balance);
        Assert.assertNotNull(balance.id);
        Assert.assertNotNull(balance.currency);
    }
}
