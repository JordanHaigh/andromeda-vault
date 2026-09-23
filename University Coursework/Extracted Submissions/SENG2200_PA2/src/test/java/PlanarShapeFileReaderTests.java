import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Jordan on 18-Mar-17.
 */
public class PlanarShapeFileReaderTests {
    @Test
    public void readPolygonDataFromString_StringWithSpaces() throws Exception {
        String line = "P  5     1 2 3 4 5 6 7 8 9 0";
        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);
        Assert.assertEquals(1, linkedList.size());
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6),
                new Point(7, 8),
                new Point(9, 0),
                new Point(1, 2)
        };
        Polygon expectedPolygon = new Polygon(points);
        Polygon actualPolygon = (Polygon) linkedList.get(0);


        Assert.assertEquals(expectedPolygon, actualPolygon);
        Assert.assertEquals(expectedPolygon.area(), actualPolygon.area(), 0.05);
    }

    @Test
    public void readPolygonDataFromString_TwoPolygonsOnSameLine() throws Exception {
        String line = "P 5 1 2   3 4 5 6 7   8 9 0   P 5 1 2 3 4 5 6 7 8 9 0";
        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(2, linkedList.size());

        Point[] pointsA = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6),
                new Point(7, 8),
                new Point(9, 0),
                new Point(1, 2)
        };
        Polygon expectedPolygonA = new Polygon(pointsA);
        Polygon actualPolygonA = (Polygon) linkedList.get(0);
        Assert.assertEquals(expectedPolygonA, actualPolygonA);
        Assert.assertEquals(expectedPolygonA.area(), actualPolygonA.area(), 0.05);

        Point[] pointsB = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6),
                new Point(7, 8),
                new Point(9, 0),
                new Point(1, 2),
        };
        Polygon expectedPolygonB = new Polygon(pointsB);
        Polygon actualPolygonB = (Polygon) linkedList.get(1);
        Assert.assertEquals(expectedPolygonB, actualPolygonB);
        Assert.assertEquals(expectedPolygonB.area(), actualPolygonB.area(), 0.05);


    }

    @Test
    public void readPolygonDataFromString_TwoPolygons_FirstPolygonOverMultipleLines_SecondPolygonUsesLowerPMultipleLines() throws Exception {
        String line = " \n P 5 \n 1 2 3 4 \n 5 6 7 8 9 0 p 2 1 2 3 4     \n";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(2, linkedList.size());

        Point[] pointsA = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6),
                new Point(7, 8),
                new Point(9, 0),
                new Point(1, 2)
        };
        Polygon expectedPolygonA = new Polygon(pointsA);
        Polygon actualPolygonA = (Polygon) linkedList.get(0);
        Assert.assertEquals(expectedPolygonA, actualPolygonA);
        Assert.assertEquals(expectedPolygonA.area(), actualPolygonA.area(), 0.05);

        Point[] pointsB = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
        };
        Polygon expectedPolygonB = new Polygon(pointsA);
        Polygon actualPolygonB = (Polygon) linkedList.get(0);
        Assert.assertEquals(expectedPolygonB, actualPolygonB);
        Assert.assertEquals(expectedPolygonB.area(), actualPolygonB.area(), 0.05);
    }

    @Test
    public void readPolygonDataFromString_OneValidPolygon_InvalidStartOfSecondPolygon() throws Exception {
        String line = "PP 5 1 2 3 4 5 6 7 8 9 0 PP";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(1, linkedList.size());


        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(3, 4),
                new Point(5, 6),
                new Point(7, 8),
                new Point(9, 0),
                new Point(1, 2)
        };
        Polygon expectedPolygon = new Polygon(points);
        Polygon actualPolygon = (Polygon) linkedList.get(0);

        boolean equalPolys = expectedPolygon.equals(actualPolygon);

        Assert.assertEquals(true, equalPolys);
        Assert.assertEquals(actualPolygon, expectedPolygon);
        Assert.assertEquals(expectedPolygon.area(), actualPolygon.area(), 0.05);

    }

    @Test
    public void readCircleDataFromString_OneValidCircle() throws Exception {
        String line = "C 0 0 5   \n";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(1, linkedList.size());

        Circle newCircle = new Circle(new Point(0, 0), 5);
        Circle actualCircle = (Circle) linkedList.get(0);
        boolean equalCircles = actualCircle.equals(newCircle);

        Assert.assertEquals(true, equalCircles);
        Assert.assertEquals(newCircle, actualCircle);
        Assert.assertEquals(newCircle.area(), actualCircle.area(), 0.05);
    }

    @Test
    public void readCircleDataFromString_OneValidCircle_MultipleCs() throws Exception {
        String line = "     \n    CC     0   0      5   CC        \n";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(1, linkedList.size());

        Circle newCircle = new Circle(new Point(0, 0), 5);
        Circle actualCircle = (Circle) linkedList.get(0);
        boolean equalCircles = actualCircle.equals(newCircle);

        Assert.assertEquals(true, equalCircles);
        Assert.assertEquals(newCircle, actualCircle);
        Assert.assertEquals(newCircle.area(), actualCircle.area(), 0.05);
    }


    @Test
    public void readCircleDataFromString_OneValidCircle_MultipleCs_TwoDatasets() throws Exception {
        String line = "     \n    CC     0   0      5   CC        \n    C    1    1   5\n";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(2, linkedList.size());

        Circle expectedCircle1 = new Circle(new Point(0, 0), 5);
        Circle actualCircle1 = (Circle) linkedList.get(0);
        boolean equalCircles1 = actualCircle1.equals(expectedCircle1);

        Assert.assertEquals(true, equalCircles1);
        Assert.assertEquals(expectedCircle1, actualCircle1);
        Assert.assertEquals(expectedCircle1.area(), actualCircle1.area(), 0.05);

        //Circle 2
        Circle expectedCircle2 = new Circle(new Point(1, 1), 5);
        Circle actualCircle2 = (Circle) linkedList.get(1);
        boolean equalCircles2 = actualCircle1.equals(expectedCircle1);

        Assert.assertEquals(true, equalCircles2);
        Assert.assertEquals(expectedCircle2, actualCircle2);
        Assert.assertEquals(expectedCircle2.area(), actualCircle2.area(), 0.05);
    }

    @Test
    public void readSemiCircleDataFromString_OneValidCircle() throws Exception {
        String line = "s    0.000000000213   \n0.000000000213   4.369    \t\t4.369    \n";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(1, linkedList.size());

        SemiCircle expectedSemi = new SemiCircle(new Point(0.000000000213, 0.000000000213), new Point(4.369,4.369));
        SemiCircle  actualSemi= (SemiCircle) linkedList.get(0);
        boolean equalSemiCircles = expectedSemi.equals(actualSemi);

        Assert.assertEquals(true, equalSemiCircles);
        Assert.assertEquals(expectedSemi, actualSemi);
        Assert.assertEquals(expectedSemi.area(), actualSemi.area(), 0.05);
    }

    @Test
    public void readSemiCircleDataFromString_TwoValidSemiCircles() throws Exception {
        String line = "ss    0.000000000213   \n0.000000000213   4.369     \t\n \t\t4.369  sS  \n" +
                "  4 3 3 \r\n3";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(2, linkedList.size());

        SemiCircle expectedSemi1 = new SemiCircle(new Point(0.000000000213, 0.000000000213), new Point(4.369,4.369));
        SemiCircle  actualSemi1= (SemiCircle) linkedList.get(0);
        boolean equalSemiCircles1 = expectedSemi1.equals(actualSemi1);

        Assert.assertEquals(true, equalSemiCircles1);
        Assert.assertEquals(expectedSemi1, actualSemi1);
        Assert.assertEquals(expectedSemi1.area(), actualSemi1.area(), 0.05);


        //SECOND
        SemiCircle expectedSemi2 = new SemiCircle(new Point(4, 3), new Point(3,3));
        SemiCircle  actualSemi2= (SemiCircle) linkedList.get(1);
        boolean equalSemiCircles2 = expectedSemi2.equals(actualSemi2);

        Assert.assertEquals(true, equalSemiCircles2);
        Assert.assertEquals(expectedSemi2, actualSemi2);
        Assert.assertEquals(expectedSemi2.area(), actualSemi2.area(), 0.05);
    }

    @Test
    public void readSemiCircleDataFromString_0_0_0_0() throws Exception
    {
        String line = "s 0 0 0 0 ";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(1, linkedList.size());

        SemiCircle expectedSemi = new SemiCircle(new Point(0,0), new Point(0,0));
        SemiCircle  actualSemi= (SemiCircle) linkedList.get(0);
        boolean equalSemiCircles = expectedSemi.equals(actualSemi);

        Assert.assertEquals(true, equalSemiCircles);
        Assert.assertEquals(expectedSemi, actualSemi);
        Assert.assertEquals(expectedSemi.area(), actualSemi.area(), 0.05);
    }

    @Test
    public void readSemiCircleDataFromString_TwoValidSemisVERYClosearea() throws Exception {
        String line = "S 0 0 9.5543 9.5545\n " +
        "    S 1 0 10.5543 9.5544";

        PlanarShapeFileReader reader = new PlanarShapeFileReader();
        LinkedList<PlanarShape> linkedList = reader.readPlanarShapeDataFromString(line);

        Assert.assertEquals(2, linkedList.size());

        SemiCircle expectedSemi1 = new SemiCircle(new Point(0, 0), new Point(9.5543, 9.5545));
        SemiCircle  actualSemi1= (SemiCircle) linkedList.get(0);
        boolean equalSemiCircles1 = expectedSemi1.equals(actualSemi1);

        Assert.assertEquals(true, equalSemiCircles1);
        Assert.assertEquals(expectedSemi1, actualSemi1);
        Assert.assertEquals(expectedSemi1.area(), actualSemi1.area(), 0.05);


        //SECOND
        SemiCircle expectedSemi2 = new SemiCircle(new Point(1, 0), new Point(10.5543, 9.5544));
        SemiCircle  actualSemi2= (SemiCircle) linkedList.get(1);
        boolean equalSemiCircles2 = expectedSemi2.equals(actualSemi2);

        Assert.assertEquals(true, equalSemiCircles2);
        Assert.assertEquals(expectedSemi2, actualSemi2);
        Assert.assertEquals(expectedSemi2.area(), actualSemi2.area(), 0.05);

        Assert.assertEquals(-1, actualSemi1.compareTo(actualSemi2));
    }


}