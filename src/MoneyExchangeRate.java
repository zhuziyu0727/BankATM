import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * store the exchange rate from one currency to another
 */
public class MoneyExchangeRate implements CurrencyAbbrs {
    // instance variables with private access
    private Map<String, Map<String, Double>> exchangeRates;

    // constructors
    public MoneyExchangeRate() {
        exchangeRates = new HashMap<String, Map<String, Double>>();
        loadData();
    }

    // load function
    public void loadData() {
        Map<String, Double> CNYMap = new HashMap<>();
        CNYMap.put(CNY, 1.00);
        CNYMap.put(EUR, 0.13);
        CNYMap.put(USD, 0.14);
        exchangeRates.put(CNY, CNYMap);
        Map<String, Double> EURMap = new HashMap<>();
        EURMap.put(EUR, 1.00);
        EURMap.put(CNY, 7.88);
        EURMap.put(USD, 1.11);
        exchangeRates.put(EUR, EURMap);
        Map<String, Double> USDMap = new HashMap<>();
        USDMap.put(USD, 1.00);
        USDMap.put(CNY, 7.08);
        USDMap.put(EUR, 0.90);
        exchangeRates.put(USD, USDMap);
    }

    // accessor function
    public double getRate(String from, String to) {
        return exchangeRates.get(from).get(to);
    }

    // calculator
    public double calculate(double prev, String from, String to) {
        double rate = getRate(from, to);
        return prev * rate;
    }
}
