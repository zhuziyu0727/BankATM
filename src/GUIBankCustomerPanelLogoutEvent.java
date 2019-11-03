import java.awt.event.ActionEvent;

/**
 * when user clicks logout in customer panel
 */
public class GUIBankCustomerPanelLogoutEvent extends GUIBankEvent {
    // constructor
    public GUIBankCustomerPanelLogoutEvent(GUIBankATMFrame frame) {
        super(frame);
    }

    // event function
    public void actionPerformed(ActionEvent e) {
        GUIBankATMFrame frame = getFrame();
        frame.showLogin();
        frame.setCustomer(null);
    };
}
