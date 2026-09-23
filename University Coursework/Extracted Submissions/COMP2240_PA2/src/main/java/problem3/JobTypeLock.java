package problem3;

import java.util.concurrent.Semaphore;


/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * JobTypeLock.java is used to lock and unlock a type of job that is running on the printer
 *
 * This class models the LightSwitch Class that is found in the Little Book of Semaphores
 * Page 70
 */
public class JobTypeLock
{
    int counter = 0;
    Semaphore mutex = new Semaphore(1);

    /**
     * public void lock(Semaphore semaphore)
     * Utilises mutual exclusion so that the counter can be incremented by  1 (If there are available permits in the mutex)
     * If the counter is equal to 1, it will acquire a permit for the parameter semaphore (Allowing only that semaphore to accept jobs)
     * @param semaphore - Semaphore of the job type
     */
    public void lock(Semaphore semaphore)
    {
        try {mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

            counter += 1;
            if(counter == 1)
                try { semaphore.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        mutex.release();
    }


    /**
     * public void lock(Semaphore semaphore)
     * Utilises mutual exclusion so that the counter can be decremented by 1 (If there are available permits in the mutex)
     * If the counter is equal to 0, it will release the permits for the parameter
     * @param semaphore - Semaphore of the job type
     */
    public void unlock(Semaphore semaphore)
    {

        try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
            counter -= 1;
            if(counter == 0)
                semaphore.release();
        mutex.release();
    }
}