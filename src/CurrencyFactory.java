/**
 * Factory design pattern.
 * Return a currency type depending on given String value.
 */
public class CurrencyFactory implements CurrencyTypes {
    public Currency getCurrency(String currencyType) {
        if (currencyType == null) {
            return null;
        }
        if (currencyType.equals(CNY)) {
            return new CurrencyCNY();
        } else if (currencyType.equals(EUR)) {
            return new CurrencyEUR();
        } else if (currencyType.equals(USD)) {
            return new CurrencyUSD();
        }
        return null;
    }
}
