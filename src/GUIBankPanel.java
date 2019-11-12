import javax.swing.*;
import java.awt.*;

public class GUIBankPanel extends JPanel {
    // instance variable with private access
    protected GUIBankATMFrame frame;

    // constructor
    public GUIBankPanel(GUIBankATMFrame frame) {
        this.frame = frame;
    }

    // accessor functions
    public GUIBankATMFrame getFrame() {
        return frame;
    }

    // dimension functions
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }
}
