/**
 * stand for a checking bank account
 */
public class BankAccountChecking extends BankAccount {
    // constructor
    public BankAccountChecking(String currency) {
        super(currency);
        setType(CHECKING);
    }

    // transaction functions
    public void deposit(int day, int month, int year, double value) {
        addOneTransaction(day, month, year, value, "Customer", "");
    }

    public void withdraw(int day, int month, int year, double value) {
        addOneTransaction(day, month, year, value, "", "Customer");
    }

    // check function
    public void checkEligibleOpen(double value) {

    }
}
