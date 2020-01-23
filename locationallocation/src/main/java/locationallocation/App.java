/**
 * Solves the p-median problem
 */
package locationallocation;

import static locationallocation.Utils.DistanceMatrix.*;
import locationallocation.Utils.*;

import static locationallocation.TeitzBart.*;

public class App {

    
    public static void main(String[] args) {

        Location[] possibleFacilityLocations    = { new Location(1,1), new Location(5,9) , new Location(2,4),  new Location(11,3) };
        Location[] demandLocations              = { new Location(7,11), new Location(2,8) , new Location(7,3) };
        int P = 2;

        // Calculate distance matrix
        double[][] dist = calculateDistanceMatrix(possibleFacilityLocations, demandLocations);

        int[] answerTB = solveTeitzBart(P,dist);

        System.out.println("\nTeitzBart: ");
        for (int i : answerTB) {
            System.out.print(i + " ");
        }
        System.out.println();

        
       

    }
}
