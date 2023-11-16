package com.starkbank;

import com.starkbank.utils.Rest;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Generator;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class Balance extends Resource {
    /**
     * Balance object
     * <p>
     * The Balance object displays the current balance of the workspace,
     * which is the result of the sum of all transactions within this
     * workspace. The balance is never generated by the user, but it
     * can be retrieved to see the information available.
     * <p>
     * Parameters:
     * id [string]: unique id returned when Balance is created. ex: "5656565656565656"
     * amount [long]: current balance amount of the workspace in cents. ex: 200 (= R$ 2.00)
     * currency [string]: currency of the current workspace. Expect others to be added eventually. ex: "BRL"
     * updated [string]: update datetime for the balance. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    static ClassData data = new ClassData(Balance.class, "Balance");

    public long amount;
    public String currency;
    public String updated;

    /**
     * Balance object
     * <p>
     * The Balance object displays the current balance of the workspace,
     * which is the result of the sum of all transactions within this
     * workspace. The balance is never generated by the user, but it
     * can be retrieved to see the information available.
     * <p>
     * Parameters:
     * @param id [string]: unique id returned when Balance is created. ex: "5656565656565656"
     * @param amount [long]: current balance amount of the workspace in cents. ex: 200 (= R$ 2.00)
     * @param currency [string]: currency of the current workspace. Expect others to be added eventually. ex: "BRL"
     * @param updated [string]: update datetime for the balance. ex: "2020-03-10 10:30:00.000000+00:00"
     */
    public Balance(long amount, String currency, String updated, String id) {
        super(id);
        this.amount = amount;
        this.currency = currency;
        this.updated = updated;
    }

    /**
     * Retrieve the Balance object
     * <p>
     * Receive the Balance object linked to your workspace in the Stark Bank API
     * <p>
     * Parameters:
     * @param user [Organization/Project object]: Organization or Project object. Not necessary if starkbank.User.defaultUser was set before function call     *
     * <p>
     * Return:
     * @return Balance object with updated attributes
     * @throws Exception error in the request
     */
    public static Balance get(User user) throws Exception {
        List<Balance> balanceList = new ArrayList<>();
        Generator<Balance> balances = Rest.getStream(data, new HashMap<String, Object>(), user);
        for (Balance balance : balances) {
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
     * @return Balance object with updated attributes
     * @throws Exception error in the request
     */
    public static Balance get() throws Exception {
        return Balance.get(null);
    }
}
