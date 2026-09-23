import java.util.Comparator;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * Process.java Class used to run processes on the CPU. Data is gathered from the input file
 * to create processes
 */
public class Process implements Comparable<Process>
{
    //Variables as specified from data input
    private String id;

    private int priority; //Range of 0-5, 0 being highest priority

    private int serviceTime;
    private int remainingServiceTime;

    private ProcessState processState;

    private int arriveTime; //Time when process was first arrived into the NEW state
    private int finishTime; //Time when process enters the TERMINATED state

    private int turnaroundTime; //Time taken from when process was spawned to when process was terminated
    private int waitingTime = 0; //Time spent waiting in the READY State



    public Process(String id, int arriveTime, int serviceTime, int priority)
    {
        this.id = id;
        this.arriveTime = arriveTime;
        this.serviceTime = serviceTime;
        remainingServiceTime = serviceTime;
        this.priority = priority;
        processState = ProcessState.NEW;

    }

    public Process(Process process)
    {
        //Constructs copy of process given parameter
        this.id = process.getId();
        this.arriveTime = process.getArriveTime();
        this.serviceTime = process.getServiceTime();
        remainingServiceTime = serviceTime;
        this.priority = process.getPriority();
        processState = ProcessState.NEW;
    }

    /**
     * public String getID()
     * @return - Process ID
     */
    public String getId() {return id;}

    /**
     * public int getTurnaroundTime()
     * @return - Process Turnaround Time
     */
    public int getTurnaroundTime() {return turnaroundTime;}

    /**
     * public int getWaitingTime()
     * @return - Process Waiting Time
     */
    public int getWaitingTime() {return waitingTime;}

    /**
     * public int getArriveTime()
     * @return - Process Arrive Time
     */
    public int getArriveTime() {return arriveTime; }

    /**
     * public int getServiceTime()
     * @return - Process Service Time
     */
    public int getServiceTime() {return serviceTime; }

    /**
     * public int getPriority()
     * @return - Process Priority
     */
    public int getPriority() {return priority;}

    /**
     * public int getRemainingServiceTime()
     * @return Process RemainingServiceTime
     */
    public int getRemainingServiceTime() {return remainingServiceTime; }

    /**
     * public boolean isNew()
     * @return - True or false if process state is NEW
     */
    public boolean isNew() { return processState.equals(ProcessState.NEW); }

    /**
     * public boolean isReady()
     * @return - True or False if process state is READY
     */
    public boolean isReady() { return processState.equals(ProcessState.READY); }

    /**
     * public boolean isRunning()
     * @return - True or False if process state is RUNNING
     */
    public boolean isRunning() { return processState.equals(ProcessState.RUNNING); }

    /**
     * public boolean isFinishedCycling()
     * @return - True or false if the remaining service time is zero
     */
    public boolean isFinishedCycling() {return remainingServiceTime == 0;}


    /********************** STATE TRANSITIONS **********************/
    /**
     * public void run()
     * Subtracts 1 from the remaining service time only if the process is running
     */
    public void run()
    {
        if(isRunning())
            remainingServiceTime--;
        else
            runTimeExceptionMessage(ProcessState.RUNNING);

    }

    /**
     * public void admit
     * Updates the process state to READY if it is NEW
     */
    public void admit(int currentTime)
    {
        //Precondition check to determine if process is in the NEW state
        if(isNew())
        {
            //Update state
            processState = ProcessState.READY;
            //stateTransitionMessage(ProcessState.READY, currentTime);

        }
        else
            runTimeExceptionMessage(ProcessState.NEW);
    }

    /**
     * public void dispatch
     * Updates the process state to RUNNING if it is READY
     */
    public void dispatch(int currentTime)
    {
        //Precondition check to determine if the process is in the READY state
        if(isReady())
        {
            //Update state
            processState = ProcessState.RUNNING;
            //stateTransitionMessage(ProcessState.RUNNING, currentTime);

        }
        else
            runTimeExceptionMessage(ProcessState.READY);
    }

    /**
     * public void interrupt()
     * Updates the process state to READY if it is RUNNING
     */
    public void interrupt(int currentTime)
    {
        //Precondition check to determine if the process is in the RUNNING state
        if(isRunning())
        {
            //Update state
            processState = ProcessState.READY;
            //stateTransitionMessage(ProcessState.READY, currentTime);

        }
        else
            runTimeExceptionMessage(ProcessState.RUNNING);
    }

    /**
     * public void exit(int currentTime)
     * Updates the process state to TERMINATED if it is RUNNING
     * Updates the finish time to the current time and calculates other statistics
     * @param currentTime - Current time on the cpu
     */
    public void exit(int currentTime)
    {
        if(isRunning())
        {
            //Update State
            processState = ProcessState.TERMINATED;
            //stateTransitionMessage(ProcessState.TERMINATED, currentTime);

            //Update finishTime
            finishTime = currentTime;

            turnaroundTime = finishTime - arriveTime;

            waitingTime = turnaroundTime - serviceTime;

        }
    }

    /**
     * private void runtimeExceptionMessage(ProcessState requiredState)
     * Throws Runtime Exception Message
     * @param requiredState - State the process is meant to be in
     */
    private void runTimeExceptionMessage(ProcessState requiredState)
    {
        throw new RuntimeException("Process is not in the " + requiredState + "state for correct transition. Actual State: " + processState);
    }
/*
    private void stateTransitionMessage(ProcessState newState, int currentTime)
    {
        System.out.println("Process: " + id + " has entered the " + newState + " state. Current Time: " + currentTime);
    }*/

    /**
     * public static Comparator<Process> processIDComparator
     * Compares two processes based on their ID
     */
    public static Comparator<Process> processIdComparator = new Comparator<Process>()
    {
        @Override
        public int compare(Process p1, Process p2)
        {
            return p1.compareTo(p2);
        }
    };

    /**
     * public int compareTo(Process process)
     * COmpares two processes based on their ID
     * @param process - Second process
     * @return - Integer value determining less than, greater than, or equal to
     */
    @Override
    public int compareTo(Process process)
    {
       return this.getId().compareTo(process.getId());
    }
}