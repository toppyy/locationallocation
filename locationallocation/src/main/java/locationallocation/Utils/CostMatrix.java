/*
*   Creates a cost matrix
*/


package locationallocation.Utils;

public final class CostMatrix {

    /**
     * Holds the matrix.
     */
    private double[][] costs;

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
            
                dist[idxA][idxB]  = this.calculateEuclideanDistance(setA[idxA], setB[idxB]);

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
        
        double rtrn = 0, minTmp, tmp;

        
        for (int col = 0; col < this.costs[0].length; col++) {
        
            minTmp = Double.MAX_VALUE;

            for (int row : rowset) {

                tmp = this.costs[row][col];
                if (tmp < minTmp) {
                    minTmp = tmp;
                }
            }
            rtrn += minTmp;
            
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