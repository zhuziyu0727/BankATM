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
	
	// customer functions
	public void buyStock(Stock stock, int amount) {
		if(amount < 0) {
			throw new NumberFormatException();
		}
		Stock newStock = new Stock(stock.getId(),stock.getName());
		newStock.setBuyPrice(stock.getSellPrice());
		for(int i = 0; i < amount; i++) {
			stockHolding.add(newStock);
		}
		Collections.sort(stockHolding, new StockComparator());
	}
	
	public void sellStock(Stock stock, int number) {
		if(getStockVolumeByStock(stock) < number) {
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
		Collections.sort(stockHolding, new StockComparator());
	}
	
	public double getAvgBoughtPriceByStock (Stock stock) {
		if(getStockVolumeByStock(stock) == 0) {
			return 0;
		}else {
			return getStockAmountByStock(stock) / getStockVolumeByStock(stock);
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
    
	// helper functions
	public boolean isClosable() {
		return stockHolding.size() == 0;
	}
	
	public int getStockVolumeByStock(Stock stock) {
		int volume = 0;
		for(Stock stockinHand : stockHolding) {
			if(stockinHand.getId() == stock.getId())
				volume++;
		}
		return volume;
	}
	
	public double getStockAmountByStock(Stock stock) {
		double amount = 0;
		for(Stock stockinHand : stockHolding) {
			if(stockinHand.getId() == stock.getId())
				amount += stockinHand.getBuyPriceAmount();
		}
		return amount;
	}
}
