/**
* An implementation of the Global/Regional Interchange Algorithm (GRIA)
*/


package locationallocation.Domain;


import locationallocation.Utils.IntegerSet;
import locationallocation.Utils.Location;
import locationallocation.Utils.CostMatrix;

public class GRIA extends Solver {

    /**
     * Holds weicostsghts. 
     */
    private CostMatrix  costs;

    /**
     * Holds P.
     */
    private int pn;
    /**
     * Holds facilities.
     */
    private Location[] facilities;

    /**
     * Solves the P-median problem with GRIA algorithm.
     * @param pnInput Number of facilities to allocate
     * @param costMatrixInput Matrix that holds costs (eg. distances) between facilities and demand locations.
     * @param facilitiesInput Locations of possible facilities
     */
    public GRIA(final CostMatrix costMatrixInput, final int pnInput, final Location[] facilitiesInput) {
        super(costMatrixInput, pnInput);

        this.costs = costMatrixInput;
        this.pn = pnInput;
        this.facilities = facilitiesInput;

    }


    /** 
     * Returns a set of facilities consired as the "optimal" locations based on demand.
     * 
     * @return int[] Array of facility location indices
     */
    public int[] solve() {

        
        int[] emptyArray = {};

        IntegerSet solution  = new IntegerSet(emptyArray), candidates = new IntegerSet(emptyArray);

        // Create initial solution and candidates
        // Initial solution is the first P facilities, candidates are facilities not in the initial solution

        for (int i = 0; i < this.costs.getRowCount(); i++) {
            if (i < this.pn) {
                solution.add(i);
            } else {
                candidates.add(i);
            }
        }
        
        // Global interchange
        IntegerSet[] returnFromGlobalInterchange = globalExhance(solution, candidates);


        // Regional interchange
        IntegerSet returnFromRegionalInterChange = regionalExchange(returnFromGlobalInterchange[0], returnFromGlobalInterchange[1]);
      
        this.setResultCost(this.costs.costSumForRowset(returnFromRegionalInterChange));
        this.setResult(returnFromRegionalInterChange);

        return this.getResult();
    }   



    /** 
     * Global interchange for (initial) solution. 
     *  1) Remove the facility which's removal increases total cost the least
     *  2) Add the facility which provides the greatest cost decrease
     *  3) If the swap lowers total cost, let it be. Reiterate phases 1-3 until it does not.
     * 
     * @param initialSolution Current solution
     * @param initialCandidates Possible facilities not in the current solution
     * @return int[] Solution and candidates after global interchange
     */
    private IntegerSet[] globalExhance(final IntegerSet initialSolution, final IntegerSet initialCandidates) {

        

        IntegerSet solution = initialSolution, candidates = initialCandidates;

        boolean bestObjectiveFunctionLessThanPreviousBest = true;
        double beforeDrop, toDropMin = Double.MAX_VALUE, toAddMin = Double.MAX_VALUE, tmp, afterDropAdd;
        int  toDrop = 0, toAdd = 0;

        while (bestObjectiveFunctionLessThanPreviousBest) {

            beforeDrop = this.costs.costSumForRowset(solution);
   
            // Find best facility to drop
            toDropMin = Double.MAX_VALUE;
            for (int i = 0; i < solution.getSetSize(); i++) {
                IntegerSet withoutISolution = new IntegerSet(solution);
                
                withoutISolution.removeByIndex(i);
                tmp =  this.costs.costSumForRowset(withoutISolution);
                
                if (tmp < toDropMin) {
                    toDropMin = tmp;
                    toDrop    = solution.getIntegerByIndex(i);
                }
            }
        
            IntegerSet solutionAfterDrop = new IntegerSet(solution);
            solutionAfterDrop.remove(toDrop);

            // Find the best facility to add
            toAddMin = Double.MAX_VALUE;
            for (int i = 0; i < candidates.getSetSize(); i++) {

                IntegerSet withISolution = new IntegerSet(solutionAfterDrop);

                int candidate = candidates.getIntegerByIndex(i);
                withISolution.add(candidate);
            
                tmp =  this.costs.costSumForRowset(withISolution);
 
                if (tmp < toAddMin) {
                    toAddMin = tmp;
                    toAdd    = candidate;
                }
            }

            solutionAfterDrop.add(toAdd);
            afterDropAdd = this.costs.costSumForRowset(solutionAfterDrop);
            
            // Cost before vs. after drop & add
            if (afterDropAdd <  beforeDrop) {
 
                solution    = new IntegerSet(solutionAfterDrop);

                // Update candidates
                candidates.remove(toAdd);
                candidates.add(toDrop);

            } else {
                bestObjectiveFunctionLessThanPreviousBest = false;
            }
            
        }

        // Return solution and candidates
        IntegerSet[] rtrn = {solution, candidates};
        return rtrn;
    }

    

    /** 
     * Regional interchange for solution: 
     *  1) Allocate each candidate facility to closest facility in solution
     *  2) Swap each candidate with the facility it's allocated to. Count cost 
     *  3) Find the swap that has the lowest total cost
     * 
     * @param initialSolution Current solution
     * @param initialCandidates Possible facilities not in the curren solution
     * @return IntegerSet Solution after regional interchange
     */
    private IntegerSet regionalExchange(final IntegerSet initialSolution, final IntegerSet initialCandidates) {

        IntegerSet solution = new IntegerSet(initialSolution), candidates = initialCandidates;

        // Distancematrix between locations and locations
        CostMatrix facilityCosts = new CostMatrix(this.facilities, this.facilities);
        double[][] distanceMatrixFacilities = facilityCosts.getCosts();
        
        // Allocate all candidates to solution facilities based on proximity
        //  For each candidate facility, find closest facility in solution
        double minSwapCost = Double.MAX_VALUE, tmpCost = 0.0;
        int minSwapCostReplaceBy = 0, minSwapCostToReplace = 0;
        
        for (int candidate : candidates.getIntegers()) {
            
            double min = Double.MAX_VALUE, distance = 0.0;
            int minFacility = 0;

            for (int solutionInt : solution.getIntegers()) {
                distance =  distanceMatrixFacilities[candidate][solutionInt];
                if (distance < min) {
                    min = distance;
                    minFacility = solutionInt;
                }
            }

            // Swap each candidate with the facility it's allocated to
            // And store which swap provides the lowest overall cost      
            IntegerSet tmpSolution = new IntegerSet(solution);
            tmpSolution.setChange(minFacility, candidate);
            tmpCost = this.costs.costSumForRowset(tmpSolution);
            if (tmpCost < minSwapCost) {
                minSwapCost = tmpCost;
                minSwapCostReplaceBy = candidate;
                minSwapCostToReplace = minFacility;
            }

        }
      
        
        // If found best swap is lower than total cost before regional interchange, persist it
        if (minSwapCost < this.costs.costSumForRowset(solution)) {
            solution.setChange(minSwapCostToReplace, minSwapCostReplaceBy);
        }
        
        // Return solution
        return solution;

    }
}