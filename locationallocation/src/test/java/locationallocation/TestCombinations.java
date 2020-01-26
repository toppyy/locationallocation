package locationallocation;

import static locationallocation.Utils.Combinations.createCombinations;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCombinations{

    @Test public void allCombinations() {

        int[] testArray = { 1,2,3 };
        int[][] actual = createCombinations(testArray,2);       

        int [][] expected = {
             { 1, 2 }
            ,{ 1, 3 }
            ,{ 2, 3 }
        };

        assertArrayEquals("K-combinations of M not as expected",expected, actual);
    }
}




 