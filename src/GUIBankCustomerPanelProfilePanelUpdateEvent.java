import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * when user clicks update in the profile panel in customer panel
 */
public class GUIBankCustomerPanelProfilePanelUpdateEvent extends GUIBankEvent {
    // instance variables with private access
    private JTextField passwordField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField address1Field;
    private JTextField address2Field;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipCodeField;
    private JTextField countryField;
    private JLabel feedbackLabel;

    // constructor
    public GUIBankCustomerPanelProfilePanelUpdateEvent(GUIBankATMFrame frame) {
        super(frame);
    }

    // mutator function
    public void setPasswordField(JTextField passwordField) {
        this.passwordField = passwordField;
    }

    public void setEmailField(JTextField emailField) {
        this.emailField = emailField;
    }

    public void setPhoneField(JTextField phoneField) {
        this.phoneField = phoneField;
    }

    public void setAddress1Field(JTextField address1Field) {
        this.address1Field = address1Field;
    }

    public void setAddress2Field(JTextField address2Field) {
        this.address2Field = address2Field;
    }

    public void setCityField(JTextField cityField) {
        this.cityField = cityField;
    }

    public void setStateField(JTextField stateField) {
        this.stateField = stateField;
    }

    public void setZipCodeField(JTextField zipCodeField) {
        this.zipCodeField = zipCodeField;
    }

    public void setCountryField(JTextField countryField) {
        this.countryField = countryField;
    }

    public void setFeedbackLabel(JLabel feedbackLabel) {
        this.feedbackLabel = feedbackLabel;
    }

    // event function
    public void actionPerformed(ActionEvent e) {
        String password = passwordField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address1 = address1Field.getText();
        String address2 = address2Field.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String zipCode = zipCodeField.getText();
        String country = countryField.getText();

        phone = phone.replaceAll("\\(", "");
        phone = phone.replaceAll("\\)", "");
        phone = phone.replaceAll("-", "");

        Bank bank = Bank.getInstance();
        BankCustomer customer = getFrame().getCustomer();

        String originalPhone = bank.getPhoneByCustomer(customer);
        originalPhone = originalPhone.replaceAll("\\(", "");
        originalPhone = originalPhone.replaceAll("\\)", "");
        originalPhone = originalPhone.replaceAll("-", "");

        try {
            if (!password.equals(bank.getPasswordByCustomer(customer)))
                bank.setCustomerPassword(customer, password);
            if (!email.equals(bank.getEmailByCustomer(customer)))
                bank.setCustomerEmail(customer, email);
            if (!phone.equals(originalPhone)) {
                bank.setCustomerPhone(customer, phone);
                phoneField.setText(bank.getPhoneByCustomer(customer));
            }
            if (!address1.equals(bank.getAddress1ByCustomer(customer)))
                bank.setCustomerAddress1(customer, address1);
            if (!address2.equals(bank.getAddress2ByCustomer(customer)))
                bank.setCustomerAddress2(customer, address2);
            if (!city.equals(bank.getCityByCustomer(customer)))
                bank.setCustomerCity(customer, city);
            if (!state.equals(bank.getStateByCustomer(customer)))
                bank.setCustomerState(customer, state);
            if (!zipCode.equals(bank.getZipCodeByCustomer(customer)))
                bank.setCustomerZipCode(customer, zipCode);
            if (!country.equals(bank.getCountryByCustomer(customer)))
                bank.setCustomerCountry(customer, country);
            feedbackLabel.setText("Success!");
            feedbackLabel.setForeground(Color.GREEN);
        } catch (Exception ex) {
            feedbackLabel.setText(ex.getMessage());
            feedbackLabel.setForeground(Color.RED);
        }
    }
}
