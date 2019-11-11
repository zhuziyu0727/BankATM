import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public interface SecurityDataOperation {
	public static void sqliteWrite() throws Exception { // from memory to database
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS stockHolding");
		stmt.executeUpdate("DROP TABLE IF EXISTS securityAccount");
		stmt.executeUpdate("DROP TABLE IF EXISTS stockTransactions");
		Bank bank = Bank.getInstance();
		ArrayList<BankCustomer> customers = bank.getCustomers();

		stmt.executeUpdate(
				"CREATE TABLE securityAccount(routingNumber STRING, accountNumber STRING, MoneyAmount DOUBLE, currencyType STRING, OpenDay INT, OpenMonth INT, OpenYear INT, lastUpdateDay INT, lastUpdateMonth INT, lastUpdateYear INT, bindedSavingAccountNumber STRING, NumTransaction INT, NumStockHolding INT)");

		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				if (account.getType().equals(BankAccountTypes.SECURITY)) {
					stmt.executeUpdate("INSERT INTO securityAccount VALUES( '" + account.getRoutingNumber() + "' , '"
							+ account.getAccountNumber() + "', '" + account.getBalance() + "', '"
							+ account.getCurrencyType() + "', '" + account.getOpenDateObject().getDay() + "', '"
							+ account.getOpenDateObject().getMonth() + "', '" + account.getOpenDateObject().getYear()
							+ "', '" + account.getLastUpdateDateObject().getDay() + "' , '"
							+ account.getLastUpdateDateObject().getMonth() + "' , '"
							+ account.getLastUpdateDateObject().getYear() + "','"
							+ ((BankAccountSecurity) account).getBindedSavingAccountNumber().getCode() + "', '"
							+ ((BankAccountSecurity) account).getNumStockTransaction() + "', '"
							+ ((BankAccountSecurity) account).getNumStockHolding() + "')");
				}
			}
		}

		stmt.executeUpdate("CREATE TABLE stockHolding(Id INT, Name TEXT, buyPrice DOUBLE)");
		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				if (account.getType().equals(BankAccountTypes.SECURITY)) {
					for (Stock stock : ((BankAccountSecurity) account).getStockHolding()) {
						stmt.executeUpdate("INSERT INTO stockHolding VALUES('" + stock.getId() + "', '"
								+ stock.getName() + "', '" + stock.getBuyPriceAmount() + "')");
					}
				}
			}
		}

		stmt.executeUpdate(
				"CREATE TABLE stockTransactions(transactionDay INT, transactionMonth INT, transactionYear INT, Currency STRING, FromAccount STRING, ToAccount STRING, stockId INT, unitPrice DOUBLE, numOfShares INT)");
		for (BankCustomer customer : customers) {
			for (BankAccount account : customer.getAccounts()) {
				if (account.getType().equals(BankAccountTypes.SECURITY)) {
					for (BankStockTransaction stockTransaction : ((BankAccountSecurity) account)
							.getStockTransaction()) {
						stmt.executeUpdate(
								"INSERT INTO stockTransactions VALUES( '" + stockTransaction.getDateObject().getDay()
										+ "' , '" + stockTransaction.getDateObject().getMonth() + "', '"
										+ stockTransaction.getDateObject().getYear() + "', '"
										+ stockTransaction.getCurrency().getType() + "', '" + stockTransaction.getFrom()
										+ "', '" + stockTransaction.getTo() + "', '" + stockTransaction.getStockid()
										+ "', '" + stockTransaction.getUnitPrice().getAmount() + "', '"
										+ stockTransaction.getNumberOfShares() + "')");
					}
				}
			}
		}
		stmt.close();
		conn.close();
	}

	public static void sqliteRead() throws Exception {
		int securityAccountAttribute = 13, stockHoldingAttribute = 3, stockTransactionAttribute = 9,
				numStockHolding = 0, numSecurityAccount = 0, numStockTransaction = 0;
		Bank bank = Bank.getInstance();
		ArrayList<BankCustomer> customers = bank.getCustomers();
		Class.forName("org.sqlite.JDBC");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:db/test.db");
		Statement stmt = conn.createStatement();
		if (!DataOperation.judgeTableExists("securityAccount") || !DataOperation.judgeTableExists("stockTransactions")
				|| !DataOperation.judgeTableExists("stockHolding"))
			return;
		ResultSet rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM stockHolding");
		if (rs.next()) {
			numStockHolding = rs.getInt(1);
		}

		rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM securityAccount");
		if (rs.next()) {
			numSecurityAccount = rs.getInt(1);
		}
		rs = stmt.executeQuery("SELECT COUNT(*) totalCount FROM stockTransactions");
		if (rs.next()) {
			numStockTransaction = rs.getInt(1);
		}
		String[][] stockHoldingTable = new String[numStockHolding][stockHoldingAttribute];
		String[][] stockTransactionTable = new String[numStockTransaction][stockTransactionAttribute];
		String[][] securityAccountTable = new String[numSecurityAccount][securityAccountAttribute];
		rs = stmt.executeQuery("SELECT * FROM stockHolding");
		for (int i = 0; i < numStockHolding; i++) {
			if (rs.next()) {
				stockHoldingTable[i][0] = String.valueOf(rs.getInt("Id"));
				stockHoldingTable[i][1] = rs.getString("Name");
				stockHoldingTable[i][2] = String.valueOf(rs.getDouble("buyPrice"));

			}
		}
		rs = stmt.executeQuery("SELECT * FROM stockTransactions");
		for (int i = 0; i < numStockTransaction; i++) {
			if (rs.next()) {
				stockTransactionTable[i][0] = String.valueOf(rs.getInt("transactionDay"));
				stockTransactionTable[i][1] = String.valueOf(rs.getInt("transactionMonth"));
				stockTransactionTable[i][2] = String.valueOf(rs.getInt("transactionYear"));
				stockTransactionTable[i][3] = rs.getString("Currency");
				stockTransactionTable[i][4] = rs.getString("FromAccount");
				stockTransactionTable[i][5] = rs.getString("ToAccount");
				stockTransactionTable[i][6] = String.valueOf(rs.getInt("stockId"));
				stockTransactionTable[i][7] = String.valueOf(rs.getDouble("unitPrice"));
				stockTransactionTable[i][8] = String.valueOf(rs.getInt("numOfShares"));
			}
		}
		rs = stmt.executeQuery("SELECT * FROM securityAccount");
		for (int i = 0; i < numSecurityAccount; i++) {
			if (rs.next()) {
				securityAccountTable[i][0] = rs.getString("routingNumber");
				securityAccountTable[i][1] = (rs.getString("accountNumber"));
				securityAccountTable[i][2] = String.valueOf(rs.getDouble("MoneyAmount"));
				securityAccountTable[i][3] = (rs.getString("currencyType"));
				securityAccountTable[i][4] = String.valueOf(rs.getInt("OpenDay"));
				securityAccountTable[i][5] = String.valueOf(rs.getInt("OpenMonth"));
				securityAccountTable[i][6] = String.valueOf(rs.getInt("OpenYear"));
				securityAccountTable[i][7] = String.valueOf(rs.getInt("lastUpdateDay"));
				securityAccountTable[i][8] = String.valueOf(rs.getInt("lastUpdateMonth"));
				securityAccountTable[i][9] = String.valueOf(rs.getInt("lastUpdateYear"));
				securityAccountTable[i][10] = (rs.getString("bindedSavingAccountNumber"));
				securityAccountTable[i][11] = String.valueOf(rs.getInt("NumTransaction"));
				securityAccountTable[i][12] = String.valueOf(rs.getInt("NumStockHolding"));
			}
		}
		int rowSecurityAccount = 0, rowstockHolding = 0, rowStockTransaction = 0;
		for (int i = 0; i < customers.size(); i++) {
			for (int j = 0; j < customers.get(i).getNumOfSecurityAccount(); j++) {
				if (rowSecurityAccount < numSecurityAccount) {
					BankAccountSecurity bankAccountSecurity = new BankAccountSecurity();
					bankAccountSecurity.setCurrency(securityAccountTable[rowSecurityAccount][3]);
					bankAccountSecurity.open(Integer.parseInt(securityAccountTable[rowSecurityAccount][4]),
							Integer.parseInt(securityAccountTable[rowSecurityAccount][5]),
							Integer.parseInt(securityAccountTable[rowSecurityAccount][6]));
					bankAccountSecurity.setRoutingNumber(securityAccountTable[rowSecurityAccount][0]);
					bankAccountSecurity.setAccountNumber(securityAccountTable[rowSecurityAccount][1]);
					bankAccountSecurity.setBalance(Double.parseDouble(securityAccountTable[rowSecurityAccount][2]));
					bankAccountSecurity.setType(BankAccountTypes.SECURITY);
					bankAccountSecurity.setLastUpdateDate(Integer.parseInt(securityAccountTable[rowSecurityAccount][7]),
							Integer.parseInt(securityAccountTable[rowSecurityAccount][8]),
							Integer.parseInt(securityAccountTable[rowSecurityAccount][9]));
					bankAccountSecurity.setBindedSavingAccountNumber(securityAccountTable[rowSecurityAccount][10]);
					for (int q = 0; q < Integer.parseInt(securityAccountTable[rowSecurityAccount][11]); q++) {
						if (rowStockTransaction < numStockTransaction) {
							BankStockTransaction transaction = new BankStockTransaction();
							transaction.setTransactionDate(
									Integer.parseInt(stockTransactionTable[rowStockTransaction][0]),
									Integer.parseInt(stockTransactionTable[rowStockTransaction][1]),
									Integer.parseInt(stockTransactionTable[rowStockTransaction][2]));
							transaction.setCurrency(stockTransactionTable[rowStockTransaction][3]);
							transaction.setFrom(stockTransactionTable[rowStockTransaction][4]);
							transaction.setTo(stockTransactionTable[rowStockTransaction][5]);
							transaction.setStockid(Integer.parseInt(stockTransactionTable[rowStockTransaction][6]));
							transaction.setUnitPriceAmount(
									Integer.parseInt(stockTransactionTable[rowStockTransaction][7]));
							transaction
									.setNumberOfShares(Integer.parseInt(stockTransactionTable[rowStockTransaction][8]));
							bankAccountSecurity.getStockTransaction().add(transaction);
							rowStockTransaction++;
						}
					}
					for (int q = 0; q < Integer.parseInt(securityAccountTable[rowSecurityAccount][12]); q++) {
						if (rowstockHolding < numStockHolding) {
							Stock newStock = new Stock(Integer.parseInt(stockHoldingTable[rowstockHolding][0]),
									stockHoldingTable[rowstockHolding][1]);
							Money money = new Money();
							money.setAmount(Double.parseDouble(stockHoldingTable[rowstockHolding][2]));
							newStock.setBuyPrice(money);
							bankAccountSecurity.getStockHolding().add(newStock);
							rowstockHolding++;
						}
					}
					rowSecurityAccount++;
				}
			}
		}
		stmt.close();
		conn.close();
	}
}
