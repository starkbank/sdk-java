import com.starkbank.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TestPaymentPreview {

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate() throws Exception{
        Settings.user = utils.User.defaultProject();

        BrcodePayment brcodePayment = TestBrcodePayment.example(false);
        BoletoPayment boletoPayment = TestBoletoPayment.example(false);
        UtilityPayment utilityPayment = TestUtilityPayment.example(false);
        TaxPayment taxPayment = TestTaxPayment.example(false);

        List<PaymentPreview> previews = new ArrayList<>();
        previews.add(new PaymentPreview(new HashMap<String, Object>(){{
            put("id", brcodePayment.brcode);
            put("scheduled", brcodePayment.scheduled);
        }}));
        previews.add(new PaymentPreview(new HashMap<String, Object>(){{
            put("id", boletoPayment.line);
            put("scheduled", boletoPayment.scheduled);
        }}));
        previews.add(new PaymentPreview(new HashMap<String, Object>(){{
            put("id", taxPayment.barCode);
            put("scheduled", taxPayment.scheduled);
        }}));
        previews.add(new PaymentPreview(new HashMap<String, Object>(){{
            put("id", utilityPayment.barCode);
            put("scheduled", utilityPayment.scheduled);
        }}));

        previews = (List<PaymentPreview>) PaymentPreview.create(previews);

        for(PaymentPreview preview : previews) {
            Assert.assertNotNull(preview.id);
            System.out.println(preview);
        }
    }
}
