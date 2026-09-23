import org.junit.jupiter.api.Test;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_Equal_Statement {
    @Test
    public void preferential_equals_statement_oNotResult(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");

        PreferentialElection.Vote vote = new PreferentialElection.Vote(new PreferentialElection.Vote.Preference("bob",1));

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Result result = preferentialElection.count(vote);

        result.equals("hello!");
    }

    @Test
    public void preferential_equals_statement_oHasDifferentResultType(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );

        PreferentialElection.Vote vote2 = new PreferentialElection.Vote();

        PreferentialElection preferentialElection1 = new PreferentialElection(validCandidates);
        PreferentialElection preferentialElection2 = new PreferentialElection(validCandidates);
        PreferentialElection.Result result1 = preferentialElection1.count(vote1);
        PreferentialElection.Result result2 = preferentialElection2.count(vote2);

        result1.equals(result2);
    }

    @Test
    public void preferential_equals_statement_oDifferentFormalVotes(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );

        PreferentialElection.Vote vote2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );


        PreferentialElection preferentialElection1 = new PreferentialElection(validCandidates);
        PreferentialElection preferentialElection2 = new PreferentialElection(validCandidates);
        PreferentialElection.Result result1 = preferentialElection1.count(vote1, vote2);
        PreferentialElection.Result result2 = preferentialElection2.count(vote1, vote2,vote3);

        result1.equals(result2);
    }

    @Test
    public void preferential_equals_statement_oDifferentInformalVotes(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );

        PreferentialElection.Vote vote2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );

        PreferentialElection.Vote vote4 = new PreferentialElection.Vote();


        PreferentialElection preferentialElection1 = new PreferentialElection(validCandidates);
        PreferentialElection preferentialElection2 = new PreferentialElection(validCandidates);
        PreferentialElection.Result result1 = preferentialElection1.count(vote1, vote2, vote3, vote4);
        PreferentialElection.Result result2 = preferentialElection2.count(vote1, vote2,vote3);

        result1.equals(result2);
    }

    @Test
    public void preferential_equals_statement_oDifferentElectedCandidate(){
        List<String> validCandidates1 = new ArrayList<>();
        validCandidates1.add("bob");

        PreferentialElection.Vote vote1_1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote1_2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote1_3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote1_4 = new PreferentialElection.Vote();

        List<String> validCandidates2 = new ArrayList<>();
        validCandidates2.add("jason");

        PreferentialElection.Vote vote2_1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("jason",1)
        );
        PreferentialElection.Vote vote2_2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("jason",1)
        );
        PreferentialElection.Vote vote2_3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("jason",1)
        );
        PreferentialElection.Vote vote2_4 = new PreferentialElection.Vote();


        PreferentialElection preferentialElection1 = new PreferentialElection(validCandidates1);
        PreferentialElection preferentialElection2 = new PreferentialElection(validCandidates2);

        PreferentialElection.Result result1 = preferentialElection1.count(vote1_1, vote1_2, vote1_3, vote1_4);
        PreferentialElection.Result result2 = preferentialElection2.count(vote2_1, vote2_2, vote2_3, vote2_4);

        result1.equals(result2);
    }

    @Test
    public void preferential_equals_statement_oDifferentStatisticSize(){
        List<String> validCandidates1 = new ArrayList<>();
        validCandidates1.add("bob");
        validCandidates1.add("jason");

        PreferentialElection.Vote vote1_1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("jason",2)
        );
        PreferentialElection.Vote vote1_2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("jason",2)
        );
        PreferentialElection.Vote vote1_3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("jason",2)
        );


        List<String> validCandidates2 = new ArrayList<>();
        validCandidates2.add("bob");

        PreferentialElection.Vote vote2_1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote2_2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote2_3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );


        PreferentialElection preferentialElection1 = new PreferentialElection(validCandidates1);
        PreferentialElection preferentialElection2 = new PreferentialElection(validCandidates2);

        PreferentialElection.Result result1 = preferentialElection1.count(true, vote1_1, vote1_2, vote1_3);
        PreferentialElection.Result result2 = preferentialElection2.count(true, vote2_1, vote2_2, vote2_3);
        //have to use mock function to get around allocatePreferences bug

        result1.equals(result2);

    }

    @Test
    public void preferential_equals_statement_decisionCoverageInsideForLoop(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");
        validCandidates.add("jim");
        validCandidates.add("garry");

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("jim",2),
                new PreferentialElection.Vote.Preference("garry",3)
        );
        PreferentialElection.Vote vote2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("jim",3),
                new PreferentialElection.Vote.Preference("garry",2)
        );
        PreferentialElection.Vote vote3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",3),
                new PreferentialElection.Vote.Preference("jim",1),
                new PreferentialElection.Vote.Preference("garry",2)
        );
        PreferentialElection.Vote vote4 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",3),
                new PreferentialElection.Vote.Preference("jim",2),
                new PreferentialElection.Vote.Preference("garry",1)
        );

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Result result1 = preferentialElection.count(true, vote1,vote2, vote3);
        PreferentialElection.Result result2 = preferentialElection.count(true, vote1,vote2, vote4);

        result1.equals(result2);
    }

    @Test
    public void preferential_equals_statement_allTheSame(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );
        PreferentialElection.Vote vote2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1)
        );

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Result result1 = preferentialElection.count(vote1,vote2);
        PreferentialElection.Result result2 = preferentialElection.count(vote1,vote2);

        result1.equals(result2);

    }




}
