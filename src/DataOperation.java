
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface DataOperation {
	public static void sqliteWrite() throws Exception {            //from memory to database
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS customer");
		stmt.executeUpdate("DROP TABLE IF EXISTS account");
		stmt.executeUpdate("DROP TABLE IF EXISTS transactions");
		Bank bank = Bank.getInstance();
		ArrayList<BankCustomer> customers = bank.getCustomers();

		stmt.executeUpdate(
				"CREATE TABLE customer(lastName STRING, firstName STRING, middleName STRING, nickName STRING, Day INT, Month INT, Year INT, Email STRING, Username STRING, Password STRING, PhoneNumber STRING, Address1 STRING, Address2 STRING, City STRING, State STRING, Zipcode TEXT, CustomerNumber STRING, NumAccount INT)");

		for (BankCustomer customer : customers) {
			stmt.executeUpdate("INSERT INTO customer VALUES( '" + customer.getNameObject().getLastName() + "' , '"
					+ customer.getNameObject().getFirstName() + "', '" + customer.getNameObject().getMiddleName()
					+ "', '" + customer.getNameObject().getNickName() + "', '" + customer.getDOBObject().getDay()
					+ "', '" + customer.getDOBObject().getMonth() + "', '" + customer.getDOBObject().getYear() + "', '"
					+ customer.getEmail() + "' , '" + customer.getUsername() + "' , '" + customer.getPassword() + "','"
					+ customer.getPhoneNumber() + "' , '" + customer.getAddressAddress1() + "' , '"
					+ customer.getAddressAddress2() + "' , '" + customer.getAddressCity() + "', '"
					+ customer.getAddressState() + "', '" + customer.getAddressZipCode() + "', '"
					+ customer.getCustomerNumber() + "', '" + customer.getNumOfAccount() + "')");
		}
		stmt.executeUpdate(
				"CREATE TABLE account(routingNumber STRING, accountNumber STRING, MoneyAmount DOUBLE, currencyType STRING, OpenDay INT, OpenMonth INT, OpenYear INT, lastUpdateDay INT, lastUpdateMonth INT, lastUpdateYear INT, type STRING, NumTransaction INT)");
		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				stmt.executeUpdate("INSERT INTO account VALUES( '" + account.getRoutingNumber() + "' , '"
						+ account.getAccountNumber() + "', '" + account.getBalance() + "', '"
						+ account.getCurrencyType() + "', '" + account.getOpenDateObject().getDay() + "', '"
						+ account.getOpenDateObject().getMonth() + "', '" + account.getOpenDateObject().getYear()
						+ "', '" + account.getLastUpdateDateObject().getDay() + "' , '"
						+ account.getLastUpdateDateObject().getMonth() + "' , '"
						+ account.getLastUpdateDateObject().getYear() + "','" + account.getType() + "', '"
						+ account.getNumTransaction() + "')");
			}
		}

		stmt.executeUpdate(
				"CREATE TABLE transactions(transactionDay INT, transactionMonth INT, transactionYear INT, Currency STRING, Amount DOUBLE, Available DOUBLE, FromAccount STRING, ToAccount STRING)");
		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				for (BankTransaction transaction : account.getTransactionHistoryObject().getHistory()) {
					stmt.executeUpdate("INSERT INTO transactions VALUES( '" + transaction.getDateObject().getDay()
							+ "' , '" + transaction.getDateObject().getMonth() + "', '"
							+ transaction.getDateObject().getYear() + "', '" + transaction.getCurrency().getType()
							+ "', '" + transaction.getAmount() + "', '" + transaction.getAvailable() + "', '"
							+ transaction.getFrom() + "', '" + transaction.getTo() + "')");
				}
			}
		}
		stmt.close();
		conn.close();
	}

	public static void sqliteRead() throws Exception {
		int customerAttribute = 18, accountAttribute = 12, transactionAttribute = 8, numCustomer = 0, numAccount = 0, numTransaction = 0;
		Bank bank = Bank.getInstance();
		ArrayList<BankCustomer> customers = bank.getCustomers();
		
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM customer");
		if (rs.next()) {
			numCustomer = rs.getInt(1);
		}
		rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM account");
		if (rs.next()) {
			numAccount = rs.getInt(1);
		}
		rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM transactions");
		if (rs.next()) {
			numTransaction = rs.getInt(1);
		}
		String[][] customerTable = new String[numCustomer][customerAttribute];
		String[][] accountTable = new String[numAccount][accountAttribute];
		String[][] transactionTable = new String[numTransaction][transactionAttribute];
		//System.out.println(numCustomer + " " + numAccount + " " + numTransaction);
		if (judgeTableExists("customer") && judgeTableExists("account") && judgeTableExists("transactions")) {
			rs = stmt.executeQuery("SELECT * FROM customer");
			for (int i = 0; i < numCustomer; i++) {
				if (rs.next()) {
					customerTable[i][0] = rs.getString("lastName");
					customerTable[i][1] = (rs.getString("firstName"));
					customerTable[i][2] = (rs.getString("middleName"));
					customerTable[i][3] = (rs.getString("nickName"));
					customerTable[i][4] = String.valueOf(rs.getInt("Day"));
					customerTable[i][5] = String.valueOf(rs.getInt("Month"));
					customerTable[i][6] = String.valueOf(rs.getInt("Year"));
					customerTable[i][7] = (rs.getString("Email"));
					customerTable[i][8] = (rs.getString("Username"));
					customerTable[i][9] = (rs.getString("Password"));
					customerTable[i][10] = (rs.getString("PhoneNumber"));
					customerTable[i][11] = (rs.getString("Address1"));
					customerTable[i][12] = (rs.getString("Address2"));
					customerTable[i][13] = (rs.getString("City"));
					customerTable[i][14] = (rs.getString("State"));
					customerTable[i][15] = (rs.getString("Zipcode"));
					customerTable[i][16] = (rs.getString("CustomerNumber"));
					customerTable[i][17] = String.valueOf(rs.getInt("NumAccount"));
				}
			}
			rs = stmt.executeQuery("SELECT * FROM account");
			for (int i = 0; i < numAccount; i++) {
				if (rs.next()) {
					accountTable[i][0] = rs.getString("routingNumber");
					accountTable[i][1] = (rs.getString("accountNumber"));
					accountTable[i][2] = String.valueOf(rs.getDouble("MoneyAmount"));
					accountTable[i][3] = (rs.getString("currencyType"));
					accountTable[i][4] = String.valueOf(rs.getInt("OpenDay"));
					accountTable[i][5] = String.valueOf(rs.getInt("OpenMonth"));
					accountTable[i][6] = String.valueOf(rs.getInt("OpenYear"));
					accountTable[i][7] = String.valueOf(rs.getInt("lastUpdateDay"));
					accountTable[i][8] = String.valueOf(rs.getInt("lastUpdateMonth"));
					accountTable[i][9] = String.valueOf(rs.getInt("lastUpdateYear"));
					accountTable[i][10] = (rs.getString("type"));
					accountTable[i][11] = String.valueOf(rs.getInt("NumTransaction"));
				}
			}
			rs = stmt.executeQuery("SELECT * FROM transactions");
			for (int i = 0; i < numTransaction; i++) {
				if (rs.next()) {
					transactionTable[i][0] = String.valueOf(rs.getInt("transactionDay"));
					transactionTable[i][1] = String.valueOf(rs.getInt("transactionMonth"));
					transactionTable[i][2] = String.valueOf(rs.getInt("transactionYear"));
					transactionTable[i][3] = (rs.getString("Currency"));
					transactionTable[i][4] = String.valueOf(rs.getDouble("Amount"));
					transactionTable[i][5] = String.valueOf(rs.getDouble("Available"));
					transactionTable[i][6] = (rs.getString("FromAccount"));
					transactionTable[i][7] = (rs.getString("ToAccount"));
				}
			}
			int rowAccount = 0, rowTransaction = 0;
			for (int i = 0; i < numCustomer; i++) {
				BankCustomer customer = new BankCustomer(customerTable[i][16]);
				customer.setLastName(customerTable[i][0]);
				customer.setFirstName(customerTable[i][1]);
				customer.setMiddleName(customerTable[i][2]);
				customer.setNickName(customerTable[i][3]);
				customer.setDay(Integer.parseInt(customerTable[i][4]));
				customer.setMonth(Integer.parseInt(customerTable[i][5]));
				customer.setYear(Integer.parseInt(customerTable[i][6]));
				customer.setEmail(customerTable[i][7]);
				customer.setUsername(customerTable[i][8]);
				customer.setPassword(customerTable[i][9]);
				if(!customerTable[i][10].equals(""))
					customer.setPhoneNumber(customerTable[i][10]);
				customer.setAddress1(customerTable[i][11]);
				customer.setAddress2(customerTable[i][12]);
				customer.setCity(customerTable[i][13]);
				if(!customerTable[i][14].equals(""))
					customer.setState(customerTable[i][14]);
				if(!customerTable[i][15].equals(""))
					customer.setZipCode(customerTable[i][15]);
				for(int j = 0; j < Integer.parseInt(customerTable[i][17]); j++){
					if(rowAccount < numAccount) {
						BankAccount account = new BankAccount(accountTable[rowAccount][3]);
						account.open(Integer.parseInt(accountTable[rowAccount][4]), Integer.parseInt(accountTable[rowAccount][5]),
								Integer.parseInt(accountTable[rowAccount][6]));
						account.setRoutingNumber(accountTable[rowAccount][0]);
						account.setAccountNumber(accountTable[rowAccount][1]);
						account.setBalance(Double.parseDouble(accountTable[rowAccount][2]));
						account.setType(accountTable[rowAccount][10]);
						account.setLastUpdateDate(Integer.parseInt(accountTable[rowAccount][7]),
								Integer.parseInt(accountTable[rowAccount][8]),Integer.parseInt(accountTable[rowAccount][9]));
						for (int q = 0; q < Integer.parseInt(accountTable[rowAccount][11]); q++) {
							if(rowTransaction < numTransaction) {
								BankTransaction transaction = new BankTransaction();
								transaction.setTransactionDate(Integer.parseInt(transactionTable[rowTransaction][0]),
										Integer.parseInt(transactionTable[rowTransaction][1]),
										Integer.parseInt(transactionTable[rowTransaction][2]));
								transaction.setCurrency(transactionTable[rowTransaction][3]);
								transaction.setAmount(Double.parseDouble(transactionTable[rowTransaction][4]));
								transaction.setAvailable(Double.parseDouble(transactionTable[rowTransaction][5]));
								transaction.setFrom(transactionTable[rowTransaction][6]);
								transaction.setTo(transactionTable[rowTransaction][7]);
								account.getTransactionHistoryObject().getHistory().add(transaction);
								rowTransaction++;
							}
						}
						customer.getAccounts().add(account);
						rowAccount++;
					}
				}
				bank.getCustomers().add(customer);
			}
			
		}
		stmt.close();
		conn.close();
	}

	public static boolean judgeTableExists(String tableName) throws Exception {
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt
				.executeQuery("select count(*)  from sqlite_master where type='table' and name = '" + tableName + "'"); // judge
																														// whether
																														// customer
																														// table
																														// exists
		if (rs.next()) {
			if (rs.getInt(1) > 0) {
				conn.close();
				stmt.close();
				return true;
			}
		}
		conn.close();
		stmt.close();
		return false;
	}
}
