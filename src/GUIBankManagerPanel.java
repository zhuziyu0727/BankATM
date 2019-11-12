import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class GUIBankManagerPanel extends GUIBankPanel {
    private Bank bank;
    private String customerUserName;
    private int currentStockId;

    private JLabel welcome1;
    private JLabel welcome2;
    private JButton history;
    private JButton savInt;
    private JButton loanInt;
    private JButton stocks;
    private JButton exit;
    private JSeparator divider1;
    private JLabel logo;
    private JSeparator divider2;
    private JComboBox customersList;
    private JScrollPane customerHistory;

    private JButton market;
    private JButton changePrice;
    private JButton mutator;
    private JScrollPane marketList;

    private JComboBox stockList;
    private JLabel stockNameLabel;
    private JTextField stockNameText;
    private JLabel buyPriceLabel;
    private JTextField buyPriceText;
    private JLabel sellPriceLabel;
    private JTextField sellPriceText;
    private JLabel numOwnedLabel;
    private JTextField numOwnedText;
    private JLabel numVoidLabel;
    private JTextField numVoidText;
    private JLabel warning;
    private JButton update;

    private JLabel stockIdLabel;
    private JTextField stockIdText;
    private JButton delete;
    // buyPriceLabel
    // buyPriceText
    // sellPriceLabel
    // sellPriceText
    private JLabel numPutLabel;
    private JTextField numPutText;
    // warning
    private JButton add;

    public GUIBankManagerPanel(GUIBankATMFrame frame) {
        super(frame);
        bank = Bank.getInstance();
        displayHistory();
    }

    public void basicDisplay() {
        removeAll();
        revalidate();
        repaint();

        welcome1 = new JLabel("Welcome!");
        welcome2 = new JLabel("manager");
        history = new JButton("History");
        savInt = new JButton("Sav. Int.");
        loanInt = new JButton("Loan Int.");
        stocks = new JButton("Stocks");
        exit = new JButton("EXIT");
        divider1 = new JSeparator(SwingConstants.VERTICAL);
        logo = new JLabel("Bank ATM");
        divider2 = new JSeparator(SwingConstants.HORIZONTAL);

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        add(welcome1);
        add(welcome2);
        add(history);
        add(savInt);
        add(loanInt);
        add(stocks);
        add(exit);
        add(divider1);
        add(logo);
        add(divider2);

        welcome1.setBounds (20, 15, 100, 25);
        welcome2.setBounds (20, 40, 100, 25);
        history.setBounds (20, 100, 100, 25);
        savInt.setBounds (20, 145, 100, 25);
        loanInt.setBounds (20, 190, 100, 25);
        stocks.setBounds (20, 235, 100, 25);
        exit.setBounds (20, 545, 100, 25);
        divider1.setBounds (130, 10, 1, 580);
        logo.setBounds (155, 15, 100, 25);
        divider2.setBounds (140, 40, 650, 100);

        savInt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LocalDateTime now = LocalDateTime.now();
                int day = now.getDayOfMonth();
                int month = now.getMonthValue();
                int year = now.getYear();
                bank.increaseSaving(day, month, year);
                displayHistory();
            }
        });

        loanInt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LocalDateTime now = LocalDateTime.now();
                int day = now.getDayOfMonth();
                int month = now.getMonthValue();
                int year = now.getYear();
                bank.increaseLoan(day, month, year);
                displayHistory();
            }
        });

        stocks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stockMarket();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.showLogin();
            }
        });
    }

    public void displayHistory() {
        basicDisplay();

        customersList = new JComboBox();
        customerHistory = new JScrollPane();

        add(customersList);
        add(customerHistory);

        customersList.setBounds (155, 65, 580, 25);
        customerHistory.setBounds (155, 100, 580, 465);

        ArrayList<BankCustomer> customers = bank.getCustomers();

        if (customers.size() != 0) {
            for (BankCustomer customer: customers) {
                customersList.addItem(bank.getUsernameByCustomer(customer));
            }
            if (customerUserName == null) {
                customerUserName = (String) customersList.getSelectedItem();
            }
            customersList.setSelectedItem(customerUserName);

            customersList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    String newCustomerName = (String) customersList.getSelectedItem();
                    if (newCustomerName != customerUserName) {
                        customerUserName = newCustomerName;
                        displayHistory();
                    }
                }
            });

            BankCustomer customer = bank.getCustomerByUsername(customerUserName);

            String[] column = {"CUSTOMER", "ACCOUNT", "TYPE", "DATE", "FROM", "TO", "AMOUNT", "AVAILABLE"};
            int totalSize = 0;

            ArrayList<String> allAccountNumbers = bank.getAllAccountNumbersByCustomer(customer);
            for (String accountNumber: allAccountNumbers) {
                String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
                totalSize += data.length;
            }

            String[][] wholeData = new String[totalSize][8];
            int rowNumber = 0;
            String customerNumber = bank.getCustomerNumberByCustomer(customer);
            for (String accountNumber: allAccountNumbers) {
                String type = bank.getAccountTypeByCustomerAccountNumber(customer, accountNumber);
                String[][] data = bank.getTransactionHistoryByCustomerAccountNumber(customer, accountNumber);
                for (int j=0; j<data.length; j++) {

                    String[] row = new String[8];
                    row[0] = customerNumber;
                    row[1] = accountNumber;
                    row[2] = type;
                    for (int i=0; i<5; i++) {
                        row[i+3] = data[j][i];
                    }
                    wholeData[rowNumber++] = row;
                }
            }
            JTable wholeTransactions = new JTable(wholeData, column);

            customerHistory.getViewport().add(wholeTransactions);
        }
    }

    public void displayStocks() {
        basicDisplay();

        market = new JButton("Market");
        changePrice = new JButton("Change Price");
        mutator = new JButton("Add or Delete");

        add(market);
        add(changePrice);
        add(mutator);

        market.setBounds (175, 100, 170, 25);
        changePrice.setBounds (350, 100, 170, 25);
        mutator.setBounds (525, 100, 170, 25);

        market.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stockMarket();
            }
        });

        changePrice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stockChangePrice();
            }
        });

        mutator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stockMutate();
            }
        });
    }

    public void stockMarket() {
        displayStocks();

        marketList = new JScrollPane();

        add(marketList);

        marketList.setBounds (175, 145, 520, 425);

        String[] column = {"ID", "NAME", "AVAILABLE", "BUYPRICE", "SELLPRICE"};
        String[][] data = bank.getShowMarket();
        JTable transactionsTable = new JTable(data, column);
        marketList.getViewport().add(transactionsTable);
    }

    public void stockChangePrice() {
        displayStocks();

        stockList = new JComboBox();
        stockNameLabel = new JLabel("Stock name");
        stockNameText = new JTextField();
        buyPriceLabel = new JLabel("Buy price");
        buyPriceText = new JTextField();
        sellPriceLabel = new JLabel("Sell price");
        sellPriceText = new JTextField();
        numOwnedLabel = new JLabel("# bought by customers");
        numOwnedText = new JTextField();
        numVoidLabel = new JLabel("# still in market");
        numVoidText = new JTextField();
        warning = new JLabel();
        update = new JButton("UPDATE");

        add(stockList);
        add(stockNameLabel);
        add(stockNameText);
        add(buyPriceLabel);
        add(buyPriceText);
        add(sellPriceLabel);
        add(sellPriceText);
        add(numOwnedLabel);
        add(numOwnedText);
        add(numVoidLabel);
        add(numVoidText);
        add(warning);
        add(update);

        stockList.setBounds (175, 145, 250, 25);
        stockNameLabel.setBounds(175, 170, 250, 25);
        stockNameText.setBounds(175, 195, 250, 25);
        buyPriceLabel.setBounds (175, 220, 250, 25);
        buyPriceText.setBounds (175, 245, 250, 25);
        sellPriceLabel.setBounds (175, 270, 250, 25);
        sellPriceText.setBounds (175, 295, 250, 25);
        numOwnedLabel.setBounds (175, 320, 250, 25);
        numOwnedText.setBounds (175, 345, 250, 25);
        numVoidLabel.setBounds (175, 370, 250, 25);
        numVoidText.setBounds (175, 390, 250, 25);
        warning.setBounds (175, 415, 405, 30);
        update.setBounds (175, 435, 100, 25);

        ArrayList<Stock> stocks = bank.getAllStocks();

        if (stocks.size() != 0) {
            for (Stock each: stocks) {
                stockList.addItem(bank.getIdByStock(each));
            }
            if (currentStockId == 0) {
                currentStockId = (int) stockList.getSelectedItem();
            }

            stockList.setSelectedItem(currentStockId);
            stockList.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    currentStockId = (int) stockList.getSelectedItem();
                    stockChangePrice();
                }
            });

            Stock currentStock = bank.getStockById(currentStockId);

            stockNameText.setEditable(false);
            stockNameText.setText(bank.getNameByStock(currentStock));

            buyPriceText.setText(Double.toString(bank.getBuyPriceByStock(currentStock)));

            sellPriceText.setText(Double.toString(bank.getSellPriceByStock(currentStock)));

            numOwnedText.setEditable(false);
            numOwnedText.setText(Integer.toString(bank.getNumOwnedByCustomers(currentStock)));

            numVoidText.setEditable(false);
            numVoidText.setText(Integer.toString(bank.getNumAvailable(currentStock)));

            update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        double newBuyPrice = Double.parseDouble(buyPriceText.getText());
                        double newSellPrice = Double.parseDouble(sellPriceText.getText());
                        bank.modifyStockBuyPrice(currentStock, newBuyPrice);
                        bank.modifyStockSellPrice(currentStock, newSellPrice);
                        warning.setForeground(Color.GREEN);
                        warning.setText("Success");
                    } catch (Exception e) {
                        warning.setForeground(Color.RED);
                        warning.setText(e.getMessage());
                    }
                }
            });
        }
    }

    public void stockMutate() {
        displayStocks();

        stockIdLabel = new JLabel("Stock ID");
        stockIdText = new JTextField();
        delete = new JButton("DELETE");
        stockNameLabel = new JLabel("Stock name");
        stockNameText = new JTextField();
        buyPriceLabel = new JLabel("Buy price");
        buyPriceText = new JTextField();
        sellPriceLabel = new JLabel("Sell price");
        sellPriceText = new JTextField();
        numPutLabel = new JLabel("# to put");
        numPutText = new JTextField();
        warning = new JLabel();
        add = new JButton("ADD");

        add(stockIdLabel);
        add(stockIdText);
        add(delete);
        add(stockNameLabel);
        add(stockNameText);
        add(buyPriceLabel);
        add(buyPriceText);
        add(sellPriceLabel);
        add(sellPriceText);
        add(numPutLabel);
        add(numPutText);
        add(warning);
        add(add);

        stockIdLabel.setBounds (175, 155, 235, 25);
        stockIdText.setBounds (175, 180, 235, 25);
        delete.setBounds (565, 180, 130, 25);
        stockNameLabel.setBounds(175, 205, 235, 25);
        stockNameText.setBounds(175, 230, 235, 25);
        buyPriceLabel.setBounds (175, 255, 235, 25);
        buyPriceText.setBounds (175, 280, 235, 25);
        sellPriceLabel.setBounds (175, 305, 235, 25);
        sellPriceText.setBounds (175, 330, 235, 25);
        numPutLabel.setBounds (175, 355, 235, 25);
        numPutText.setBounds (175, 380, 235, 25);
        warning.setBounds (175, 405, 520, 25);
        add.setBounds (175, 445, 100, 25);

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int id = Integer.parseInt(stockIdText.getText());
                    bank.deleteStock(id);
                    warning.setForeground(Color.GREEN);
                    warning.setText("Success");
                } catch (Exception e) {
                    warning.setForeground(Color.RED);
                    warning.setText(e.getMessage());
                }
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    int id = Integer.parseInt(stockIdText.getText());
                    String name = stockNameText.getText();
                    int num = Integer.parseInt(numPutText.getText());
                    double buy = Double.parseDouble(buyPriceText.getText());
                    double sell = Double.parseDouble(sellPriceText.getText());
                    bank.addStock(id, name, num, buy, sell);
                    warning.setForeground(Color.GREEN);
                    warning.setText("Success");
                } catch (Exception e) {
                    warning.setForeground(Color.RED);
                    warning.setText(e.getMessage());
                }
            }
        });
    }
}
