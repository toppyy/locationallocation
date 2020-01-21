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

        this.numberOfCombinations = factorial( N ) / (  factorial( N - P ) * factorial(P)) + 10;

        this.combinations = new Location[this.numberOfCombinations][];
        this.comboCounter = 0;

    }

    public Location[][] createCombinations(Location[] arr) {

        if (arr.length == 2) {
            Location[][] tmp  =  { { arr[0], arr[1] }, { arr[1], arr[0] }};
            return tmp;
        }
        

        for ( int i = 0; i < arr.length; i++ ) {
        
            Location current = arr[i];
            Location[] arrayWithoutCurrent = new Location[(arr.length-1)];

            for ( int a = 0; a < arr.length; a++ ) {
                if (a != i) {
                    if (a>i) {
                        arrayWithoutCurrent[a-1] = arr[i];
                    } else {
                        arrayWithoutCurrent[a]   =  arr[i];
                    }
                }

            }
            Location[][] newcombo = createCombinations(arrayWithoutCurrent);
            System.out.println("newcombosize " + newcombo.length);
            for (Location a : newcombo[0]) {

            }
            
            for ( int a = 0; a < newcombo.length; a++ ) {
                Location[] forConcat = new Location[ newcombo[a].length + 1 ];
            
                int pos = 0;
                for (Location element : newcombo[a]) {
                    forConcat[pos] = element;
                    pos++;
                }
                forConcat[pos] = current;
                newcombo[a] = forConcat;
                
            }

            for (Location[] combo: newcombo) {
                System.out.println(comboCounter);
                this.combinations[comboCounter] = combo;
                comboCounter++;
            }
            //System.out.println("\nSize of current: " + arr.length +" and size of minus-1: " + arrayWithoutCurrent.length);

        }
        
        return this.combinations;
    }
}