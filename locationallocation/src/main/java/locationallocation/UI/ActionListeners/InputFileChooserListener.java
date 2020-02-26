package locationallocation.UI.ActionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

import java.io.File;

import locationallocation.Domain.Pmedian;
import locationallocation.UI.GUI;

/**
 * File chooser.
 */
public class InputFileChooserListener implements ActionListener {
    /**
     * Is it demand or possible.
     */
    private String which;
    private Pmedian app;
    private GUI ui;

    public InputFileChooserListener(final GUI uiInput, final Pmedian appInput, final String whichInput) {
     
        this.app = appInput;
        this.ui = uiInput;
        this.which = whichInput;
    
    }
    /**
     * On file choose, load file content as demand/possible locations.
     * @param e Event.
     */
    public void actionPerformed(final ActionEvent e) {
        
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(this.ui);
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            if (this.which.equals("demand")) {
                this.app.loadDemandlocations(selectedFile.getAbsolutePath());
            }
            if (this.which.equals("possible")) {
                this.app.loadPossiblelocations(selectedFile.getAbsolutePath());
            }

            this.ui.updateStatus("locations");

        }

        
    }
}