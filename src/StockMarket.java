import java.util.ArrayList;

/**
 * This is a stock market including many different stocks.
 * Stock market is only a single one.
 * Implement the singleton class design patter.
 */
public class StockMarket {
    private static StockMarket stockMarket;

    private ArrayList<Stock> market;

    private StockMarket() {
        market = new ArrayList<>();
    }

    public static StockMarket getInstance() {
        if (stockMarket == null) {
            stockMarket = new StockMarket();
        }
        return stockMarket;
    }

    // Check if the new add stock is unique.
    public boolean checkUnique(Stock other) {
        for (Stock stock: market) {
            if (stock.isEqual(other)) {
                return false;
            }
        }
        return true;
    }

    // When the manager wants to add a new stock.
    public boolean addStock(Stock stock) {
        if (checkUnique(stock)) {
            market.add(stock);
            return true;
        }
        return false;
    }

    public Stock getStockById(int stockId) {
        for (Stock stock: market) {
            if (stockId == stock.getId()) {
                return stock;
            }
        }
        return null;
    }

    // Functions used by customers.
    public void customerPurchase(Stock stock, int number) {
        stock.customerPurchase(number);
    }

    public void customerSell(Stock stock, int number) {
        stock.customerSell(number);
    }
}
