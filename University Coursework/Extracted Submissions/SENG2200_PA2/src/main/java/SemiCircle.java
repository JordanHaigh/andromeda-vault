/**
 * SemiCircle class extends the abstract PlanarShape class, implementing methods for area(), originDistance() and toString()
 * As well as the equals and hashCode methods (Generated from IntelliJ).
 * Class utilises methods for calculating the vector of a semicircle and calculating the extremity points
 */
public class SemiCircle extends PlanarShape
{
    private Point basePoint;
    private Point perpendicularPoint;

    /**
     * Overloaded Constructor
     * @param basePoint - Base Point of Semi Circle
     * @param perpendicularPoint - Perpendicular point of Semi Circle
     */
    public SemiCircle(Point basePoint, Point perpendicularPoint)
    {
        super();
        this.basePoint = basePoint;
        this.perpendicularPoint = perpendicularPoint;
    }

    /**
     * public Point getBasePoint()
     * @return - Returns BasePoint
     */
    public Point getBasePoint() {return basePoint;}

    /**
     *  public Point getPerpendicularPoint()
     *  @return - Returns PerpendicularPoint
     */
    public Point getPerpendicularPoint() {return perpendicularPoint; }

    /**
     * public void setBasePoint(Point basePoint)
     * @param basePoint - BasePoint of SemiCircle
     */
    public void setBasePoint(Point basePoint) {this.basePoint = basePoint;}


    /**
     * public void setPerpendicular(Point perpendicularPoint)
     * @param perpendicularPoint - Perpendicular point of semicircle
     */
    public void setPerpendicularPoint(Point perpendicularPoint){this.perpendicularPoint = perpendicularPoint;}


    /**
     * private Point calculateVector()
     * Calculates the vector point between perpendicular point and base point
     * @return - Vector point
     */
    private Point calculateVector()
    {
        double x = perpendicularPoint.getX() - basePoint.getX();
        double y = perpendicularPoint.getY() - basePoint.getY();
        Point p = new Point(x,y);
        return p;
    }


    /**
     * public double calculateRadius()
     * Calculates the distance from the vector from the origin
     * @return - Double Euclidean result
     */
    public double calculateRadius()
    {
        Point vector = calculateVector();
        //Magnitude of vector
        return vector.calculateDistanceFromOrigin();
    }

    /**
     * public Point calculateExtremityPoint1()
     * Calculates the the first extremity point of a semicircle (Rotated by 90 degrees)
     * @return - Extremity point
     */
    public Point calculateExtremityPoint1()
    {
        return calculateExtremityPoint(90);
    }

    /**
     * public Point calculateExtremityPoint2()
     * Calculates the the second extremity point of a semicircle (Rotated by -90 degrees)
     * @return - Extremity point
     */
    public Point calculateExtremityPoint2()
    {
        return calculateExtremityPoint(-90);
    }

    /**
     * private Point calculateExtemityPoint(double degrees)
     * @param degrees - Degree value to rotate Point by.
     * @return
     */
    private Point calculateExtremityPoint(double degrees)
    {
        // Origin-relative vector from basepoint to perpendicular point
        Point vector = calculateVector();

        //Rotate by 90 degrees
        //Cos only works in radians - Need to convert to degrees for calculation to work
        double radians = degrees * (Math.PI/180);

        // Origin-relative rotated vector
        double x = (vector.getX() * Math.cos(radians)) - (vector.getY() * Math.sin(radians));
        double y = (vector.getX() * Math.sin(radians)) + (vector.getY() * Math.cos(radians));
        Point rotatedVec = new Point(x,y);

        // Move rotated vector so the tail is at the base point
        Point p = new Point(
            basePoint.getX() + rotatedVec.getX(),
            basePoint.getY() + rotatedVec.getY()
        );

        return p;
    }

    /**
     * public String toString()
     * Overloaded toString() method from parent class as per the assignment specification
     * @return - String of semicircle base point and perpendicular point
     */
    @Override
    public String toString()
    {
        return "SEMI=[" + basePoint.toString() + " " + perpendicularPoint+ "]: " + String.format("%5.2f", area());
    }

    /**
     * public double area()
     * Overloaded area() method from parent class as per the assignment specification
     * @return - Area of the semi circle
     */
    @Override
    public double area()
    {
        return Math.PI * calculateRadius() * calculateRadius()/2;
    }


    /**
     * public double originDistance()
     * Overloaded originDistance() method from parent class as per the assignment specification
     * Determines closest euclidean distance of each point of semicircle
     * @return - Closest distance
     */
    @Override
    public double originDistance()
    {
        double extremity1Euclidean = calculateExtremityPoint1().calculateDistanceFromOrigin();
        double extremity2Euclidean = calculateExtremityPoint2().calculateDistanceFromOrigin();
        double baseEuclidean = basePoint.calculateDistanceFromOrigin();
        double perpendicularEuclidean = perpendicularPoint.calculateDistanceFromOrigin();

        double closest = extremity1Euclidean;
        if(extremity2Euclidean < closest) closest = extremity2Euclidean;
        if(baseEuclidean < closest) closest = baseEuclidean;
        if(perpendicularEuclidean < closest) closest = perpendicularEuclidean;
        return closest;
    }


    /**
     * public boolean equals(Object o)
     * Generated from IntelliJ
     * Overridden method that determines whether two semi circles have the same points
     * @param o - Second Semi Circle Object
     * @return - Boolean value if equal
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SemiCircle that = (SemiCircle) o;

        if (basePoint != null ? !basePoint.equals(that.basePoint) : that.basePoint != null) return false;
        return perpendicularPoint != null ? perpendicularPoint.equals(that.perpendicularPoint) : that.perpendicularPoint == null;
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
        result = 31 * result + (basePoint != null ? basePoint.hashCode() : 0);
        result = 31 * result + (perpendicularPoint != null ? perpendicularPoint.hashCode() : 0);
        return result;
    }
}
