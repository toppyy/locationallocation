package locationallocation.Utils;

/**
* A data structure to store a "location" which is a coordinate pair.
*/

public class Location {

    private final String id;
    private final double x, y, w;

    /**
     * Creates a Location.
     * @param idInput Input for location id.
     * @param inputX X-coordinate.
     * @param inputY Y-coordinate.
     */
    public Location(final String idInput, final double inputX, final double inputY) {
        this.id = idInput;
        this.x = inputX;
        this.y = inputY;
        this.w = 1;
    }

    /**
     * Creates a Location.
     * @param idInput Input for location id.
     * @param inputX X-coordinate.
     * @param inputY Y-coordinate.
     * @param inputW Weight. Used in demand locations to weight distances.
     */
    public Location(final String idInput, final double inputX, final double inputY, final double inputW) {
        this.id = idInput;
        this.x = inputX;
        this.y = inputY;
        this.w = inputW;
    }
    
    public final String getId() {
        return this.id;
    }

    public final double getW() {
        return this.w;
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