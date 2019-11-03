import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * event when user clicks REGISTER button in login panel
 */
public class GUIBankLoginPanelRegisterEvent extends MouseAdapter {
    // instance variable with private access
    private GUIBankATMFrame frame;

    // constructor
    public GUIBankLoginPanelRegisterEvent(GUIBankATMFrame frame) {
        this.frame = frame;
    }

    // primary function
    public void mouseClicked(MouseEvent e) {
        frame.showRegister();
    }
}
