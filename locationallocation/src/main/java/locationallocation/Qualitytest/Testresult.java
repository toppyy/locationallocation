
package locationallocation.Qualitytest;
/**
 * Holds the results of locationallocation.Qualitytest.Reporter.
 */


public class Testresult {


    private int p, candidates, demandlocations;
    private double cost;
    private long timeElapsed;
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


    public final int getP() {
        return this.p;
    }

    public final int getCandidates() {
        return this.candidates;
    }

    public final int getDemandlocations() {
        return this.demandlocations;
    }


    public final double getCost() {
        return this.cost;
    }


    public final long getTimeElapsed() {
        return this.timeElapsed;
    }

    public final String getAlgorithmName() {
        return this.algorithmName;
    }


}


