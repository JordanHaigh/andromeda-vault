import java.util.List;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * PreemptivePriority.java is one of the scheduling algorithms used for the CPU
 * This algorithm utilises the IPreemptiveSchedulingAlgorithm interface, allowing for preemption if
 * another process arrives with a greater priority
 */
public class PreemptivePriority implements IPreemptiveSchedulingAlgorithm
{
    private Process previousProcess;
    private boolean isPreempted = false;
    private StringBuilder sb = new StringBuilder("PP: \n");

    /**
     * public void runProcess(Process process, CPU cpu)
     * Runs the dispatcher first to ready the process. Process is run till it is completed or until another process with
     * higher priority takes over
     * Stringbuilder appends the time the process starts relevant to the specification
     * @param process - Process to run on the cpu
     * @param cpu - CPU instance
     *
     */
    @Override
    public void runProcess(Process process, CPU cpu) {
        isPreempted = false;

        if(process.isNew())
            process.admit(cpu.getCurrentTime());

        if(process != previousProcess)
        {
            cpu.runDispatcher();
            sb.append("T")
                    .append(cpu.getCurrentTime())
                    .append(": ")
                    .append(process.getId())
                    .append("(")
                    .append(process.getPriority())
                    .append(")")
                    .append("\n");
        }

        process.dispatch(cpu.getCurrentTime());

        //cpu.performProcessing(process,timeRequiredToRunNextProcess(process));
        previousProcess = process;

        do
        {
            cpu.performProcessing(process,1);
        }while(!isPreempted && !process.isFinishedCycling());

        if(isPreempted && !process.isFinishedCycling())
            process.interrupt(cpu.getCurrentTime());
        else
            process.exit(cpu.getCurrentTime());
    }

    /**
     * public Process nextProcessToRun(List<Process> processList)
     * Decides which process will run on the cpu next
     * @param processList - Process list containing all processes ready to be run on the cpu
     * @return - Process to run next
     */
    @Override
    public Process nextProcessToRun(List<Process> processList) {
        if(processList.isEmpty())
            return null;

        Process nextProcess = processList.get(0);

        for(Process process: processList)
        {
            int priorityResult = determinePriority(process, nextProcess);
            int remainingServiceTimeResult = determineRemainingServiceTime(process, nextProcess);
            int idResult = process.getId().compareTo(nextProcess.getId());

            if(priorityResult < 0)
                nextProcess = process;

            if(priorityResult == 0)
            {
                //Processes have equal priority
                if(remainingServiceTimeResult < 0)
                    nextProcess = process;

                if(remainingServiceTimeResult == 0)
                {
                    //Processes have equal priority
                    if(idResult < 0)
                        nextProcess = process; //Found a process with the same priority, same runtime, but higher naming convention
                }
            }
        }
        return nextProcess;
    }

    /**
     * public int timeRequiredToRunNextProcess(Process process)
     * Determines the time required to run the next process
     * @param process - Process that will run on the cpu
     * @return - Integer determining time length
     */
    @Override
    public int timeRequiredToRunNextProcess(Process process) {
        return process.getRemainingServiceTime();
    }

    /**
     * public void preemptCPU(CPU cpu, Process currentProcess, List<Process> processList)
     * Determines what the next process would be to run on the cpu after the current process.
     * The CPU will be preempted if the next process doesn't equal the current process
     * @param cpu - Current CPU
     * @param currentProcess - Current process running on the CPU
     * @param processList - Process list in the CPU
     */
    @Override
    public void preemptCPU(CPU cpu, Process currentProcess, List<Process> processList) {
        Process nextProcess = nextProcessToRun(processList);

        if(!nextProcess.equals(currentProcess))
        {
            isPreempted = true;
        }
    }

    /**
     * public boolean isPreempted()
     * @return - True or false if preempted
     */
    @Override
    public boolean isPreempted() {
        return isPreempted;
    }


    /**
     * private int determineRemainingServiceTime(Process process, Process otherProcess)
     * Determines whether the first process has a shorter remaining time than the second process
     * @param process - First process
     * @param otherProcess - Second process
     * @return - Integer determining less than, greater than or equal to
     */
    private int determineRemainingServiceTime(Process process, Process otherProcess)
    {
        if(process.getRemainingServiceTime() < otherProcess.getRemainingServiceTime())
            return -1;
        if(process.getRemainingServiceTime() > otherProcess.getRemainingServiceTime())
            return 1;
        else
            return 0;
    }

    /**
     * private int determinePriority(Process process, Process nextProcess)
     * Determines whether the first process has a higher priority other the second process
     * @param process - First process
     * @param nextProcess - Second process
     * @return - Integer determining less than, greater than or equal to
     */
    private int determinePriority(Process process,Process nextProcess)
    {
        if(process.getPriority() < nextProcess.getPriority())
            return -1;
        if(process.getPriority() > nextProcess.getPriority())
            return 1;
        else
            return 0;
    }

    /**
     * public String toString()
     * @return - PP data in toString format as per specification
     */
    @Override
    public String toString()
    {
        return sb.toString();
    }

}
