import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * stands for a bank customer
 */
public class BankCustomer extends Member implements BankAccountTypes {
    // instance variable with private access
    private String customerNumber;
    private ArrayList<BankAccount> accounts;

    // constructor
    public BankCustomer(String number) {
        super();
        accounts = new ArrayList<>();
        setCustomerNumber(number);
    }

    // accessor function
    public int getNumOfSecurityAccount() {
    	int num = 0;
    	for(BankAccount account : accounts) {
    		if(account.getType().equals(BankAccountTypes.SECURITY))
    			num++;
    	}
    	return num;
    }
    
    public String getCustomerNumber() {
        return customerNumber;
    }
    
    public int getNumOfAccount() {
    	return this.accounts.size();
    }
    
    public ArrayList<BankAccount> getAccounts(){
    	return this.accounts;
    }

    public ArrayList<String> getAllAccountNumbers() {
        ArrayList<String> allAccountNumbers = new ArrayList<>();
        for (BankAccount bankAccount: accounts) {
            if (!bankAccount.getType().equals(SECURITY)) {
                allAccountNumbers.add(bankAccount.getAccountNumber());
            }
        }
        return allAccountNumbers;
    }

    public BankAccount getAccountByAccountNumber(String accountNumber) {
        for (BankAccount b: accounts)
            if (accountNumber.equals(b.getAccountNumber()))
                return b;
        String alert = String.format("\"%s\" is not found in this customer.", accountNumber);
        throw new IllegalArgumentException(alert);
    }
    
    public int getNumberofAccountChecking() {
    	int count = 0;
        for (BankAccount b: accounts)
            if (b.getType() == "Checking") {
            	count ++;
            }
        return count;
    }
    
    public int getNumberofAccountSaving() {
    	int count = 0;
        for (BankAccount b: accounts)
            if (b.getType() == "Saving") {
            	count ++;
            }
        return count;
    }
    
    public int getNumberofAccountLoan() {
    	int count = 0;
        for (BankAccount b: accounts)
            if (b.getType() == "Loan") {
            	count ++;
            }
        return count;
    }
    
    public int getNumberofAccountSecurity() {
    	int count = 0;
        for (BankAccount b: accounts)
            if (b.getType() == "Security") {
            	count ++;
            }
        return count;
    }
    
    public String getRoutingNumberByAccountNumber(String accountNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        return bankAccount.getRoutingNumber();
    }

    public void setRoutingNumberByAccountNumber(String accountNumber, String routingNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        bankAccount.setRoutingNumber(routingNumber);
    }

    public String getAccountCurrencyAbbrByAccountNumber(String accountNumber) {
        BankAccount b = getAccountByAccountNumber(accountNumber);
        return b.getCurrencyAbbr();
    }
 
    public String getAccountTypeByAccountNumber(String accountNumber) {
        BankAccount b = getAccountByAccountNumber(accountNumber);
        return b.getType();
    }

    public double getAccountBalanceByAccountNumber(String accountNumber) {
        BankAccount b = getAccountByAccountNumber(accountNumber);
        return b.getBalance();
    }
    
    public String getOpenDateByAccountNumber(String accountNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        return bankAccount.getOpenDate();
    }

    public String getLastUpdateDateByAccountNumber(String accountNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        return bankAccount.getLastUpdateDate();
    }

    public String[][] getTransactionHistoryByAccountNumber(String accountNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        return bankAccount.getTransactionHistory();
    }
    //security account functions
    
    public BankAccountSecurity getSecurityAccountByAccountNumber(String accountNumber) {
    	BankAccountSecurity b = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
        return b;
    }
  
    public int getstockCountsByStockId(String accountNumber, String stockid) {
    	BankAccountSecurity b = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
        return b.getStockVolumeByStockId(Integer.parseInt(stockid));
    }
    
    public int getNumStockHoldingByAccountNumber(String accountNumber) {
    	BankAccountSecurity b = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
        return b.getNumStockHolding();
    }
    
    public double getStockTotalValueByAccountNumber(String accountNumber) {
    	BankAccountSecurity b = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
        return b.getTotalStockValue();
    }
    
    public double getAvgBoughtPriceByStockIdByAccount(String accountNumber, String stockId) {
    	BankAccountSecurity b = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
    	return b.getAvgBoughtPriceByStock(stockId);
    }
    public String[][] getStockTransactionHistoryByAccountNumber(String accountNumber) {
        BankAccountSecurity bankAccount = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
        return bankAccount.getStockTransactionHistory();
    }
    
    public String[][] getMyStocksByAccountNumber(String accountNumber, String[][] stockList){
    	BankAccountSecurity bankAccount = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
    	return bankAccount.getMyStocks(stockList);
    }
    
    public boolean isSavinghasSecurityByAccountNumber(String accountNumber) {
        for (BankAccount b: accounts)
            if (b.getType() == "Security") {
            	BankAccountSecurity security = (BankAccountSecurity) getAccountByAccountNumber(b.getAccountNumber());
            	if(security.getBindedSavingAccountNumber().getCode() == accountNumber) return true;
            }
        return false;
    }
    
    public String getSecurityAccountBySavingAccountNumber(String savingAccountNumber) {
        for (BankAccount b: accounts)
            if (b.getType() == "Security") {
            	BankAccountSecurity securityAccount = (BankAccountSecurity) getAccountByAccountNumber(b.getAccountNumber());
            	if(securityAccount.getBindedSavingAccountNumber().getCode() == savingAccountNumber) return securityAccount.getAccountNumber();
            }
        return "";
    }
    
