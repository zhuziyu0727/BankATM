import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * event when user clicks REGISTER button in register panel
 */
public class GUIBankRegisterPanelLoginEvent extends MouseAdapter {
    // instance variable with private access
    private GUIBankATMFrame frame;

    // constructor
    public GUIBankRegisterPanelLoginEvent(GUIBankATMFrame frame) {
        this.frame = frame;
    }

    // primary function
    public void mouseClicked(MouseEvent e) {
        frame.showLogin();
    }
}