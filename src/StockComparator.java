import java.util.Comparator;

public class StockComparator implements Comparator<Stock>{

	public StockComparator() {
		
	}

	@Override
	public int compare(Stock o1, Stock o2) {
		return (int) (o1.getBuyPriceAmount() - o2.getBuyPriceAmount());
	}

}
