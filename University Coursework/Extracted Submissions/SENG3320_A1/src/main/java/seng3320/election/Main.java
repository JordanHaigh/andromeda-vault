package seng3320.election;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    /**
     * This method is provided as a convenience method for initially understanding how the code works/an example of how to use the classes.
     * <br>
     * Can be fed input from a file (either via a pipe or copy/pasting the entire file into the command line after the first prompt.
     * <br>
     * One sample has been provided for each election, these can be found in the {@code resources} folder.
     * <br>
     * Alternatively, you may simply run the program and follow the instructions sent to the stdout/the console.
     */
    public static void main(String[] args) {
        Scanner theScanner = new Scanner(System.in);
        while(true) {
            System.out.print("Please choose the election type (FPTP or Preferential) (case sensitive): ");
            String electionType = theScanner.nextLine();
            List<String> candidates;
            String eachLine;
            switch (electionType) {
                case "FPTP":
                    System.out.println();
                    System.out.print("Please specify the candidates (space separated): ");
                    candidates = Arrays.asList(theScanner.nextLine().split(" "));
                    FirstPastThePostElection fElection = new FirstPastThePostElection(candidates);

                    System.out.println();
                    System.out.println("Please specify the votes (in format 'CANDIDATE'). One vote per line. Empty line to finish the list:");
                    //e.g. "A"
                    List<FirstPastThePostElection.Vote> fVotes = new ArrayList<>();
                    while (theScanner.hasNextLine()) {
                        eachLine = theScanner.nextLine();
                        //////////////////I WROTE THIS///////////////////////////
                        if(eachLine.equals(""))
                            break;
                        //////////////////I WROTE THIS///////////////////////////

                        fVotes.add(new FirstPastThePostElection.Vote(eachLine));
                    }

                    FirstPastThePostElection.Result fResult = fElection.count(fVotes.toArray(new FirstPastThePostElection.Vote[]{}));
                    System.out.println("The election result is...");
                    System.out.println(fResult.toString());

                    return;

                case "Preferential":
                    System.out.println();
                    System.out.print("Please specify the candidates (space separated): ");
                    candidates = Arrays.asList(theScanner.nextLine().split(" "));
                    PreferentialElection pElection = new PreferentialElection(candidates);

                    System.out.println();
                    System.out.println("Please specify the votes as a series of preferences (in format 'CANDIDATE PRIORITY' (where PRIORITY is an integer with 1 being for the first preference)). Put spaces between candidates. One vote per line. Empty line to finish the list:");
                    //e.g. "A 1 B 2"
                    List<PreferentialElection.Vote> pVotes = new ArrayList<>();
                    while (theScanner.hasNextLine()) {
                        eachLine = theScanner.nextLine();
                        List<PreferentialElection.Vote.Preference> preferences = new ArrayList<>();
                        String[] parts = eachLine.split(" ");
                        int i = 0;
                        while (i < parts.length-1) {
                            String candidate = parts[i++];
                            Scanner intScanner = new Scanner(parts[i++]);
                            int priority = -1;
                            if(intScanner.hasNextInt(10)) {
                                priority = intScanner.nextInt();
                                if(intScanner.hasNext()) {
                                    priority = -1;
                                }
                            }
                            preferences.add(new PreferentialElection.Vote.Preference(candidate, priority));
                        }
                        pVotes.add(new PreferentialElection.Vote(preferences.toArray(new PreferentialElection.Vote.Preference[]{})));
                    }

                    PreferentialElection.Result pResult = pElection.count(pVotes.toArray(new PreferentialElection.Vote[]{}));
                    System.out.println("The election result is...");
                    System.out.println(pResult.toString());

                    return;
                default:
                    System.err.println("Invalid election type please try again");
            }
        }
    }
}
