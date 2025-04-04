package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class CorporateBalance extends Resource {
    /**
     * CorporateBalance object
     * <p>
     * The CorporateBalance object displays the current corporate balance of the Workspace,
     * which is the result of the sum of all transactions within this
     * Workspace. The balance is never generated by the user, but it
     * can be retrieved to see the available information.
     * <p>
     * Parameters:
     * id [string]: unique id returned when CorporateBalance is created. ex: "5656565656565656"
     * amount [Long]: current balance amount of the Workspace in cents. ex: 200 (= R$ 2.00)
     * limit [Long]: The maximum negative balance allowed by the user. ex: 10000 (= R$ 100.00)
     * maxLimit [Long]: The maximum negative balance allowed by StarkBank. ex: 100000 (= R$ 1000.00)
     * currency [string]: currency of the current Workspace. Expect others to be added eventually. ex: "BRL"
     * updated [string]: latest update datetime for the CorporateBalance. ex: "2020-03-10 10:30:00.000000+00:00"
     *
     */
    static ClassData data = new ClassData(CorporateBalance.class, "CorporateBalance");

    public Long amount;
    public Long limit;
    public Long maxLimit;
    public String currency;
    public String updated;

    /**
     * CorporateBalance object
     * <p>
     * The CorporateBalance object displays the current corporate balance of the workspace,
     * which is the result of the sum of all transactions within this
     * workspace. The balance is never generated by the user, but it
     * can be retrieved to see the available information.
     * <p>
     * Parameters:
     * @param id [string]: unique id returned when CorporateBalance is created. ex: "5656565656565656"
     * @param amount [Long]: current balance amount of the Workspace in cents. ex: 200 (= R$ 2.00)
     * @param limit [Long]: The maximum negative balance allowed by the user. ex: 10000 (= R$ 100.00)
     * @param maxLimit [Long]: The maximum negative balance allowed by StarkBank. ex: 100000 (= R$ 1000.00)
     * @param currency [string]: currency of the current workspace. Expect others to be added eventually. ex: "BRL"
     * @param updated [string]: latest update datetime for the CorporateBalance. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public CorporateBalance(String id, Long amount, Long limit, Long maxLimit, String currency, String updated) {
        super(id);
        this.amount = amount;
        this.limit = limit;
        this.maxLimit = maxLimit;
        this.currency = currency;
        this.updated = updated;
    }

    public CorporateBalance(){
        super(null);
    }

    /**
     * Retrieve the CorporateBalance object
     * <p>
     * Receive the CorporateBalance object linked to your workspace in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object, default null]: Organization or Project object. Not necessary if StarkBank.Settings.user was set before function call
     * <p>
     * Return:
     * @return CorporateBalance object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateBalance get(User user) throws Exception{
        List<CorporateBalance> balanceList = new ArrayList<>();
        Generator<CorporateBalance> balances = Rest.getStream(data, new HashMap<String, Object>(), user);
        for (CorporateBalance balance : balances) {
            balanceList.add(balance);
        }
        return balanceList.get(0);
    }

    /**
     * Retrieve the Balance object
     * <p>
     * Receive the Balance object linked to your workspace in the Stark Bank API
     * <p>
     * Return:
     * @return CorporateBalance object with updated attributes
     * @throws Exception error in the request
     */
    public static CorporateBalance get() throws Exception {
        return CorporateBalance.get(null);
    }

}
