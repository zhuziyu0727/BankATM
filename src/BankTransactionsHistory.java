import java.util.ArrayList;

/**
 * record list of transactions happened in one account
 */
public class BankTransactionsHistory {
    // instance variable with private access
    private ArrayList<BankTransaction> history;

    // constructors
    public BankTransactionsHistory() {
        history = new ArrayList<>();
    }

    // accessor function
    public ArrayList<BankTransaction> getHistory(){
    	return history;
    }
    
    public String[][] getData() {
        String[][] data = new String[history.size()][5];
        int count = 0;
        for (BankTransaction transaction: history) {
            String[] row = new String[5];
            row[0] = transaction.getDate();
            row[1] = transaction.getFrom();
            row[2] = transaction.getTo();
            row[3] = transaction.getAmountStr();
            row[4] = transaction.getAvailableStr();
            data[count++] = row;
        }
        return data;
    }

    // mutator function
    public void addNewTransaction(int day, int month, int year, String currency, double amount,
                                  double available, String from, String to) {
        BankTransaction t = new BankTransaction();
        t.setTransactionDate(day, month, year);
        t.setCurrency(currency);
        t.setAmount(amount);
        t.setAvailable(available);
        t.setFrom(from);
        t.setTo(to);
        history.add(t);
    }
}
