/*
*   Returns p-sized combinatios of a given array of Locations
*/


package locationallocation.Utils;

public class Combinations {

    private int P;
    private Location[] arr;
    public int numberOfCombinations, comboCounter;
    public Location[][] combinations;
    

    public int factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }

    public Combinations(int P,Location[] arr) {
        this.arr = arr;
        this.P   = P;

        int N = arr.length;

        this.numberOfCombinations = factorial( N ) / (  factorial( N - P ) * factorial(P)) ;

        this.combinations = new Location[this.numberOfCombinations][];
        this.comboCounter = 0;

    }

    public int[][] createCombinations(int[] arr, int P) {

        int N = arr.length;
        int numberOfCombinations = factorial( N ) / (  factorial( N - P ) * factorial(P)) ;

        int[][] rtrn = new int[numberOfCombinations][P];
        int[] combo = new int[P];
        

        int toKeepIndex = P-2;
        int combinations = 0;
        while (combinations < numberOfCombinations) {
            
            for (int i= 0; i<toKeepIndex; i++) {
                combo[i] = arr[i];
            }
            for (int i = (toKeepIndex-1); i < N; i++) {
                combo[(toKeepIndex+1)] = i;
                rtrn[combinations] = combo.clone();
                combinations  = combinations + 1;
            }

            toKeepIndex = toKeepIndex - 1;
            

            
            
        }
        
        return rtrn;
    }
}