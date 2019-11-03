import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class GUIBankCustomerPanelAccountsPanelSummaryPanelWithdrawEvent extends GUIBankEvent {
    // instance
    private String accountNumber;
    private JTextField withdrawField;
    private GUIBankCustomerPanelAccountsPanel panel;

    // constructor
    public GUIBankCustomerPanelAccountsPanelSummaryPanelWithdrawEvent(GUIBankATMFrame frame,
                                                                      GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
    }

    // mutator function
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setWithdrawField(JTextField withdrawField) {
        this.withdrawField = withdrawField;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        GUIBankATMFrame frame = getFrame();
        BankCustomer customer = frame.getCustomer();

        String valueStr = withdrawField.getText();
        try {
            double value = Double.parseDouble(valueStr);
            LocalDateTime now = LocalDateTime.now();
            int day = now.getDayOfMonth();
            int month = now.getMonthValue();
            int year = now.getYear();
            bank.withdrawFromAccount(customer, accountNumber, value, day, month, year);
            withdrawField.setText("Success");
            panel.showSummary();
        } catch (Exception exception) {
            withdrawField.setText(exception.getMessage());
        }
    }
}
