import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class KWIC_FuzzerTool {
    static Random random = new Random();
    public static final char MIN_CHAR = '!';
    public static final char MAX_CHAR = '~';

    //we should assume itll be utf8, no utf16s or utf32, etc.

    HashMap<String, List<String>> uniqueExceptionsAndAssociatedFile = new HashMap<>();
    HashMap<String, Integer> exceptionsCounter = new HashMap<>();

    @Test
    public void runFuzzyKWIC() throws FileNotFoundException {

        int numberOfTests = 5000;
        int i = 0;
        while (i < numberOfTests) {
            System.out.println("Test Number:" + i);
            List<String> inputFile = generateFileInput();
            try {
                String[] outputLines = KWIC_HelperMethods.createInputRunProgramGetOutput(inputFile);
            } catch (Exception e) {
                uniqueExceptionsAndAssociatedFile.put(e.getClass().toString(), inputFile);

                if (exceptionsCounter.get(e.getClass().toString()) == null) {
                    exceptionsCounter.put(e.getClass().toString(), 1);
                } else {
                    exceptionsCounter.put(e.getClass().toString(), exceptionsCounter.get(e.getClass().toString()).intValue() + 1);
                }
            }
            i++;
        }

        //reset sys out and print same thing to console
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out))); //reset console.output
        System.out.println("Unique exceptions: " + uniqueExceptionsAndAssociatedFile.size());
        printMap(uniqueExceptionsAndAssociatedFile, exceptionsCounter);

    }



    public static void printMap(Map mp, Map counterMap) {
        Iterator it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey()); //Print exception name

            int exceptionCount = (int) counterMap.get(pair.getKey().toString());
            System.out.println("Exception count: " + exceptionCount);

            printInputFile((List<String>) pair.getValue()); //print to console


            try { //Make input.txt files for marker
                KWIC_HelperMethods.createInputTextFile("src\\test\\java\\", (List<String>)pair.getValue(), pair.getKey().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }


            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public static void printInputFile(List<String> inputFile) {
        System.out.println("Test File: ");
        for (String s : inputFile) {
            System.out.println("\t" + s);
        }

    }

    public List<String> generateFileInput() {
        List<String> list = new ArrayList<>();
        int numberOfLinesInList = random.nextInt(5);

        for (int i = 0; i < numberOfLinesInList; i++) {
            StringBuilder workingLine = new StringBuilder();
            int numberOfWordsOnLine = random.nextInt(10) + 1;

            for (int j = 0; j < numberOfWordsOnLine; j++) {
                int wordLength = random.nextInt(10) + 1;

                for (int k = 0; k < wordLength; k++) {
                    workingLine.append((char) (random.nextInt(MAX_CHAR - MIN_CHAR + 1) + MIN_CHAR));
                }
                if (j != numberOfWordsOnLine - 1)
                    workingLine.append(" ");
            }
            list.add(workingLine.toString());
        }

        return list;
    }
}
