package problem2;

import java.util.concurrent.Semaphore;


/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * BridgeP2.java contains all semaphores required for this problem, including turnstiles and mutual exclusion
 * Semaphores enforce the mutual exclusion
 * Includes neon counters used for printing out the number of farmers who have crossed
 * Farmers must cross in groups of two, otherwise they will wait forever on their island
 *
 * Bridge class models the Reusable Barrier Design found in the Little Book of Semaphores recommended by the lecturer.
 * Page 41.
 */
public class BridgeP2
{

    private static int neon = 0;

    //Two types of turnstiles for the North Island and South Island
    private static Semaphore turnstile1N = new Semaphore(0);
    private static Semaphore turnstile2N = new Semaphore(1);

    private static Semaphore turnstile1S = new Semaphore(0);
    private static Semaphore turnstile2S = new Semaphore(1);

    private static Semaphore mutex = new Semaphore(1);
    private static int countN = 0;
    private static int countS = 0;
    private static final int NUMBEROFFARMERSTOCROSS = 2;

    //Two types of semaphores (Island Permits) for north farmers and south farmers
    private static Semaphore northFarmersCrossing = new Semaphore(NUMBEROFFARMERSTOCROSS);
    private static Semaphore southFarmersCrossing = new Semaphore(NUMBEROFFARMERSTOCROSS);

    //Three bridge permits are distributed so that one islandSemaphore will always have more tickets than the other
    //I.e N = 2 and S = 1, OR N=1 and S=2
    private static Semaphore bridgePermits = new Semaphore(NUMBEROFFARMERSTOCROSS+1);

