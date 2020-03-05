package locationallocation;


import locationallocation.Domain.Pmedian;

import locationallocation.Utils.Location;
import locationallocation.Utils.Arguments;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

import locationallocation.Exceptions.NotInSet;

public class TestPmedian {

    private Pmedian app;

    
    @Before
    public void setUp() {

        String argsRaw = "out EXAMPLE.csv p 7 a GRIA dl src/test/resources/testdata_1_demand_locations.csv pl src/test/resources/testdata_1_facility_locations.csv";
        String[] args = argsRaw.split(" ");

        Arguments argsParsed = new Arguments(args);
        this.app = new Pmedian(argsParsed);
       
    }


    @Test public void emptyConstructor() {

        String[] expected = {"Naive", "TeitzBart", "GRIA"};
        
        Pmedian emptyApp = new Pmedian();

        assertArrayEquals("Algo list not OK", expected, emptyApp.getAlgorithms());
        assertEquals("default P not OK", 0, emptyApp.getP());
        assertEquals("default locations not OK", 0, emptyApp.getNumberOfDemandLocations());
        assertEquals("default locations not OK", 0, emptyApp.getNumberOfPossibleLocations());

        assertFalse("Requirements should not be filled", emptyApp.solveRequirementsFulfilled());

    }

    @Test public void constructorFromArguments() {

        String[] expected = {"Naive", "TeitzBart", "GRIA"};
        assertArrayEquals("Algo list not OK", expected, this.app.getAlgorithms());

        assertEquals("P not OK", 7, this.app.getP());
        assertEquals("Demand location count not OK", 100, this.app.getNumberOfDemandLocations());
        assertEquals("Possible location count not OK", 20, this.app.getNumberOfPossibleLocations());
        assertEquals("Algorithm not OK", "GRIA", this.app.getAlgorithmName());

    }

    @Test public void resultAllocations() {
        this.app.solve();

        int[] allocations = this.app.getResultAllocations();

        assertEquals("Each demand location is not allocated", allocations.length, this.app.getNumberOfDemandLocations() );

        String[] allocationsAsString = this.app.resultAllocationsForFlatFile();

        assertEquals("Result size not correct",  allocations.length + 1 , allocationsAsString.length );

    }

    @Test public void solving() {


        assertTrue("Requirements should be filled", this.app.solve());
        assertTrue("Does not solve", this.app.solve());

        assertTrue("Cost > 0", this.app.getResultCost()>0);

    }

    @Test public void solverSwitch() {


        try {
            this.app.setSolver("TeitzBart");
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals("Algorithm not OK", "TeitzBart", this.app.getAlgorithmName());

        try {
            this.app.setSolver("Naive");
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals("Algorithm not OK", "Naive", this.app.getAlgorithmName());

    }
    

}