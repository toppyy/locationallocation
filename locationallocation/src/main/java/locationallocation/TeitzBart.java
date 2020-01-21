/*
* An implementation of the Teitz and Bart algorithm for solving the p-median problem
* 
*/

package locationallocation;

import locationallocation.Utils.Location;

public class TeitzBart {

    /*
    private int P;
    private double[][] distanceMatrix;
  

    public TeitzBart(int P, double[][] distanceMatrix  ) {
        this.P = P;
        this.distanceMatrix     = distanceMatrix;
       
    }
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

    public static double solveTeitzBart(int P, double[][] distanceMatrix  ) {

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
        System.out.println("Chosen:");
        for (int id : locationSet) {
            System.out.print(id + " ");
        }
        System.out.println("");
        System.out.println("-----");

        return sumOfDistanceMatrixMiminumRowset(locationSet, distanceMatrix);
    }

}