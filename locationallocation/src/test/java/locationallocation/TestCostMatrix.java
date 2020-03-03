package locationallocation;

import locationallocation.Utils.CostMatrix;
import locationallocation.Utils.Location;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestCostMatrix {

    private Location[] testLocations1, testLocations2;
    private CostMatrix costs;

    private double[][] answer;


    @Before
    public void setUp() {

        this.testLocations1 = new Location[]{ new Location("ID", 1,1), new Location("ID", 5,9) , new Location("ID", 2,4),  new Location("ID", 11,3) };
        this.testLocations2 = new Location[]{ new Location("ID", 7,11), new Location("ID", 2,8) , new Location("ID", 7,3) };

        // Calculate cost matrix
        this.costs = new CostMatrix(testLocations1, testLocations2);


        this.answer = new double[][]{
            { 11.6619037896906, 7.07106781186548    ,6.32455532033676 },
            { 2.82842712474619, 3.16227766016838    ,6.32455532033676 },
            { 8.60232526704263, 4                   ,5.09901951359278 },
            { 8.94427190999916, 10.295630140987     ,4  }
         };
        
    }



    @Test public void costMatrixIsCorrect() {
        
        double[][] costsM = this.costs.getCosts();

        

        int[] correctDimensions = { this.testLocations1.length, this.testLocations2.length };
        int[] matrixDimensions =  { costsM.length , costsM[0].length   };

        assertArrayEquals("Distance matrix dimensions are incorrect", correctDimensions , matrixDimensions );
        
        for (int row = 0;row < this.answer.length; row++) {
            assertArrayEquals("Distance matrix incorrect on row " + row, this.answer[row] , costsM[row] , 0.0001 );
        }
        
    }

    @Test public void costMatrixRowSums() {

        int[] rowset = { 0, 3 };
        double actual = this.costs.costSumForRowset(rowset);

        double expected = 0, columnminumum, value;

        for (int col = 0; col < this.testLocations2.length; col++) {
            
            columnminumum = Double.MAX_VALUE;

            for (int row : rowset) {
            
                value = this.answer[row][col];
                if (value < columnminumum) {
                    columnminumum = value;
                }
            }
            
            expected += columnminumum;
        }

        assertEquals("Cost of rowset minumums is correct", expected, actual, 0.00001);

    }
}