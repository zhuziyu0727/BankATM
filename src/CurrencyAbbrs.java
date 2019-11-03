import java.util.HashMap;
import java.util.Map;

/**
 * list all abbr for available currencies
 */
public interface CurrencyAbbrs {
    String CNY = "CNY";
    String EUR = "EUR";
    String USD = "USD";
    String CurrencyCNY = "CurrencyCNY";
    String CurrencyEUR = "CurrencyEUR";
    String CurrencyUSD = "CurrencyUSD";
    Map<String, String> CurrencyDict = new HashMap() {{
        put(CurrencyCNY, CNY);
        put(CurrencyEUR, EUR);
        put(CurrencyUSD, USD);
    }};

    static String getAbbr(String name) {
        return CurrencyDict.get(name);
    }
}
