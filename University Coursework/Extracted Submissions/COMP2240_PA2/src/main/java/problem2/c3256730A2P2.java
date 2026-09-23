package problem2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * c3256730A2P2.java is the entry point to the second problem of the assignment
 */

public class c3256730A2P2
{
    private BridgeP2 bridgeP2 = new BridgeP2();


    public static void main(String[] args)
    {
        c3256730A2P2 intFace = new c3256730A2P2();
        intFace.run(args);
    }

    /**
     * private void run(String[]args)
     * Args parameter is checked to determine if there is something in position 0 of the array
     * @param args - Args from command line
     */
    private void run(String[]args)
    {
        if(args.length < 1 || args[0] == null)
            try { throw new Exception("Missing file argument"); } catch (Exception e) { e.printStackTrace(); }

        try { readDataFile(args[0]); } catch (Exception e) { e.printStackTrace(); }


    }

    /**
     * private void readDataFile(String filepath)
     * Reads the number of north and south farmers and instantiates the correct amount
     * @param filePath - Arguments defining the filepath
     */
    private void readDataFile(String filePath) throws Exception
    {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String wholeFile = readWholeFile(reader);

            // Once file has been fully read, convert the string builder into a standard java string
            wholeFile = wholeFile.replace("N=", "");
            wholeFile = wholeFile.replace("S=", "");
            wholeFile = wholeFile.replaceAll("\n", "");
            wholeFile = wholeFile.replaceAll(",", "");
            String[] splitNorthSouth = wholeFile.split(" ");

            if(splitNorthSouth.length != 2)
            {
                throw new Exception("Wrong input syntax. MUST be 'N=_, S=_'");
            }

            int northNumber = Integer.parseInt(splitNorthSouth[0]);
            int southNumber = Integer.parseInt(splitNorthSouth[1]);

            //If there are an odd id of farmers, the program will match as many farmers as it can.
            //The odd farmer will always wait i.e program will not end
            //"The odd id farmer should be waiting forever" - Nasim


            instantiateFarmers(northNumber, southNumber);

        } catch (Exception e) { throw e; }
    }

    /**
     * public String readWholeFile(Buffered reader)
     * Reads the entire input data file and appends to a stringbuilder
     * @param reader - BufferedReader used to read the file
     * @return - String containing all data
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
     * private void instantiateFarmers(int northNumber, int southNumber)
     * Create the appropriate number of threads for each island

     * @param northNumber - North number of farmers to be instantiated
     * @param southNumber - South number of farmers to be instantiated
     */
    private void instantiateFarmers(int northNumber, int southNumber)
    {

        List<FarmerP2> farmers = new ArrayList<>();


        //Instantiate Farmers on corresponding Islands

        //ATTENTION - If the both loops were starting the threads as they are creating them, the solution would have n North
        //Threads running before the South threads have a chance.
        //It is not mentioned in the specs, but randomising this list would be a solution for initial fairness.


        for (int i = 0; i < northNumber; i++) {
            farmers.add(new FarmerP2(IslandLocations.N, i + 1, this.bridgeP2));
        }

        for (int i = 0; i < southNumber; i++) {
            farmers.add(new FarmerP2(IslandLocations.S, i + 1, this.bridgeP2));
        }

        //Collections.shuffle(farmers);
        for(FarmerP2 farmerP2 : farmers)
            farmerP2.start();

    }

}
