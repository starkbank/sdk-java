package com.starkbank;

import com.starkbank.utils.Resource;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;


public final class CorporateRule extends Resource {

    /**
     * CorporateRule object
     * <p>
     * The CorporateRule object displays the spending rules of CorporateCards and CorporateHolders created in your Workspace.
     * <p>
     * Parameters:
     * name [string]: rule name. ex: "Travel" or "Food"
     * amount [Long]: maximum amount that can be spent in the informed interval. ex: 200000 (= R$ 2000.00)
     * interval [string, default "lifetime"]: interval after which the rule amount counter will be reset to 0. ex: "instant", "day", "week", "month", "year" or "lifetime"
     * schedule [string, default null]: schedule time for user to spend. ex: "every monday, wednesday from 00:00 to 23:59 in America/Sao_Paulo"
     * purposes [list of string, default null]: list of strings representing the allowed purposes for card purchases, you can use this to restrict ATM withdrawals. ex: ["purchase", "withdrawal"]
     * currencyCode [string, default "BRL"]: code of the currency that the rule amount refers to. ex: "BRL" or "USD"
     * categories [list of MerchantCategory objects, default []]: merchant categories accepted by the rule. ex: ["eatingPlacesRestaurants", "travelAgenciesTourOperators"]
     * countries [list of MerchantCountry objects, default []]: countries accepted by the rule. ex: ["BRA", "USA"]
     * methods [list of CardMethod objects, default []]: card purchase methods accepted by the rule. ex: ["contactless", "manual"]
     * id [string]: unique id returned when a CorporateRule is created, used to update a specific CorporateRule. ex: "5656565656565656"
     * counterAmount [Long]: current rule spent amount. ex: 1000
     * currencySymbol [string]: currency symbol. ex: "R$"
     * currencyName [string]: currency name. ex: "Brazilian Real"
     *
     */
    static ClassData data = new ClassData(CorporateRule.class, "CorporateRule");

    public String name;
    public Long amount;
    public String interval;
    public String schedule;
    public String[] purposes;
    public String currencyCode;
    public List<MerchantCategory> categories;
    public List<MerchantCountry> countries;
    public List<CardMethod> methods;
    public Number counterAmount;
    public String currencySymbol;
    public String currencyName;

    /**
     * CorporateRule object
     * <p>
     * The CorporateRule object displays the spending rules of CorporateCards and CorporateHolders created in your Workspace.
     * <p>
     * Parameters:
     * @param name [string]: rule name. ex: "Travel" or "Food"
     * @param amount [Long]: maximum amount that can be spent in the informed interval. ex: 200000 (= R$ 2000.00)
     * @param interval [string, default "lifetime"]: interval after which the rule amount counter will be reset to 0. ex: "instant", "day", "week", "month", "year" or "lifetime"
     * @param schedule [string, default null]: schedule time for user to spend. ex: "every monday, wednesday from 00:00 to 23:59 in America/Sao_Paulo"
     * @param purposes [list of string, default null]: list of strings representing the allowed purposes for card purchases, you can use this to restrict ATM withdrawals. ex: ["purchase", "withdrawal"]
     * @param currencyCode [string, default "BRL"]: code of the currency that the rule amount refers to. ex: "BRL" or "USD"
     * @param categories [list of MerchantCategory objects, default []]: merchant categories accepted by the rule. ex: ["eatingPlacesRestaurants", "travelAgenciesTourOperators"]
     * @param countries [list of MerchantCountry objects, default []]: countries accepted by the rule. ex: ["BRA", "USA"]
     * @param methods [list of CardMethod objects, default []]: card purchase methods accepted by the rule. ex: ["contactless", "manual"]
     * @param id [string]: unique id returned when a CorporateRule is created, used to update a specific CorporateRule. ex: "5656565656565656"
     * @param counterAmount [Long]: current rule spent amount. ex: 1000
     * @param currencySymbol [string]: currency symbol. ex: "R$"
     * @param currencyName [string]: currency name. ex: "Brazilian Real"
     */
    public CorporateRule(String id, String name, Long amount, String interval, String schedule, String[] purposes, 
                        String currencyCode, List<MerchantCategory> categories, List<MerchantCountry> countries, List<CardMethod> methods,
                        Long counterAmount, String currencySymbol, String currencyName
    ) {
        super(id);
        this.name = name;
        this.amount = amount;
        this.interval = interval;
        this.schedule = schedule;
        this.purposes = purposes;
        this.currencyCode = currencyCode;
        this.categories = categories;
        this.countries = countries;
        this.methods = methods;
        this.counterAmount = counterAmount;
        this.currencySymbol = currencySymbol;
        this.currencyName = currencyName;
    }

