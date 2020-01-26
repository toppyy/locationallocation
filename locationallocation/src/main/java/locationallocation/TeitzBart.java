

/*
* An implementation of the Teitz and Bart algorithm for solving the p-median problem
* 
*/

package locationallocation;


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

        int numberOfTested = 0, possibleLocationCount = distanceMatrix.length, newcandidate;
        double toTestAgainst;


        // Init with first P locations
        int[] locationSet = new int[P];

        for (int i = 0; i < P; i++) {
            locationSet[i] = i;
        }

        int[] tmp = new int[locationSet.length];

        while ( numberOfTested <  possibleLocationCount ) {
            toTestAgainst = sumOfDistanceMatrixMiminumRowset(locationSet, distanceMatrix);
            newcandidate = numberOfTested;
            numberOfTested++;

            double minOfTests = Double.MAX_VALUE, testsum;
            int minOfTestsIndex = -1;

            // See if newcandidate is already in set
            boolean newCandidateInSet  = false;
            for (int location : locationSet) {
                if (newcandidate == location) {
                    newCandidateInSet = true;
                }
            }
            // if so, continue without tests
            if (newCandidateInSet) {
                continue;
            }
            
            // Test by replacing each location in set with newcandidate
            // Store minimum of tests
            for (int i = 0; i < locationSet.length; i++) {
                tmp = locationSet.clone();
                tmp[i] = newcandidate;
                testsum = sumOfDistanceMatrixMiminumRowset(tmp, distanceMatrix);
                
                if ( testsum < minOfTests  ) {
                    minOfTests      = i;
                    minOfTestsIndex = i;
                }
            }
            // If better solution is found, change set 
            if ( minOfTests < toTestAgainst ) {
                locationSet[ minOfTestsIndex ] = newcandidate;
            }

            

        }
        System.out.println("Sum of distances: " + sumOfDistanceMatrixMiminumRowset(locationSet, distanceMatrix));

        return locationSet;
    }

}