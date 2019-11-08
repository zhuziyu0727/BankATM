import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * the main window for bank ATM
 */
public class GUIBankATMFrame extends JFrame {
    // instance variable with private access
    private BankCustomer customer;
    private String routingNumber;

    // constructor
    public GUIBankATMFrame() {
        setAttributes();
        generateRoutingNumber();
    }

    // accessor function
    public BankCustomer getCustomer() {
        return customer;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    // mutator function
    public void setCustomer(BankCustomer customer) {
        this.customer = customer;
    }

    // other functions
    public void setAttributes() {
        setTitle("Bank ATM");
        setSize(800, 600);
        setLocationRelativeTo(null); //default Center of window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display(String title) {
        setTitle(title);
        setVisible(true);
    }

    public void clear() {
        getContentPane().removeAll();
        repaint();
    }

    public void generateRoutingNumber() {
        Random rand = new Random();
        int numberInt = rand.nextInt(1000000000);
        routingNumber = String.format("%09d", numberInt);
    }

    // show contents
    public void showLogin() {
        clear();
        add(new GUIBankLoginPanel(this));
        display("Login...");
    }

    public void showRegister() {
        clear();
        add(new GUIBankRegisterPanel(this));
        display("Register...");
    }

    public void showCustomer() {
        clear();
        add(new GUIBankCustomerPanel(this));
        Bank bank = Bank.getInstance();
        display("Hi "+bank.getUsernameByCustomer(customer));
    }

    public void showManager() {
        clear();
        add(new GUIBankManagerPanel(this));
        display("Manager...");
    }
}
