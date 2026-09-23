import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;

import seng3320.election.Election;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class BlackBox_PreferentialElection_isFormal {
    /*
        - In a preferential election, a vote is considered formal (and therefore can be counted) if:
        - It is a PreferentialElection.Vote, and
        - There is exactly one preference for each valid candidate, and
        - For all preferences, the priority is a value in the range [1,n] (inclusive), where n is the number of candidates.
        - There are no two preferences with the same priority
    * */
    @Test
    public void  preferential_isFormal_voteIsAPreferentialVote_returnTrue(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("lucas");
        validCandidates.add("pukas");
        validCandidates.add("jukas");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        //List<PreferentialElection.Vote.Preference> preferences = new ArrayList<>();

        PreferentialElection.Vote.Preference p1 = new PreferentialElection.Vote.Preference("lucas", 2);
        PreferentialElection.Vote.Preference p2 = new PreferentialElection.Vote.Preference("pukas", 3);
        PreferentialElection.Vote.Preference p3 = new PreferentialElection.Vote.Preference("jukas", 1);

        //why not pass a list in to the constructor instead of a variable number of arguments?
        // The class contains a list of preferences....

//        preferences.add(p1);
//        preferences.add(p2);
//        preferences.add(p3);

        PreferentialElection.Vote v = new PreferentialElection.Vote(p1,p2,p3);

        assertEquals(true, preferentialElection.isFormal(v));

    }


    @Test
    public void  preferential_isFormal_voteIsNotAPreferentialVote_returnFalse(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("lucas");
        validCandidates.add("samantha");
        validCandidates.add("jukas");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Vote v = new PreferentialElection.Vote((PreferentialElection.Vote.Preference) null);

        assertEquals(false, preferentialElection.isFormal(v));

    }

//            - There is exactly one preference for each valid candidate, and

    @Test
    public void  preferential_isFormal_missingOnePreference_returnFalse(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("jake");
        validCandidates.add("santa");
        validCandidates.add("bread");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        //santa is missing from preferences
        PreferentialElection.Vote.Preference p1 = new PreferentialElection.Vote.Preference("jake", 2);
        PreferentialElection.Vote.Preference p3 = new PreferentialElection.Vote.Preference("bread", 1);

        PreferentialElection.Vote v = new PreferentialElection.Vote(p1, p3);

        assertEquals(false, preferentialElection.isFormal(v));

    }

    //        - For all preferences, the priority is a value in the range [1,n] (inclusive), where n is the number of candidates.
    @Test
    public void  preferential_isFormal_priorityOutOfRange_returnFalse(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("peter");
        validCandidates.add("brody");
        validCandidates.add("cameron");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        //santa is missing from preferences
        PreferentialElection.Vote.Preference p1 = new PreferentialElection.Vote.Preference("peter", 0);
        PreferentialElection.Vote.Preference p2 = new PreferentialElection.Vote.Preference("brody", 66);
        PreferentialElection.Vote.Preference p3 = new PreferentialElection.Vote.Preference("cameron", -1);

        PreferentialElection.Vote v = new PreferentialElection.Vote(p1,p2,p3);

        assertEquals(false, preferentialElection.isFormal(v));

    }

    //        - There are no two preferences with the same priority
    @Test
    public void  preferential_isFormal_twoPreferencesWithSamePriority_returnFalse(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("greg");
        validCandidates.add("shannon");
        validCandidates.add("juan");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        //santa is missing from preferences
        PreferentialElection.Vote.Preference p1 = new PreferentialElection.Vote.Preference("greg", 1);
        PreferentialElection.Vote.Preference p2 = new PreferentialElection.Vote.Preference("shannon", 1);
        PreferentialElection.Vote.Preference p3 = new PreferentialElection.Vote.Preference("juan", 3);

        PreferentialElection.Vote v = new PreferentialElection.Vote(p1,p2,p3);

        assertEquals(false, preferentialElection.isFormal(v));

    }
    ////////////////////////////////////////////////////////////////////////////////////////////


}
