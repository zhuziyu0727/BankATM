import java.text.DecimalFormat;

/**
 * This contains the main function to trigger the project.
 */
public class Main {
    public static void main(String[] args) {
        Bank bank = Bank.getInstance();

       bank.setManager("admin", "Admin000", "admin@gmail.com");

       bank.registerNewCustomer("dezhouw", "Aa123456", "dezhouw@sina.com");


        GUIBankATMFrame tf = new GUIBankATMFrame();
        tf.showLogin();

        CurrencyExchangeRate rates = CurrencyExchangeRate.getInstance();
        rates.setRate("USD", "RMB", 5);
        rates.setRate("USD", "CCC", 8);
        System.out.println(rates.getRate("USD", "RMB"));
    }
}
