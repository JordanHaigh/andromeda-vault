/**
 * Class name: StationHeap
 * Student Numbers: c3256741 && c3256730 - Kyle Fennell and Jordan Haigh
 * Purpose of Class: This is a container class for the StationEdges. It acts as an Edge in the graph structure
 */
public class StationEdge{
    private String name;
    private String line;
    private int duration;
    private Station station;

    public StationEdge(String name, String line, int duration) {
        this.name = name;
        this.line = line;
        this.duration = duration;
        this.station = null;
    }

    public String getName() { return name; }
    public String getLine() { return line; }
    public int getDuration() { return duration; }
    public Station getStation() { return station; }

    public void setStation(Station station){
        if (this.station == null){
            this.station = station;
        }
    }

}
