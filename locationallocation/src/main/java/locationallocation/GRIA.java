/**
* An implementation of the Global/Regional Interchange Algorithm (GRIA)
*/

package locationallocation;

import static locationallocation.Utils.DistanceMatrix.sumOfDistanceMatrixMiminumRowset;
import static locationallocation.Utils.DistanceMatrix.calculateDistanceMatrix;

import locationallocation.Utils.IntegerSet;
import locationallocation.Utils.Location;

public class GRIA {

    /**
     * Distancematrix of facilities vs demand locations.
     */

    private double[][] distanceMatrix;

    /**
     * Holds possible facility locations.
     */
    private Location[] facilities;

    /**
     * Solver for GRIA.
     * @param pn number of facilities to choose
     * @param distanceMatrixInput distancematrix between facilities and demand locations
     * @param faciliesInput Array of potential facility location
     */

    public GRIA(final int pn, final double[][] distanceMatrixInput, final Location[] faciliesInput) {

        this.distanceMatrix = distanceMatrixInput;
        this.facilities     = faciliesInput;

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

        System.out.println("GRIA GLOBAL:");
        for (int i : returnFromGlobalInterchange[0].getIntegerSet()) {
            System.out.print(i + ", ");
        }


        System.out.println("\nGRIA REGIONAL: ");
        IntegerSet returnFromRegionalInterChange = regionalExchange(returnFromGlobalInterchange[0], returnFromGlobalInterchange[1]);
        for (int i : returnFromRegionalInterChange.getIntegerSet()) {
            System.out.print(i + ", ");
        }
        System.out.println("Sum: " + sumOfDistanceMatrixMiminumRowset(returnFromRegionalInterChange.getIntegerSet(), this.distanceMatrix));


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
        int  toDrop = 0, toAdd = 0;

        while (bestObjectiveFunctionLessThanPreviousBest) {

            beforeDrop = sumOfDistanceMatrixMiminumRowset(solution.getIntegerSet(), this.distanceMatrix);
   
            // Find best facility to drop
            toDropMin = Double.MAX_VALUE;
            for (int i = 0; i < solution.getSetSize(); i++) {
                IntegerSet withoutISolution = new IntegerSet(solution.getIntegerSet());
                
                withoutISolution.removeByIndex(i);
                tmp =  sumOfDistanceMatrixMiminumRowset(withoutISolution.getIntegerSet(), this.distanceMatrix);
                
                if (tmp < toDropMin) {
                    toDropMin = tmp;
                    toDrop    = solution.getIntegerByIndex(i);
                }
            }
        
            IntegerSet solutionAfterDrop = new IntegerSet(solution.getIntegerSet());
            solutionAfterDrop.remove(toDrop);

            // Find the best facility to add
            toAddMin = Double.MAX_VALUE;
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
     * Regional interchange for (initial) solution: 
     *  1) Allocate each candidate facility to closest facility in solution
     *  2) Swap each candidate with the facility it's allocated to. Count cost 
     *  3) Find the swap that has the lowest total cost
     * 
     * @param initialSolution Current solution
     * @param initialCandidates Possible facilities not in the curren solution
     * @return IntegerSet Solution after regional interchange
     */
    private IntegerSet regionalExchange(final IntegerSet initialSolution, final IntegerSet initialCandidates) {

        IntegerSet solution = new IntegerSet(initialSolution.getIntegerSet()), candidates = initialCandidates;

        // Distancematrix between locations and locations
        double[][] distanceMatrixFacilities = calculateDistanceMatrix(this.facilities, this.facilities);
        
        // Allocate all candidates to solution facilities based on proximity
        //  For each candidate facility, find closest facility in solution
        double minSwapCost = Double.MAX_VALUE, tmpCost = 0.0;
        int minSwapCostReplaceBy = 0, minSwapCostToReplace = 0;
        
        for (int candidate : candidates.getIntegerSet()) {
            
            double min = Double.MAX_VALUE, distance = 0.0;
            int minFacility = 0;

            for (int solutionInt : solution.getIntegerSet()) {
                distance =  distanceMatrixFacilities[candidate][solutionInt];
                if (distance < min) {
                    min = distance;
                    minFacility = solutionInt;
                }
            }

            // Swap each candidate with the facility it's allocated to
            // And store which swap provides the lowest overall cost      
            IntegerSet tmpSolution = new IntegerSet(solution.getIntegerSet());
            tmpSolution.setChange(minFacility, candidate);
            tmpCost = sumOfDistanceMatrixMiminumRowset(tmpSolution.getIntegerSet(), this.distanceMatrix);
            if (tmpCost < minSwapCost) {
                minSwapCost = tmpCost;
                minSwapCostReplaceBy = candidate;
                minSwapCostToReplace = minFacility;
            }

        }
      
        
        // If found best swap is lower than total cost before regional interchange, persist it
        if (minSwapCost < sumOfDistanceMatrixMiminumRowset(solution.getIntegerSet(), this.distanceMatrix)) {
            solution.setChange(minSwapCostToReplace, minSwapCostReplaceBy);
        }
        
        // Return solution
        return solution;

    }
}