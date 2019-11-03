import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * the accounts panel inside customer panel
 */
public class GUIBankCustomerPanelAccountsPanel extends GUIBankPanel {
    // instance variable
    private GUIBankCustomerPanel customerPanel;
    private String accountNumber;

    // constructor
    public GUIBankCustomerPanelAccountsPanel(GUIBankATMFrame frame, GUIBankCustomerPanel customerPanel) {
        super(frame);
        this.customerPanel = customerPanel;
        showSummary();
    }

    // clear
    public void clear() {
        removeAll();
        repaint();
    }

    // accessor function
    public String getAccountNumber() {
        return accountNumber;
    }

    // mutator function
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // show summary
    public void showSummary() {
        clear();
        add(new GUIBankCustomerPanelAccountsPanelSummaryPanel(getFrame(), this));
        GUIBankATMFrame frame = getFrame();
        frame.display("Account summary...");
    }

    // show open account
    public void showOpenAccount() {
        clear();
        add(new GUIBankCustomerPanelAccountsPanelOpenAccountPanel(getFrame(), this, customerPanel));
        GUIBankATMFrame frame = getFrame();
        frame.display("Open account...");
    }
}
