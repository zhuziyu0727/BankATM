import java.awt.event.ActionEvent;

/**
 * when user clicks open button in customer accounts summary panel
 */
public class GUIBankCustomerPanelAccountsPanelSummaryPanelOpenEvent extends GUIBankEvent {
    // instance variable
    private GUIBankCustomerPanelAccountsPanel panel;

    // constructor
    public GUIBankCustomerPanelAccountsPanelSummaryPanelOpenEvent(GUIBankATMFrame frame,
                                                                  GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        panel.showOpenAccount();
    }
}
