/*
* An implementation of the Teitz and Bart algorithm.
* Solves the p-median problem.
*/

package locationallocation;


import locationallocation.Utils.IntegerSet;
import locationallocation.Utils.CostMatrix;


public class TeitzBart extends Solver {

    /**
     * Solves the P-median problem with TeitzBart algorithm.
     * @param pnInput Number of facilities to allocate
     * @param costMatrixInput Matrix that holds costs (eg. distances) between facilities and demand locations.
     */
    public TeitzBart(final CostMatrix costMatrixInput, final int pnInput) {
        super(costMatrixInput, pnInput);

    }

    /** 
     * Returns a set of facilities consired as the "optimal" locations based on demand.
     * 
     * @return int[] Array of facility location indices
     */
    public int[] solve() {

        int newcandidate = -1;
        double toTestAgainst;

        CostMatrix costs = this.getCosts();
        int pn = this.getP();


        // Init with first pn locations
        int[] initialSet = new int[pn];
        int[] initialCandidates = new int[costs.getRowCount() - pn];

        int ci = 0;
        for (int i = 0; i < costs.getRowCount(); i++) {
            if (i < pn) {
                initialSet[i] = i;
            } else {
                initialCandidates[ci] = i;
                ci++;
            }
        }


        IntegerSet solutions = new IntegerSet(initialSet);
        IntegerSet candidates = new IntegerSet(initialCandidates);
        
        double minOfTests = Double.MAX_VALUE, testsum;
        int toBeReplaced = -1;


        while (!candidates.isEmpty()) {
            
        
            toTestAgainst = costs.costSumForRowset(solutions);

            newcandidate = candidates.pop();

            int[] testSet = solutions.getIntegers();
            for (int i = 0; i < testSet.length; i++) {


                int[] tmp = testSet.clone();
                tmp[i] = newcandidate;

                testsum = costs.costSumForRowset(tmp);
                
                if (testsum < minOfTests) {
                    minOfTests      = testsum;
                    toBeReplaced = testSet[i];
                }
                
            }
            // If better solution is found, change set 
            if (minOfTests < toTestAgainst) {
                
                if (solutions.setChange(toBeReplaced, newcandidate)) {
                    candidates.add(toBeReplaced);
                }
            }
        }
        
        this.setResultCost(costs.costSumForRowset(solutions));
        this.setResult(solutions);

       return this.getResult();
    }

}