import java.awt.event.ActionEvent;

/**
 * when user wants to show a certain account info
 */
public class GUIBankCustomerPanelAccountsPanelSummaryPanelAccountEvent extends GUIBankEvent {
    // instance variables
    private GUIBankCustomerPanelAccountsPanel panel;
    private String accountNumber;

    // constructor
    public GUIBankCustomerPanelAccountsPanelSummaryPanelAccountEvent(GUIBankATMFrame frame,
                                                                     GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
    }

    // mutator function
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        panel.setAccountNumber(accountNumber);
        panel.showSummary();
    }
}
