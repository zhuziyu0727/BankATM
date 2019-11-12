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
        /*
        market.add(new Stock(1020,"Boston University",500));
        market.add(new Stock(1021,"Harvard University",500));
        market.add(new Stock(1022,"Massachusetts Institute of Technology",600));
        market.add(new Stock(1023,"Boston College",600));
        market.add(new Stock(1024,"Tufts University",700));
        market.add(new Stock(1025,"Brandeis University",700));
        */
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
    
    //When the manager wants to modify the number of a stock
    public boolean modifyStockNumber(Stock stock, int number) {
    	for (Stock stck: market) {
            if (stck.isEqual(stock)) {
            	stck.addNumber(number);
                return true;
            }
        }
        return false;
    }
    //When the manager wants to modify the price of a stock
    public boolean modifyStockBuyPrice(Stock stock, double price) {
    	try {
    		if (price <= 0) {
    			throw new Exception();
    		}
    	} catch (Exception e) {
    		String alert = String.format("The price cannot small than or equal to 0.");
            throw new IllegalArgumentException(alert);
    	}
    	for (Stock stck: market) {
    		if (stck.isEqual(stock)) {
            	stck.setBuyPriceAmount(price);
                return true;
            }
        }
        return false;
    }
    
    public boolean modifyStockSellPrice(Stock stock, double price) {
    	try {
    		if (price <= 0) {
    			throw new Exception();
    		}
    	} catch (Exception e) {
    		String alert = String.format("The price cannot small than or equal to 0.");
            throw new IllegalArgumentException(alert);
    	}
    	for (Stock stck: market) {
    		if (stck.isEqual(stock)) {
            	stck.setSellPriceAmount(price);
                return true;
            }
        }
        return false;
    }
     
    public String[][] getStockList() {
    	String[][] data = new String[market.size()][3];
        int count = 0;
        for (Stock stock: market) {
            String[] row = new String[3];
            row[0] = Integer.toString(stock.getId());
            row[1] = stock.getName();
            row[2] = Integer.toString(0);
            data[count++] = row;
        }
        return data;
    }
    
    public String[][] showMarket() {
    	String[][] data = new String[market.size()][5];
        int count = 0;
        for (Stock stock: market) {
            String[] row = new String[5];
            row[0] = Integer.toString(stock.getId());
            row[1] = stock.getName();
            row[2] = Integer.toString(stock.getAvailableNumber());
            row[3] = Double.toString(stock.getBuyPrice().getAmount());
            row[4] = Double.toString(stock.getSellPrice().getAmount());
            data[count++] = row;
        }
        return data;
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
