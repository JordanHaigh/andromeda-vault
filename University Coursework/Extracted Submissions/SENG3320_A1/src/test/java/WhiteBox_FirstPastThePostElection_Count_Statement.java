import org.junit.jupiter.api.Test;
import seng3320.election.FirstPastThePostElection;

import java.util.ArrayList;
import java.util.List;

public class WhiteBox_FirstPastThePostElection_Count_Statement {

    @Test
    public void fptp_count_winner_clear(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("mike");
        validCandidates.add("max");
        validCandidates.add("fred");

        FirstPastThePostElection firstPastThePostElection = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote v1 = new FirstPastThePostElection.Vote("mike");

        firstPastThePostElection.count(v1);
    }

    @Test
    public void fptp_count_informal_invalid(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("mike");
        validCandidates.add("max");
        validCandidates.add("fred");

        FirstPastThePostElection firstPastThePostElection = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote v2 = new FirstPastThePostElection.Vote("greg");

        firstPastThePostElection.count(v2);
    }


    @Test
    public void fptp_count_tie(){

        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("mike");
        validCandidates.add("max");
        validCandidates.add("fred");

        FirstPastThePostElection firstPastThePostElection = new FirstPastThePostElection(validCandidates);

        FirstPastThePostElection.Vote v1 = new FirstPastThePostElection.Vote("mike");
        FirstPastThePostElection.Vote v2 = new FirstPastThePostElection.Vote("fred");

        firstPastThePostElection.count(v1,v2);
    }

}
