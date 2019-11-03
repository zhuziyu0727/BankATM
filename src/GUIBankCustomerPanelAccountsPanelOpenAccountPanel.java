import javax.swing.*;
import java.awt.*;

/**
 * when a customer wants to open an account
 */
public class GUIBankCustomerPanelAccountsPanelOpenAccountPanel extends GUIBankPanel
        implements BankAccountTypes, CurrencyAbbrs {
    // instance variable with private access
    private GUIBankCustomerPanelAccountsPanel panel;
    private GUIBankCustomerPanel customerPanel;


    // constructor
    public GUIBankCustomerPanelAccountsPanelOpenAccountPanel(GUIBankATMFrame frame,
                                                             GUIBankCustomerPanelAccountsPanel panel,
                                                             GUIBankCustomerPanel customerPanel) {
        super(frame);
        this.panel = panel;
        this.customerPanel = customerPanel;
        addElements();
    }

    // add elements
    public void addElements() {
        String[] accountTypes = new String[]{CHECKING, SAVING, LOAN};
        JComboBox accountTypeComboBox = new JComboBox(accountTypes);

        String[] currencyTypes = new String[]{CurrencyCNY, CurrencyEUR, CurrencyUSD};
        JComboBox currencyTypeComboBox = new JComboBox(currencyTypes);

        JTextField valueField = new JTextField();

        JLabel feedbackLabel = new JLabel(" ");

        JButton openButton = new JButton("OPEN ACCOUNT");
        GUIBankCustomerPanelAccountsPanelOpenAccountPanelOpenEvent openEvent =
                new GUIBankCustomerPanelAccountsPanelOpenAccountPanelOpenEvent(getFrame(), customerPanel);
        openEvent.setAccountTypeComboBox(accountTypeComboBox);
        openEvent.setCurrencyTypeComboBox(currencyTypeComboBox);
        openEvent.setValueField(valueField);
        openEvent.setFeedbackLabel(feedbackLabel);
        openButton.addActionListener(openEvent);

        JButton backButton = new JButton("BACK");
        GUIBankCustomerPanelAccountsPanelOpenAccountPanelBackEvent backEvent =
                new GUIBankCustomerPanelAccountsPanelOpenAccountPanelBackEvent(getFrame(), panel);
        backButton.addActionListener(backEvent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Account type"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(accountTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Currency type"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(currencyTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Value"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(valueField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(feedbackLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(openButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(backButton, gbc);
    }
}
