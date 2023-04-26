import com.starkbank.CorporatePurchase;
import org.junit.Test;
import org.junit.Assert;

import com.starkbank.Settings;
import com.starkbank.CorporatePurchase;
import com.starkbank.utils.Generator;
import com.starkbank.error.InvalidSignatureError;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public class TestCorporatePurchase {

    @Test
    public void testPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporatePurchase.Page page = CorporatePurchase.page(params);
            for (CorporatePurchase purchase: page.corporatePurchases) {
                System.out.println(purchase);
                if (ids.contains(purchase.id)) {
                    throw new Exception("repeated id");
                }
                ids.add(purchase.id);
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
    public void testQueryGet() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporatePurchase> purchases = CorporatePurchase.query(params);

        for (CorporatePurchase purchase : purchases) {
            CorporatePurchase purchaseExpected = CorporatePurchase.get(purchase.id);
            Assert.assertNotNull(purchase.id, purchaseExpected.id);
            System.out.println(purchase);
        }
    }

    @Test
    public void testLogQueryAndGet() throws Exception{
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 3);
        Generator<CorporatePurchase.Log> logs = CorporatePurchase.Log.query(params);

        for (CorporatePurchase.Log log : logs) {
            log = CorporatePurchase.Log.get(log.id);
            System.out.println(log);
        }
    }

    @Test
    public void testLogPage() throws Exception {
        Settings.user = utils.User.defaultProject();

        HashMap<String, Object> params = new HashMap<>();
        params.put("limit", 2);
        params.put("cursor", null);

        List<String> ids = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            CorporatePurchase.Log.Page page = CorporatePurchase.Log.page(params);
            for (CorporatePurchase.Log log: page.logs) {
                System.out.println(log);
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

        if (ids.size() != 4) {
            throw new Exception("ids.size() != 4");
        }
    }

    @Test
    public void testResponseResponse() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<String> tags = new ArrayList<>();
        tags.add("test");
        tags.add("response");
        tags.add("corporate-purchase");

        HashMap<String, Object> datas = new HashMap<>();
        datas.put("status", "approved");
        datas.put("amount", 100000);
        datas.put("reason", "other");
        datas.put("tags", tags);

        HashMap<String, Object> data = new HashMap<>();
        data.put("authorization", datas);

        String response = CorporatePurchase.response(data);
        Assert.assertNotNull(response);
        System.out.println(response);
    }

    public void testResponseDenied() throws Exception {
        Settings.user = utils.User.defaultProject();

        List<String> tags = new ArrayList<>();
        tags.add("test");
        tags.add("response");
        tags.add("corporate-purchase");

        HashMap<String, Object> datas = new HashMap<>();
        datas.put("status", "denied");
        datas.put("reason", "other");
        datas.put("tags", tags);

        HashMap<String, Object> data = new HashMap<>();
        data.put("authorization", datas);

        String response = CorporatePurchase.response(data);
        Assert.assertNotNull(response);
        System.out.println(response);
    }

    @Test
    public void testParseRight() throws Exception{
        String content = "{\"acquirerId\": \"236090\", \"amount\": 100, \"cardId\": \"5671893688385536\", \"cardTags\": [], \"endToEndId\": \"2fa7ef9f-b889-4bae-ac02-16749c04a3b6\", \"holderId\": \"5917814565109760\", \"holderTags\": [], \"isPartialAllowed\": false, \"issuerAmount\": 100, \"issuerCurrencyCode\": \"BRL\", \"merchantAmount\": 100, \"merchantCategoryCode\": \"bookStores\", \"merchantCountryCode\": \"BRA\", \"merchantCurrencyCode\": \"BRL\", \"merchantFee\": 0, \"merchantId\": \"204933612653639\", \"merchantName\": \"COMPANY 123\", \"methodCode\": \"token\", \"purpose\": \"purchase\", \"score\": null, \"tax\": 0, \"walletId\": \"\"}";
        String validSignature = "MEUCIBxymWEpit50lDqFKFHYOgyyqvE5kiHERi0ZM6cJpcvmAiEA2wwIkxcsuexh9BjcyAbZxprpRUyjcZJ2vBAjdd7o28Q=";
        Settings.user = utils.User.defaultProject();

        CorporatePurchase request = CorporatePurchase.parse(content, validSignature);
        System.out.println(request);
    }

    @Test
    public void testParseMalformedSignature() throws Exception{
        String content = "{\"acquirerId\": \"236090\", \"amount\": 100, \"cardId\": \"5671893688385536\", \"cardTags\": [], \"endToEndId\": \"2fa7ef9f-b889-4bae-ac02-16749c04a3b6\", \"holderId\": \"5917814565109760\", \"holderTags\": [], \"isPartialAllowed\": false, \"issuerAmount\": 100, \"issuerCurrencyCode\": \"BRL\", \"merchantAmount\": 100, \"merchantCategoryCode\": \"bookStores\", \"merchantCountryCode\": \"BRA\", \"merchantCurrencyCode\": \"BRL\", \"merchantFee\": 0, \"merchantId\": \"204933612653639\", \"merchantName\": \"COMPANY 123\", \"methodCode\": \"token\", \"purpose\": \"purchase\", \"score\": null, \"tax\": 0, \"walletId\": \"\"}";
        String malformedSignature = "something is definitely wrong";
        Settings.user = utils.User.defaultProject();

        try{
            CorporatePurchase.parse(content, malformedSignature);
            throw new Error("Signature incorrectly validated");
        } catch (InvalidSignatureError e){
            System.out.println("Signature correctly rejected");
        }
    }

    @Test
    public void testParseInvalidSignature() throws Exception{
        String content = "{\"acquirerId\": \"236090\", \"amount\": 100, \"cardId\": \"5671893688385536\", \"cardTags\": [], \"endToEndId\": \"2fa7ef9f-b889-4bae-ac02-16749c04a3b6\", \"holderId\": \"5917814565109760\", \"holderTags\": [], \"isPartialAllowed\": false, \"issuerAmount\": 100, \"issuerCurrencyCode\": \"BRL\", \"merchantAmount\": 100, \"merchantCategoryCode\": \"bookStores\", \"merchantCountryCode\": \"BRA\", \"merchantCurrencyCode\": \"BRL\", \"merchantFee\": 0, \"merchantId\": \"204933612653639\", \"merchantName\": \"COMPANY 123\", \"methodCode\": \"token\", \"purpose\": \"purchase\", \"score\": null, \"tax\": 0, \"walletId\": \"\"}";
        String invalidSignature = "MEUCIQDOpo1j+V40pNZK2URL2786UQK/8mDXon9ayEd8U0/l7AIgYXtIZJBTs8zCRR3vmted6Ehz/qfw1GRut/eYyvf1yOk=";
        Settings.user = utils.User.defaultProject();

        try{
            CorporatePurchase.parse(content, invalidSignature);
            throw new Error("Signature incorrectly validated");
        } catch (InvalidSignatureError e){
            System.out.println("Signature correctly rejected");
        }
    }
}
