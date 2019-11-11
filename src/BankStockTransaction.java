import java.text.DecimalFormat;

public class BankStockTransaction extends BankTransaction {
	
	private int stockid;
	private Money unitPrice;
	private int numberOfShares;
	
	public BankStockTransaction() {
		super();
	}
	
	// accessor functions
    public int getStockid() {
    	return this.stockid;
    }
    
    public String getStockidAttr() {
    	return Integer.toString(stockid);
    }
    
    public Money getUnitPrice() {
    	return this.unitPrice;
    }
    
    public String getUnitPriceAttr() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(unitPrice.getAmount());
    }
    
    public int getNumberOfShares() {
    	return numberOfShares;
    }
    
    public String getNumberOfSharesAttr() {
    	return Integer.toString(numberOfShares);
    }
    
    // mutator functions
    public void setStockid(int id) {
        this.stockid = id;
    }
    
    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public void setUnitPriceAmount(double unitPrice) {
    	this.unitPrice.setAmount(unitPrice);
    }
    
    public void setNumberOfShares(int number) {
        this.numberOfShares = number;
    }
}
