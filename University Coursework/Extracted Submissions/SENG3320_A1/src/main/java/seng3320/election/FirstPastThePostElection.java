package seng3320.election;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;

public class FirstPastThePostElection extends Election {

    /**
     * Stores information about the outcome of an election.
     * <br>
     * Note that the tallies/statistics are sorted before being stored, such that candidates with more votes appear earlier in the list. Lexicographical sorting is used to break ties.
     * <br>
     * This also means that if the only difference between two elections is the order in which candidates are listed, (and tallies are otherwise the same), the result is considered equivalent
     * @see Election.Result
     * @see #equals(Object)
     */
    public static class Result extends Election.Result {
        public final List<Map.Entry<String, Integer>> tallies;

        /**
         * @see FirstPastThePostElection.Result
         */
        private Result(Election.ResultType resultType, String electedCandidate, int formalVotes, int informalVotes, Map<String, Integer> tallies) {
            super(resultType, electedCandidate, formalVotes, informalVotes);
            this.tallies = Collections.unmodifiableList(
                    tallies.entrySet()
                            .stream()
                            .sorted(
                                    Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                                            .thenComparing(Map.Entry.comparingByKey())
                            )
                            .collect(Collectors.toList())
            );
        }


        /**
         * @see FirstPastThePostElection.Result
         */
        public Result(String electedCandidate, int formalVotes, int informalVotes, Map<String, Integer> tallies) {
            this(Election.ResultType.CLEAR, electedCandidate, formalVotes, informalVotes, tallies);
        }

        /**
         * @see FirstPastThePostElection.Result
         */
        public Result(Election.ResultType resultType, int formalVotes, int informalVotes, Map<String, Integer> tallies) {
            this(resultType, null, formalVotes, informalVotes, tallies);
        }

        /**
         * Creates a string representation of the result.
         */
        @Override
        public String toString() {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printWriter.println("Election type: FirstPastThePost");
            printWriter.print("Result: ");
            switch (resultType) {
                case CLEAR:
                    printWriter.print("Clear");
                    break;
                case TIE:
                    printWriter.print("Tie");
                    break;
                case INVALID:
                    printWriter.print("Invalid");
            }
            printWriter.println();

            if(!resultType.equals(Election.ResultType.INVALID)) {
                printWriter.printf("Elected Candidate: %s%n", electedCandidate);
            }

            printWriter.printf("Formal Votes: %s%n", formalVotes);
            printWriter.printf("Informal Votes: %s%n", informalVotes);

            printWriter.println();
            printWriter.println("The tallies were...");
            printWriter.println();

            for (int i = 0; i < tallies.size(); i++) {
                Map.Entry<String, Integer> eachTally = tallies.get(i);
                printWriter.printf("%s: %s%n", eachTally.getKey(), eachTally.getValue());
            }

            printWriter.flush();
            printWriter.close();
            return stringWriter.toString();
        }

        /**
         * Determines whether or not two {@code Result}s are equal
         * @param o an object to compare with
         * @return true is o is a {@link FirstPastThePostElection.Result} and the recorded outcomes are the same.
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Result)) {
                return false;
            }

            Result other = (Result)o;

           if (!resultType.equals(other.resultType)) {
               return false;
           }

           if(formalVotes != other.formalVotes) {
               return false;
           }

           if(informalVotes != other.informalVotes) {
               return false;
           }

           if(!electedCandidate.equals(other.electedCandidate)) {
               return false;
           }

           if(tallies.size() != other.tallies.size()) {
               return false;
           }

            for (int i = 0; i < tallies.size(); i++) {
                Map.Entry<String, Integer> each = tallies.get(i);
                Map.Entry<String, Integer> eachOther = other.tallies.get(i);
                if (!each.getKey().equals(eachOther.getKey()) || !each.getValue().equals(eachOther.getValue())) {
                    return false;
                }
            }

           return true;
        }
    }

    public static class Vote implements Election.Vote {
        public final String candidate;

        public Vote(String candidate) {
            this.candidate = candidate;
        }
    }

    public FirstPastThePostElection(List<String> validCandidates) {
        super(validCandidates);
    }

    /**
     * In a first past the post election, a vote is considered formal (and therefore can be counted) if it is a {@link FirstPastThePostElection.Vote} and the candidate is amongst the valid candidates
     * @param vote a vote which of a type that extends {@link Election.Vote}
     * @param <T> The specific type of {@code vote} (inferred via the {@code} parameter)
     * @return true if the vote is formal, false otherwise
     */
    @Override
    public <T extends Election.Vote> boolean isFormal(T vote) {
        return vote instanceof Vote && validCandidates.contains(((Vote) vote).candidate);
    }

    /**
     * Determines the outcome of an election.
     * <br>
     * An election is to be declared invalid if there are no formal votes.
     * <br>
     * In a first past the post election, the result is clear if one candidate has more votes than any other.
     * <br>
     * If two or more candidates has the highest number of votes, the result is a tie.
     * @param votes an array of objects, each of which is of some type that extends {@link Election.Vote}
     * @param <T> The most-specific type which encompasses all {@code votes} (inferred via the {@code votes} parameter)
     * @return information about the outcome of an election conducted with the candidates stored in this class, and the {@code votes} supplied as a parameter to this method
     * @see #isFormal(Election.Vote)
     * @see FirstPastThePostElection.Result
     */
    @Override
    public <T extends Election.Vote> Result count(T... votes) {
        Map<String, Integer> tallies = new HashMap<>();
        int formalVotes = 0;
        int informalVotes = 0;

        for (String eachCandidate : validCandidates) {
            tallies.put(eachCandidate, 0);
        }

        for (T each : votes) {
            if (isFormal(each)) {
                ++formalVotes;
                String candidate = ((Vote)each).candidate;
                tallies.put(candidate, tallies.get(candidate) + 1);
            } else {
                ++informalVotes;
            }
        }

        if (formalVotes == 0) {
            return new Result(ResultType.INVALID, formalVotes, informalVotes, tallies);
        }

        Map.Entry<String, Integer> highestTally = null;
        boolean hasTie = false;

        for (Map.Entry<String, Integer> eachEntry : tallies.entrySet()) {


            if (highestTally == null || eachEntry.getValue().compareTo(highestTally.getValue()) > 0) {
                highestTally = eachEntry;
                hasTie = false;
            } else if (eachEntry.getValue().equals(highestTally.getValue())) {
                hasTie = true;
            }


        //            System.out.println(eachEntry.toString());
//            if(highestTally == null) {
//                highestTally = eachEntry;
//                hasTie = false;
//            }else {
//                if (eachEntry.getValue().compareTo(highestTally.getValue()) > 0) {
//                    highestTally = eachEntry;
//                    hasTie = false;
//                } else  if(eachEntry.getValue().equals(highestTally.getValue())){
//                    hasTie = true;
//                }else
//                    System.out.println("test");
//            }
//
        }

        if (hasTie) {
            return new Result(ResultType.TIE, formalVotes, informalVotes, tallies);
        } else {
            return new Result(highestTally.getKey(), formalVotes, informalVotes, tallies);
        }
    }
}
