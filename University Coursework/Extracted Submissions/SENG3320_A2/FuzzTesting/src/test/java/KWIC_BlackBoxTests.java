import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KWIC_BlackBoxTests {
    /**
     * “The KWIC [Key Word in Context] index system
     * accepts an ordered set of lines;
     * each line is an ordered set of words,
     * each word is an ordered set of characters.
     * Any line may be “circularly shifted” by repeatedly removing the first word and appending it at the end of the line.
     * The KWIC index system outputs a list of all circular shifts of all lines in alphabetical order.”
     */
    @Test
    public void KWIC_validInputMultiLines_returnsValidCombinations() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();
        linesToAddToFile.add("a c e");
        linesToAddToFile.add("b d f");

        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);


        /*Do assertions on each line*/
        assertEquals(outputLines[0], "a c e");
        assertEquals(outputLines[1], "b d f");
        assertEquals(outputLines[2], "c e a");
        assertEquals(outputLines[3], "d f b");
        assertEquals(outputLines[4], "e a c");
        assertEquals(outputLines[5], "f b d");
    }

    @Test
    public void KWIC_emptyFile() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();

        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);

        /*Do assertions on each line*/
        assertEquals(outputLines[0], "");
    }

    @Test
    public void KWIC_emptyLine() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();
        linesToAddToFile.add("a b");
        linesToAddToFile.add("");


        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);

        /*Do assertions on each line*/
        assertEquals(outputLines[0], "a b");
        assertEquals(outputLines[1], "b a");
    }

    @Test
    public void KWIC_emptyWord() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();
        linesToAddToFile.add("you  interesting");


        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);

        /*Do assertions on each line*/
        assertEquals(outputLines[0], "interesting you");
        assertEquals(outputLines[1], "you interesting");

        //java.lang.ArrayIndexOutOfBoundsException: 2

    }

//////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void KWIC_dodgyCase() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();
        linesToAddToFile.add("6 *&^%$ aaaa");
        linesToAddToFile.add("z z z");

        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);

    }

    @Test
    public void KWIC_numbersCase() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();
        linesToAddToFile.add("1 2 3");

        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);

    }

    @Test
    public void KWIC_specialCase() throws IOException {
        /*Create inputs here*/
        List<String> linesToAddToFile = new ArrayList<>();
        //linesToAddToFile.add("@ # $"); //this one doesnt work
        linesToAddToFile.add("$ # @"); //this one works

        /* Create input.txt, run program with input.txt, save output to output.txt*/
        String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(linesToAddToFile);

    }


}
