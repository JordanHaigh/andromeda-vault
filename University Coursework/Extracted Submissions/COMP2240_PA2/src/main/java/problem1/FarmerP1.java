package problem1;

/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A2
 * FarmerP1.java contains all information for farmers crossing the bridge/s
 */
public class FarmerP1 extends Thread
{
    private int uniqueID;
    private BridgeP1 bridge;
    private IslandLocations startIsland;
    private IslandLocations currentIsland;

    /**
     * public FarmerP1(IslandLocations startIsland, int uniqueId, BridgeP1 bridge)
     * Default constructor for the FarmerP1 Class
     */
    public FarmerP1(IslandLocations startIsland, int uniqueID, BridgeP1 bridge)
    {
        this.startIsland = startIsland;
        this.uniqueID = uniqueID;
        this.bridge = bridge;
        currentIsland = startIsland;
    }

    /**
     * public boolean isOnNorthIsland()
     * @return - True or false if current island matches with North
     */
    public boolean isOnNorthIsland() { return currentIsland == IslandLocations.N; }

    /**
     * public boolean isOnSouthIsland()
     * @return - True or false if current island matches with South
     */
    public boolean isOnSouthIsland() { return currentIsland == IslandLocations.S; }

    /**
     * public String determineNextIsland()
     * @return - String word whether the farmer is on the south island or the north island
     */
    public String determineNextIsland() { return isOnNorthIsland() ? "South" : "North"; }

    /**
     * public void travelNorth()
     * If the farmer is on the south island, he will change his current island to the north island
     */
    public void travelNorth()
    {
        if(isOnSouthIsland())
            currentIsland = IslandLocations.N;
        else
            throw new IllegalStateException("Trying to send FarmerP1 north when already on North Island");
    }

    /**
     * public void travelSouth()
     * If the farmer is on the north island, he will change his current island to the south island
     */
    public void travelSouth()
    {
        if(isOnNorthIsland())
            currentIsland = IslandLocations.S;
        else
            throw new IllegalStateException("Trying to send FarmerP1 South when already on South Island");

    }


    /**
     * public String getFarmerName()
     * @return - Name of the farmer, his starting island and the unique id [N|S]_Farmer[ID]
     */
    public String getFarmerName() { return startIsland + "_Farmer" + uniqueID; }

    /**
     * public String toString
     * @return - Name of farmer, starting island, unique id
     */
    @Override
    public String toString() { return getFarmerName(); }

    /**
     * public void run()
     * Extended from thread class
     * Farmer attempts to enter, cross and exit bridge
     */
    @Override
    public void run()
    {
        while(true)
        {
            System.out.println(this.getFarmerName() + ": Waiting on Bridge. Going towards " + (isOnNorthIsland() ? "South" : "North"));

            bridge.enterBridge(this);

            bridge.crossBridge(this);

            bridge.exitBridge(this);

        }

    }

}
