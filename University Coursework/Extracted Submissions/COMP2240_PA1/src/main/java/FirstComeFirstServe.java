import java.util.List;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * FirstComeFirstServe.java is one of the scheduling algorithms used for the CPU
 * Simplest algorithm, where the process runs for its entire duration without interruption
 */
public class FirstComeFirstServe implements ISchedulingAlgorithm
{
    private StringBuilder sb = new StringBuilder("FCFS: \n");

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
        cpu.performProcessing(process, timeRequiredToRunNextProcess(process));
        process.exit(cpu.getCurrentTime());
    }

    /**
     * public Process nextProcessToRun(List<Process> processList)
     * Decides which process is to run on the cpu next
     * @param processList - Process list containing all processes ready to be run on the cpu
     * @return - Process to run next
     */
    @Override
    public Process nextProcessToRun(List<Process> processList) {
        if(processList.isEmpty())
            return null;

        else
            return processList.get(0);
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
     * @return - FCFS data in toString format as per specification
     */
    @Override
    public String toString()
    {
        return sb.toString();
    }
}
