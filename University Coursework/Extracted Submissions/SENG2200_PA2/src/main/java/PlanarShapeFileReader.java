import java.io.*;

/**
 * PlanarShapeFileReader handles all file input in the application
 * Reads the entire file and "cleanses" the string to remove any unwanted characters
 * Moves to build polygons and add to the LL
 */
public class PlanarShapeFileReader
{

    /**
     * public LinkedList readPlanarShapeDataFromFile(String filePath)
     * Reads whole file and stores in string. Moves to read the polygon data from the string
     * @param filePath - File path of the data file
     * @return - LinkedList<PlanarShape>
     */
    public LinkedList<PlanarShape> readPlanarShapeDataFromFile(String filePath) throws Exception
    {
        //import bufferedReader for the readLine method
        //try-with-resources and catch block for exceptions
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String wholeFile = readWholeFile(reader);

            // once file has been fully read, convert the string builder into a standard java string
            // and call readPlanarShapeDataFromString();
            return readPlanarShapeDataFromString(wholeFile);
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    /**
     * public LinkedList readPlanarShapeDataFromString(String textData)
     * Firstly cleanses the string and stores in another variable so the original text data is unaltered
     * Uses the RebuildDataFile to fix and create individual data sets inside a string array
     * Iterates through the string array and creates a new polygon
     * @param textData - Line of all polygons
     * @return - LinkedList Object with all polygons stores properly in the LL
     * @throws Exception - Check if file was not imported correctly
     */
    public LinkedList<PlanarShape> readPlanarShapeDataFromString(String textData) throws Exception
    {
        //Cleanse line to remove \n, \t characters and make the any glyphs toUpperCase
        String cleansedData = cleansePlanarShapeData(textData);

        String[] individualDataSets = rebuildDataFile(cleansedData);

        LinkedList<PlanarShape> linkedList = new LinkedList<>();

        // for each line in the file, call processNextLine();
        for (String dataset : individualDataSets)
        {
            char shapeType = dataset.charAt(0);
            PlanarShape shape = makePlanarShape(shapeType, dataset);

            if(shape != null)
                linkedList.append(shape);
        }

        return linkedList;
    }

    /**
     * private String[] rebuildDataFile(String textData)
     * Uses a regex string to split the textData by the specific shape glyph
     * Returns the rebuilt data file
     * @param textData - Text data containing all data sets
     * @return - String array with fixed data sets
     */
    private String[] rebuildDataFile(String textData)
    {
        textData = textData.trim();
        String regex = "(?=\\b[PCSQTE])"; //Splits by P, C, S, Q, T , E - Shapes defined in spec
        String[] planarShapeData = textData.split(regex);

        String[] rebuiltPlanarShapeData = new String[planarShapeData.length]; // Used when rebuilding data lines if contains more than one glyph
        for(int i = 0; i < planarShapeData.length; i++)
        {
            String shapeData = planarShapeData[i];
            String[] shapeDataSplits = shapeData.split(" ");
            //Get the first element in the shapeDataSplits - Determine whether we need to fix the data set
            String firstElement = shapeDataSplits[0];

            //Check if there is more than one character in the first element
            String rebuiltString = shapeData;
            if(firstElement.length() > 1)
            {
                rebuiltString = rebuildSingleDataset(firstElement, shapeDataSplits);
            }
            rebuiltPlanarShapeData[i] = rebuiltString;
        }
        return rebuiltPlanarShapeData;
    }

    /**
     * private String rebuildSingleDataset(String firstElement, String[] shapeDataSplits)
     * Determines whether there is more than one glyph in the first position and replaces the first index with the last char
     * @param firstElement - First element in the String
     * @param shapeDataSplits - Remainder of the string
     * @return - Rebuilt data string
     */
    private String rebuildSingleDataset(String firstElement, String[] shapeDataSplits)
    {
        //Need to rebuild the data set
        //Identify the last character in the first element - Data set will now use this character
        char lastCharacter = firstElement.charAt(firstElement.length()-1);

        //Replace the entire firstElement (shapeDataSplits[0]) with the lastCharacter
        shapeDataSplits[0] = String.valueOf(lastCharacter);

        //Now need to rebuild string
        //Create a stringbuilder for rebuilding
        StringBuilder sb = new StringBuilder();
        //Start from element one since we have reassigned element zero
        for(int i = 0; i < shapeDataSplits.length; i++)
        {
            //Check if j is at the end of the for loop so that we don't add an extra null character
            if(i == shapeDataSplits.length-1)
                sb.append(shapeDataSplits[i]);
            else
                sb.append(shapeDataSplits[i])
                        .append(" ");
        }

        return sb.toString();
    }

    /**
     * private String cleansePlanarShapeData(String line)
     * Removes all unnecessary characters and returns a cleaner string to be worked with
     * @param line - String containing all polygons found in data file
     * @return - String of all polygons without certain characters (Double spaces, new lines, tabs)
     */
    private String cleansePlanarShapeData(String line)
    {
        String cleansed = line.replaceAll("\r\n", " ") // \r\n is windows version of new line
                    .replaceAll("\n", " ")
                    .replaceAll("\t", " ")
                    .toUpperCase();

        while (cleansed.contains("  "))
            cleansed = cleansed.replace("  ", " ");

        return cleansed;
    }

    /**
     * public String readWholeFile(Buffered reader)
     * Reads the entire input data file and appends to a stringbuilder
     * @param reader - BufferedReader used to read the file
     * @return - String containing all polygons separated by new line character
     * @throws IOException
     */
    private String readWholeFile(BufferedReader reader) throws IOException
    {
        //Modified from http://abhinandanmk.blogspot.com.au/2012/05/java-how-to-read-complete-text-file.html
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = reader.readLine()) != null)
        {
            sb.append(line).append("\n");
        }

