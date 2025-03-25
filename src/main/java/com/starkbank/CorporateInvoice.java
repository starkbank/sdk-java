package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;
import com.starkcore.utils.SubResource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class CorporateInvoice extends Resource {
    /**
     * CorporateInvoice object
     * <p>
     * The CorporateInvoice objects created in your Workspace load your Corporate balance when paid.
     * <p>
     * When you initialize a CorporateInvoice, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the created object.
     * <p>
     * Parameters:
     * amount [Long]: CorporateInvoice value in cents. ex: 1234 (= R$ 12.34)
     * tags [list of strings, default null]: list of strings for tagging. ex: ["travel", "food"]
     * id [string, default null]: unique id returned when CorporateInvoice is created. ex: "5656565656565656"
     * name [string, default sub-issuer name]: payer name. ex: "Iron Bank S.A."
     * taxId [string, default sub-issuer tax ID]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * brcode [string]: BR Code for the Invoice payment. ex: "00020101021226930014br.gov.bcb.pix2571brcode-h.development.starkbank.com/v2/d7f6546e194d4c64a153e8f79f1c41ac5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***63042109"
     * due [string]: Invoice due and expiration date in UTC ISO format. ex: "2020-10-28T17:59:26.249976+00:00"
     * link [string, default null]: public Invoice webpage URL. ex: "https://starkbank-card-issuer.development.starkbank.com/invoicelink/d7f6546e194d4c64a153e8f79f1c41ac"
     * status [string]: current CorporateInvoice status. ex: "created", "expired", "overdue", "paid"
     * corporateTransactionId [string]: ledger transaction ids linked to this CorporateInvoice. ex: "corporate-invoice/5656565656565656"
     * updated [string]: latest update datetime for the CorporateInvoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporateInvoice. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(CorporateInvoice.class, "CorporateInvoice");

    public Long amount;
    public String taxId;
    public String name;
    public String[] tags;
    public String due;
    public String brcode;
    public String link;
    public String status;
    public String corporateTransactionId;
    public String updated;
    public String created;

    /**
     * CorporateInvoice object
     * <p>
     * The CorporateInvoice objects created in your Workspace load your Corporate balance when paid.
     * <p>
     * When you initialize a CorporateInvoice, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the created object.
     * <p>
     * Parameters:
     * @param amount [Long]: CorporateInvoice value in cents. ex: 1234 (= R$ 12.34)
     * @param tags [list of strings, default null]: list of strings for tagging. ex: ["travel", "food"]
     * @param id [string]: unique id returned when CorporateInvoice is created. ex: "5656565656565656"
     * @param name [string, default sub-issuer name]: payer name. ex: "Iron Bank S.A."
     * @param taxId [string, default sub-issuer tax ID]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param brcode [string]: BR Code for the Invoice payment. ex: "00020101021226930014br.gov.bcb.pix2571brcode-h.development.starkbank.com/v2/d7f6546e194d4c64a153e8f79f1c41ac5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***63042109"
     * @param due [string]: Invoice due and expiration date in UTC ISO format. ex: "2020-10-28T17:59:26.249976+00:00"
     * @param link [string]: public Invoice webpage URL. ex: "https://starkbank-card-issuer.development.starkbank.com/invoicelink/d7f6546e194d4c64a153e8f79f1c41ac"
     * @param status [string]: current CorporateInvoice status. ex: "created", "expired", "overdue", "paid"
     * @param corporateTransactionId [string]: ledger transaction ids linked to this CorporateInvoice. ex: "corporate-invoice/5656565656565656"
     * @param updated [string]: latest update datetime for the CorporateInvoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param created [string]: creation datetime for the CorporateInvoice. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public CorporateInvoice(String id, String name, String brcode, String due, String link, Long amount, String taxId,
                          String status, String corporateTransactionId, String[] tags, String updated, String created
    ) {
        super(id);
        this.amount = amount;
        this.tags = tags;
        this.name = name;
        this.taxId = taxId;
        this.brcode = brcode;
        this.due = due;
        this.link = link;
        this.status = status;
        this.corporateTransactionId = corporateTransactionId;
        this.updated = updated;
        this.created = created;
    }

    /**
     * CorporateInvoice object
     * <p>
     * The CorporateInvoice objects created in your Workspace load your Corporate balance when paid.
     * <p>
     * When you initialize a CorporateInvoice, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the created object.
     * <p>
     * Parameters (required):
     * @param data map of properties for the creation of the CorporateInvoice
     * amount [integer]: CorporateInvoice value in cents. ex: 1234 (= R$ 12.34)
     * <p>
     * Parameters (optional):
     * tags [list of strings, default []]: list of strings for tagging. ex: ["travel", "food"]
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when CorporateInvoice is created. ex: "5656565656565656"
     * name [string, default sub-issuer name]: payer name. ex: "Iron Bank S.A."
     * taxId [string, default sub-issuer tax ID]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * brcode [string]: BR Code for the Invoice payment. ex: "00020101021226930014br.gov.bcb.pix2571brcode-h.development.starkbank.com/v2/d7f6546e194d4c64a153e8f79f1c41ac5204000053039865802BR5925Stark Bank S.A. - Institu6009Sao Paulo62070503***63042109"
     * due [string]: Invoice due and expiration date in UTC ISO format. ex: "2020-10-28T17:59:26.249976+00:00"
     * link [string]: public Invoice webpage URL. ex: "https://starkbank-card-issuer.development.starkbank.com/invoicelink/d7f6546e194d4c64a153e8f79f1c41ac"
     * status [string]: current CorporateInvoice status. ex: "created", "expired", "overdue", "paid"
     * corporateTransactionId [string]: ledger transaction ids linked to this CorporateInvoice. ex: "corporate-invoice/5656565656565656"
     * updated [string]: latest update datetime for the CorporateInvoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * created [string]: creation datetime for the CorporateInvoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public CorporateInvoice(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = ((Number) dataCopy.remove("amount")).longValue();
        this.tags = (String[]) dataCopy.remove("tags");
        this.name = null;
        this.taxId = null;
        this.brcode = null;
        this.due = null;
        this.link = null;
        this.status = null;
        this.corporateTransactionId = null;
        this.updated = null;
        this.created = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public CorporateInvoice(){
        super(null);
    }

    /**
     * Create a CorporateInvoice
     * <p>
     * Send a CorporateInvoice object for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param invoice [CorporateInvoice object]: CorporateInvoice object to be created in the API
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateInvoice object with updated attributes
     * @throws Exception error in the CorporateInvoice
     */
    public static CorporateInvoice create(CorporateInvoice invoice, User user) throws Exception {
        return Rest.postSingle(data, invoice, user);
    }

    /**
     * Create a CorporateInvoice
     * <p>
     * Send a CorporateInvoice object for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param invoice [CorporateInvoice object]: CorporateInvoice object to be created in the API
     * <p>
     * Return:
     * @return CorporateInvoice object with updated attributes
     * @throws Exception error in the CorporateInvoice
     */
    public static CorporateInvoice create(CorporateInvoice invoice) throws Exception {
        return Rest.postSingle(data, invoice, null);
    }

    /**
     * Retrieve CorporateInvoices
     * <p>
     * Receive a generator of CorporateInvoice objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex:"2020-03-10"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "overdue", "paid".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateInvoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateInvoice> query(Map<String, Object> params, User user) throws Exception{
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve CorporateInvoices
     * <p>
     * Receive a generator of CorporateInvoice objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param params map of parameters for the query
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2020-03-10"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "overdue", "paid".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * <p>
     * Return:
     * @return generator of CorporateInvoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateInvoice> query(Map<String, Object> params) throws Exception{
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve CorporateInvoices
     * <p>
     * Receive a generator of CorporateInvoice objects previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of CorporateInvoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateInvoice> query(User user) throws Exception{
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve CorporateInvoices
     * <p>
     * Receive a generator of CorporateInvoice objects previously created in the Stark Bank API
     * <p>
     * Return:
     * @return generator of CorporateInvoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<CorporateInvoice> query() throws Exception{
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<CorporateInvoice> invoices;
        public String cursor;

        public Page(List<CorporateInvoice> invoices, String cursor) {
            this.invoices = invoices;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged CorporateInvoices
     * <p>
     * Receive a list of up to 100 CorporateInvoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your CorporateInvoices.
     * <p>
     * Return:
     * CorporateInvoice.Page.invoices: list of CorporateInvoice objects with updated attributes
     * CorporateInvoice.Page.cursor: cursor to retrieve the next page of CorporateInvoice objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged CorporateInvoices
     * <p>
     * Receive a list of up to 100 CorporateInvoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your CorporateInvoices.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [string, default null]: filter for status of retrieved objects. Options: "created", "expired", "overdue", "paid".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * <p>
     * Return:
     * CorporateInvoice.Page.invoices: list of CorporateInvoice objects with updated attributes
     * CorporateInvoice.Page.cursor: cursor to retrieve the next page of CorporateInvoice objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String , Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged CorporateInvoices
     * <p>
     * Receive a list of up to 100 CorporateInvoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your CorporateInvoices.
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * CorporateInvoice.Page.invoices: list of CorporateInvoice objects with updated attributes
     * CorporateInvoice.Page.cursor: cursor to retrieve the next page of CorporateInvoice objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged CorporateInvoices
     * <p>
     * Receive a list of up to 100 CorporateInvoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your CorporateInvoices.
     * <p>
     * Parameters:
     * @param params map of parameters
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 35
     * after [string, default null]: date filter for objects created only after specified date. ex: "2022-03-22"
     * before [date string, default null]: date filter for objects created only before specified date. ex: "2022-03-22"
     * status [string, default null]: filter for status of retrieved objects. Options: created", "expired", "overdue", "paid".
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if starkbank.Settings.user was set before function call
     * <p>
     * Return:
     * CorporateInvoice.Page.invoices: list of CorporateInvoice objects with updated attributes
     * CorporateInvoice.Page.cursor: cursor to retrieve the next page of CorporateInvoice objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String , Object> params, User user) throws Exception {
        com.starkcore.utils.Page page = Rest.getPage(data, params, user);
        List<CorporateInvoice> invoices = new ArrayList<>();
        for (SubResource corporateInvoice: page.entities) {
            invoices.add((CorporateInvoice) corporateInvoice);
        }
        return new Page(invoices, page.cursor);

    }

}
