import java.util.List;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * IPreemptiveSchedulingAlgorithm.java extends from the ISchedulingAlgorithm to include methods for preemptive algorithms
 */
public interface IPreemptiveSchedulingAlgorithm extends ISchedulingAlgorithm
{
    void preemptCPU(CPU cpu, Process currentProcess, List<Process> processList); //Stops current process and send it to the back of the process list
    boolean isPreempted(); //Determines if process has been preempted
}
