import java.io.*;
import java.util.List;

public class KWIC_HelperMethods {

    static final KWIC kwic = new KWIC();

    public static void createInputTextFile(String filePath, List<String> linesToAddToFile, String id) throws IOException {
        //Write input test cases to file
        FileWriter writer = new FileWriter(filePath + "input" + id+ ".txt");
        for(String str: linesToAddToFile)
            writer.write(str + "\n");
        writer.close();

    }

    public static String[] createInputRunProgramGetOutput(List<String> linesToAddToFile) throws IOException {
        String filePath = "src\\test\\java\\";

        createInputTextFile(filePath,linesToAddToFile, "");

        //Get Output.txt ready to write from System.Out.Printlns
        PrintStream fileOut = new PrintStream(filePath + "output.txt");
        System.setOut(fileOut);

        //Run KWIC with input.txt - Output.txt will be created instead of printing to console
        kwic.main(new String[]{filePath + "input.txt"});
        fileOut.close();

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out))); //reset console.output


        //Get list of lines from output.txt
        return readFile(filePath + "output.txt");

    }

    private static String[] readFile(String filePath) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath)))
        {
            StringBuilder sb = new StringBuilder();
            //Modified from http://abhinandanmk.blogspot.com.au/2012/05/java-how-to-read-complete-text-file.html
            String line = null;
            while((line = reader.readLine()) != null)
                sb.append(line).append("\n");

            return sb.toString().split("\n");
        }
    }

}
