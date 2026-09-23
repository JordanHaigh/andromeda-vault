package seng3320.election;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class PreferentialElection extends Election {
    public PreferentialElection(List<String> validCandidates) {
        super(validCandidates);
    }

    /**
     * Stores information about the outcome of an election.
     * <br>
     * Note that the tallies/statistics are sorted before being stored, such that candidates with more votes appear earlier in the list. The tallies are sorted primarily by primary votes, and secondarily by votes after preferences. Lexicographical sorting is used to break ties.
     * <br>
     * This also means that if the only difference between two elections is the order in which candidates are listed, (and tallies are otherwise the same), the result is considered equivalent
     * @see Election.Result
     * @see #equals(Object)
     */
    public static class Result extends Election.Result {

        public class CandidateStatistic {
            public final String candidateName;
            public final int primary;
            public final int afterPreferences;

            public CandidateStatistic(String candidateName, int primary, int afterPreferences) {
                this.candidateName = candidateName;
                this.primary = primary;
                this.afterPreferences = afterPreferences;
            }
        }

        public final List<CandidateStatistic> statistics;

        /**
         * @see PreferentialElection.Result
         */
        private Result(Election.ResultType resultType, String electedCandidate, int formalVotes, int informalVotes, Map<String, Integer> firstTallies, Map<String, Integer> preferencedTallies) {
            super(resultType, electedCandidate, formalVotes, informalVotes);
            this.statistics = firstTallies.keySet()
                    .stream()
                    .map(each -> new CandidateStatistic(each, firstTallies.get(each), preferencedTallies.getOrDefault(each, 0)))
                    .sorted(
                            Comparator.comparing((Function<CandidateStatistic, Integer>)  each -> each.afterPreferences)
                                    .thenComparing(each -> each.primary)
                                    .reversed()
                                    .thenComparing(each -> each.candidateName)
                    )
                    .collect(Collectors.toList());
        }

        /**
         * @see PreferentialElection.Result
         */
        public Result(String electedCandidate, int formalVotes, int informalVotes, Map<String, Integer> firstTallies, Map<String, Integer> preferencedTallies) {
            this(Election.ResultType.CLEAR, electedCandidate, formalVotes, informalVotes, firstTallies, preferencedTallies);
        }

        /**
         * @see PreferentialElection.Result
         */
        public Result(Election.ResultType resultType, int formalVotes, int informalVotes, Map<String, Integer> firstTallies, Map<String, Integer> preferencedTallies) {
            this(resultType, null, formalVotes, informalVotes, firstTallies, preferencedTallies);
        }

        /**
         * Creates a string representation of the result.
         */
        @Override
        public String toString() {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printWriter.println("Election type: Preferential");
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
                    break;
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

            for (int i = 0; i < statistics.size(); i++) {
                CandidateStatistic eachStat = statistics.get(i);
                printWriter.printf("%s: %s (primary) %s (after preferences)%n", eachStat.candidateName, eachStat.primary, eachStat.afterPreferences);
            }

            printWriter.flush();
            printWriter.close();
            return stringWriter.toString();
        }


        /**
         * Determines whether or not two {@code Result}s are equal
         * @param o an object to compare with
         * @return true is o is a {@link PreferentialElection.Result} and the recorded outcomes are the same.
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

            if(statistics.size() != other.statistics.size()) {
                return false;
            }

            for (int i = 0; i < statistics.size(); i++) {
                CandidateStatistic each = statistics.get(i);
                CandidateStatistic eachOther = other.statistics.get(i);

                if(!each.candidateName.equals(eachOther.candidateName)){
                    return false;
                }
                else{
                    if(each.primary != eachOther.primary){
                        return false;
                    }
                    else{
                        if(each.afterPreferences != eachOther.afterPreferences ){
                            return false;
                        }
                        else
                            System.out.println();
                    }
                }

//                if (!each.candidateName.equals(eachOther.candidateName) || each.primary != eachOther.primary || each.afterPreferences != eachOther.afterPreferences ) {
//                    return false;
//                }
            }
            System.out.println();
            return true;
        }
    }

    public static class Vote implements Election.Vote {
        public static class Preference {
            public final String candidate;
            public final int priority;

            public Preference(String candidate, int priority) {
                this.candidate = candidate;
                this.priority = priority;
            }
        }

        public final List<Preference> preferences;

        public Vote(Preference... preferences) {
            List<Preference> tmp = new ArrayList<>(Arrays.asList(preferences));
            tmp.sort(Comparator.comparing(each -> each.priority));
            this.preferences = Collections.unmodifiableList(tmp);
        }
    }

    /**
     * In a preferential election, a vote is considered formal (and therefore can be counted) if:
     * <br>
     * - It is a {@link PreferentialElection.Vote}, and
     * <br>
     * - There is exactly one preference for each valid candidate, and
     * <br>
     * - For all preferences, the priority is a value in the range [1,n] (inclusive), where n is the number of candidates.
     * <br>
     * - There are no two preferences with the same priority
     * @param vote a vote whose type is any class that extends {@link Election.Vote}
     * @param <T> The specific type of {@code vote} (inferred via the {@code} parameter)
     * @return true if the vote is formal, false otherwise
     */
    public <T extends Election.Vote> boolean isFormal(T vote) {
        if (!(vote instanceof Vote)) {
            return false;
        }

        Vote theVote = ((Vote)vote);

        if(theVote.preferences.size() < validCandidates.size()) {
            return false;
        }
        for (int i = 0; i < theVote.preferences.size(); i++) {
            Vote.Preference each = theVote.preferences.get(i);

            if(!validCandidates.contains(each.candidate) || each.priority < 1 || each.priority > validCandidates.size()) {
                return false;
            }
            else {
                for (int k = i+1; k < theVote.preferences.size(); k++) {
                    Vote.Preference eachOther = theVote.preferences.get(k);
                    if(each.candidate == eachOther.candidate || each.priority == eachOther.priority) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Performs preference allocations and returns whether or not a single candidate has been clearly elected.
     * <br>
     * In order to be elected, a candidate must have a dominant proportion (&gt;50%) of the total vote.
     * <br>
     * Preferences are allocated iteratively. In each iteration, the method tests whether or not there is a clear winner, if not, the candidates who currently have the least votes are removed from preferenceTallies and anyone whose vote is currently allocated to the removed candidates will have their next preference applied.
     * <br>
     * The process finishes when either there is a clear winner, or all candidates have equal votes
     * @param preferenceTallies a map from candidate name to vote-count. First preferences should already be allocated when this is passed in. NOTE: this parameter is modified and should be checked when verifying results in a test.
     * @param formalVotes a list of formal (valid) votes; Not modified, not part of the testable results
     * @return true if any candidate has been clearly elected, false otherwise
     */
    private boolean allocatePreferences(Map<String, Integer> preferenceTallies, List<Vote> formalVotes) {
        Map<Vote, Integer> currentPreferenceIndices = new HashMap<>();
        for (Vote each : formalVotes) {
            currentPreferenceIndices.put(each, 0);
        }

        do {
            Map.Entry<String, Integer> highestTally = preferenceTallies.entrySet().iterator().next();
            for (Map.Entry<String, Integer> eachEntry : preferenceTallies.entrySet()) {
                if (eachEntry.getValue().compareTo(highestTally.getValue()) > 0) {
                    highestTally = eachEntry;
                }
            }

            boolean allTied = preferenceTallies.values()
                    .stream()
                    .distinct()
                    .count() == 1;

            boolean hasDominantCandidate = highestTally.getValue().compareTo(validCandidates.size()/2 + 1) > 0;

            if(!hasDominantCandidate && !allTied) {
                int lowestVote = preferenceTallies.entrySet()
                        .stream()
                        .mapToInt(Map.Entry::getValue)
                        .min()
                        .getAsInt();

                for (Map.Entry<String, Integer> eachTally : preferenceTallies.entrySet()) {
                    if (eachTally.getValue() == lowestVote) {
                        preferenceTallies.remove(eachTally.getKey());

                        for (int i = 0; i < formalVotes.size(); i++) {
                            Vote eachVote = formalVotes.get(i);

                            int currentPreferenceIndex = currentPreferenceIndices.get(eachVote);

                            if (
                                    eachTally.getKey()
                                            .equals(
                                                    eachVote.preferences
                                                            .get(currentPreferenceIndex)
                                                            .candidate
                                            )
                            ) {
                                int nextPreferenceIndex = currentPreferenceIndex;
                                Vote.Preference nextPreference;

                                do {
                                    ++nextPreferenceIndex;
                                    nextPreference = eachVote.preferences.get(nextPreferenceIndex);
                                } while (!preferenceTallies.containsKey(nextPreference.candidate));

                                preferenceTallies.put(nextPreference.candidate, preferenceTallies.get(nextPreference.candidate)+1);
                                currentPreferenceIndices.put(eachVote, nextPreferenceIndex);
                            }
                        }
                    }
                }
            } else {
                return hasDominantCandidate;
            }
        } while (true);
    }
    /**
     * Determines the outcome of an election.
     * <br>
     * An election is to be declared invalid if there are no formal votes.
     * <br>
     * In a preferential election, voters include every candidate in vote assigning a preference to each. Their first/primary preference is their most preferred candidate, their second preference indicates their next-most preferred candidate and so on.
     * <br>
     * In order for a single candidate to win the election, they must have a dominant proportion of the vote, otherwise the next preferences of the least popular candidates are considered (who is then eliminated), and then the next, until either a clear winner can be declared or all remaining candidates have the same number of votes.
     * <br>
     * In the event that there is a tie after preferences, the number of first-preference votes allocated to the remaining candidates is used an attempted tiebreaker.
     * <br>
     * If two or more of those remaining candidates have equal first-preference votes, the election is declared a tie.
     * <br>
     * Note that non-primary preference allocation is performed via {@link #allocatePreferences(Map, List)}.
     * <br>
     * @param votes an array of objects, each of which is of some type that extends {@link Election.Vote}
     * @param <T> the most-specific type which encompasses all {@code votes} (inferred via the {@code votes} parameter)
     * @return information about the outcome of an election conducted with the candidates stored in this class, and the {@code votes} supplied as a parameter to this method
     * @see #isFormal(Election.Vote)
     * @see PreferentialElection.Result
     * @see #allocatePreferences(Map, List)
     */
    @Override
    public <T extends Election.Vote> Result count(T... votes) {
        Map<String, Integer> firstPreferenceTallies = new HashMap<>();
        int numFormalVotes = 0;
        int numInformalVotes = 0;

        for (String eachCandidate : validCandidates) {
            firstPreferenceTallies.put(eachCandidate, 0);
        }

        List<Vote> formalVotes = new ArrayList<>(votes.length);

        for (T each : votes) {
            if (isFormal(each)) {
                ++numFormalVotes;
                Vote theVote = (Vote)each;
                Vote.Preference firstPreference = theVote.preferences.get(0);
                firstPreferenceTallies.put(firstPreference.candidate, firstPreferenceTallies.get(firstPreference.candidate)+1);
                formalVotes.add(theVote);
            } else {
                ++numInformalVotes;
            }
        }

        if (numFormalVotes == 0) {
            return new Result(ResultType.INVALID, numFormalVotes, numInformalVotes, firstPreferenceTallies, firstPreferenceTallies);
        }

        Map<String, Integer> preferenceTallies = new HashMap<>();
        for (Map.Entry<String, Integer> eachTally : firstPreferenceTallies.entrySet()) {
            preferenceTallies.put(eachTally.getKey(), eachTally.getValue());
        }

        boolean hasDominantCandidate = allocatePreferences(preferenceTallies, formalVotes);

        Map.Entry<String, Integer> highestTally = preferenceTallies.entrySet().iterator().next();

        if(!hasDominantCandidate) {
            Map<String, Integer> relevantFirstPreferences = new HashMap<>();
            for (Map.Entry<String, Integer> eachEntry : preferenceTallies.entrySet()) {
                relevantFirstPreferences.put(eachEntry.getKey(), firstPreferenceTallies.get(eachEntry.getKey()));
            }

            boolean hasTie = false;

            for (Map.Entry<String, Integer> eachEntry : relevantFirstPreferences.entrySet()) {
                if (eachEntry.getValue().equals(highestTally.getValue())) {
                    hasTie = true;
                } else if (eachEntry.getValue().compareTo(highestTally.getValue()) > 0) {
                    highestTally = eachEntry;
                    hasTie = false;
                }
            }

            if (hasTie) {
                return new Result(ResultType.TIE, numFormalVotes, numInformalVotes, firstPreferenceTallies, preferenceTallies);
            }
        }

        return new Result(highestTally.getKey(), numFormalVotes, numInformalVotes, firstPreferenceTallies, preferenceTallies);
    }

    public <T extends Election.Vote> Result count(boolean hasDominant, T... votes) {
        Map<String, Integer> firstPreferenceTallies = new HashMap<>();
        int numFormalVotes = 0;
        int numInformalVotes = 0;

        for (String eachCandidate : validCandidates) {
            firstPreferenceTallies.put(eachCandidate, 0);
        }

        List<Vote> formalVotes = new ArrayList<>(votes.length);

        for (T each : votes) {
            if (isFormal(each)) {
                ++numFormalVotes;
                Vote theVote = (Vote)each;
                Vote.Preference firstPreference = theVote.preferences.get(0);
                firstPreferenceTallies.put(firstPreference.candidate, firstPreferenceTallies.get(firstPreference.candidate)+1);
                formalVotes.add(theVote);
            } else {
                ++numInformalVotes;
            }
        }

        if (numFormalVotes == 0) {
            return new Result(ResultType.INVALID, numFormalVotes, numInformalVotes, firstPreferenceTallies, firstPreferenceTallies);
        }

        Map<String, Integer> preferenceTallies = new HashMap<>();
        for (Map.Entry<String, Integer> eachTally : firstPreferenceTallies.entrySet()) {
            preferenceTallies.put(eachTally.getKey(), eachTally.getValue());
        }

//        boolean hasDominantCandidate = allocatePreferences(preferenceTallies, formalVotes);
        //overrides the allocatePreferences returned value such that bugs from allocatePreferences don't affect coverage
        boolean hasDominantCandidate = hasDominant;
        Map.Entry<String, Integer> highestTally = preferenceTallies.entrySet().iterator().next();

        if(!hasDominantCandidate) {
            Map<String, Integer> relevantFirstPreferences = new HashMap<>();
            for (Map.Entry<String, Integer> eachEntry : preferenceTallies.entrySet()) {
                relevantFirstPreferences.put(eachEntry.getKey(), firstPreferenceTallies.get(eachEntry.getKey()));
            }

            boolean hasTie = false;

            for (Map.Entry<String, Integer> eachEntry : relevantFirstPreferences.entrySet()) {
                if (eachEntry.getValue().equals(highestTally.getValue())) {
                    hasTie = true;
                } else if (eachEntry.getValue().compareTo(highestTally.getValue()) > 0) {
                    highestTally = eachEntry;
                    hasTie = false;
                }
            }

            if (hasTie) {
                return new Result(ResultType.TIE, numFormalVotes, numInformalVotes, firstPreferenceTallies, preferenceTallies);
            }
        }

        return new Result(highestTally.getKey(), numFormalVotes, numInformalVotes, firstPreferenceTallies, preferenceTallies);
    }
}
