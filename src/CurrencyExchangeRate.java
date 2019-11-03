import java.util.HashMap;
import java.util.Map;

/**
 * Currency exchange rate should be only one instance.
 * Apply singleton class design pattern for this class.
 */
public class CurrencyExchangeRate {
    // Static variable makes sure that there is only one instance.
    private static CurrencyExchangeRate currencyExchangeRate;

    // Private variable store the rates.
    private Map<String, Map<String, Double>> exchangeRates;

    // Constructor initializes the private variable if it has not been initialized.
    private CurrencyExchangeRate() {
        exchangeRates = new HashMap<>();
    }

    // Static method makes sure there is at most one instance.
    public static CurrencyExchangeRate getInstance() {
        if (currencyExchangeRate == null) {
            currencyExchangeRate = new CurrencyExchangeRate();
        }
        return currencyExchangeRate;
    }

    // Put rate value into storage.
    public void setRate(String from, String to, double rate) {
        Map<String, Double> one = exchangeRates.get(from);
        if (one == null) {
            one = new HashMap<>();
        }
        one.put(to, rate);  // Change one row of values.
        exchangeRates.put(from, one);  // Update this row.
    }

    // Get rate value form the storage.
    public double getRate(String from, String to) {
        return exchangeRates.get(from).get(to);
    }
}
