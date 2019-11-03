import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

/**
 * when user clicks transfer button
 */
public class GUIBankCustomerPanelTransferPanelTransferEvent extends GUIBankEvent {
    // instance variables
    private JComboBox accountComboBox;
    private JTextField targetAccountNumberField;
    private JTextField valueField;
    private JLabel feedbackLabel;
    private GUIBankCustomerPanel customerPanel;

    // constructor
    public GUIBankCustomerPanelTransferPanelTransferEvent(GUIBankATMFrame frame, GUIBankCustomerPanel customerPanel) {
        super(frame);
        this.customerPanel = customerPanel;
    }

    // mutator functions
    public void setAccountComboBox(JComboBox accountComboBox) {
        this.accountComboBox = accountComboBox;
    }

    public void setTargetAccountNumberField(JTextField targetAccountNumberField) {
        this.targetAccountNumberField = targetAccountNumberField;
    }

    public void setValueField(JTextField valueField) {
        this.valueField = valueField;
    }

    public void setFeedbackLabel(JLabel feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    // event action
    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        GUIBankATMFrame frame = getFrame();
        BankCustomer customer = frame.getCustomer();

        String fromAccountNumber = accountComboBox.getSelectedItem().toString();
        String toAccountNumber = targetAccountNumberField.getText();
        String valueStr = valueField.getText();
        try {
            double value = Double.parseDouble(valueStr);
            LocalDateTime now = LocalDateTime.now();
            int day = now.getDayOfMonth();
            int month = now.getMonthValue();
            int year = now.getYear();
            bank.transferValue(customer, fromAccountNumber, toAccountNumber, value, day, month, year);
            feedbackLabel.setText("Success!");
            feedbackLabel.setForeground(Color.GREEN);
            customerPanel.getAccountsPanel().showSummary();
        } catch (Exception exception) {
            feedbackLabel.setText(exception.getMessage());
            feedbackLabel.setForeground(Color.RED);
        }
    }
}
