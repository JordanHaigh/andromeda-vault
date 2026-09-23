package problem3;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * Printer.java contains all Semaphores for the number of jobs that are allowed to run parallel and Light Switches
 * These light switches are used to control which job type is running on the printer heads
 *
 * This class models the Unisex Bathroom Problem that is found in the Little Book of Semaphores
 * Page 171
 */
public class Printer
{
    private static final int NUMBERPRINTERHEADS = 3;
    Queue<Job> masterJobQueue;


    private int colourJobIDNowServing = 0;
    private int monochromeJobIDNowServing = 0;
    Semaphore jobIDUpdateMutex = new Semaphore(1);


    Semaphore empty = new Semaphore(1);
    Semaphore turnstile = new Semaphore(1);

    JobTypeLock colourLock = new JobTypeLock();
    JobTypeLock monochromeLock = new JobTypeLock();
    Semaphore colourMultiplex = new Semaphore(NUMBERPRINTERHEADS);
    Semaphore monochromeMultiplex = new Semaphore(NUMBERPRINTERHEADS);

    private int printerTime = 0;


    /**
     * public void feedJobs(Queue<Job> masterJobQueue)
     * Feeds the masterJobQueue from the Main class to the Printer Class
     * @param masterJobQueue - Job Queue from Main class containing all jobs
     */
    public void feedJobs(Queue<Job> masterJobQueue)
    {
        this.masterJobQueue = masterJobQueue;
    }


    /**
     * public void enter(Job job)
     * Job enters the printer. Depending on the type of the job, it will use the monochrome or colour switch and multiplex
     * @param job - New job added to the printer
     */
    public void enter(Job job)
    {
        if(job.isColourJob())
            runJob(colourLock, colourMultiplex, job);
        else
            runJob(monochromeLock, monochromeMultiplex, job);
    }

    /**
     * private void runJob(JobTypeLock jobTypeLock, Semaphore multiplex, Job job)
     * Job is first checked to determine if it able to run (mX must be served before mY in the specification)
     * The turnstile is then acquired to lock the lightswitch for this job (It there are permits available)
     * The multiplex will attempt to acquire a permit and run the critical section if ther eare available permits
     * The lightswitch will be unlocked after the critical section
     * @param jobTypeLock - Specific Lightswitch according to the current Job's Job type
     * @param multiplex - Specific Multiplex, according to the current Job's Job Type
     * @param job - Current job wanting to run on the printer heads
     */
    private void runJob(JobTypeLock jobTypeLock, Semaphore multiplex, Job job)
    {
        enforceIdScheduling(job);

        try { turnstile.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
            jobTypeLock.lock(empty);
        turnstile.release();

        try { multiplex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        {{
            /////////////////////////////////

            runJobOnPrinterHead(multiplex, job);

            /////////////////////////////////

        }}
        multiplex.release();

        //Unlock the respective lock
        jobTypeLock.unlock(empty);
    }

    /**
     * private void enforceIdScheduling(Job job)
     * Checks are put in place to determine if the current id that is currently being served
     * is less than the new job's id
     * The job must wait if it's id is higher than the current job running on the printer
     * @param job - Job trying to run on the printer
     */
    private void enforceIdScheduling(Job job)
    {
        //Whilst there is a lower order job number for this job type
        //i.e

        if(job.isColourJob())
        {
            while(colourJobIDNowServing+1 < job.getJobID() )
            {
                synchronized (this)
                {
                    //System.out.println(job.getJobName() + ": Job is waiting");
                    try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                    // System.out.println(job.getJobName() + ": Job as woken up");

                }
            }
        }
        else
        {
            while(monochromeJobIDNowServing+1 < job.getJobID() )
            {
                synchronized (this)
                {
                    //System.out.println(job.getJobName() + ": Job is waiting");
                    try { wait(); } catch (InterruptedException e) { e.printStackTrace(); }
                    // System.out.println(job.getJobName() + ": Job as woken up");

                }
            }
        }
    }

    /**
     * private void runJobOnPrinterHead(Semaphore multiplex, Job job)
     * Critical Section of the Printer Class
     * Current Job now serving id is updated to the parameter Job's id.
     * Runs on the printer for n number of pages
     * Updates the printer time by determining which checking which
     * @param multiplex - Specific Multiplex depending on the type of job that is running
     * @param job - Job running on the printer
     */
    private void runJobOnPrinterHead(Semaphore multiplex, Job job)
    {

        System.out.println(printerJobMessage(job, multiplex));

        try { jobIDUpdateMutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        {{
            if(job.isColourJob())
                colourJobIDNowServing = job.getJobID();
            else
                monochromeJobIDNowServing = job.getJobID();
        }}
        jobIDUpdateMutex.release();

        synchronized (this)
        {
            notifyAll();
        }

        int currentTime = printerTime;

        for(int i = 0; i < job.getNumberOfPages(); i++)
        {
            //Thread.sleep(100) is only utilised so that other threads can occupy other heads of the printer
            //Otherwise it would always be using the one head since the jobs would go through the program too quickly
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        }

        //Find the higher value of the two parameters to determine what the new printer time will be.
        printerTime = Math.max(currentTime + job.getNumberOfPages(), printerTime);

    }


    /**
     * public void leave(Job job)
     * Removes the job from the master queue
     * Once the master queue hits a size of zero, it will print the DONE message
     * @param job - Job to be removed from the masterQueue
     */
    public void leave(Job job)
    {
        masterJobQueue.remove(job);
        if(masterJobQueue.isEmpty())
            System.out.println("(" + printerTime + ") DONE");
    }

    /**
     * private String printerJobMessage(Job job, Semaphore multiplex)
     * Message that is printed once a job is running on a printer head
     * @param job - Current job running on the printer
     * @param multiplex - Specific multiplex determined by the Job parameter
     * @return - String formatted to the specification
     */
    private String printerJobMessage(Job job, Semaphore multiplex)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("(")
                .append(printerTime)
                .append(") ")
                .append(job.getJobName())
                .append(" uses head ")
                .append(NUMBERPRINTERHEADS - multiplex.availablePermits())
                .append(" (time: ")
                .append(job.getNumberOfPages())
                .append(")");

        return sb.toString();
    }
}
