
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WorkspaceFileReader {

  ArrayList<Polygon> readFile(String filePath) throws Exception {

    polygonList = new ArrayList<Polygon>();

    try {
      BufferedReader reader = new BufferedReader(new FileReader(filePath));


        String wholeFile = readWholeFile(reader);
      String[] polygonText = wholeFile.split("}");

      for (int i = 0; i < polygonText.length; i++) {
        String polygonStr = polygonText[i];
        if (polygonStr.equals("\n")) {
          break;
        }
        String[] linesInPolygon = polygonStr.split("\n");
        String id ="";

        ArrayList<LinePositions> lines = new ArrayList<LinePositions>();

        for (int j = 0; j < linesInPolygon.length; j++) {
          String line = linesInPolygon[j];

          if (line.equals("\n") || line.equals(""))
            continue;

          if (line.contains("Polygon")) {
            id = polygonStr.substring(polygonStr.indexOf("n")+1, polygonStr.indexOf("{"));
            continue;
          }

          //get id
          //split lines

          line = line.replaceAll("[^\\d.]", " ");
          line = line.trim().replaceAll(" +", " ");

          String[] coordinates = line.split(" ");
          lines.add(
            new LinePositions(
            Integer.parseInt(coordinates[0]), 
            Integer.parseInt(coordinates[1]), 
            Integer.parseInt(coordinates[2]), 
            Integer.parseInt(coordinates[3])
            )
            );
        }
        polygonList.add(new Polygon(lines, Integer.parseInt(id)));
      }
    }
    catch(Exception e)
    {
      throw e;
    }

    return polygonList;
  }

  private String readWholeFile(BufferedReader reader) throws IOException {
    //Modified from http://abhinandanmk.blogspot.com.au/2012/05/java-how-to-read-complete-text-file.html
    String line = null;
    StringBuilder sb = new StringBuilder();
    while ((line = reader.readLine()) != null)
    {
      sb.append(line).append("\n");
    }

    return sb.toString();
  }
}
