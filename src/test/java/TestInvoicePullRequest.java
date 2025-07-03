import com.starkbank.*;
import utils.User;
import org.junit.Test;
import org.junit.Assert;
import com.starkbank.utils.Generator;

import java.util.*;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.StandardCopyOption;


public class TestInvoicePullRequest {

    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        Generator<InvoicePullRequest> requests = InvoicePullRequest.query(params);

        for (InvoicePullRequest request : requests) {
            request = InvoicePullRequest.get(request.id);
            Assert.assertNotNull(request.id);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            InvoicePullRequest.Page page = InvoicePullRequest.page(params);
            for (InvoicePullRequest request: page.requests) {
                if (ids.contains(request.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(request.id);
            }
            if (page.cursor == null) {
                break;
            }
            params.put("cursor", page.cursor);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        Generator<InvoicePullRequest.Log> logs = InvoicePullRequest.Log.query(params);

        int i = 0;
        for (InvoicePullRequest.Log log : logs) {
            i += 1;
            log = InvoicePullRequest.Log.get(log.id);
            Assert.assertNotNull(log.id);
        }
        Assert.assertTrue(i > 0);
    }

    @Test
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            InvoicePullRequest.Log.Page page = InvoicePullRequest.Log.page(params);
            for (InvoicePullRequest.Log log: page.logs) {
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
}
