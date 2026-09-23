import org.junit.jupiter.api.Test;
import seng3320.election.FirstPastThePostElection;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlackBox_FirstPastThePostElection_Result_Equals {
    @Test
    public void fptp_result_equals_electedCandidateIsTheSame_returnTrue(){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",2);

        FirstPastThePostElection.Result result1 = new FirstPastThePostElection.Result("b", 3,0, map);
        FirstPastThePostElection.Result result2 = new FirstPastThePostElection.Result("b", 3,0, map);
        assertEquals(true, result1.equals(result2));
    }

    //different elected candidate a,b
    @Test
    public void fptp_result_equals_electedCandidateIsDifferent_returnFalse(){

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",2);

        FirstPastThePostElection.Result result1 = new FirstPastThePostElection.Result("a", 3,0, map);
        FirstPastThePostElection.Result result2 = new FirstPastThePostElection.Result("b", 3,0, map);


        assertEquals(false, result1.equals(result2));
    }


    //different formal votes
    @Test
    public void fptp_result_equals_differentFormalVotes_returnFalse(){

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",2);

        FirstPastThePostElection.Result result1 = new FirstPastThePostElection.Result("b", 7,0, map);
        FirstPastThePostElection.Result result2 = new FirstPastThePostElection.Result("b", 3,0, map);


        assertEquals(false, result1.equals(result2));
    }


    //different informal votes
    @Test
    public void fptp_result_equals_differentInformalVotes_returnFalse(){

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",2);

        FirstPastThePostElection.Result result1 = new FirstPastThePostElection.Result("b", 3,3, map);
        FirstPastThePostElection.Result result2 = new FirstPastThePostElection.Result("b", 3,0, map);


        assertEquals(false, result1.equals(result2));
    }


    //different map
    @Test
    public void fptp_result_equals_differentMap_returnFalse(){

        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("a",1);
        map1.put("b",2);

        Map<String, Integer> map2 = new HashMap<String, Integer>();
        map2.put("c",77);
        map2.put("d",284);

        FirstPastThePostElection.Result result1 = new FirstPastThePostElection.Result("b", 3,3, map1);
        FirstPastThePostElection.Result result2 = new FirstPastThePostElection.Result("b", 3,0, map2);


        assertEquals(false, result1.equals(result2));
    }

}
