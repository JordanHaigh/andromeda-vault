public abstract class PlanarShape implements Comparable<PlanarShape>
{
    private int id;
    private static int index;
    private static final double THRESHOLD = 0.005;

    /**
     * Default Constructor
     */
    public PlanarShape()
    {
        this.id = index;
        index++;
    }

    /**
     * public abstract String toString
     * Abstract toString() method that is implemented in child classes
     * @return - String of dataset
     */
    public abstract String toString();

    /**
     * public abstract double area()
     * Abstract area() method that is implemented in child classes
     * @return - Area result
     */
    public abstract double area();

    /**
     * public abstract double originDistance()
     * Abstract originDistance() that is implemented in child classes
     * @return - Origin distance result
     */
    public abstract double originDistance();

    /**
     * public boolean equals(Object o)
     * Generated from IntelliJ
     * Overridden method that determines whether two PlanarShapes are equal
     * @param o - Second PlanarShape Object
     * @return - Boolean value if equal
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanarShape p = (PlanarShape)o;
        return compareAreaTo(p) == 0 && compareOriginDistanceTo(p.originDistance()) == 0;
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
        result = 31 * result + (int)area();
        result = 31 * result + (int)originDistance();
        return result;
    }

    /**
     * public int compareTo(PlanarShape p)
     * Determines if a planarshape comes before a second planarshape, uses area, origin distance and index to determine
     * @param p - Second planar shape
     * @return - Int value if less than, equal to or greater than
     */
    @Override
    public int compareTo(PlanarShape p)
    {
        if(p == null)
            throw new NullPointerException();

        double otherArea = p.area();
        double otherDistanceToOrigin = p.originDistance();
        int otherIndex = p.id;

        int compareAreaResult = compareAreaTo(p);

        if(compareAreaResult == 0)
        {
            //Areas are equal according to the specification
            //Now check which polygon is closest to the origin
            //Check equal distance

            int val = compareOriginDistanceTo(otherDistanceToOrigin);

            // If Equal distance to origin. Return the index comparison (Same entry into unsorted list)
            return val == 0 ? compareIndexTo(otherIndex) : val;
        }
        else
            return compareAreaResult;
    }

    /**
     * private int compareAreaTo(PlanarsShape p)
     * Compares areas
     * @param p - Second planarShape
     * @return - Int value if greater than, less than or equal to
     */
    private int compareAreaTo(PlanarShape p)
    {
        if(hasEqualArea(p))
            return 0;

        double otherArea = p.area();

        if(this.area() < otherArea)
            return -1;
        else if(this.area() > otherArea)
            return 1;
        else
            return 0;
    }

    /**
     * private boolean hasEqualArea(PlanarShape p)
     * Determines whether the area of two planar shapes are within the threshold
     * @param p - Second planar shape object
     * @return - Boolean if equal
     */
    private boolean hasEqualArea(PlanarShape p)
    {
        return (Math.abs(this.area()-p.area()) <= THRESHOLD);
    }

    /**
     * private int compareOriginDistanceTo(double otherDistanceToOrigin)
     * Determines whether one originDistance is less than another originDistance
     * @param otherDistanceToOrigin - Second distanceToOrigin
     * @return - Int value if less than, greater than or equal to
     */
    private int compareOriginDistanceTo(double otherDistanceToOrigin)
    {
        if(originDistance() < otherDistanceToOrigin)
            return -1;
        else if(this.originDistance() > otherDistanceToOrigin)
            return 1;
        else
            return 0;
    }

    /**
     * private int compareIndexTo(int otherIndex)
     * Compares whether one index comes before or after another index
     * @param otherIndex - Second index
     * @return - Int value if less than, greater than or equal to
     */
    private int compareIndexTo(int otherIndex)
    {
        if(this.id < otherIndex)
            return -1;
        else if(this.id > otherIndex)
            return 1;
        else
            return 0;
    }

}
