import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCasesFromKLEE {

    @Test
    public void testCase1(){
        int a = 0;
        int b = 0;
        int c = 0;

        int median = Program.median(a,b,c);
        assertEquals(median,0);
    }

    @Test
    public void testCase2(){
        int a = 0;
        int b = 1;
        int c = 0;

        int median = Program.median(a,b,c);
        assertEquals(median, 0);
    }

    @Test
    public void testCase3(){
        int a = 16777216;
        int b = 16777216;
        int c = 0;

        int median = Program.median(a,b,c);
        assertEquals(median, 16777216);

    }

    @Test
    public void testCase4(){
        int a = -2147483648;
        int b = -2130706432;
        int c = 0;

        int median = Program.median(a,b,c);
        //assertEquals(median, -2130706432);
    }

    @Test
    public void testCase5(){
        int a = -2147483648;
        int b = 0;
        int c = 0;

        int median = Program.median(a,b,c);
        assertEquals(median, 0);

    }

    @Test
    public void testCase6(){
        int a = 16777216;
        int b = -2147483648;
        int c = 0;

        int median = Program.median(a,b,c);
        assertEquals(median, 0);
    }

    @Test
    public void testCase7(){
        int a = 16777216;
        int b = 0;
        int c = 0;

        int median = Program.median(a,b,c);
        assertEquals(median, 0);
    }

}
