import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUIBankCustomerPanel extends GUIBankPanel {

    private Bank bank;
    private BankCustomer customer;
    private String accountNumber;

    private JLabel welcome;
    private JLabel welcomeName;
    private JButton overview;
    private JButton accounts;
    private JButton profile;
    private JButton exit;
    private JSeparator verticalDivider;
    private JLabel logo;
    private JSeparator horizontalDivider;

    private JLabel emailLabel;
    private JTextField emailText;
    private JLabel phoneLabel;
    private JTextField phoneText;
    private JLabel checkingNumberLabel;
    private JTextField checkingNumberText;
    private JLabel savingNumberLabel;
    private JTextField savingNumberText;
    private JLabel loanNumberLabel;
    private JTextField loanNumberText;

    private JLabel customerNumberLabel;
    private JTextField customerNumberText;
    private JLabel usernameLabel;
    private JTextField usernameText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    // emailLabel
    // emailText
    // phoneLabel
    // phoneText
    private JLabel addressLabel1;
    private JTextField addressText1;
    private JLabel addressLabel2;
    private JTextField addressText2;
    private JLabel cityLabel;
    private JTextField cityText;
    private JLabel stateLabel;
    private JTextField stateText;
    private JLabel zipCodeLabel;
    private JTextField zipCodeText;
    private JLabel countryLabel;
    private JTextField countryText;
    private JLabel warning;
    private JButton update;

    private JComboBox accountsBox;
    private JButton openNewAccount;
    private JButton accountOverview;
    private JButton accountHistory;
    private JButton accountTransfer;
    private JButton accountSecurity;
    private JLabel routingNumberLabel;
    private JTextField routingNumberText;
    private JLabel accountNumberLabel;
    private JTextField accountNumberText;
    private JLabel dateOpenLabel;
    private JTextField dateOpenText;
    private JLabel dateLastLabel;
    private JTextField dateLastText;
    private JLabel typeLabel;
    private JTextField typeText;
    private JLabel currencyLabel;
    private JTextField currencyText;
    // warning
    private JLabel closeNotice;
    private JLabel close;

    private JLabel accountTypeLabel;
    private JComboBox accountTypes;
    private JLabel currencyTypeLabel;
    private JComboBox currencyTypes;
    private JLabel valueLabel;
    private JTextField valueText;
    // warning;
    private JButton open;

    private JScrollPane history;
    private JTextField depositValue;
    private JButton deposit;
    private JTextField withdrawValue;
    private JButton withdraw;

    private JLabel fromLabel;
    private JTextField fromText;
    private JLabel toLabel;
    private JTextField toText;
    private JLabel amountLabel;
    private JTextField amountText;
    // currencyLabel;
    // currencyText;
    // warning
    private JButton transfer;

    private JLabel securityNotice;
    private JButton securityOpen;

    private JButton securityOverview;
    private JButton market;
    private JButton myStock;
    private JButton buy;
    private JButton sell;
    private JButton securityHistory;
    private JSeparator divider3;

    // accountNumberLabel
    // accountNumberText
    // dateOpenLabel
    // dateOpenText
    private JLabel stocksNumberLabel;
    private JTextField stocksNumberText;
    private JLabel stocksValueLabel;
    private JTextField stocksValueText;
    // closeNotice
    // close

    private JScrollPane marketList;

    private JScrollPane myStockList;

    private JLabel buyStock;
    private JLabel stockIdLabel;
    private JTextField stockIdText;
    private JLabel buyPriceLabel;
    private JTextField buyPriceText;
    private JLabel buyNumberLabel;
    private JTextField buyNumberText;
    // warning
    private JButton buyButton;

    private JLabel sellStock;
    // stockIdLabel
    // stockIdText
    private JLabel sellPriceLabel;
    private JTextField sellPriceText;
    private JLabel moneySpentLabel;
    private JTextField moneySpentText;
    private JLabel numberInHandLabel;
    private JTextField numberInHandText;
    private JLabel sellNumberLabel;
    private JTextField sellNumberText;
    // warning
    private JButton sellButton;

    private JScrollPane stockHistory;

    public GUIBankCustomerPanel(GUIBankATMFrame frame) {
        super(frame);
        bank = Bank.getInstance();
        customer = frame.getCustomer();
        displayOverview();
    }

    public void basicDisplay() {
        removeAll();
        revalidate();
        repaint();

        welcome = new JLabel("Welcome!");
        welcomeName = new JLabel();
        overview = new JButton("Overview");
        accounts = new JButton("Accounts");
        profile = new JButton("Profile");
        exit = new JButton("EXIT");
        verticalDivider = new JSeparator(SwingConstants.VERTICAL);
        logo = new JLabel("Bank ATM");
        horizontalDivider = new JSeparator(SwingConstants.HORIZONTAL);

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        add(welcome);
        add(welcomeName);
        add(overview);
        add(accounts);
        add(profile);
        add(exit);
        add(verticalDivider);
        add(logo);
        add(horizontalDivider);

        welcome.setBounds (20, 15, 100, 25);
        welcomeName.setBounds (20, 40, 100, 25);
        overview.setBounds (20, 100, 100, 25);
        accounts.setBounds (20, 145, 100, 25);
        profile.setBounds (20, 190, 100, 25);
        exit.setBounds (20, 545, 100, 25);
        verticalDivider.setBounds (130, 10, 1, 580);
        logo.setBounds (155, 15, 100, 25);
        horizontalDivider.setBounds (140, 40, 650, 100);

        welcomeName.setText(bank.getUsernameByCustomer(customer));

        overview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayOverview();
            }
        });

        accounts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Overview", "");
            }
        });

        profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayProfile();
            }
        });

        GUIBankCustomerPanelLogoutEvent logoutEvent = new GUIBankCustomerPanelLogoutEvent(getFrame());
        exit.addActionListener(logoutEvent);
    }

    public void displayOverview() {
        basicDisplay();

        emailLabel = new JLabel("Email");
        emailText = new JTextField();
        phoneLabel = new JLabel("Phone #");
        phoneText = new JTextField();
        checkingNumberLabel = new JLabel("Checking Accounts Number");
        checkingNumberText = new JTextField();
        savingNumberLabel = new JLabel("Saving Accounts Number");
        savingNumberText = new JTextField();
        loanNumberLabel = new JLabel("Loan Accounts Number");
        loanNumberText = new JTextField();

        add(emailLabel);
        add(emailText);
        add(phoneLabel);
        add(phoneText);
        add(checkingNumberLabel);
        add(checkingNumberText);
        add(savingNumberLabel);
        add(savingNumberText);
        add(loanNumberLabel);
        add(loanNumberText);

        emailLabel.setBounds (155, 65, 170, 25);
        emailText.setBounds (155, 90, 170, 25);
        phoneLabel.setBounds (155, 115, 170, 25);
        phoneText.setBounds (155, 140, 170, 25);
        checkingNumberLabel.setBounds (155, 165, 170, 25);
        checkingNumberText.setBounds (155, 190, 170, 25);
        savingNumberLabel.setBounds (155, 215, 170, 25);
        savingNumberText.setBounds (155, 240, 170, 25);
        loanNumberLabel.setBounds (155, 265, 170, 25);
        loanNumberText.setBounds (155, 290, 170, 25);

        emailText.setEditable(false);
        emailText.setText(bank.getEmailByCustomer(customer));

        phoneText.setEditable(false);
        phoneText.setText(bank.getPhoneByCustomer(customer));

        checkingNumberText.setEditable(false);
        checkingNumberText.setText(Integer.toString(bank.getNumberofAccountCheckingByCustomer(customer)));

        savingNumberText.setEditable(false);
        savingNumberText.setText(Integer.toString(bank.getNumberofAccountSavingByCustomer(customer)));

        loanNumberText.setEditable(false);
        loanNumberText.setText(Integer.toString(bank.getNumberofAccountLoanByCustomer(customer)));
    }

    public void displayProfile() {
        basicDisplay();

        customerNumberLabel = new JLabel("Customer Number");
        customerNumberText = new JTextField();
        usernameLabel = new JLabel("User name");
        usernameText = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordText = new JTextField();
        emailLabel = new JLabel("Email");
        emailText = new JTextField();
        phoneLabel = new JLabel("Phone #");
        phoneText = new JTextField();
        addressLabel1 = new JLabel("Address 1");
        addressText1 = new JTextField();
        addressLabel2 = new JLabel("Address 2");
        addressText2 = new JTextField();
        cityLabel = new JLabel("City");
        cityText = new JTextField();
        stateLabel = new JLabel("State");
        stateText = new JTextField();
        zipCodeLabel = new JLabel("Zip Code");
        zipCodeText = new JTextField();
        countryLabel = new JLabel("Country");
        countryText = new JTextField();
        warning = new JLabel();
        update = new JButton("UPDATE");

        add(customerNumberLabel);
        add(customerNumberText);
        add(usernameLabel);
        add(usernameText);
        add(passwordLabel);
        add(passwordText);
        add(emailLabel);
        add(emailText);
        add(phoneLabel);
        add(phoneText);
        add(addressLabel1);
        add(addressText1);
        add(addressLabel2);
        add(addressText2);
        add(cityLabel);
        add(cityText);
        add(stateLabel);
        add(stateText);
        add(zipCodeLabel);
        add(zipCodeText);
        add(countryLabel);
        add(countryText);
        add(warning);
        add(update);

        customerNumberLabel.setBounds (155, 65, 275, 25);
        customerNumberText.setBounds (155, 90, 275, 25);
        usernameLabel.setBounds (155, 115, 275, 25);
        usernameText.setBounds (155, 140, 275, 25);
        passwordLabel.setBounds (155, 165, 275, 25);
        passwordText.setBounds (155, 190, 275, 25);
        emailLabel.setBounds (155, 215, 275, 25);
        emailText.setBounds (155, 240, 275, 25);
        phoneLabel.setBounds (155, 265, 275, 25);
        phoneText.setBounds (155, 290, 275, 25);
        addressLabel1.setBounds (155, 315, 275, 25);
        addressText1.setBounds (155, 340, 275, 25);
        addressLabel2.setBounds (155, 365, 275, 25);
        addressText2.setBounds (155, 390, 275, 25);
        cityLabel.setBounds (440, 315, 275, 25);
        cityText.setBounds (440, 340, 275, 25);
        stateLabel.setBounds (440, 365, 275, 25);
        stateText.setBounds (440, 390, 275, 25);
        zipCodeLabel.setBounds (155, 415, 275, 25);
        zipCodeText.setBounds (155, 440, 275, 25);
        countryLabel.setBounds (440, 415, 275, 25);
        countryText.setBounds (440, 440, 275, 25);
        warning.setBounds (155, 465, 560, 25);
        update.setBounds (385, 505, 100, 25);

        customerNumberText.setEditable(false);
        customerNumberText.setText(bank.getCustomerNumberByCustomer(customer));

        usernameText.setEditable(false);
        usernameText.setText(bank.getUsernameByCustomer(customer));

        passwordText.setText(bank.getPasswordByCustomer(customer));

        emailText.setText(bank.getEmailByCustomer(customer));

        phoneText.setText(bank.getPhoneByCustomer(customer));

        addressText1.setText(bank.getAddress1ByCustomer(customer));

        addressText2.setText(bank.getAddress2ByCustomer(customer));

        cityText.setText(bank.getCityByCustomer(customer));

        stateText.setText(bank.getStateByCustomer(customer));

        zipCodeText.setText(bank.getZipCodeByCustomer(customer));

        countryText.setText(bank.getCountryByCustomer(customer));

        GUIBankCustomerPanelProfilePanelUpdateEvent updateEvent = new GUIBankCustomerPanelProfilePanelUpdateEvent(frame);
        updateEvent.setPasswordField(passwordText);
        updateEvent.setEmailField(emailText);
        updateEvent.setPhoneField(phoneText);
        updateEvent.setAddress1Field(addressText1);
        updateEvent.setAddress2Field(addressText2);
        updateEvent.setCityField(cityText);
        updateEvent.setStateField(stateText);
        updateEvent.setZipCodeField(zipCodeText);
        updateEvent.setCountryField(countryText);
        updateEvent.setFeedbackLabel(warning);
        update.addActionListener(updateEvent);
    }

    public void displayAccounts(String tab, String tab2) {
        ArrayList<String> allAccounts = bank.getAllAccountNumbersByCustomer(customer);

        basicDisplay();

        displayAccountsNoAccount();

        if (tab.equals("OpenAccount")) {
            openAccount();
        } else if (allAccounts.size() != 0) {
            displayAccountsHasAccount();

            if (tab.equals("Overview")) {
                displayAccountsOverview();
            } else if (tab.equals("History")) {
                displayAccountsHistory();
            } else if (tab.equals("Transfer")) {
                displayAccountsTransfer();
            } else if (tab.equals("Security")) {
                displayAccountsSecurity(tab2);
            }
        }
    }

    public void displayAccountsNoAccount() {
        accountsBox = new JComboBox();
        openNewAccount = new JButton("Open New Account");

        add(accountsBox);
        add(openNewAccount);

        accountsBox.setBounds (155, 65, 425, 25);
        openNewAccount.setBounds (580, 65, 155, 25);

        // openNewAccount
        openNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("OpenAccount", "");
            }
        });
    }

    public void displayAccountsHasAccount() {
        accountOverview = new JButton("Overview");
        accountHistory = new JButton("History");
        accountTransfer = new JButton("Transfer");
        accountSecurity = new JButton("Security");

        add(accountOverview);
        add(accountHistory);
        add(accountTransfer);
        add(accountSecurity);

        accountOverview.setBounds (155, 100, 145, 25);
        accountHistory.setBounds (300, 100, 140, 25);
        accountTransfer.setBounds (440, 100, 140, 25);
        accountSecurity.setBounds (580, 100, 155, 25);

        ArrayList<String> allAccounts = bank.getAllAccountNumbersByCustomer(customer);
        if (accountNumber == null) {
            accountNumber = allAccounts.get(0);
        }

        for (String account: allAccounts) {
            accountsBox.addItem(account);
        }
        accountsBox.setSelectedItem(accountNumber);
        accountsBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String newAccountNumber = (String) accountsBox.getSelectedItem();
                if (newAccountNumber != accountNumber) {
                    accountNumber = newAccountNumber;
                    displayAccounts("Overview", "");
                }
            }
        });

        accountOverview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Overview", "");
            }
        });

        accountHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("History", "");
            }
        });

        accountTransfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Transfer", "");
            }
        });

        if (bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber).toLowerCase().equals("saving")) {
            accountSecurity.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    displayAccounts("Security", "Overview");
                }
            });
        } else {
            accountSecurity.setForeground(Color.GRAY);
        }
    }

    public void openAccount() {
        accountTypeLabel = new JLabel("Account Type");
        accountTypes = new JComboBox();
        currencyTypeLabel = new JLabel("Currency Type");
        currencyTypes = new JComboBox();
        valueLabel = new JLabel("Value");
        valueText = new JTextField();
        warning = new JLabel();
        open = new JButton("OPEN");

        add(accountTypeLabel);
        add(accountTypes);
        add(currencyTypeLabel);
        add(currencyTypes);
        add(valueLabel);
        add(valueText);
        add(warning);
        add(open);

        accountTypeLabel.setBounds (295, 190, 200, 30);
        accountTypes.setBounds (295, 220, 200, 25);
        currencyTypeLabel.setBounds (295, 245, 200, 25);
        currencyTypes.setBounds (295, 270, 200, 25);
        valueLabel.setBounds (295, 295, 200, 25);
        valueText.setBounds (295, 320, 200, 25);
        warning.setBounds (295, 370, 200, 25);
        open.setBounds (345, 420, 100, 25);

        accountTypes.addItem(BankAccountTypes.CHECKING);
        accountTypes.addItem(BankAccountTypes.SAVING);
        accountTypes.addItem(BankAccountTypes.LOAN);

        currencyTypes.addItem(CurrencyAbbrs.CurrencyCNY);
        currencyTypes.addItem(CurrencyAbbrs.CurrencyEUR);
        currencyTypes.addItem(CurrencyAbbrs.CurrencyUSD);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double value = Double.parseDouble(valueText.getText());
                    String accountType = (String) accountTypes.getSelectedItem();
                    String currencyType = (String) currencyTypes.getSelectedItem();
                    LocalDateTime now = LocalDateTime.now();
                    int day = now.getDayOfMonth();
                    int month = now.getMonthValue();
                    int year = now.getYear();
                    String accountNumber = bank.openAccount(customer, accountType, currencyType, value, day, month, year);
                    String routingNumber = frame.getRoutingNumber();
                    bank.setRoutingNumberByCustomerAccountNumber(customer, accountNumber, routingNumber);
                    warning.setForeground(Color.GREEN);
                    warning.setText("Success");
                } catch (Exception exception) {
                    warning.setForeground(Color.RED);
                    warning.setText(exception.getMessage());
                }
            }
        });
    }

    public void displayAccountsOverview() {
        routingNumberLabel = new JLabel("Routing number");
        routingNumberText = new JTextField();
        accountNumberLabel = new JLabel("Account number");
        accountNumberText = new JTextField();
        dateOpenLabel = new JLabel("Date Open");
        dateOpenText = new JTextField();
        dateLastLabel = new JLabel("Last Update Date");
        dateLastText = new JTextField();
        typeLabel = new JLabel("Type");
        typeText = new JTextField();
        deposit = new JButton("DEPOSIT");
        withdraw = new JButton("WITHDRAW");
        currencyLabel = new JLabel("Currency");
        currencyText = new JTextField();
        warning = new JLabel();
        closeNotice = new JLabel("Want to close this account?");
        close = new JLabel("CLOSE");

        add(routingNumberLabel);
        add(routingNumberText);
        add(accountNumberLabel);
        add(accountNumberText);
        add(dateOpenLabel);
        add(dateOpenText);
        add(dateLastLabel);
        add(dateLastText);
        add(typeLabel);
        add(typeText);
        add(currencyLabel);
        add(currencyText);
        add(warning);
        add(closeNotice);
        add(close);

        routingNumberLabel.setBounds (155, 145, 305, 25);
        routingNumberText.setBounds (155, 170, 305, 25);
        accountNumberLabel.setBounds (155, 195, 305, 25);
        accountNumberText.setBounds (155, 220, 305, 25);
        dateOpenLabel.setBounds (155, 245, 305, 25);
        dateOpenText.setBounds (155, 270, 305, 25);
        dateLastLabel.setBounds (155, 295, 305, 25);
        dateLastText.setBounds (155, 320, 305, 25);
        typeLabel.setBounds (155, 345, 305, 25);
        typeText.setBounds (155, 370, 305, 25);
        currencyLabel.setBounds(155, 395, 305, 25);
        currencyText.setBounds(155, 420, 305, 25);
        warning.setBounds (155, 510, 580, 25);
        closeNotice.setBounds (155, 545, 180, 25);
        close.setBounds (340, 545, 55, 25);

        routingNumberText.setEditable(false);
        routingNumberText.setText(bank.getRoutingNumberByCustomerAccountNumber(customer, accountNumber));

        accountNumberText.setEditable(false);
        accountNumberText.setText(accountNumber);

        dateOpenText.setEditable(false);
        dateOpenText.setText(bank.getOpenDateByCustomerAccountNumber(customer, accountNumber));

        dateLastText.setEditable(false);
        dateLastText.setText(bank.getLastUpdateDateByCustomerAccountNumber(customer, accountNumber));

        typeText.setEditable(false);
        typeText.setText(bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber));

        currencyText.setEditable(false);
        currencyText.setText(bank.getAccountCurrencyAbbrByCustomerAccountNumber(customer, accountNumber));

        close.setForeground(Color.BLUE);
        close.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                try {
                    LocalDateTime now = LocalDateTime.now();
                    int day = now.getDayOfMonth();
                    int month = now.getMonthValue();
                    int year = now.getYear();

                    bank.closeAccount(customer, accountNumber, day, month, year);
                    accountNumber = null;
                    displayAccounts("Overview", "");
                } catch (Exception e) {
                    warning.setForeground(Color.RED);
                    warning.setText(e.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                close.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                close.setForeground(Color.BLUE);
            }
        });
    }

    public void displayAccountsHistory() {
        history = new JScrollPane();
        depositValue = new JTextField();
        deposit = new JButton("DEPOSIT");
        withdrawValue = new JTextField();
        withdraw = new JButton("WITHDRAW");

        add(history);
        add(depositValue);
        add(deposit);
        add(withdrawValue);
        add(withdraw);

        history.setBounds (155, 140, 580, 325);
        depositValue.setBounds (250, 480, 190, 25);
        deposit.setBounds (440, 480, 190, 25);
        withdrawValue.setBounds (250, 525, 190, 25);
        withdraw.setBounds (440, 525, 190, 25);

        String[] column = {"DATE", "FROM", "TO", "AMOUNT", "AVAILABLE"};
        String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
        JTable transactionsTable = new JTable(data, column);
        history.getViewport().add(transactionsTable);

        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double value = Double.parseDouble(depositValue.getText());
                    LocalDateTime now = LocalDateTime.now();
                    int day = now.getDayOfMonth();
                    int month = now.getMonthValue();
                    int year = now.getYear();
                    bank.depositToAccount(customer, accountNumber, value, day, month, year);
                    depositValue.setText("Success");
                    displayAccounts("History", "");
                } catch (Exception exception) {
                    depositValue.setText(exception.getMessage());
                }
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double value = Double.parseDouble(withdrawValue.getText());
                    LocalDateTime now = LocalDateTime.now();
                    int day = now.getDayOfMonth();
                    int month = now.getMonthValue();
                    int year = now.getYear();
                    bank.withdrawFromAccount(customer, accountNumber, value, day, month, year);
                    withdrawValue.setText("Success");
                    displayAccounts("History", "");
                } catch (Exception exception) {
                    withdrawValue.setText(exception.getMessage());
                }
            }
        });
    }

    public void displayAccountsTransfer() {
        fromLabel = new JLabel("FROM");
        fromText = new JTextField();
        toLabel = new JLabel("TO");
        toText = new JTextField();
        amountLabel = new JLabel("Amount");
        amountText = new JTextField();
        currencyLabel = new JLabel("Currency");
        currencyText = new JTextField();
        warning = new JLabel();
        transfer = new JButton("Make Transfer");

        add(fromLabel);
        add(fromText);
        add(toLabel);
        add(toText);
        add(amountLabel);
        add(amountText);
        add(currencyLabel);
        add(currencyText);
        add(warning);
        add(transfer);

        fromLabel.setBounds (310, 200, 255, 25);
        fromText.setBounds (310, 225, 255, 25);
        toLabel.setBounds (310, 250, 255, 25);
        toText.setBounds (310, 275, 255, 25);
        amountLabel.setBounds (310, 300, 255, 25);
        amountText.setBounds (310, 325, 255, 25);
        currencyLabel.setBounds (310, 350, 255, 25);
        currencyText.setBounds (310, 375, 255, 25);
        warning.setBounds (160, 400, 575, 30);
        transfer.setBounds (365, 450, 135, 25);

        fromText.setEditable(false);
        fromText.setText(accountNumber);

        currencyText.setEditable(false);
        currencyText.setText(bank.getAccountCurrencyAbbrByCustomerAccountNumber(customer, accountNumber));

        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    double value = Double.parseDouble(amountText.getText());
                    LocalDateTime now = LocalDateTime.now();
                    int day = now.getDayOfMonth();
                    int month = now.getMonthValue();
                    int year = now.getYear();
                    bank.transferValue(customer, accountNumber, toText.getText(), value, day, month, year);
                    warning.setText("Success!");
                    warning.setForeground(Color.GREEN);
                } catch (Exception exception) {
                    warning.setText(exception.getMessage());
                    warning.setForeground(Color.RED);
                }
            }
        });
    }

    public void displayAccountsSecurity(String tab) {
        if (true) { // have already opened a security account
            displaySecurity();
            if (tab.equals("Overview")) {
                displaySecurityOverview();
            } else if (tab.equals("Market")) {
                displaySecurityMarket();
            } else if (tab.equals("myStock")) {
                displaySecurityMyStock();
            } else if (tab.equals("Buy")) {
                displaySecurityBuy();
            } else if (tab.equals("Sell")) {
                displaySecuritySell();
            } else if (tab.equals("History")) {
                displaySecurityHistory();
            }
        } else if (true) { // able to open an account
            displaySecurityEligible();
        } else { // unable to open an account
            displaySecurityNotEligible();
        }
    }

    public void displaySecurityNotEligible() {
        securityNotice = new JLabel("Sorry, you are not allowed to open a security account.");

        add(securityNotice);

        securityNotice.setBounds (155, 145, 580, 25);
    }

    public void displaySecurityEligible() {
        securityNotice = new JLabel("Congratulations! You are eligible to apply for a security account!");
        securityOpen = new JButton("Open a security Account");

        add(securityNotice);
        add(securityOpen);

        securityNotice.setBounds (155, 145, 580, 25);
        securityOpen.setBounds (330, 320, 220, 25);

        securityOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // open account
                displayAccounts("Security", "Overview");
            }
        });
    }

    public void displaySecurity() {
        securityOverview = new JButton("Overview");
        market = new JButton("Market");
        myStock = new JButton("My stocks");
        buy = new JButton("Buy");
        sell = new JButton("Sell");
        securityHistory = new JButton("History");
        divider3 = new JSeparator(SwingConstants.VERTICAL);

        add(securityOverview);
        add(market);
        add(myStock);
        add(buy);
        add(sell);
        add(securityHistory);
        add(divider3);

        securityOverview.setBounds (155, 165, 100, 25);
        market.setBounds (155, 210, 100, 25);
        myStock.setBounds (155, 255, 100, 25);
        buy.setBounds (155, 300, 100, 25);
        sell.setBounds (155, 345, 100, 25);
        securityHistory.setBounds (155, 390, 100, 25);
        divider3.setBounds (265, 140, 1, 450);

        securityOverview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Security", "Overview");
            }
        });

        market.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Security", "Market");
            }
        });

        myStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Security", "myStock");
            }
        });

        buy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Security", "Buy");
            }
        });

        sell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Security", "Sell");
            }
        });

        securityHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayAccounts("Security", "History");
            }
        });
    }

    public void displaySecurityOverview() {
        accountNumberLabel = new JLabel("Binding saving account number");
        accountNumberText = new JTextField();
        dateOpenLabel = new JLabel("Open date");
        dateOpenText = new JTextField();
        stocksNumberLabel = new JLabel("# of stocks in Hand");
        stocksNumberText = new JTextField();
        stocksValueLabel = new JLabel("Total value of stocks in Hand");
        stocksValueText = new JTextField();
        closeNotice = new JLabel("Want to close this security account?");
        close = new JLabel("CLOSE");

        add(accountNumberLabel);
        add(accountNumberText);
        add(dateOpenLabel);
        add(dateOpenText);
        add(stocksNumberLabel);
        add(stocksNumberText);
        add(stocksValueLabel);
        add(stocksValueText);
        add(closeNotice);
        add(close);

        accountNumberLabel.setBounds (295, 165, 235, 25);
        accountNumberText.setBounds (295, 190, 235, 25);
        dateOpenLabel.setBounds (295, 215, 235, 25);
        dateOpenText.setBounds (295, 240, 235, 25);
        stocksNumberLabel.setBounds (295, 265, 235, 25);
        stocksNumberText.setBounds (295, 290, 235, 25);
        stocksValueLabel.setBounds (295, 315, 235, 30);
        stocksValueText.setBounds (295, 345, 235, 25);
        closeNotice.setBounds (295, 545, 250, 25);
        close.setBounds (550, 545, 100, 25);

        accountNumberText.setEditable(false);
        accountNumberText.setText(accountNumber);

        dateOpenText.setEditable(false);
        dateOpenText.setText("..");  // getText

        stocksNumberText.setEditable(false);
//        stocksNumberText.setText(Integer.toString(bank.getNumStockHoldingByAccountNumber(customer, securityAccountNumber))); // get stocks number

        stocksValueText.setEditable(false);
//        stocksValueText.setText(Double.toString(bank.getStockTotalValueByCustomerAccountNumber(customer, securityAccountNumber))); // get stocks value

        close.setForeground(Color.BLUE);
        close.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                close.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                close.setForeground(Color.BLUE);
            }
        });
    }

    public void displaySecurityMarket() {
        marketList = new JScrollPane();

        add(marketList);

        marketList.setBounds (290, 155, 445, 405);

        // add market content
        String[] column = {"ID", "NAME", "AVAILABLE", "BUYPRICE", "SELLPRICE"};
        String[][] data = bank.getShowMarket();
        JTable transactionsTable = new JTable(data, column);
        marketList.getViewport().add(transactionsTable);
    }

    public void displaySecurityMyStock() {
        myStockList = new JScrollPane();

        add(myStockList);

        myStockList.setBounds (290, 155, 445, 405);

//        String[] column = {"STOCKID", "STOCKNAME ", "MYSTOCKCOUNTS"};
//        String[][] data = bank.getCustomerMyStocksByCustomerAccountNumber(customer, securityAccountNumber);
//        JTable transactionsTable = new JTable(data, column);
//        myStockList.getViewport().add(transactionsTable);
    }

    public void displaySecurityBuy() {
        buyStock = new JLabel("BUY STOCKS");
        stockIdLabel = new JLabel("Stock ID");
        stockIdText = new JTextField();
        buyPriceLabel = new JLabel("Current single buy price");
        buyPriceText = new JTextField();
        buyNumberLabel = new JLabel("# to buy");
        buyNumberText = new JTextField();
        warning = new JLabel();
        buyButton = new JButton("BUY");

        add(buyStock);
        add(stockIdLabel);
        add(stockIdText);
        add(buyPriceLabel);
        add(buyPriceText);
        add(buyNumberLabel);
        add(buyNumberText);
        add(warning);
        add(buyButton);

        buyStock.setBounds (305, 165, 210, 25);
        stockIdLabel.setBounds (305, 195, 210, 25);
        stockIdText.setBounds (305, 220, 210, 25);
        buyPriceLabel.setBounds (305, 245, 210, 25);
        buyPriceText.setBounds (305, 270, 210, 25);
        buyNumberLabel.setBounds (305, 295, 210, 25);
        buyNumberText.setBounds (305, 320, 210, 25);
        warning.setBounds (305, 345, 405, 30);
        buyButton.setBounds (355, 435, 100, 25);

        stockIdText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                 int StockId = Integer.parseInt(stockIdText.getText());
                 Stock stock = bank.getStockById(StockId);
                 buyPriceText.setText(Double.toString(bank.getBuyPriceByStock(stock)));
            }
        });

        buyPriceText.setEditable(false);

