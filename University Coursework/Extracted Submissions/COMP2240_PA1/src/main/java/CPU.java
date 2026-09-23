import java.util.ArrayList;
import java.util.List;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * CPU.java is used for executing a process for a period of time.
 * Depending on the scheduling algorithm used for the cpu, it can be preempted in the event of a higher priority process
 */
public class CPU implements IObservable
{
    private ISchedulingAlgorithm schedulingAlgorithm;
    private int currentTime;
    private List<ISubscriber> subscribers = new ArrayList<>();
    private List<Process> processList = new ArrayList<>();
    private List<Process> completedProcessList = new ArrayList<>();
    private Process currentProcess;
    private int dispatcherTime;
    private boolean isRunningDispatcher = false; //Used to determine if dispatcher has been run for the current process

    public CPU(ISchedulingAlgorithm schedulingAlgorithm, int dispatcherTime)
    {
        this.schedulingAlgorithm = schedulingAlgorithm;
        this.dispatcherTime = dispatcherTime;
        currentTime = 0;
    }

    /**
     * public int getCurrentTime()
     * @return - Current time
     */
    public int getCurrentTime() { return currentTime; }

    /**
     * public List<Process> getCompletedProcessList()
     * @return - Completed Process List
     */
    public List<Process> getCompletedProcessList() {return completedProcessList; }

    /**
     * public void cycle()
     * Determines the next process that has arrived, to run on the cpu, depending on the algorithm
     * If there are no processes found, the cpu will force a time tick update.
     */
    public void cycle()
    {
        //System.out.println("=====Beginning New Cycle. Current Time: " + currentTime);

        Process process = schedulingAlgorithm.nextProcessToRun(processList);

        if(process != null)
            schedulingAlgorithm.runProcess(process, this);
        else
        {
            //No processes to run. Currently idling
            updateTimeTick(1);
           // System.out.println("Forced time increment. Current time now: " + currentTime);

        }
    }

    /**
     * public void performProcessing(Process process, int numberOfCycles)
     * Run the process on the cpu for a specified period of time
     * Once completed, it will be added to a 'Completed Process List' for data statistics
     * If the process does not complete processing within the allocated number of cycles, it is sent to the back
     * of the process list.
     * @param process - Current process that will run on the CPU
     * @param numberOfCycles - Length of time the process runs for
     */
    public void performProcessing(Process process, int numberOfCycles)
    {
        this.currentProcess = process; //Used in determining if CPU is running if we need to preempt

        for(int i = 0; i < numberOfCycles; i++)
        {
            process.run();
            updateTimeTick(1);
        }

        if(process.getRemainingServiceTime() == 0)
        {
            processList.remove(process);
            completedProcessList.add(process);
        }

        else //In the event of round robin
        {
            processList.remove(process);
            processList.add(process); //Add to back of the process list
        }

    }

    /**
     * public boolean hasQueuedProcesses()
     * @return - True or false if the process list size is greater than zero
     */
    public boolean hasQueuedProcesses() { return processList.size() > 0; }

    /**
     * public void runDispatcher()
     * Updates the time tick specified by the dispatcher time
     * Simulates dispatcher readying process for CPU
     */
    public void runDispatcher()
    {
        isRunningDispatcher = true;
        updateTimeTick(dispatcherTime);

    }

    /**
     * public void updateTimeTick(int timeIncrement)
     * Updates the current time by the specified increment
     * @param timeIncrement - Time increment to update current time
     */
    private void updateTimeTick(int timeIncrement)
    {
        currentTime += timeIncrement;

        if(timeIncrement == dispatcherTime && isRunningDispatcher)
        {
            //System.out.println("Dispatcher has readied process. Current Time now: " + currentTime);
            isRunningDispatcher = false;
        }

        ObservableMessage message = new ObservableCPUTimeMessage(currentTime);
        notifySubscribers(message);
    }

    /**
     * public void feedProcess(Process process)
     * Process is added to the CPU's process list from the main program once the process arrival time matches with the
     * current time.
     * @param process - Process to be added to the list
     */
    public void feedProcess(Process process)
    {
        processList.add(process);

        if(schedulingAlgorithm instanceof IPreemptiveSchedulingAlgorithm && currentProcess != null)
        {
            ((IPreemptiveSchedulingAlgorithm) schedulingAlgorithm).preemptCPU(this, currentProcess, processList);
        }
    }

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