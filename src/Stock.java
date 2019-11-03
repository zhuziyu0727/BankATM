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

    // Constructor.
    public Stock(int id, String name, int totalNumber) {
        this.id = id;
        this.name = name;
        this.totalNumber = totalNumber;
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

    // When the manager wants to add more of this stock into market.
    public void addNumber(int number) {
        totalNumber += number;
        availableNumber += number;
    }

    // When a customer tries to purchase this stock.
    public void customerPurchase(int number) {
        assert number<=availableNumber: "Customer cannot buy more than available number of the stock.";
        availableNumber -= number;
    }

    // When a customer tries to sell this stock.
    public void customerSell(int number) {
        availableNumber += number;
    }

    public boolean isEqual(Stock other) {
        return id == other.id;
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
}
