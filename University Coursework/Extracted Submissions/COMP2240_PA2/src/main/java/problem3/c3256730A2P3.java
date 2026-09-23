package problem3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * c3256730A2P3.java is the entry point to the third problem of the assignment
 */
public class c3256730A2P3
{
    Printer printer = new Printer();
    Queue<Job> masterJobQueue = new LinkedList<>();


    public static void main(String[]args)
    {
        c3256730A2P3 intFace = new c3256730A2P3();
        intFace.run(args);
    }

    /**
     * private void run(String[]args)
     * Args parameter is checked to determine if there is something in position 0 of the array
     * @param args - Args from command line
     */
    private void run(String[] args)
    {
        if(args.length < 1 || args[0] == null )
            throw new IllegalArgumentException("No datafile to work with");

        String datafile = args[0];

        try { readDataFile(datafile); } catch (Exception e) { e.printStackTrace(); }

        printer.feedJobs(masterJobQueue);

        for(Job job : masterJobQueue)
            job.start();

    }

    /**
     * private void readDataFile(String filepath)
     * Reads the number of jobs and all jobs found in the file
     * @param filePath - Arguments defining the filepath
     */
    private void readDataFile(String filePath) throws Exception
    {

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            String wholeFile = readWholeFile(reader);

            // Once file has been fully read, convert the string builder into a standard java string
            String[] fileFragments = wholeFile.split("\n");

            int numberOfJobs = Integer.parseInt(fileFragments[0]);
            for(int i = 1; i < fileFragments.length; i++)
            {
                int endIndex = fileFragments[i].indexOf(" ");

                String typeAndId = fileFragments[i].substring(0,endIndex);
                JobType jobType = (typeAndId.contains("M")) ? JobType.M : JobType.C;
                int id = Integer.parseInt(typeAndId.replace("M", "").replace("C",""));
                int pages = Integer.parseInt(fileFragments[i].substring(endIndex+1, fileFragments[i].length()));

                Job newJob = new Job(jobType, id, pages, this.printer);
                masterJobQueue.add(newJob);
            }


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

}
