import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.*;
import seng3320.election.PreferentialElection;
import java.util.HashMap;
import java.util.Map;

public class BlackBox_PreferentialElection_Equals {


    @Test
    public void preferential_equals_AllIsEqual_returnTrue(){
        Map<String, Integer> firstTallies = new HashMap<>();
        firstTallies.put("joseph", 1);
        firstTallies. put("frank",2);
        firstTallies.put("barbara", 2);

        Map<String, Integer> preferencedTallies = new HashMap<>();
        preferencedTallies.put("joseph", 2);
        preferencedTallies.put("frank",4);
        preferencedTallies.put("barbara", 2);

        PreferentialElection.Result result1 = new PreferentialElection.Result("joseph",6,0, firstTallies, preferencedTallies);
        PreferentialElection.Result result2 = new PreferentialElection.Result("joseph",6,0, firstTallies, preferencedTallies);

        assertEquals(true, result1.equals(result2));

    }
    @Test
    public void preferential_equals_differentElectedCandidates_returnFalse(){
        Map<String, Integer> firstTallies = new HashMap<>();
        firstTallies.put("joseph", 1);
        firstTallies. put("frank",2);
        firstTallies.put("barbara", 2);

        Map<String, Integer> preferencedTallies = new HashMap<>();
        preferencedTallies.put("joseph", 2);
        preferencedTallies.put("frank",4);
        preferencedTallies.put("barbara", 2);

        PreferentialElection.Result result1 = new PreferentialElection.Result("henry",6,0, firstTallies, preferencedTallies);
        PreferentialElection.Result result2 = new PreferentialElection.Result("joseph",6,0, firstTallies, preferencedTallies);

        assertEquals(false, result1.equals(result2));

    }

    @Test
    public void preferential_equals_differentFormalVotes_returnFalse(){
        Map<String, Integer> firstTallies = new HashMap<>();
        firstTallies.put("joseph", 1);
        firstTallies. put("frank",2);
        firstTallies.put("barbara", 2);

        Map<String, Integer> preferencedTallies = new HashMap<>();
        preferencedTallies.put("joseph", 2);
        preferencedTallies.put("frank",4);
        preferencedTallies.put("barbara", 2);

        PreferentialElection.Result result1 = new PreferentialElection.Result("joseph",3,0, firstTallies, preferencedTallies);
        PreferentialElection.Result result2 = new PreferentialElection.Result("joseph",7,0, firstTallies, preferencedTallies);

        assertEquals(false, result1.equals(result2));

    }

    @Test
    public void preferential_equals_differentInformalVotes_returnFalse(){

        Map<String, Integer> firstTallies = new HashMap<>();
        firstTallies.put("joseph", 1);
        firstTallies. put("frank",2);
        firstTallies.put("barbara", 2);

        Map<String, Integer> preferencedTallies = new HashMap<>();
        preferencedTallies.put("joseph", 2);
        preferencedTallies.put("frank",4);
        preferencedTallies.put("barbara", 2);

        PreferentialElection.Result result1 = new PreferentialElection.Result("joseph",6,6, firstTallies, preferencedTallies);
        PreferentialElection.Result result2 = new PreferentialElection.Result("joseph",6,0, firstTallies, preferencedTallies);

        assertEquals(false, result1.equals(result2));

    }

    @Test
    public void preferential_equals_differentFirstTallies_returnFalse(){
        Map<String, Integer> firstTallies1 = new HashMap<>();
        firstTallies1.put("joseph", 2);
        firstTallies1. put("frank",3);
        firstTallies1.put("barbara", 1);


        Map<String, Integer> firstTallies2 = new HashMap<>();
        firstTallies1.put("sebastian", 1);
        firstTallies1. put("kyle",2);
        firstTallies1.put("evan", 2);


        Map<String, Integer> preferencedTallies = new HashMap<>();
        preferencedTallies.put("joseph", 2);
        preferencedTallies.put("frank",4);
        preferencedTallies.put("barbara", 2);

        PreferentialElection.Result result1 = new PreferentialElection.Result("joseph",6,0, firstTallies1, preferencedTallies);
        PreferentialElection.Result result2 = new PreferentialElection.Result("joseph",6,0, firstTallies2, preferencedTallies);

        assertEquals(false, result1.equals(result2));

    }

    @Test
    public void preferential_equals_differentPreferencedTallies_returnFalse(){
        Map<String, Integer> firstTallies = new HashMap<>();
        firstTallies.put("joseph", 1);
        firstTallies. put("frank",2);
        firstTallies.put("barbara", 2);

        Map<String, Integer> preferencedTallies1 = new HashMap<>();
        preferencedTallies1.put("joseph", 2);
        preferencedTallies1.put("frank",4);
        preferencedTallies1.put("barbara", 2);

        Map<String, Integer> preferencedTallies2 = new HashMap<>();
        preferencedTallies1.put("sebastian", 4);
        preferencedTallies1.put("kyle",66);
        preferencedTallies1.put("evan", 2);

        PreferentialElection.Result result1 = new PreferentialElection.Result("joseph",6,0, firstTallies, preferencedTallies1);
        PreferentialElection.Result result2 = new PreferentialElection.Result("joseph",6,0, firstTallies, preferencedTallies2);

        assertEquals(false, result1.equals(result2));

    }
}
