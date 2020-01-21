package locationallocation;

import static locationallocation.Utils.DistanceMatrix.*;
import locationallocation.Utils.Location;

import static locationallocation.TeitzBart.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestTeitzBart {
    @Test public void distanceMatrixIsCorrect() {
        
        
        Location[] testLocations1    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] testLocations2    = { new Location(7,11), new Location(2,8) , new Location(7,3) };
      
        // Calculate distance matrix
        double[][] dist = calculateDistanceMatrix(testLocations1, testLocations2);

        double expectedAnswer = 9.990705;
        
        assertEquals("Incorrect answer of mimimum distance sum ", expectedAnswer, solveTeitzBart(2, dist), 0.000001  );


        
    }
}