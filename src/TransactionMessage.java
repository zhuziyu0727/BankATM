import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public interface TransactionMessage {
	public static String[][] getData() {
		Bank bank = Bank.getInstance();
		int totalSize = 0;
		ArrayList<BankCustomer> customers = bank.getCustomers();
		for (BankCustomer customer : customers) {
			String customerNumber = bank.getCustomerNumberByCustomer(customer);
			ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
			for (String accountNumber : allAccountNumbers) {
				String type = bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber);
				String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
				totalSize += data.length;
			}
		}
		String[][] wholeData = new String[totalSize][8];
		int rowNumber = 0;
		for (BankCustomer customer : customers) {
			String customerNumber = bank.getCustomerNumberByCustomer(customer);
			ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
			for (String accountNumber : allAccountNumbers) {
				String type = bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber);
				String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
				for (int j = 0; j < data.length; j++) {

					String[] row = new String[8];
					row[0] = customerNumber;
					row[1] = accountNumber;
					row[2] = type;
					for (int i = 0; i < 5; i++) {
						row[i + 3] = data[j][i];
					}
					wholeData[rowNumber++] = row;
				}

			}

		}
		/*
		for (int i = 0; i < wholeData.length; i++) {
			for (int j = 0; j < wholeData[0].length; j++) {
				System.out.println(wholeData[i][j]);
			}
		}*/

		return wholeData;
	}

	public static void writeFile() throws IOException {
		String[][] wholeData = getData();
		File file = new File("TransactionMessage.txt");
		String messagesum = "";
		for (int i = 0; i < wholeData.length; i++) {
			for (int j = 0; j < wholeData[0].length; j++) {
				messagesum += wholeData[i][j] + " ";
			}
			messagesum += "\n";
		}
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file, true);
		StringBuffer sb = new StringBuffer();
		sb.append(messagesum + "\n"); 
		out.write(sb.toString().getBytes("gb2312")); 
		out.close(); 
	}
}
