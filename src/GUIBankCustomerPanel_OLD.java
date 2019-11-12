import javax.swing.*;
import java.awt.*;

/**
 * window showed after a user successfully login
 */
public class GUIBankCustomerPanel_OLD extends GUIBankPanel {
    // private instance
    private GUIBankCustomerPanelProfilePanel profilePanel;
    private GUIBankCustomerPanelAccountsPanel accountsPanel;
    private GUIBankCustomerPanelTransferPanel transferPanel;

    // constructor
    public GUIBankCustomerPanel_OLD(GUIBankATMFrame frame) {
        super(frame);
        profilePanel = new GUIBankCustomerPanelProfilePanel(getFrame());
        accountsPanel = new GUIBankCustomerPanelAccountsPanel(getFrame(), this);
        transferPanel = new GUIBankCustomerPanelTransferPanel(getFrame(), this);
        addElements();
    }

    // accessor function
    public GUIBankCustomerPanelProfilePanel getProfilePanel() {
        return profilePanel;
    }

    public GUIBankCustomerPanelAccountsPanel getAccountsPanel() {
        return accountsPanel;
    }

    public GUIBankCustomerPanelTransferPanel getTransferPanel() {
        return transferPanel;
    }

    // other function
    public void addElements() {
        JButton logoutButton = new JButton("LOGOUT");
        GUIBankCustomerPanelLogoutEvent logoutEvent = new GUIBankCustomerPanelLogoutEvent(getFrame());
        logoutButton.addActionListener(logoutEvent);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Profile", profilePanel);
        tabbedPane.addTab("Accounts", accountsPanel);
        tabbedPane.addTab("Transfer", transferPanel);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(logoutButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 8;
        add(tabbedPane, gbc);
    }
}
