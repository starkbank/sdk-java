package com.starkbank;

import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentPreview extends Resource {
    /**
     * PaymentPreview object
     * <p>
     * A PaymentPreview is used to get information from a payment code you received to check the information before paying it.
     * This resource can be used to preview BR Codes and codes of boleto, tax and utility payments
     * <p>
     * Parameters:
     * id [string]: Main identification of the payment. This should be the BR Code for Pix payments and lines or bar codes for payment slips. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A"
     * scheduled [string]: intended payment date. Right now, this parameter only has effect on BrcodePreviews. ex: datetime.date(2020, 3, 10)
     * type [string]: Payment type. ex: "brcode-payment", "boleto-payment", "utility-payment" or "tax-payment"
     * payment [BrcodePreview, BoletoPreview, UtilityPreview or TaxPreview]: Information preview of the informed payment.
     */
    static ClassData data = new ClassData(PaymentPreview.class, "PaymentPreview");

    public String scheduled;
    public String type;
    public Object payment;

    /**
     * PaymentPreview object
     * <p>
     * A PaymentPreview is used to get information from a payment code you received to check the information before the payment.
     * <p>
     * Parameters:
     * @param id [string]: Main identification of the payment. This should be the BR Code for Pix payments and lines or bar codes for payment slips. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A"
     * @param scheduled [string]: intended payment date. Right now, this parameter only has effect on BrcodePreviews. ex: datetime.date(2020, 3, 10)
     * @param type [string]: Payment type. ex: "brcode-payment", "boleto-payment", "utility-payment" or "tax-payment"
     * @param payment [BrcodePreview, BoletoPreview, UtilityPreview or TaxPreview]: Information preview of the informed payment.
     */
    public PaymentPreview(String id, String scheduled, String type, String payment) {
        super(id);
        this.scheduled = scheduled;
        this.type = type;
        this.payment = payment;

        Map<String, ClassData> subResourceByType = new HashMap<String, ClassData>() {{
            put("brcode-payment", BrcodePreview.data);
            put("boleto-payment", BoletoPreview.data);
            put("utility-payment", UtilityPreview.data);
            put("tax-payment", TaxPreview.data);
        }};

        if (subResourceByType.containsKey(payment)) {
            this.payment = subResourceByType.get(payment);
        }
    }

    /**
     * PaymentPreview object
     * <p>
     * A PaymentPreview is used to get information from a payment code you received to check the information before the payment.
     * <p>
     * @param data map of parameters for the creation of the Invoice
     * Parameters:
     * id [string]: Main identification of the payment. This should be the BR Code for Pix payments and lines or bar codes for payment slips. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062", "00020126580014br.gov.bcb.pix0136a629532e-7693-4846-852d-1bbff817b5a8520400005303986540510.005802BR5908T'Challa6009Sao Paulo62090505123456304B14A"
     * Parameters (optional):
     * scheduled [string]: intended payment date. Right now, this parameter only has effect on BrcodePreviews. ex: datetime.date(2020, 3, 10)
     * Parameters (return-only):
     * type [string]: Payment type. ex: "brcode-payment", "boleto-payment", "utility-payment" or "tax-payment"
     * payment [BrcodePreview, BoletoPreview, UtilityPreview or TaxPreview]: Information preview of the informed payment.
     */
    public PaymentPreview(Map<String, Object> data) throws Exception {
        super((String) data.remove("id"));
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.scheduled = (String) dataCopy.remove("scheduled");
        this.type = null;
        this.payment = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of PaymentPreviews objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param previews [list of PaymentPreviews objects]: list of PaymentPreviews objects to be created in the API
     * <p>
     * Return:
     * @return list of PaymentPreviews objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<?> create(List<?> previews) throws Exception {
        return PaymentPreview.create(previews, null);
    }

    /**
     * Create Boletos
     * <p>
     * Send a list of PaymentPreviews objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param previews [list of PaymentPreviews objects]: list of PaymentPreviews objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of PaymentPreviews objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<?> create(List<?> previews, User user) throws Exception {
        List<PaymentPreview> previewList = new ArrayList<>();
        for (Object preview : previews){
            if (preview instanceof Map){
                previewList.add(new PaymentPreview((Map<String, Object>) preview));
                continue;
            }
            if (preview instanceof PaymentPreview){
                previewList.add((PaymentPreview) preview);
                continue;
            }
            throw new Exception("Unknown type \"" + preview.getClass() + "\", use PaymentPreview or HashMap");
        }
        return Rest.post(data, previewList, user);
    }
}
