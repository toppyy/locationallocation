package locationallocation;

import locationallocation.Domain.Pmedian;
import locationallocation.Qualitytest.Reporter;
import locationallocation.Qualitytest.Testresult;

import locationallocation.Utils.Arguments;

import locationallocation.UI.GUI;
/**
 * Solves the p-median problem.
 */


public final class App {

    private App() {
    }


    public static void main(final String[] args) {

        



        Pmedian app = new Pmedian();

        if (args.length > 0) {

            // Performance tests
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


            // Run application with CMD-arguments
            Arguments arguments = new Arguments(args);

            if (arguments.allRequiredArgumentsGiven()) {

                Pmedian appFromArgs = new Pmedian(arguments);

                if (appFromArgs.solve()) {

                    System.out.println("Solved with " + arguments.getAlgorithm() + ". Cost: " + appFromArgs.getResultCost());

                    appFromArgs.writeAllocationsToFile(arguments.getOutputPath());
                }

            } else {
                System.out.println("All required arguments not given.");
            }


        } else {

            GUI application = new GUI(app);

            application.start();
        }
    }
}
