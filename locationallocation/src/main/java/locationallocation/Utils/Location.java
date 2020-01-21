package locationallocation.Utils;

public class Location {

    private double x, y;


    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] getCoordinates()  {

        double[] rtrn = { this.x,this.y };
        return rtrn;
    }

    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }


}