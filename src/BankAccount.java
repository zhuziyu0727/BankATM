import java.util.ArrayList;

/**
 * stand for a bank account
 */
public class BankAccount implements BankAccountTypes {
    // instance variables with private access
    private BankNumberRouting routingNumber;
    private BankNumberAccount accountNumber;
    private Money balance;
    private BankTransactionsHistory transactions;
    private Date openDate;
    private Date lastUpdateDate;
    private String type;

    // constructor
    public BankAccount() {
        routingNumber = new BankNumberRouting();
        accountNumber = new BankNumberAccount();
        balance = new Money();
        transactions = new BankTransactionsHistory();
        openDate = new Date();
        lastUpdateDate = new Date();
        type = "";
    }

    public BankAccount(String currency) {
        this();
        setCurrency(currency);
    }

    // open function
    public void open(int day, int month, int year) {
        setOpenDate(day, month, year);
    }

    // accessor functions
    public int getNumTransaction() {
    	return transactions.getHistory().size();
    }
    
    public Date getLastUpdateDateObject() {
    	return this.lastUpdateDate;
    }
    
    public Date getOpenDateObject() {
    	return this.openDate;
    }
    
    public String getRoutingNumber() {
        return routingNumber.getCode();
    }

    public String getAccountNumber() {
        return accountNumber.getCode();
    }

    public double getBalance() {
        return balance.getAmount();
    }

    public String getCurrencyAbbr() {
        return balance.getCurrencyAbbr();
    }

    public String getCurrency() {
        return balance.getCurrency();
    }

    public String getCurrencyType() {
        return balance.getCurrencyType();
    }

    public String getOpenDate() {
        return openDate.toString();
    }

    public String getLastUpdateDate() {
        return lastUpdateDate.toString();
    }

    public String getType() {
        return type;
    }

    public String[][] getTransactionHistory() {
        return transactions.getData();
    }
    
    public BankTransactionsHistory getTransactionHistoryObject() {
    	return this.transactions;
    }

    // mutator functions
    public void setRoutingNumber(String code) {
        routingNumber.setCode(code);
    }

    public void setAccountNumber(String code) {
        accountNumber.setCode(code);
    }

    public void setBalance(double amount) {
        balance.setAmount(amount);
    }

    public void setCurrency(String currency) {
        balance.setCurrency(currency);
    }

    public void addOneTransaction(int day, int month, int year, double amount, String from, String to) {
        checkFromTo(from, to);
        if (!from.equals("")) {
            double available = getBalance() + amount;
            transactions.addNewTransaction(day, month, year, getCurrencyType(), amount, available, from , to);
            addBalance(amount);
        } else {
            double available = getBalance() - amount;
            transactions.addNewTransaction(day, month, year, getCurrencyType(), amount, available, from , to);
            deductBalance(amount);
        }
        setLastUpdateDate(day, month, year);
    };

    public void setOpenDate(int day, int month, int year) {
        openDate.setDate(day, month, year);
    }

    public void setLastUpdateDate(int day, int month, int year) {
        lastUpdateDate.setDate(day, month, year);
    }

    public void setType(String type) {
        this.type = type;
    }

    // change functions
    public void addBalance(double value) {
        balance.addAmount(value);
    }

    public void deductBalance(double value) {
        balance.deductAmount(value);
    }

    // check functions
    protected void checkFromTo(String from, String to) {
        if (from.equals("") && to.equals(""))
            throw new IllegalArgumentException("\"From\" and \"To\" must not be both empty.");
        else if (!from.equals("") && !to.equals(""))
            throw new IllegalArgumentException("\"From\" and \"To\" must not be both not empty.");
    }
}
