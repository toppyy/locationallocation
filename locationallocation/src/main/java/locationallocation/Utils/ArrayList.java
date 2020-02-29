    

package locationallocation.Utils;

/**
 *  An evergrowing array (to replace ArrayList) for Strings.
 */

public class ArrayList {
    

    private String[] array;
    private int size;
    private int elementsInArray;

    /**
     * The number of elements to increment array when full/empty.
     */
    private int increment;

    /**
     * Creates an object imitating the behavior of Java's ArrayList.
     */

    public ArrayList() {
        
        this.increment = 10;
        this.array = new String[this.increment];
        this.size = this.array.length;
        this.elementsInArray = 0;

    }
    /**
     * Add to array.
     * @param toAdd Element to add 
     */
    public void add(final String toAdd) {

        if (elementsInArray < (this.size - 1)) {

            this.array[elementsInArray] = toAdd;
            elementsInArray++;
        } else {

            this.extendArray();

            this.array[elementsInArray] = toAdd;
            elementsInArray++;
        }

    }

    /**
     * Extends array size. Use when elementsInArray reaches size.
     */
    private void extendArray() {

        String[] largerArray = new String[this.size + this.increment];

        for (int i = 0; i < this.size; i++) {
            largerArray[i] = this.array[i];
        }

        this.size = largerArray.length;
        this.array = largerArray;
    }

    /**
     * Getter for array. Note that it returns only the portion of array that has elements.
     * @return Array.
     */
    public String[] getArray() {
        String[] rtrn = new String[this.elementsInArray];

        for (int i = 0; i < this.elementsInArray; i++) {
            rtrn[i] = this.array[i];
        }
        return rtrn;
    }
    /**
     * Getter for a single value.
     * @param idx Index to retrieve value from.
     * @return Array of strings.
     */
    public String get(final int idx) {
        return this.array[idx];
    }
    /**
     * Getter for size. 
     * @return Returns the number of elements stored in array (not array.length).
     */
    public int size() {
        return this.elementsInArray;
    }

}