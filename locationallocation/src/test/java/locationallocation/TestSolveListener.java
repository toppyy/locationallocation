package locationallocation;

import locationallocation.UI.ActionListeners.SolveListener;
import locationallocation.Domain.Pmedian;
import locationallocation.UI.GUI;
import locationallocation.Utils.Arguments;


import java.awt.event.ActionEvent;
import javax.swing.JButton;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;


public class TestSolveListener {

    private GUI ui;
    private Pmedian app;

    
    @Before
    public void setUp() {

        String argsRaw = "out EXAMPLE.csv p 7 a GRIA dl src/test/resources/testdata_1_demand_locations.csv pl src/test/resources/testdata_1_facility_locations.csv";
        String[] args = argsRaw.split(" ");

        Arguments argsParsed = new Arguments(args);
        this.app = new Pmedian(argsParsed);

        ui = new GUI(this.app);
        ui.start();
       
    }
   
    @Test
    public void solveButtonSolves() {

        
        SolveListener listener = new SolveListener(ui, app);

        ActionEvent click = new ActionEvent(new JButton(), 1, "");

        listener.actionPerformed(click);

        assertTrue("No solution after click", this.app.getResultCost() > 0);
    }




}