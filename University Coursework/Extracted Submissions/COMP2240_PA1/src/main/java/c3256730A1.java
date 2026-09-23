import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * c3256730A1.java acts as the Entry point to the program.
 * The program required a data input file in order to run
 */
public class c3256730A1 implements ISubscriber
{
    private ISchedulingAlgorithm firstComeFirstServe = new FirstComeFirstServe();
    private ISchedulingAlgorithm shortestProcessNext = new ShortestProcessNext();
    private ISchedulingAlgorithm preemptivePriority = new PreemptivePriority();
    private ISchedulingAlgorithm priorityRoundRobin = new PriorityRoundRobin();
    private List<ISchedulingAlgorithm> schedulingAlgorithms = new ArrayList<>();

    private CPU cpu;
    private List<Process> copiedList = new ArrayList<>();

    private int currentTime;
    private int dispatcher;

    private double[] averageTurnaroundTime = new double[4];
    private double[] averageWaitingTime = new double[4];
    private int currentIndex = 0;

    private StringBuilder sb = new StringBuilder();

    public static void main(String[]args)
    {
        c3256730A1 program = new c3256730A1();
        program.run(args);
    }


    /**
     * private void run(String[]args)
     * Reads full file input and creates a list of the processes gathered from the file stored in masterList
     * For each scheduling algorithm, a copied list is created to use the data for each algorithm.
     * Pri
     * @param args - Data file specified from the command line
     */
    private void run(String[]args)
    {
        List<Process> masterProcessList = readFileInput(args);


        schedulingAlgorithms.add(firstComeFirstServe);
        schedulingAlgorithms.add(shortestProcessNext);
        schedulingAlgorithms.add(preemptivePriority);
        schedulingAlgorithms.add(priorityRoundRobin);

        for(ISchedulingAlgorithm currentAlgorithm: schedulingAlgorithms)
        {
            for(Process process: masterProcessList)
                copiedList.add(new Process(process));

            cpu = new CPU(currentAlgorithm, dispatcher);
            //System.out.println("\n===A new CPU has been created\n");
            cpu.addSubscriber(this);
            currentTime = 0; //Reset for each algorithm


            while(cpu.hasQueuedProcesses() || copiedList.size() > 0)
            {
                feedProcesses();
                cpu.cycle();
                //System.out.println("Current Time: " + currentTime);

            }

            individualAlgorithmDataStatistics(currentAlgorithm);
        }

        finalDataStatistics();
    }

    /**
     * private void individualAlgorithmDataStatistics(ISchedulingAlgorithm currentAlgorithm)
     * Appends to algorithm's data output to the the main StringBuilder of the class.
     * @param currentAlgorithm - Current Algorithm being run(FCFS,SPN,PP,PRR)
     */
    private void individualAlgorithmDataStatistics(ISchedulingAlgorithm currentAlgorithm)
    {
        //Data stats
        List<Process> completedProcesses = cpu.getCompletedProcessList();

        sb.append(currentAlgorithm.toString());
        sb.append("\nProcess  Turnaround Time  Waiting Time\n");
        double totalTurnaroundTime = 0.0;
        double totalWaitingTime = 0.0;
        int numberOfProcesses = 0;

        List<Process> sortedList = getprocessListSorted(completedProcesses);

        for(Process process: sortedList)
        {
            sb.append(process.getId())
                    .append("\t\t ")
                    .append(process.getTurnaroundTime())
                    .append("\t\t\t\t  ")
                    .append(process.getWaitingTime())
                    .append("\n");
            totalTurnaroundTime += process.getTurnaroundTime();
            totalWaitingTime += process.getWaitingTime();
            numberOfProcesses++;
        }
        sb.append("\n");

        averageTurnaroundTime[currentIndex] = totalTurnaroundTime/numberOfProcesses;
        averageWaitingTime[currentIndex] = totalWaitingTime/numberOfProcesses;
        currentIndex++;
    }

