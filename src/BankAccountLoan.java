/**
 * stands for a loan
 */
public class BankAccountLoan extends BankAccount implements BankAccountTypes {
    // constructor
    public BankAccountLoan(String currency) {
        super(currency);
        setType(LOAN);
    }
}
