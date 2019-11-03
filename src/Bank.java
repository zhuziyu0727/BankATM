import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * stand for a bank
 * make bank as a singleton class
 */
public class Bank implements BankAccountTypes {
    // private static instance variable
    private static Bank bank_instance = null;

    // instance variables with private access
    private BankManager manager;
    private ArrayList<BankCustomer> customers;
    private BankChargeStandard chargeStandard;
    private MoneyExchangeRate exchangeRate;

    // private constructor
    private Bank() {
        loadData();
    }

    // load data
    public void loadData() {
        manager = new BankManager();
        customers = new ArrayList<>();
        chargeStandard = new BankChargeStandard();
        exchangeRate = new MoneyExchangeRate();
    }

    // accessor function
    public ArrayList<BankCustomer> getCustomers() {
        return customers;
    }

    public static Bank getInstance() {
        if (bank_instance == null)
            bank_instance = new Bank();
        return bank_instance;
    }

    public String getCustomerNumberByCustomer(BankCustomer customer) {
        return customer.getCustomerNumber();
    }

    public String getUsernameByCustomer(BankCustomer customer) {
        return customer.getUsername();
    }

    public String getPasswordByCustomer(BankCustomer customer) {
        return customer.getPassword();
    }

    public String getEmailByCustomer(BankCustomer customer) {
        return customer.getEmail();
    }

    public String getPhoneByCustomer(BankCustomer customer) {
        return customer.getPhoneNumber();
    }

    public String getAddress1ByCustomer(BankCustomer customer) {
        return customer.getAddressAddress1();
    }

    public String getAddress2ByCustomer(BankCustomer customer) {
        return customer.getAddressAddress2();
    }

    public String getCityByCustomer(BankCustomer customer) {
        return customer.getAddressCity();
    }

    public String getStateByCustomer(BankCustomer customer) {
        return customer.getAddressState();
    }

    public String getZipCodeByCustomer(BankCustomer customer) {
        return customer.getAddressZipCode();
    }

    public String getCountryByCustomer(BankCustomer customer) {
        return customer.getAddressCountry();
    }

    public ArrayList<String> getAllAccountNumbersByCustomer(BankCustomer customer) {
        return customer.getAllAccountNumbers();
    }

    public String getAccountTypeByCustomerAccountNumber(BankCustomer customer, String accountNumber) {
        return customer.getAccountTypeByAccountNumber(accountNumber);
    }

    public String getAccountCurrencyAbbrByCustomerAccountNumber(BankCustomer customer, String accountNumber) {
        return customer.getAccountCurrencyAbbrByAccountNumber(accountNumber);
    }

    public double getAccountBalanceByCustomerAccountNumber(BankCustomer customer, String accountNumber) {
        return customer.getAccountBalanceByAccountNumber(accountNumber);
    }

    public String getRoutingNumberByCustomerAccountNumber(BankCustomer customer, String accountNumber) {
        return customer.getRoutingNumberByAccountNumber(accountNumber);
    }

    public void setRoutingNumberByCustomerAccountNumber(BankCustomer customer, String accountNumber, String routingNumber) {
        customer.setRoutingNumberByAccountNumber(accountNumber, routingNumber);
    }

    public String getOpenDateByCustomerAccountNumber(BankCustomer customer, String accountNumber) {
        return customer.getOpenDateByAccountNumber(accountNumber);
    }

    public String getLastUpdateDateByCustomerAccountNumber(BankCustomer customer, String accountNumber) {
        return customer.getLastUpdateDateByAccountNumber(accountNumber);
    }

    public String[][] getTransactionHistoryByCustomerAccountNumber(BankCustomer customer,
                                                                                     String accountNumber) {
        return customer.getTransactionHistoryByAccountNumber(accountNumber);
    }

    // primary functions
    public void increaseSaving(int day, int month, int year) {
        for (BankCustomer customer: customers) {
            ArrayList<String> allAccountNumbers = getAllAccountNumbersByCustomer(customer);
            for (String accountNumber: allAccountNumbers) {
                String type = getAccountTypeByCustomerAccountNumber(customer, accountNumber);
                if (type.equals(SAVING)) {
                    double balanceAmount = getAccountBalanceByCustomerAccountNumber(customer, accountNumber);
                    double rate = chargeStandard.getSavingInterestRate();
                    double tax = balanceAmount * rate;
                    customer.addInterest(accountNumber, tax, day, month, year);
                }
            }
        }
    }

    public void increaseLoan(int day, int month, int year) {
        for (BankCustomer customer: customers) {
            ArrayList<String> allAccountNumbers = getAllAccountNumbersByCustomer(customer);
            for (String accountNumber: allAccountNumbers) {
                String type = getAccountTypeByCustomerAccountNumber(customer, accountNumber);
                if (type.equals(LOAN)) {
                    double balanceAmount = getAccountBalanceByCustomerAccountNumber(customer, accountNumber);
                    double rate = chargeStandard.getLoanInterestRate();
                    double tax = balanceAmount * rate;
                    customer.addInterest(accountNumber, tax, day, month, year);
                }
            }
        }
    }


