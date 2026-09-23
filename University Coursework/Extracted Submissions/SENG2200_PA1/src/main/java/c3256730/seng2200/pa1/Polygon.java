package c3256730.seng2200.pa1;
import java.util.Arrays; //Auto imported by IntelliJ for the HashCode method. Not used anywhere else

/**
 * Polygon class implements the ComparePolygon interface, making sure that it includes methods inside the interface
 * Polygon uses a Point array for all the points that make up the polygon
 * The class uses methods to calculate the area of a polygon, find the euclidean distance from the origin, and
 * determine where a polygon comes before another
 */
public class Polygon implements ComparePolygon
{
    private Point[] points;
    private int sides;
    public final static int MINIMUM_POINTS_PER_POLYGON = 3;
    private int id;
    private static int index;


    /**
     * public Polygon(Points[] points)
     * Overloaded Constructor
     * @param points - Points[] will become the private variable points
     */
    public Polygon(Point[] points)
    {
        this.points = points;
        if(points.length > 3)
            sides = points.length-1;
        else
            sides = 0;

        this.id = index;
        index++;
    }

    public int getSides() { return this.sides; }

    /**
     * @Override
     * public String toString()
     * Returns String according to the assignment specification "[(x1,y1),...(xn,y,)]:area"
     * @return - String of polygon and its area
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(int i = 0; i < points.length; i++)
        {
            sb.append(points[i].toString());
            if(i != points.length-1)
                sb.append(" ");
        }

        sb.append("]: " + String.format("%5.2f", calculateArea()));
        String output = sb.toString();

        return output;
    }

    /**
     * public double calculateArea()
     * Uses the equation in the assignment specification to caluclate the area of the polygon
     * @return - Double result with area
     */
    public double calculateArea()
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
    public double calculateDistanceFromOrigin()
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
     * @Override
     * public boolean equals(Object o)
     * Auto Generated from IntelliJ
     * Determines whether two Polygon Objects are equal
     * @param o - Object o that will be type cast to a polygon object
     * @return - Boolean value whether equal
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Polygon polygon = (Polygon) o;

        if(this.points.length != polygon.points.length)
            return false;


        for(int i = 0; i < points.length; i++)
        {
            if(!this.points[i].equals(polygon.points[i]))
                return false;
        }
        return true;
    }

    /**
     * @Override
     * public int hashCode()
     * Auto generated from IntelliJ
     * Returns a hash code value for the object. Used for hash tables (Though not used in this assignment)
     * @return - Hash code value for object
     */
    @Override //Generates by IntelliJ
    public int hashCode() {
        int result = Arrays.hashCode(points);
        result = 31 * result + sides;
        return result;
    }

    /**
     * public boolean comesBefore(Object o)
     * Implemented Interface Method
     * Determines whether the euclidean distance of the a Polygon calling the method
     *  or Object parameter(Type cast to Polygon) is closest to origin
     * @param o - Type Object (Can be type cast to any other type)
     * @return - Returns boolean value if statement is true or not
     */
    public boolean comesBefore(Object o)
    {
        if(o == null)
            return false; //Or true

        if(!(o instanceof Polygon))
            throw new IllegalArgumentException("Parameter is not of type Polygon");
            //a.comesBefore(b)
        Polygon otherPolygon = (Polygon)o;

        double otherArea = otherPolygon.calculateArea();
        double otherDistanceToOrigin = otherPolygon.calculateDistanceFromOrigin();

        double maxRange = this.calculateArea() * 1.05;
        double minRange= this.calculateArea() * 0.95;


        if(minRange <= otherArea && otherArea <= maxRange)
        {
            //Areas are equal according to the specification
            //Now check which polygon is closest to the origin
            //Check equal distance
            if(otherDistanceToOrigin == this.calculateDistanceFromOrigin())
                return (this.id < otherPolygon.id); //Return the closer polygon

            return this.calculateDistanceFromOrigin() < otherDistanceToOrigin ;
        }

        else
            return this.calculateArea() < otherArea;
    }
}
