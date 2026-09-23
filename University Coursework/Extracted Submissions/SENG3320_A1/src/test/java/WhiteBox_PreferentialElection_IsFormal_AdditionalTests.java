import org.junit.jupiter.api.Test;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_IsFormal_AdditionalTests {
    @Test
    public void preferential_isFormal_MCDC_FormalVote(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("a");
        validCandidates.add("b");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
            new PreferentialElection.Vote.Preference("a",1),
            new PreferentialElection.Vote.Preference("b",2)
        );

        preferentialElection.isFormal(vote1);
        //First condition:000
        // Second condition: 00
    }


    @Test
    public void preferential_isFormal_MCDC_PriorityGreaterThanMax(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("a");
        validCandidates.add("b");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("a",45),
                new PreferentialElection.Vote.Preference("b",2)
        );

        preferentialElection.isFormal(vote1);

        //First condition: 001
    }

    @Test
    public void preferential_isFormal_MCDC_PriorityLessThan1(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("a");
        validCandidates.add("b");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("a",1),
                new PreferentialElection.Vote.Preference("b",0)
        );

        preferentialElection.isFormal(vote1);

        //First condition: 010
    }

    @Test
    public void preferential_isFormal_MCDC_WrongName(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("a");
        validCandidates.add("b");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("a",1),
                new PreferentialElection.Vote.Preference("boromi",2)
        );

        preferentialElection.isFormal(vote1);

        //First condition: 100
    }

    @Test
    public void preferential_isFormal_MCDC_SamePriority(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("a");
        validCandidates.add("b");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("a",1),
                new PreferentialElection.Vote.Preference("b",1)
        );

        preferentialElection.isFormal(vote1);

        //First condition: 000,01
    }

    @Test
    public void preferential_isFormal_MCDC_SameName(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("a");
        validCandidates.add("b");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("a",1),
                new PreferentialElection.Vote.Preference("a",2)
        );

        preferentialElection.isFormal(vote1);

        //First condition: 000,10
    }
}
