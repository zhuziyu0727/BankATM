import java.text.DecimalFormat;

/**
 * stand for one single bank transaction
 */
public class BankTransaction {
    // instance variables with private access
    private Date transactionDate;
    private Currency currency;
    private double amount;
    private double available;
    private String from;
    private String to;

    // constructors
    public BankTransaction() {
        transactionDate = new Date();
        setCurrency("CurrencyUSD");
        amount = 0;
        available = 0;
        from = "";
        to = "";
    }

    // accessor functions
    public Date getDateObject() {
    	return this.transactionDate;
    }
    
    public Currency getCurrency() {
    	return this.currency;
    }
    
    public String getDate() {
        return transactionDate.toString();
    }

    public double getAmount() {
        return amount;
    }

    public String getAmountStr() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(amount);
    }

    public double getAvailable() {
        return available;
    }

    public String getAvailableStr() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(available);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    // mutator functions
    public void setTransactionDate(int day, int month, int year) {
        transactionDate.setDate(day, month, year);
    }

    public void setCurrency(String currency) {
        CurrencyFactory currencyFactory = new CurrencyFactory();
        this.currency = currencyFactory.getCurrency(currency);
        if (this.currency == null) {
            String alert = String.format("\"%s\" currency is not valid right now.", currency);
            throw new IllegalArgumentException(alert);
        }
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
