import org.junit.jupiter.api.*;
import seng3320.election.PreferentialElection;
import java.util.ArrayList;
import java.util.List;

public class WhiteBox_PreferentialElection_Count_Path {

    @Test
    public void preferential_count_path_candidatesButNoVotes(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("Bop");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        preferentialElection.count();
        //1a,2b,4a
    }

    @Test
    public void preferential_count_path_noVotes(){
        List<String> validCandidates = new ArrayList<>();
        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        preferentialElection.count();

        //1b,2b,4a
    }

    @Test
    public void preferential_count_path_oneInformalVoteOneCandidate(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("Bop");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);
        PreferentialElection.Vote vote = new PreferentialElection.Vote(new PreferentialElection.Vote.Preference("bop it",1));
        preferentialElection.count(vote);
        //1a,2a,3b,4a
    }

    @Test
    public void preferential_count_path_candidatesOneFormalVotes(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("Bop");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(new PreferentialElection.Vote.Preference("Bop",1));
        preferentialElection.count(vote);
        //1a,2a,3a,4b,5a,6b

    }

    @Test
    public void preferential_count_path_perfectTie(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("BopIt");
        validCandidates.add("TwistIt");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote vote = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("BopIt",1),
                new PreferentialElection.Vote.Preference("TwistIt",2));

        PreferentialElection.Vote vote2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("BopIt",2),
                new PreferentialElection.Vote.Preference("TwistIt",1));


        preferentialElection.count(vote, vote2);
        //[...],5a,6a,7a,8a,9a,11a
    }



    @Test
    public void preferential_count_path_secondaryCountAlphabeticallyFirstCandidateWins(){
            List<String> validCandidates = new ArrayList<>();
            validCandidates.add("bob");
            validCandidates.add("linus");
            validCandidates.add("lewis");


            PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

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
                    new PreferentialElection.Vote.Preference("bob",1),
                    new PreferentialElection.Vote.Preference("linus",3),
                    new PreferentialElection.Vote.Preference("lewis",2)
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
                    new PreferentialElection.Vote.Preference("bob",3),
                    new PreferentialElection.Vote.Preference("linus",1),
                    new PreferentialElection.Vote.Preference("lewis",2)
            );

            PreferentialElection.Result result = preferentialElection.count(false, v1, v2, v3, v4, v5,v6);

            //[...], 5a,6a,7a,8a,9b,10a,11b

    }

    @Test
    public void preferential_count_path_secondaryCountWinner(){
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
                new PreferentialElection.Vote.Preference("bob",3),
                new PreferentialElection.Vote.Preference("linus",2),
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


        //[...], 5a,6a,7a,8a,9b,10b,11b
    }

}
