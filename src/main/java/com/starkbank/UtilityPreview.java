package com.starkbank;

import com.starkbank.utils.SubResource;


public final class UtilityPreview extends SubResource {
    /**
     * UtilityPreview object
     * <p>
     * A UtilityPreview is used to get information from a Utility Payment you received to check the information before the payment.
     * <p>
     * Parameters:
     * amount [number]: final amount to be paid. ex: 23456 (= R$ 234.56)
     * name [string]: beneficiary full name. ex: "Iron Throne"
     * description [string]: Utility payment description. ex: "Utility Payment - Iron Throne"
     * line [string]: Number sequence that identifies the payment. ex: "85660000006 6 67940064007 5 41190025511 7 00010601813 8"
     * barCode [string]: Bar code number that identifies the payment. ex: "85660000006679400640074119002551100010601813"
     */
    static ClassData data = new ClassData(UtilityPreview.class, "UtilityPreview");

    public long amount;
    public String name;
    public String description;
    public String line;
    public String barCode;

    /**
     * UtilityPreview object
     * <p>
     * A UtilityPreview is used to get information from a Utility Payment you received to check the information before the payment.
     * <p>
     * Parameters:
     * @param amount [number]: final amount to be paid. ex: 23456 (= R$ 234.56)
     * @param name [string]: beneficiary full name. ex: "Iron Throne"
     * @param description [string]: Utility payment description. ex: "Utility Payment - Iron Throne"
     * @param line [string]: Number sequence that identifies the payment. ex: "85660000006 6 67940064007 5 41190025511 7 00010601813 8"
     * @param barCode [string]: Bar code number that identifies the payment. ex: "85660000006679400640074119002551100010601813"
     */
    public UtilityPreview(long amount, String name, String description, String line, String barCode) {
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.line = line;
        this.barCode = barCode;
    }
}
