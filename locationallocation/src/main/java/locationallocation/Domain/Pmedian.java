package locationallocation.Domain;

import locationallocation.Utils.LocationLoader;
import locationallocation.Utils.Location;
import locationallocation.Utils.CostMatrix;
import static locationallocation.Utils.Writer.writeLines;
import java.io.IOException;


/**
 * Algorithms are accessed through this. Holds application logic.
 */

public class Pmedian {

    /**
     * Chosen algorithm.
     */
    private Solver algorithm;
    /**
     * Demand locations.
     */
    private Location[] demandLocations;
    /**
     * Possible locations.
     */
    private Location[] possibleLocations;
    /**
     * Cost matrix.
     */
    private CostMatrix costs;
    /**
     * P (=number of facilities to choose). 
     */
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
     * Set P (=number of facilities to choose).
     * @param inputP
     */
    public void setP(final int inputP) {
        this.p = inputP;
    }
    /** 
     * Getter for P (=number of facilities to choose).
     * @return int p
     */
    public int getP() {
        return this.p;
    }

    /** 
     * Load a file as demand locations.
     * @param path
     */
    public void loadDemandlocations(final String path) {
        this.demandLocations = this.loadLocations(path);
        
    }
    /**
     * Getter for number of demand locations.
     * @return int Number of loaded demand locations.
     */
    public int getNumberOfDemandLocations() {
        return this.demandLocations.length;
    }
    
    /** 
     * Load a file as possible facility locations.
     * @param path
     */
    public void loadPossiblelocations(final String path) {
        this.possibleLocations = this.loadLocations(path);
    }
    /**
     * Getter for number of possible facility locations.
     * @return int Number of loaded possible locations.
     */
    public int getNumberOfPossibleLocations() {
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

    /**
     * Calculate cost matrix.
     */
    public void calculateCostMatrix() {

        this.costs = new CostMatrix(this.possibleLocations, this.demandLocations);
        
    }
    /**
     * Use naive solution to solve.
     */
    public void setSolverToNaive() {
        this.algorithm = new Naive();
    }
    /**
     * Use GRIA to solve.
     */
    public void setSolverToGRIA() {
        this.algorithm = new GRIA();
        this.algorithm.setPossibleLocations(this.possibleLocations);
    }
    /**
     * Use TeitzBart to solve.
     */
    public void setSolverToTeitzBart() {
        this.algorithm = new TeitzBart();
    }
    /**
     * Choose solver by name.
     * @param chosenAlgorithm Algorithm. One of three: Naive, TeitzBart, GRIA.
     */
    public void setSolver(final String chosenAlgorithm) {
        if (chosenAlgorithm == "Naive") {
            this.setSolverToNaive();
        }
        if (chosenAlgorithm == "GRIA") {
            this.setSolverToGRIA();
        }
        if (chosenAlgorithm == "TeitzBart") {
            this.setSolverToTeitzBart();
        }
    }


    /**
     * Solve the problem specified.
     */
    public void solve() {
        this.algorithm.solveWithParams(this.costs, this.p);

    }

    /**
     * Get cost of solution.
     * @return cost of solution.
     */
    public double getResultCost() {
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
     * @param filePathInput Path to write results to.
     */
    public void writeAllocationsToFile(final String filePathInput) {

        int[] allocations = this.getResultAllocations();
        String[] lines = new String[allocations.length + 1];

        // Header
        lines[0] = "demandlocation;facility\n";
        
        for (int demandLocationIdx = 1; demandLocationIdx < lines.length; demandLocationIdx++) {
            String line = demandLocationIdx + ";" + allocations[demandLocationIdx - 1] + "\n";

            lines[demandLocationIdx] = line;
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
     * Check existance of cost matrix.
     * @return True/false if cost matrix is loaded.
     */
    public boolean costMatrixExists() {
        return this.costs != null;
    }

    /**
     * Getter for algorithms.
     * @return Algorithms as strings;
     */
    public String[] getAlgorithms() {
        return this.algorithms;
    }

}