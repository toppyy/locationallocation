/*
*   Returns p-sized combinatios of a given array of Locations
*/


package locationallocation.Utils;

public class Combinations {
   

    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }


    public static int[][] createCombinations(int[] arr, int k) {

        int pit = arr.length;

        int komboja = factorial( pit ) / (  factorial( pit - k ) * factorial(k)) ;

        int[][] kombot      = new int[komboja][];
        int[] Kt            = new int[k]; 
        for (int i = 0; i < k; i++) {
            Kt[i] = i+1;
        }


       
        int komboCounter = 0, i;
        boolean paivitetty;
        
        while (komboCounter < komboja) {
            
            kombot[komboCounter] = Kt.clone();
            komboCounter = komboCounter + 1;

            if (komboCounter == komboja) {
                break;
            }

            
            if (Kt[k-1] == pit) {
                
                paivitetty = false;
                i = k-2;
                
                while(!paivitetty) {
                
                    if ( Kt[i+1] - Kt[i] > 1 ) {
                        
                        Kt[i] = Kt[i] + 1;
                    
                        while( i < (k-1) ) {
                            i = i +1;
                            Kt[i] = Kt[i-1]+1;
                        }
                        paivitetty=true;
                        
                    }
                    
                    i = i - 1;
                    
                }
                
            } else {
                
                Kt[k-1] = Kt[k-1] + 1;
                
            }
        }
            
        
        return kombot;
    }
}