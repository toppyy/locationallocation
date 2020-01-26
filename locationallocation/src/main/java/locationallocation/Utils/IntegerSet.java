/**
 * Data structure set
 */

 package locationallocation.Utils;

 public class IntegerSet {

    private int[] integers; 
    public int setSize;

    public IntegerSet(int[] initialObjects) {
        this.integers = initialObjects;
        this.setSize = initialObjects.length;
    } 

    public boolean setChange(int toReplace, int replaceByThis) {
        boolean changed = false;
        for (int i = 0; i < this.setSize; i++) {
            if (this.integers[i]==toReplace) {
                this.integers[i] = replaceByThis;
                changed = true;
            }
        }
        return changed;
    }

    public void add(int toAdd) {

        this.setSize = this.setSize+1;

        int[] replacement = new int[this.setSize];

        for (int i = 0; i < (this.setSize-1); i++) {
            replacement[i] = this.integers[i];
        }
        replacement[(this.setSize-1)] = toAdd;
        this.integers = replacement;

    }



    public int pop() {


        int rtrn = this.integers[this.setSize-1];

        this.setSize = this.setSize-1;
        int[] replacement = new int[this.setSize];
        for (int i = 0; i < this.setSize; i++) {
            replacement[i] = this.integers[i];
        }
        this.integers = replacement;

        return rtrn;
    }

    public int[] getIntegerSet() {
        return this.integers;
    }

    public boolean isEmpty() {
        boolean rtrn = false;
        if (this.setSize==0) {
            return true;
        }
        return rtrn;
    }
 }