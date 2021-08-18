package com.starkbank;

import com.starkbank.utils.SubResource;

public final class BoletoPreview extends SubResource {
    /**
     * BoletoPreview object
     * <p>
     * A BoletoPreview is used to get information from a Boleto Payment you received to check the information before the payment.
     * <p>
     * Parameters:
     * status [string]: current boleto status. ex: "active", "expired" or "inactive"
     * amount [number]: final amount to be paid. ex: 23456 (= R$ 234.56)
     * discountAmount [number]: discount amount to be paid. ex: 23456 (= R$ 234.56)
     * fineAmount [number]: fine amount to be paid. ex: 23456 (= R$ 234.56)
     * interestAmount [number]: interest amount to be paid. ex: 23456 (= R$ 234.56)
     * due [string]: Boleto due date. ex: 2020-04-30
     * expiration [string]: Boleto expiration date. ex: 2020-04-30
     * name [string]: beneficiary full name. ex: "Anthony Edward Stark"
     * taxId [string]: beneficiary tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * receiverName [string]: receiver (Sacador Avalista) full name. ex: "Anthony Edward Stark"
     * receiverTaxId [string]: receiver (Sacador Avalista) tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * payerName [string]: payer full name. ex: "Anthony Edward Stark"
     * payerTaxId [string]: payer tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * line [string]: Number sequence that identifies the payment. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * barCode [string]: Bar code number that identifies the payment. ex: "34195819600000000621090063571277307144464000"
     */
    static ClassData data = new ClassData(BoletoPreview.class, "BoletoPreview");

    public String status;
    public long amount;
    public Number discountAmount;
    public long fineAmount;
    public long interestAmount;
    public String due;
    public String expiration;
    public String name;
    public String taxId;
    public String receiverName;
    public String receiverTaxId;
    public String payerName;
    public String payerTaxId;
    public String line;
    public String barCode;

    /**
     * BrcodePreview object
     * <p>
     * A BrcodePreview is used to get information from a BR Code you received to check the informations before paying it.
     * <p>
     * Parameters:
     * @param status [string]: current boleto status. ex: "active", "expired" or "inactive"
     * @param amount [number]: final amount to be paid. ex: 23456 (= R$ 234.56)
     * @param discountAmount [number]: discount amount to be paid. ex: 23456 (= R$ 234.56)
     * @param fineAmount [number]: fine amount to be paid. ex: 23456 (= R$ 234.56)
     * @param interestAmount [number]: interest amount to be paid. ex: 23456 (= R$ 234.56)
     * @param due [string]: Boleto due date. ex: 2020-04-30
     * @param expiration [string]: Boleto expiration date. ex: 2020-04-30
     * @param name [string]: beneficiary full name. ex: "Anthony Edward Stark"
     * @param taxId [string]: beneficiary tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * @param receiverName [string]: receiver (Sacador Avalista) full name. ex: "Anthony Edward Stark"
     * @param receiverTaxId [string]: receiver (Sacador Avalista) tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * @param payerName [string]: payer full name. ex: "Anthony Edward Stark"
     * @param payerTaxId [string]: payer tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * @param line [string]: Number sequence that identifies the payment. ex: "34191.09008 63571.277308 71444.640008 5 81960000000062"
     * @param barCode [string]: Bar code number that identifies the payment. ex: "34195819600000000621090063571277307144464000"
     */
    public BoletoPreview(String status, long amount, Number discountAmount, long fineAmount, long interestAmount,
                         String due, String expiration, String name, String taxId, String receiverName,
                         String receiverTaxId, String payerName, String payerTaxId, String line, String barCode) {
        this.status = status;
        this.amount = amount;
        this.discountAmount = discountAmount;
        this.fineAmount = fineAmount;
        this.interestAmount = interestAmount;
        this.due = due;
        this.expiration = expiration;
        this.name = name;
        this.taxId = taxId;
        this.receiverName = receiverName;
        this.receiverTaxId = receiverTaxId;
        this.payerName = payerName;
        this.payerTaxId = payerTaxId;
        this.line = line;
        this.barCode = barCode;
    }
}
