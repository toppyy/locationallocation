package locationallocation.Domain;

import locationallocation.Utils.LocationLoader;
import locationallocation.Utils.Location;
import locationallocation.Utils.Arguments;

import locationallocation.Utils.CostMatrix;
import static locationallocation.Utils.Writer.writeLines;
import java.io.IOException;


/**
 * Algorithms are accessed through this. Holds application logic.
 */

public class Pmedian {


    private Solver algorithm;
    private Location[] demandLocations;
    private Location[] possibleLocations;
    private CostMatrix costs;
    private int p;

    /**
     * Possible algorithms.
     */
    private String[] algorithms;


    public Pmedian() {
        this.demandLocations = new Location[0];
        this.possibleLocations = new Location[0];
        this.p = 0;

        this.algorithms = new String[3];
        this.algorithms[0] =  "Naive";
        this.algorithms[1] =  "TeitzBart";
        this.algorithms[2] =  "GRIA";
    }
    /**
     * Constuctor with given Arguments.
     * @param args Parsed Arguments from cmd.
     */
    public Pmedian(final Arguments args) {
        

        this.algorithms = new String[3];
        this.algorithms[0] =  "Naive";
        this.algorithms[1] =  "TeitzBart";
        this.algorithms[2] =  "GRIA";

        
        this.loadDemandlocations(args.getDemandLocationPath());
        this.loadPossiblelocations(args.getPossibleLocationPath());
        this.setP(args.getP());

        this.setSolver(args.getAlgorithm());
        
        this.calculateCostMatrix();


    }

    public final void setP(final String inputP) {
        this.p = Integer.parseInt(inputP);
    }

    public final void setP(final int inputP) {
        this.p = inputP;
    }

    public final int getP() {
        return this.p;
    }

    public final String[] getAlgorithms() {
        return this.algorithms;
    }

    public final void loadDemandlocations(final String path) {
        this.demandLocations = this.loadLocations(path);
        
    }

    public final int getNumberOfDemandLocations() {
        return this.demandLocations.length;
    }
    

    public final void loadPossiblelocations(final String path) {
        this.possibleLocations = this.loadLocations(path);
    }

    public final int getNumberOfPossibleLocations() {
        return this.possibleLocations.length;
    }
    
    /** 
     * Load a file as locations.
     * @param path
     * @return Location[]
     */
    private Location[] loadLocations(final String path) {
        LocationLoader data = new LocationLoader(path, true);
        Location[] locations = data.loadAsLocations();
        return locations;
    }


    public final void calculateCostMatrix() {

        this.costs = new CostMatrix(this.possibleLocations, this.demandLocations);
        
    }


    public final boolean costMatrixExists() {
        return this.costs != null;
    }



    public final void setSolverToNaive() {
        this.algorithm = new Naive();
    }

    public final void setSolverToGRIA() {
        this.algorithm = new GRIA();
        this.algorithm.setPossibleLocations(this.possibleLocations);
    }

    public final void setSolverToTeitzBart() {
        this.algorithm = new TeitzBart();
    }

    public final void setSolver(final String chosenAlgorithm) {
        if (chosenAlgorithm.equals("Naive")) {
            this.setSolverToNaive();
        }
        if (chosenAlgorithm.equals("GRIA")) {
            this.setSolverToGRIA();
        }
        if (chosenAlgorithm.equals("TeitzBart")) {
            this.setSolverToTeitzBart();
        }
    }

    public final String getAlgorithmName() {
        return this.algorithm.getName();
    }


    public final void solve() {
        this.algorithm.solveWithParams(this.costs, this.p);

    }

    public final double getResultCost() {
        return this.algorithm.getResultCost();
    }

    /**
     * Get result as demand facility - possible facility pairs.
     * @return For each demand facility, the index of possible facility location it's allocated to.
     */
    public int[] getResultAllocations() {
        return this.algorithm.getResultAllocations();
    }
    /**
     * Write results (facility allocations) to a file.
     * Output is a CSV-file containing two columns: one for demand location and another for the facility it's allocated to.
     * @param filePathInput Path to write results to.
     */
    public void writeAllocationsToFile(final String filePathInput) {

        int[] allocations = this.getResultAllocations();
        String[] lines = new String[allocations.length + 1];

        // Header
        lines[0] = "demandlocation;facility\n";
        
        for (int demandLocationIdx = 0; demandLocationIdx < this.demandLocations.length; demandLocationIdx++) {

            int idx = allocations[demandLocationIdx];

            String demandLocationId     = this.demandLocations[demandLocationIdx].getId();
            String facilityLocationId   = this.possibleLocations[idx].getId();

            String line = demandLocationId + ";" + facilityLocationId + "\n";

            lines[demandLocationIdx + 1] = line;
        }

    
        
        try {
            writeLines(filePathInput, lines);
            System.out.println("Wrote lines to " + filePathInput);
        } catch (IOException e) { 
      
            System.out.println("Problem with writing the results");
            e.printStackTrace(); 
          } 

        
    }


     /**
     * Check if everything required for solving exists.
     * @return boolean. True if problem is specified.
     */
    
    public final boolean solveRequirementsFulfilled() {
        Boolean requirements = true;

        if (!this.costMatrixExists()) {
            return false;
        }

        if (this.p <= 0) {
            return false;
        }

        // GRIA is the only algorithm that requires possible locations, so: 
        if (this.algorithm.getName().equals("GRIA") & this.getNumberOfPossibleLocations() == 0) {
            return false;
        }       
 

        return requirements;
    }
    


}