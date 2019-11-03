import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * show summary of one customer's accounts
 */
public class GUIBankCustomerPanelAccountsPanelSummaryPanel extends GUIBankPanel {
    // instance variable with private access
    private GUIBankCustomerPanelAccountsPanel panel;

    // constructor
    public GUIBankCustomerPanelAccountsPanelSummaryPanel(GUIBankATMFrame frame, GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
        addElements();
    }

    // dimension functions
    public Dimension getPreferredSize() {
        return new Dimension(550, 350);
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return getPreferredSize();
    }

    // add elements
    public void addElements() {
        GUIBankATMFrame frame = getFrame();
        Bank bank = Bank.getInstance();
        BankCustomer customer = frame.getCustomer();

        DecimalFormat keep2Decimals = new DecimalFormat("#.##");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton openAccountButton = new JButton("Open a new account");
        GUIBankCustomerPanelAccountsPanelSummaryPanelOpenEvent openEvent =
                new GUIBankCustomerPanelAccountsPanelSummaryPanelOpenEvent(frame, panel);
        openAccountButton.addActionListener(openEvent);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(openAccountButton, gbc);

        ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
        JButton accountButton;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (String accountNumber: allAccountNumbers) {
            gbc.gridy += 1;
            accountButton = new JButton();
            String accountType = bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber);
            String accountNumber4digits = accountNumber.substring(accountNumber.length()-4);
            String accountCurrencyAbbr = bank.getAccountCurrencyAbbrByCustomerAccountNumber(customer, accountNumber);
            double accountBalance = bank.getAccountBalanceByCustomerAccountNumber(customer, accountNumber);
            String accountBalanceStr = keep2Decimals.format(accountBalance);
            String accountMoney = String.format("%s%s", accountCurrencyAbbr, accountBalanceStr);
            String accountSpec = String.format("%-8s XXXXX%s %10s", accountType, accountNumber4digits, accountMoney);
            accountButton.setText(accountSpec);
            GUIBankCustomerPanelAccountsPanelSummaryPanelAccountEvent accountEvent =
                    new GUIBankCustomerPanelAccountsPanelSummaryPanelAccountEvent(getFrame(), panel);
            accountEvent.setAccountNumber(accountNumber);
            accountButton.addActionListener(accountEvent);
            add(accountButton, gbc);
        }


        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        String currentAccountNumber = panel.getAccountNumber();

        if (currentAccountNumber == null && allAccountNumbers.size() > 0) {
            currentAccountNumber = allAccountNumbers.get(0);
            panel.setAccountNumber(currentAccountNumber);
        }
        if (currentAccountNumber != null) {

            gbc.gridy += 1;
            gbc.gridx = 0;
            gbc.weightx = 1;
            add(new JLabel("Routing number"), gbc);

            gbc.gridx = 1;
            gbc.weightx = 1;
            add(new JLabel(bank.getRoutingNumberByCustomerAccountNumber(customer, currentAccountNumber)), gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            gbc.weightx = 0;
            add(new JLabel("Account number"), gbc);

            gbc.gridx = 1;
            add(new JLabel(currentAccountNumber), gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            add(new JLabel("Account type"), gbc);

            gbc.gridx = 1;
            add(new JLabel(bank.getAccountTypeByCustomerAccountNumber(customer, currentAccountNumber)), gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            add(new JLabel("Currency type"), gbc);

            gbc.gridx = 1;
            add(new JLabel(bank.getAccountCurrencyAbbrByCustomerAccountNumber(customer, currentAccountNumber)), gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            add(new JLabel("Money balance"), gbc);

            gbc.gridx = 1;
            double balance = bank.getAccountBalanceByCustomerAccountNumber(customer, currentAccountNumber);
            add(new JLabel(keep2Decimals.format(balance)), gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            add(new JLabel("Open date"), gbc);

            gbc.gridx = 1;
            add(new JLabel(bank.getOpenDateByCustomerAccountNumber(customer, currentAccountNumber)), gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            add(new JLabel("Last update date"), gbc);

            gbc.gridx = 1;
            add(new JLabel(bank.getLastUpdateDateByCustomerAccountNumber(customer, currentAccountNumber)), gbc);

            gbc.gridx = 0;
            gbc.gridy += 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JTextField depositField = new JTextField();
            add(depositField, gbc);
            System.out.println(gbc.gridy);

            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton depositButton = new JButton("DEPOSIT");
            GUIBankCustomerPanelAccountsPanelSummaryPanelDepositEvent depositEvent = new GUIBankCustomerPanelAccountsPanelSummaryPanelDepositEvent(getFrame(), panel);
            depositEvent.setAccountNumber(currentAccountNumber);
            depositEvent.setDepositField(depositField);
            depositButton.addActionListener(depositEvent);
            add(depositButton, gbc);

            gbc.gridx = 0;
            gbc.gridy += 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JTextField withdrawField = new JTextField();
            add(withdrawField, gbc);
            System.out.println(gbc.gridy);

            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton withdrawButton = new JButton("WITHDRAW");
            GUIBankCustomerPanelAccountsPanelSummaryPanelWithdrawEvent withdrawEvent = new GUIBankCustomerPanelAccountsPanelSummaryPanelWithdrawEvent(getFrame(), panel);
            withdrawEvent.setAccountNumber(currentAccountNumber);
            withdrawEvent.setWithdrawField(withdrawField);
            withdrawButton.addActionListener(withdrawEvent);
            add(withdrawButton, gbc);

            gbc.gridy += 1;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            gbc.gridheight = 4;
            gbc.weighty = 1;
            gbc.fill = GridBagConstraints.BOTH;
            String[] column = {"DATE", "FROM", "TO", "AMOUNT", "AVAILABLE"};
            String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, currentAccountNumber);
            JTable transactionsTable = new JTable(data, column);
            JScrollPane transactionsSP = new JScrollPane(transactionsTable);
            add(transactionsSP, gbc);

            gbc.gridx = 0;
            gbc.gridy += 4;
            gbc.gridheight = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JButton closeAccountButton = new JButton("Close this account");
            GUIBankCustomerPanelAccountsPanelSummaryPanelCloseAccountEvent closeEvent =
                    new GUIBankCustomerPanelAccountsPanelSummaryPanelCloseAccountEvent(getFrame(), panel);
            closeEvent.setAccountNumber(currentAccountNumber);
            closeAccountButton.addActionListener(closeEvent);
            add(closeAccountButton, gbc);
        }
    }
}
