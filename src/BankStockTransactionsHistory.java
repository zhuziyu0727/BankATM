import java.util.ArrayList;

public class BankStockTransactionsHistory {

	private ArrayList<BankStockTransaction> stockHistory;
	
	public BankStockTransactionsHistory() {
		stockHistory = new ArrayList<>();
	}
    // accessor function
	//String[] column = {"DATE", "FROM", "TO", "STOCKID", "UNITPRICE", "NUMBER OF SHARES"};
    public String[][] getData() {
        String[][] data = new String[stockHistory.size()][6];
        int count = 0;
        for (BankStockTransaction stockTransaction: stockHistory) {
            String[] row = new String[5];
            row[0] = stockTransaction.getDate();
            row[1] = stockTransaction.getFrom();
            row[2] = stockTransaction.getTo();
            row[3] = stockTransaction.getStockidAttr();
            row[4] = stockTransaction.getUnitPriceAttr();
            row[5] = stockTransaction.getNumberOfSharesAttr();
            data[count++] = row;
        }
        return data;
    }

    // mutator function
    public void addNewStockTransaction(int day, int month, int year, int stockid, 
    								Money unitPrice, int numberOfShares, String from, String to) {
    	BankStockTransaction st = new BankStockTransaction();
    	st.setTransactionDate(day, month, year);
    	st.setUnitPrice(unitPrice);
    	st.setStockid(stockid);
    	st.setNumberOfShares(numberOfShares);
    	st.setFrom(from);
    	st.setTo(to);
        stockHistory.add(st);
    }
}
