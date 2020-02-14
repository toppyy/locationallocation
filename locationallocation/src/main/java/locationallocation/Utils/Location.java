package locationallocation.Utils;

/**
* A data structure to store a "location" which is a coordinate pair.
*/

public class Location {

    /**
     * Coordinates of location.
     */
    private final double x, y;

    /**
     * Creates a Location.
     * @param inputX X-coordinate.
     * @param inputY Y-coordinate.
     */
    public Location(final double inputX, final double inputY) {
        this.x = inputX;
        this.y = inputY;
    }


     

    
    public final double[] getCoordinates()  {

        double[] rtrn = {this.x, this.y};
        return rtrn;
    }

    public final double getX() {
        return this.x;
    }
    public final double getY() {
        return this.y;
    }


}