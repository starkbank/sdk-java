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


public class TestInvoicePullSubscription {

    @Test
    public void testCreatePush() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<InvoicePullSubscription> subscriptions = InvoicePullSubscription.create(Arrays.asList(Example("push")));

        for (InvoicePullSubscription subscription : subscriptions) {
            Assert.assertNotNull(subscription.id);
        }
    }

    @Test
    public void testCreateQrCode() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<InvoicePullSubscription> subscriptions = InvoicePullSubscription.create(Arrays.asList(Example("qrcode")));

        for (InvoicePullSubscription subscription : subscriptions) {
            Assert.assertNotNull(subscription.id);
        }
    }

    @Test
    public void testCreateQrCodeAndPayment() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<InvoicePullSubscription> subscriptions = InvoicePullSubscription.create(Arrays.asList(Example("qrcodeAndPayment")));

        for (InvoicePullSubscription subscription : subscriptions) {
            Assert.assertNotNull(subscription.id);
        }
    }

    @Test
    public void testCreatePaymentAndQrCode() throws Exception {
        Settings.user = utils.User.defaultProject();
        List<InvoicePullSubscription> subscriptions = InvoicePullSubscription.create(Arrays.asList(Example("paymentAndOrQrcode")));

        for (InvoicePullSubscription subscription : subscriptions) {
            Assert.assertNotNull(subscription.id);
        }
    }

    @Test
    public void testQueryAndGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);
        Generator<InvoicePullSubscription> subscriptions = InvoicePullSubscription.query(params);

        for (InvoicePullSubscription subscription : subscriptions) {
            subscription = InvoicePullSubscription.get(subscription.id);
            Assert.assertNotNull(subscription.id);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            InvoicePullSubscription.Page page = InvoicePullSubscription.page(params);
            for (InvoicePullSubscription subscription: page.subscriptions) {
                if (ids.contains(subscription.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(subscription.id);
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
        Generator<InvoicePullSubscription.Log> logs = InvoicePullSubscription.Log.query(params);

        int i = 0;
        for (InvoicePullSubscription.Log log : logs) {
            i += 1;
            log = InvoicePullSubscription.Log.get(log.id);
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
            InvoicePullSubscription.Log.Page page = InvoicePullSubscription.Log.page(params);
            for (InvoicePullSubscription.Log log: page.logs) {
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
    static InvoicePullSubscription Example(String typeValue) throws Exception
    {
        InvoicePullSubscription example = null;
        HashMap<String, Object> data = new HashMap<>();

        String[] tags = new String[]{};
        String start = LocalDate.now().plusDays(5).toString();
        String end = LocalDate.now().plusDays(35).toString();
        String interval = "month";
        String pullMode = "manual";
        Long pullRetryLimit = 3L;
        Long amount = 0L;
        Long amountMinLimit = 5000L;
        String displayDescription = "Dragon Travel Fare";
        String externalId = UUID.randomUUID().toString();
        String referenceCode = "contract-12345";
        String name = "John Snow";
        String taxId = "012.345.678-90";
        String type = typeValue;
        if (type.equals("push")) {
            data.put("accountNumber", "9123900000");
            data.put("bankCode", "05097757");
            data.put("branchCode", "1126");
            data.put("taxId", "20.018.183/0001-80");
            example = new InvoicePullSubscription(
                start,
                interval,
                pullMode,
                pullRetryLimit,
                type,
                amount,
                amountMinLimit,
                displayDescription,
                null,
                externalId,
                referenceCode,
                end,
                data,
                name,
                taxId,
                tags,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            );
        }
        if (type.equals("qrcode")) {
            example = new InvoicePullSubscription(
                start,
                interval,
                pullMode,
                pullRetryLimit,
                type,
                amount,
                amountMinLimit,
                displayDescription,
                null,
                externalId,
                referenceCode,
                end,
                null,
                name,
                taxId,
                tags,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            );
        }
        if (type.equals("qrcodeAndPayment")) {
            data.put("amount", 400000L);
            example = new InvoicePullSubscription(
                start,
                interval,
                pullMode,
                pullRetryLimit,
                typeValue,
                amount,
                amountMinLimit,
                displayDescription,
                null,
                externalId,
                referenceCode,
                end,
                data,
                name,
                taxId,
                tags,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            );
        }
        if (type.equals("paymentAndOrQrcode")) {
            data.put("amount", 400000L);
            data.put("due", "2026-06-26T17:59:26.000000+00:00");
            data.put("fine", 2.5);
            data.put("interest", 1.3);
            example = new InvoicePullSubscription(
                start,
                interval,
                pullMode,
                pullRetryLimit,
                type,
                amount,
                amountMinLimit,
                displayDescription,
                null,
                externalId,
                referenceCode,
                end,
                data,
                name,
                taxId,
                tags,
                null,
                null,
                null,
                null,
                null,
                null,
                null
            );
        }
        return example;
    }
}
