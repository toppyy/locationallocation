/**
* An implementation of the Global/Regional Interchange Algorithm (GRIA)
*/

package locationallocation;

import static locationallocation.Utils.DistanceMatrix.sumOfDistanceMatrixMiminumRowset;

import locationallocation.Utils.IntegerSet;

public class GRIA {

    /**
     * Distancematrix of facilities vs demand locations.
     */

    private double[][] distanceMatrix;

    /**
     * Solver for GRIA.
     * @param pn number of facilities to choose
     * @param distanceMatrixInput double[][] distancematrix between facilities and demand locations
     */

    public GRIA(final int pn, final double[][] distanceMatrixInput) {

        this.distanceMatrix = distanceMatrixInput;

        int[] emptyArray = {};

        IntegerSet solution  = new IntegerSet(emptyArray), candidates = new IntegerSet(emptyArray);

        // Create initial solution and candidates
        // Initial solution is the first P facilities, candidates are facilities not in the initial solution

        for (int i = 0; i < distanceMatrix.length; i++) {
            if (i < pn) {
                solution.add(i);
            } else {
                candidates.add(i);
            }
        }
        
        // Global interchange
        IntegerSet[] returnFromGlobalInterchange = globalExhance(solution, candidates);

    }



    /** 
     * Global interchange for (initial) solution. 
     *  1) Remove the facility which's removal increases total cost the least
     *  2) Add the facility which provides the greatest cost decrese
     *  3) If the swap lowers total cost, let it be. If not, reiterate phases 1-3 until it does.
     * 
     * @param initialSolution Current solution
     * @param initialCandidates Possible facilities not in the current solution
     * @return int[] Solution and candidates after global interchange
     */
    private IntegerSet[] globalExhance(final IntegerSet initialSolution, final IntegerSet initialCandidates) {

        IntegerSet solution = initialSolution, candidates = initialCandidates;

        boolean bestObjectiveFunctionLessThanPreviousBest = true;
        double beforeDrop, toDropMin = Double.MAX_VALUE, toAddMin = Double.MAX_VALUE, tmp, afterDropAdd;
        int  toDrop = 0, toAdd = 0, toDropIndex = 0;

        int candidatesSize = candidates.getSetSize();


        while (bestObjectiveFunctionLessThanPreviousBest) {

            beforeDrop = sumOfDistanceMatrixMiminumRowset(solution.getIntegerSet(), this.distanceMatrix);
            
            toDropMin = Double.MAX_VALUE;

            for (int i = 0; i < solution.getSetSize(); i++) {
                IntegerSet withoutISolution = new IntegerSet(solution.getIntegerSet());
                
                withoutISolution.removeByIndex(i);
                tmp =  sumOfDistanceMatrixMiminumRowset(withoutISolution.getIntegerSet(), this.distanceMatrix);
                
                
                if (tmp < toDropMin) {
                    toDropMin = tmp;
                    toDrop    = solution.getIntegerByIndex(i);
                    toDropIndex = i;
                }
            }
        

            IntegerSet solutionAfterDrop = new IntegerSet(solution.getIntegerSet());
            solutionAfterDrop.remove(toDrop);

            // Find the best site to add
            for (int i = 0; i < candidates.getSetSize(); i++) {

                IntegerSet withISolution = new IntegerSet(solutionAfterDrop.getIntegerSet());

                int candidate = candidates.getIntegerByIndex(i);
                withISolution.add(candidate);
            
                tmp =  sumOfDistanceMatrixMiminumRowset(withISolution.getIntegerSet(), this.distanceMatrix);
 
                if (tmp < toAddMin) {
                    toAddMin = tmp;
                    toAdd    = candidate;
                }
            }

            solutionAfterDrop.add(toAdd);
            afterDropAdd = sumOfDistanceMatrixMiminumRowset(solutionAfterDrop.getIntegerSet(), this.distanceMatrix);
            
            // Cost before vs. after drop & add
            if (afterDropAdd <  beforeDrop) {
 
                solution    = new IntegerSet(solutionAfterDrop.getIntegerSet());

                // Update candidates
                int[] candidatesTMP = new int[candidatesSize];
                int addedIdx = 0;
                for (int i = 0; i < distanceMatrix.length; i++) {
                    if (!solution.inSet(i)) {
                        candidatesTMP[addedIdx] = i;
                        addedIdx++;
                    }
                }
                candidates = new IntegerSet(candidatesTMP);

            } else {
                bestObjectiveFunctionLessThanPreviousBest = false;
            }
            
        } // while-end

        // Return solution and candidates
        IntegerSet[] rtrn = {solution, candidates};
        return rtrn;


    }

    

    /** 
     * Regional interchange for (initial) solution: 
     *  1) Allocate each candidate facility to closest facility in solution
     *  2) Swap each candidate with the facility it's allocated to. Count cost 
     *  3) Find the swap that has the lowest total cost
     * 
     * @param solution Current solution
     * @param candidates Possible facilities not in the curren solution
     * @return IntegerSet Solution after regional interchange
     */
    private IntegerSet regionalExchange(final IntegerSet solution, final IntegerSet candidates) {


        // Distancematrix between locations and locations
        
        // Allocate all candidates to solution facilities

        // Swap each candidate with the facility it's allocated to

        // See which provides the lowest overall cost

        // Return solution and candidates
        return solution;

    }
}