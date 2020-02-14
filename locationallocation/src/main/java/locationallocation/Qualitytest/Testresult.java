
package locationallocation.Qualitytest;
/**
 * Holds the results of locationallocation.Qualitytest.Reporter.
 */


public class Testresult {


    /**
     * Inputs.
     */
    private int p, candidates, demandlocations;

    /**
     * Cost.
     */
    private double cost;

    /**
     * Runtime.
     */
    private long timeElapsed;

    /**
     * Input strings.
     */
    private String algorithmName;

    /**
     * Test result. Holds test results.
     * @param pInput Number of facilities to allocate in test.
     * @param candidatesInput Number of candidates in test.
     * @param demandlocationsInput Number of demand locations in test.
     * @param costInput Cost for solution.
     * @param timeElapsedInput Run time of solution.
     * @param algorithmNameInput Name/abbrevation of tested algorithm.
     */

    public Testresult(final int pInput, final int candidatesInput, final int demandlocationsInput, final double costInput, final long timeElapsedInput, final String algorithmNameInput) {

        this.p = pInput;
        this.candidates = candidatesInput;
        this.demandlocations = demandlocationsInput;
        this.cost = costInput;
        this.timeElapsed = timeElapsedInput;
        this.algorithmName = algorithmNameInput;

    }



    /**
     * Returns the test result in a String form. String contains results separated by ";".s
     * @return String Test results separated by ";".
     */
    @Override
    public String toString() {
        return this.p + ";" + this.candidates + ";" + this.demandlocations + ";" + this.cost + ";" + this.timeElapsed + ";" + this.algorithmName;

    }


    /**
     * Getter for P.
     * @return P.
     */
    public int getP() {
        return this.p;
    }

    /**
     * Getter for candidates.
     * @return candidates.
     */
    public int getCandidates() {
        return this.candidates;
    }

    /**
     * Getter for demandlocations.
     * @return demandlocations.
     */
    public int getDemandlocations() {
        return this.demandlocations;
    }


    /**
     * Getter for cost.
     * @return cost.
     */
    public double getCost() {
        return this.cost;
    }

    /**
     * Getter for time elapsed.
     * @return Time elapsed.
     */
    public long getTimeElapsed() {
        return this.timeElapsed;
    }

     /**
     * Getter for algorithm.
     * @return Algorithm.
     */
    public String getAlgorithmName() {
        return this.algorithmName;
    }


}


