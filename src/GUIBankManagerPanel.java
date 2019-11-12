import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GUIBankManagerPanel extends GUIBankPanel {
    private Bank bank;
    private String customerUserName;

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
        buyPriceLabel.setBounds (175, 170, 250, 25);
        buyPriceText.setBounds (175, 195, 250, 25);
        sellPriceLabel.setBounds (175, 220, 250, 25);
        sellPriceText.setBounds (175, 245, 250, 25);
        numOwnedLabel.setBounds (175, 270, 250, 25);
        numOwnedText.setBounds (175, 295, 250, 25);
        numVoidLabel.setBounds (175, 320, 250, 25);
        numVoidText.setBounds (175, 345, 250, 25);
        warning.setBounds (175, 370, 405, 30);
        update.setBounds (175, 410, 100, 25);
    }

    public void stockMutate() {
        displayStocks();

        stockIdLabel = new JLabel("Stock ID");
        stockIdText = new JTextField();
        delete = new JButton("DELETE");
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
        buyPriceLabel.setBounds (175, 205, 235, 25);
        buyPriceText.setBounds (175, 230, 235, 25);
        sellPriceLabel.setBounds (175, 255, 235, 25);
        sellPriceText.setBounds (175, 280, 235, 25);
        numPutLabel.setBounds (175, 305, 235, 25);
        numPutText.setBounds (175, 330, 235, 25);
        warning.setBounds (175, 355, 520, 25);
        add.setBounds (175, 395, 100, 25);
    }
}
