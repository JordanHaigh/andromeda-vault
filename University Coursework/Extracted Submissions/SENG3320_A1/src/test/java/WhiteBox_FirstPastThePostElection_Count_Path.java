import org.junit.jupiter.api.*;
import seng3320.election.Election;
import seng3320.election.FirstPastThePostElection;
import seng3320.election.PreferentialElection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhiteBox_FirstPastThePostElection_Count_Path {

    @Test
    public void fptp_count_path_noCandidatesNoVotes(){

        List<String> validCandidates = new ArrayList<>();
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);
        election.count();
        //1b,2b,4a

    }


    @Test
    public void fptp_count_path_candidatesNoVotes(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joseph");
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);
        election.count();
        //1a,2b,4a

    }

    @Test
    public void fptp_count_path_candidatesInformalVote(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joseph");
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote = new FirstPastThePostElection.Vote("sebastian");
        election.count(vote);

        // 1a,2a,3b,4a
    }

    @Test
    public void fptp_count_path_OneFormalVoteClearWin(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joseph");
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote = new FirstPastThePostElection.Vote("joseph");
        election.count(vote);
        // 1a,2a,3a,4b,5a,6a,8b
    }


    @Test
    public void fptp_count_path_TwoCandidates_SecondCandidateHasLessVotes_ClearWin(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joseph");
        validCandidates.add("lewis");
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote1 = new FirstPastThePostElection.Vote("joseph");
        FirstPastThePostElection.Vote vote2 = new FirstPastThePostElection.Vote("joseph");
        FirstPastThePostElection.Vote vote3 = new FirstPastThePostElection.Vote("lewis");
        election.count(vote1,vote2,vote3);


        // [...], 5a,6b,7b, 8b
    }

    @Test
    public void fptp_count_path_ThreeCandidates_ClearWinByLexicalgraphicallyLastCandidate(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joseph");
        validCandidates.add("lewis");
        validCandidates.add("zanuel");
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote1 = new FirstPastThePostElection.Vote("joseph");
        FirstPastThePostElection.Vote vote2 = new FirstPastThePostElection.Vote("lewis");
        FirstPastThePostElection.Vote vote3 = new FirstPastThePostElection.Vote("zanuel");
        FirstPastThePostElection.Vote vote4 = new FirstPastThePostElection.Vote("zanuel");

        election.count(vote1,vote2,vote3,vote4);


        // [...], 5a,6b,7a, 8b
    }

    @Test
    public void fptp_count_path_TwoCandidates_PerfectTie(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joseph");
        validCandidates.add("lewis");
        FirstPastThePostElection election = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote1 = new FirstPastThePostElection.Vote("joseph");
        FirstPastThePostElection.Vote vote2 = new FirstPastThePostElection.Vote("lewis");
        election.count(vote1,vote2);

        // [...],5a,6b,7a,8a
    }



}
