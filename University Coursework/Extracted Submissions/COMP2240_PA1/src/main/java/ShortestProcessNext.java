import java.util.List;
/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * ShortestProcessNext.java is one of the scheduling algorithms used for the CPU
 * The process runs for its entire duration without interruption, selecting the next process with the shortest remaining time
 * Ignores priority completely
 */
public class ShortestProcessNext implements ISchedulingAlgorithm
{
    public StringBuilder sb = new StringBuilder("SPN:\n");

    /**
     * public void runProcess(Process process, CPU cpu)
     * Runs the dispatcher first to ready the process. Process is run till it is completed.
     * Stringbuilder appends the time the process starts relevant to the specification
     * @param process - Process to run on the cpu
     * @param cpu - CPU instance
     */
    @Override
    public void runProcess(Process process, CPU cpu) {
        cpu.runDispatcher();

        process.admit(cpu.getCurrentTime());
        process.dispatch(cpu.getCurrentTime());
        sb.append("T")
                .append(cpu.getCurrentTime())
                .append(": ")
                .append(process.getId())
                .append("(")
                .append(process.getPriority())
                .append(")")
                .append("\n");

        cpu.performProcessing(process,timeRequiredToRunNextProcess(process));
        process.exit(cpu.getCurrentTime());
    }

    /**
     * public Process nextProcessToRun(List<Process> processList)
     * Decides which process is to run on the cpu next. Searches through the current process list to determine the process
     * with the shortest remaining time to run next
     * @param processList - Process list containing all processes ready to be run on the cpu
     * @return - Process to run next
     */
    @Override
    public Process nextProcessToRun(List<Process> processList) {

        if(processList.isEmpty())
            return null;

        Process nextProcess = processList.get(0); //First process in the list - may be reassigned later

        for(Process process : processList)
        {
            int remainingServiceTimeResult = determineRemainingServiceTime(process, nextProcess);
            //int priorityResult = determinePriority(process, nextProcess);
            int idResult = process.getId().compareTo(nextProcess.getId());

            if(remainingServiceTimeResult < 0)
                nextProcess = process;

            if(remainingServiceTimeResult == 0)
            {
                //Processes have equal remaining time
                if(idResult < 0)
                    process = nextProcess; //Found the next process to run based on its identity
            }
        }
        return nextProcess;
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
     * public String toString()
     * @return - SPN data in toString format as per specification
     */
    @Override
    public String toString()
    {
        return sb.toString();
    }
}
