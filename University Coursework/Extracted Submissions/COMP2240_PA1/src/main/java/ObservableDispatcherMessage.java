/**
 * Student Number: 3256730 Jordan Haigh
 * COMP2240 A1
 * ObservableDispatcherMessage.java extends from the abstract ObservableMessage class
 * This class monitors what the dispatcher time will be when read from the data file.
 * This is later fed back to c3256730.java, utilising the observer pattern
 */
public class ObservableDispatcherMessage extends ObservableMessage
{
    private int dispatcherTime;

    public ObservableDispatcherMessage(int dispatcherTime) {this.dispatcherTime = dispatcherTime;}

    /**
     * public int getDispatcherTime()
     * @return - Dispatcher time found from the data file
     */
    public int getDispatcherTime() {return dispatcherTime; }
}
