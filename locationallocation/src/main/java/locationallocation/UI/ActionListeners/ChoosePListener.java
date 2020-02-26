package locationallocation.UI.ActionListeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


import locationallocation.Domain.Pmedian;
import locationallocation.UI.GUI;

/**
 * P-chooser listener.
 */
public class ChoosePListener implements ActionListener {

        private JTextField pInputField;
        private Pmedian app;
        private GUI ui;

        public ChoosePListener(final GUI uiInput, final Pmedian appInput, final JTextField pInput) {
            this.pInputField = pInput;
            this.app = appInput;
            this.ui = uiInput;
        }
        /**
         * When clicked, parse input into a number and update app.
         * @param e Event.
         */
        public void actionPerformed(final ActionEvent e) {

            Integer chosenP = Integer.parseInt(this.pInputField.getText());
            this.app.setP(chosenP);
            this.ui.updateStatus("P");

        }
    }