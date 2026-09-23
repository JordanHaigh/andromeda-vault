import org.junit.jupiter.api.Test;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_IsFormal_Path {
    @Test
    public void preferential_statement_isFormal_ValidVote_Brancha6b(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",1),
                new PreferentialElection.Vote.Preference("steve",2),
                new PreferentialElection.Vote.Preference("brad",3)
        );

        preferentialElection.isFormal(vote);
    }
    @Test
    public void preferential_statement_isFormal_InvalidVoteRepeatedPreference_Brancha6a(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",1),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("brad",3)
        );

        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_statement_isFormal_InvalidVoteOnlyOneCandidate_Brancha5b(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",1)
        );

        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_statement_isFormal_InvalidPreference_Brancha4a(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",-1),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("brad",3)
        );

        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_statement_isFormal_VoteWithNoPreferencesAndNoCandidates_Brancha3b(){
        List<String> validCandidates = new ArrayList<>();


        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        preferentialElection.isFormal(new PreferentialElection.Vote());
    }

    @Test
    public void preferential_statement_isFormal_fewerPreferencesThanCandidates_Brancha2a(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",1),
                new PreferentialElection.Vote.Preference("brad",2)
        );

        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_statement_isFormal_NotAVoteObject_Brancha1a(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        preferentialElection.isFormal(null);
    }

}
