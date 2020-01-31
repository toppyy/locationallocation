/*
* An implementation of the Teitz and Bart algorithm.
* Solves the p-median problem.
*/

package locationallocation;

//import java.util.*;

import locationallocation.Utils.IntegerSet;

import static locationallocation.Utils.DistanceMatrix.sumOfDistanceMatrixMiminumRowset;

public final class TeitzBart {

    private TeitzBart() {

    } 

    /** 
     * Returns a set of facilities consired as the "optimal" locations based on demand.
     * 
     * @param pn Number of facilities to locate
     * @param distanceMatrix distances between facilities and demand locations.
     * @return int[] Array of facility location indices
     */
    public static int[] solveTeitzBart(final int pn, final double[][] distanceMatrix) {

        int newcandidate = -1;
        double toTestAgainst;


        // Init with first pn locations
        int[] initialSet = new int[pn];
        int[] initialCandidates = new int[distanceMatrix.length - pn];

        int ci = 0;
        for (int i = 0; i < distanceMatrix.length; i++) {
            if (i < pn) {
                initialSet[i] = i;
            } else {
                initialCandidates[ci] = i;
                ci++;
            }
        }


        IntegerSet solutions = new IntegerSet(initialSet);
        IntegerSet candidates = new IntegerSet(initialCandidates);
        
        double minOfTests = Double.MAX_VALUE, testsum;
        int toBeReplaced = -1;


        while (!candidates.isEmpty()) {
            
        
            toTestAgainst = sumOfDistanceMatrixMiminumRowset(solutions.getIntegerSet(), distanceMatrix);

            newcandidate = candidates.pop();

            int[] testSet = solutions.getIntegerSet();
            for (int i = 0; i < testSet.length; i++) {


                int[] tmp = testSet.clone();
                tmp[i] = newcandidate;

                testsum = sumOfDistanceMatrixMiminumRowset(tmp, distanceMatrix);
                
                if (testsum < minOfTests) {
                    minOfTests      = testsum;
                    toBeReplaced = testSet[i];
                }
                
            }
            // If better solution is found, change set 
            if (minOfTests < toTestAgainst) {
                
                if (solutions.setChange(toBeReplaced, newcandidate)) {
                    candidates.add(toBeReplaced);
                }
            }
        }
        System.out.println("Sum of distances: " + sumOfDistanceMatrixMiminumRowset(solutions.getIntegerSet(), distanceMatrix));

        return solutions.getIntegerSet();
    }

}