package locationallocation;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;
import locationallocation.Utils.LocationLoader;

import locationallocation.Domain.Naive;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TestNaive {
    @Test public void correctFacilitySet() {
        
        
        Location[] testLocations1    = { new Location("ID", 1,1), new Location("ID", 5,9) , new Location("ID", 2,4),  new Location("ID", 11,3) };
        Location[] testLocations2    = { new Location("ID", 7,11), new Location("ID", 2,8) , new Location("ID", 7,3) };
        
        // Calculate distance matrix
        CostMatrix costs = new CostMatrix(testLocations1, testLocations2);

        int[] expectedAnswer = { 1,3 }; // TODO: better test. Also tests order of indices which is not necessary

        Naive naive = new Naive();
    
        
        assertArrayEquals("Incorrect facility set as result", expectedAnswer, naive.solveWithParams(costs, 2) );


        
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
        Naive naiveSolver = new Naive();

        naiveSolver.solveWithParams(costs, 7);
   

        double expectedCost = 1202.3467386700231;
        
        assertEquals("Incorrect cost as result", expectedCost , naiveSolver.getResultCost(), 0.0001 );


        
    }
}