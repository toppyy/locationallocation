/**
 * Solves the p-median problem
 */
package locationallocation;

import static locationallocation.Utils.Combinations.createCombinations;
import static locationallocation.Utils.DistanceMatrix.*;
import locationallocation.Utils.*;


import static locationallocation.TeitzBart.*;
import static locationallocation.Naive.*;

import java.io.*;

public class App {

    
    public static void main(String[] args) {

        String path = ".\\src\\test\\resources\\testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path,true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = ".\\src\\test\\resources\\testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path,true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();

        int P = 3;

        // Calculate distance matrix
        double[][] dist = calculateDistanceMatrix(testFacilityLocations, testDemandLocations );

        int[] answerTB = solveTeitzBart(P,dist);

        
        System.out.println("\nTeitzBart: ");
        for (int i : answerTB) {
            System.out.print(i + " ");
        }
        System.out.println("\n\n");

        int[] answerNaive = solveNaive(P,dist);
        System.out.println("Naive: ");
        for (int i : answerNaive) {
            System.out.print(i + " ");
        }
        System.out.println();
      
    

    }
}
