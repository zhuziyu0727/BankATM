import javax.swing.*;
import java.awt.*;

public class GUIBankManagerPanel extends GUIBankPanel {
    // constructor
    public GUIBankManagerPanel(GUIBankATMFrame frame) {
        super(frame);
        refresh();
    }

    public void refresh() {
        removeAll();
        repaint();
        addElements();
        getFrame().display("Manager...");
    }

    // add elements
    public void addElements() {
        JButton logoutButton = new JButton("LOGOUT");
        GUIBankCustomerPanelLogoutEvent logoutEvent = new GUIBankCustomerPanelLogoutEvent(getFrame());
        logoutButton.addActionListener(logoutEvent);

        JButton savingInterestButton = new JButton("Saving Interest");
        GUIBankManagerPanelSavingInterestEvent savingInterestEvent = new GUIBankManagerPanelSavingInterestEvent(getFrame(), this);
        savingInterestButton.addActionListener(savingInterestEvent);

        JButton loanInterestButton = new JButton("Loan Interest");
        GUIBankManagerPanelLoanInterestEvent loanInterestEvent = new GUIBankManagerPanelLoanInterestEvent(getFrame(), this);
        loanInterestButton.addActionListener(loanInterestEvent);

        GUIBankManagerPanelTransactionsPanel transactionsPanel = new GUIBankManagerPanelTransactionsPanel(getFrame());

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(logoutButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(savingInterestButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1;
        add(loanInterestButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 8;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(transactionsPanel, gbc);
    }
}
