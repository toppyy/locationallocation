

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
    private int p;

    private CostMatrix costMatrix;
    private int[] result;
    private double resultCost = -1;
    private String nameOfAlgorithm;
   

    /**
     * Constuctor.
     * @param nameInput Name of algorithm.
     */
    public Solver(final String nameInput) {
        this.nameOfAlgorithm = nameInput;
    }

    public final int getP() {
        return this.p;
    }

    public final void setP(final int pInput) {
        this.p = pInput;
    }

    public final String getName() {
        return this.nameOfAlgorithm;
    }


    public final CostMatrix getCosts() {
        return this.costMatrix;
    }


    public final void setCosts(final CostMatrix inputCostMatrix) {
        this.costMatrix = inputCostMatrix;
    }

    public final void printResults() {

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
     * @return Result (a set of integers describing the indexes of chosen locations).
     */
    public final int[] getResult() {
        return this.result;
    }
    /**
     * Getter for demand facility allocations.
     * Given a result, to which possible facility each demand facility is allocated to?
     * @return  For each demand facility, the index of possible facility location it's allocated to.
     */
    public int[] getResultAllocations() {
        return this.costMatrix.allocateColumnsToRows(this.result);
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
   

    public final double getResultCost() {
        return this.resultCost;
    }
  
    public final void setResultCost(final double resultCostInput) {
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
