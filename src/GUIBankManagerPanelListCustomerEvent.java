import java.awt.event.ActionEvent;

import javax.swing.JLabel;

public class GUIBankManagerPanelListCustomerEvent extends GUIBankEvent{
	private JLabel msg;
	
	public GUIBankManagerPanelListCustomerEvent(GUIBankATMFrame frame,JLabel message) {
        super(frame);
        msg = message;
    }

	public void actionPerformed(ActionEvent e) {
		Bank bank = Bank.getInstance();
		String html_head = "<html><body><p>";
		String html_end = "</p></body></html>";
		String res = html_head + bank.getCustomerUsernames().replaceAll("\n", "<br/>") + html_end;
		msg.setText(res);
	}
	
}
