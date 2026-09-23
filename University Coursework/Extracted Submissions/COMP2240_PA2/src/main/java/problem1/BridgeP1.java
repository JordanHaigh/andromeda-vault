package problem1;

import java.util.concurrent.Semaphore;


/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * BridgeP1.java is used by Farmers to cross to the opposite island
 * Enforces mutual exclusion and non starvation allowing all farmers to cross the bridge
 */
public class BridgeP1
{
    private Semaphore semaphore;
    private int neonSign = 0;
    private static final int STEPS = 20;


    /**
     * public BridgeP1(int permits)
     * Overloaded Constructor
     * @param permits - Number of permits allowed for this bridge (In this case 1)
     */
    public BridgeP1(int permits)
    {
        semaphore = new Semaphore(permits);
    }

    /**
     * public void enterBridge(FarmerP1 farmer)
     * Attempt to acquire the semaphore to allow for the farmer to cross the bridge
     * @param farmer - Farmer object attempting to cross the bridge
     */
    public void enterBridge(FarmerP1 farmer)
    {
        //Attempt to acquire the semaphore
        try { semaphore.acquire(1); }  catch(InterruptedException e) { System.err.println(e); }
    }

    /**
     * public void crossBridge(FarmerP1 farmer)
     * Farmer parameter crosses the bridge. Sleeps for half a second during each stepping stage. See attention note
     * @param farmer - Farmer object crossing the bridge
     */
    public void crossBridge(FarmerP1 farmer)
    {
        for(int i = 5; i < STEPS; i+=5)
        {
            System.out.println(farmer.getFarmerName() + ": Crossing the Bridge. Step " + i);
            //System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getFarmerName() + ": Crossing bridge");


            /*ATTENTION - This Thread.Sleep section is used for the marker's benefit of seeing the treads stepping
            * across the bridge. Without this sleep method it would be very difficult to trace how each farmer is going
            * with the bridge crossing. It is not mentioned in the specification that we are forbidden to use Thread.Sleep()
            */
            try { Thread.sleep(500); } catch (InterruptedException e) { }
        }

    }

    /**
     * public void exitBridge(FarmerP1 farmer)
     * Farmer is now across the bridge. Farmer current island is updated as well as the neon sign
     * Semaphore is released for the next farmer thread to access it and cross
     * @param farmer - Farmer object that has crossed the bridge
     */
    public void exitBridge(FarmerP1 farmer)
    {
        System.out.println(farmer.getFarmerName() + ": Across the bridge");

        //Change island status
        if(farmer.isOnNorthIsland())
            farmer.travelSouth();
        else
            farmer.travelNorth();

        //Update neon sign
        neonSign++;
        System.out.println("NEON = " + neonSign);

        //Release semaphore
        semaphore.release(1);
    }


}