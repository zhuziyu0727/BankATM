import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUIBankCustomerPanelTransferPanel extends GUIBankPanel {
    // instance
    private GUIBankCustomerPanel_OLD customerPanel;

    // constructor
    public GUIBankCustomerPanelTransferPanel(GUIBankATMFrame frame, GUIBankCustomerPanel_OLD customerPanel) {
        super(frame);
        this.customerPanel = customerPanel;
        refresh();
    }

    public void clear() {
        removeAll();
        repaint();
    }

    // add elements
    public void refresh() {
        clear();
        GUIBankATMFrame frame = getFrame();
        Bank bank = Bank.getInstance();
        BankCustomer customer = frame.getCustomer();

        ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
        String[] accounts = new String[allAccountNumbers.size()];
        int count = 0;
        for (String accountNumber: allAccountNumbers) {
            accounts[count++] = accountNumber;
        }

        JComboBox accountComboBox = new JComboBox(accounts);

        JTextField targetAccountNumberField = new JTextField();

        JTextField valueField = new JTextField();

        JLabel feedbackLabel = new JLabel(" ");

        JButton transferButton = new JButton("TRANSFER");
        GUIBankCustomerPanelTransferPanelTransferEvent transferEvent = new GUIBankCustomerPanelTransferPanelTransferEvent(getFrame(), customerPanel);
        transferEvent.setAccountComboBox(accountComboBox);
        transferEvent.setTargetAccountNumberField(targetAccountNumberField);
        transferEvent.setValueField(valueField);
        transferEvent.setFeedbackLabel(feedbackLabel);
        transferButton.addActionListener(transferEvent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("From"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(accountComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("To"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(targetAccountNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Value"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(valueField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(feedbackLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(transferButton, gbc);
    }
}
