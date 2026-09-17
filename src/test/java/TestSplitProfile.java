import org.junit.Test;
import com.starkbank.*;
import org.junit.Assert;
import com.starkbank.utils.Generator;

import java.util.*;

public class TestSplitProfile {

    @Test
    public void testPut() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<SplitProfile> profiles = new ArrayList<>();
        profiles.add(TestSplitProfile.example());

        profiles = SplitProfile.put(profiles);

        for (SplitProfile profile : profiles) {
            Assert.assertNotNull(profile.id);
            System.out.println(profile);
        }

        SplitProfile profile = SplitProfile.get(profiles.get(0).id);
        Assert.assertEquals("day", profile.interval);
        Assert.assertEquals(Integer.valueOf(0), profile.delay);
    }

    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        Map<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<SplitProfile> profiles = SplitProfile.query(params);

        int i = 0;
        for (SplitProfile profile : profiles) {
            i += 1;
            profile = SplitProfile.get(profile.id);
            Assert.assertNotNull(profile.id);
            System.out.println(profile);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SplitProfile.Page page = SplitProfile.page(params);
            for (SplitProfile profile : page.splitProfiles) {
                System.out.println(profile);
                if (ids.contains(profile.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(profile.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<SplitProfile.Log> logs = SplitProfile.Log.query(params);

        int i = 0;
        for (SplitProfile.Log log : logs) {
            i += 1;
            log = SplitProfile.Log.get(log.id);
            Assert.assertNotNull(log.id);
            Assert.assertNotNull(log.profile.id);
            System.out.println(log);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            SplitProfile.Log.Page page = SplitProfile.Log.page(params);
            for (SplitProfile.Log log : page.logs) {
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
    }

    static SplitProfile example() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("interval", "day");
        params.put("delay", 0);
        return new SplitProfile(params);
    }
}
