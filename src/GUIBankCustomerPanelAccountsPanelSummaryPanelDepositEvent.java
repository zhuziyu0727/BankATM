import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class GUIBankCustomerPanelAccountsPanelSummaryPanelDepositEvent extends GUIBankEvent {
    // instance
    private String accountNumber;
    private JTextField depositField;
    private GUIBankCustomerPanelAccountsPanel panel;


    // constructor
    public GUIBankCustomerPanelAccountsPanelSummaryPanelDepositEvent(GUIBankATMFrame frame,
                                                                     GUIBankCustomerPanelAccountsPanel panel) {
        super(frame);
        this.panel = panel;
    }

    // mutator function
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setDepositField(JTextField depositField) {
        this.depositField = depositField;
    }

    // event function
    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        GUIBankATMFrame frame = getFrame();
        BankCustomer customer = frame.getCustomer();

        String valueStr = depositField.getText();
        try {
            double value = Double.parseDouble(valueStr);
            LocalDateTime now = LocalDateTime.now();
            int day = now.getDayOfMonth();
            int month = now.getMonthValue();
            int year = now.getYear();
            bank.depositToAccount(customer, accountNumber, value, day, month, year);
            depositField.setText("Success");
            panel.showSummary();
        } catch (Exception exception) {
            depositField.setText(exception.getMessage());
        }

    }
}
