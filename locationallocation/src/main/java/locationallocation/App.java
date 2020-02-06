/**
 * Solves the p-median problem
 */
package locationallocation;


import locationallocation.Utils.LocationLoader;
import locationallocation.Utils.Location;
import locationallocation.Utils.CostMatrix;



public final class App {

    private App() {
    }

    /**
     * Number of facilities to choose.
     */
    private static final int PN = 3;

    public static void main(final String[] args) {

        String path = "src/test/resources/testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path, true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = "src/test/resources/testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path, true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();

        
        // Calculate distance matrix
       


        CostMatrix costs = new CostMatrix();
        costs.calculateDistanceMatrix(testFacilityLocations, testDemandLocations);

        Solver teitzbart = new TeitzBart(costs, PN);
        teitzbart.solve();
        System.out.println("\nTeitzBart: ");
        teitzbart.printResults();
    

        Solver naive = new Naive(costs, PN);

        naive.solve();
        System.out.println("\nNaive: ");
        naive.printResults();

        // GRIA

        Solver griaSolver = new GRIA(costs, PN, testFacilityLocations);
        griaSolver.solve();
        System.out.println("\nGRIA: ");
        griaSolver.printResults();
   

    }
}
