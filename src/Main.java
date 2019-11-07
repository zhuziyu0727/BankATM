import java.io.IOException;
import java.text.DecimalFormat;

/**
 * This contains the main function to trigger the project.
 */
public class Main implements DataOperation {
	public static void main(String[] args) {
		try {
			DataOperation.sqliteRead();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Bank bank = Bank.getInstance();

		bank.setManager("admin", "Admin000", "admin@gmail.com");

		//bank.registerNewCustomer("dezhouw", "Aa123456", "dezhouw@sina.com");

		GUIBankATMFrame tf = new GUIBankATMFrame();
		tf.showLogin();

		CurrencyExchangeRate rates = CurrencyExchangeRate.getInstance();
		rates.setRate("USD", "RMB", 5);
		rates.setRate("USD", "CCC", 8);
		// System.out.println(rates.getRate("USD", "RMB"));

		tf.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				try {
					DataOperation.sqliteWrite();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
}
