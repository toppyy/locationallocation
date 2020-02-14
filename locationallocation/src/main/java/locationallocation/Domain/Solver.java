/**
 * Generic solver class
 */

package locationallocation.Domain;
import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.IntegerSet;


public abstract class Solver {

    /**
     * Holds P: number of facilities to allocate.
     */
    private int pn;
    /**
     * Holds weights between facilities and demand locations.
     */
    private CostMatrix costMatrix;

    /**
     * Holds result after solve.
     */
    private int[] result;
    /**
     * Holds cost of result.
     */
    private double resultCost = -1;

    /**
     * Constuctor.
     * @param pnInput Number of facilities to allocate
     * @param costMatrixInput Matrix that holds costs (eg. distances) between facilities and demand locations.
     */
    public Solver(final CostMatrix costMatrixInput, final int pnInput) {
        this.pn = pnInput;
        this.costMatrix = costMatrixInput;
    }

    
    /**
     * Getter for pn.
     * @return P
     */
    public int getP() {
        return this.pn;
    }
    /**
     * Getter for cost matrix.
     * @return Matrix of costs between facilities and demand facilities.
     */
    public CostMatrix getCosts() {
        return this.costMatrix;
    }
    /**
     * Print result.
     */
    public void printResults() {

        if (resultCost < 0) {
            System.out.println("No results yet. Try .solve() first.");
            return;
        }

        
        
        for (int i : this.result) {
            System.out.print(i + " ");
        }
        System.out.println("\nCostsum: " + this.resultCost);
        System.out.println("\n");
    }
    /**
     * Getter for result.
     * @return Result (a set of integers).
     */
    public int[] getResult() {
        return this.result;
    }
    /**
     * Setter for result.
     * @param resultInput A set of integers.
     */
    public void setResult(final int[] resultInput) {
        this.result = resultInput;
    }
    /**
     * Setter for result as a IntegerSet.
     * @param resultInput A set of integers.
     */
    public void setResult(final IntegerSet resultInput) {
        this.result = resultInput.getIntegers();
    }
    /**
     * Getter for result cost.
     * @return Sum of result costs.
     */
    public double getResultCost() {
        return this.resultCost;
    }
    /**
     * Setter for result cost.
     * @param resultCostInput Sum of result costs.
     */
    public void setResultCost(final double resultCostInput) {
        this.resultCost = resultCostInput;
    }
    
    
    /**
     * Solve.
     * @return Set of demand locations to allocate P facilities to.
     */
    public abstract int[] solve();
}
