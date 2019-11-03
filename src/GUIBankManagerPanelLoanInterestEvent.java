import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class GUIBankManagerPanelLoanInterestEvent extends GUIBankEvent {
    private GUIBankManagerPanel managerPanel;

    public GUIBankManagerPanelLoanInterestEvent(GUIBankATMFrame frame, GUIBankManagerPanel managerPanel) {
        super(frame);
        this.managerPanel = managerPanel;
    }

    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        bank.increaseLoan(day, month, year);
        managerPanel.refresh();
    }
}
