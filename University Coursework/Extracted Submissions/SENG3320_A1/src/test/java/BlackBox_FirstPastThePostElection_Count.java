import org.junit.jupiter.api.*;
import seng3320.election.Election;
import seng3320.election.FirstPastThePostElection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackBox_FirstPastThePostElection_Count {
    @Test
    public void fptp_count_isInvalid(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("joe");
        validCandidates.add("lewis");

        FirstPastThePostElection fptp = new FirstPastThePostElection(validCandidates);

        Election.Vote votes[] = new Election.Vote[6];

        FirstPastThePostElection.Result result = fptp.count(votes);

        assertEquals(FirstPastThePostElection.ResultType.INVALID, result.resultType);
        //invalid because there are no formal votes
    }

    @Test
    public void fptp_count_isTie(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("phillip");
        validCandidates.add("juliet");

        FirstPastThePostElection fptp = new FirstPastThePostElection(validCandidates);

        Election.Vote votes[] = new Election.Vote[2];
        votes[0] = new FirstPastThePostElection.Vote("phillip");
        votes[1] = new FirstPastThePostElection.Vote("juliet");

        FirstPastThePostElection.Result result = fptp.count(votes);

        assertEquals(FirstPastThePostElection.ResultType.TIE, result.resultType);
    }

    @Test
    public void fptp_count_isClear(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("phillip");
        validCandidates.add("juliet");

        FirstPastThePostElection fptp = new FirstPastThePostElection(validCandidates);

        Election.Vote votes[] = new Election.Vote[2];
        votes[0] = new FirstPastThePostElection.Vote("phillip");
        votes[1] = new FirstPastThePostElection.Vote("phillip");

        FirstPastThePostElection.Result result = fptp.count(votes);

        assertEquals(FirstPastThePostElection.ResultType.CLEAR, result.resultType);
    }
}
