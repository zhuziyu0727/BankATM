import java.io.IOException;
import java.text.DecimalFormat;

/**
 * This contains the main function to trigger the project.
 */
public class Main implements TransactionMessage{
    public static void main(String[] args) {
        Bank bank = Bank.getInstance();

       bank.setManager("admin", "Admin000", "admin@gmail.com");

       bank.registerNewCustomer("dezhouw", "Aa123456", "dezhouw@sina.com");
       bank.registerNewCustomer("test1", "Aa123456", "test@sina.com");


        GUIBankATMFrame tf = new GUIBankATMFrame();
        tf.showLogin();

        CurrencyExchangeRate rates = CurrencyExchangeRate.getInstance();
        rates.setRate("USD", "RMB", 5);
        rates.setRate("USD", "CCC", 8);
        System.out.println(rates.getRate("USD", "RMB"));
        
//        tf.addWindowListener(new   java.awt.event.WindowAdapter()   { 
//        	public   void   windowClosing(java.awt.event.WindowEvent   e)   { 
//        		 try {
//        				TransactionMessage.writeFile();
//        			} catch (IOException e2) {
//        				// TODO Auto-generated catch block
//        				e2.printStackTrace();
//        			}
//        	} 
//        	});
        
       
    }
}
