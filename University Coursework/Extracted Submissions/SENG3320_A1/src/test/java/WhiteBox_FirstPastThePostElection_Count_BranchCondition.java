import org.junit.jupiter.api.Test;
import seng3320.election.FirstPastThePostElection;

import java.util.ArrayList;
import java.util.List;

    public class WhiteBox_FirstPastThePostElection_Count_BranchCondition {

    @Test
    public void fptp_count_branch_CompletelyEmpty(){
        List<String> validCandidates = new ArrayList<>();

        FirstPastThePostElection firstPastThePostElection = new FirstPastThePostElection(validCandidates);

        firstPastThePostElection.count();

    }

    @Test
    public void fptp_count_branch_formalAndInformalVotesWin(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("mike");

        FirstPastThePostElection firstPastThePostElection = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote v1 = new FirstPastThePostElection.Vote("mike");
        FirstPastThePostElection.Vote v2 = new FirstPastThePostElection.Vote("greg");

        firstPastThePostElection.count(v1,v2);
    }

    @Test
    public void fptp_count_branch_formalVotesTie(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("aa");

        validCandidates.add("max");
        validCandidates.add("mike");
        validCandidates.add("fred");

        FirstPastThePostElection firstPastThePostElection = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote v1 = new FirstPastThePostElection.Vote("max");
        FirstPastThePostElection.Vote v2 = new FirstPastThePostElection.Vote("max");
        FirstPastThePostElection.Vote v3 = new FirstPastThePostElection.Vote("mike");
        FirstPastThePostElection.Vote v4 = new FirstPastThePostElection.Vote("mike");
        FirstPastThePostElection.Vote v5 = new FirstPastThePostElection.Vote("fred");

        firstPastThePostElection.count(v5, v1,v2,v3,v4);
    }





}
