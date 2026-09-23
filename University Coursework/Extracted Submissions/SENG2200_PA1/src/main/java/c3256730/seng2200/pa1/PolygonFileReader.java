package c3256730.seng2200.pa1;

import java.io.*;

/**
 * PolygonFileReader handles all file input in the application
 * Reads the entire file and "cleanses" the string to remove any unwanted characters
 * Moves to build polygons and add to the LL
 */
public class PolygonFileReader
{

    /**
     * public MyPolygons readPolygonDataFromFile(String filePath)
     * Reads whole file and stores in string. Moves to read the polygon data from the string
     * @param filePath - File path of the data file
     * @return - MyPolygons object
     */
    public MyPolygons readPolygonDataFromFile(String filePath) throws Exception
    {
        //import bufferedReader for the readLine method
        //try-with-resources and catch block for exceptions
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String wholeFile = readWholeFile(reader);

            // once file has been fully read, convert the string builder into a standard java string
            // and call readPolygonDataFromString();
            return readPolygonDataFromString(wholeFile);
        }
        catch(Exception e)
        {
            throw e;
        }
    }

    /**
     * public MyPolygons readPolygonDataFromString(String textData)
     * Firstly cleanses the string and stores in another variable so the original text data is unaltered
     * Splits the string into a String Array by the "P" character
     * Iterates through the string array and creates a new polygon
     * @param textData - Line of all polygons
     * @return - MyPolygons Object with all polygons stores properly in the LL
     * @throws Exception - Check if file was not imported correctly
     */
    public MyPolygons readPolygonDataFromString(String textData) throws Exception
    {
        //Cleanse line to remove \n, \t characters and make the any glyphs toUpperCase
        String cleansedData = cleansePolygonData(textData);

        //Polygon will be specified by the input 'P'
        String[] polygonText = cleansedData.split("P");

        MyPolygons myPolygons = new MyPolygons();

        // for each line in the file, call processNextLine();
        for (String polygon : polygonText)
        {
            Polygon newPolygon = processNextPolygon(polygon);
            if(newPolygon != null)
                myPolygons.append(newPolygon);
        }

        return myPolygons;
    }

    /**
     * private String cleansePolygonData(String line)
     * Removes all unnecessary characters and returns a cleaner string to be worked with
     * @param line - String containing all polygons found in data file
     * @return - String of all polygons without certain characters (Double spaces, new lines, tabs)
     */
    private String cleansePolygonData(String line)
    {
        String cleansed = line.replaceAll("\n", " ")
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
     * public Polygon processNextPolygon(String polygontext)
     * Handles if the string contains no text or any whitespace
     * Splits the string by the null character and builds polygon
     * @param polygonText - String of all polygon text
     * @return - Polygon containing all points specified from the string parameter
     * @throws Exception - Error if line was incorrect with the syntax
     */
    private Polygon processNextPolygon(String polygonText) throws Exception
    {

        // Handle case where there is no text
        if (polygonText.trim().length() < 1) return null;

        String[] allCharacters = polygonText.split(" ");
        return buildPolygon(allCharacters);
    }


    /**
     * private Polygon buildPolygon(String[] allCharacters)
     * Creates a new Polygon with the characters found in the string array
     * @param allCharacters - String array containing all coordinates
     * @return - New Polygon containing the correct coordinates
     * @throws Exception
     */
    private Polygon buildPolygon(String[] allCharacters) throws Exception
    {
        int numberOfPoints = 0;
        int maxNumberOfPoints = Integer.parseInt(allCharacters[1]);

     /*   //Check if minimum number point is less than three
        if(maxNumberOfPoints < MINIMUM_POINTS_PER_POLYGON)
        {
            //Either a straight line or a single point
            //Area will always be zero

        }*/

        Point[] points = new Point[maxNumberOfPoints+1];

        for(int i = 2; i < allCharacters.length; i+=2)
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

        //if(numberOfPoints >= Polygon.MINIMUM_POINTS_PER_POLYGON) //Three is the minimum number of sides for a polygon to exist
        //{
            //Create endpoint to close the polygon
            points[maxNumberOfPoints] = new Point(points[0]);
        //}

        return new Polygon(points);


    }

}
