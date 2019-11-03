/**
 * factory design patter for create a bank account with given string
 */
public class BankAccountFactory implements BankAccountTypes {
    public BankAccount getBankAccount(String type, String currency) {
        if (type == null) {
            return null;
        }
        if (type.equals(CHECKING)) {
            return new BankAccountChecking(currency);
        } else if (type.equals(LOAN)) {
            return new BankAccountLoan(currency);
        } else if (type.equals(SAVING)) {
            return new BankAccountSaving(currency);
        }
        return null;
    }
}
