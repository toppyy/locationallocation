/*
*   Returns k-sized combinations of a given array
*/


package locationallocation.Utils;

public class Combinations {
   

    
    /**
     * Factorial 
     * @param n
     * @return int
     */
    private static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }


    
    /** 
     * Creates an array of arrays holding all k combinations of "arr"
     * @param arr: array of items 
     * @param k: size of combination
     * @return int[][]: array of arrays holding indexes for combination. Not values of arr.
     */
    public static int[][] createCombinations(int[] arr, int k) {

        int pit = arr.length;

        int numberOfCombinations = factorial( pit ) / (  factorial( pit - k ) * factorial(k)) ;

        int[][] combinations      = new int[numberOfCombinations][];
        int[] Kt            = new int[k]; 
        for (int i = 0; i < k; i++) {
            Kt[i] = i+1;
        }


       
        int komboCounter = 0, i;
        boolean updated;
        
        while (komboCounter < numberOfCombinations) {
            
            combinations[komboCounter] = Kt.clone();
            komboCounter = komboCounter + 1;

            if (komboCounter == numberOfCombinations) {
                break;
            }

            
            if (Kt[k-1] == pit) {
                
                updated = false;
                i = k-2;
                
                while(!updated) {
                
                    if ( Kt[i+1] - Kt[i] > 1 ) {
                        
                        Kt[i] = Kt[i] + 1;
                    
                        while( i < (k-1) ) {
                            i = i +1;
                            Kt[i] = Kt[i-1]+1;
                        }
                        updated=true;
                        
                    }
                    
                    i = i - 1;
                    
                }
                
            } else {
                
                Kt[k-1] = Kt[k-1] + 1;
                
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