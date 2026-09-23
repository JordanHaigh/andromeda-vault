import org.junit.jupiter.api.Test;
import seng3320.election.Election;
import seng3320.election.PreferentialElection;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackBox_Preferential_Count {

    @Test
    public void preferential_count_invalidDueToNoFormalVotes_returnFalse(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("disco");
        validCandidates.add("socky");
        validCandidates.add("sabre");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote[] votes = new PreferentialElection.Vote[6];


        assertEquals(Election.ResultType.INVALID, preferentialElection.count(votes).resultType);
    }

    @Test
    public void preferential_count_clearWinnerInFirst_solution1_concurrentModificationException(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("trump");
        validCandidates.add("hilary");
        validCandidates.add("berny");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("trump",1),
                new PreferentialElection.Vote.Preference("hilary",2),
                new PreferentialElection.Vote.Preference("berny",3)
        );

        PreferentialElection.Result result = preferentialElection.count(v1);

        assertEquals(Election.ResultType.CLEAR, result.resultType);

        //CONCURRENT MODIFICATION EXCEPTION
    }

    @Test
    public void preferential_count_clearWinnerInFirst_solution2_ClearResult(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("trump");
        validCandidates.add("hilary");
        validCandidates.add("berny");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("trump",2),
                new PreferentialElection.Vote.Preference("hilary",3),
                new PreferentialElection.Vote.Preference("berny",1)
        );

        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("trump",2),
                new PreferentialElection.Vote.Preference("hilary",3),
                new PreferentialElection.Vote.Preference("berny",1)
        );

        PreferentialElection.Vote v3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("trump",2),
                new PreferentialElection.Vote.Preference("hilary",3),
                new PreferentialElection.Vote.Preference("berny",1)
        );

        PreferentialElection.Result result = preferentialElection.count(v1,v2, v3);
        assertEquals(Election.ResultType.CLEAR, result.resultType);

    }

    @Test
    public void preferential_count_tieInFirstClearInSecond__solution1_concurrentModificationException(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("greg");
        validCandidates.add("henry");
        validCandidates.add("craig");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote[] votes = new PreferentialElection.Vote[2];

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("greg",1),
                new PreferentialElection.Vote.Preference("henry",3),
                new PreferentialElection.Vote.Preference("craig",2)
        );

        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("greg",2),
                new PreferentialElection.Vote.Preference("henry",1),
                new PreferentialElection.Vote.Preference("craig",3)
        );

        votes[0] = v1;
        votes[1] = v2;

        PreferentialElection.Result result = preferentialElection.count(votes);

        assertEquals(Election.ResultType.CLEAR, result.resultType);


        //CONCURRENT MODIFICATION EXCEPTION
    }

    @Test
    public void preferential_count_tieInFirstClearInSecond__solution2_returnClear()
    {
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("abra");
        validCandidates.add("zulu");
        validCandidates.add("shamus");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("abra",1),
                new PreferentialElection.Vote.Preference("zulu",2),
                new PreferentialElection.Vote.Preference("shamus",3)
        );
        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("abra",1),
                new PreferentialElection.Vote.Preference("zulu",2),
                new PreferentialElection.Vote.Preference("shamus",3)
        );
        PreferentialElection.Vote v3 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("abra",2),
                new PreferentialElection.Vote.Preference("zulu",3),
                new PreferentialElection.Vote.Preference("shamus",1)
        );
        PreferentialElection.Vote v4 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("abra",2),
                new PreferentialElection.Vote.Preference("zulu",3),
                new PreferentialElection.Vote.Preference("shamus",1)
        );
        PreferentialElection.Vote v5 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("abra",2),
                new PreferentialElection.Vote.Preference("zulu",3),
                new PreferentialElection.Vote.Preference("shamus",1)
        );
        PreferentialElection.Vote v6 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("abra",2),
                new PreferentialElection.Vote.Preference("zulu",1),
                new PreferentialElection.Vote.Preference("shamus",3)
        );

        PreferentialElection.Result result = preferentialElection.count(v1, v2, v3, v4, v5,v6);
        assertEquals(Election.ResultType.CLEAR, result.resultType);
    }

    @Test
    public void preferential_count_tieInFirstTieInSecond_solution1_concurrentModificationException(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("benny");
        validCandidates.add("chris");
        validCandidates.add("michael");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);


        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("benny",1),
                new PreferentialElection.Vote.Preference("chris",2),
                new PreferentialElection.Vote.Preference("michael",3)
        );

        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("benny",2),
                new PreferentialElection.Vote.Preference("chris",1),
                new PreferentialElection.Vote.Preference("michael",3)
        );

        PreferentialElection.Result result = preferentialElection.count(v1,v2);
        assertEquals(Election.ResultType.TIE, result.resultType);

        //CONCURRENT MODIFICATION EXCEPTION
    }

    @Test
    public void preferential_count_tieInFirstTieInSecond_solution2_returnTrue(){
        List<String> validCandidates = new ArrayList<>();
        validCandidates.add("benny");
        validCandidates.add("chris");
        validCandidates.add("michael");

        PreferentialElection preferentialElection = new PreferentialElection(validCandidates);

        PreferentialElection.Vote v1 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("benny",1),
                new PreferentialElection.Vote.Preference("chris",2),
                new PreferentialElection.Vote.Preference("michael",3)
        );

        PreferentialElection.Vote v2 = new PreferentialElection.Vote(
                new PreferentialElection.Vote.Preference("benny",3),
                new PreferentialElection.Vote.Preference("chris",2),
                new PreferentialElection.Vote.Preference("michael",1)
        );

        PreferentialElection.Result result = preferentialElection.count(v1, v2);
        assertEquals(Election.ResultType.TIE, result.resultType);

    }
}
