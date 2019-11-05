import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUIBankManagerPanelSearchCustomerEvent extends GUIBankEvent {
	private JLabel message;
	private JTextField textfield;
	private GUIBankPanel transcationpanel;
	
	public GUIBankManagerPanelSearchCustomerEvent(GUIBankATMFrame frame, JTextField textfield, GUIBankPanel transcationpanel, JLabel message) {
		super(frame);
		this.textfield = textfield;
		this.transcationpanel = transcationpanel;
		this.message = message;
	}

	public void actionPerformed(ActionEvent e) {
		Bank bank = Bank.getInstance();
        String[] column = {"CUSTOMER", "ACCOUNT", "TYPE", "DATE", "FROM", "TO", "AMOUNT", "AVAILABLE"};
        int totalSize = 0;
        String username = textfield.getText();
        if(!bank.hasCustomerByUsername(username)) {
        	message.setText("Invalid username");
        	return;
        }
        BankCustomer customer = bank.getCustomerByUsername(textfield.getText());
        
        ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
        for (String accountNumber: allAccountNumbers) {
        	String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
        	totalSize += data.length;
        }
        
        String[][] wholeData = new String[totalSize][8];
        int rowNumber = 0;
        String customerNumber = bank.getCustomerNumberByCustomer(customer);
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
        JTable wholeTransactions = new JTable(wholeData, column);
        transcationpanel.removeAll();
        transcationpanel.add(new JScrollPane(wholeTransactions));
        transcationpanel.revalidate();
        transcationpanel.repaint();
       
	}

}