    /**
     * CorporateRule object
     * <p>
     * The CorporateRule object displays the spending rules of CorporateCards and CorporateHolders created in your Workspace.
     * <p>
     * Parameters (required):
     * @param data map of properties for the creation of the CorporateRule
     * name [string]: rule name. ex: "Travel" or "Food"
     * amount [Long]: maximum amount that can be spent in the informed interval. ex: 200000 (= R$ 2000.00)
     * <p>
     * Parameters (optional):
     * interval [string, default "lifetime"]: interval after which the rule amount counter will be reset to 0. ex: "instant", "day", "week", "month", "year" or "lifetime"
     * schedule [string, default null]: schedule time for user to spend. ex: "every monday, wednesday from 00:00 to 23:59 in America/Sao_Paulo"
     * purposes [list of string, default null]: list of strings representing the allowed purposes for card purchases, you can use this to restrict ATM withdrawals. ex: ["purchase", "withdrawal"]
     * currencyCode [string, default "BRL"]: code of the currency that the rule amount refers to. ex: "BRL" or "USD"
     * categories [list of MerchantCategory objects, default []]: merchant categories accepted by the rule. ex: ["eatingPlacesRestaurants", "travelAgenciesTourOperators"]
     * countries [list of MerchantCountry objects, default []]: countries accepted by the rule. ex: ["BRA", "USA"]
     * methods [list of CardMethod objects, default []]: card purchase methods accepted by the rule. ex: ["contactless", "manual"]
     * <p>
     * Attributes (return-only):
     * id [string]: unique id returned when a CorporateRule is created, used to update a specific CorporateRule. ex: "5656565656565656"
     * counterAmount [Long]: current rule spent amount. ex: 1000
     * currencySymbol [string]: currency symbol. ex: "R$"
     * currencyName [string]: currency name. ex: "Brazilian Real"
     * @throws Exception error in the request
     */
    public CorporateRule(Map<String, Object> data) throws Exception {
        super(null);
        HashMap<String, Object> dataCopy = new HashMap<>(data);

        this.name = (String) dataCopy.remove("name");
        this.amount = ((Number) dataCopy.remove("amount")).longValue();
        this.interval = (String) dataCopy.remove("interval");
        this.schedule = (String) dataCopy.remove("schedule");
        this.purposes = (String[]) dataCopy.remove("purposes");
        this.currencyCode = (String) dataCopy.remove("currencyCode");
        this.categories = (List<MerchantCategory>) dataCopy.remove("categories");
        this.countries = (List<MerchantCountry>) dataCopy.remove("countries");
        this.methods = (List<CardMethod>)dataCopy.remove("methods");
        this.counterAmount = null;
        this.currencySymbol = null;
        this.currencyName = null;

        if (!dataCopy.isEmpty()) {
            throw new Exception("Unknown parameters used in constructor: [" + String.join(", ", dataCopy.keySet()) + "]");
        }
    }

    public CorporateRule(){
        super(null);
    }

    @SuppressWarnings("unchecked")
    static List<CorporateRule> parseRules(List<Object> rules) throws Exception {
        if (rules == null)
            return null;

        List<CorporateRule> parsed = new ArrayList<>();
        if (rules.size() == 0 || rules.get(0) instanceof CorporateRule) {
            for (Object rule : rules) {
                parsed.add((CorporateRule) rule);
            }
            return parsed;
        }

        for (Object rule : rules) {
            CorporateRule ruleObject = new CorporateRule((Map<String, Object>) rule);
            parsed.add(ruleObject);
        }

        return parsed;
    }
}
