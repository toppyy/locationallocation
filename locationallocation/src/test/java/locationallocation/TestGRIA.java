package locationallocation;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;
import locationallocation.Utils.LocationLoader;


import locationallocation.Domain.GRIA;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGRIA {
    @Test public void correctFacilitySetSmall() {
        
        
        Location[] testLocations1    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] testLocations2    = { new Location(7,11), new Location(2,8) , new Location(7,3) };
      
        // Calculate distance matrix
        CostMatrix costs = new CostMatrix(testLocations1, testLocations2);

        int[] expectedAnswer = { 1,3 }; // TODO: better test. Also tests order of indices which is not necessary

        GRIA gria = new GRIA();

        gria.setPossibleLocations(testLocations1);

        
    
        
        assertArrayEquals("Incorrect facility set as result", expectedAnswer, gria.solveWithParams(costs, 2));


        
    }
    @Test public void correctCost() {
        
        
        String path = "src/test/resources/testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path, true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = "src/test/resources/testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path, true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();
      
        // Calculate cost matrix of euclidean distances
        CostMatrix costs = new CostMatrix(testFacilityLocations, testDemandLocations);
        GRIA griaSolver = new GRIA();
        griaSolver.setPossibleLocations(testFacilityLocations);

        griaSolver.solveWithParams(costs, 7);
   

        double expectedCost = 1217.1475700616218;
        
        assertEquals("Incorrect cost as result", expectedCost , griaSolver.getResultCost(), 0.0001 );


        
    }
}