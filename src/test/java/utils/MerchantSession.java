package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MerchantSession {

    public static com.starkbank.MerchantSession exampleMerchantSession(String challengeMode) throws Exception {
        Map<String, Object> data = new HashMap<>();
        List<String> allowedFundingTypes = new ArrayList<>();
        allowedFundingTypes.add("debit");
        allowedFundingTypes.add("credit");
        data.put("allowedFundingTypes", allowedFundingTypes);

        List<com.starkbank.MerchantSession.AllowedInstallment> allowedInstallments = new ArrayList<>();

        com.starkbank.MerchantSession.AllowedInstallment allowedInstallment1 = new com.starkbank.MerchantSession.AllowedInstallment(5000L, 1);
        com.starkbank.MerchantSession.AllowedInstallment allowedInstallments2 = new com.starkbank.MerchantSession.AllowedInstallment(5500L, 2);

        allowedInstallments.add(allowedInstallment1);
        allowedInstallments.add(allowedInstallments2);


        data.put("allowedInstallments", allowedInstallments);
        data.put("expiration", 3600);
        data.put("challengeMode", challengeMode);

        data.put("tags", new String[]{"Stark", "Suit"});

        return new com.starkbank.MerchantSession(data);
    }

    public static com.starkbank.MerchantSession.Purchase examplePurchaseChallengeModeEnable() throws Exception {
        Map<String, Object> purchaseData = new HashMap<>();
        purchaseData.put("amount", 5000L);
        purchaseData.put("installmentCount", 1);
        purchaseData.put("cardExpiration", "2035-01");
        purchaseData.put("cardNumber", "5448280000000007");
        purchaseData.put("cardSecurityCode", "123");
        purchaseData.put("holderName", "Margaery Tyrell");
        purchaseData.put("holderEmail", "margaery.tyrell@starkbank.com");
        purchaseData.put("holderPhone", "11998663456");
        purchaseData.put("fundingType", "credit");
        purchaseData.put("billingCountryCode", "BRA");
        purchaseData.put("billingCity", "Sao Paulo");
        purchaseData.put("billingStateCode", "SP");
        purchaseData.put("billingStreetLine1", "Rua do Jardim de cima, 123");
        purchaseData.put("billingStreetLine2", "1 andar");
        purchaseData.put("billingZipCode", "11111-111");

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("userAgent", "userAgent");
        metadata.put("userIp", "255.255.255.255");
        metadata.put("language", "pt-BR");
        metadata.put("timezoneOffset", 3);
        metadata.put("extraData", "extraData");

        purchaseData.put("metadata", metadata);
        purchaseData.put("tags", new String[]{"Stark", "Suit"});

        return new com.starkbank.MerchantSession.Purchase(purchaseData);
    }

    public static com.starkbank.MerchantSession.Purchase examplePurchaseChallengeModeDisable() throws Exception {
        Map<String, Object> purchaseData = new HashMap<>();
        purchaseData.put("amount", 5000L);
        purchaseData.put("installmentCount", 1);
        purchaseData.put("cardExpiration", "2035-01");
        purchaseData.put("cardNumber", "36490101441625");
        purchaseData.put("cardSecurityCode", "123");
        purchaseData.put("fundingType", "credit");
        purchaseData.put("holderName", "Margaery Tyrell");


        return new com.starkbank.MerchantSession.Purchase(purchaseData);
    }

}
