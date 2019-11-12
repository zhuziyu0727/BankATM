import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class GUIBankLoginPanel extends GUIBankPanel {

    private JLabel logo;
    private JLabel usernameLabel;
    private JTextField usernameText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JButton login;
    private JLabel warning;
    private JLabel registerNotice;
    private JLabel register;
    private JEditorPane news;


    public GUIBankLoginPanel(GUIBankATMFrame frame) {
        super(frame);
        display();
    }

    public void display() {
        logo = new JLabel("Bank ATM");
        usernameLabel = new JLabel("User name");
        usernameText = new JTextField();
        passwordLabel = new JLabel("Password");
        passwordText = new JTextField();
        warning = new JLabel();
        login = new JButton("LOGIN");
        registerNotice = new JLabel("Not have an account?");
        register = new JLabel("REGISTER");
        news = new JEditorPane();

        setPreferredSize(new Dimension(800, 600));
        setLayout(null);

        add(logo);
        add(usernameLabel);
        add(usernameText);
        add(passwordLabel);
        add(passwordText);
        add(login);
        add(warning);
        add(registerNotice);
        add(register);
        add(news);

        logo.setBounds(45, 120, 165, 30);
        usernameLabel.setBounds(45, 190, 165, 25);
        usernameText.setBounds(45,225,165,25);
        passwordLabel.setBounds(45,260, 165, 25);
        passwordText.setBounds(45, 295,165,25);
        login.setBounds(75,380,100,25);
        warning.setBounds(45, 340,165,25);
        registerNotice.setBounds(45,485,165,25);
        register.setBounds(45,515,100,25);
        news.setBounds(240,30,535,540);

        // set functions
        GUIBankLoginPanelLoginEvent loginEvent = new GUIBankLoginPanelLoginEvent(getFrame());
        loginEvent.setUsernameField(usernameText);
        loginEvent.setPasswordField(passwordText);
        loginEvent.setFeedbackLabel(warning);
        login.addActionListener(loginEvent);

        register.setForeground(Color.BLUE);
        GUIBankLoginPanelRegisterEvent registerEvent = new GUIBankLoginPanelRegisterEvent(getFrame());
        register.addMouseListener(registerEvent);

        news.setEditable(false);
        news.setContentType("text/html");
        news.setText("<html>Welcome to use our Bank ATM.<br>" +
                "Breaking NEWs<br>" +
                "New cooperation with stock system which can help you to earn more money.</html>");
    }
}
