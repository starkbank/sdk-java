package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.SubResource;
import com.starkbank.utils.Rest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Invoice extends Resource {
    /**
     * Invoice object
     * <p>
     * When you initialize an Invoice, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * amount           [number]: Invoice value in cents. Minimum = 0 (any value will be accepted). ex: 1234 (= R$ 12.34)
     * due              [string, default today + 2 days]: Invoice due date in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
     * taxId            [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * name             [string]: payer name. ex: "Iron Bank S.A."
     * expiration       [number, default null]: time interval in seconds between due date and expiration date. ex 123456789
     * fine             [number, default 0.0]: Invoice fine for overdue payment in %. ex: 2.5
     * interest         [number, default 0.0]: Invoice monthly interest for overdue payment in %. ex: 5.2
     * descriptions     [list of maps, default null]: list of maps with "key":string and (optional) "value":string pairs
     * discounts        [list of maps, default null]: list of maps with "percentage":number and "due":string pairs
     * tags             [list of strings, default null]: list of strings for tagging
     * pdf              [string, default null]: public Invoice PDF URL. ex: "https://invoice.starkbank.com/pdf/d454fa4e524441c1b0c1a729457ed9d8"
     * link             [string, default null]: public Invoice page URL. ex: "https://my-workspace.sandbox.starkbank.com/invoicelink/d454fa4e524441c1b0c1a729457ed9d8"
     * nominalAmount    [number, default null]: Invoice emission value in cents (will change if invoice is updated, but not if it's paid). ex: 400000
     * fineAmount       [number, default null]: Invoice fine value calculated over nominalAmount. ex: 20000
     * interestAmount   [number, default null]: Invoice interest value calculated over nominalAmount. ex: 10000
     * discountAmount   [number, default null]: Invoice discount value calculated over nominalAmount. ex: 3000
     * id               [string, default null]: unique id returned when Invoice is created. ex: "5656565656565656"
     * brcode           [string, default null]: BR Code for the Invoice payment. ex: "00020101021226800014br.gov.bcb.pix2558invoice.starkbank.com/f5333103-3279-4db2-8389-5efe335ba93d5204000053039865802BR5913Arya Stark6009Sao Paulo6220051656565656565656566304A9A0"
     * fee              [integer, default null]: fee charged buy this Invoice. ex: 65 (= R$ 0.65)
     * transactionIds   [list of strings]: ledger transaction ids linked to this Invoice (if there are more than one, all but the first are reversals or failed reversal chargebacks). ex: ["19827356981273"]
     * status           [string, default null]: current Invoice status. ex: "created", "paid", "canceled" or "overdue"
     * created          [string, default null]: creation datetime for the Invoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated          [string, default null]: creation datetime for the Invoice. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(Invoice.class, "Invoice");

    public Number amount;
    public String due;
    public String taxId;
    public String name;
    public Number expiration;
    public Number fine;
    public Number interest;
    public List<Invoice.Description> descriptions;
    public List<Invoice.Discount> discounts;
    public String[] tags;
    public String pdf;
    public String link;
    public Number nominalAmount;
    public Number fineAmount;
    public Number interestAmount;
    public Number discountAmount;
    public String brcode;
    public Integer fee;
    public String[] transactionIds;
    public String status;
    public String created;
    public String updated;

    /**
     * Invoice object
     * When you initialize an Invoice, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * To create scheduled Invoices, which will display the discount, interest, etc. on the final users banking interface,
     * use dates instead of datetimes on the "due" and "discounts" fields.
     * All parameters are passed in a Map of String and Object object.

     * Parameters:
     * @param amount            [number]: Invoice value in cents. Minimum = 200 (R$2,00). ex: 1234 (= R$ 12.34)
     * @param due               [string, default today + 2 days]: Invoice due date in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
     * @param taxId             [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * @param name              [string]: payer name. ex: "Iron Bank S.A."
     * @param expiration        [number, default null]: time interval in seconds between due date and expiration date. ex 123456789
     * @param fine              [number, default 0.0]: Invoice fine for overdue payment in %. ex: 2.5
     * @param interest          [number, default 0.0]: Invoice monthly interest for overdue payment in %. ex: 5.2
     * @param descriptions      [list of maps, default null]: list of maps with "key":string and (optional) "value":string pairs
     * @param discounts         [list of maps, default null]: list of maps with "percentage":number and "due":datetime.datetime or string pairs
     * @param tags              [list of strings, default null]: list of strings for tagging
     * @param pdf               [string, default null]: public Invoice PDF URL. ex: "https://invoice.starkbank.com/pdf/d454fa4e524441c1b0c1a729457ed9d8"
     * @param link              [string, default null]: public Invoice page URL. ex: "https://my-workspace.sandbox.starkbank.com/invoicelink/d454fa4e524441c1b0c1a729457ed9d8"
     * @param nominalAmount     [number, default null]: Invoice emission value in cents (will change if invoice is updated, but not if it's paid). ex: 400000
     * @param fineAmount        [number, default null]: Invoice fine value calculated over nominalAmount. ex: 20000
     * @param interestAmount    [number, default null]: Invoice interest value calculated over nominalAmount. ex: 10000
     * @param discountAmount    [number, default null]: Invoice discount value calculated over nominalAmount. ex: 3000
     * @param id                [string, default null]: unique id returned when Invoice is created. ex: "5656565656565656"
     * @param brcode            [string, default null]: BR Code for the Invoice payment. ex: "00020101021226800014br.gov.bcb.pix2558invoice.starkbank.com/f5333103-3279-4db2-8389-5efe335ba93d5204000053039865802BR5913Arya Stark6009Sao Paulo6220051656565656565656566304A9A0"
     * @param fee               [integer, default null]: fee charged buy this Invoice. ex: 65 (= R$ 0.65)
     * @param transactionIds    [list of strings]: ledger transaction ids linked to this Invoice (if there are more than one, all but the first are reversals or failed reversal chargebacks). ex: ["19827356981273"]
     * @param status            [string, default null]: current Invoice status. ex: "created", "paid", "canceled" or "overdue"
     * @param created           [string, default null]: creation datetime for the Invoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated           [string, default null]: creation datetime for the Invoice. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public Invoice(Number amount, String due, String taxId, String name, Number expiration, Number fine,
                    Number interest, List<Invoice.Description> descriptions, List<Invoice.Discount> discounts, String pdf,
                    String link, String[] tags, Number nominalAmount, Number fineAmount, Number interestAmount,
                    Number discountAmount, String id, String brcode, Integer fee, String[] transactionIds, String status,
                   String created, String updated
    ) {
        super(id);
        this.amount = amount;
        this.due = due;
        this.taxId = taxId;
        this.name = name;
        this.expiration = expiration;
        this.fine = fine;
        this.interest = interest;
        this.discounts = discounts;
        this.tags = tags;
        this.pdf = pdf;
        this.link = link;
        this.nominalAmount = nominalAmount;
        this.fineAmount = fineAmount;
        this.interestAmount = interestAmount;
        this.discountAmount = discountAmount;
        this.descriptions = descriptions;
        this.brcode = brcode;
        this.fee = fee;
        this.transactionIds = transactionIds;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    /**
     * Invoice object
     * <p>
     * When you initialize an Invoice, the entity will not be automatically
     * sent to the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * All parameters are passed in a Map of String and Object object.
     * <p>
     * @param data map of parameters for the creation of the Invoice
     * Parameters:
     * amount       [number]: Invoice value in cents. Minimum = 200 (R$2,00). ex: 1234 (= R$ 12.34)
     * taxId        [string]: payer tax ID (CPF or CNPJ) with or without formatting. ex: "01234567890" or "20.018.183/0001-80"
     * name         [string]: payer name. ex: "Iron Bank S.A."
     * <p>
     * Parameters (optional):
     * due          [string, default today + 2 days]: Invoice due date in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
     * expiration   [number, default 59 days]: time interval in seconds between due date and expiration date. ex 123456789
     * fine         [number, default 2.0]: Invoice fine for overdue payment in %. ex: 2.5
     * interest     [number, default 1.0]: Invoice monthly interest for overdue payment in %. ex: 5.2
     * descriptions [list of maps, default null]: list of maps with "key":string and (optional) "value":string pairs
     * discounts    [list of maps, default null]: list of maps with "percentage":number and "due":string pairs
     * tags         [list of strings, default null]: list of strings for tagging
     * <p>
     * Attributes (return-only):
     * pdf            [string, default null]: public Invoice PDF URL. ex: "https://invoice.starkbank.com/pdf/d454fa4e524441c1b0c1a729457ed9d8"
     * link           [string, default null]: public Invoice page URL. ex: "https://my-workspace.sandbox.starkbank.com/invoicelink/d454fa4e524441c1b0c1a729457ed9d8"
     * nominalAmount  [number, default null]: Invoice emission value in cents (will change if invoice is updated, but not if it's paid). ex: 400000
     * fineAmount     [number, default null]: Invoice fine value calculated over nominalAmount. ex: 20000
     * interestAmount [number, default null]: Invoice interest value calculated over nominalAmount. ex: 10000
     * discountAmount [number, default null]: Invoice discount value calculated over nominalAmount. ex: 3000
     * id             [string, default null]: unique id returned when Invoice is created. ex: "5656565656565656"
     * brcode         [string, default null]: BR Code for the Invoice payment. ex: "00020101021226800014br.gov.bcb.pix2558invoice.starkbank.com/f5333103-3279-4db2-8389-5efe335ba93d5204000053039865802BR5913Arya Stark6009Sao Paulo6220051656565656565656566304A9A0"
     * status         [string, default null]: current Invoice status. ex: "created", "paid", "canceled" or "overdue"
     * created        [string, default null]: creation datetime for the Invoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated        [string, default null]: creation datetime for the Invoice. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public Invoice(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = (Number) dataCopy.remove("amount");
        this.due = (String) dataCopy.remove("due");
        this.taxId = (String) dataCopy.remove("taxId");
        this.name = (String) dataCopy.remove("name");
        this.expiration = (Number) dataCopy.remove("expiration");
        this.fine = (Number) dataCopy.remove("fine");
        this.interest = (Number) dataCopy.remove("interest");
        this.descriptions = parseDescriptions((List<Object>) dataCopy.remove("descriptions"));
        this.discounts = parseDiscounts((List<Object>) dataCopy.remove("discounts"));
        this.tags = (String[]) dataCopy.remove("tags");
        this.pdf = null;
        this.link = null;
        this.nominalAmount = null;
        this.fineAmount = null;
        this.interestAmount = null;
        this.discountAmount = null;
        this.brcode = null;
        this.status = null;
        this.created = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    @SuppressWarnings("unchecked")
    private List<Invoice.Description> parseDescriptions(List<Object> descriptions) {
        if (descriptions == null)
            return null;

        List<Invoice.Description> parsed = new ArrayList<>();
        if (descriptions.size() == 0 || descriptions.get(0) instanceof Invoice.Description) {
            for (Object description : descriptions) {
                parsed.add((Invoice.Description) description);
            }
            return parsed;
        }

        for (Object description : descriptions) {
            Invoice.Description descriptionObject = new Invoice.Description(
                (String) ((Map<String, Object>) description).get("key"),
                (String) ((Map<String, Object>) description).get("value")
            );
            parsed.add(descriptionObject);
        }
        return parsed;
    }

    @SuppressWarnings("unchecked")
    private List<Invoice.Discount> parseDiscounts(List<Object> discounts) {
        if (discounts == null)
            return null;

        List<Invoice.Discount> parsed = new ArrayList<>();
        if (discounts.size() == 0 || discounts.get(0) instanceof Invoice.Discount) {
            for (Object discount : discounts) {
                parsed.add((Invoice.Discount) discount);
            }

            return parsed;
        }

        for (Object discount : discounts) {
            Invoice.Discount discountObject = new Invoice.Discount(
                (Number) ((Map<String, Object>) discount).get("percentage"),
                (String) ((Map<String, Object>) discount).get("due")
            );
            parsed.add(discountObject);
        }

        return parsed;
    }

    /**
     * Retrieve a specific Invoice
     * <p>
     * Receive a single Invoice object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Return:
     * @return Invoice object with updated attributes
     * @throws Exception error in the request
     */
    public static Invoice get(String id) throws Exception {
        return Invoice.get(id, null);
    }

    /**
     * Retrieve a specific Invoice
     * <p>
     * Receive a single Invoice object previously created in the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return Invoice object with updated attributes
     * @throws Exception error in the request
     */
    public static Invoice get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve Invoices
     * <p>
     * Receive a generator of Invoice objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of Invoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Invoice> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    /**
     * Retrieve Invoices
     * <p>
     * Receive a generator of Invoice objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params map of parameters
     * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return generator of Invoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Invoice> query(Map<String, Object> params) throws Exception {
        return Rest.getStream(data, params, null);
    }

    /**
     * Retrieve Invoices
     * <p>
     * Receive a generator of Invoice objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return generator of Invoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Invoice> query(User user) throws Exception {
        return Rest.getStream(data, new HashMap<>(), user);
    }

    /**
     * Retrieve Invoices
     * <p>
     * Receive a generator of Invoice objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * Return:
     * @return generator of Invoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<Invoice> query() throws Exception {
        return Rest.getStream(data, new HashMap<>(), null);
    }

    public final static class Page {
        public List<Invoice> invoices;
        public String cursor;

        public Page(List<Invoice> invoices, String cursor) {
            this.invoices = invoices;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged Invoices
     * <p>
     * Receive a list of up to 100 Invoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * <p>
     * Return:
     * @return Invoice.Page object:
     * Invoice.Page.invoices: list of Invoice objects with updated attributes
     * Invoice.Page.cursor: cursor to retrieve the next page of Invoice objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged Invoices
     * <p>
     * Receive a list of up to 100 Invoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Invoice.Page object:
     * Invoice.Page.invoices: list of Invoice objects with updated attributes
     * Invoice.Page.cursor: cursor to retrieve the next page of Invoice objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged Invoices
     * <p>
     * Receive a list of up to 100 Invoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return Invoice.Page object:
     * Invoice.Page.invoices: list of Invoice objects with updated attributes
     * Invoice.Page.cursor: cursor to retrieve the next page of Invoice objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged Invoices
     * <p>
     * Receive a list of up to 100 Invoice objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * status [string, default null]: filter for status of retrieved objects. ex: "created", "paid", "canceled" or "overdue"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Invoice.Page object:
     * Invoice.Page.invoices: list of Invoice objects with updated attributes
     * Invoice.Page.cursor: cursor to retrieve the next page of Invoice objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<Invoice> invoices = new ArrayList<>();
        for (SubResource invoice: page.entities) {
            invoices.add((Invoice) invoice);
        }
        return new Page(invoices, page.cursor);
    }

    /**
     * Create Invoices
     * <p>
     * Send a list of Invoice objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param invoices  [list of Invoice objects or Maps]: list of Invoice objects to be created in the API
     * @param user      [Project object]: Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return list of Invoice objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<Invoice> create(List<?> invoices, User user) throws Exception {
        List<Invoice> invoiceList = new ArrayList<>();
        for (Object invoice : invoices){
            if (invoice instanceof Map){
                invoiceList.add(new Invoice((Map<String, Object>) invoice));
                continue;
            }
            if (invoice instanceof Invoice){
                invoiceList.add((Invoice) invoice);
                continue;
            }
            throw new Exception("Unknown type \"" + invoice.getClass() + "\", use Invoice or HashMap");
        }
        return Rest.post(data, invoiceList, user);
    }

    /**
     * Create Invoices
     * <p>
     * Send a list of Invoice objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param invoices [list of Invoice objects or Maps]: list of Invoice objects to be created in the API
     * <p>
     * Return:
     * @return list of Invoice objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<Invoice> create(List<?> invoices) throws Exception {
        return Invoice.create(invoices, null);
    }

    /**
     * Update notification Invoice entity
     * <p>
     * Update the Invoice by passing id.
     * <p>
     * Parameters:
     * @param id        [string]: Invoice unique ids. ex: "5656565656565656"
     * @param patchData map of parameters to patch
     *                  status [string]: If the Invoice hasn't been paid yet, you may cancel it by passing "canceled" in the status
     *                  amount [string]: If the Invoice hasn't been paid yet, you may update its amount by passing the desired amount integer
     *                  due [string, default today + 2 days]: Invoice due date in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
     *                  expiration [number, default null]: time interval in seconds between due date and expiration date. ex 123456789
     * <p>
     * Return:
     * @return Invoice object with updated attributes
     * @throws Exception error in the request
     */
    public static Invoice update(String id, Map<String, Object> patchData) throws Exception {
        return Invoice.update(id, patchData, null);
    }

    /**
     * Update notification Invoice entity
     * <p>
     * Update notification Invoice by passing id.
     * <p>
     * Parameters:
     * @param id        [string]: Invoice unique ids. ex: "5656565656565656"
     * @param patchData map of properties to patch
     *                  status [string]: If the Invoice hasn't been paid yet, you may cancel it by passing "canceled" in the status
     *                  amount [string]: If the Invoice hasn't been paid yet, you may update its amount by passing the desired amount integer
     *                  due [string, default today + 2 days]: Invoice due date in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
     *                  expiration [number, default null]: time interval in seconds between due date and expiration date. ex 123456789
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Invoice object with updated attributes
     * @throws Exception error in the request
     */
    public static Invoice update(String id, Map<String, Object> patchData, User user) throws Exception {
        return Rest.patch(data, id, patchData, user);
    }

    /**
     * Retrieve a specific Invoice pdf file
     * <p>
     * Receive a single Invoice pdf file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Invoice pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id) throws Exception {
        return Invoice.pdf(id, null);
    }

    /**
     * Retrieve a specific Invoice pdf file
     * <p>
     * Receive a single Invoice pdf file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Invoice pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, User user) throws Exception {
        return Rest.getContent(data, id, "pdf", user, new HashMap<>());
    }

    /**
     * Retrieve a specific Invoice QR Code file
     * <p>
     * Receive a single Invoice QR Code png file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Invoice pdf file
     * @throws Exception error in the request
     */
    public static InputStream qrcode(String id) throws Exception {
        return Invoice.qrcode(id, null);
    }

    /**
     * Retrieve a specific Invoice QR Code file
     * <p>
     * Receive a single Invoice QR Code png file generated in the Stark Bank API by passing its id.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Invoice pdf file
     * @throws Exception error in the request
     */
    public static InputStream qrcode(String id, User user) throws Exception {
        return Rest.getContent(data, id, "qrcode", user, new HashMap<>());
    }

    /**
     * Retrieve a specific Invoice payment information
     * <p>
     * Receive the Invoice.Payment sub-resource associated with a paid Invoice.
     * <p>
     * Parameters:
     * @param id [string]: invoice unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return Invoice.Payment sub-resource
     * @throws Exception error in the request
     */
    public static Payment payment(String id) throws Exception {
        return payment(id, null);
    }

    /**
     * Retrieve a specific Invoice payment information
     * <p>
     * Receive the Invoice.Payment sub-resource associated with a paid Invoice.
     * <p>
     * Parameters:
     * @param id [string]: invoice unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return Invoice.Payment sub-resource
     * @throws Exception error in the request
     */
    public static Payment payment(String id, User user) throws Exception {
        return Rest.getSubResource(data, id, Payment.data, user, new HashMap<>());
    }

    /**
     * Invoice.Discount object
     * <p>
     * Used to define a discount in the invoice
     * <p>
     * Parameters:
     * percentage   [number]: discount percentage that will be applied. ex: 2.5
     * due          [string]: Date after when the discount will be overdue in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
     */
    public final static class Discount extends SubResource{
        public Number percentage;
        public String due;

        /**
         * Invoice.Discount object
         * Used to define a discount in the invoice

         * Parameters:
         * @param percentage    [number]: discount percentage that will be applied. ex: 2.5
         * @param due           [string]: Date after when the discount will be overdue in UTC ISO format. ex: "2020-11-25T17:59:26.249976+00:00"
         */
        public Discount(Number percentage, String due){
            this.percentage = percentage;
            this.due = due;
        }
    }

    /**
     * Invoice.Description object
     * <p>
     * Used to define a description in the invoice
     * <p>
     * Parameters:
     * key      [string]: key describing a part of the invoice value. ex: "Taxes"
     * value    [string]: value to which the key refers to. ex: "120"
     */
    public final static class Description extends SubResource {
        public String key;
        public String value;

        /**
         * Invoice.Description object
         * Used to define a description in the invoice

         * Parameters:
         * @param key   [string]: text indicating an item to be described. ex: "Taxes"
         * @param value [string]: text describing the specified item. ex: "Bad"
         */
        public Description(String key, String value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Invoice.Payment object
     * <p>
     * When an Invoice is paid, its Payment sub-resource will become available.
     * It carries all the available information about the invoice payment.
     * <p>
     * Parameters:
     * - amount [integer]: amount in cents that was paid. ex: 1234 (= R$ 12.34)
     * - name [string]: payer full name. ex: "Anthony Edward Stark"
     * - taxId [string]: payer tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
     * - bankCode [string]: code of the payer bank institution in Brazil. ex: "20018183"
     * - branchCode [string]: payer bank account branch. ex: "1357-9"
     * - accountNumber [string]: payer bank account number. ex: "876543-2"
     * - accountType [string]: payer bank account type. ex: "checking", "savings", "salary" or "payment"
     * - endToEndId [string]: central bank's unique transaction ID. ex: "E79457883202101262140HHX553UPqeq"
     * - method [string]: payment method that was used. ex: "pix"
     */
    public final static class Payment extends SubResource {
        public Integer amount;
        public String name;
        public String taxId;
        public String bankCode;
        public String branchCode;
        public String accountNumber;
        public String accountType;
        public String endToEndId;
        public String method;

        static ClassData data = new ClassData(Payment.class, "Payment");

        /**
         * Invoice.Payment object
         * <p>
         * When an Invoice is paid, its Payment sub-resource will become available.
         * It carries all the available information about the invoice payment.
         * <p>
         * Parameters:
         * @param amount [integer]: amount in cents that was paid. ex: 1234 (= R$ 12.34)
         * @param name [string]: payer full name. ex: "Anthony Edward Stark"
         * @param taxId [string]: payer tax ID (CPF or CNPJ). ex: "20.018.183/0001-80"
         * @param bankCode [string]: code of the payer bank institution in Brazil. ex: "20018183"
         * @param branchCode [string]: payer bank account branch. ex: "1357-9"
         * @param accountNumber [string]: payer bank account number. ex: "876543-2"
         * @param accountType [string]: payer bank account type. ex: "checking", "savings", "salary" or "payment"
         * @param endToEndId [string]: central bank's unique transaction ID. ex: "E79457883202101262140HHX553UPqeq"
         * @param method [string]: payment method that was used. ex: "pix"
         */
        public Payment(Integer amount, String name, String taxId, String bankCode, String branchCode, String accountNumber, String accountType, String endToEndId, String method) {
            this.amount = amount;
            this.name = name;
            this.taxId = taxId;
            this.bankCode = bankCode;
            this.branchCode = branchCode;
            this.accountNumber = accountNumber;
            this.accountType = accountType;
            this.endToEndId = endToEndId;
            this.method = method;
        }
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(Log.class, "InvoiceLog");

        public String created;
        public String type;
        public String[] errors;
        public Invoice invoice;

        /**
         * Invoice Log object
         * <p>
         * Every time an Invoice entity is updated, a corresponding Invoice Log
         * is generated for the entity. This log is never generated by the
         * user, but it can be retrieved to check additional information
         * on the Invoice.
         * <p>
         * Attributes:
         * @param id        [string]: unique id returned when the log is created. ex: "5656565656565656"
         * @param invoice   [Invoice]: Invoice entity to which the log refers to.
         * @param errors    [list of strings]: list of errors linked to this Invoice event
         * @param type      [string]: type of the Invoice event which triggered the log creation. ex: "registered" or "paid"
         * @param created   [string]: creation datetime for the log. ex: "2020-03-10 10:30:00.000000+00:00"
         */
        public Log(String created, String type, String[] errors, Invoice invoice, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.invoice = invoice;
        }

        /**
         * Retrieve a specific Invoice Log
         * <p>
         * Receive a single Invoice Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return Invoice Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific Invoice Log
         * <p>
         * Receive a single Invoice Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id    [string]: object unique id. ex: "5656565656565656"
         * @param user  [Project object]: Project object. Not necessary if StarkBank.Settings.user was set before function call
         * <p>
         * Return:
         * @return Invoice Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve Invoice Logs
         * <p>
         * Receive a generator of Invoice.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created", "paid", "canceled" or "overdue"
         * invoiceIds [list of strings, default null]: list of Invoice ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of Invoice Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve Invoice Logs
         * <p>
         * Receive a generator of Invoice.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
         * <p>
         * Return:
         * @return list of Invoice Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve Invoice Logs
         * <p>
         * Receive a generator of Invoice.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of Invoice Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve Invoice Logs
         * <p>
         * Receive a generator of Invoice.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params map of parameters
         * limit [integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created", "paid", "canceled" or "overdue"
         * invoiceIds [list of strings, default null]: list of Invoice ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user  [Project object, default null]: Project object. Not necessary if StarkBank.Settings.user was set before function call
         * <p>
         * Return:
         * @return list of Invoice Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params, User user) throws Exception {
            return Rest.getStream(data, params, user);
        }

        public final static class Page {
            public List<Log> logs;
            public String cursor;

            public Page(List<Log> logs, String cursor) {
                this.logs = logs;
                this.cursor = cursor;
            }
        }

        /**
         * Retrieve paged Invoice.Logs
         * <p>
         * Receive a list of up to 100 Invoice.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created", "paid", "canceled" or "overdue"
         * invoiceIds [list of strings, default null]: list of Invoice ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return Invoice.Log.Page object:
         * Invoice.Log.Page.logs: list of Invoice.Log objects with updated attributes
         * Invoice.Log.Page.cursor: cursor to retrieve the next page of Invoice.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged Invoice.Logs
         * <p>
         * Receive a list of up to 100 Invoice.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Invoice.Log.Page object:
         * Invoice.Log.Page.logs: list of Invoice.Log objects with updated attributes
         * Invoice.Log.Page.cursor: cursor to retrieve the next page of Invoice.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged Invoice.Logs
         * <p>
         * Receive a list of up to 100 Invoice.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return Invoice.Log.Page object:
         * Invoice.Log.Page.logs: list of Invoice.Log objects with updated attributes
         * Invoice.Log.Page.cursor: cursor to retrieve the next page of Invoice.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged Invoice.Logs
         * <p>
         * Receive a list of up to 100 Invoice.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter for log event types. ex: "created", "paid", "canceled" or "overdue"
         * invoiceIds [list of strings, default null]: list of Invoice ids to filter logs. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Invoice.Log.Page object:
         * Invoice.Log.Page.logs: list of Invoice.Log objects with updated attributes
         * Invoice.Log.Page.cursor: cursor to retrieve the next page of Invoice.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkbank.utils.Page page = Rest.getPage(data, params, user);
            List<Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((Log) log);
            }
            return new Log.Page(logs, page.cursor);
        }

        /**
         * Retrieve a specific Invoice.Log pdf file
         * <p>
         * Receive a single Invoice.Log pdf file generated in the Stark Bank API by passing its id.
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return Invoice.Log pdf file
         * @throws Exception error in the request
         */
        public static InputStream pdf(String id) throws Exception {
            return pdf(id, null);
        }

        /**
         * Retrieve a specific Invoice.Log pdf file
         * <p>
         * Receive a single Invoice.Log pdf file generated in the Stark Bank API by passing its id.
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return Invoice.Log pdf file
         * @throws Exception error in the request
         */
        public static InputStream pdf(String id, User user) throws Exception {
            return Rest.getContent(data, id, "pdf", user, new HashMap<>());
        }
    }
}
