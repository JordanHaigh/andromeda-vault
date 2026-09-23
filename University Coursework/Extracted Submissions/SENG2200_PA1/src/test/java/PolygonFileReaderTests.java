import c3256730.seng2200.pa1.MyPolygons;
import c3256730.seng2200.pa1.Point;
import c3256730.seng2200.pa1.Polygon;
import c3256730.seng2200.pa1.PolygonFileReader;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jordan on 18-Mar-17.
 */
public class PolygonFileReaderTests
{
    @Test
    public void readPolygonDataFromString_StringWithSpaces() throws Exception
    {
        String line = "P  5     1 2 3 4 5 6 7 8 9 0";
        PolygonFileReader reader = new PolygonFileReader();
        MyPolygons myPolygons = reader.readPolygonDataFromString(line);
        Assert.assertEquals(1, myPolygons.size());
        Point[] points = new Point[]{ new Point(1,2), new Point(3,4), new Point(5,6), new Point(7,8) ,new Point(9,0) ,new Point(1,2)};
        Polygon expectedPolygon = new Polygon(points);
        Polygon actualPolygon = (Polygon)myPolygons.getDataAtIndex(0);


        Assert.assertEquals(expectedPolygon, actualPolygon);
        Assert.assertEquals(expectedPolygon.calculateArea(), actualPolygon.calculateArea(), 0.05);
    }

    @Test
    public void readPolygonDataFromString_TwoPolygonsOnSameLine() throws Exception
    {
        String line = "P 5 1 2   3 4 5 6 7   8 9 0   P 5 1 2 3 4 5 6 7 8 9 0";
        PolygonFileReader reader = new PolygonFileReader();
        MyPolygons myPolygons = reader.readPolygonDataFromString(line);

        Assert.assertEquals(2, myPolygons.size());

        Point[] pointsA = new Point[]{
                new Point(1,2),
                new Point(3,4),
                new Point(5,6),
                new Point(7,8),
                new Point(9,0),
                new Point(1,2)
        };
        Polygon expectedPolygonA = new Polygon(pointsA);
        Polygon actualPolygonA = (Polygon)myPolygons.getDataAtIndex(0);
        Assert.assertEquals(expectedPolygonA, actualPolygonA);
        Assert.assertEquals(expectedPolygonA.calculateArea(), actualPolygonA.calculateArea(), 0.05);

        Point[] pointsB = new Point[]{
                new Point(1,2),
                new Point(3,4),
                new Point(5,6),
                new Point(7,8),
                new Point(9,0),
                new Point(1,2),
        };
        Polygon expectedPolygonB = new Polygon(pointsB);
        Polygon actualPolygonB = (Polygon)myPolygons.getDataAtIndex(1);
        Assert.assertEquals(expectedPolygonB, actualPolygonB);
        Assert.assertEquals(expectedPolygonB.calculateArea(), actualPolygonB.calculateArea(), 0.05);


    }

    @Test
    public void readPolygonDataFromString_TwoPolygons_FirstPolygonOverMultipleLines_SecondPolygonUsesLowerPMultipleLines() throws Exception
    {
        String line = " \n P 5 \n 1 2 3 4 \n 5 6 7 8 9 0 p 2 1 2 3 4";

        PolygonFileReader reader = new PolygonFileReader();
        MyPolygons myPolygons = reader.readPolygonDataFromString(line);

        Assert.assertEquals(2, myPolygons.size());

        Point[] pointsA = new Point[]{
                new Point(1,2),
                new Point(3,4),
                new Point(5,6),
                new Point(7,8),
                new Point(9,0),
                new Point(1,2)
        };
        Polygon expectedPolygonA = new Polygon(pointsA);
        Polygon actualPolygonA = (Polygon)myPolygons.getDataAtIndex(0);
        Assert.assertEquals(expectedPolygonA, actualPolygonA);
        Assert.assertEquals(expectedPolygonA.calculateArea(), actualPolygonA.calculateArea(), 0.05);

        Point[] pointsB = new Point[]{
                new Point(1,2),
                new Point(3,4),
        };
        Polygon expectedPolygonB = new Polygon(pointsA);
        Polygon actualPolygonB = (Polygon)myPolygons.getDataAtIndex(0);
        Assert.assertEquals(expectedPolygonB, actualPolygonB);
        Assert.assertEquals(expectedPolygonB.calculateArea(), actualPolygonB.calculateArea(), 0.05);
    }

    @Test
    public void readPolygonDataFromString_OneValidPolygon_InvalidStartOfSecondPolygon() throws Exception
    {
        String line = "PP 5 1 2 3 4 5 6 7 8 9 0 PP";

        PolygonFileReader reader = new PolygonFileReader();
        MyPolygons myPolygons = reader.readPolygonDataFromString(line);

        Assert.assertEquals(1, myPolygons.size());


        Point[] points = new Point[]{
                new Point(1,2),
                new Point(3,4),
                new Point(5,6),
                new Point(7,8),
                new Point(9,0),
                new Point(1,2)
        };
        Polygon expectedPolygon = new Polygon(points);
        Polygon actualPolygon = (Polygon)myPolygons.getDataAtIndex(0);
        Assert.assertEquals(expectedPolygon, actualPolygon);
        Assert.assertEquals(expectedPolygon.calculateArea(), actualPolygon.calculateArea(), 0.05);


    }



    //PP 5 1 2 3 4 5 6 7 8 9 0 PP


    //PP
}