

 package locationallocation.Utils;
 /**
 * Datastructure providing some of the behavior of Set and Arraylist Java-classes. For integers.
 */

 public class IntegerSet {

    /**
     * Holds the set of integers.
     */
    private int[] integers; 
    /**
     * Holds the set size for convenience.
     */
    private int setSize;

    public IntegerSet(final int[] initialObjects) {
        this.integers = initialObjects;
        this.setSize = initialObjects.length;
    }
    public IntegerSet(final IntegerSet initialObjects) {
        this.integers = initialObjects.getIntegers(); // Get as int[]
        this.setSize = this.integers.length;
    }
    public IntegerSet() {
        int[] empty = new int[0];
        this.integers = empty;
        this.setSize = this.integers.length;
    }

    /**
     * Get set size.
     * @return integer Size of set
     */
    public int getSetSize() {
        return this.setSize;
    }

    
    /** 
     * Replace an integer in set by another integer.
     * @param toReplace integer to be replaced
     * @param replaceByThis integer to replace by
     * @return boolean true if swap successfull.
     */
    public boolean setChange(final int toReplace, final int replaceByThis) {
       

        boolean changed = false;
        for (int i = 0; i < this.setSize; i++) {
            if (this.integers[i] == toReplace) {
                this.integers[i] = replaceByThis;
                changed = true;
            }
        }
        return changed;
    }

    
    /** 
     * Adds a integer to set.
     * Does not check if is already in set.
     * @param toAdd integer to add
     */
    public void add(final int toAdd) {

        this.setSize = this.setSize + 1;

        int[] replacement = new int[this.setSize];

        for (int i = 0; i < (this.setSize - 1); i++) {
            replacement[i] = this.integers[i];
        }
        replacement[(this.setSize - 1)] = toAdd;
        this.integers = replacement;

    }

    
    /** 
     * Removes an integer from set.
     * @param toRemove integer to remove
     * @return Boolean. True if element was removed.
     */
    public boolean remove(final int toRemove) {

        int[] replacement = new int[this.setSize - 1];

        int addedInt = 0;
        
        for (int i = 0; i < this.setSize; i++) {
            
            
            if (addedInt == this.setSize - 1 & this.integers[i]  !=  toRemove) {
                return false;
            }
            
            
        
            if (this.integers[i]  !=  toRemove) {
                replacement[addedInt] = this.integers[i];
                addedInt++;
            }
        
        }
        

        this.setSize = this.setSize - 1;
        this.integers = replacement;

        return true;
        
    }
    
    /** 
     * Remove integer by index.
     * @param idx index of integer to be removed
     * @return True if element was removed.
     */
    public boolean removeByIndex(final int idx) {


        // Sanity check
        
        if (idx > (this.setSize - 1) | idx < 0) {
            return false;
        }
        

        int[] replacement = new int[this.setSize - 1];

        int addedInt = 0;
        
        for (int i = 0; i < this.setSize; i++) {

            
        
            if (i != idx) {
                replacement[addedInt] = this.integers[i];
                addedInt++;
            }
            
        
        }
        
        

        this.setSize = this.setSize - 1;
        this.integers = replacement;
        
        return true;
    }



    
    /** 
     * Return and remove the last element of set.
     * Last element equals latest addition
     * @return int Popped integer
     */
    public int pop() {


        int rtrn = this.integers[this.setSize - 1];

        this.setSize = this.setSize - 1;
        int[] replacement = new int[this.setSize];
        for (int i = 0; i < this.setSize; i++) {
            replacement[i] = this.integers[i];
        }
        this.integers = replacement;

        return rtrn;
    }

    
    /** 
     * Returns the set as an array.
     * @return int[] set as an array of integers
     */
    public int[] getIntegers() {
        return this.integers.clone();
    }

    
    /** 
     * Get integer by index.
     * @param toGet index of integer wanted
     * @return int integer at asked position
     */
    public int getIntegerByIndex(final int toGet) {
        return this.integers[toGet];
    }

    
    /** 
     * Check if set is empty.
     * @return boolean true if set size is zero
     */
    public boolean isEmpty() {
        boolean rtrn = false;
        if (this.setSize == 0) {
            return true;
        }
        return rtrn;
    }


    
    /** 
     * Print set preceded by a message.
     * @param message message to be printed before the set
     */
    
    public void print(final String message) {
        System.out.println("\n " + message + " ");
        for (int i : this.integers) {
            System.out.print(i + ", ");
        }
        System.out.println("\n");
    }
    
 }