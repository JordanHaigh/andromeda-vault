import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 22-Apr-17.
 */
public class CircleTests
{
    @Test
    public void getRadius_Radius5() throws Exception
    {
        Circle circle = new Circle(new Point(0,0), 5);
        Assert.assertEquals(5, circle.getRadius(), 0.01);
    }

    @Test
    public void area_Radius10Origin0_0() throws Exception
    {
        Circle circle = new Circle(new Point(0,0), 10);
        double area = circle.area();
        Assert.assertEquals(314.159, area, 0.05);
    }

    @Test
    public void area_Radius10Origin10_10() throws Exception
    {
        Circle circle = new Circle(new Point(10,10), 10);
        double area = circle.area();
        Assert.assertEquals(314.159, area, 0.05);
    }

    @Test
    public void area_Radius10OriginNeg10_Neg10() throws Exception
    {
        Circle circle = new Circle(new Point(-10,-10), 10);
        double area = circle.area();
        Assert.assertEquals(314.159, area, 0.05);
    }

    @Test
    public void originDistance() throws Exception
    {

    }


    @Test
    public void originDistanceFromCentre_Circle10_10_Radius5_10TimesSqrt2() throws Exception
    {
        Circle circle = new Circle(new Point(10,10),5);
        double euclidean = circle.originDistanceFromCentre();
        Assert.assertEquals(14.14, euclidean, 0.05);
    }

    @Test
    public void originDistanceFromCentre_CircleCentre0_0_Radius5() throws Exception
    {
        Circle circle = new Circle(new Point(0,0),5);
        double euclidean = circle.originDistanceFromCentre();
        Assert.assertEquals(0, euclidean, 0.05);
    }

    @Test
    public void originDistanceFromCentre_CircleCentreNeg10_Neg10_Radius5() throws Exception
    {
        Circle circle = new Circle(new Point(-10,-10),5);
        double euclidean = circle.originDistanceFromCentre();
        Assert.assertEquals(14.14, euclidean, 0.05);
    }

    @Test
    public void originDistanceFromCentre_CircleCentre0_0_Radius_0() throws Exception
    {
        Circle circle = new Circle(new Point(0,0),0);
        double euclidean = circle.originDistanceFromCentre();
        Assert.assertEquals(0, euclidean, 0.05);
    }

    @Test
    public void originDistanceToOuterEdge_Circle10_10_Radius_5_9Point14() throws Exception
    {
        Circle circle = new Circle(new Point(10,10),5);
        double euclidean = circle.originDistanceToOuterEdge();
        Assert.assertEquals(9.14, euclidean, 0.05);
    }

    @Test
    public void originDistanceToOuterEdge_CentreCentre0_0_Radius5() throws Exception
    {
        Circle circle = new Circle(new Point(0,0),5);
        double euclidean = circle.originDistanceToOuterEdge();
        Assert.assertEquals(-5, euclidean, 0.05);
    }

    @Test
    public void compareTo_Circle0_0_1_Circle_0_0_1Point005() throws Exception
    {
        Circle circle1 = new Circle(new Point(0,0),1);
        Circle circle2 = new Circle(new Point(0,0), 1.005);
        Assert.assertEquals(-1, circle1.compareTo(circle2));
    }

    @Test
    public void compareTo_Circle_0_0_1_Circle_1Point1_0_1() throws Exception
    {
        Circle circle1 = new Circle(new Point(0,0),1);
        Circle circle2 = new Circle(new Point(1.1,0), 1);
        Assert.assertEquals(-1, circle1.compareTo(circle2));

    }

    @Test
    public void compareTo_Circle_0_0_1_Circle_0Point9_0_1() throws Exception
    {
        Circle circle1 =  new Circle(new Point(0,0), 1);
        Circle circle2 = new Circle(new Point(0.9, 0), 1);
        Assert.assertEquals(-1, circle1.compareTo(circle2));

    }
}