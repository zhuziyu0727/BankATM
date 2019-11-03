import java.util.ArrayList;

/**
 * List all available currency types.
 */
public interface CurrencyTypes {
    String CNY = "CurrencyCNY";
    String EUR = "CurrencyEUR";
    String USD = "CurrencyUSD";

    static ArrayList<String> getAllCurrencyTypes() {
        return new ArrayList<String>(){{
            add(CNY);
            add(EUR);
            add(USD);
        }};
    }

    static boolean isCurrencyTypeValid(String currencyType) {
        return getAllCurrencyTypes().contains(currencyType);
    }
}
