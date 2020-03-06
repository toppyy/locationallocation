package locationallocation.UI.ActionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileNotFoundException;

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
    public void actionPerformed(final ActionEvent e)  {
        
        JFileChooser fileChooser = new JFileChooser();

        int result = fileChooser.showOpenDialog(this.ui);
        if (result == JFileChooser.APPROVE_OPTION) {

            File selectedFile = fileChooser.getSelectedFile();
            
            this.loadFile(selectedFile);

            this.ui.updateStatus("locations");

        }

        
    }
    /**
     * Load chosen file.
     * @param selectedFile Chosen file.
     */
    public void loadFile(final File selectedFile) {

        if (this.which.equals("demand")) {
            try { 
                this.app.loadDemandlocations(selectedFile.getAbsolutePath());
            } catch (FileNotFoundException exception) {
                System.out.println("File not found!");
            }
        }
        if (this.which.equals("possible")) {
            try { 
                this.app.loadPossiblelocations(selectedFile.getAbsolutePath());

            } catch (FileNotFoundException exception) {
                System.out.println("File not found!");
            }
        }

    }
}