    public void setManager(String username, String password, String email) {
        checkUsername(username);
        checkEmail(email);
        manager = new BankManager();
        manager.setUsername(username);
        manager.setPassword(password);
        manager.setEmail(email);
    }

    public boolean checkManager(String username, String password) {
        return username.equals(manager.getUsername()) && password.equals(manager.getPassword());
    }


    public BankCustomer memberLogin(String username, String password) {
        // manager login

        // customer login
        for (BankCustomer customer: customers) {
            if (username.equals(customer.getUsername()) && password.equals((customer.getPassword()))) {
                return customer;
            }
        }
        return null;
    }

    public void registerNewCustomer(String username, String password, String email) {
        checkUsername(username);
        checkEmail(email);
        String customerNumber = generateRandomCustomerNumber();
        BankCustomer newCustomer = new BankCustomer(customerNumber);
        newCustomer.setUsername(username);
        newCustomer.setPassword(password);
        newCustomer.setEmail(email);
        customers.add(newCustomer);
    }

    public BankCustomer getCustomerByUsername(String username) {
        for (BankCustomer b: customers) {
            if (username.equals(b.getUsername()))
                return b;
        }
        String alert = String.format("Cannot find customer with username as \"%s\"", username);
        throw new IllegalArgumentException(alert);
    }

    public String openAccount(BankCustomer customer, String type, String currency,
                            double value, int day, int month, int year) {
        double fee = getOpenAccountFeeByCurrency(currency);
        String accountNumber = generateRandomAccountNumber();
        customer.openAccount(accountNumber, type, currency, value, day, month, year, fee);
        return accountNumber;
    }

    public void depositToAccount(BankCustomer customer, String accountNumber,
                                 double value, int day, int month, int year) {
        String type = customer.getAccountTypeByAccountNumber(accountNumber);
        double fee = 0.00;
        if (type.equals(CHECKING)) {
            String toAbbr = customer.getAccountCurrencyAbbrByAccountNumber(accountNumber);
            fee = getCheckingDepositFeeByCurrencyAbbr(toAbbr);
        }
        customer.depositToAccount(accountNumber, value, day, month, year, fee);
    }

    public void withdrawFromAccount(BankCustomer customer, String accountNumber,
                                    double value, int day, int month, int year) {
        String type = customer.getAccountTypeByAccountNumber(accountNumber);
        double fee = 0.00;
        if (type.equals(CHECKING)) {
            String toAbbr = customer.getAccountCurrencyAbbrByAccountNumber(accountNumber);
            fee = getCheckingWithdrawFeeByCurrencyAbbr(toAbbr);
        }
        customer.withdrawFromAccount(accountNumber, value, day, month, year, fee);
    }

    public void closeAccount(BankCustomer customer, String accountNumber, int day, int month, int year) {
        String toAbbr = customer.getAccountCurrencyAbbrByAccountNumber(accountNumber);
        double fee = getCloseAccountFeeByCurrencyAbbr(toAbbr);
        customer.closeAccount(accountNumber, day, month, year, fee);
    }

    public void transferValue(BankCustomer customer, String fromAccountNumber, String toAccountNumber,
                              double value, int day, int month, int year) {
        BankCustomer customer2 = null;
        for (BankCustomer each: customers) {
            if (!each.checkEligibleAccountNumber(toAccountNumber)) {
                customer2 = each;
                break;
            }
        }
        if (customer2 == null) {
            String alert = String.format("Cannot find account number \"%s\".", toAccountNumber);
            throw new IllegalArgumentException(alert);
        }
        String type = customer.getAccountTypeByAccountNumber(fromAccountNumber);
        if (type.equals(LOAN)) {
            throw new IllegalArgumentException("Cannot transfer from LOAN account.");
        }
        String type2 = customer2.getAccountTypeByAccountNumber(toAccountNumber);
        if (type2.equals(LOAN)) {
            throw new IllegalArgumentException("Cannot transfer into LOAN account.");
        }
        String toAbbr = customer.getAccountCurrencyAbbrByAccountNumber(fromAccountNumber);
        double fee = getTransferFeeByCurrencyAbbr(toAbbr);
        customer.transferFromAccount(fromAccountNumber, value, day, month, year, fee, toAccountNumber);
        String abbr2 = customer2.getAccountCurrencyAbbrByAccountNumber(toAccountNumber);
        double value2 = exchangeRate.calculate(value, toAbbr, abbr2);
        customer2.transferIntoAccount(toAccountNumber, value2, day, month, year, fromAccountNumber);
    }

