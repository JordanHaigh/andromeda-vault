import org.junit.jupiter.api.Test;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_IsFormal_BranchCondition {


    @Test
    public void preferential_branchDecision_isFormal_noCandidatesEmptyVoteHitsBranch3B(){
        List<String> validCandidates = new ArrayList<>();
        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Vote vote = new PreferentialElection.Vote();
        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_branchDecision_isFormal_oneCandidateOneVoteHitsBranch5B(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("yong");
        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Vote vote = new PreferentialElection.Vote(
        new PreferentialElection.Vote.Preference("yong",1));
        preferentialElection.isFormal(vote);
    }


    ///////////////
    ///same as before


    @Test
    public void preferential_branchDecision_isFormal_NotAVote(){
        List<String> validCandidates = new ArrayList<>();
        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        preferentialElection.isFormal(null);
    }

    @Test
    public void preferential_branchDecision_isFormal_votePreferenceLessThanCandidateSize(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("steve",1)
        );

        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_branchCondition_isFormal_candidatesDoesntContainSpecificCandidate(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",3),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("boris",1)
        );

        preferentialElection.isFormal(vote);

    }

    @Test
    public void preferential_branchCondition_isFormal_CandidateEqualsSameCandidate(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",3),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("brad",1)
        );

        preferentialElection.isFormal(vote);
    }

    @Test
    public void preferential_statement_isFormal_returnTrue(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",3),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("steve",1)
        );

        preferentialElection.isFormal(vote);
        //also does the ff result in first multiple condition
    }


    @Test
    public void preferential_branchCondition_isFormal_priorityLessThan1(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",3),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("steve",-1)
        );

        preferentialElection.isFormal(vote);

    }

    @Test
    public void preferential_branchCondition_isFormal_priorityGreaterThanCandidatesSize(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",3),
                new PreferentialElection.Vote.Preference("brad",2),
                new PreferentialElection.Vote.Preference("steve",17)
        );

        preferentialElection.isFormal(vote);

    }

    @Test
    public void preferential_statement_isFormal_PrioritiesAreTheSame(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("geoff");
        validCandidates.add("brad");
        validCandidates.add("steve");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("geoff",3),
                new PreferentialElection.Vote.Preference("brad",1),
                new PreferentialElection.Vote.Preference("steve",1)
        );

        preferentialElection.isFormal(vote);
    }

}
