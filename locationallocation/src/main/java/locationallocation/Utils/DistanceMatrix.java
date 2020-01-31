/*
*   Creates a distance matrix from a set of Location arrays
*/



package locationallocation.Utils;

public final class DistanceMatrix {

    private DistanceMatrix() {
    }

    public static double calculateEuclideanDistance(final Location a, final Location b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public static double[][] calculateDistanceMatrix(final Location[] setA, final Location[] setB) {

        double[][] dist = new double[setA.length][setB.length];

        for (int idxA = 0; idxA < setA.length; idxA++) {

            for (int idxB = 0; idxB < setB.length; idxB++) {
            
                dist[idxA][idxB]  = calculateEuclideanDistance(setA[idxA], setB[idxB]);

            }
        }

        return dist;

    }

    /** 
     * Returns the sum distances between demand locations and a set facilities.
     * Distance is minimized by allocating each demand location to closest facility.
     * 
     * @param rowset set of rows for which to find sum of minimums of distances. 
     * @param dm a distancematrix that holds distances between facilities and demand locations.
     * @return double
     */
    public static double sumOfDistanceMatrixMiminumRowset(final int[] rowset, final double[][] dm) {
        
        double rtrn = 0, minTmp, tmp;
        
        for (int col = 0; col < dm[0].length; col++) {
        
            minTmp = Double.MAX_VALUE;

            for (int row : rowset) {

                tmp = dm[row][col];
                if (tmp < minTmp) {
                    minTmp = tmp;
                }
            }
            rtrn += minTmp;
            
        }

        return rtrn;
    }


}