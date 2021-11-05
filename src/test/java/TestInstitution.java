import com.starkbank.Institution;
import com.starkbank.Settings;
import com.starkbank.utils.SubResource;
import org.junit.Assert;
import org.junit.Test;
import utils.User;

import java.util.*;

public class TestInstitution {

    @Test
    public void testInstitutionQuery() throws Exception {
        Settings.user = User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();

        params.put("search", "stark");
        List<Institution> institutions = Institution.query(params);
        Assert.assertEquals(2, institutions.size());

        params.remove("search");
        params.put("spiCodes", Collections.singletonList("20018183"));
        institutions = Institution.query(params);
        Assert.assertEquals(1, institutions.size());

        params.remove("spiCodes");
        params.put("strCodes", Collections.singletonList("341"));
        institutions = Institution.query(params);
        Assert.assertEquals(1, institutions.size());

    }
}
