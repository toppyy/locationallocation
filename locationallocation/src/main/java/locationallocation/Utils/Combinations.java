/*
*   Returns k-sized combinations of a given array
*/


package locationallocation.Utils;

public final class Combinations {
   
    private Combinations() {
    }

    
    /**
     * Factorial of n.
     * @param n
     * @return int
     */
    private static long factorial(final long n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }


    
    /** 
     * Creates an array of arrays holding all k combinations of "arr".
     * @param arr array of items 
     * @param k size of combination
     * @return int[][]: array of arrays holding indexes for combination. Not values of arr.
     */
    public static int[][] createCombinations(final int[] arr, final int k) {

        int pit = arr.length;

        long numberOfCombinations = factorial(pit) / (factorial(pit - k) * factorial(k));
        

        int[][] combinations = new int[(int) numberOfCombinations][]; 
        int[] kt = new int[k]; 
        for (int i = 0; i < k; i++) {
            kt[i] = i + 1;
        }


       
        int komboCounter = 0, i;
        boolean updated;
        
        while (komboCounter < numberOfCombinations) {
            
            combinations[komboCounter] = kt.clone();
            komboCounter = komboCounter + 1;

            if (komboCounter == numberOfCombinations) {
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

        // Fix this, but: -1 for all elements in all combinations
        for (int a = 0; a < combinations.length; a++) {
            for (int b = 0; b < combinations[0].length; b++) {
                combinations[a][b] =  combinations[a][b] - 1;
            
            }
        }
            
        
        return combinations;
    }
}