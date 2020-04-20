package com.starkbank;

import com.starkbank.user.Project;
import com.starkbank.utils.Generator;
import com.starkbank.utils.Resource;
import com.starkbank.utils.Rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Balance extends Resource {
    static ClassData data = new ClassData(Balance.class, "Balance");

    public int amount;
    public String currency;
    public String updated;

    public Balance(int amount, String currency, String updated, String id) {
        super(id);
        this.amount = amount;
        this.currency = currency;
        this.updated = updated;
    }

    public static Balance get(Project user) {
        List<Balance> balanceList = new ArrayList<>();
        Generator<Balance> balances = Rest.getList(data, new HashMap<>(), user);
        for (Balance balance : balances) {
            balanceList.add(balance);
        }
        return balanceList.get(0);
    }
}
