package locationallocation.UI.ActionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import locationallocation.Domain.Pmedian;
import locationallocation.UI.GUI;
/**
 * Solve problem.
 */
public class SolveListener implements ActionListener {

    private Pmedian app;
    private GUI ui;

    public SolveListener(final GUI uiInput, final Pmedian inputApp) {
        this.app = inputApp;
        this.ui = uiInput;
    }
    /**
     * When clicked, solve problem and update app.
     * @param e Event.
     */
    public void actionPerformed(final ActionEvent e) {   
        
        // Empty results before run
        this.app.deleteResult();
        this.ui.updateStatus("cost");
        this.app.solve();
        this.ui.updateStatus("cost");        
    }
}