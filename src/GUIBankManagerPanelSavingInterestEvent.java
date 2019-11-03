import java.awt.event.ActionEvent;
import java.time.LocalDateTime;

public class GUIBankManagerPanelSavingInterestEvent extends GUIBankEvent {
    private GUIBankManagerPanel managerPanel;

    public GUIBankManagerPanelSavingInterestEvent(GUIBankATMFrame frame, GUIBankManagerPanel managerPanel) {
        super(frame);
        this.managerPanel = managerPanel;
    }

    public void actionPerformed(ActionEvent event) {
        Bank bank = Bank.getInstance();
        LocalDateTime now = LocalDateTime.now();
        int day = now.getDayOfMonth();
        int month = now.getMonthValue();
        int year = now.getYear();
        bank.increaseSaving(day, month, year);
        managerPanel.refresh();
    }
}
