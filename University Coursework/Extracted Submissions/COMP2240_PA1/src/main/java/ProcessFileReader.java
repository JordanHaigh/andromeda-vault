import java.util.ArrayList;
import java.util.List;
import java.io.*;


/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * ProcessFileReader.java Class reads from a data file and extracts all information relevant for the program.
 * Utilises precondition checking to rebuild the data file and work with "Sunny Day" data
 */
public class ProcessFileReader implements IObservable
{
    List<ISubscriber> subscribers = new ArrayList<>();



    /**
     * public List<Process> readProcessesFromFile(String filePath)
     * Reads the file location and processes the data into a list of processes
     * @param filePath - Filepath of data file
     * @return - List of Processes found in the data file
     */
    public List<Process> readProcessesFromFile(String filePath) throws Exception
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String wholeFile = readWholeFile(reader);

            // Once file has been fully read, convert the string builder into a standard java string
            return readProcessDataFromString(wholeFile);
        }
        catch(Exception e)
        {
            throw e;
        }
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
     * @param textData
     * @return
     * @throws Exception
     */


    /**
     * private List<Process> readProcessDataFromString(String textData) throws Exception
     * Firstly cleanses the string and stores in another variable so the original text data is unaltered
     * Then creates a process from the cleansed string dataset
     * @param textData - Line of all data
     * @return - List<Process> Object with all Processes found in data file
     * @throws Exception - Check if file was not imported correctly
     */
    private List<Process> readProcessDataFromString(String textData) throws Exception
    {
        //Cleanse line to remove \n, \t characters and make the any glyphs toUpperCase
        String cleansedDataString = cleanseProcessDataFile(textData);

        String[] individualDataSets = rebuildDataFile(cleansedDataString);

        List<Process> processList = new ArrayList<>();

        for (String dataset : individualDataSets)
        {
            if(dataset != null) //Check removing a null at the end of the string array
            {
                String[] individualValues = dataset.split(" ");

                //Condition check if there is a missing "END" keyword between two data sets
                if(individualValues.length == 4)
                {
                    String id = individualValues[0];
                    int arrive = Integer.parseInt(individualValues[1]);
                    int execSize = Integer.parseInt(individualValues[2]);
                    int priority = Integer.parseInt(individualValues[3]);

                    Process p = new Process(id,arrive,execSize,priority);
                    processList.add(p);
                }
                else
                    throw new IllegalArgumentException("Missing END keyword between data set");
            }
        }

        return processList;
    }

    /**
     * private String cleanseProcessDataFile(String line)
     * Removes all unnecessary characters and returns a cleaner string to be worked with
     * @param line - Data file to string
     * @return - String of all data without certain characters (Double spaces, new lines, tabs)
     */
    private String cleanseProcessDataFile(String line)
    {
        String cleansed = line.replaceAll("\r\n", " ") // \r\n is windows version of new line
                    .replaceAll("\n", " ")
                    .replaceAll("\t", " ");

        while (cleansed.contains("  "))
            cleansed = cleansed.replace("  ", " ");

        return cleansed;
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
        String[] dispatchDataAndProcessData = textData.split(" END ");
        String[] rebuiltProcessData = new String[dispatchDataAndProcessData.length-1]; //To eliminate the DISP section and EOF statement
        int currentIndex = 0;


        //Determining dispatch value and sending it back to the program main
        String dispatchData = dispatchDataAndProcessData[0];
        int dispatcherValue = determineDispatcherValue(dispatchData);
        ObservableMessage message = new ObservableDispatcherMessage(dispatcherValue);
        notifySubscribers(message);


        //Determining process data sets
        for(int i = 1; i < dispatchDataAndProcessData.length-1; i++) //Using :Length-1 to eliminate the "EOF" statement
        {
            rebuiltProcessData[currentIndex] = determineProcessVales(dispatchDataAndProcessData[i]);
            currentIndex++;
        }

        return rebuiltProcessData;
    }

    /**
     * private int determineDispatcherValue(String dispatchData)
     * Determines whether the dispatcher data contains the regex string and separates it out
     * Is then able to determine the dispatcher value
     * @param dispatchData - String of dispatcher data
     * @return - Dispatcher value
     */
    private int determineDispatcherValue(String dispatchData)
    {
        //Separating the dispatcher information from the rest of the data sets
        if(dispatchData.contains("DISP: "))
        {
            //Need to find the index where the keyword "DISP: " finishes to determine the actual value
            String[] separatedValues = dispatchData.split("DISP: ");

            //Condition check if missing "END" keyword between dispatcher and first data set
            if(separatedValues[1].contains("ID: ") || separatedValues[1].contains("Arrive: ")
                    || separatedValues[1].contains("ExecSize: ") || separatedValues[1].contains("Priority: "))
            {
                throw new IllegalArgumentException("Missing 'END' keyword between Dispatcher data and Process data");
            }


            return Integer.parseInt(separatedValues[1]);

        }
        else
            throw new IllegalArgumentException("Cannot find dispatcher data");
    }

    /**
     * @param processData
     * @return
     */


    /**
     * private String determineProcessVales(String processData)
     * Determines whether the string parameter contains the required regexes for a process and returns process data string
     * with only its values
     * @param processData- String of process data
     * @return - String of values
     */
    private String determineProcessVales(String processData)
    {
        if(processData.contains("ID: ") && processData.contains("Arrive: ")
                && processData.contains("ExecSize: ") && processData.contains("Priority: "))
        {
            String newProcessData = processData;
            newProcessData = newProcessData.replace("ID: ", "");
            newProcessData = newProcessData.replace("Arrive: ", "");
            newProcessData = newProcessData.replace("ExecSize: ", "");
            newProcessData = newProcessData.replace("Priority: ", "");


            return newProcessData;
        }
        else
            throw new IllegalArgumentException("File data is not sufficient. Check formatting");

    }


    //Subsciber methods used for transferring the dispatcher information back to the program
    /**
     * public void addSubscriber(ISubscriber subscriber)
     * Adds a new subscriber to the subscribers list.
     * Utilised for the observer pattern
     * @param subscriber - New subscriber
     */
    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    /**
     * public void removeSubScriber(ISubscriber subscriber)
     * Removes a subscriber from the subscribers list
     * @param subscriber - Existing subscriber in the the list
     */
    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * public void notifySubscribers(ObservableMessage message)
     * Broadcast a message from the CPU class to all subscribers of this class.
     * Utilised for the observer pattern
     * @param message - Observable Message to be sent to all subscribers
     */
    @Override
    public void notifySubscribers(ObservableMessage message) {
        for(ISubscriber subscriber : subscribers)
            subscriber.handleMessage(message);
    }
}