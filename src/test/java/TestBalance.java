import com.starkbank.Balance;
import com.starkbank.User;
import org.junit.Test;


public class TestBalance {

    @Test
    public void testGet() throws Exception {
        User.defaultUser = utils.User.defaultProject();
        Balance balance = Balance.get();
        System.out.println(balance.amount);
    }
}
