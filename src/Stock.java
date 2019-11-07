/**
 * One single stock.
 * It has a unique ID.
 * Assume there is a total number of each stock.
 */
public class Stock {
    // Private instance variables.
	
    private int id;
    private String name;
    private int totalNumber;
    private int availableNumber;
    private Money buyPrice;
    private Money sellPrice;
    public static int defaultTotal = 500;
    // Constructor.
    public Stock(int id, String name, int totalNumber) {
        this.id = id;
        this.name = name;
        this.totalNumber = totalNumber;
        initialPrice(totalNumber);
    }
    
    public Stock(int id, String name) {
        this.id = id;
        this.name = name;
        this.totalNumber = defaultTotal;
        initialPrice(defaultTotal);
    }

    // Functions used by the manager.
    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

    public void setBuyPrice(Money buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void setSellPrice(Money sellPrice) {
        this.sellPrice = sellPrice;
    }
    
    public void initialPrice(int initialNumber) {
    	double d = Math.random() * Math.abs(initialNumber - 2000);
    	this.buyPrice.setAmount(d);
    	d = d *Math.random();
    	this.sellPrice.setAmount(d);
    }
    
    public void refresh() {
    	double priceBuy = this.buyPrice.getAmount();
    	double priceSell = this.sellPrice.getAmount();
    	double percent = (1 - this.availableNumber/this.totalNumber);
    	this.buyPrice.setAmount(priceBuy*percent*(Math.random() - 0.5) * 2);
    	this.sellPrice.setAmount(priceSell*percent*(Math.random() - 0.5) * 2);
    }
    
    public void refresh(int number) {
    	double priceBuy = this.buyPrice.getAmount();
    	double priceSell = this.sellPrice.getAmount();
    	double percent = number/this.totalNumber;
    	this.buyPrice.setAmount(priceBuy*Math.random()*(1 + percent));
    	this.sellPrice.setAmount(priceSell*Math.random()*(1 - percent));
    }

    // When the manager wants to add more of this stock into market.
    public void addNumber(int number) {
        totalNumber += number;
        availableNumber += number;
    }

    // When a customer tries to purchase this stock.
    public void customerPurchase(int number) {
        assert number<=availableNumber: "Customer cannot buy more than available number of the stock.";
        this.refresh(number);
        availableNumber -= number;
    }

    // When a customer tries to sell this stock.
    public void customerSell(int number) {
    	this.refresh(-number);
        availableNumber += number;
    }

    public boolean isEqual(Stock other) {
        return id == other.id || name == other.name;
    }
    
    // Accessor functions
    public int getId() {
        return id;
    }

    public Money getBuyPrice() {
        return buyPrice;
    }

    public Money getSellPrice() {
        return sellPrice;
    }
    
    public int getTotalNumber() {
    	return this.totalNumber;
    }
    
    public int getAvailableNumber() {
    	return this.availableNumber;
    }
}
