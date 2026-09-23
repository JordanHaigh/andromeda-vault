/**
 * Circle class extends the abstract Planarshape class, implementing methods for area(), originDistance() and toString()
 * As well as the equals and hashCode methods (Generated from IntelliJ).
 * Class calculates distance from origin to the centre of a circle and distance from origin to the outer edge
 */
public class Circle extends PlanarShape
{
    private Point centre;
    private double radius;

    /**
     * Overloaded Constructor
     * @param centre - Centre Point of the circle
     * @param radius - Radius of the circle
     */
    public Circle(Point centre, double radius)
    {
        super(); //Call Parent constructor
        this.centre = centre;
        this.radius = radius;
    }

    /**
     * public double getRadius()
     * @return - Radius of circle
     */
    public double getRadius(){ return radius;}

    /**
     * public Point getCentre
     * @return - Centre point of circle
     */
    public Point getCentre(){ return centre;}

    /**
     * public void setRadius(double radius)
     * @param radius - Radius of the circle
     */
    public void setRadius(double radius) { this.radius = radius;}

    /**
     * public void setCentre(Point centre)
     * @param centre - Centre point of circle
     */
    public void setCentre(Point centre) {this.centre = centre;}


    /**
     * public String toString()
     * Overloaded toString() method from parent class as per the assignment specification
     * @return - String of circle centre point and radius
     */
    @Override
    public String toString()
    {
        return "CIRC=[" + centre.toString() + " " + radius + "]: " + String.format("%5.2f", area());
    }

    /**
     * public double area()
     * Overloaded area() method from parent class as per the assignment specification
     * @return - Area of the circle
     */
    @Override
    public double area()
    {
        return Math.PI * radius * radius;
    }

    /**
     * public double originDistance()
     * Overloaded originDistance() method from parent class as per the assignment specification
     * @return - Origin distance of circle to outer edge
     */
    @Override
    public double originDistance()
    {
        return originDistanceToOuterEdge();
    }

    /**
     * public double originDistanceFromCentre()
     * Calculates the origin distance from the origin (Uses point class for calculation)
     * @return - Euclidean distance of origin to centre
     */
    public double originDistanceFromCentre()
    {
        return centre.calculateDistanceFromOrigin();
    }

    /**
     * public double originDistanceToOuterEdge()
     * Calculates the origin distance from the origin to the outer edge of the circle
     * @return - Absolute value of Euclidean distance from origin to outer edge
     */
    public double originDistanceToOuterEdge()
    {
        return centre.calculateDistanceFromOrigin() - radius;
    }

    /**
     * public boolean equals(Object o)
     * Generated from IntelliJ
     * Overridden method that determines whether two circles have the same radius and centre point
     * @param o - Second Circle Object
     * @return - Boolean value if equal
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Circle circle = (Circle) o;

        if (Double.compare(circle.radius, radius) != 0) return false;
        return centre.equals(circle.centre);
    }

    /**
     * public int hashCode()
     * Auto generated from IntelliJ
     * Returns a hash code value for the object. Used for hash tables (Though not used in this assignment)
     * @return - Hash code value for object
     */
    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        long temp;
        result = 31 * result + centre.hashCode();
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
