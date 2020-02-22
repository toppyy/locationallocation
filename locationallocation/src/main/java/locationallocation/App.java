package locationallocation;

import locationallocation.Domain.Pmedian;
import locationallocation.Qualitytest.Reporter;
import locationallocation.Qualitytest.Testresult;

import locationallocation.UI.GUI;
/**
 * Solves the p-median problem.
 */


public final class App {

    private App() {
    }


    public static void main(final String[] args) {

        Pmedian app = new Pmedian();

        // To run a simple example, try: gradle run --args "example 8"
        if (args.length > 0) {

            if (args[0].equals("example")) {

                Integer p = Integer.parseInt(args[1]);

                // Set up
                app.setP(p);
                
                app.loadDemandlocations("src/test/resources/testdata_1_demand_locations.csv");
                app.loadPossiblelocations("src/test/resources/testdata_1_facility_locations.csv");

                app.calculateCostMatrix();

                // Solve with all algorithms
                app.setSolverToTeitzBart();
                app.solve();
                double costTB = app.getResultCost();

                app.writeAllocationsToFile("/home/tob/Desktop/dump/RES_TB.csv");

                app.setSolverToGRIA();
                app.solve();
                double costGRIA = app.getResultCost();

                app.setSolverToNaive();
                app.solve();
                double costNaive = app.getResultCost();

                app.writeAllocationsToFile("/home/tob/Desktop/dump/RES_NAIVE.csv");

                System.out.println("Total cost of solution:");
                System.out.println("TB: " + costTB);
                System.out.println("GRIA: " + costGRIA);
                System.out.println("Naive: " + costNaive);
                
            }

            if (args[0].equals("perf")) {

                String outputfolder = args[1];                

                // Test quality and performance
                System.out.println("Running performance tests..");

                Reporter report = new Reporter();

                // Large tests
                
                String[] algorithms = {"TB", "GRIA"};
                int[] limits = {2, 25};

                report.setPlimits(limits);
                Testresult[] largeTests = report.runTests(algorithms, 50, 300, 10);

                
                report.writeResults(outputfolder + "/large tests with iterations.txt");

            
                // Small tests


                String[] algorithmsSmall = {"TB", "GRIA", "Naive"};
                int[] limitsSmall = {2, 7};

                report.setPlimits(limitsSmall);

                Testresult[] smallTests = report.runTests(algorithmsSmall, 20, 300, 10);
                
                report.writeResults(outputfolder + "/small tests with iterations.txt");

                System.out.println("... tests done.");
            

            }
    
        } else {

            GUI application = new GUI(app);

            application.start();
        }
    }
}
