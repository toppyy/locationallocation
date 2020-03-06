package locationallocation;

import locationallocation.UI.ActionListeners.InputFileChooserListener;
import locationallocation.Domain.Pmedian;
import locationallocation.UI.GUI;


import java.io.File;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestInputFileChooserListener {

   
    @Test
    public void selectingFileLoadsLocations() {

        Pmedian application = new Pmedian();
        GUI ui = new GUI(application);

        InputFileChooserListener listener = new InputFileChooserListener(ui, application, "demand");

        File dl = new File("src/test/resources/testdata_1_demand_locations.csv");

        listener.loadFile(dl);



        assertEquals("Locations loaded after click", 100, application.getNumberOfDemandLocations());
    }




}