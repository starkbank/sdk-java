package utils;

import java.util.HashMap;

public class MerchantPurchase {

public static com.starkbank.MerchantPurchase exampleMerchantPurchase(String cardId) throws Exception {
    HashMap<String, Object> data = new HashMap<>();

    data.put("amount", 1000L);
    data.put("installmentCount", 1);
    data.put("cardId", cardId);
    data.put("fundingType", "credit");
    data.put("billingCity", "Sao Paulo");
    data.put("billingCountryCode", "BRA");
    data.put("billingStateCode", "SP");
    data.put("billingStreetLine1", "Rua Casterly Rock, 2000");
    data.put("billingStreetLine2", "");
    data.put("billingZipCode", "01450-000");
    data.put("holderEmail", "tywin.lannister@gmail.com");
    data.put("holderPhone", "11999999999");
    HashMap<String, Object> metadata = new HashMap<>();
    metadata.put("userAgent", "Mozilla");
    metadata.put("userIp", "111.111.111.111");
    metadata.put("language", "pt-BR");
    data.put("metadata", metadata);

    return new com.starkbank.MerchantPurchase(data);
}
}
