import c3256730.seng2200.pa1.Point;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jordan on 18-Mar-17.
 */
public class PointTests
{
    @Test
    public void calculateDistanceFromOrigin_X5Y5_DistanceSqrt50() throws Exception
    {
        Point point = new Point(5,5);
        Assert.assertEquals(Math.sqrt(50), point.calculateDistanceFromOrigin(), 0.05);
    }


    @Test
    public void calculateDistanceFromOrigin_X0Y5_Distance5() throws Exception
    {
        Point point = new Point(0,5);
        Assert.assertEquals(5, point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromOrigin_XMinus5Y5_DistanceSqrt50() throws Exception
    {
        Point point = new Point(-5,5);
        Assert.assertEquals(Math.sqrt(50), point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromOrigin_XMinus5Y5_Distance5() throws Exception
    {
        Point point = new Point(-5,0);
        Assert.assertEquals(5, point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromOrigin_XMinus5YMinus5_DistanceSqrt50() throws Exception
    {
        Point point = new Point(-5,-5);
        Assert.assertEquals(Math.sqrt(50), point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromOrigin_X0YMinus5_Distance5() throws Exception
    {
        Point point = new Point(0,-5);
        Assert.assertEquals(5, point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromOrigin_X5YMinus5_DistanceSqrt50() throws Exception
    {
        Point point = new Point(5,-5);
        Assert.assertEquals(Math.sqrt(50), point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromOrigin_X5Y0_Distance5() throws Exception
    {
        Point point = new Point(5,0);
        Assert.assertEquals(5, point.calculateDistanceFromOrigin(), 0.05);
    }

    @Test
    public void calculateDistanceFromPoint_X5Y5_X1Y1_Distance() throws Exception
    {
        Point a = new Point(1,1);
        Point b = new Point(5,5);
        Assert.assertEquals(Math.sqrt(50)-Math.sqrt(2),b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_X0Y5_X0Y1_Distance() throws Exception
    {
        Point a = new Point(0,1);
        Point b = new Point(0,5);
        Assert.assertEquals(4,b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_XMinus5Y5_XMinus1Y1_Distance() throws Exception
    {
        Point a = new Point(-1,1);
        Point b = new Point(-5,5);
        Assert.assertEquals(Math.sqrt(50)-Math.sqrt(2),b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_XMinus5Y0_XMinus1Y0_Distance() throws Exception
    {
        Point a = new Point(-1,0);
        Point b = new Point(-5,0);
        Assert.assertEquals(4,b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_XMinus5YMinus5_XMinus1YMinus1_Distance() throws Exception
    {
        Point a = new Point(-1,-1);
        Point b = new Point(-5,-5);
        Assert.assertEquals(Math.sqrt(50)-Math.sqrt(2),b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_X0YMinus1_X0YMinus5_Distance() throws Exception
    {
        Point a = new Point(0,-1);
        Point b = new Point(0,-5);
        Assert.assertEquals(4,b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_X5YMinus5_X1YMinus1_Distance() throws Exception
    {
        Point a = new Point(1,-1);
        Point b = new Point(5,-5);
        Assert.assertEquals(Math.sqrt(50)-Math.sqrt(2),b.calculateDistanceFromPoint(a),0.05);
    }

    @Test
    public void calculateDistanceFromPoint_X1Y0_X5Y0_Distance() throws Exception
    {
        Point a = new Point(1,0);
        Point b = new Point(5,0);
        Assert.assertEquals(4,b.calculateDistanceFromPoint(a),0.05);
    }
}