import com.starkbank.DictKey;
import com.starkbank.Settings;
import org.junit.Test;
import org.junit.Assert;


public class TestDictKey {

    @Test
    public void testGet() throws Exception {
        Settings.user = utils.User.defaultProject();
        String pixKey = "tony@starkbank.com";
        DictKey dictKey = DictKey.get(pixKey);
        System.out.println(dictKey);
        Assert.assertNotNull(dictKey.id);
        Assert.assertEquals(dictKey.id, pixKey);
    }
}