    // Customer functions
    public void setCustomerPassword(BankCustomer customer, String password) {
        if (!password.equals(customer.getPassword()))
            customer.setPassword(password);
    }

    public void setCustomerEmail(BankCustomer customer, String email) {
        if (!email.equals(customer.getEmail())) {
            checkEmail(email);
            customer.setEmail(email);
        }
    }

    public void setCustomerPhone(BankCustomer customer, String phone) {
        String original = customer.getPhoneNumber();
        original = original.replaceAll("\\(", "");
        original = original.replaceAll("\\)", "");
        original = original.replaceAll("-", "");
        if (!phone.equals(original))
            customer.setPhoneNumber(phone);
    }

    public void setCustomerAddress1(BankCustomer customer, String address1) {
        if (!address1.equals(customer.getAddressAddress1()))
            customer.setAddress1(address1);
    }

    public void setCustomerAddress2(BankCustomer customer, String address2) {
        if (!address2.equals(customer.getAddressAddress2()))
            customer.setAddress2(address2);
    }

    public void setCustomerCity(BankCustomer customer, String city) {
        if (!city.equals(customer.getAddressCity()))
            customer.setCity(city);
    }

    public void setCustomerState(BankCustomer customer, String state) {
        if (!state.equals(customer.getAddressState()))
            customer.setState(state);
    }

    public void setCustomerCountry(BankCustomer customer, String country) {
        if (!country.equals(customer.getAddressCountry()))
            customer.setCountry(country);
    }

    public void setCustomerZipCode(BankCustomer customer, String zipCode) {
        if (!zipCode.equals(customer.getAddressZipCode()))
            customer.setZipCode(zipCode);
    }

    // helper functions
    public String generateRandomCustomerNumber() {
        Random rand = new Random();
        int numberInt = rand.nextInt(1000000000);
        String number = String.format("%09d", numberInt);
        while (!checkCustomerNumber(number)) {
            numberInt = rand.nextInt(1000000000);
            number = String.format("%09d", numberInt);
        }
        return number;
    }

    public String generateRandomAccountNumber() {
        String number = BankNumber.generateRandomNumber();
        while (!checkAccountNumber(number))
            number = BankNumber.generateRandomNumber();
        return number;
    }

    public double getOpenAccountFeeByCurrency(String currency) {
        double fee = chargeStandard.getOpenAccountFeeValue();
        String fromAbbr = chargeStandard.getOpenAccountFeeAbbr();
        String toAbbr = CurrencyAbbrs.getAbbr(currency);
        return exchangeRate.calculate(fee, fromAbbr, toAbbr);
    }

    public double getCheckingDepositFeeByCurrencyAbbr(String toAbbr) {
        double fee = chargeStandard.getCheckingDepositFeeValue();
        String fromAbbr = chargeStandard.getCheckingDepositFeeAbbr();
        return exchangeRate.calculate(fee, fromAbbr, toAbbr);
    }

    public double getCheckingWithdrawFeeByCurrencyAbbr(String toAbbr) {
        double fee = chargeStandard.getCheckingWithdrawFeeValue();
        String fromAbbr = chargeStandard.getCheckingWithdrawFeeAbbr();
        return exchangeRate.calculate(fee, fromAbbr, toAbbr);
    }

    public double getTransferFeeByCurrencyAbbr(String toAbbr) {
        double fee = chargeStandard.getTransferFeeValue();
        String fromAbbr = chargeStandard.getTransferFeeAbbr();
        return exchangeRate.calculate(fee, fromAbbr, toAbbr);
    }

    public double getCloseAccountFeeByCurrencyAbbr(String toAbbr) {
        double fee = chargeStandard.getCloseAccountFeeValue();
        String fromAbbr = chargeStandard.getCloseAccountFeeAbbr();
        return exchangeRate.calculate(fee, fromAbbr, toAbbr);
    }

    // check functions
    public void checkUsername(String username) {
        for (BankCustomer b: customers) {
            if (username.equals(b.getUsername())) {
                String alert = String.format("\"%s\" has been used.", username);
                throw new IllegalArgumentException(alert);
            }
        }
    }

    public void checkEmail(String email) {
        for (BankCustomer b: customers) {
            if (email.equals(b.getEmail())) {
                String alert = String.format("\"%s\" has been used.", email);
                throw new IllegalArgumentException(alert);
            }
        }
    }

    public boolean checkCustomerNumber(String number) {
        for (BankCustomer b: customers) {
            if (number.equals(b.getCustomerNumber()))
                return false;
        }
        return true;
    }

    public boolean checkAccountNumber(String number) {
        for (BankCustomer b: customers) {
            if (!b.checkEligibleAccountNumber(number))
                return false;
        }
        return true;
    }
}
