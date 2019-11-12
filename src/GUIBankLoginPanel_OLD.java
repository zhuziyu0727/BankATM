import javax.swing.*;
import java.awt.*;

/**
 * login in panel for bank ATM
 */
public class GUIBankLoginPanel_OLD extends GUIBankPanel {
    // constructor
    public GUIBankLoginPanel_OLD(GUIBankATMFrame frame) {
        super(frame);
        addElements();
    }

    // other function
    public void addElements() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();

        JLabel feedbackLabel = new JLabel(" ");

        JButton loginButton = new JButton("LOGIN");
        GUIBankLoginPanelLoginEvent loginEvent = new GUIBankLoginPanelLoginEvent(getFrame());
        loginEvent.setUsernameField(usernameField);
        loginEvent.setPasswordField(passwordField);
        loginEvent.setFeedbackLabel(feedbackLabel);
        loginButton.addActionListener(loginEvent);

        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setForeground(Color.BLUE);
        GUIBankLoginPanelRegisterEvent registerEvent = new GUIBankLoginPanelRegisterEvent(getFrame());
        registerLabel.addMouseListener(registerEvent);

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(600, 500));
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
        add(new JLabel("Password"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(feedbackLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new JLabel("Not have an account? "), gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(registerLabel, gbc);
    }
}