    /**
     * private void finalDataStatistics()
     * Appends the overall data statistics to the StringBuiler in the class
     */
    private void finalDataStatistics()
    {
        sb.append("Summary \n")
                .append("Algorithm \t Average Turnaround Time \t Average Waiting Time \n");
        sb.append(String.format("FCFS \t\t\t %.2f \t\t\t\t\t\t %.2f \n", averageTurnaroundTime[0], averageWaitingTime[0]));
        sb.append(String.format("SPN \t\t\t %.2f \t\t\t\t\t\t %.2f \n", averageTurnaroundTime[1], averageWaitingTime[1]));
        sb.append(String.format("PP \t\t\t\t %.2f \t\t\t\t\t\t %.2f \n", averageTurnaroundTime[2], averageWaitingTime[2]));
        sb.append(String.format("PRR \t\t\t %.2f \t\t\t\t\t\t %.2f \n", averageTurnaroundTime[3], averageWaitingTime[3]));

        //Print all results and statistics
        System.out.println();
        System.out.println(sb.toString());

    }

    /**
     * private List<Process> getProcessListSorted(List<Process> processList)
     * Sorts the list based on ID using a comparator
     * @param processList - Unsorted process list
     * @return - List<Process> type of sorted processes
     */
    public List<Process> getprocessListSorted(List<Process> processList)
    {
        List<Process> sortedProcesses = new ArrayList<Process>(processList);
        Collections.sort(sortedProcesses, Process.processIdComparator);
        return sortedProcesses;
    }

    /**
     * private List<Process> readFileInput(String[]args)
     * Checks the argument path to determine if there is a valid data file to work with.
     * If found, a ProcessFileReader entity is created to read the contents of the file
     * @param args - Arguments from the console
     * @return - List<Process> found from the data file
     */
    private List<Process> readFileInput(String[]args)
    {
        if(args.length < 1)
        {
            System.out.println("Error. File argument was not provided");
            System.exit(0);
        }


        String filePath = args[0];
        List<Process> processList = new ArrayList<>();

        try
        {
            ProcessFileReader reader = new ProcessFileReader();
            reader.addSubscriber(this);

            processList = reader.readProcessesFromFile(filePath);
        }
        catch (Exception e)
        {
            System.out.println("Error. File data could not be loaded. Error: " + e.getMessage());
            System.exit(0);
        }

        return processList;
    }

    /**
     * private void feedProcesses()
     * With each time tick, the method with search through the current list (Copied from the master)
     * It will determine whether there are any processes with the arrival time equalling to the current time
     * To remove these processes, they must be first marked for removal by creating a new list and adding where necessary
     * They can then be safely added to the cpu's ready queue and removed from the copied list
     */
    private void feedProcesses(){
        List<Process> processesMarkedForRemoval = new ArrayList<>();
        //Need to determine which processes can be removed from the list

        for(Process process : copiedList){
            if(process.getArriveTime() <= currentTime){
                processesMarkedForRemoval.add(process);

            }
        }

        if(processesMarkedForRemoval.size() != 0)
        {
            //System.out.println("Found " + processesMarkedForRemoval.size() + " process(es) to be spawned. Current Time: " + currentTime);
            for(Process process : processesMarkedForRemoval){
                cpu.feedProcess(process);
                copiedList.remove(process);
            }
        }
    }


    /**
     * public void handleMessage(ObservableMessage message)
     * Utilises the Observer Pattern to either feed the current time from the cpu to the program
     * Or feed the dispatcher information from the reader class to the program.
     * Essential to the program as it eliminates tight coupling
     * @param message - ObservableMessage that contains relevant time information
     */
    @Override
    public void handleMessage(ObservableMessage message) {
        if(message instanceof ObservableCPUTimeMessage)
        {
            currentTime = ((ObservableCPUTimeMessage) message).getCpuTimeTick();

           //System.out.println("Start handling on tick message. Current Time: " + currentTime);

            feedProcesses();

            //System.out.println("Finished handling on tick message. Current Time: " + currentTime);

        }
        if(message instanceof  ObservableDispatcherMessage)
        {
            this.dispatcher = ((ObservableDispatcherMessage) message).getDispatcherTime();
        }
    }
}