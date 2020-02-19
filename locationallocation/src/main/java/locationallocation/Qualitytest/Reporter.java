package locationallocation.Qualitytest;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;
import locationallocation.Utils.Writer;

import java.util.Random;
import java.io.IOException;

import locationallocation.Domain.GRIA;
import locationallocation.Domain.Naive;
import locationallocation.Domain.Solver;
import locationallocation.Domain.TeitzBart;

/**
 * Runs performance and quality reports for random solutions.
 */


public class Reporter {

    /**
     * Bunds for values of P to be tested.
     */
    private int[] pLimits;

    /**
     * Cost matrix to test against.
     */
    private CostMatrix costs;

    /**
     * Candidate facilities.
     */
    private Location[] facilities;


    /**
     * Random number generation.
     */
    private Random random;

    /**
     * Test results as a set of strings for writing to a flatfile.
     */
    private Testresult[] results;

    /**
     * Number of candidate facilities and demand locations.
     */
    private int candidates, demandlocations;

    /**
     * Writes data on performance and quality.
     */
    public Reporter() {

        int[] defaultPlimits = {2};
        this.pLimits    = defaultPlimits;
        this.random = new Random();
        
    }

    /**
     * Create a random number between a specific range.
     * @return A random number.
     */

    private double randomNumber() {
        double rangeMin = 1;
        double rangeMax = 200;

        return rangeMin + (rangeMax - rangeMin) * this.random.nextDouble();

    }

    /**
     * Test setup. Create a random problem of given size.
     */
    private void init() {

        Location[] testLocations1    = new Location[this.candidates];
        Location[] testLocations2    = new Location[this.demandlocations];

        this.facilities = testLocations1; // GRIA needs these.


        for (int i = 0; i < candidates; i++) {
            testLocations1[i] = new Location(this.randomNumber(), this.randomNumber());
        }

        for (int i = 0; i < demandlocations; i++) {
            testLocations2[i] = new Location(this.randomNumber(), this.randomNumber());
        }
      
        // Calculate distance matrix
        CostMatrix costsOfRandom = new CostMatrix(testLocations1, testLocations2);

        this.costs = costsOfRandom;

    }

    /**
     * Runs a single test for given parameters.
     * @param p P.
     * @param algorithmName Name of algorithm to test.
     * @return String containting test info.
     */
    private Testresult runTest(final int p, final String algorithmName) {

        Solver a = new Naive();
        
        if (algorithmName == "TB") {
            a = new TeitzBart();
        } 
        
        if (algorithmName == "GRIA") {
            a = new GRIA();
            a.setPossibleLocations(this.facilities);
        }
        
        long startTime = System.currentTimeMillis();
        a.solveWithParams(costs, p);
        long endTime = System.currentTimeMillis();
		long timeElapsed = (endTime - startTime);

        return new Testresult(p, this.candidates, this.demandlocations, a.getResultCost(), timeElapsed, algorithmName);

    }

    
    /**
     * Runs test for specified algorithm.
     * @param algorithmsToTest Names of algorithm to test. Can be one of three: TB, GRIA or Naive.  
     * @param candidatesInput Number of candidates.
     * @param demandlocationsInput Number of demandlocations.
     * @param iterationsInput How many times to run the tests.
     * @return Testresult[] A set of strings containing test results in a 
     */
    public Testresult[] runTests(final String[] algorithmsToTest, final int candidatesInput, final int demandlocationsInput,  final int iterationsInput) {


        int start = this.pLimits[0];
        int end   = this.pLimits[1];

        this.candidates = candidatesInput;
        this.demandlocations = demandlocationsInput;

       

        int idx = 0;

        // +1 to end to make it inclusive
        Testresult[] testResults   = new Testresult[((1 + end) - start) * iterationsInput * algorithmsToTest.length];

        // For each P between start - end
        // Solve with each algorithm as many times as requested(=iterations)
        // Create a new random problem for each iteration

        for (int i = start; i <= end; i++) {


            
            int iterations = iterationsInput;

            while (iterations > 0) {
                
                this.init();                
                for (String algorithmName : algorithmsToTest) {
                    testResults[idx]   = this.runTest(i, algorithmName);
                    idx++;
                }
                iterations--;
            
            }
        }

        this.results = testResults;
        return testResults;
    }

    /**
     * Write results to a flatfile.
     * @param outputpathInput Path (inc. filename) of file to write results to.
    */
    public void writeResults(final String outputpathInput) {
       
        try {
            String filename = outputpathInput;
            Writer w = new Writer(filename);
           
            for (Testresult s : this.results) {
                
                w.write(s.toString() + "\n");
              
            }

            w.close();
            System.out.println("Wrote results to: " + filename);

        } catch (IOException e) { 
      
          System.out.println("Problem with writing the results");
          e.printStackTrace(); 
          
          
        } 
    }



    /**
    * Setter for P-limits.
    * @param pLimitsInput P-limits. An array of two integers, [start, end]. Tests are run from start to end.
    */

    public void setPlimits(final int[] pLimitsInput) {
        this.pLimits = pLimitsInput;
    }

}