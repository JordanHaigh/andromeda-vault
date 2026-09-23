/**
 * Class name: XMLParser
 * Student Numbers: c3256741 && c3256730 - Kyle Fennell and Jordan Haigh
 * Purpose of class: Parses the XML train line document to a graph structure where Stations
 * are nodes and StationEdges are edges.
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    private List<Station> stations = new ArrayList<>();
    private List<String> stationLines = new ArrayList<>();

    /**
     * Starting method of the parser - breaks
     * @param filePath path of the file to read from (.xml file)
     * @return a list of station objects found in the file
     */
    public List<Station> runParser(String filePath){
        try {
            //read all file lines to list of strings
            List<String> fileLines = Files.readAllLines(Paths.get(filePath));

            List<List<String>> fileLinesBrokenIntoStations = new ArrayList<>();

            //current working station section
            List<String> stationSection = new ArrayList<>();

            //boolean used to determine when finished reading a station or not
            boolean isWorking = false;

            for(String line : fileLines){

                if(line.contains("<Station>")){
                    isWorking = true; //start the boolean such that we are adding the current line to our working section
                }
                else if(line.contains("</Station>")){
                    isWorking = false; //finished reading station section
                    stationSection.add(line); //final line doesn't need to be included, only for readability
                    fileLinesBrokenIntoStations.add(stationSection);
                    stationSection = new ArrayList<>(); //reset for next station section
                }

                if(isWorking){
                    stationSection.add(line);
                }
            }

            //finished reading entire xml file, now build station information from the station sections from before
            for(List<String> stationSectionRawData : fileLinesBrokenIntoStations){
                Station station = buildStationInformation(stationSectionRawData);
                stations.add(station);
            }

        } catch (IOException e) {
            System.out.println("Error in reading file lines: " + e.getMessage());
        }

        //link all stations to corresponding edges
        linkStation();

        return stations;
    }


    /**
     * Builds information about a station that is currently being worked on
     * @param currentWorkingStation the current station being parsed to
     * @return the filled station container
     */
    public Station buildStationInformation(List<String> currentWorkingStation){
        //initialise variables to be altered later
        String name = "";
        String stationLine = "";
        List<StationEdge> stationEdges = new ArrayList<StationEdge>();

        //look through the current list searching for specific lines
        for(int i = 0; i < currentWorkingStation.size(); i++){
            String s = currentWorkingStation.get(i);

            if(s.contains("<Name>")){
                //Cleanse line
                s = s.replaceAll("<Name>","");
                s = s.replaceAll("</Name>","");
                s = s.trim();

                name = s;
            }
            else if(s.contains("<Line>")){
                //Cleanse line
                s = s.replaceAll("<Line>","");
                s = s.replaceAll("</Line>","");
                s = s.trim();

                stationLine = s;

                //Make sure that the stationline in the raw xml matches to what we have in our enumeration
                if(!stationLines.contains(s)){
                    stationLines.add(s);
                }

//                stationLine = findLineInEnum(s);
//
//                if(stationLine == null){
//                    throw new IllegalArgumentException("Could not find station line in file");
//                }
            }

            else if(s.contains("<StationEdges>")){
                //Need to look through all station edges underneath the current station xml

                List<String> stationEdgesRawXml = new ArrayList<>();

                //Starting from the initial tag for station edges, add all station edge raw xml to a new list
                for(int j = i; j < currentWorkingStation.size();j++){
                    String stationEdgesLine = currentWorkingStation.get(j); //Get the current line and add to station edges raw xml
                    stationEdgesRawXml.add(stationEdgesLine);
                }

                //generate all station edges found from the raw xml
                stationEdges = generateStationEdges(stationEdgesRawXml);

                break; //stop looping - this section takes care of everything else
            }
        }

        return new Station(name, stationLine, stationEdges);
    }

    /**
     * Generates station edges that correspond to a station
     * @param subList the list of station edge strings for a single stations
     * @return a list of stationEdge objects that corresponds the subList param
     */
    public List<StationEdge> generateStationEdges(List<String> subList){
        List<StationEdge> stationEdges = new ArrayList<>();
        boolean isWorking = false;

        //Init working variables to be altered later
        String name = "";
        String stationLine = null;
        int duration= 0;

        //search through each line in the raw xml
        for(String s : subList){
            if(s.contains("</StationEdges>")) {
                break; //We're done
            }
            else if(s.contains("<StationEdge>")){
                isWorking = true;
            }

            else if(s.contains("<Name>")){
                //Trim and replace
                s = s.replaceAll("<Name>","");
                s = s.replaceAll("</Name>","");
                s = s.trim();
                name = s;

            }
            else if(s.contains("<Line>")){
                //Trim and replace
                s = s.replaceAll("<Line>","");
                s = s.replaceAll("</Line>","");
                s = s.trim();
                stationLine = s;
            }
            else if(s.contains("<Duration>")){
                //Trim and replace
                s = s.replaceAll("<Duration>","");
                s = s.replaceAll("</Duration>","");
                s = s.trim();
                duration = Integer.parseInt(s);

            }
            else{
                //Line must be the end of Station Edge xml block
                if(isWorking){
                    StationEdge stationEdge = new StationEdge(name, stationLine, duration);
                    stationEdges.add(stationEdge);
                    isWorking = false;
                }
            }
        }

        return stationEdges;
    }

    /**
     * links all the edges to their respective stations
     */
    private void linkStation(){
        for (Station from : stations){
            for (StationEdge e : from.getStationEdges()){           // for every station edge
                for (Station to : stations){                        // find the station that matches the edge
                    if (e.getName().equals(to.getName()) && e.getLine().equals(to.getLine())){
                        e.setStation(to);                           // link that station to the edge to complete the graph
                        break;
                    }
                }
            }
        }
    }

}
