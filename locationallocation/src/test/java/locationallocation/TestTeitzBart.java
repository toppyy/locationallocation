package locationallocation;

import static locationallocation.Utils.DistanceMatrix.*;
import locationallocation.Utils.Location;

import static locationallocation.TeitzBart.*;
import locationallocation.Utils.LocationLoader;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTeitzBart {
    @Test public void correctFacilitySet() {
        
        
        Location[] testLocations1    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] testLocations2    = { new Location(7,11), new Location(2,8) , new Location(7,3) };
      
        // Calculate distance matrix
        double[][] dist = calculateDistanceMatrix(testLocations1, testLocations2);

        int[] expectedAnswer = { 3,1 }; // TODO: better test. Also tests order of indices which is not necessary
        
        assertArrayEquals("1. Incorrect facility set as result", expectedAnswer, solveTeitzBart(2, dist)  );

        // 2. 
        String path = ".\\src\\test\\resources\\testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path,true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = ".\\src\\test\\resources\\testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path,true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();

        // Calculate distance matrix
        double[][] dist2 = calculateDistanceMatrix(testFacilityLocations, testDemandLocations );

        int[] expectedAnswer2 = { 18,8,4 }; // TODO: better test. Also tests order of indices which is not necessary

        assertArrayEquals("2. Incorrect facility set as result", expectedAnswer2, solveTeitzBart(3, dist2)  );

        
    }
}