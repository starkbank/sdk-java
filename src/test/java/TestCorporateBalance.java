import org.junit.Test;
import org.junit.Assert;
import com.starkbank.Settings;
import com.starkbank.CorporateBalance;


public class TestCorporateBalance {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        CorporateBalance balance = CorporateBalance.get();
        System.out.println(balance);
        Assert.assertNotNull(balance.id);
        Assert.assertNotNull(balance.currency);
    }
}
