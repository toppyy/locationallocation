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

            //for ( int idx_b = 0; idx_b <= idx_a && idx_b<setB.length; idx_b++ ) {
            
            for ( int idx_b = 0;idx_b<setB.length; idx_b++ ) {

                dist[idx_a][idx_b]  = calculateEuclideanDistance(setA[idx_a], setB[idx_b]);

            }
        }
       

        return dist;

    }
    // For dev
    /*
    public void printDistanceMatrix(double[][] dist) {

        for ( int idx_a = 0; idx_a < dist.length; idx_a++ ) {
            System.out.println("");
            for ( int idx_b = 0; idx_b < dist[0].length; idx_b++ ) {
                System.out.print( String.format("%.2f",  dist[idx_a][idx_b] ) + " " );
            }
        }
        System.out.println("\n\n");
    }
    */

    

}