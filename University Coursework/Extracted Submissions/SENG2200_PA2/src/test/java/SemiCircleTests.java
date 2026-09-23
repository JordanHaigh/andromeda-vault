import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 22-Apr-17.
 */
public class SemiCircleTests
{
    private static final double _4_OVER_ROOT2 = 4/Math.sqrt(2);
    private static final double _10_OVER_ROOT2 = 10/Math.sqrt(2);

    @Test
    public void calculateRadius() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(0,4));
        double radius = semi.calculateRadius();
        Assert.assertEquals(4,radius,0.005);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_Point0_4_Return4_0() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(0,4));
        Point extremity1 = semi.calculateExtremityPoint1();

        Point ep1 = new Point(-4,0);
        boolean equalPoints = extremity1.equals(ep1);
        Assert.assertEquals(true, equalPoints);

        Assert.assertEquals(ep1, extremity1);

        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(4,0), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_Point4Root2() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(_4_OVER_ROOT2,_4_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(-_4_OVER_ROOT2,_4_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(_4_OVER_ROOT2,-_4_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_Point4_0() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(4,0));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(0,4), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(0,-4), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_Point4Root2_Neg4Root2() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(_4_OVER_ROOT2,-_4_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(_4_OVER_ROOT2,_4_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(-_4_OVER_ROOT2, -_4_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_Point0_Neg4() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(0,-4));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(4,0), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(-4,0), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_PointNeg4Root2_Neg4Root2() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(-_4_OVER_ROOT2,-_4_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(_4_OVER_ROOT2,-_4_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(-_4_OVER_ROOT2, _4_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_PointNeg4_0() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(-4,0));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(0,-4), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(0,4), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point0_0_PointNeg4Root2_4Root2() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(-_4_OVER_ROOT2,_4_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(-_4_OVER_ROOT2,-_4_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(_4_OVER_ROOT2, _4_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_Point10_20() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(10,20));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(0,10), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(20,10), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_20Minusx_20MinusX() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(20-_10_OVER_ROOT2,20-_10_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(_10_OVER_ROOT2,20-_10_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(20-_10_OVER_ROOT2,_10_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_Point20_10() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(20,10));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(10,20), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(10,0), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_20Minusx_X() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(20-_10_OVER_ROOT2,_10_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(20-_10_OVER_ROOT2,20-_10_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(_10_OVER_ROOT2,_10_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_Point10_0() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(10,0));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(20,10), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(0,10), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_x_x() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(_10_OVER_ROOT2,_10_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(20-_10_OVER_ROOT2,_10_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(_10_OVER_ROOT2,20-_10_OVER_ROOT2), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_Point0_10() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(0,10));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(10,0), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(10,20), extremity2);
    }

    @Test
    public void calculateExtremityPoint1_Circle_Point10_10_x_20Minusx() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(_10_OVER_ROOT2,20-_10_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(_10_OVER_ROOT2,_10_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(20-_10_OVER_ROOT2,20-_10_OVER_ROOT2), extremity2);
    }

    @Test
    public void area_Semicircle_Point10_10_Pointx_x() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(_10_OVER_ROOT2,_10_OVER_ROOT2));
        double radius = semi.calculateRadius();
        Assert.assertEquals(Math.PI * radius * radius /2, semi.area(), 0.005);
    }

    @Test
    public void originDistance_semicircle_point0_0_point_0_4() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(0,0), new Point(0,4));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(-4,0), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(4,0), extremity2);

        double ep1Euclidean = extremity1.calculateDistanceFromOrigin();
        double ep2Euclidean = extremity2.calculateDistanceFromOrigin();
        double baseEuclidean = semi.getBasePoint().calculateDistanceFromOrigin();
        double perpEuclidean = semi.getPerpendicularPoint().calculateDistanceFromOrigin();

        Assert.assertEquals(0.0,baseEuclidean, 0.005);

    }

    @Test
    public void originDistance_semicircle_point0_0_point_x_20Minusx() throws Exception
    {
        SemiCircle semi = new SemiCircle(new Point(10,10), new Point(_10_OVER_ROOT2,20-_10_OVER_ROOT2));
        Point extremity1 = semi.calculateExtremityPoint1();

        Assert.assertEquals(new Point(_10_OVER_ROOT2,_10_OVER_ROOT2), extremity1);


        Point extremity2 = semi.calculateExtremityPoint2();
        Assert.assertEquals(new Point(20-_10_OVER_ROOT2,20-_10_OVER_ROOT2), extremity2);

        double ep1Euclidean = extremity1.calculateDistanceFromOrigin();
        double ep2Euclidean = extremity2.calculateDistanceFromOrigin();
        double baseEuclidean = semi.getBasePoint().calculateDistanceFromOrigin();
        double perpEuclidean = semi.getPerpendicularPoint().calculateDistanceFromOrigin();

        Assert.assertEquals(10.0,ep1Euclidean, 0.005);

    }

}