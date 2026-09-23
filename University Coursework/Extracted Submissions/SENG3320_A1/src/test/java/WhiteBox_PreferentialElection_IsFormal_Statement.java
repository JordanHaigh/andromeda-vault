import org.junit.jupiter.api.Test;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_IsFormal_Statement {

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
    public void preferential_statement_isFormal_votePreferenceLessThanCandidateSize(){
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
    public void preferential_statement_isFormal_NotAVote(){
        List<String> validCandidates = new ArrayList<>();
        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        preferentialElection.isFormal(null);
    }

    @Test
    public void preferential_statement_isFormal_candidatesDoesntContainSpecificCandidate(){
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
    public void preferential_statement_isFormal_CandidateEqualsSameCandidate(){
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

//
//    @Test
//    public void preferential_statement_isFormal_FirstCombinedCondition_priorityLessThan1(){
//        List<String> validCandidates = new ArrayList<>();
//        validCandidates.add("geoff");
//        validCandidates.add("brad");
//        validCandidates.add("steve");
//
//        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
//
//        PreferentialElection.Vote vote = new PreferentialElection.Vote(
//                new PreferentialElection.Vote.Preference("geoff",3),
//                new PreferentialElection.Vote.Preference("brad",2),
//                new PreferentialElection.Vote.Preference("steve",-1)
//        );
//
//        preferentialElection.isFormal(vote);
//
//    }
//
//    @Test
//    public void preferential_statement_isFormal_FirstCombinedCondition_priorityGreaterThanCandidatesSize(){
//        List<String> validCandidates = new ArrayList<>();
//        validCandidates.add("geoff");
//        validCandidates.add("brad");
//        validCandidates.add("steve");
//
//        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
//
//        PreferentialElection.Vote vote = new PreferentialElection.Vote(
//                new PreferentialElection.Vote.Preference("geoff",3),
//                new PreferentialElection.Vote.Preference("brad",2),
//                new PreferentialElection.Vote.Preference("steve",17)
//        );
//
//        preferentialElection.isFormal(vote);
//
//    }
//

//
//    @Test
//    public void preferential_statement_isFormal_SecondCombinedCondition_PrioritiesAreTheSame(){
//        List<String> validCandidates = new ArrayList<>();
//        validCandidates.add("geoff");
//        validCandidates.add("brad");
//        validCandidates.add("steve");
//
//        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
//
//        PreferentialElection.Vote vote = new PreferentialElection.Vote(
//                new PreferentialElection.Vote.Preference("geoff",3),
//                new PreferentialElection.Vote.Preference("brad",1),
//                new PreferentialElection.Vote.Preference("steve",1)
//        );
//
//        preferentialElection.isFormal(vote);
//    }


}