//        buyButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    String stockId = stockIdText.getText();
//                    int num = Integer.parseInt(buyNumberText.getText());
//                    LocalDateTime now = LocalDateTime.now();
//                    int day = now.getDayOfMonth();
//                    int month = now.getMonthValue();
//                    int year = now.getYear();
//                    bank.buyStockByAccountNumber(customer, securityAccountNumber, stockId, num, day, month, year);
//                    warning.setForeground(Color.GREEN);
//                    warning.setText("Success");
//                } catch (Exception e) {
//                    warning.setForeground(Color.RED);
//                    warning.setText(e.getMessage());
//                }
//            }
//        });
    }

    public void displaySecuritySell() {
        sellStock = new JLabel("SELL STOCKS");
        stockIdLabel = new JLabel("Stock ID");
        stockIdText = new JTextField();
        sellPriceLabel = new JLabel("Current single sell price");
        sellPriceText = new JTextField();
        moneySpentLabel = new JLabel("Value spent");
        moneySpentText = new JTextField();
        numberInHandLabel = new JLabel("# in Hand");
        numberInHandText = new JTextField();
        sellNumberLabel = new JLabel("# to sell");
        sellNumberText = new JTextField();
        warning = new JLabel();
        sellButton = new JButton("SELL");

        add(sellStock);
        add(stockIdLabel);
        add(stockIdText);
        add(sellPriceLabel);
        add(sellPriceText);
        add(moneySpentLabel);
        add(moneySpentText);
        add(numberInHandLabel);
        add(numberInHandText);
        add(sellNumberLabel);
        add(sellNumberText);
        add(warning);
        add(sellButton);

        sellStock.setBounds (305, 165, 210, 25);
        stockIdLabel.setBounds (305, 195, 210, 25);
        stockIdText.setBounds (305, 220, 210, 25);
        sellPriceLabel.setBounds (305, 245, 210, 25);
        sellPriceText.setBounds (305, 270, 210, 25);
        moneySpentLabel.setBounds (305, 295, 210, 25);
        moneySpentText.setBounds (305, 320, 210, 25);
        numberInHandLabel.setBounds (515, 295, 210, 25);
        numberInHandText.setBounds (515, 320, 210, 25);
        sellNumberLabel.setBounds (305, 345, 210, 25);
        sellNumberText.setBounds (305, 370, 210, 25);
        warning.setBounds (305, 395, 420, 25);
        sellButton.setBounds (355, 435, 100, 25);

//        stockIdText.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                int StockId = Integer.parseInt(stockIdText.getText());
//                Stock stock = bank.getStockById(StockId);
//                sellPriceText.setText(Double.toString(bank.getSellPriceByStock(stock))); // set Price
//                moneySpentText.setText(bank.getAvgBoughtPriceByStockIdByCustomerAccountNumber(customer, securityAccountNumber, stockIdText.getText())); // ....
//                numberInHandText.setText("sss"); // ...
//            }
//        });

        sellPriceText.setEditable(false);

        moneySpentText.setEditable(false);

        numberInHandText.setEditable(false);

//        sellButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                try {
//                    // sell this stock
//                    String stockId = stockIdText.getText();
//                    int num = Integer.parseInt(sellNumberText.getText());
//                    LocalDateTime now = LocalDateTime.now();
//                    int day = now.getDayOfMonth();
//                    int month = now.getMonthValue();
//                    int year = now.getYear();
//                    bank.sellStockByAccountNumber(customer, securityAccountNumber, stockId, num, day, month, year);
//                    warning.setForeground(Color.GREEN);
//                    warning.setText("Success");
//                } catch (Exception e) {
//                    warning.setForeground(Color.RED);
//                    warning.setText(e.getMessage());
//                }
//            }
//        });
    }

    public void displaySecurityHistory() {
        stockHistory = new JScrollPane();

        add(stockHistory);

        stockHistory.setBounds (290, 155, 445, 405);

//        String[] column = {"DATE", "FROM", "TO", "STOCKID", "UNITPRICE", "NUMBER OF SHARES"};
//        String[][] data = bank.getStockTransactionHistoryByCustomerAccountNumber(customer, securityAccountNumber);
//        JTable transactionsTable = new JTable(data, column);
//        stockHistory.getViewport().add(transactionsTable);
    }
}
