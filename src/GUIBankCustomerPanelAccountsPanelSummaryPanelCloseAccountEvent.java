import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

/**
 * when user wants to close this account
 */
public class GUIBankCustomerPanelAccountsPanelSummaryPanelCloseAccountEvent extends GUIBankEvent {
    // instance variable
    private GUIBankCustomerPanelAccountsPanel panel;
    private String accountNumber;

    // constructor
    public GUIBankCustomerPanelAccountsPanelSummaryPanelCloseAccountEvent(GUIBankATMFrame frame, GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
    }

    // mutator function
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        GUIBankATMFrame frame = getFrame();
        BankCustomer customer = frame.getCustomer();

        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();

        bank.closeAccount(customer, accountNumber, day, month, year);
        panel.setAccountNumber(null);
        panel.showSummary();
    }
}
