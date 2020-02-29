package locationallocation.Utils;
/**
 *   Creates a cost matrix.
 */


public final class CostMatrix {

    /**
     * Holds the matrix.
     */
    
    private double[][] costs;
    /**
     * Default constructor.
     */
    public CostMatrix() {

    }

    /**
     * Create a costmatrix of two sets of locations.
     * @param setA A set of locations
     * @param setB A set of locations
     */
    public CostMatrix(final Location[] setA, final Location[] setB) {

        
        double[][] dist = new double[setA.length][setB.length];

        for (int idxA = 0; idxA < setA.length; idxA++) {

            for (int idxB = 0; idxB < setB.length; idxB++) {
            
                dist[idxA][idxB]  = this.calculateEuclideanDistance(setA[idxA], setB[idxB]) * setA[idxA].getW() * setB[idxB].getW();

            }
        }

        this.costs = dist;

    }   


    /**
     * Calculates euclidean distance between two Locations.
     * @param a Location
     * @param b Another Location
     * @return Euclidean distance
    */

    private double calculateEuclideanDistance(final Location a, final Location b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    /** 
     * Returns the sum of costs for a given set of rows given as a IntegerSet.
     * For each row, the minumum cost is chosen.
     * 
     * @param rowset set of rows for which to find sum of minimums of costs.
     * @return double
     */
    public double costSumForRowset(final IntegerSet rowset) {
        return this.costSumForRowset(rowset.getIntegers());
    }

    /** 
     * Returns the sum of costs for a given set of rows.
     * For each row, the minumum cost is chosen.
     * 
     * @param rowset set of rows for which to find sum of minimums of costs.
     * @return double
     */
    public double costSumForRowset(final int[] rowset) {
        
        double rtrn = 0, minCost, cost;

        
        for (int col = 0; col < this.costs[0].length; col++) {
        
            minCost = Double.MAX_VALUE;

            for (int row : rowset) {

                cost = this.costs[row][col];
                if (cost < minCost) {
                    minCost = cost;
                }
            }
            rtrn += minCost;
            
        }

        return rtrn;
    }
    /**
     * Find the minumum cost for each column given a set of rows.
     * Purpose: Allocate demand locations (=columns in the matrix) to possible facility locations given 
     * a specific solution (= a set of row indices).
     * @param rowset Rows to find columns for.
     * @return For each column, the index of row it's allocated to.
     */
    public int[] allocateColumnsToRows(final int[] rowset) {

        double minCost, cost;
        int minRow = 0;
        int[] rtrn = new int[this.costs[0].length];

        
        for (int col = 0; col < this.costs[0].length; col++) {

            minCost = Double.MAX_VALUE;
            
            for (int row : rowset) {
                cost = this.costs[row][col];

                if (cost < minCost) {
                    minCost = cost;

                    minRow = row; 
                    
                }
            }
            rtrn[col] = minRow;
            
            
            
        }
        return rtrn;

    }


    /**
     * Getter for cost matrix.
     * @return cost matrix.
     */
    public double[][] getCosts() {
        return this.costs;
    }
    /**
     * Setter for cost matrix.
     * @param costsInput cost matrix.
     */
    public void setCosts(final double[][] costsInput) {
        this.costs = costsInput;
    }

    /**
     * Getter for row count.
     * @return Row count of matrix.
     */
    public int getRowCount() {
        return this.costs.length;
    }

}