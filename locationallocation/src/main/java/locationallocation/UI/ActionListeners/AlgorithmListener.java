
package locationallocation.UI.ActionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JRadioButton;

import locationallocation.Domain.Pmedian;

 /**
 * Action listener for radio button choosing the algorithm to use.
 */
public class AlgorithmListener implements ActionListener {


    private JRadioButton button;
    private Pmedian app;

    public AlgorithmListener(final Pmedian appInput, final JRadioButton buttonInput) {
        this.button = buttonInput;
        this.app = appInput;
    }
    /**
     * Sets solver based on clicked (radio-)button.
     * @param e Event.
     */
    public void actionPerformed(final ActionEvent e) {

        String command = button.getActionCommand();
                
        this.app.setSolver(command);
    }

}