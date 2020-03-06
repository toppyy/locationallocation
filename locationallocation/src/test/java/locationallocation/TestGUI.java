package locationallocation;

import locationallocation.UI.GUI;
import locationallocation.Domain.Pmedian;
import locationallocation.Utils.Arguments;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Poorly tested GUI.
 */

public class TestGUI {

    private GUI ui;
    private Pmedian app;

    
    @Before
    public void setUp() {

        String argsRaw = "out EXAMPLE.csv p 7 a GRIA dl src/test/resources/testdata_1_demand_locations.csv pl src/test/resources/testdata_1_facility_locations.csv";
        String[] args = argsRaw.split(" ");

        Arguments argsParsed = new Arguments(args);
        this.app = new Pmedian(argsParsed);

        ui = new GUI(this.app);
       
    }
    

    @Test
    public void GUIstarts() {
        assertTrue("GUI does not start", ui.start());
    }

    

}