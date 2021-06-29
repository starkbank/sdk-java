import com.starkbank.Settings;
import com.starkbank.Workspace;
import com.starkbank.Organization;
import com.starkbank.utils.Generator;
import org.junit.Test;
import org.junit.Assert;

import java.util.*;


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
    public void testCreateAndUpdate() throws Exception {
        Organization organization = utils.User.defaultOrganization();
        HashMap<String, Object> params = new HashMap<>();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        params.put("username", "starkv2-" + uuid);
        params.put("name", "Stark V2: " + uuid);
        params.put("allowedTaxIds", Arrays.asList("35953668082", "88889288043"));
        Workspace workspace = Workspace.create(params, organization);
        Assert.assertNotNull(workspace.id);
        Assert.assertNotNull(workspace.username);
        Assert.assertNotNull(workspace.name);
        System.out.println(workspace);

        String newName = "New name test";
        String newUsername = UUID.randomUUID().toString().replace("-", "");
        Map<String, Object> patchData = new HashMap<>();
        List<String> allowedIds = Arrays.asList("96448045031", "26312286002");
        patchData.put("name", newName);
        patchData.put("username", newUsername);
        patchData.put("allowedTaxIds", allowedIds);
        Workspace updatedWorkspace = Workspace.update(workspace.id, patchData, Organization.replace(organization, workspace.id));

        assert updatedWorkspace != null;
        if (!updatedWorkspace.name.equals(newName)) {
            throw new Exception("updatedWorkspace.name != " + newName);
        }
        if (!updatedWorkspace.username.equals(newUsername)) {
            throw new Exception("updatedWorkspace.username != " + newUsername);
        }
        if (updatedWorkspace.allowedTaxIds.size() != allowedIds.size()) {
            throw new Exception("updatedWorkspace.allowedTaxIds != " + allowedIds);
        }

        System.out.println(updatedWorkspace);
    }

    @Test
    public void testPage() throws Exception {
        Organization organization = utils.User.defaultOrganization();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Workspace.Page page = Workspace.page(params, organization);
            for (Workspace workspace: page.workspaces) {
                System.out.println(workspace);
                if (ids.contains(workspace.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(workspace.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }

        if (ids.size() != 4) {
            throw new Exception("ids.size() != 4");
        }
    }
}
