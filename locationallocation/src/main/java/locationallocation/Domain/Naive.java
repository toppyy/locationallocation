

package locationallocation.Domain;

import static locationallocation.Utils.Combinations.createCombinations;
import locationallocation.Utils.CostMatrix;
/**
 * Naive/brute force solution for the p-median problem.
 *  - Minimize distance by going through all possible combinatios
 */

public class Naive extends Solver {


    /**
     * Solves the P-median problem with a brute force method.
     * @param pnInput Number of facilities to allocate
     * @param costMatrixInput Matrix that holds costs (eg. distances) between facilities and demand locations.
     */
    public Naive(final CostMatrix costMatrixInput, final int pnInput) {
        super(costMatrixInput, pnInput);
    }


     /** 
     * Returns the optimal set of facility locations based on demand.
     *
     * @return int[] Array of facility location indices
     */
    public int[] solve() {


        double  currentMinimum = Double.MAX_VALUE, toTestAgainst;
        int     indexOfCurrentMinimum = 0;

        CostMatrix costs = this.getCosts();

        // Create all possible combinations of facilities of size pn
        int[] m = new int[costs.getRowCount()];
        for (int i = 0; i < m.length; i++) {
            m[i] = i + 1;
        }
        int[][] combinations = createCombinations(m, this.getP());


        for (int i = 0; i < combinations.length; i++) {
            toTestAgainst = costs.costSumForRowset(combinations[i]);

            if (toTestAgainst < currentMinimum) {
                currentMinimum = toTestAgainst;
                indexOfCurrentMinimum = i;
            }

        }
        this.setResultCost(currentMinimum);
        this.setResult(combinations[indexOfCurrentMinimum]);

        return this.getResult();

    }


}