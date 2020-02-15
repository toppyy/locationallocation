package locationallocation;

import locationallocation.Utils.IntegerSet;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestIntegerSet {

    private IntegerSet set;
    private int[] testset;
    
    @Before
    public void setUp() {
        int[] init =  { 11, 22, 33, 44, 55, 66, 77, 88 };
        this.testset = init;
        this.set = new IntegerSet(this.testset);
    }


    @Test public void testSetSize() {
        
        assertEquals("setSize is incorrect after init ", this.testset.length, this.set.getSetSize());
        

    }
    @Test public void testAddRemove() {
        
        set.add(99);
        assertEquals("setSize is incorrect after add ", this.testset.length+1, this.set.getSetSize());

        assertEquals(true,set.remove(11));
        assertEquals("setSize is incorrect after remove ", this.testset.length, this.set.getSetSize());

        assertEquals(true,set.removeByIndex(2));
        assertEquals("removeByIndex incorrect", this.testset.length - 1 , this.set.getSetSize());

        assertEquals("remove nonexistant value removes something",false,set.remove(-1));
        assertEquals("try to remove with index out of bounds",false,set.removeByIndex(  this.set.getSetSize() + 1  ));
        assertEquals("try to remove with index out of bounds",false,set.removeByIndex(  -1  ));

    }
    @Test public void testGetByIndex() {

        assertEquals("getIntegerByIndex incorrect", 11, this.set.getIntegerByIndex(0));

        assertEquals("getIntegerByIndex incorrect", 88, this.set.getIntegerByIndex(this.testset.length-1));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidGetByIndex() {
        this.set.getIntegerByIndex( this.testset.length );
    }

    @Test public void testSetSizeAfterOperations() {

        set.removeByIndex(2);
        set.removeByIndex(2);
        assertEquals("setSize is incorrect after init ", this.testset.length-2, this.set.getSetSize());


        set.add(2);
        set.add(2);
        set.add(2);
        assertEquals("setSize is incorrect after init ", this.testset.length+1, this.set.getSetSize());
        

    }
        
        
       
    
}