package locationallocation;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;
import locationallocation.Utils.LocationLoader;

import locationallocation.Domain.TeitzBart;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTeitzBart {
    @Test public void smallTest() {
        
        
        Location[] testLocations1    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] testLocations2    = { new Location(7,11), new Location(2,8) , new Location(7,3) };
      
        // Calculate distance matrix

        CostMatrix costs = new CostMatrix(testLocations1, testLocations2);


        int[] expectedAnswer = { 3,1 }; // TODO: better test. Also tests order of indices which is not necessary

        TeitzBart tb1 = new TeitzBart();
        
        assertArrayEquals("Incorrect facility set as result", expectedAnswer, tb1.solveWithParams(costs,2));

        

        
    }
    @Test public void largeTest() {
        
        
        String path = "src/test/resources/testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path, true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = "src/test/resources/testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path, true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();
      
        // Calculate cost matrix of euclidean distances
        CostMatrix costs = new CostMatrix(testFacilityLocations, testDemandLocations);
        TeitzBart teitzBartSolver = new TeitzBart();

        teitzBartSolver.solveWithParams(costs, 7);
   

        double expectedCost = 1276.1210073112543;
        
        assertEquals("Incorrect cost as result", expectedCost , teitzBartSolver.getResultCost(), 0.0001 );


        
    }
}