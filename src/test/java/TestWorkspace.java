import com.starkbank.Settings;
import com.starkbank.Workspace;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

public class TestWorkspace {
    
    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 5);
        Generator<Workspace> workspaces = Workspace.query(params);

        int i = 0;
        for (Workspace workspace : workspaces) {
            i += 1;
            String prevId = workspace.id;
            workspace = Workspace.get(workspace.id);
            Assert.assertNotNull(workspace.id);
            Assert.assertEquals(prevId, workspace.id);
            System.out.println(workspace);
        }
        System.out.println(i);
    }
}
