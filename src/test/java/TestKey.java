import com.starkbank.Key;
import org.junit.Test;

import java.io.File;


public class TestKey {
    @Test
    public void testCreate() throws Exception {
        Key key = Key.create();
        assert key.publicPem != null;
        assert key.privatePem != null;
    }

    @Test
    public void testCreateWithSavepath() throws Exception {
        String savepath = "keys";
        Key.create(savepath);
        File f = new File(savepath, "privateKey.pem");
        if(!(f.exists() && !f.isDirectory())) {
            throw new Error("Private Key not created!");
        }
        f = new File(savepath, "publicKey.pem");
        if(!(f.exists() && !f.isDirectory())) {
            throw new Error("Public Key not created!");
        }
    }
}
