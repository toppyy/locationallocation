/**
 * Naive/brute force solution for the p-median problem.
 *  - Minimize distance by going through all possible combinatios
 */
package locationallocation;

import static locationallocation.Utils.Combinations.createCombinations;
import static locationallocation.Utils.DistanceMatrix.sumOfDistanceMatrixMiminumRowset;

public final class Naive {

    private Naive() {
    }

     /** 
     * Returns the optimal set of facility locations based on demand.
     * 
     * @param pn Number of facilities to locate
     * @param distanceMatrix distancematrix that holds distances between facilities and demand locations. Row = facility location.
     * @return int[] Array of facility location indices
     */
    public static int[] solveNaive(final int pn, final double[][] distanceMatrix) {


        double  currentMinimum = Double.MAX_VALUE, toTestAgainst;
        int     indexOfCurrentMinimum = 0;

        // Create all possible combinations of facilities of size pn
        int[] m = new int[distanceMatrix.length];
        for (int i = 0; i < m.length; i++) {
            m[i] = i + 1;
        }
        int[][] combinations = createCombinations(m, pn);

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