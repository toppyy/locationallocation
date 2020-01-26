/**
 * Naive/brute force solution for the p-median problem.
 *  - Minimize distance by going through all possible combinatios
 */
package locationallocation;

import static locationallocation.Utils.Combinations.createCombinations;
import static locationallocation.Utils.DistanceMatrix.sumOfDistanceMatrixMiminumRowset;

public class Naive {

    
     /** 
     * Returns the optimal set of facility locations based on demand.
     * 
     * @param P: Number of facilities to locate
     * @param distanceMatrix: distancematrix that holds distances between facilities and demand locations. Row = facility location.
     * @return int[]: Array of facility location indices
     */
    public static int[] solveNaive(int P, double[][] distanceMatrix  ) {


        double  currentMinimum = Double.MAX_VALUE, toTestAgainst;
        int     indexOfCurrentMinimum = 0;

    
        // Create all possible combinations of facilities of size P
        int[] m = new int[distanceMatrix.length];
        for (int i = 0; i < m.length; i++) {
            m[i] = i+1;
        }
        int[][] combinations = createCombinations(m,P);

        for (int i = 0; i < combinations.length; i++) {
            toTestAgainst = sumOfDistanceMatrixMiminumRowset(combinations[i], distanceMatrix);

            if (toTestAgainst < currentMinimum) {
                currentMinimum = toTestAgainst;
                indexOfCurrentMinimum = i;
            }

        }
        System.out.println("Naive solution: " + currentMinimum);
        return combinations[indexOfCurrentMinimum];

    }


}