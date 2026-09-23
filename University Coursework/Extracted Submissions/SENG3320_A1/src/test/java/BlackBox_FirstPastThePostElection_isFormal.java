import org.junit.jupiter.api.Test;
import seng3320.election.FirstPastThePostElection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackBox_FirstPastThePostElection_isFormal {
    @Test
    public void fptp_isFormal_voteIsFormal_returnTrue(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("boris");
        validCandidates.add("butch");

        FirstPastThePostElection fptp = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote = new FirstPastThePostElection.Vote("boris");

        assertEquals(true, fptp.isFormal(vote));

    }

    @Test
    public void fptp_isFormal_voteIsNotFormal_returnTrue(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("derek");
        validCandidates.add("jimothy");

        FirstPastThePostElection fptp = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote vote = new FirstPastThePostElection.Vote("craig");

        assertEquals(false, fptp.isFormal(vote));

    }

}
