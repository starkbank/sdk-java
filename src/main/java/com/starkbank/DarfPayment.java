package com.starkbank;

import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;
import com.starkbank.utils.SubResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DarfPayment extends Resource {
    static ClassData data = new ClassData(DarfPayment.class, "DarfPayment");

    public String description;
    public String revenueCode;
    public String taxId;
    public String competence;
    public long nominalAmount;
    public long fineAmount;
    public long interestAmount;
    public String due;
    public String referenceNumber;
    public String scheduled;
    public String[] tags;
    public String status;
    public String amount;
    public String fee;
    public String updated;
    public String created;


    /**
     * DarfPayment object
     * <p>
     * When you initialize a DarfPayment, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters (required):
     * @param description [string]: Text to be displayed in your statement (min. 10 characters). ex: "payment ABC"
     * @param revenueCode [string]: 4-digit tax code assigned by Federal Revenue. ex: "5948"
     * @param taxId [string]: tax id (formatted or unformatted) of the payer. ex: "12.345.678/0001-95"
     * @param competence [string]: competence month of the service. ex: "2021-04-30"
     * @param nominalAmount [int]: amount due in cents without fee or interest. ex: 23456 (= R$ 234.56)
     * @param fineAmount [int]: fixed amount due in cents for fines. ex: 234 (= R$ 2.34)
     * @param interestAmount [int]: amount due in cents for interest. ex: 456 (= R$ 4.56)
     * @param due [string]: due date for payment. ex: "2021-05-17"
     * Parameters (optional):
     * @param referenceNumber [string]: number assigned to the region of the tax. ex: "08.1.17.00-4"
     * @param scheduled [string, default today]: payment scheduled date. ex: "2021-05-10"
     * @param tags [list of strings]: list of strings for tagging
     * Attributes (return-only):
     * @param id [string, default null]: unique id returned when payment is created. ex: "5656565656565656"
     * @param status [string, default null]: current payment status. ex: "success" or "failed"
     * @param amount [int, default null]: Total amount due calculated from other amounts. ex: 24146 (= R$ 241.46)
     * @param fee [integer, default null]: fee charged when the DarfPayment is processed. ex: 0 (= R$ 0.00)
     * @param updated [string, default null]: creation datetime for the payment. ex: "2021-07-02T14:39:22.166351+00:00"
     * @param created [string, default null]: creation datetime for the payment. ex: "2021-07-02T14:39:22.166351+00:00"
     */
    public DarfPayment(String id, String description, String revenueCode, String taxId, String competence,
                       long nominalAmount, long fineAmount, long interestAmount, String due, String referenceNumber,
                       String scheduled, String[] tags, String status, String amount, String fee, String updated, String created) {
        super(id);
        this.description = description;
        this.revenueCode = revenueCode;
        this.taxId = taxId;
        this.competence = competence;
        this.nominalAmount = nominalAmount;
        this.fineAmount = fineAmount;
        this.interestAmount = interestAmount;
        this.due = due;
        this.referenceNumber = referenceNumber;
        this.scheduled = scheduled;
        this.tags = tags;
        this.status = status;
        this.amount = amount;
        this.fee = fee;
        this.updated = updated;
        this.created = created;
    }

    /**
     * DarfPayment object
     * <p>
     * When you initialize a DarfPayment, the entity will not be automatically
     * created in the Stark Bank API. The 'create' function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * @param data map of parameters for the creation of the DarfPayment
     * Parameters (required):
     * - description [string]: Text to be displayed in your statement (min. 10 characters). ex: "payment ABC"
     * - revenueCode [string]: 4-digit tax code assigned by Federal Revenue. ex: "5948"
     * - taxId [string]: tax id (formatted or unformatted) of the payer. ex: "12.345.678/0001-95"
     * - competence [string]: competence month of the service. ex: "2021-04-30"
     * - nominalAmount [int]: amount due in cents without fee or interest. ex: 23456 (= R$ 234.56)
     * - fineAmount [int]: fixed amount due in cents for fines. ex: 234 (= R$ 2.34)
     * - interestAmount [int]: amount due in cents for interest. ex: 456 (= R$ 4.56)
     * - due [string]: due date for payment. ex: "2021-05-17"
     * <p>
     * ## Parameters (optional):
     * - referenceNumber [string]: number assigned to the region of the tax. ex: "08.1.17.00-4"
     * - scheduled [string, default today]: payment scheduled date. ex: "2021-05-10"
     * - tags [list of strings]: list of strings for tagging
     * <p>
     * ## Attributes (return-only):
     * - status [string, default null]: current payment status. ex: "success" or "failed"
     * - amount [int, default null]: Total amount due calculated from other amounts. ex: 24146 (= R$ 241.46)
     * - fee [integer, default null]: fee charged when the DarfPayment is processed. ex: 0 (= R$ 0.00)
     * - updated [string, default null]: creation datetime for the payment. ex: "2021-07-02T14:39:22.166351+00:00"
     * - created [string, default null]: creation datetime for the payment. ex: "2021-07-02T14:39:22.166351+00:00"
     * @throws Exception error in the request
     */
    public DarfPayment(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.description = (String) dataCopy.remove("description");
        this.revenueCode = (String) dataCopy.remove("revenueCode");
        this.taxId = (String) dataCopy.remove("taxId");
        this.competence = (String) dataCopy.remove("competence");
        this.nominalAmount = Long.parseLong(String.valueOf(dataCopy.remove("nominalAmount")));
        this.fineAmount = Long.parseLong(String.valueOf(dataCopy.remove("fineAmount")));
        this.interestAmount = Long.parseLong(String.valueOf(dataCopy.remove("interestAmount")));
        this.due = (String) dataCopy.remove("due");
        this.referenceNumber = (String) dataCopy.remove("referenceNumber");
        this.scheduled = (String) dataCopy.remove("scheduled");
        this.tags = (String[]) dataCopy.remove("tags");
        this.status = null;
        this.amount = null;
        this.fee = null;
        this.updated = null;
        this.created = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    /**
     * Create DarfPayments
     * <p>
     * Send a list of DarfPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of DarfPayment objects or HashMaps]: list of DarfPayment objects to be created in the API
     * Return:
     * @return list of DarfPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<DarfPayment> create(List<?> payments) throws Exception {
        return DarfPayment.create(payments, null);
    }

    /**
     * Create DarfPayments
     * <p>
     * Send a list of DarfPayment objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param payments [list of DarfPayment objects or HashMaps]: list of DarfPayment objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * Return:
     * @return list of DarfPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<DarfPayment> create(List<?> payments, User user) throws Exception {
        List<DarfPayment> paymentList = new ArrayList<>();
        for (Object payment : payments) {
            if (payment instanceof Map) {
                paymentList.add(new DarfPayment((Map<String, Object>) payment));
                continue;
            }
            if (payment instanceof DarfPayment) {
                paymentList.add((DarfPayment) payment);
                continue;
            }
            throw new Exception("Unknown type \"" + payment.getClass() + "\", use DarfPayment or HashMap");
        }
        return Rest.post(data, paymentList, user);
    }

    /**
     * Retrieve a specific DarfPayment
     * <p>
     * Receive a single DarfPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return DarfPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static DarfPayment get(String id) throws Exception {
        return DarfPayment.get(id, null);
    }

    /**
     * Retrieve a specific DarfPayment
     * <p>
     * Receive a single DarfPayment object previously created by the Stark Bank API by passing its id
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DarfPayment object with updated attributes
     * @throws Exception error in the request
     */
    public static DarfPayment get(String id, User user) throws Exception {
        return Rest.getId(data, id, user);
    }

    /**
     * Retrieve a specific DarfPayment pdf file
     * <p>
     * Receive a single DarfPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for tax payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return DarfPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id) throws Exception {
        return DarfPayment.pdf(id, null);
    }

    /**
     * Retrieve a specific DarfPayment pdf file
     * <p>
     * Receive a single DarfPayment pdf file generated in the Stark Bank API by passing its id.
     * Only valid for tax payments with "success" status.
     * <p>
     * Parameters:
     * @param id [string]: object unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DarfPayment pdf file
     * @throws Exception error in the request
     */
    public static InputStream pdf(String id, User user) throws Exception {
        return Rest.getContent(data, id, "pdf", user, null);
    }


    /**
     * Retrieve DarfPayments
     * <p>
     * Receive a generator of DarfPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params paremeters of the query
     * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * <p>
     * Return:
     * @return generator of DarfPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DarfPayment> query(Map<String, Object> params) throws Exception {
        return DarfPayment.query(params, null);
    }

    /**
     * Retrieve DarfPayments
     * <p>
     * Receive a generator of DarfPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of DarfPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DarfPayment> query(User user) throws Exception {
        return DarfPayment.query(new HashMap<>(), user);
    }

    /**
     * Retrieve DarfPayments
     * <p>
     * Receive a generator of DarfPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Return:
     * @return generator of DarfPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DarfPayment> query() throws Exception {
        return DarfPayment.query(new HashMap<>(), null);
    }

    /**
     * Retrieve DarfPayments
     * <p>
     * Receive a generator of DarfPayment objects previously created in the Stark Bank API.
     * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
     * <p>
     * Parameters:
     * @param params paremeters of the query
     * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return generator of DarfPayment objects with updated attributes
     * @throws Exception error in the request
     */
    public static Generator<DarfPayment> query(Map<String, Object> params, User user) throws Exception {
        return Rest.getStream(data, params, user);
    }

    public final static class Page {
        public List<DarfPayment> payments;
        public String cursor;

        public Page(List<DarfPayment> payments, String cursor) {
            this.payments = payments;
            this.cursor = cursor;
        }
    }

    /**
     * Retrieve paged DarfPayments
     * <p>
     * Receive a list of up to 100 DarfPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * <p>
     * Return:
     * @return DarfPayment.Page object:
     * DarfPayment.Page.payments: list of DarfPayment objects with updated attributes
     * DarfPayment.Page.cursor: cursor to retrieve the next page of DarfPayment objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params) throws Exception {
        return page(params, null);
    }

    /**
     * Retrieve paged DarfPayments
     * <p>
     * Receive a list of up to 100 DarfPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DarfPayment.Page object:
     * DarfPayment.Page.payments: list of DarfPayment objects with updated attributes
     * DarfPayment.Page.cursor: cursor to retrieve the next page of DarfPayment objects
     * @throws Exception error in the request
     */
    public static Page page(User user) throws Exception {
        return page(new HashMap<>(), user);
    }

    /**
     * Retrieve paged DarfPayments
     * <p>
     * Receive a list of up to 100 DarfPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Return:
     * @return DarfPayment.Page object:
     * DarfPayment.Page.payments: list of DarfPayment objects with updated attributes
     * DarfPayment.Page.cursor: cursor to retrieve the next page of DarfPayment objects
     * @throws Exception error in the request
     */
    public static Page page() throws Exception {
        return page(new HashMap<>(), null);
    }

    /**
     * Retrieve paged DarfPayments
     * <p>
     * Receive a list of up to 100 DarfPayment objects previously created in the Stark Bank API and the cursor to the next page.
     * Use this function instead of query if you want to manually page your requests.
     * <p>
     * Parameters:
     * @param params parameters of the query
     * cursor [string, default null]: cursor returned on the previous page function call
     * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
     * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
     * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
     * tags [list of strings, default null]: tags to filter retrieved objects. ex: ["tony", "stark"]
     * ids [list of strings, default null]: list of ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
     * status [string, default null]: filter for status of retrieved objects. ex: "success"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return DarfPayment.Page object:
     * DarfPayment.Page.payments: list of DarfPayment objects with updated attributes
     * DarfPayment.Page.cursor: cursor to retrieve the next page of DarfPayment objects
     * @throws Exception error in the request
     */
    public static Page page(Map<String, Object> params, User user) throws Exception {
        com.starkbank.utils.Page page = Rest.getPage(data, params, user);
        List<DarfPayment> payments = new ArrayList<>();
        for (SubResource payment : page.entities) {
            payments.add((DarfPayment) payment);
        }
        return new Page(payments, page.cursor);
    }

    /**
     * Delete a DarfPayment entity
     * <p>
     * Delete a DarfPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: DarfPayment unique id. ex: "5656565656565656"
     * <p>
     * Return:
     * @return deleted DarfPayment object
     * @throws Exception error in the request
     */
    public static DarfPayment delete(String id) throws Exception {
        return DarfPayment.delete(id, null);
    }

    /**
     * Delete a DarfPayment entity
     * <p>
     * Delete a DarfPayment entity previously created in the Stark Bank API
     * <p>
     * Parameters:
     * @param id [string]: DarfPayment unique id. ex: "5656565656565656"
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return deleted DarfPayment object
     * @throws Exception error in the request
     */
    public static DarfPayment delete(String id, User user) throws Exception {
        return Rest.delete(data, id, user);
    }

    public final static class Log extends Resource {
        static ClassData data = new ClassData(DarfPayment.Log.class, "DarfPaymentLog");

        public String created;
        public String type;
        public String[] errors;
        public DarfPayment payment;

        public Log(String created, String type, String[] errors, DarfPayment payment, String id) {
            super(id);
            this.created = created;
            this.type = type;
            this.errors = errors;
            this.payment = payment;
        }

        /**
         * Retrieve a specific DarfPayment Log
         * <p>
         * Receive a single DarfPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * <p>
         * Return:
         * @return DarfPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id) throws Exception {
            return Log.get(id, null);
        }

        /**
         * Retrieve a specific DarfPayment Log
         * <p>
         * Receive a single DarfPayment Log object previously created by the Stark Bank API by passing its id
         * <p>
         * Parameters:
         * @param id [string]: object unique id. ex: "5656565656565656"
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return DarfPayment Log object with updated attributes
         * @throws Exception error in the request
         */
        public static Log get(String id, User user) throws Exception {
            return Rest.getId(data, id, user);
        }

        /**
         * Retrieve DarfPayment Logs
         * <p>
         * Receive a generator of DarfPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of DarfPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return list of DarfPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(Map<String, Object> params) throws Exception {
            return Log.query(params, null);
        }

        /**
         * Retrieve DarfPayment Logs
         * <p>
         * Receive a generator of DarfPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of DarfPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query(User user) throws Exception {
            return Log.query(new HashMap<>(), user);
        }

        /**
         * Retrieve DarfPayment Logs
         * <p>
         * Receive a generator of DarfPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Return:
         * @return list of DarfPayment Log objects with updated attributes
         * @throws Exception error in the request
         */
        public static Generator<Log> query() throws Exception {
            return Log.query(new HashMap<>(), null);
        }

        /**
         * Retrieve DarfPayment Logs
         * <p>
         * Receive a generator of DarfPayment.Log objects previously created in the Stark Bank API.
         * Use this function instead of page if you want to stream the objects without worrying about cursors and pagination.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * limit [Integer, default null]: maximum number of objects to be retrieved. Unlimited if null. ex: 35
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of DarfPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return list of DarfPayment Log objects with updated attributes
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
         * Retrieve paged DarfPayment.Logs
         * <p>
         * Receive a list of up to 100 DarfPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of DarfPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * <p>
         * Return:
         * @return DarfPayment.Log.Page object:
         * DarfPayment.Log.Page.logs: list of DarfPayment.Log objects with updated attributes
         * DarfPayment.Log.Page.cursor: cursor to retrieve the next page of DarfPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params) throws Exception {
            return Log.page(params, null);
        }

        /**
         * Retrieve paged DarfPayment.Logs
         * <p>
         * Receive a list of up to 100 DarfPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return DarfPayment.Log.Page object:
         * DarfPayment.Log.Page.logs: list of DarfPayment.Log objects with updated attributes
         * DarfPayment.Log.Page.cursor: cursor to retrieve the next page of DarfPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(User user) throws Exception {
            return Log.page(new HashMap<>(), user);
        }

        /**
         * Retrieve paged DarfPayment.Logs
         * <p>
         * Receive a list of up to 100 DarfPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Return:
         * @return DarfPayment.Log.Page object:
         * DarfPayment.Log.Page.logs: list of DarfPayment.Log objects with updated attributes
         * DarfPayment.Log.Page.cursor: cursor to retrieve the next page of DarfPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page() throws Exception {
            return Log.page(new HashMap<>(), null);
        }

        /**
         * Retrieve paged DarfPayment.Logs
         * <p>
         * Receive a list of up to 100 DarfPayment.Log objects previously created in the Stark Bank API and the cursor to the next page.
         * Use this function instead of query if you want to manually page your requests.
         * <p>
         * Parameters:
         * @param params parameters of the query
         * cursor [string, default null]: cursor returned on the previous page function call
         * limit [integer, default 100]: maximum number of objects to be retrieved. It must be an integer between 1 and 100. ex: 50
         * after [string, default null] date filter for objects created only after specified date. ex: "2020-03-10"
         * before [string, default null] date filter for objects created only before specified date. ex: "2020-03-10"
         * types [list of strings, default null]: filter retrieved objects by event types. ex: "processing" or "success"
         * paymentIds [list of strings, default null]: list of DarfPayment ids to filter retrieved objects. ex: ["5656565656565656", "4545454545454545"]
         * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
         * <p>
         * Return:
         * @return DarfPayment.Log.Page object:
         * DarfPayment.Log.Page.logs: list of DarfPayment.Log objects with updated attributes
         * DarfPayment.Log.Page.cursor: cursor to retrieve the next page of DarfPayment.Log objects
         * @throws Exception error in the request
         */
        public static Log.Page page(Map<String, Object> params, User user) throws Exception {
            com.starkbank.utils.Page page = Rest.getPage(data, params, user);
            List<DarfPayment.Log> logs = new ArrayList<>();
            for (SubResource log: page.entities) {
                logs.add((DarfPayment.Log) log);
            }
            return new Log.Page(logs, page.cursor);
        }
    }
}
