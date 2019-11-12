/**
 * extend for a saving bank account
 */
public class BankAccountSaving extends BankAccount implements BankAccountTypes {
    // constructor
	private String bindedSecurityAccountNumber;
	
    public BankAccountSaving(String currency) {
        super(currency);
        setType(SAVING);
    }
    
    public String getBindedSecurityAccountNumber(){
    	return this.bindedSecurityAccountNumber;
    }
    
    public void setBindedSecurityAccountNumber(String s) {
    	this.bindedSecurityAccountNumber = s;
    }
}
