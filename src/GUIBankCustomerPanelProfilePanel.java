import javax.swing.*;
import java.awt.*;

/**
 * In customer panel, the profile panel
 */
public class GUIBankCustomerPanelProfilePanel extends GUIBankPanel {
    // constructor
    public GUIBankCustomerPanelProfilePanel(GUIBankATMFrame frame) {
        super(frame);
        addElements();
    }

    // other function
    public void addElements() {
        GUIBankATMFrame frame = getFrame();
        Bank bank = Bank.getInstance();

        JTextField passwordField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField address1Field = new JTextField();
        JTextField address2Field = new JTextField();
        JTextField cityField = new JTextField();
        JTextField stateField = new JTextField();
        JTextField zipCodeField = new JTextField();
        JTextField countryField = new JTextField();
        JLabel feedbackLabel = new JLabel();

        JButton updateButton = new JButton("UPDATE");
        GUIBankCustomerPanelProfilePanelUpdateEvent updateEvent = new GUIBankCustomerPanelProfilePanelUpdateEvent(frame);
        updateEvent.setPasswordField(passwordField);
        updateEvent.setEmailField(emailField);
        updateEvent.setPhoneField(phoneField);
        updateEvent.setAddress1Field(address1Field);
        updateEvent.setAddress2Field(address2Field);
        updateEvent.setCityField(cityField);
        updateEvent.setStateField(stateField);
        updateEvent.setZipCodeField(zipCodeField);
        updateEvent.setCountryField(countryField);
        updateEvent.setFeedbackLabel(feedbackLabel);
        updateButton.addActionListener(updateEvent);

        BankCustomer customer = frame.getCustomer();
        passwordField.setText(bank.getPasswordByCustomer(customer));
        emailField.setText(bank.getEmailByCustomer(customer));
        phoneField.setText(bank.getPhoneByCustomer(customer));
        address1Field.setText(bank.getAddress1ByCustomer(customer));
        address2Field.setText(bank.getAddress2ByCustomer(customer));
        cityField.setText(bank.getCityByCustomer(customer));
        stateField.setText(bank.getStateByCustomer(customer));
        zipCodeField.setText(bank.getZipCodeByCustomer(customer));
        countryField.setText(bank.getCountryByCustomer(customer));

        setLayout(new GridLayout(12,2));
        add(new JLabel("Customer number:"));
        add(new JLabel(bank.getCustomerNumberByCustomer(customer)));
        add(new JLabel("User name:"));
        add(new JLabel(bank.getUsernameByCustomer(customer)));
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Phone:"));
        add(phoneField);
        add(new JLabel("Address 1:"));
        add(address1Field);
        add(new JLabel("Address 2:"));
        add(address2Field);
        add(new JLabel("City:"));
        add(cityField);
        add(new JLabel("State:"));
        add(stateField);
        add(new JLabel("Zip code:"));
        add(zipCodeField);
        add(new JLabel("Country:"));
        add(countryField);
        add(feedbackLabel);
        add(updateButton);
    }
}
