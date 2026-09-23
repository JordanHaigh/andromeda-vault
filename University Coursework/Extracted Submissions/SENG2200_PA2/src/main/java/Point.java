/**
 * Point class accommodates a single coordinate for the polygon
 * Uses two doubles (x and y) for determining the axes
 * Has appropriate getters (Not setters - data would be mutable if setters were implemented), and general query methods
 */
public class Point
{
    // Const threshold value for equating points
    private static final double EQUALITY_THRESHOLD = 0.000005;

    private final double x;
    private final double y;

    /**
     * public Point()
     * Default Constructor
     * Sets x and y variables to 0,0
     */
    public Point() {this(0,0);}

    /**
     * public Point(double x, double y)
     * Overloaded Constructor
     * @param x - Sets the private variable x to the parameter
     * @param y - Sets the private variable y to the parameter
     */
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * public Point(Point p)
     * Copy Constructor
     * @param p - Sets the private variables x and y to the paramters x and y values
     */
    public Point(Point p)
    {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * public double getX()
     * @return - Private variable x
     */
    public double getX() { return x; }

    /**
     * public double getY()
     * @return - Private variable y
     */
    public double getY() { return y; }

    /**
     * public double calculateDistanceFromOrigin()
     * Utilises refactored calculateDIstanceFromPoint with 0,0 as the parameters
     * @return - Euclidean distance of the x and y private variables to the origin
     */
    public double calculateDistanceFromOrigin() {return calculateDistanceFromPoint(0,0);}

    /**
     * public double calculateDistanceFromPoint(Point otherPoint)
     * Override method that uses the parameter Point otherPoint's x and coordinates
     * @param otherPoint - Point object that uses its getX and getY methods for the overridden method
     * @return - Euclidean distance of otherPoint to the private x and y variables
     */
    public double calculateDistanceFromPoint(Point otherPoint)
    {
        return calculateDistanceFromPoint(otherPoint.getX(), otherPoint.getY());
    }

    /**
     * public double calculateDistanceFromPoint(double x, double y)
     * Calculates the euclidean distance from the parameter x and y coordinates to the private x and y coordinates
     * @param x - Double x Coordinate value
     * @param y - Double y Coordinate value
     * @return - Euclidean distance from parameters to the private variables
     */
    public double calculateDistanceFromPoint(double x, double y)
    {
        double powX = Math.pow(this.x - x, 2);
        double powY = Math.pow(this.y - y, 2);
        return Math.sqrt(powX + powY);
    }

    /**
     * public String toString()
     * Overridden method to accommodate the assignment specification of printing a single coordinate "(x,y)"
     * @return - String formatted with the %4.2f specification
     */
    @Override
    public String toString()
    {
        return String.format("(%4.2f,%4.2f)", x,y);
    }

    /**
     * public boolean equals(Object o)
     * Modified from Auto Generated IntelliJ Method
     * Determines whether two points are equal
     * @param o - Type Object that can be type cast to any data type
     * @return - Boolean value if equal
     */
    @Override //Modified from Auto Generated IntelliJ Method
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Point point = (Point) o;

        // When testing the semicircle extremity point code,
        // the rotated point was meant to be 0,
        // but was actually
        // 2.4492935982947064E-16
        // ( 0.00000000000000024492935982947064 )
        // Place threshold on value to make the numbers seem equal when they are "close enough"

        if (Math.abs(point.x - this.x) > EQUALITY_THRESHOLD)
        //if (Double.compare(point.x, x) != 0)
            return false;

        //boolean equal = Double.compare(point.y, y) == 0;
        boolean equal = (Math.abs(point.y - this.y) <= EQUALITY_THRESHOLD);
        return equal;

    }

    /**
     * public int hashCode()
     * Auto generated from IntelliJ
     * Returns a hash code value for the object. Used for hash tables (Though not used in this assignment)
     * @return - Hash code value for object
     */
    @Override //Auto Generated from IntelliJ
    public int hashCode()
    {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
