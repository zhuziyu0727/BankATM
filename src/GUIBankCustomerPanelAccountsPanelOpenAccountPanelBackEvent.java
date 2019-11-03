import java.awt.event.ActionEvent;

/**
 * when user clicks back button in customer accounts open panel
 */
public class GUIBankCustomerPanelAccountsPanelOpenAccountPanelBackEvent extends GUIBankEvent {
    // instance variable
    private GUIBankCustomerPanelAccountsPanel panel;

    // constructor
    public GUIBankCustomerPanelAccountsPanelOpenAccountPanelBackEvent(GUIBankATMFrame frame,
                                                                      GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        panel.showSummary();
    }
}
