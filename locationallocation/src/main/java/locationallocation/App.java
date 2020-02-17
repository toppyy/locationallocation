package locationallocation;


import locationallocation.Utils.LocationLoader;
import locationallocation.Utils.Location;
import locationallocation.Utils.CostMatrix;

import locationallocation.Domain.GRIA;
import locationallocation.Domain.Naive;
import locationallocation.Domain.TeitzBart;

import locationallocation.Qualitytest.Reporter;
import locationallocation.Qualitytest.Testresult;


/**
 * Solves the p-median problem.
 */


public final class App {

    private App() {
    }

    /**
     * Number of facilities to choose.
     */
    private static final int PN = 7;

    public static void main(final String[] args) {

        String path = "src/test/resources/testdata_1_demand_locations.csv";
        LocationLoader testdataDemand = new LocationLoader(path, true);
        Location[] testDemandLocations = testdataDemand.loadAsLocations();

        path = "src/test/resources/testdata_1_facility_locations.csv";
        LocationLoader testdataFacility = new LocationLoader(path, true);
        Location[] testFacilityLocations = testdataFacility.loadAsLocations();

        
       
        
        // Calculate cost matrix of euclidean distances
        CostMatrix costs = new CostMatrix(testFacilityLocations, testDemandLocations);

        TeitzBart teitzbart = new TeitzBart(costs, PN);
        teitzbart.solve();
        System.out.println("\nTeitzBart: ");
        teitzbart.printResults();
    
        Naive naive = new Naive(costs, PN);

        naive.solve();
        System.out.println("\nNaive: ");
        naive.printResults();

        GRIA griaSolver = new GRIA(costs, PN, testFacilityLocations);

        griaSolver.solve();
        System.out.println("\nGRIA: ");
        griaSolver.printResults();
        
      

        // Test quality and performance
        System.out.println("Running performance tests..");

        Reporter report = new Reporter();


        // Change path!
        String outputfolder = "//home/";
        

        // Large tests
        
        String[] algorithms = {"TB", "GRIA"};
        int[] limits = {2, 25};

        report.setPlimits(limits);
        Testresult[] largeTests = report.runTests(algorithms, 50, 300, 10);

        
        //report.writeResults(outputfolder + "/large tests with iterations.txt");

    
        // Small tests


        String[] algorithmsSmall = {"TB", "GRIA", "Naive"};
        int[] limitsSmall = {1, 8};

        report.setPlimits(limitsSmall);

        Testresult[] smallTests = report.runTests(algorithmsSmall, 20, 100, 10);
        
        //report.writeResults(outputfolder + "/small tests with iterations.txt");

        System.out.println("... tests done.");
       
        

    }
}
