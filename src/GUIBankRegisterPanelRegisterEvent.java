import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * event when user clicks REGISTER button in register panel
 */
public class GUIBankRegisterPanelRegisterEvent extends GUIBankEvent {
    // instance variables with private access
    private JTextField usernameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField password2Field;
    private JLabel feedbackLabel;

    // constructor
    public GUIBankRegisterPanelRegisterEvent(GUIBankATMFrame frame) {
        super(frame);
    }

    // mutator function
    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public void setPasswordField(JTextField passwordField) {
        this.passwordField = passwordField;
    }

    public void setPassword2Field(JTextField password2Field) {
        this.password2Field = password2Field;
    }

    public void setFeedbackLabel(JLabel feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    // primary function
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String password2 = password2Field.getText().trim();
        Bank bank = Bank.getInstance();
        if (!password.equals(password2)) {
            feedbackLabel.setText("Passwords must match!");
            feedbackLabel.setForeground(Color.RED);
        } else {
            try {
                bank.registerNewCustomer(username, password, email);
                feedbackLabel.setText("Success!");
                feedbackLabel.setForeground(Color.GREEN);
            } catch (IllegalArgumentException ex) {
                feedbackLabel.setText(ex.getMessage());
                feedbackLabel.setForeground(Color.RED);
            }
        }
    }
}
