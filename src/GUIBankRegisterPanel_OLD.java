import javax.swing.*;
import java.awt.*;

/**
 * register panel for bank
 */
public class GUIBankRegisterPanel_OLD extends GUIBankPanel {

    // constructor
    public GUIBankRegisterPanel_OLD(GUIBankATMFrame frame) {
        super(frame);
        addElements();
    }

    // add elements
    public void addElements() {
        JTextField usernameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField password2Field = new JTextField();

        JLabel feedbackLabel = new JLabel(" ");

        JButton registerButton = new JButton("REGISTER");
        GUIBankRegisterPanelRegisterEvent registerEvent = new GUIBankRegisterPanelRegisterEvent(getFrame());
        registerEvent.setUsernameField(usernameField);
        registerEvent.setEmailField(emailField);
        registerEvent.setPasswordField(passwordField);
        registerEvent.setPassword2Field(password2Field);
        registerEvent.setFeedbackLabel(feedbackLabel);
        registerButton.addActionListener(registerEvent);

        JLabel loginLabel = new JLabel("LOGIN");
        loginLabel.setForeground(Color.BLUE);
        GUIBankRegisterPanelLoginEvent loginEvent = new GUIBankRegisterPanelLoginEvent(getFrame());
        loginLabel.addMouseListener(loginEvent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("User name"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Email"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Password"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Re-enter password"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(password2Field, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(feedbackLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Have an account already? "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginLabel, gbc);

    }
}
