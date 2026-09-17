package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class VerifiedTransfer extends Resource {
    /**
     * VerifiedTransfer object
     * <p>
     * When you initialize a VerifiedTransfer, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * amount [long]: transfer value in cents. ex: 1234 (= R$ 12.34)
     * accountId [string]: receiver's VerifiedAccount id. ex: "5656565656565656"
     * accountType [string]: receiver bank account type. This parameter only has effect on Pix Transfers. ex: "checking", "savings", "salary" or "payment"
     * externalId [string]: url safe string that must be unique among all your transfers. Duplicated externalIds will cause failures. By default, this parameter will block any transfer that repeats amount and receiver information on the same date. ex: "my-internal-id-123456"
     * scheduled [string]: date or datetime when the transfer will be processed. May be pushed to next business day if necessary. ex: "2020-03-11 08:00:00.000"
     * description [string]: optional description to override default description to be shown in the bank statement. ex: "Payment for service #1234"
     * displayDescription [string]: optional description to be shown in the receiver bank interface. ex: "Payment for service #1234"
     * tags [list of strings]: list of strings for reference when searching for verified transfers. ex: ["employees", "monthly"]
     * rules [list of Transfer.Rules]: list of Transfer.Rule objects for modifying transfer behavior. ex: [Transfer.Rule(key="resendingLimit", value=5)]
     * id [string]: unique id returned when the VerifiedTransfer is created. ex: "5656565656565656"
     * fee [integer]: fee charged when the transfer is created. ex: 200 (= R$ 2.00)
     * status [string]: current verified transfer status. ex: "created", "processing", "success" or "failed"
     * transactionIds [list of strings]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * metadata [Hashmap object]: Hashmap object used to store additional information about the VerifiedTransfer object.
     * created [string]: creation datetime for the verified transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: update datetime for the verified transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(VerifiedTransfer.class, "VerifiedTransfer");

    public long amount;
    public String accountId;
    public String accountType;
    public String externalId;
    public String scheduled;
    public String description;
    public String displayDescription;
    public String[] tags;
    public List<Transfer.Rule> rules;
    public Integer fee;
    public String status;
    public String[] transactionIds;
    public HashMap<String, Object> metadata;
    public String created;
    public String updated;

    /**
     * VerifiedTransfer object
     * <p>
     * When you initialize a VerifiedTransfer, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param amount [long]: transfer value in cents. ex: 1234 (= R$ 12.34)
     * @param accountId [string]: receiver's VerifiedAccount id. ex: "5656565656565656"
     * @param accountType [string]: receiver bank account type. This parameter only has effect on Pix Transfers. ex: "checking", "savings", "salary" or "payment"
     * @param externalId [string]: url safe string that must be unique among all your transfers. Duplicated externalIds will cause failures. By default, this parameter will block any transfer that repeats amount and receiver information on the same date. ex: "my-internal-id-123456"
     * @param scheduled [string]: date or datetime when the transfer will be processed. May be pushed to next business day if necessary. ex: "2020-03-11 08:00:00.000"
     * @param description [string]: optional description to override default description to be shown in the bank statement. ex: "Payment for service #1234"
     * @param displayDescription [string]: optional description to be shown in the receiver bank interface. ex: "Payment for service #1234"
     * @param tags [list of strings]: list of strings for reference when searching for verified transfers. ex: ["employees", "monthly"]
     * @param rules [list of Transfer.Rules]: list of Transfer.Rule objects for modifying transfer behavior. ex: [Transfer.Rule(key="resendingLimit", value=5)]
     * @param id [string]: unique id returned when the VerifiedTransfer is created. ex: "5656565656565656"
     * @param fee [integer]: fee charged when the transfer is created. ex: 200 (= R$ 2.00)
     * @param status [string]: current verified transfer status. ex: "created", "processing", "success" or "failed"
     * @param transactionIds [list of strings]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * @param metadata [Hashmap object]: Hashmap object used to store additional information about the VerifiedTransfer object.
     * @param created [string]: creation datetime for the verified transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * @param updated [string]: update datetime for the verified transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public VerifiedTransfer(String id, long amount, String accountId, String accountType, String externalId,
                    String scheduled, String description, String displayDescription, String[] tags,
                    List<Transfer.Rule> rules, Integer fee, String status, String[] transactionIds,
                    HashMap<String, Object> metadata, String created, String updated) {
        super(id);
        this.amount = amount;
        this.accountId = accountId;
        this.accountType = accountType;
        this.externalId = externalId;
        this.scheduled = scheduled;
        this.description = description;
        this.displayDescription = displayDescription;
        this.tags = tags;
        this.rules = rules;
        this.fee = fee;
        this.status = status;
        this.transactionIds = transactionIds;
        this.metadata = metadata;
        this.created = created;
        this.updated = updated;
    }

    /**
     * VerifiedTransfer object
     * <p>
     * When you initialize a VerifiedTransfer, the entity will not be automatically
     * created in the Stark Bank API. The "create" function sends the objects
     * to the Stark Bank API and returns the list of created objects.
     * <p>
     * Parameters:
     * @param data map of properties for the creation of the VerifiedTransfer
     * amount [long]: transfer value in cents. ex: 1234 (= R$ 12.34)
     * accountId [string]: receiver's VerifiedAccount id. ex: "5656565656565656"
     * <p>
     * Parameters (optional):
     * accountType [string, default "checking"]: receiver bank account type. This parameter only has effect on Pix Transfers. ex: "checking", "savings", "salary" or "payment"
     * externalId [string, default null]: url safe string that must be unique among all your transfers. Duplicated externalIds will cause failures. By default, this parameter will block any transfer that repeats amount and receiver information on the same date. ex: "my-internal-id-123456"
     * scheduled [string, default now]: date or datetime when the transfer will be processed. May be pushed to next business day if necessary. ex: "2020-03-11 08:00:00.000"
     * description [string, default null]: optional description to override default description to be shown in the bank statement. ex: "Payment for service #1234"
     * displayDescription [string, default null]: optional description to be shown in the receiver bank interface. ex: "Payment for service #1234"
     * tags [list of strings, default null]: list of strings for reference when searching for verified transfers. ex: ["employees", "monthly"]
     * rules [list of Transfer.Rules, default []]: list of Transfer.Rule objects for modifying transfer behavior. ex: [Transfer.Rule(key="resendingLimit", value=5)]
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when the VerifiedTransfer is created. ex: "5656565656565656"
     * fee [integer]: fee charged when the transfer is created. ex: 200 (= R$ 2.00)
     * status [string]: current verified transfer status. ex: "created", "processing", "success" or "failed"
     * transactionIds [list of strings]: ledger transaction ids linked to this transfer (if there are two, second is the chargeback). ex: ["19827356981273"]
     * metadata [Hashmap object]: Hashmap object used to store additional information about the VerifiedTransfer object.
     * created [string]: creation datetime for the verified transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * updated [string]: update datetime for the verified transfer. ex: "2020-03-10 10:30:00.000000+00:00"
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public VerifiedTransfer(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.amount = ((Number) dataCopy.remove("amount")).longValue();
        this.accountId = (String) dataCopy.remove("accountId");
        this.accountType = (String) dataCopy.remove("accountType");
        this.externalId = (String) dataCopy.remove("externalId");
        this.scheduled = (String) dataCopy.remove("scheduled");
        this.description = (String) dataCopy.remove("description");
        this.displayDescription = (String) dataCopy.remove("displayDescription");
        this.tags = (String[]) dataCopy.remove("tags");
        this.rules = parseRules((List<Object>) dataCopy.remove("rules"));
        this.fee = null;
        this.status = null;
        this.transactionIds = null;
        this.metadata = null;
        this.created = null;
        this.updated = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public VerifiedTransfer() {
        super(null);
    }

    /**
     * Create VerifiedTransfers
     * <p>
     * Send a list of VerifiedTransfer objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param transfers [list of VerifiedTransfer objects or HashMaps]: list of VerifiedTransfer objects to be created in the API
     * <p>
     * Return:
     * @return list of VerifiedTransfer objects with updated attributes
     * @throws Exception error in the request
     */
    public static List<VerifiedTransfer> create(List<?> transfers) throws Exception {
        return VerifiedTransfer.create(transfers, null);
    }

    /**
     * Create VerifiedTransfers
     * <p>
     * Send a list of VerifiedTransfer objects for creation in the Stark Bank API
     * <p>
     * Parameters:
     * @param transfers [list of VerifiedTransfer objects or HashMaps]: list of VerifiedTransfer objects to be created in the API
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call
     * <p>
     * Return:
     * @return list of VerifiedTransfer objects with updated attributes
     * @throws Exception error in the request
     */
    @SuppressWarnings("unchecked")
    public static List<VerifiedTransfer> create(List<?> transfers, User user) throws Exception {
        List<VerifiedTransfer> transferList = new ArrayList<>();
        for (Object transfer : transfers) {
            if (transfer instanceof Map) {
                transferList.add(new VerifiedTransfer((Map<String, Object>) transfer));
                continue;
            }
            if (transfer instanceof VerifiedTransfer) {
                transferList.add((VerifiedTransfer) transfer);
                continue;
            }
            throw new Exception("Unknown type \"" + transfer.getClass() + "\", use VerifiedTransfer or HashMap");
        }
        return Rest.post(data, transferList, user);
    }

    @SuppressWarnings("unchecked")
    private static List<Transfer.Rule> parseRules(List<Object> rules) throws Exception {
        if (rules == null)
            return null;

        List<Transfer.Rule> parsed = new ArrayList<>();
        if (rules.size() == 0 || rules.get(0) instanceof Transfer.Rule) {
            for (Object rule : rules) {
                parsed.add((Transfer.Rule) rule);
            }
            return parsed;
        }

        for (Object rule : rules) {
            parsed.add(new Transfer.Rule((Map<String, Object>) rule));
        }
        return parsed;
    }
}
