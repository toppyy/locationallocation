/**
 * Solves the p-median problem
 */
package locationallocation;

import static locationallocation.Utils.DistanceMatrix.calculateDistanceMatrix;
import locationallocation.Utils.LocationLoader;
import locationallocation.Utils.Location;

import static locationallocation.TeitzBart.solveTeitzBart;
import static locationallocation.Naive.solveNaive;


public final class App {

    private App() {
    }

    /**
     * Number of facilities to choose.
     */
    private static final int PN = 3;

    public static void main(final String[] args) {

        String path = ".\\src\\test\\resources\\testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path, true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = ".\\src\\test\\resources\\testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path, true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();

        // pn for running algorithms
        


        // Calculate distance matrix
        double[][] dist = calculateDistanceMatrix(testFacilityLocations, testDemandLocations);

        int[] answerTB = solveTeitzBart(PN, dist);

        
        System.out.println("\nTeitzBart: ");
        for (int i : answerTB) {
            System.out.print(i + " ");
        }
        System.out.println("\n\n");

        int[] answerNaive = solveNaive(PN, dist);
        System.out.println("Naive: ");
        for (int i : answerNaive) {
            System.out.print(i + " ");
        }
        System.out.println("\n\n");
        
        GRIA griaSolver = new GRIA(PN, dist, testFacilityLocations);
      

    }
}
