import com.starkbank.*;
import com.starkcore.error.InvalidSignatureError;
import com.starkbank.utils.Generator;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class TestEvent {

    private void assertFalseForInconclusive(boolean condition) {
        if(!condition)
            throw new AssumptionViolatedException("Inconclusive");
    }

    @Test
    public void testEventQuery() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 5);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<Event> events = Event.query(params);

        int i = 0;
        for (Event event : events) {
            i += 1;
            Assert.assertNotNull(event.id);
            Assert.assertNotNull(event.workspaceId);

            HashMap<String, Object> attemptParams = new HashMap<>();
            attemptParams.put("limit", 1);
            attemptParams.put("eventIds", event.id);
            Generator<Event.Attempt> attempts = Event.Attempt.query(attemptParams);
            for (Event.Attempt attempt : attempts) {
                Event.Attempt a = Event.Attempt.get(attempt.id);
                Assert.assertNotNull(a);
            }
        }
        assertFalseForInconclusive(i > 0);
    }

    @Test
    public void testEventQueryGetAndUpdate() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 100);
        params.put("isDelivered", false);
        Generator<Event> events = Event.query(params);
        HashMap<String, Object> data = new HashMap<>();
        data.put("isDelivered", true);

        int index = new Random().nextInt(100);
        int i = 0;
        for (Event event : events) {
            i ++;
            if (i != index) {
                continue;
            }
            Assert.assertFalse(event.isDelivered);
            event = Event.get(event.id);
            event = Event.update(event.id, data);
            Assert.assertTrue(event.isDelivered);
        }
        assertFalseForInconclusive(i > 0);
    }

    @Test
    public void testEventQueryAndDelete() throws Exception{
        Settings.user = utils.User.defaultProject();
        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 100);
        int i = 0;
        int index = new Random().nextInt(100);
        for (Event event : Event.query(params)) {
            i ++;
            if (i != index) {
                continue;
            }
            event = Event.delete(event.id);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Event.Page page = Event.page(params);
            for (Event event: page.events) {
                if (ids.contains(event.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(event.id);
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

    @Test
    public void testEventParse() throws Exception{
        String content = "{\"event\": {\"created\": \"2025-09-19T14:49:46.144056+00:00\", \"id\": \"4737010520555520\", \"log\": {\"account\": {\"bankCode\": \"341\", \"bankName\": \"\", \"branchCode\": \"2201\", \"created\": \"2025-09-19T14:49:44.669078+00:00\", \"id\": \"6628670762385408\", \"keyId\": \"\", \"name\": \"Daenerys Targaryen Stormborn\", \"number\": \"76543-8\", \"status\": \"processing\", \"tags\": [\"verified-account-test\"], \"taxId\": \"911.544.440-66\", \"type\": \"checking\", \"updated\": \"2025-09-19T14:49:45.192888+00:00\"}, \"created\": \"2025-09-19T14:49:45.192903+00:00\", \"errors\": [], \"id\": \"4753514616061952\", \"type\": \"processing\"}, \"subscription\": \"verified-account\", \"workspaceId\": \"6341320293482496\"}}";
        String valid_signature = "	MEQCIEHTFZYeVjBDxFtdlHybIFb9E0ElEQFodfa1LxrZWjrDAiBl53PLGG9RXxUZbKEtOzmgoXFe1FQyODMZ/o6H3vMZRA==";

        Settings.user = utils.User.defaultProject();
        Event event = Event.parse(content, valid_signature);

        switch (event.subscription) {
            case "transfer": {
                Transfer.Log log = ((Event.TransferEvent) event).log;
                Assert.assertNotNull(log.transfer.id);
                break;
            }
            case "boleto": {
                Boleto.Log log = ((Event.BoletoEvent) event).log;
                Assert.assertNotNull(log.boleto.id);
                break;
            }
            case "boleto-payment": {
                BoletoPayment.Log log = ((Event.BoletoPaymentEvent) event).log;
                Assert.assertNotNull(log.payment.id);
                break;
            }
            case "utility-payment": {
                UtilityPayment.Log log = ((Event.UtilityPaymentEvent) event).log;
                Assert.assertNotNull(log.payment.id);
                break;
            }
            case "boleto-holmes": {
                BoletoHolmes.Log log = ((Event.BoletoHolmesEvent) event).log;
                Assert.assertNotNull(log.holmes.id);
                break;
            }
            case "brcode-payment": {
                BrcodePayment.Log log = ((Event.BrcodePaymentEvent) event).log;
                Assert.assertNotNull(log.payment.id);
                break;
            }
            case "deposit": {
                Deposit.Log log = ((Event.DepositEvent) event).log;
                Assert.assertNotNull(log.deposit.id);
                break;
            }
            case "invoice": {
                Invoice.Log log = ((Event.InvoiceEvent) event).log;
                Assert.assertNotNull(log.invoice.id);
                break;
            }
            case "verified-account": {
                VerifiedAccount.Log log = ((Event.VerifiedAccountEvent) event).log;
                Assert.assertNotNull(log.account.id);
                break;
            }
        }
    }

    @Test
    public void testEventParseInvalidSignature() throws Exception{
        String content = "{\"event\": {\"log\": {\"transfer\": {\"status\": \"processing\", \"updated\": \"2020-04-03T13:20:33.485644+00:00\", \"fee\": 160, \"name\": \"Lawrence James\", \"accountNumber\": \"10000-0\", \"id\": \"5107489032896512\", \"tags\": [], \"taxId\": \"91.642.017/0001-06\", \"created\": \"2020-04-03T13:20:32.530367+00:00\", \"amount\": 2, \"transactionIds\": [\"6547649079541760\"], \"bankCode\": \"01\", \"branchCode\": \"0001\"}, \"errors\": [], \"type\": \"sending\", \"id\": \"5648419829841920\", \"created\": \"2020-04-03T13:20:33.164373+00:00\"}, \"subscription\": \"transfer\", \"id\": \"6234355449987072\", \"created\": \"2020-04-03T13:20:40.784479+00:00\"}}";
        String invalid_signature = "MEUCIQDOpo1j+V40DNZK2URL2786UQK/8mDXon9ayEd8U0/l7AIgYXtIZJBTs8zCRR3vmted6Ehz/qfw1GRut/eYyvf1yOk=";

        Settings.user = utils.User.defaultProject();
        try{
            Event.parse(content, invalid_signature);
            Assert.fail("Signature incorrectly validated");
        } catch (InvalidSignatureError e){
            System.out.println("Signature correctly rejected");
        }
    }

    @Test
    public void testEventParseMalformedSignature() throws Exception{
        String content = "{\"event\": {\"log\": {\"transfer\": {\"status\": \"processing\", \"updated\": \"2020-04-03T13:20:33.485644+00:00\", \"fee\": 160, \"name\": \"Lawrence James\", \"accountNumber\": \"10000-0\", \"id\": \"5107489032896512\", \"tags\": [], \"taxId\": \"91.642.017/0001-06\", \"created\": \"2020-04-03T13:20:32.530367+00:00\", \"amount\": 2, \"transactionIds\": [\"6547649079541760\"], \"bankCode\": \"01\", \"branchCode\": \"0001\"}, \"errors\": [], \"type\": \"sending\", \"id\": \"5648419829841920\", \"created\": \"2020-04-03T13:20:33.164373+00:00\"}, \"subscription\": \"transfer\", \"id\": \"6234355449987072\", \"created\": \"2020-04-03T13:20:40.784479+00:00\"}}";
        String malformed_signature = "something is definitely wrong";

        Settings.user = utils.User.defaultProject();
        try{
            Event.parse(content, malformed_signature);
            Assert.fail("Signature incorrectly validated");
        } catch (InvalidSignatureError e){
            System.out.println("Signature correctly rejected");
        }
    }
}
