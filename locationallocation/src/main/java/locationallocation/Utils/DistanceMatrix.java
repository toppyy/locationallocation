/*
*   Creates a distance matrix from a set of Location arrays
*/



package locationallocation.Utils;

public class DistanceMatrix {

    public static double calculateEuclideanDistance(Location a, Location b ) {
        return Math.sqrt( Math.pow( a.getX() - b.getX() , 2 ) + Math.pow( a.getY() - b.getY() , 2 ) );
    }

    public static double[][] calculateDistanceMatrix(Location[] setA, Location[] setB) {

        double[][] dist = new double[setA.length][setB.length];

        for ( int idx_a = 0; idx_a < setA.length; idx_a++ ) {

            for ( int idx_b = 0; idx_b<setB.length; idx_b++ ) {
            
                dist[idx_a][idx_b]  = calculateEuclideanDistance(setA[idx_a], setB[idx_b]);

            }
        }

        return dist;

    }

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


}