	public boolean isSecurityClosable(String accountNumber) {
		BankAccountSecurity security = (BankAccountSecurity) getAccountByAccountNumber(accountNumber);
		return security.isClosable();
	}
	
    // mutator function
    public void setCustomerNumber(String number) {
        checkCustomerNumber(number);
        this.customerNumber = number;
    }

    // check function
    /**
     * must be a 9 digit number
     */
    public void checkCustomerNumber(String number) {
        try {
            if (number.length() != 9)
                throw new NumberFormatException();
            int numberInt = Integer.parseInt(number);
            if (numberInt < 0)
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            String alert = String.format("\"%s\" is not a valid customer number.", number);
            throw new IllegalArgumentException(alert);
        }
    }

    // primary function
    public void openAccount(String accountNumber, String type, String currency,
                            double value, int day, int month, int year, double fee) {
        if (!checkEligibleAccountNumber(accountNumber))
            throw new IllegalArgumentException("account number is duplicate.");
        if (value < fee)
            throw new IllegalArgumentException("Deposit is not enough to open an account.");
        BankAccountFactory bankAccountFactory = new BankAccountFactory();
        BankAccount bankAccount = bankAccountFactory.getBankAccount(type, currency);
        if (bankAccount == null) {
            String alert = String.format("\"%s\" type is not configured.", type);
            throw new IllegalArgumentException(alert);
        }
        bankAccount.open(day, month, year);
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.addOneTransaction(day, month, year, value, "Customer", "");
        bankAccount.addOneTransaction(day, month, year, fee, "", "Bank");
        accounts.add(bankAccount);
    }

    public void openSecurityAccount(String accountNumber, String bindedSavingNumber,
            double savingbalance, int day, int month, int year, double threshHold) {
    	if (!checkEligibleAccountNumber(accountNumber))
    		throw new IllegalArgumentException("account number is duplicate.");
    	if (savingbalance < threshHold)
    		throw new IllegalArgumentException("Saving balance is not enough to open an security account.");
    	BankAccount bankAccountSecurity = new BankAccountSecurity(new BankNumberAccount(bindedSavingNumber));
    	//doesn't charge fee
    	bankAccountSecurity.open(day, month, year);
    	bankAccountSecurity.setAccountNumber(accountNumber);
		accounts.add(bankAccountSecurity);
    }
    
    public void closeSecurityAccount(String accountNumber) {
    	BankAccountSecurity b = (BankAccountSecurity)getAccountByAccountNumber(accountNumber);
        if (!b.isClosable())
            throw new IllegalArgumentException("There exist stocks in security account.");
        accounts.remove(b);
    }
    
    public void depositToAccount(String accountNumber, double value, int day, int month, int year, double fee) {
        BankAccount b = getAccountByAccountNumber(accountNumber);
        double balance = b.getBalance();
        if (balance + value < fee)
            throw new IllegalArgumentException("Don't have enough money to make this deposit.");
        b.addOneTransaction(day, month, year, value, "Customer", "");
        if (fee > 0)
            b.addOneTransaction(day, month, year, fee, "", "Bank");
    }

    public void withdrawFromAccount(String accountNumber, double value, int day, int month, int year, double fee) {
        BankAccount b = getAccountByAccountNumber(accountNumber);
        double balance = b.getBalance();
        if (balance < value + fee)
            throw new IllegalArgumentException("Don't have enough money to make this withdraw.");
        b.addOneTransaction(day, month, year, value, "", "Customer");
        if (fee > 0)
            b.addOneTransaction(day, month, year, fee, "", "Bank");
    }

    public void closeAccount(String accountNumber, int day, int month, int year, double fee) {
        BankAccount b = getAccountByAccountNumber(accountNumber);
        double balance = b.getBalance();
        if (balance < fee)
            throw new IllegalArgumentException("Balance is not enough to close this account.");
        b.addOneTransaction(day, month, year, balance-fee, "", "Customer");
        b.addOneTransaction(day, month, year, fee, "", "Bank");
        accounts.remove(b);
    }

    public void transferFromAccount(String accountNumber, double value, int day, int month,
                                    int year, double fee, String otherAccountNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        double balance = bankAccount.getBalance();
        if (balance < value + fee)
            throw new IllegalArgumentException("Don't have enough money to make this transfer.");
        bankAccount.addOneTransaction(day, month, year, value, "", otherAccountNumber);
        if (fee > 0)
            bankAccount.addOneTransaction(day, month, year, fee, "", "Bank");
    }

    public void transferIntoAccount(String accountNumber, double value, int day, int month,
                                    int year, String otherAccountNumber) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        bankAccount.addOneTransaction(day, month, year, value, otherAccountNumber, "");
    }

    public void addInterest(String accountNumber, double tax, int day, int month, int year) {
        BankAccount bankAccount = getAccountByAccountNumber(accountNumber);
        bankAccount.addOneTransaction(day, month, year, tax, "Bank", "");
    }

    // check function
    public boolean checkEligibleAccountNumber(String number) {
        for (BankAccount b: accounts) {
            if (number.equals(b.getAccountNumber()))
                return false;
        }
        return true;
    }
    public boolean checkOpenSecurityAccount(String accountNumber, String bindedSavingNumber,
            double savingbalance, double threshHold) {
    	if (!checkEligibleAccountNumber(accountNumber))
    		return false;
    	if (savingbalance < threshHold)
    		return false;
    	return true;
    }
}
