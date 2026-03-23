import static org.junit.Assert.*;

import com.starkbank.PaymentRequest;
import com.starkbank.Settings;
import com.starkbank.Transaction;
import com.starkbank.error.InputErrors;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.Event;
import com.starkcore.error.InvalidSignatureError;
import com.starkbank.BoletoPayment;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Test;
import com.google.gson.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class TestPaymentRequest {

    @Test
    public void testCreate() throws Exception{
        Settings.user = utils.User.defaultProject();
        List<PaymentRequest> requests = new ArrayList<>();
        for(int i=0; i<10; i++) {
            requests.add(TestPaymentRequest.example());
        }

        try{
            requests = PaymentRequest.create(requests);
        } catch (InputErrors e) {
            if(e.errors.get(0).code.equals("invalidDictKey"))
                throw new AssumptionViolatedException("Inconclusive");
            throw e;
        }

        for(PaymentRequest request : requests) {
            assertNotNull(request.id);
        }
    }

    @Test
    public void testDeserializeCreateResponsePayload() {
        String payload = "{\n" +
            " \"message\" : \"Requisição(ões) de pagamento(s) criada(s) com sucesso\",\n" +
            " \"requests\" : [ {\n" +
            "   \"actions\" : [ {\n" +
            "     \"action\" : \"requested\",\n" +
            "     \"email\" : \"\",\n" +
            "     \"id\" : \"12345454444\",\n" +
            "     \"name\" : \"Proj\",\n" +
            "     \"pictureUrl\" : \"\",\n" +
            "     \"status\" : \"active\",\n" +
            "     \"type\" : \"project\"\n" +
            "   }, {\n" +
            "     \"action\" : \"required\",\n" +
            "     \"email\" : \"fgomes@helpnei.com\",\n" +
            "     \"id\" : \"12345454444\",\n" +
            "     \"name\" : \"Fernando Gomes\",\n" +
            "     \"pictureUrl\" : \"\",\n" +
            "     \"status\" : \"active\",\n" +
            "     \"type\" : \"member\"\n" +
            "   }, {\n" +
            "     \"action\" : \"required\",\n" +
            "     \"email\" : \"vpaz@helpnei.com\",\n" +
            "     \"id\" : \"12345454444\",\n" +
            "     \"name\" : \"Vinicius Paz\",\n" +
            "     \"pictureUrl\" : \"\",\n" +
            "     \"status\" : \"active\",\n" +
            "     \"type\" : \"member\"\n" +
            "   } ],\n" +
            "   \"amount\" : 12334,\n" +
            "   \"attachments\" : [ ],\n" +
            "   \"centerId\" : \"12345671234567\",\n" +
            "   \"created\" : \"2025-11-23T12:32:36.965218+00:00\",\n" +
            "   \"description\" : \"fERNANDO teste manual (011.222.333-40)\",\n" +
            "   \"due\" : \"2025-11-23T12:32:36.952898+00:00\",\n" +
            "   \"id\" : \"12345454444\",\n" +
            "   \"payment\" : {\n" +
            "     \"accountNumber\" : \"*IEQ1ATFCgZwH8JFsfAdeurjYW9AR3JpFIwm4v7iTVnpYkY140znoQZt/1u6SyhCtQ7FlmKwgyVsAsVzR7EhEwdtLhSq4NDPLsj3NxQTFakw=\",\n" +
            "     \"accountType\" : \"payment\",\n" +
            "     \"amount\" : 12334,\n" +
            "     \"bankCode\" : \"123456\",\n" +
            "     \"bankName\" : \"NU PAGAMENTOS - IP\",\n" +
            "     \"branchCode\" : \"*7at/HwNgLRQDwejLYsmwPHRe4qlN/WToRlf/3aNi22Q=\",\n" +
            "     \"description\" : \"Cash-out request ID: e53a40d3-76f2-4da2-a217-b2829faa1c05\",\n" +
            "     \"externalId\" : \"e53a40d3-76f2-4da2-a217-b2829faa1c05\",\n" +
            "     \"name\" : \"fERNANDO teste manual\",\n" +
            "     \"tags\" : [ \"requestId:e53a40d3-76f2-4da2-a217-b2829faa1c05\" ],\n" +
            "     \"taxId\" : \"011.222.333-40\"\n" +
            "   },\n" +
            "   \"status\" : \"pending\",\n" +
            "   \"tags\" : [ \"requestid:e53a40d3-76f2-4da2-a217-b2829faa1c05\" ],\n" +
            "   \"type\" : \"transfer\",\n" +
            "   \"updated\" : \"2025-11-23T12:32:36.965225+00:00\"\n" +
            " } ]\n" +
            "}";

        JsonObject obj = JsonParser.parseString(payload).getAsJsonObject();
        JsonArray arr = obj.getAsJsonArray("requests");
        JsonElement first = arr.get(0);

        // This must not throw (previously it would fail trying to instantiate abstract Resource)
        PaymentRequest request = new Gson().fromJson(first, PaymentRequest.class);

        assertNotNull(request);
        assertEquals("transfer", request.type);
        assertNotNull(request.payment);
        assertTrue(request.payment instanceof com.starkbank.Transfer);
        assertEquals("pending", request.status);
        assertNotNull(request.actions);
        assertEquals(3, request.actions.size());
    }

    @Test
    public void testQuery() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("centerId", System.getenv("SANDBOX_CENTER_ID"));
        params.put("limit", 3);
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        Generator<PaymentRequest> requests = PaymentRequest.query(params);

        int i = 0;
        for (PaymentRequest request : requests) {
            i += 1;
            assertNotNull(request.id);
        }
    }

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("centerId", System.getenv("SANDBOX_CENTER_ID"));
        params.put("after", "2019-04-01");
        params.put("before", "2030-04-30");
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            PaymentRequest.Page page = PaymentRequest.page(params);
            for (PaymentRequest request: page.requests) {
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

        if (ids.size() != 4) {
            throw new Exception("ids.size() != 4");
        }
    }

    @Test
    public void testPaymentRequestEventParse() throws Exception{
        Settings.user = utils.User.defaultProject();

        String content = "{\"event\": {\"created\": \"2025-12-02T19:26:10.088671+00:00\", \"id\": \"4954566443401216\", \"log\": {\"created\": \"2025-12-02T19:26:07.449707+00:00\", \"errors\": [], \"id\": \"5602863480832000\", \"request\": {\"actions\": [{\"action\": \"requested\", \"id\": \"6200062581407744\", \"type\": \"project\"}, {\"action\": \"required\", \"id\": \"6263563236671488\", \"type\": \"member\"}], \"amount\": 4000, \"centerId\": \"6315803154579456\", \"created\": \"2025-12-02T19:26:07.396749+00:00\", \"description\": \"Payment for killing white walkers\", \"due\": \"2025-12-07T03:00:00+00:00\", \"id\": \"6207755400511488\", \"payment\": {\"amount\": 4000, \"description\": \"Payment for killing white walkers\", \"line\": \"34191.09107 83495.507309 71444.640008 4 12970000004000\", \"name\": \"Iron Bank S.A.\", \"tags\": [\"little girl\", \"no one\"], \"taxId\": \"38.435.677/0001-25\"}, \"status\": \"pending\", \"tags\": [], \"type\": \"boleto-payment\", \"updated\": \"2025-12-02T19:26:07.449725+00:00\"}, \"type\": \"created\", \"updated\": \"2025-12-02T19:26:07.449729+00:00\", \"user\": {\"id\": \"6200062581407744\", \"type\": \"project\"}}, \"subscription\": \"payment-request\", \"workspaceId\": \"6341320293482496\"}}";
        String valid_signature = "MEYCIQCeKDA2cQgiIv4oK9u4Hi3zbryBwZfc5gF81G2rsMlU4wIhANZXNnp1umRiERsIVgOQu0Z8thnJQKRBd22p4ldzuEV5";

        Event.PaymentRequestEvent event = null;

        try {
            event = (Event.PaymentRequestEvent) Event.parse(content, valid_signature);
        } catch (InvalidSignatureError e) {
            fail(e.getMessage());
        }

        assertEquals(event.getClass(), Event.PaymentRequestEvent.class);
        assertEquals(event.log.getClass(), PaymentRequest.Log.class);
        assertEquals(event.log.request.getClass(), PaymentRequest.class);
        assertEquals(event.log.request.payment.getClass(), BoletoPayment.class);
    }

    static PaymentRequest example() throws Exception{
        Resource payment = TestPaymentRequest.createPayment();
        String due = null;
        if(!(payment instanceof Transaction)) {
            int days = ThreadLocalRandom.current().nextInt(1, 7);
            LocalDateTime date = LocalDateTime.now().plusDays(days);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            due = dateFormat.format(date);
        }
        return new PaymentRequest(null, System.getenv("SANDBOX_CENTER_ID"), payment, null, due, null, null, null, null, null, null, null);
    }

    static Resource createPayment() throws Exception{
        int random = ThreadLocalRandom.current().nextInt(0, 5);
        switch (random) {
            case 0:
                return TestTransfer.example(false);
            case 1:
                return TestTransaction.example();
            case 2:
                return TestBoletoPayment.example(false);
            case 3:
                return TestUtilityPayment.example(false);
            case 4:
                return TestBrcodePayment.example(false);
            default:
                throw new Exception("Bad random number");
        }
    }
}
