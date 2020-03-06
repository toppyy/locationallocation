package locationallocation;

import locationallocation.UI.ActionListeners.ChoosePListener;
import locationallocation.Domain.Pmedian;
import locationallocation.UI.GUI;


import javax.swing.JTextField;

import java.awt.event.ActionEvent;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestChoosePListener {


   
    @Test
    public void pUpdatedOnClick() {

        Pmedian application = new Pmedian();
        GUI ui = new GUI(application);
        ui.start();

        JTextField input = new JTextField();
        ChoosePListener listener = new ChoosePListener(ui, application, input);

        input.setText("1234");

        ActionEvent textInput = new ActionEvent(input, 1, "");
        listener.actionPerformed(textInput);

        assertEquals("Incorrect after textinput", 1234,  application.getP());
    }




}