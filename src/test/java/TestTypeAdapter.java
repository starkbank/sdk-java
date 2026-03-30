import java.util.HashMap;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;

import com.starkbank.BoletoPayment;
import com.starkbank.Event;
import com.starkbank.Invoice;
import com.starkbank.PaymentRequest;
import com.starkbank.Settings;
import com.starkbank.utils.Generator;
import com.starkcore.error.InvalidSignatureError;

public class TestTypeAdapter {

    @Before
    public void setup() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 1);

        Generator<Invoice> invoices = Invoice.query(params);
        for (Invoice invoice : invoices) {
            System.out.println(invoice.id);
        }
    }

    @Test
    public void testParseVerifiedAccountEvent() throws Exception {
        String content = "{\"event\": {\"created\": \"2025-09-19T14:49:46.144056+00:00\", \"id\": \"4737010520555520\", \"log\": {\"account\": {\"bankCode\": \"341\", \"bankName\": \"\", \"branchCode\": \"2201\", \"created\": \"2025-09-19T14:49:44.669078+00:00\", \"id\": \"6628670762385408\", \"keyId\": \"\", \"name\": \"Daenerys Targaryen Stormborn\", \"number\": \"76543-8\", \"status\": \"processing\", \"tags\": [\"verified-account-test\"], \"taxId\": \"911.544.440-66\", \"type\": \"checking\", \"updated\": \"2025-09-19T14:49:45.192888+00:00\"}, \"created\": \"2025-09-19T14:49:45.192903+00:00\", \"errors\": [], \"id\": \"4753514616061952\", \"type\": \"processing\"}, \"subscription\": \"verified-account\", \"workspaceId\": \"6341320293482496\"}}";
        String valid_signature = "	MEQCIEHTFZYeVjBDxFtdlHybIFb9E0ElEQFodfa1LxrZWjrDAiBl53PLGG9RXxUZbKEtOzmgoXFe1FQyODMZ/o6H3vMZRA==";
        Event event = Event.parse(content, valid_signature);
        Assert.assertEquals(Event.VerifiedAccountEvent.class, event.getClass());
    }

    @Test
    public void testPaymentRequestEventParse() throws Exception {
        Settings.user = utils.User.defaultProject();

        String content = "{\"event\": {\"created\": \"2025-12-02T19:26:10.088671+00:00\", \"id\": \"4954566443401216\", \"log\": {\"created\": \"2025-12-02T19:26:07.449707+00:00\", \"errors\": [], \"id\": \"5602863480832000\", \"request\": {\"actions\": [{\"action\": \"requested\", \"id\": \"6200062581407744\", \"type\": \"project\"}, {\"action\": \"required\", \"id\": \"6263563236671488\", \"type\": \"member\"}], \"amount\": 4000, \"centerId\": \"6315803154579456\", \"created\": \"2025-12-02T19:26:07.396749+00:00\", \"description\": \"Payment for killing white walkers\", \"due\": \"2025-12-07T03:00:00+00:00\", \"id\": \"6207755400511488\", \"payment\": {\"amount\": 4000, \"description\": \"Payment for killing white walkers\", \"line\": \"34191.09107 83495.507309 71444.640008 4 12970000004000\", \"name\": \"Iron Bank S.A.\", \"tags\": [\"little girl\", \"no one\"], \"taxId\": \"38.435.677/0001-25\"}, \"status\": \"pending\", \"tags\": [], \"type\": \"boleto-payment\", \"updated\": \"2025-12-02T19:26:07.449725+00:00\"}, \"type\": \"created\", \"updated\": \"2025-12-02T19:26:07.449729+00:00\", \"user\": {\"id\": \"6200062581407744\", \"type\": \"project\"}}, \"subscription\": \"payment-request\", \"workspaceId\": \"6341320293482496\"}}";
        String valid_signature = "MEYCIQCeKDA2cQgiIv4oK9u4Hi3zbryBwZfc5gF81G2rsMlU4wIhANZXNnp1umRiERsIVgOQu0Z8thnJQKRBd22p4ldzuEV5";

        Event.PaymentRequestEvent event = null;

        try {
            event = (Event.PaymentRequestEvent) Event.parse(content, valid_signature);
        } catch (InvalidSignatureError e) {
            Assert.fail(e.getMessage());
        }

        Assert.assertEquals(event.getClass(), Event.PaymentRequestEvent.class);
        Assert.assertEquals(event.log.getClass(), PaymentRequest.Log.class);
        Assert.assertEquals(event.log.request.getClass(), PaymentRequest.class);
        Assert.assertEquals(event.log.request.payment.getClass(), BoletoPayment.class);
    }
}
