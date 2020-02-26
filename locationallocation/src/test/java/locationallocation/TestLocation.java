package locationallocation;

import locationallocation.Utils.Location;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestLocation {
    @Test public void locationReturnsCoordinates() {
        
        double[] coords = { 1.2, 9.7 };
        
        Location testLocation = new Location("ID", coords[0],coords[1]);

       

        assertArrayEquals("Coordinate getter does not work", coords , testLocation.getCoordinates() , 0.0001 );
        assertEquals("X-getter does not work",coords[0] , testLocation.getX() , 0.0001 );
        assertEquals("Y-getter does not work",coords[1] , testLocation.getY() , 0.0001 );
        
    }
}