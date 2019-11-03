import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * abstract event class for bank
 */
public abstract class GUIBankEvent implements ActionListener {
    // instance variables with private access
    private GUIBankATMFrame frame;

    // constructor
    public GUIBankEvent(GUIBankATMFrame frame) {
        this.frame = frame;
    }

    // accessor functions
    public GUIBankATMFrame getFrame() {
        return frame;
    }

    // abstract function
    public abstract void actionPerformed(ActionEvent e);
}
