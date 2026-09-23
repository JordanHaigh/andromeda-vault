/**
 * class name: StationHeap
 * student nos: c3256741 && c3256730
 * purpose: container class for station information. acts as a Node in the graph structure
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: Station
 * Student Numbers: c3256741 && c3256730 - Kyle Fennell and Jordan Haigh
 * Purpose of Class: This class is the main data structure for the assignment. Each station has a List of station edges,
 * as well as the line it is on, and other variables to assist in the graph traversal.
 */
public class Station implements Comparable<Station>{


    private String name;
    private String line;
    private List<StationEdge> stationEdges = new ArrayList<>();
    private int noOfChanges = 0;
    private int pathLength = Integer.MAX_VALUE;
    private Station prevStation = null;
    public static boolean preferTime = true;
    private ArrayList<String[]> lineChanges;
    private boolean visited = false;


    public Station(String name, String line, List<StationEdge> stationEdges) {
        this.name = name;
        this.line = line;
        this.stationEdges = stationEdges;
        this.lineChanges = new ArrayList<>();
    }

    public String getName() {return name; }
    public String getLine() { return line; }
    public ArrayList<String[]> getLineChanges() { return lineChanges; }
    public int getNoOfChanges() { return noOfChanges; }
    public int getPathLength() { return pathLength; }
    public Station getPrevStation() { return prevStation; }
    public List<StationEdge> getStationEdges() { return stationEdges; }

    public void setVisited(boolean visited) { this.visited = visited; }
    public void setLineChanges(ArrayList<String[]> lineChanges) { this.lineChanges = lineChanges; }
    public void setNoOfChanges(int noOfChanges) { this.noOfChanges = noOfChanges; }
    public void setPathLength(int pathLength) { this.pathLength = pathLength; }
    public void setPrevStation(Station prevStation) { this.prevStation = prevStation; }

    public boolean isVisited() { return visited; }

    /**
     * Prints the path of the station along its line
     * Continues till null value reached (End of line)
     */
    public void printPath(){
        Station crnt = this;
        while(crnt.getPrevStation() != null){
            System.out.println(crnt);
            crnt = crnt.getPrevStation();
        }
    }

    @Override
    public String toString() {
        return name + "(" + line + ") " + pathLength + " " + noOfChanges + " " + ((prevStation == null)? " no previous station" : (prevStation.getName() + "(" + prevStation.getLine() + ")"));
    }


    /**
     * Compares a station to another station based on either time taken to get the each one so far
     * or by number of line changes so far based on a boolean set on program start
     * @param other - Second station for comparison
     * @return - Integer value denoting -1 or 1 for comparison
     */
    @Override
    public int compareTo(Station other) {
        int pref1this = this.noOfChanges;
        int pref1other = other.noOfChanges;
        int pref2this = this.pathLength;
        int pref2other = other.pathLength;
        if (preferTime) {
            pref1this = this.pathLength;
            pref1other = other.pathLength;
            pref2this = this.noOfChanges;
            pref2other = other.noOfChanges;
        }
        if (pref1this < pref1other) {
            return 1;
        } else if (pref1this > pref1other) {
            return -1;
        } else {
            return (pref2this < pref2other) ? 1 : -1;
        }
    }
}
