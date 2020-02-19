

package locationallocation.Domain;
import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;
import locationallocation.Utils.IntegerSet;
/**
 * Generic solver class.
 */

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
     * Name of algorithm.
     */
    private String nameOfAlgorithm;
   

    /**
     * Constuctor.
     * @param nameInput Name of algorithm.
     */
    public Solver(final String nameInput) {
        this.nameOfAlgorithm = nameInput;
    }

    
    /**
     * Getter for pn.
     * @return P
     */
    public int getP() {
        return this.pn;
    }
    /**
     * Setter for pn.
     * @param pInput
     */
    public void setP(final int pInput) {
        this.pn = pInput;
    }
    /**
     * Getter for name.
     * @return Name of algorithm.
     */
    public String getName() {
        return this.nameOfAlgorithm;
    }


    /**
     * Getter for cost matrix.
     * @return Matrix of costs between facilities and demand facilities.
     */
    public CostMatrix getCosts() {
        return this.costMatrix;
    }
    /**
     * Setter for cost matrix.
     * @param inputCostMatrix 
     */
    public void setCosts(final CostMatrix inputCostMatrix) {
        this.costMatrix = inputCostMatrix;
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

    /**
     * Solve with params.
     * @param pnInput Number of facilities to allocate
     * @param costMatrixInput Matrix that holds costs (eg. distances) between facilities and demand locations.
     * @return Set of demand locations to allocate P facilities to.
     */
    public abstract int[] solveWithParams(CostMatrix costMatrixInput, int pnInput);

    /**
     * Setter for possible facilities.
     * @param facilitiesInput Possible facilities to allocate.
     */
    public abstract void setPossibleLocations(Location[] facilitiesInput);
}
