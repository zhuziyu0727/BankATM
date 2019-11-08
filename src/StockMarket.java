import java.util.ArrayList;
import java.util.List;

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
        //by default, we create 6 stocks
        market.add(new Stock(1020,"Boston University",500));
        market.add(new Stock(1021,"Harvard University",500));
        market.add(new Stock(1022,"Massachusetts Institute of Technology",600));
        market.add(new Stock(1023,"Boston College",600));
        market.add(new Stock(1024,"Tufts University",700));
        market.add(new Stock(1025,"Brandeis University",700));
    }
    
    private StockMarket(ArrayList<Stock> stock) {
    	this.market = stock;
    }
    
    public ArrayList<Stock> saveData(){
    	return this.market;
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
    
    public boolean deleteStock(int stockId) {
    	for (Stock stock: market) {
            if (stockId == stock.getId()) {
                if(stock.getAvailableNumber() == stock.getTotalNumber()) {
                	this.market.remove(stock);
                	return true;
                }
                else {
                	break;
                }
            }
        }
    	return false;
    }

    // Functions used by customers.
    public void customerPurchase(Stock stock, int number) {
        stock.customerPurchase(number);
    }

    public void customerSell(Stock stock, int number) {
        stock.customerSell(number);
    }
}