        return sb.toString();
    }


    /**
     * private PlanarShape makePlanarShape(char shapeType, String dataset) throws Exception
     * Factory method for creating shape types
     * @param shapeType - Glyph defining the type of shape
     * @param dataset - Remainder of dataset
     * @return - New PlanarShape of shapeType
     * @throws Exception - If Glyph is not supported
     */
    private PlanarShape makePlanarShape(char shapeType, String dataset) throws Exception
    {
        //remove glyph from dataset

        dataset = dataset.substring(1);
        dataset = dataset.trim();

        // Handle case where there is no text
        if (dataset.length() < 1) return null;

        String[] allValues = dataset.split(" ");

        switch (shapeType)
        {
            case 'P': return buildPolygon(allValues);
            case 'C': return buildCircle(allValues);
            case 'S': return buildSemiCircle(allValues);
            default: throw new Exception("Undefined Shape type");
        }
    }

    /**
     * private Polygon buildPolygon(String[] allCharacters)
     * Creates a new Polygon with the characters found in the string array
     * @param allCharacters - String array containing all coordinates
     * @return - New Polygon containing the correct coordinates
     * @throws Exception - If number of points does not equal the max number of points
     */
    private Polygon buildPolygon(String[] allCharacters) throws Exception
    {
        // P [NumVertices] [x1] [y1] ... [xNumVertices] [yNumVertices]

        int numberOfPoints = 0;
        int maxNumberOfPoints = Integer.parseInt(allCharacters[0]);

        Point[] points = new Point[maxNumberOfPoints+1];

        for(int i = 1; i < allCharacters.length; i+=2)
        {
            Point newPoint = new Point(
                    Double.parseDouble(allCharacters[i]),
                    Double.parseDouble(allCharacters[i+1])
            );
            points[numberOfPoints] = newPoint;
            numberOfPoints++;
        }

        if(numberOfPoints != maxNumberOfPoints)
            throw new Exception("Max number of points entered as second character does not equal the number of points entered");

        points[maxNumberOfPoints] = new Point(points[0]);

        return new Polygon(points);
    }

    /**
     * private Circle buildCircle(String[] allCharacters)
     * Creates a new Circle with the characters found in the string array
     * @param allCharacters - String array containing coordinates
     * @return - New Circle
     * @throws Exception
     */
    private Circle buildCircle(String[] allCharacters) throws Exception
    {
        //C [x0] [y0] [r]
        double x0 = Double.parseDouble(allCharacters[0]);
        double y0 = Double.parseDouble(allCharacters[1]);
        double radius = Double.parseDouble(allCharacters[2]);

        Point centre = new Point(x0,y0);
        return new Circle(centre, radius);
    }

    /**
     * private SemiCircle buildSemiCircle(String[] allCharacters)
     * Creates a new SemiCircle with characters found in the string array
     * @param allCharacters - String array containing coordinates
     * @return - New Semi Circle
     */
    private SemiCircle buildSemiCircle(String[] allCharacters)
    {
        //S [x0] [y0] [x1] [y1]
        double x0 = Double.parseDouble(allCharacters[0]);
        double y0 = Double.parseDouble(allCharacters[1]);
        double x1 = Double.parseDouble(allCharacters[2]);
        double y1 = Double.parseDouble(allCharacters[3]);

        Point basePoint = new Point(x0,y0);
        Point perpendicularPoint = new Point(x1,y1);
        return new SemiCircle(basePoint, perpendicularPoint);
    }

}
