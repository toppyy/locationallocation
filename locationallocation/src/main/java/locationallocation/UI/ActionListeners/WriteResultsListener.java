package locationallocation.UI.ActionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


import locationallocation.Domain.Pmedian;

/**
 * Write results listener.
 */
public class WriteResultsListener implements ActionListener {

        private JTextField pathInputField;
        private Pmedian app;

        public WriteResultsListener(final Pmedian appInput, final JTextField pathInput) {
            this.pathInputField = pathInput;
            this.app = appInput;
        }
        /**
         * When clicked, write results to file given as input.
         * @param e Event.
         */
        public void actionPerformed(final ActionEvent e) {

            app.writeAllocationsToFile(this.pathInputField.getText());

        }
}

    