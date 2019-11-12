import java.util.ArrayList;
import java.util.Collections;

public class BankAccountSecurity extends BankAccount {

	private BankNumberAccount bindedSavingAccountNumber;
	private ArrayList<Stock> stockHolding;
	private BankStockTransactionsHistory stockTransactions;
	
	// constructors
	public BankAccountSecurity() {
		super();
	}
	
	public BankAccountSecurity(BankNumberAccount savingAccount) {
		this();
		this.bindedSavingAccountNumber = savingAccount;
		this.stockHolding = new ArrayList<Stock>();
		setType(SECURITY);
	}
	// accessor functions
	public BankNumberAccount getBindedSavingAccountNumber() {
		return this.bindedSavingAccountNumber;
	}
	
	public ArrayList<Stock> getStockHolding(){
		return this.stockHolding;
	}
		
	public int getNumStockTransaction() {
		return this.stockTransactions.getStockHistory().size();
	}
	
	public ArrayList<BankStockTransaction> getStockTransaction(){
		return this.stockTransactions.getStockHistory();
	}
	
	public int getNumStockHolding() {
		return this.stockHolding.size();
	}
	
	public double getTotalStockValue() {
		updateStockBalance();
		return this.getBalance();
	}
	
	public String[][] getStockTransactionHistory() {
		return stockTransactions.getData();
	}
	//mutator functions
	public void setBindedSavingAccountNumber(String BindedSavingAccountNumber) {
		this.bindedSavingAccountNumber.setCode(BindedSavingAccountNumber);
	}
	// customer functions
	public void buyStock(Stock stock, int amount, int day, int month, int year) {
		if(amount < 0) {
			throw new NumberFormatException();
		}
		Stock newStock = new Stock(stock.getId(),stock.getName());
		newStock.setBuyPrice(stock.getSellPrice());
		for(int i = 0; i < amount; i++) {
			stockHolding.add(newStock);
		}
		addOneStockTransaction(day, month, year, stock.getId(), stock.getBuyPrice(), amount, "stockmarket", "");
		Collections.sort(stockHolding, new StockComparator());
	}
	
	public void sellStock(Stock stock, int number, int day, int month, int year) {
		if(getStockVolumeByStockId(stock.getId()) < number) {
			throw new NumberFormatException();
		}
		while(number > 0) {
			for(int i= 0; i < stockHolding.size(); i++) {
				if(stockHolding.get(i).getId() == stock.getId()) {
					stockHolding.remove(i--);
					number--;
				}
			}
		}
		addOneStockTransaction(day, month, year, stock.getId(), stock.getSellPrice(), number, "Customer", "");
		Collections.sort(stockHolding, new StockComparator());
	}
	
	public double getAvgBoughtPriceByStock (String stockId) {
		int stockid = Integer.parseInt(stockId);
		if(getStockVolumeByStockId(stockid) == 0) {
			return 0;
		}else {
			return getStockAmountByStockId(stockid) / getStockVolumeByStockId(stockid);
		}
	}
	
	public double getSellAmount(Stock stock, int sharesNum) {
		return (double) stock.getSellPriceAmount() * sharesNum;
	}
	
	public double getBuyAmount(Stock stock, int sharesNum) {
		return (double) stock.getBuyPriceAmount() * sharesNum;
	}
	
    public void addOneStockTransaction(int day, int month, int year, int stockid, Money unitPrice, int numberOfShares, String from, String to) {
        checkFromTo(from, to);
        if (!from.equals("")) {
        	stockTransactions.addNewStockTransaction(day, month, year, stockid, unitPrice, numberOfShares, from , to);
        } else {
        	stockTransactions.addNewStockTransaction(day, month, year, stockid, unitPrice, numberOfShares, from , to);
        }
        setLastUpdateDate(day, month, year);
    };
    
    public String[][] getMyStocks(String[][] stockList){
    //String[] column = {"STOCKID", "STOCKNAME ", "MYSTOCKCOUNTS"};
    	int n = stockList.length;
    	if(n == 0) {
    		String[][] data = new String[3][3]; 
    		return data;
    	}
    	if(stockList[0].length != 3) {
    		return stockList;
    	}else {
    		for(int i = 0; i < n; i++) {
    			int stockId = Integer.parseInt(stockList[i][0]);
    			stockList[i][2] = Integer.toString(getStockVolumeByStockId(stockId));
    		}
    	}
    	return stockList;
    }
    
	// helper functions
	public boolean isClosable() {
		return stockHolding.size() == 0;
	}
	
	public int getStockVolumeByStockId(int stockid) {
		int volume = 0;
		for(Stock stockinHand : stockHolding) {
			if(stockinHand.getId() == stockid)
				volume++;
		}
		return volume;
	}
	
	public double getStockAmountByStockId(int stockid) {
		double amount = 0;
		for(Stock stockinHand : stockHolding) {
			if(stockinHand.getId() == stockid)
				amount += stockinHand.getBuyPriceAmount();
		}
		return amount;
	}
	
	public void updateStockBalance() {
		double amount = 0;
		for(Stock stockinHand : stockHolding) {
				amount += stockinHand.getBuyPriceAmount();
		}
		this.setBalance(amount);
	}
}
