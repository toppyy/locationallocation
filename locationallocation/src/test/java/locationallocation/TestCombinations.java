package locationallocation;

import static locationallocation.Utils.Combinations.createCombinations;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCombinations{

    @Test public void invalidInput() {

        int[] testArray = { 1,2,3 };
        int[][] actual = createCombinations(testArray,4);       

        int [][] expected = new int[1][0];
        expected[0] = testArray;

        assertArrayEquals("K larger than M, original array returned",expected, actual);

    }

    @Test public void smallCombinations() {

        int[] testArray = { 1,2,3 };
        int[][] actual = createCombinations(testArray,2);       

        int [][] expected = {
             { 0, 1 }
            ,{ 0, 2 }
            ,{ 1, 2 }
        };

        assertArrayEquals("Small K-combinations of M not as expected",expected, actual);
    }

    @Test public void largerCombinations() {

        int[] testArray = { 1,4,3,99,45,6 };
        int[][] actual = createCombinations(testArray,4);       

        int [][] expected = {
            { 1,4,3,99 },  { 1,4,3,45 },  { 1,4,3,6 },   { 1,4,99,45 },
            { 1,4,99,6 },  { 1,4,45,6 },  { 1,3,99,45 }, { 1,3,99,6 }, 
            { 1,3,45,6 },  { 1,99,45,6 }, { 4,3,99,45 }, { 4,3,99,6 }, 
            { 4,3,45,6 },  { 4,99,45,6 }, { 3,99,45,6 }
        };

        // Combinations returns indexes, not values. Test against values.
        int[][] actualValues = actual.clone();
        for (int a = 0; a < actual.length; a++) {
            for (int b = 0; b < actual[0].length; b++) {
                actualValues[a][b] = testArray[ actual[a][b] ];
            }
        }

        assertArrayEquals("Larger K-combinations of M not as expected",expected, actualValues);
    }

    @Test public void largeCombinations() {

        int[] testArray = { 1,4,3,99,45,6,66,55,34,32,11,344,987,23,2,123,124,125,126 };
        int[][] actual = createCombinations(testArray,9);
        

        int expected = 92378;
        assertEquals("Largeish number of combinations correct", expected, actual.length);


    }
}




 