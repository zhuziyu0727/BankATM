import java.awt.event.ActionEvent;

public class GUIBankManagerPanelStockManageEvent extends GUIBankEvent {

	public GUIBankManagerPanelStockManageEvent(GUIBankATMFrame frame) {
		super(frame);

	}

	public void actionPerformed(ActionEvent e) {
		Bank bank = Bank.getInstance();

	}

}
