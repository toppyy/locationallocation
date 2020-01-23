

/*
* An implementation of the Teitz and Bart algorithm for solving the p-median problem
* 
*/

package locationallocation;

import locationallocation.Utils.Location;

public class TeitzBart {

    

    
    /** 
     * Returns the sum distances between demand locations and a set facilities.
     * Distance is minimized by allocating each demand location to closest facility.
     * 
     * @param rowset: set of rows for which to find sum of minimums of distances. 
     * @param dm: a distancematrix that holds distances between facilities and demand locations. Row = facility location.
     * @return double
     */
    public static double sumOfDistanceMatrixMiminumRowset(int[] rowset, double[][] dm ) {
        double rtrn = 0, min_tmp, tmp;
        

        for (int col = 0; col < dm[0].length; col++) {
            
            min_tmp = Double.MAX_VALUE;

            for (int row : rowset) {

                tmp = dm[row][col];
                if (tmp<min_tmp) {
                    min_tmp = tmp;
                }
            }
            rtrn += min_tmp;
            
        }

        return rtrn;
    }

    
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