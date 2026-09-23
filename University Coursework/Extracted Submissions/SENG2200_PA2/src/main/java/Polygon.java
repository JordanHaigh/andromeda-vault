import java.util.Arrays; //Used for Hashcode

/**
 * Polygon class extends the abstract PlanarShape class, implementing methods for area(), originDistance() and toString()
 * As well as the equals and hashCode methods (Generated from IntelliJ).
 */
public class Polygon extends PlanarShape
{
    private Point[] points;
    private int sides;
    public final static int MINIMUM_POINTS_PER_POLYGON = 3;

    /**
     * public Polygon(Points[] points)
     * Overloaded Constructor
     * @param points - Points[] will become the private variable points
     */
    public Polygon(Point[] points)
    {
        // call PlanarShape constructor to set id field
        super();

        this.points = points;
        if(points.length > 3)
            sides = points.length-1;
        else
            sides = 0;
    }

    /**
     * public int getSides()
     * @return - Returns number of sides
     */
    public int getSides() { return this.sides; }

    /**
     * public double area()
     * Uses the equation in the assignment specification to calculate the area of the polygon
     * @return - Double result with area
     */
    @Override
    public double area()
    {
        double xHalf = 0;
        double yHalf = 0;
        double sum = 0;

        if(points.length < MINIMUM_POINTS_PER_POLYGON)
            return 0.0; //No Area in a straight line or point

        for(int i = 0; i < points.length -1; i++)
        {
            xHalf = points[i+1].getX() + points[i].getX();
            yHalf = points[i+1].getY() - points[i].getY();
            sum += xHalf * yHalf;

        }

        return 0.5 * Math.abs(sum);
    }

    /**
     * public double calculateDistanceFromOrigin()
     * Iterates through each point in the Polygon object and determines the euclidean distance for each point
     * Finds the smallest euclidean distance from the origin and returns that number
     * @return - Double result of closest euclidean
     */
    @Override
    public double originDistance()
    {
        //Find closest point to the origin
        //Instantiate a variable to track the closest point. Start at max value
        double closestPoint = Double.MAX_VALUE;
        for (Point point : points)
        {
            double euclideanDistance = point.calculateDistanceFromOrigin();
            if (euclideanDistance < closestPoint)
                closestPoint = euclideanDistance;
        }
        //Any value will now replace the max value and will be the closest point to the origin
        return closestPoint;
    }

    /**
     * public String toString()
     * Overloaded toString() method from parent class as per the assignment specification
     * @return - String of circle centre point and radius
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("POLY=[");

        for(int i = 0; i < points.length; i++)
        {
            sb.append(points[i].toString());
            if(i != points.length-1)
                sb.append(" ");
        }

        sb.append("]: " + String.format("%5.2f", area()));
        String output = sb.toString();

        return output;
    }

    /**
     * public boolean equals(Object obj)
     * Generated from IntelliJ
     * Overridden method that determines whether two polygons have the same points
     * @param obj - Second Circle Object
     * @return - Boolean value if equal
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        Polygon p = (Polygon) obj;

        if(this.points.length != p.points.length)
            return false;


        for(int i = 0; i < points.length; i++)
        {
            if(!this.points[i].equals(p.points[i]))
                return false;
        }
        return true;
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
        /*
         "You must override hashCode in every class that overrides equals. Failure to do so
          will result in a violation of the general contract for Object.hashCode, which will
          prevent your class from functioning properly in conjunction with all hash-based
          collections, including HashMap, HashSet, and Hashtable.
          http://www.ebooksbucket.com/uploads/itprogramming/java/Effective_Java_2nd_Edition.pdf - Page 45
        */
        int result = Arrays.hashCode(points);
        result = 31 * result + sides;
        return result;
    }
}
