/**
 * Chinese Yuan Renminbi currency
 */
public class CurrencyCNY extends Currency {
    // private static instance variables
    private static final String ABBR = "CNY";
    private static final String DETAILS = "Chinese Yuan Renminbi";

    // constructors
    public CurrencyCNY() {
        super(ABBR, DETAILS, CNY);
    }
}
