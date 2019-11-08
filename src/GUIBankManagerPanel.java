import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
        JLabel message = new JLabel("Welcome!");
        
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

        JTextField searchUserText = new JTextField();
        
        JButton stockManageButton = new JButton("Stock Management");
        GUIBankManagerPanelStockManageEvent stockManageEvent = new GUIBankManagerPanelStockManageEvent(getFrame()); // NEED IMPLEMENT
        stockManageButton.addActionListener(stockManageEvent);
        
        JButton listCustomerButton = new JButton("List Customers");
        GUIBankManagerPanelListCustomerEvent listCustomerEvent = new GUIBankManagerPanelListCustomerEvent(getFrame(), message);
        listCustomerButton.addActionListener(listCustomerEvent);
        
        JButton searchButton = new JButton("Search");
        GUIBankManagerPanelSearchCustomerEvent searchCustomerEvent = new GUIBankManagerPanelSearchCustomerEvent(getFrame(), searchUserText, transactionsPanel, message);
        searchButton.addActionListener(searchCustomerEvent);
                
        this.setBorder(new EmptyBorder(10, 0, 10, 0));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWidths = new int[]{150, 160, 250, 170, 170};
        setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.insets = new Insets(0, 0, 5, 5);
        add(logoutButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 5, 5);
        add(savingInterestButton, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 5, 0);
        add(loanInterestButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.gridheight = 7;
        gbc.weighty = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(transactionsPanel, gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridheight = 7;
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(message, gbc);
        
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 5, 5);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(stockManageButton, gbc);
        
        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.gridx = 2;
        gbc.gridy = 8;
        add(listCustomerButton, gbc);
        
        gbc = new GridBagConstraints();  
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(0, 0, 0, 5);
        gbc.gridx = 3;
        gbc.gridy = 8;
        add(searchUserText, gbc);
        searchUserText.setColumns(15);
        searchUserText.setHorizontalAlignment(SwingConstants.CENTER);
        
        gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 4;
        gbc.gridy = 8;
        add(searchButton, gbc);
        
    }
}
