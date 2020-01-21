/*
 * Naive/brute force solution for the p-median problem.
 *  - Minimize distance by going through all possible combinatios
 */
package locationallocation;

import locationallocation.Utils.Location;
import locationallocation.Utils.Combinations;

public class Naive {

    private int P;
    private Location[] possibleLocations, demandLocations;
    private double[][] distanceMatrix;
  


    public Naive(int P, Location[] possibleLocations, Location[] demandLocations, double[][] distanceMatrix  ) {
        this.P = P;
        this.possibleLocations  = possibleLocations;
        this.demandLocations    = demandLocations;
        this.distanceMatrix     = distanceMatrix;
       
    }

    public double solve() {


        return 1.1;
    }


}