    /**
     * public void run(FarmerP2 farmerP2)
     * Starting point for all Farmer threads
     * They will attempt to acquire a bridge permit if there are resources available (Sleep if none available)
     * Continues on with the respectful islandSemaphore and turnstiles.
     * @param farmerP2 - Farmer thread wanting  to run on the bridge
     */
    public void run(FarmerP2 farmerP2)
    {
        //Help acquired from the Little Book of Semaphores - Reusable Barrier Solution
        //Page 41 of the Little Book of Semaphores

        //Only running once, don't need a while true block
        System.out.println(farmerP2.getFarmerName() + ": Waiting on Bridge. Going towards " + (farmerP2.isOnNorthIsland() ? "South" : "North"));


        //If there are bridge permits available, hand out a bridge permit to the current farmer
        try { bridgePermits.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        //System.out.println(this.getFarmerName() + ": Has acquired a bridge permit");


        if(farmerP2.isOnNorthIsland())
            runIslandFarmer(northFarmersCrossing, turnstile1N, turnstile2N, farmerP2);
        else
            runIslandFarmer(southFarmersCrossing, turnstile1S, turnstile2S, farmerP2);
    }


    /**
     * private void runIslandFarmer(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
     * Generic method for running the farmer with his respectful island semaphores.
     * Farmer will move through the three main methods to get across the bridge
     * @param islandCrossing - Specific Island Semaphore, depending on whether the farmer is on the north island or south
     * @param turnstile1 - Specific Turnstile1, depending on whether the farmer in on the north island or south island
     * @param turnstile2 - Specific Turnstile2, depending on whether the farmer is on the north island or south island
     * @param farmerP2 - Farmer trying to cross the bridge
     */
    private void runIslandFarmer(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
    {
        acquireBridgeAndIslandPermits(islandCrossing, turnstile1, turnstile2, farmerP2);

        runAcrossBridge(islandCrossing, turnstile1, turnstile2, farmerP2);

        releaseBridgeAndIslandPermits(islandCrossing, turnstile1, turnstile2, farmerP2);
    }

    /**
     * private void acquireBridgeAndIslandPermits
     * Depending on what island the farmer originates, the program will attempt to acquire an island ticket for the farmer
     * to cross. If successful, mututal exclusion barriers are enforces so that the counter for the Farmer's island is incremented
     * If the counter for the current island matches with the correct number of farmers to cross, it will flip the turnstiles
     * and allow for the two threads to run across the bridge
     * @param islandCrossing - Specific Island Semaphore, depending on whether the farmer is on the north island or south
     * @param turnstile1 - Specific Turnstile1, depending on whether the farmer in on the north island or south island
     * @param turnstile2 - Specific Turnstile2, depending on whether the farmer is on the north island or south island
     * @param farmerP2 - Farmer trying to cross the bridge
     */
    private void acquireBridgeAndIslandPermits(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
    {
        //See if there are available resources on the current island to allow this farmer to cross
        // If no remaining resources for this island, the farmer will wait at this section
        try { islandCrossing.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        //System.out.println(this.getFarmerName() + ": Has acquired an island permit");

        //Mutual Exclusion Barrier
        //Side Note: The Double curly braces was to help me with showing the section inside the mutex
        try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace();  }
        {{
            //System.out.println(this.getFarmerName() + ": Acquired Mutex");

            //Increment the counter for the number of farmers currently running on this island

            synchronized (this)
            {
                if(farmerP2.isOnNorthIsland())
                    countN++;
                else
                    countS++;
            }

            if(this.getCounter(farmerP2) == NUMBEROFFARMERSTOCROSS)
            {
                //System.out.println(this.getFarmerName() + ": Count == 2");

                //Lock the second turnstile and unlock the first turnstile
                //Allows for flip in turnstiles so that only one turnstile is in action at a time
                try { turnstile2.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
                //System.out.println(this.getFarmerName() + ": Acquired Turnstile2");

                turnstile1.release();
                //System.out.println(this.getFarmerName() + ": Released Turnstile1");

            }

        }}
        mutex.release();
        //System.out.println(this.getFarmerName() + ": Releasing Mutex");


        //Acquire and release block allows for only one thread to pass through to the next section at a time
        try { turnstile1.acquire(); } catch (InterruptedException e) { e.printStackTrace(); } //First turnstile
        turnstile1.release();




    }

    /**
     * private void runAcrossBridge(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
     * Critical section for the bridge
     * Farmer steps across the bridge and updates the neon sign once across
     * @param islandCrossing - Specific Island Semaphore, depending on whether the farmer is on the north island or south
     * @param turnstile1 - Specific Turnstile1, depending on whether the farmer in on the north island or south island
     * @param turnstile2 - Specific Turnstile2, depending on whether the farmer is on the north island or south island
     * @param farmerP2 - Farmer trying to cross the bridge
     */
    private void runAcrossBridge(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
    {
        //Critical Section
        ////////////////////////////////////////////////////////

        //once all bridge permits have been acquired
        //then we can start to cross the bridge, depending on the island that has more permits
        //cross bridge
        for(int i = 5; i < 20; i+= 5)
            System.out.println(farmerP2.getFarmerName() + ": Crossing the bridge. Step " + i);
        System.out.println(farmerP2.getFarmerName() + ": Across the bridge");

        //Synchronise all instances of Farmer so that the neon is updated across all of them
        synchronized (this)
        {
            neon++;
            System.out.println("NEON=" + neon);
        }
        /////////////////////////////////////////////////////////
        //End Critical Section
    }

    /**
     * public void releaseBridgeAndIslandPermits(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
     * Mututal Exclusion is enforced to decrement a farmer from the current island counter
     * Once the counter reaches zero (I.e no farmers on the bridge) the turnstiles will flip again
     * All appropriate bridge and island permits will also be released for the next group of farmers
     * @param islandCrossing - Specific Island Semaphore, depending on whether the farmer is on the north island or south
     * @param turnstile1 - Specific Turnstile1, depending on whether the farmer in on the north island or south island
     * @param turnstile2 - Specific Turnstile2, depending on whether the farmer is on the north island or south island
     * @param farmerP2 - Farmer trying to cross the bridge
     */
    public void releaseBridgeAndIslandPermits(Semaphore islandCrossing, Semaphore turnstile1, Semaphore turnstile2, FarmerP2 farmerP2)
    {
        try { mutex.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
        {{
            //System.out.println(this.getFarmerName() + ": Acquired Mutex");

            //Decrement the number of threads currently running


            synchronized (this)
            {
                if(farmerP2.isOnNorthIsland())
                    countN--;
                else
                    countS--;
            }


            if(this.getCounter(farmerP2) == 0) //No more threads running
            {
                //Flip the turnstiles
                //Lock the first turnstile and unlock the second
                try { turnstile1.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }
                turnstile2.release();
            }

        }}
        mutex.release();
        //System.out.println(this.getFarmerName() + ": Released Mutex");

        //Acquire and release block allows for only one thread to pass through to the next section at a time
        try { turnstile2.acquire(); } catch (InterruptedException e) { e.printStackTrace(); } //Second turnstile
        turnstile2.release();


        //Release bridge permits
        bridgePermits.release();
        //System.out.println(this.getFarmerName() + ": Released Bridge Permit");

        //Release island permits
        islandCrossing.release();
        //System.out.println(this.getFarmerName() + ": Released Island Permit");

    }

    /**
     * private synchronized int getCounter(FarmerP2 farmerP2)
     * Returns the counter for the respective island
     * @param farmerP2 - Farmer used to determine island counter
     * @return - Counter respective to the island of the farmer parameter
     */
    private synchronized int getCounter(FarmerP2 farmerP2)
    {
        if(farmerP2.isOnNorthIsland())
            return countN;
        else
            return countS;
    }

}
