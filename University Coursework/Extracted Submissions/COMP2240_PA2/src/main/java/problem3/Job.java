package problem3;



/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * Job.java contains all information for Jobs that will be running on the printer
 * Extends the thread class to utilise the run method
 */
public class Job extends Thread
{
    private JobType jobType;
    private int id;
    private int numberOfPages;
    private Printer printer;

    /**
     * public Job(JobType jobType, int id, int numberOfPages, Printer printer)
     * Overloaded Constructor used for creating jobs
     * @param jobType - Type of the Job (M or C)
     * @param id - Unique ID of the Job in its category
     * @param numberOfPages - Number of pages it needs to print out
     * @param printer - Printer object that the job will run on
     */
    public Job(JobType jobType, int id, int numberOfPages, Printer printer)
    {
        this.jobType = jobType;
        this.id = id;
        this.numberOfPages = numberOfPages;
        this.printer = printer;
    }

    /**
     * public String getJobName()
     * @return - The type of the job and its unique id (M1| C1)
     */
    public String getJobName(){return jobType.toString() + id; }

    /**
     * public int getJobID()
     * @return - Id of the Job
     */
    public int getJobID() {return id; }

    /**
     * public int getNumberOfPages()
     * @return - Number of Pages that the job must print
     */
    public int getNumberOfPages() {return numberOfPages; }

    /**
     * public boolean isMonohromeJob()
     * @return - True or false depending on whether the job is of type 'M'
     */
    public boolean isMonochromeJob() {return jobType.equals(JobType.M); }

    /**
     * public boolean isColourJob()
     * @return - True or false depending on whether the job is of type 'C'
     */
    public boolean isColourJob() {return jobType.equals(JobType.C); }

    /**
     * public void run()
     * Overridden method from the Thread class.
     * Job is submitted to the printer and eventually leaves the printer
     */
    @Override
    public void run()
    {
        //Enter printer
        printer.enter(this);

        //Leave printer
        printer.leave(this);

    }

}

