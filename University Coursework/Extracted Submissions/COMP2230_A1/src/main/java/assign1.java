/**
 * class name: StationHeap
 * student nos: c3256741 && c3256730
 * purpose: main entry point for the program. controls program flow;
 * input handling; graph traversal and output statements.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Class name: assign1
 * Student Numbers: c3256741 && c3256730 - Kyle Fennell and Jordan Haigh
 * Purpose of class: Main entry point to the program. Program needs to take 3-4 arguments (4th is optional)
 */

public class assign1 {

    private static String startStationString = "", endStationString = "";
    private static Station finalStation = null; //destination station
    private static StationHeap stationHeap = new StationHeap();
    //String is station name concatenated with the station line concat with num of changes

    public static void main(String[] args) {

        /**Pre processing arguments*/
        if (!processProgramArguments(args)) {
            return;
        }

        XMLParser parse = new XMLParser();

        /**Raw stations read straight from xml - no collation, just a really big list**/
        List<Station> allStations = parse.runParser(args[0]);

        if (!findStartingAndTerminatingStations(allStations))
            return;

        /** Main Logic Section */
        //At this point we now have a start node and an end node that we need to get to.
        //We KNOW  that the station heap has one node in it.

        Station currentStation = stationHeap.remove();

        while (!currentStation.getName().equals(finalStation.getName())) {
//            System.out.println(stationHeap);
//            System.out.println("currently processing station: " + currentStation);
            for (StationEdge e : currentStation.getStationEdges()) {
                Station s = e.getStation();
//                System.out.println("processing adjacent station: " + s);
                if (s != currentStation.getPrevStation() && currentStation.getPathLength() + e.getDuration() < s.getPathLength()) {

                    s.setPathLength(currentStation.getPathLength() + e.getDuration());
                    s.setPrevStation(currentStation);
                    s.setLineChanges(currentStation.getLineChanges());
                    s.setNoOfChanges(currentStation.getNoOfChanges());
//                    System.out.println("stationDifferences? "+currentStation.getLine()+" "+s.getLine());
                    if (!currentStation.getLine().equals(s.getLine())) {
                        s.setNoOfChanges(s.getNoOfChanges() + 1);
                        ArrayList<String[]> listOfAllLineChanges = new ArrayList<>(currentStation.getLineChanges());
                        listOfAllLineChanges.add(new String[]{currentStation.getLine(), currentStation.getName()});
                        s.setLineChanges(listOfAllLineChanges);
                    }
                }
                if (!s.isVisited()){
//                    System.out.println("adding station "+s+" to the heap");
                    s.setVisited(true);
                    stationHeap.add(s);
                }
            }
            stationHeap.heapify();
            currentStation = stationHeap.remove();
        }

//        System.out.println(stationHeap);

        /** Printout */
        ArrayList<String[]> listOfAllLineChanges = new ArrayList<>(currentStation.getLineChanges());
        listOfAllLineChanges.add(new String[]{currentStation.getLine(), currentStation.getName()});

        String[] sArray = listOfAllLineChanges.get(0);
        System.out.println("From " + args[1] + ", take line " + sArray[0] + " to station " + sArray[1] + ";");

        String previousLine = sArray[0];
        for (int i = 1; i < listOfAllLineChanges.size() - 1; i++) {
            sArray = listOfAllLineChanges.get(i);
            System.out.println("then change to line " + sArray[0] + ", and continue to " + sArray[1] + ";");
        }
        sArray = listOfAllLineChanges.get(listOfAllLineChanges.size() - 1);
        String currentLine = sArray[0];
        if(!previousLine.equals(currentLine))
            System.out.println("then change to line " + sArray[0] + ", and continue to " + sArray[1] + ".");


        System.out.println("The total trip will take approximately " + currentStation.getPathLength() + " minutes and will have " + currentStation.getNoOfChanges() + " changes.");

        //currentStation`.printPath();


    }


    /**
     * @param args Program arguments from the start of the execution
     * @return true if all arguments were parsed and handled properly
     */
    public static boolean processProgramArguments(String[] args) {

        switch (args.length) {
            case 4:
                if (args[3].toLowerCase().equals("changes") || args[3].toLowerCase().equals("time")) {
                    Station.preferTime = args[3].toLowerCase().equals("changes");
                } else {
                    System.out.println("Invalid criterion");
                    return false;
                }
            case 3:             // Fall through
                boolean startFound = false, endFound = false; //flags for finding the first and last station

                startStationString = args[1].replaceAll("\"", "");
                endStationString = args[2].replaceAll("\"", "");
                return true;
            default:
                System.out.println("Missing arguments. Please enter in format: java assign1 RailNetwork.xml \"station 1\" \"station 2\" [criterion]");
                return false;
        }
    }

    /**
     * @param allStations a list of all stations to be checked from the xml data
     * @return true if at least one station was found for the starting and finishing stations specified
     */
    public static boolean findStartingAndTerminatingStations(List<Station> allStations) {
        boolean startFound = false, endFound = false;
        for (Station s : allStations) {
            if (s.getName().equals(startStationString)) {
                //Create a new node
                //Add that node to our heap such that we can begin using the heap
                s.setNoOfChanges(0);
                s.setPathLength(0);
                s.setVisited(true);
                stationHeap.add(s);
                //System.out.println("Starting station found "+s);
                startFound = true;
            }
            if (!endFound && s.getName().equals(endStationString)) {
                finalStation = s;
//                System.out.println("Final Station Found:" + finalStation);
                endFound = true;
            }
        }
        if (!startFound) {
            System.out.println("Starting station not found");
            return false;
        }
        if (!endFound) {
            System.out.println("Final Station not found");
            return false;
        }
        return true;
    }

}


