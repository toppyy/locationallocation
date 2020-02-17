

package locationallocation.Utils;
import java.math.BigInteger;
/**
 *   Returns k-sized combinations of a given array.
 */


public final class Combinations {
   
    private Combinations() {
    }


     /**
     * Factorial of n.
     * @param n
     * @return int
     */
    public static BigInteger factorial(final BigInteger n) {
        BigInteger one = BigInteger.ONE;
        BigInteger i = n;
        BigInteger sum = n;

        while (i.compareTo(one) > 0) {
            i = i.subtract(one);
            sum = sum.multiply(i);
            
        }
        return sum;
    }
    /** 
     * Creates an array of arrays holding all k combinations of "arr".
     * @param arr array of items 
     * @param k size of combination
     * @return int[][]: array of arrays holding indexes for combination. Not values of arr.
     */
    public static int[][] createCombinations(final int[] arr, final int k) {

        int pit = arr.length;

        // Sanity check
        if (k > pit) {
            System.out.println("K greater than array size. Returning array.");
            int[][] tmp = new int[1][];
            tmp[0] = arr;
            return tmp;
        }

        BigInteger nAsBigInt = BigInteger.valueOf(pit);
        BigInteger kAsBigInt = BigInteger.valueOf(k);
        BigInteger factorialOfN = factorial(nAsBigInt);
        BigInteger factorialOfK = factorial(kAsBigInt);

        BigInteger numberOfCombinations = factorialOfN.divide(factorial(nAsBigInt.subtract(kAsBigInt)).multiply(factorialOfK));


        
        int[][] combinations = new int[numberOfCombinations.intValue()][]; 
        int[] kt = new int[k]; 
        for (int i = 0; i < k; i++) {
            kt[i] = i + 1;
        }
       
        int komboCounter = 0, i;
        boolean updated, notFinished = true;
        
        while (notFinished) {
            
            combinations[komboCounter] = kt.clone();
            komboCounter = komboCounter + 1;

            // Test if array last and first difference + 1 equals k
            // True means no other combinations exist
            if (kt[k - 1] == pit & (kt[k - 1] - kt[0] + 1)  == k) {
                notFinished = false;
                break;
            }

            
            if (kt[k - 1] == pit) {
                
                updated = false;
                i = k - 2;
                
                while (!updated) {
                
                    if (kt[i + 1] - kt[i] > 1) {
                        
                        kt[i] = kt[i] + 1;
                    
                        while (i < (k - 1)) {
                            i = i + 1;
                            kt[i] = kt[i - 1] + 1;
                        }
                        updated = true;
                        
                    }
                    
                    i = i - 1;
                    
                }
                
            } else {
                
                kt[k - 1] = kt[k - 1] + 1;
                
            }
        }

        // Create an array of the size that was actually needed
        int[][] combinationsToReturn = new int[komboCounter][]; 

        for (int a = 0; a < komboCounter; a++) {

            combinationsToReturn[a] = combinations[a].clone();

            for (int b = 0; b < combinations[0].length; b++) {

                // -1 for all elements in all combinations for indexing purposes.
                combinationsToReturn[a][b] =  combinations[a][b] - 1;
            
            }
        }
            
        
        return combinationsToReturn;
    }
}