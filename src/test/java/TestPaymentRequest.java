import com.starkbank.PaymentRequest;
import com.starkbank.Settings;
import com.starkbank.Transaction;
import com.starkbank.error.InputErrors;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import org.junit.Assert;
import org.junit.AssumptionViolatedException;
import org.junit.Test;

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
            Assert.assertNotNull(request.id);
            System.out.println(request);
        }
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
            System.out.println(request);
            Assert.assertNotNull(request.id);
        }
        System.out.println(i);
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
                System.out.println(request);
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

    static PaymentRequest example() throws Exception{
        Resource payment = TestPaymentRequest.createPayment();
        String due = null;
        if(!(payment instanceof Transaction)) {
            int days = ThreadLocalRandom.current().nextInt(1, 7);
            LocalDateTime date = LocalDateTime.now().plusDays(days);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            due = dateFormat.format(date);
        }
        return new PaymentRequest(null, System.getenv("SANDBOX_CENTER_ID"), payment, null, due, null, null, null, null, null, null);
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
