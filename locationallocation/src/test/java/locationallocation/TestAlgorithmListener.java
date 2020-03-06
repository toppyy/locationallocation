package locationallocation;

import locationallocation.UI.ActionListeners.AlgorithmListener;
import locationallocation.Domain.Pmedian;

import javax.swing.JRadioButton;

import java.awt.event.ActionEvent;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestAlgorithmListener {


   
    @Test
    public void algorithmUpdatedOnClick() {

        Pmedian application = new Pmedian();

        JRadioButton buttonTB = createRadioButton("TeitzBart");
        AlgorithmListener listener = new AlgorithmListener(application, buttonTB);

        ActionEvent click = new ActionEvent(buttonTB, 1, "");
        listener.actionPerformed(click);

        assertEquals("Incorrect after click", "TeitzBart",  application.getAlgorithmName());
    }


    private final JRadioButton createRadioButton(final String text) {
        JRadioButton butt = new JRadioButton(text);
        butt.setActionCommand(text);
        return butt;
    }

}