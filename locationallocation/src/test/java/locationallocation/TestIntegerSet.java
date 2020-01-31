package locationallocation;

import locationallocation.Utils.IntegerSet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestIntegerSet {
    @Test public void integerSetWorksAsExpected() {
        
        int[] testSet = { 11, 22, 33, 44, 55, 66, 77, 88 };
        
        IntegerSet set = new IntegerSet(testSet);

        assertEquals("setSize is incorrect after init ", testSet.length, set.getSetSize());
        set.add(99);
        assertEquals("setSize is incorrect after add ", testSet.length + 1, set.getSetSize());
        assertEquals("inSet incorrect", true, set.inSet(99));
        assertEquals("inSet incorrect", false, set.inSet(999));
        set.remove(11);
        assertEquals("inSet incorrect", false, set.inSet(11));
        assertEquals("setSize is incorrect after remove ", testSet.length, set.getSetSize());
        assertEquals("getIntegerByIndex incorrect after remove ", 22, set.getIntegerByIndex(0));
        set.removeByIndex(2);
        assertEquals("removeByIndex incorrect", testSet.length - 1 , set.getSetSize());
    }
}