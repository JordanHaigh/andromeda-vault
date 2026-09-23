import org.junit.jupiter.api.Test;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_Count_Branch {
    @Test
    public void preferential_count__branch_containsFormalAndInformalVotes(){

        //////////////////////////
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("linus");
        validCandidates.add("lewis");
        validCandidates.add("bob");


        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote[] votes = new PreferentialElection.Vote[3];

        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("linus",2),
                new PreferentialElection.Vote.Preference("lewis",3)
        );

        PreferentialElection.Vote v3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("linus",3),
                new PreferentialElection.Vote.Preference("lewis",1)
        );

        PreferentialElection.Vote v4 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("linus",3),
                new PreferentialElection.Vote.Preference("lewis",1)
        );

        PreferentialElection.Vote v5 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("lewis",1)
        );

        PreferentialElection.Result result = preferentialElection.count(true, v2, v3, v4, v5);

    }

    @Test
    public void preferential_count__branch_NoDominantCandidate()
    {
        ///
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");
        validCandidates.add("linus");
        validCandidates.add("lewis");


        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote[] votes = new PreferentialElection.Vote[3];

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("linus",2),
                new PreferentialElection.Vote.Preference("lewis",3)
        );
        PreferentialElection.Vote v5 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("linus",2),
                new PreferentialElection.Vote.Preference("lewis",3)
        );
        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("linus",3),
                new PreferentialElection.Vote.Preference("lewis",1)
        );
        PreferentialElection.Vote v6 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("linus",3),
                new PreferentialElection.Vote.Preference("lewis",1)
        );
        PreferentialElection.Vote v3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("linus",3),
                new PreferentialElection.Vote.Preference("lewis",1)
        );
        PreferentialElection.Vote v4 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",2),
                new PreferentialElection.Vote.Preference("linus",1),
                new PreferentialElection.Vote.Preference("lewis",3)
        );

        PreferentialElection.Result result = preferentialElection.count(false, v1, v2, v3, v4, v5,v6);
    }

    @Test
    public void preferential_count_branch_PerfectTie()
    {

        /////
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("bob");
        validCandidates.add("linus");
        validCandidates.add("lewis");


        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote[] votes = new PreferentialElection.Vote[3];

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",1),
                new PreferentialElection.Vote.Preference("linus",2),
                new PreferentialElection.Vote.Preference("lewis",3)
        );

        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("bob",3),
                new PreferentialElection.Vote.Preference("linus",2),
                new PreferentialElection.Vote.Preference("lewis",1)
        );

        PreferentialElection.Result result = preferentialElection.count(false, v1, v2);
    }

    @Test
    public void preferential_count_branch_noVotesNoCandidates(){
///////////
        List<String> validCandidates = new ArrayList<>();
        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        preferentialElection.count(false);
    }

}
