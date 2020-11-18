import com.starkbank.BrcodePreview;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import org.junit.Test;

import java.util.HashMap;

public class TestBrcodePreview {

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("brcodes", new String[]{"00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A"});
        Generator<BrcodePreview> dictKeys = BrcodePreview.query(params);

        int i = 0;
        for (BrcodePreview dictKey : dictKeys) {
            i += 1;
            System.out.println(dictKey);
        }
        System.out.println(i);
    }
}
