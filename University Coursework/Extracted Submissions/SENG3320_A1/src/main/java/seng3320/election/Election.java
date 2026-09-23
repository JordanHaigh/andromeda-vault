package seng3320.election;

import java.util.Collections;
import java.util.List;

public abstract class Election {
    public enum ResultType {
        /**
         * A clear result means that there is a single clearly elected candidate
         */
        CLEAR,
        /**
         * A tie result means that two or more candidates have the highest vote under the rules of the election
         */
        TIE,
        /**
         * An invalid result means that neither a clear result or a tie result could be identified from the supplied votes under the rules of the election
         */
        INVALID
    }

    /**
     * Stores information about the outcome of an election.
     */
    public static abstract class Result {
        public final ResultType resultType;
        public final String electedCandidate;
        public final int formalVotes;
        public final int informalVotes;

        protected Result(ResultType resultType, String electedCandidate, int formalVotes, int informalVotes) {
            this.resultType = resultType;
            this.electedCandidate = electedCandidate;
            this.formalVotes = formalVotes;
            this.informalVotes = informalVotes;
        }

        public abstract String toString();
    }

    public interface Vote {
    }

    public abstract <T extends Vote> boolean isFormal(T vote);

    protected final List<String> validCandidates;

    public Election(List<String> validCandidates) {
        this.validCandidates = Collections.unmodifiableList(validCandidates);
    }

    public abstract <T extends Vote> Result count(T... votes);

    public List<String> getValidCandidates() {
        return validCandidates;
    }
}
