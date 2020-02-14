
package locationallocation;

import locationallocation.Utils.ArrayList;;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestArrayList {

    @Test public void arrayListWorks() {


                
        ArrayList arr = new ArrayList();

        assertEquals("Size is not zero after init", 0, arr.size());
        

        for (int i = 0; i < 15; i++) {
            arr.add((i*3) + "");
        }

        assertEquals("Size is not 15 after additions", 15, arr.size());


        assertEquals("Value should be equal to 9 * 3 ","27", arr.get(9)); 

        
    }

}

