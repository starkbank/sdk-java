import com.starkbank.Settings;
import com.starkbank.Workspace;
import com.starkbank.Organization;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;
import java.util.UUID;


public class TestWorkspace {
    
    @Test
    public void testQueryAndGet() throws Exception {
        Organization organization = utils.User.defaultOrganization();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 5);
        Generator<Workspace> workspaces = Workspace.query(params, organization);

        int i = 0;
        for (Workspace workspace : workspaces) {
            i += 1;
            String prevId = workspace.id;
            workspace = Workspace.get(workspace.id, Organization.replace(organization, workspace.id));
            Assert.assertNotNull(workspace.id);
            Assert.assertEquals(prevId, workspace.id);
            System.out.println(workspace);
        }
        System.out.println(i);
    }

    @Test
    public void testCreate() throws Exception {
        Settings.user = utils.User.defaultOrganization();
        HashMap<String, Object> params = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        params.put("username", "starkv2-" + uuid);
        params.put("name", "Stark V2: " + uuid);
        Workspace workspace = Workspace.create(params);
        Assert.assertNotNull(workspace.id);
        Assert.assertNotNull(workspace.username);
        Assert.assertNotNull(workspace.name);
        System.out.println(workspace);
    }
}
