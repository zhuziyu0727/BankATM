/**
 * US dollar currency
 */
public class CurrencyUSD extends Currency {
    // private static instance variables
    private static final String ABBR = "USD";
    private static final String DETAILS = "US Dollar";

    // constructors
    public CurrencyUSD() {
        super(ABBR, DETAILS, USD);
    }
}
