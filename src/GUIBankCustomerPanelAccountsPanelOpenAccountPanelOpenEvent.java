import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

/**
 * when user clicks open in open account page
 */
public class GUIBankCustomerPanelAccountsPanelOpenAccountPanelOpenEvent extends GUIBankEvent {
    // instance variables with private access
    private JComboBox accountTypeComboBox;
    private JComboBox currencyTypeComboBox;
    private JTextField valueField;
    private JLabel feedbackLabel;
    private GUIBankCustomerPanel customerPanel;

    // constructor
    public GUIBankCustomerPanelAccountsPanelOpenAccountPanelOpenEvent(GUIBankATMFrame frame, GUIBankCustomerPanel customerPanel) {
        super(frame);
        this.customerPanel = customerPanel;
    }

    // mutator function
    public void setAccountTypeComboBox(JComboBox accountTypeComboBox) {
        this.accountTypeComboBox = accountTypeComboBox;
    }

    public void setCurrencyTypeComboBox(JComboBox currencyTypeComboBox) {
        this.currencyTypeComboBox = currencyTypeComboBox;
    }

    public void setValueField(JTextField valueField) {
        this.valueField = valueField;
    }

    public void setFeedbackLabel(JLabel feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        GUIBankATMFrame frame = getFrame();
        BankCustomer customer = frame.getCustomer();

        String accountType = accountTypeComboBox.getSelectedItem().toString();
        String currencyType = currencyTypeComboBox.getSelectedItem().toString();
        String valueStr = valueField.getText();

        try {
            double value = Double.parseDouble(valueStr);
            LocalDateTime now = LocalDateTime.now();
            int day = now.getDayOfMonth();
            int month = now.getMonthValue();
            int year = now.getYear();
            String accountNumber = bank.openAccount(customer, accountType, currencyType, value, day, month, year);
            String routingNumber = frame.getRoutingNumber();
            bank.setRoutingNumberByCustomerAccountNumber(customer, accountNumber, routingNumber);
            feedbackLabel.setText("Success");
            feedbackLabel.setForeground(Color.GREEN);
            customerPanel.getTransferPanel().refresh();
        } catch (Exception exception) {
            feedbackLabel.setText(exception.getMessage());
            feedbackLabel.setForeground(Color.RED);
        }
    }
}
