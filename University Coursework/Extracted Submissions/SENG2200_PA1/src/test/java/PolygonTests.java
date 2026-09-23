import c3256730.seng2200.pa1.Point;
import c3256730.seng2200.pa1.Polygon;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jordan on 18-Mar-17.
 */
public class PolygonTests
{
    @Test
    public void calculateArea_1Points_Area0() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
                new Point(1,1)
        });

        Assert.assertEquals(0, polygon.getSides());

        Assert.assertEquals(0, polygon.calculateArea(),0.05);
    }

    @Test
    public void calculateArea_3Points_Area0() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
                new Point(1,1),
                new Point(1,2),
                new Point(2,2),
                new Point(1,1),
        });


        Assert.assertEquals(0.5, polygon.calculateArea(),0.05);
    }


    @Test
    public void calculateArea_2Points_Area0() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
            new Point(1,1),
            new Point(2,2),
        });

        Assert.assertEquals(0, polygon.getSides());

        Assert.assertEquals(0, polygon.calculateArea(),0.05);
    }

    @Test
    public void calculateArea_40_48_78_73_90_71_Area24_5() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
                new Point(4,0),
                new Point(4,8),
                new Point(7,8),
                new Point(7,3),
                new Point(9,0),
                new Point(7,1),
                new Point(4,0),
        });
        Assert.assertEquals(24.5, polygon.calculateArea(), 0.05);
    }

    @Test
    public void calculateArea_12_34_56_78_90_12_ScaleneTriangle_Area30() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
                new Point(1,2),
                new Point(3,4),
                new Point(5,6),
                new Point(7,8),
                new Point(9,0),
                new Point(1,2),
        });
        Assert.assertEquals(30, polygon.calculateArea(), 0.05);
    }

    @Test
    public void calculateArea_Neg10Neg10_10Neg10_010_Triangle_Area200() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
                new Point(-10,-10),
                new Point(10,-10),
                new Point(0,10),
                new Point(-10,-10)
        });
        Assert.assertEquals(200, polygon.calculateArea(), 0.05);
    }

    @Test
    public void calculateArea_Neg10Neg10_10Neg10_1010_Neg1010_Square_Area400() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]{
                new Point(-10,-10),
                new Point(10,-10),
                new Point(10,10),
                new Point(-10,10),
                new Point(-10,-10)
        });
        Assert.assertEquals(400, polygon.calculateArea(), 0.05);
    }

    @Test
    public void calculateArea_SixSidedPolygon_UsingDoublesOverFourQuadrants_Distance63Point905() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]
                {
                        new Point(6.1,6.8),
                        new Point(8.3,2.8),
                        new Point(5.0,-1.1),
                        new Point(-1.3,-1.7),
                        new Point(-1.9,2.4),
                        new Point(-0.6,5.8),
                        new Point(6.1,6.8)
                });
        Assert.assertEquals(63.905, polygon.calculateArea(), 0.05);
    }

    @Test
    public void calculateArea_NineSidedPolygon_UsingDoublesOverFourQuadrants_ComplexDiagram_Distance63Point905() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]
                {
                        new Point(5.1,5.8),
                        new Point(0.2,2.3),
                        new Point(1.7,-1.5),
                        new Point(4.9,-0.6),
                        new Point(-4.1,-5.5),
                        new Point(0.3,-3.1),
                        new Point(-1.4,3.2),
                        new Point(1.4,4.6),
                        new Point(-0.7,6.0),
                        new Point(5.1,5.8)
                });
        Assert.assertEquals(19.85, polygon.calculateArea(), 0.05);
    }

    @Test
    public void calculateArea_NineSidedPolygon_ReverseOrder_UsingDoublesOverFourQuadrants_ComplexDiagram_Distance63Point905() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]
                {
                        new Point(5.1,5.8),
                        new Point(-0.7,6.0),
                        new Point(1.4,4.6),
                        new Point(-1.4,3.2),
                        new Point(0.3,-3.1),
                        new Point(-4.1,-5.5),
                        new Point(4.9,-0.6),
                        new Point(1.7,-1.5),
                        new Point(0.2,2.3),
                        new Point(5.1,5.8),
                });
        Assert.assertEquals(19.85, polygon.calculateArea(), 0.05);
    }


    @Test
    public void calculateDistanceFromOrigin_NineSidedPolygon_ComplexPolygon_ReturnPointEuclieanX1_7YNeg1_5() throws Exception
    {
        Polygon polygon = new Polygon(new Point[]
                {
                        new Point(5.1,5.8),
                        new Point(-0.7,6.0),
                        new Point(1.4,4.6),
                        new Point(-1.4,3.2),
                        new Point(0.3,-3.1),
                        new Point(-4.1,-5.5),
                        new Point(4.9,-0.6),
                        new Point(1.7,-1.5),
                        new Point(0.2,2.3),
                        new Point(5.1,5.8),
                });
        Point pointA = new Point(0.2,2.3);
        double euclideanPointA = pointA.calculateDistanceFromOrigin();

        Point pointB = new Point(1.7,-1.5);
        double euclideanPointB = pointB.calculateDistanceFromOrigin();


        Assert.assertEquals(euclideanPointB,polygon.calculateDistanceFromOrigin(),0.005);
    }

    @Test
    public void comesBefore_PolygonTriangle_PolygonTrianglePlus1() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(-10,-10),
                new Point(10,-10),
                new Point(0,10),
                new Point(-10,-10)
        }); //Area = 200

        Polygon b = new Polygon(new Point[]{
                new Point(-9,-10),
                new Point(11,-10),
                new Point(1,10),
                new Point(-9,-10)
        }); //Area = 200
        Assert.assertEquals(true,a.comesBefore(b));
    }

    @Test
    public void comesBefore_PolygonTriangle_PolygonTrianglePlusDoubles() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(-10,-10),
                new Point(10,-10),
                new Point(0,10),
                new Point(-10,-10)
        }); //Area = 200

        Polygon b = new Polygon(new Point[]{
                new Point(-9+3.2,-9+2.4),
                new Point(11+2.2,-11+1.2),
                new Point(1+5.7,11+1.1),
                new Point(-9+3.2,-9+2.4)
        }); //Area = 197.65

        double euclideanA = a.calculateDistanceFromOrigin(); //10.0
        double euclideanB = b.calculateDistanceFromOrigin(); //8.78

        //So b should be before a, i.e false
        Assert.assertEquals(false,a.comesBefore(b));
    }

    @Test
    public void comesBefore_Area100_Area94_95_False() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(0,10),
                new Point(0,0)
        }); //Area = 100

        Polygon b = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(-0.99,8),
                new Point(0,0)
        }); //Area = 94.95

        Assert.assertEquals(false,a.comesBefore(b));
    }

    @Test
    public void comesBefore_Area100_Area95_WithinRange_CheckEuclidean() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(0,10),
                new Point(0,0)
        }); //Area = 100

        Polygon b = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(-1,8),
                new Point(0,0)
        }); //Area = 95

        double euclideanA = a.calculateDistanceFromOrigin();
        double euclideanB = b.calculateDistanceFromOrigin();

        //So b should be before a, i.e false
        Assert.assertEquals(true,a.comesBefore(b));
    }

    @Test
    public void comesBefore_Area100_Area95_05_WithinRange_CheckEuclidean() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(0,10),
                new Point(0,0)
        }); //Area = 100

        Polygon b = new Polygon(new Point[]{
                new Point(1,0),
                new Point(10+1,0),
                new Point(10+1,10),
                new Point(-1.01+1,8),
                new Point(1,0)
        }); //Area = 95.05

        double euclideanA = a.calculateDistanceFromOrigin();
        double euclideanB = b.calculateDistanceFromOrigin();

        //So b should be before a, i.e false
        Assert.assertEquals(true,a.comesBefore(b));
    }

    @Test
    public void comesBefore_Area100_Area104_95_WithinRange_CheckEuclidean() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(0+1,0),
                new Point(10+1,0),
                new Point(10+1,10),
                new Point(0+1,10),
                new Point(0+1,0)
        }); //Area = 100

        Polygon b = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(-2.99,8),
                new Point(0,0)
        }); //Area = 104.95

        double euclideanA = a.calculateDistanceFromOrigin();
        double euclideanB = b.calculateDistanceFromOrigin();

        //So b should be before a, i.e false
        Assert.assertEquals(false,a.comesBefore(b));
    }

    @Test
    public void comesBefore_Area100_Area105_WithinRange_CheckEuclidean() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(0+1,0),
                new Point(10+1,0),
                new Point(10+1,10),
                new Point(0+1,10),
                new Point(0+1,0)
        }); //Area = 100

        Polygon b = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(-3,8),
                new Point(0,0)
        }); //Area = 105

        double euclideanA = a.calculateDistanceFromOrigin();
        double euclideanB = b.calculateDistanceFromOrigin();

        //So b should be before a, i.e false
        Assert.assertEquals(false,a.comesBefore(b));
    }

    @Test
    public void comesBefore_Area100_Area105_05_OutsideRange() throws Exception
    {
        Polygon a = new Polygon(new Point[]{
                new Point(0+1,0),
                new Point(10+1,0),
                new Point(10+1,10),
                new Point(0+1,10),
                new Point(0+1,0)
        }); //Area = 100

        Polygon b = new Polygon(new Point[]{
                new Point(0,0),
                new Point(10,0),
                new Point(10,10),
                new Point(-3.01,8),
                new Point(0,0)
        }); //Area = 105.05

        //So b should be before a, i.e false
        Assert.assertEquals(true,a.comesBefore(b));
    }



}