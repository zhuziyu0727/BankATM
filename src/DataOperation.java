
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface DataOperation {
	public static void sqliteWrite() throws Exception { // from memory to database
		int numStock = 6; // by default, we create 6 stocks
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS customer");
		stmt.executeUpdate("DROP TABLE IF EXISTS account");
		stmt.executeUpdate("DROP TABLE IF EXISTS transactions");
		stmt.executeUpdate("DROP TABLE IF EXISTS stocks");
		Bank bank = Bank.getInstance();
		ArrayList<BankCustomer> customers = bank.getCustomers();
		ArrayList<Stock> market = bank.getStockMarket().saveData();

		stmt.executeUpdate(
				"CREATE TABLE stocks(Id INT, Name TEXT, totalNumber INT, availableNumber INT, buyPrice DOUBLE, sellPrice DOUBLE)");
		for (Stock stock : market) {
			stmt.executeUpdate("INSERT INTO stocks VALUES('" + stock.getId() + "', '" + stock.getName() + "', '"
					+ stock.getTotalNumber() + "', '" + stock.getAvailableNumber() + "', '" + stock.getBuyPriceAmount()
					+ "', '" + stock.getSellPriceAmount() + "')");
		}

		stmt.executeUpdate(
				"CREATE TABLE customer(lastName TEXT, firstName TEXT, middleName TEXT, nickName TEXT, Day INT, Month INT, Year INT, Email TEXT, Username TEXT, Password TEXT, PhoneNumber TEXT, Address1 TEXT, Address2 TEXT, City TEXT, State TEXT, Zipcode TEXT, CustomerNumber TEXT, NumAccount INT)");
		// NumAccount is getCustomerNumber() - getNumOfSecurityAccount()
		for (BankCustomer customer : customers) {
			stmt.executeUpdate("INSERT INTO customer VALUES( '" + customer.getNameObject().getLastName() + "' , '"
					+ customer.getNameObject().getFirstName() + "', '" + customer.getNameObject().getMiddleName()
					+ "', '" + customer.getNameObject().getNickName() + "', '" + customer.getDOBObject().getDay()
					+ "', '" + customer.getDOBObject().getMonth() + "', '" + customer.getDOBObject().getYear() + "', '"
					+ customer.getEmail() + "' , '" + customer.getUsername() + "' , '" + customer.getPassword() + "','"
					+ customer.getPhoneNumber() + "' , '" + customer.getAddressAddress1() + "' , '"
					+ customer.getAddressAddress2() + "' , '" + customer.getAddressCity() + "', '"
					+ customer.getAddressState() + "', '" + customer.getAddressZipCode() + "', '"
					+ customer.getCustomerNumber() + "', '"
					+ (customer.getNumOfAccount() - customer.getNumOfSecurityAccount()) + "')");
		}
		stmt.executeUpdate(
				"CREATE TABLE account(routingNumber TEXT, accountNumber TEXT, MoneyAmount DOUBLE, currencyType TEXT, OpenDay INT, OpenMonth INT, OpenYear INT, lastUpdateDay INT, lastUpdateMonth INT, lastUpdateYear INT, type TEXT, NumTransaction INT, bindedSecurityAccountNumber TEXT)");
		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				if (!account.getType().equals(BankAccountTypes.SECURITY)) {
					if(!account.getType().equals(BankAccountTypes.SAVING)) {
					stmt.executeUpdate("INSERT INTO account (routingNumber,accountNumber,MoneyAmount,currencyType,OpenDay, OpenMonth, OpenYear, lastUpdateDay, lastUpdateMonth, lastUpdateYear, type, NumTransaction) VALUES( '" + account.getRoutingNumber() + "' , '"
							+ account.getAccountNumber() + "', '" + account.getBalance() + "', '"
							+ account.getCurrencyType() + "', '" + account.getOpenDateObject().getDay() + "', '"
							+ account.getOpenDateObject().getMonth() + "', '" + account.getOpenDateObject().getYear()
							+ "', '" + account.getLastUpdateDateObject().getDay() + "' , '"
							+ account.getLastUpdateDateObject().getMonth() + "' , '"
							+ account.getLastUpdateDateObject().getYear() + "','" + account.getType() + "', '"
							+ account.getNumTransaction() + "')");
					}
					else if(account.getType().equals(BankAccountTypes.SAVING)){
						stmt.executeUpdate("INSERT INTO account VALUES( '" + account.getRoutingNumber() + "' , '"
								+ account.getAccountNumber() + "', '" + account.getBalance() + "', '"
								+ account.getCurrencyType() + "', '" + account.getOpenDateObject().getDay() + "', '"
								+ account.getOpenDateObject().getMonth() + "', '" + account.getOpenDateObject().getYear()
								+ "', '" + account.getLastUpdateDateObject().getDay() + "' , '"
								+ account.getLastUpdateDateObject().getMonth() + "' , '"
								+ account.getLastUpdateDateObject().getYear() + "','" + account.getType() + "', '"
								+ account.getNumTransaction() + "', '" + ((BankAccountSaving) account).getBindedSecurityAccountNumber() + "')");
					}
				}
			}
		}

		stmt.executeUpdate(
				"CREATE TABLE transactions(transactionDay INT, transactionMonth INT, transactionYear INT, Currency TEXT, Amount DOUBLE, Available DOUBLE, FromAccount TEXT, ToAccount TEXT)");
		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				if (!account.getType().equals(BankAccountTypes.SECURITY)) {
					for (BankTransaction transaction : account.getTransactionHistoryObject().getHistory()) {
						stmt.executeUpdate("INSERT INTO transactions VALUES( '" + transaction.getDateObject().getDay()
								+ "' , '" + transaction.getDateObject().getMonth() + "', '"
								+ transaction.getDateObject().getYear() + "', '" + transaction.getCurrency().getType()
								+ "', '" + transaction.getAmount() + "', '" + transaction.getAvailable() + "', '"
								+ transaction.getFrom() + "', '" + transaction.getTo() + "')");
					}
				}
			}
		}
		stmt.close();
		conn.close();
	}

	public static void sqliteRead() throws Exception {
		int stockAttribute = 6, customerAttribute = 18, accountAttribute = 13, transactionAttribute = 8,
				numCustomer = 0, numAccount = 0, numTransaction = 0, numStock = 0;
		Bank bank = Bank.getInstance();
		ArrayList<BankCustomer> customers = bank.getCustomers();
		//ArrayList<Stock> market = bank.getStockMarket().saveData();
		ArrayList<Stock> newMarket = new ArrayList<Stock>();
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		if (!judgeTableExists("customer") || !judgeTableExists("account") || !judgeTableExists("transactions"))
			return;
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
		rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM stocks");
		if (rs.next()) {
			numStock = rs.getInt(1);
		}
		String[][] customerTable = new String[numCustomer][customerAttribute];
		String[][] accountTable = new String[numAccount][accountAttribute];
		String[][] transactionTable = new String[numTransaction][transactionAttribute];
		String[][] stockTable = new String[numStock][stockAttribute];
		// System.out.println(numCustomer + " " + numAccount + " " + numTransaction);
		if (judgeTableExists("stocks")) {
			rs = stmt.executeQuery("SELECT * FROM stocks");
			for (int i = 0; i < numStock; i++) {
				if (rs.next()) {
					stockTable[i][0] = String.valueOf(rs.getInt("Id"));
					stockTable[i][1] = rs.getString("Name");
					stockTable[i][2] = String.valueOf(rs.getInt("totalNumber"));
					stockTable[i][3] = String.valueOf(rs.getInt("availableNumber"));
					stockTable[i][4] = String.valueOf(rs.getDouble("buyPrice"));
					stockTable[i][5] = String.valueOf(rs.getDouble("sellPrice"));
				}
				//System.out.println(market.size());
				//System.out.println(i);
				Stock stock = new Stock(Integer.parseInt(stockTable[i][0]), stockTable[i][1], Integer.parseInt(stockTable[i][2]));
				stock.setAvailableNumber(Integer.parseInt(stockTable[i][3]));
				stock.setBuyPriceAmount(Double.parseDouble(stockTable[i][4]));
				stock.setSellPriceAmount(Double.parseDouble(stockTable[i][5]));
				newMarket.add(stock);
			}
		}
		bank.getStockMarket().setMarket(newMarket);
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
				accountTable[i][12] = rs.getString("bindedSecurityAccountNumber");
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
			if (!customerTable[i][10].equals(""))
				customer.setPhoneNumber(customerTable[i][10]);
			customer.setAddress1(customerTable[i][11]);
			customer.setAddress2(customerTable[i][12]);
			customer.setCity(customerTable[i][13]);
			if (!customerTable[i][14].equals(""))
				customer.setState(customerTable[i][14]);
			if (!customerTable[i][15].equals(""))
				customer.setZipCode(customerTable[i][15]);
			for (int j = 0; j < Integer.parseInt(customerTable[i][17]); j++) {
				if (rowAccount < numAccount) {
					if(accountTable[rowAccount][12].equals(BankAccountTypes.SAVING)) {
					BankAccountSaving account = new BankAccountSaving(accountTable[rowAccount][3]);
					account.open(Integer.parseInt(accountTable[rowAccount][4]),
							Integer.parseInt(accountTable[rowAccount][5]),
							Integer.parseInt(accountTable[rowAccount][6]));
					account.setRoutingNumber(accountTable[rowAccount][0]);
					account.setAccountNumber(accountTable[rowAccount][1]);
					account.setBalance(Double.parseDouble(accountTable[rowAccount][2]));
					account.setType(accountTable[rowAccount][10]);
					account.setLastUpdateDate(Integer.parseInt(accountTable[rowAccount][7]),
							Integer.parseInt(accountTable[rowAccount][8]),
							Integer.parseInt(accountTable[rowAccount][9]));
					((BankAccountSaving) account).setBindedSecurityAccountNumber(accountTable[i][12]);
					for (int q = 0; q < Integer.parseInt(accountTable[rowAccount][11]); q++) {
						if (rowTransaction < numTransaction) {
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
					else {
						BankAccount account = new BankAccount(accountTable[rowAccount][3]);
						account.open(Integer.parseInt(accountTable[rowAccount][4]),
								Integer.parseInt(accountTable[rowAccount][5]),
								Integer.parseInt(accountTable[rowAccount][6]));
						account.setRoutingNumber(accountTable[rowAccount][0]);
						account.setAccountNumber(accountTable[rowAccount][1]);
						account.setBalance(Double.parseDouble(accountTable[rowAccount][2]));
						account.setType(accountTable[rowAccount][10]);
						account.setLastUpdateDate(Integer.parseInt(accountTable[rowAccount][7]),
								Integer.parseInt(accountTable[rowAccount][8]),
								Integer.parseInt(accountTable[rowAccount][9]));
						for (int q = 0; q < Integer.parseInt(accountTable[rowAccount][11]); q++) {
							if (rowTransaction < numTransaction) {
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
			}
			bank.getCustomers().add(customer);
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
