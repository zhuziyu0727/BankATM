import javax.swing.*;
import java.util.ArrayList;

public class GUIBankManagerPanelTransactionsPanel extends GUIBankPanel {

    public GUIBankManagerPanelTransactionsPanel(GUIBankATMFrame frame) {
        super(frame);
        addElements();
    }

    public void addElements() {
        Bank bank = Bank.getInstance();

        String[] column = {"CUSTOMER", "ACCOUNT", "TYPE", "DATE", "FROM", "TO", "AMOUNT", "AVAILABLE"};
        int totalSize = 0;
        ArrayList<BankCustomer> customers = bank.getCustomers();
        for (BankCustomer customer: customers) {
            String customerNumber = bank.getCustomerNumberByCustomer(customer);
            ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
            for (String accountNumber: allAccountNumbers) {
                String type = bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber);
                String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
                totalSize += data.length;
            }
        }
        String[][] wholeData = new String[totalSize][8];
        int rowNumber = 0;
        for (BankCustomer customer: customers) {
            String customerNumber = bank.getCustomerNumberByCustomer(customer);
            ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
            for (String accountNumber: allAccountNumbers) {
                String type = bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber);
                String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
                for (int j=0; j<data.length; j++) {

                    String[] row = new String[8];
                    row[0] = customerNumber;
                    row[1] = accountNumber;
                    row[2] = type;
                    for (int i=0; i<5; i++) {
                        row[i+3] = data[j][i];
                    }
                    wholeData[rowNumber++] = row;
                }

            }
        }
        JTable wholeTransactions = new JTable(wholeData, column);
        add(new JScrollPane(wholeTransactions));
    }
}
