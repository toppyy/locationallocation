

/*
* An implementation of the Teitz and Bart algorithm for solving the p-median problem
* 
*/

package locationallocation;


import java.util.*;

import locationallocation.Utils.*;

import static locationallocation.Utils.DistanceMatrix.sumOfDistanceMatrixMiminumRowset;

public class TeitzBart {

    

    
    
    
    /** 
     * Returns a set of facilities consired as the "optimal" locations based on demand.
     * 
     * @param P: Number of facilities to locate
     * @param distanceMatrix: distancematrix that holds distances between facilities and demand locations. Row = facility location.
     * @return int[]: Array of facility location indices
     */
    public static int[] solveTeitzBart(int P, double[][] distanceMatrix  ) {

        int newcandidate = -1;
        double toTestAgainst;


        // Init with first P locations
        int[] initialSet = new int[P];
        int[] initialCandidates = new int[distanceMatrix.length-P];

        int ci = 0;
        for (int i = 0; i < distanceMatrix.length; i++) {
            if (i<P) {
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


        while (  !candidates.isEmpty() ) {
            
        
            toTestAgainst = sumOfDistanceMatrixMiminumRowset( solutions.getIntegerSet(), distanceMatrix);

            newcandidate = candidates.pop();

            int[] testSet = solutions.getIntegerSet();
            for (int i = 0; i < testSet.length; i++) {


                int[] tmp = testSet.clone();
                tmp[i] = newcandidate;

                testsum = sumOfDistanceMatrixMiminumRowset( tmp , distanceMatrix);
                
                if ( testsum < minOfTests  ) {
                    minOfTests      = testsum;
                    toBeReplaced = testSet[i];
                }
                
            }
            // If better solution is found, change set 
            if ( minOfTests < toTestAgainst ) {
                
                if ( solutions.setChange(toBeReplaced, newcandidate) ) {
                    candidates.add(toBeReplaced);
                }
            }
        }
        System.out.println("Sum of distances: " + sumOfDistanceMatrixMiminumRowset( solutions.getIntegerSet() , distanceMatrix));

        return solutions.getIntegerSet();
    }

}