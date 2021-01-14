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
        params.put("brcodes", new String[]{"00020126580014br.gov.bcb.pix013635719950-ac93-4bab-8ad6-56d7fb63afd252040000530398654040.005802BR5915Stark Bank S.A.6009Sao Paulo62070503***6304AA26"});
        Generator<BrcodePreview> dictKeys = BrcodePreview.query(params);

        int i = 0;
        for (BrcodePreview dictKey : dictKeys) {
            i += 1;
            System.out.println(dictKey);
        }
        System.out.println(i);
    }
}
