package locationallocation;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCostMatrix {
    @Test public void distanceMatrixIsCorrect() {
        
        
        Location[] testLocations1    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] testLocations2    = { new Location(7,11), new Location(2,8) , new Location(7,3) };
      
        // Calculate distance matrix
        CostMatrix distM = new CostMatrix();
        distM.calculateDistanceMatrix(testLocations1, testLocations2);

        double[][] dist = distM.getCosts();

        double[][] answer = {
           { 11.6619037896906, 7.07106781186548, 6.32455532033676 },
           { 2.82842712474619,3.16227766016838,6.32455532033676   },
           { 8.60232526704263,4,5.09901951359278 },
           { 8.94427190999916,10.295630140987,4  }
        };

        int[] correctDimensions = { testLocations1.length, testLocations2.length };
        int[] matrixDimensions =  { dist.length , dist[0].length   };

        assertArrayEquals("Distance matrix dimensions are incorrect", correctDimensions , matrixDimensions );
        
        for (int row = 0;row < answer.length; row++) {
            assertArrayEquals("Distance matrix incorrect on row " + row, answer[row] , dist[row] , 0.0001 );
        }

    
        
    }
}