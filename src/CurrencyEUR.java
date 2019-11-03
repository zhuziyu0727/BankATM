/**
 * Euro currency
 */
public class CurrencyEUR extends Currency {
    // private static instance variables
    private static final String ABBR = "EUR";
    private static final String DETAILS = "Euro";

    // constructors
    public CurrencyEUR() {
        super(ABBR, DETAILS, EUR);
    }
}
