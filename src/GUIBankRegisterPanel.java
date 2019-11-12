import javax.swing.*;
import java.awt.*;

public class GUIBankRegisterPanel extends GUIBankPanel {

    private JLabel logo;
    private JLabel msg1;
    private JLabel msg2;
    private JLabel loginNotice;
    private JLabel login;
    private JSeparator divider;
    private JLabel registerLabel;
    private JLabel usernameLabel;
    private JTextField usernameText;
    private JLabel emailLabel;
    private JTextField emailText;
    private JLabel passwordLabel1;
    private JTextField passwordText1;
    private JLabel passwordLabel2;
    private JTextField passwordText2;
    private JLabel warning;
    private JButton register;


    public GUIBankRegisterPanel(GUIBankATMFrame frame) {
        super(frame);
        display();
    }

    public void display() {
        logo = new JLabel("Bank ATM");
        msg1 = new JLabel("Welcome");
        msg2 = new JLabel("New customer!");
        loginNotice = new JLabel("Already have an account?");
        login = new JLabel("LOGIN");
        divider = new JSeparator(SwingConstants.VERTICAL);
        registerLabel = new JLabel("REGISTER");
        usernameLabel = new JLabel("User name");
        usernameText = new JTextField();
        emailLabel = new JLabel("Email");
        emailText = new JTextField();
        passwordLabel1 = new JLabel("Password");
        passwordText1 = new JTextField();
        passwordLabel2 = new JLabel("Re-enter password");
        passwordText2 = new JTextField();
        warning = new JLabel();
        register = new JButton("REGISTER");

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        add(logo);
        add(msg1);
        add(msg2);
        add(loginNotice);
        add(login);
        add(divider);
        add(registerLabel);
        add(usernameLabel);
        add(usernameText);
        add(emailLabel);
        add(emailText);
        add(passwordLabel1);
        add(passwordText1);
        add(passwordLabel2);
        add(passwordText2);
        add(warning);
        add(register);

        logo.setBounds(45,120,165,30);
        msg1.setBounds(45,225,100,25);
        msg2.setBounds(45,255,100,25);
        loginNotice.setBounds(45,485,165,30);
        login.setBounds(45,515,100,25);
        divider.setBounds(240,30,1,550);
        registerLabel.setBounds(305,75,100,25);
        usernameLabel.setBounds(305,120,165,25);
        usernameText.setBounds(305,145,165,25);
        emailLabel.setBounds(305,185,160,25);
        emailText.setBounds(305, 210, 165, 25);
        passwordLabel1.setBounds(305, 250, 165, 25);
        passwordText1.setBounds(305, 275, 165, 25);
        passwordLabel2.setBounds(305, 315, 165, 25);
        passwordText2.setBounds(305, 340, 165, 25);
        warning.setBounds(305, 380, 365, 30);
        register.setBounds(425, 465, 100, 25);

        GUIBankRegisterPanelRegisterEvent registerEvent = new GUIBankRegisterPanelRegisterEvent(getFrame());
        registerEvent.setUsernameField(usernameText);
        registerEvent.setEmailField(emailText);
        registerEvent.setPasswordField(passwordText1);
        registerEvent.setPassword2Field(passwordText2);
        registerEvent.setFeedbackLabel(warning);
        register.addActionListener(registerEvent);

        login.setForeground(Color.BLUE);
        GUIBankRegisterPanelLoginEvent loginEvent = new GUIBankRegisterPanelLoginEvent(getFrame());
        login.addMouseListener(loginEvent);

    }
}
