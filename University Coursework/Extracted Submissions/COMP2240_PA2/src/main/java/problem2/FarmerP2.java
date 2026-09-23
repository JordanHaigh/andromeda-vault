package problem2;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * FarmerP2.java contains all information for farmers crossing the bridge/s
 * Extends the thread class to implement the run method
 */
public class FarmerP2 extends Thread
{
    private int uniqueID;
    private IslandLocations startIsland;
    private IslandLocations currentIsland;
    private BridgeP2 bridge;


    /**
     * public FarmerP2(IslandLocations startIsland, int uniqueID, BridgeP2 bridge
     * @param startIsland - Starting island of farmer
     * @param uniqueID - Farmer ID
     * @param bridge - Bridge object to cross
     */
    public FarmerP2(IslandLocations startIsland, int uniqueID, BridgeP2 bridge)
    {
        this.startIsland = startIsland;
        this.uniqueID = uniqueID;
        currentIsland = startIsland;
        this.bridge = bridge;
    }

    /**
     * public boolean isOnNorthIsland()
     * @return - True or false if the farmer's current island is the north island
     */
    public boolean isOnNorthIsland() { return currentIsland == IslandLocations.N; }

    /**
     * public boolean isOnSouthIsland()
     * @return - True or false if the farmers current island is the south island
     */
    public boolean isOnSouthIsland() { return currentIsland == IslandLocations.S; }

    /**
     * public String getStartIsland()
     * @return - String depending if the starting island is North "North" or "South"
     */
    public String getStartIsland()
    {
        return (startIsland.equals(IslandLocations.N)) ? "North" : "South";
    }

    /**
     * public String determineNextIsland()
     * @return - Returns a string of the island the farmer will visit next (Hopefully if he finds a partner)
     */
    public String determineNextIsland() { return isOnNorthIsland() ? "South" : "North"; }

    /**
     * public void travelNorth()
     * If the current farmer is on the south island, his current island will change to the North island
     */
    public void travelNorth()
    {
        if(isOnSouthIsland())
        {
            currentIsland = IslandLocations.N;
        }
        else
            throw new IllegalStateException("Trying to send FarmerP1 north when already on North Island");
    }

    /**
     * public void travelSouth()
     * If the current farmer is on the north island, his current island will change to the South island
     */
    public void travelSouth()
    {
        if(isOnNorthIsland())
        {
            currentIsland = IslandLocations.S;
        }
        else
            throw new IllegalStateException("Trying to send FarmerP1 South when already on South Island");

    }


    /**
     * public String getFarmerName()
     * @return - String containing the starting island of the farmer and his unique ID [N|S] _Farmer[ID]
     */
    public String getFarmerName() { return startIsland + "_Farmer" + uniqueID; }

    /**
     * public String toString
     * Overridden method that calls the Farmer's Name
     * @return - Farmer Name
     */
    @Override
    public String toString() { return getFarmerName(); }

    /**
     * public void run()
     * Overridden method from Thread class
     * Calls upon the bridge class's run method to start running across the bridge
     */
    @Override
    public void run()
    {
        bridge.run(this);

    }
}
