
package locationallocation;

import locationallocation.Utils.ArrayList;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TestArrayList {

    private ArrayList arr;

    @Before
    public void setUp() {

        this.arr = new ArrayList();
       
    }

    @Test public void arrayListInit() {


        assertEquals("Size is not zero after init", 0, this.arr.size());
    }

    @Test public void arrayAddWorks() {


        for (int i = 0; i < 15; i++) {
            this.arr.add((i*3) + "");
        }

        assertEquals("Size incorrect after additions", 15, arr.size());

        assertEquals("Value should be equal to 9 * 3 ","27", arr.get(9)); 

        
    }

    @Test public void arrayGetWorks() {


        
        this.arr.add("A string");
        this.arr.add("Another string");
        this.arr.add("Third string");

        String[] expected = { "A string", "Another string", "Third string" };
        

        assertArrayEquals("Size incorrect after additions", expected, this.arr.getArray());
    }

    @Test public void arrayLargeAdd() {


        int largeAdd = 100000; // Larger than ArrayList private attribute increment.

        for (int i = 0; i < largeAdd; i++) {
            this.arr.add(i+"");
        }
        assertEquals("Size incorrect after additions", largeAdd, this.arr.size());
    }

}

