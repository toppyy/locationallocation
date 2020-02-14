package locationallocation;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;

import locationallocation.Domain.GRIA;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestGRIA {
    @Test public void correctFacilitySet() {
        
        
        Location[] testLocations1    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] testLocations2    = { new Location(7,11), new Location(2,8) , new Location(7,3) };
      
        // Calculate distance matrix
        CostMatrix costs = new CostMatrix(testLocations1, testLocations2);

        int[] expectedAnswer = { 1,3 }; // TODO: better test. Also tests order of indices which is not necessary

        GRIA gria = new GRIA(costs, 2, testLocations1);
    
        
        assertArrayEquals("Incorrect facility set as result", expectedAnswer, gria.solve());


        
    }
}