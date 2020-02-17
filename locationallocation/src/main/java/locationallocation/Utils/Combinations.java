

package locationallocation.Utils;
/**
 *   Returns k-sized combinations of a given array.
 */


public final class Combinations {
   
    private Combinations() {
    }


    /**
     * Initial size of combinations array.
     * Instead of calculating the number of combinations and initialing the array with that, this constant is used.
     * Reason: Factorial of numbers greater than 20 do not fit into long. Factorials are needed to calculate
     * the binomial coffient (number of combinations).
     * 
     * Currently limits the number of combinations possible to create.
     */
    private static final int INITIAL_SIZE = 10000000;

    /** 
     * Creates an array of arrays holding all k combinations of "arr".
     * @param arr array of items 
     * @param k size of combination
     * @return int[][]: array of arrays holding indexes for combination. Not values of arr.
     */
    public static int[][] createCombinations(final int[] arr, final int k) {

        int pit = arr.length;

        // Sanity check
        if (k>pit) {
            System.out.println("K greater than array size. Returning array.");
            int[][] tmp = new int[1][];
            tmp[0] = arr;
            return tmp;
        }

        
        int[][] combinations = new int[INITIAL_SIZE][]; 
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