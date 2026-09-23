import java.io.FileNotFoundException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import processing.core.PApplet;


abstract class Button {
  private int startX, startY, buttonWidth, buttonHeight;
  private int endX, endY;
  private String text;


  Button(int startX, int startY, int buttonWidth, int buttonHeight, String text) {
    this.startX = startX;
    this.startY = startY;
    this.buttonWidth = buttonWidth;
    this.buttonHeight = buttonHeight;
    this.text = text;

    this.endX = startX + buttonWidth;
    this.endY= startY + buttonHeight;
  }


  public int getStartX() {
    return startX;
  }
  public int getStartY() {
    return startY;
  }
  public int getButtonWidth() {
    return buttonWidth;
  }
  public int getButtonHeight() {
    return buttonHeight;
  }
  public int getEndX() { 
    return endX;
  }
  public int getEndY() {
    return endY;
  }
  public String getText() {
    return text;
  }


  boolean isMouseOverButton() {
    return((startX < mouseX && mouseX < endX) && (startY < mouseY && mouseY < endY));
  }

  void invokePayload() {
  };

  void render(int r, int g, int b) {
    if(isMouseOverButton()){
      fill(200);
    }
    else
      fill(r,g,b);
    rectMode(CORNER);
    rect(startX, startY, buttonWidth, buttonHeight);
    textAlign(CENTER);
    fill(0);
    textFont(f, 24);
    text(text, (endX + startX)/2, (endY+startY)/2);
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class ResetButton extends Button {
  ResetButton(int startX, int startY, int buttonWidth, int buttonHeight) {
    super(startX, startY, buttonWidth, buttonHeight, "Reset");
  }

  void invokePayload() {
    polygonInProgress = false;
    lineInProgress = false;
    numberOfPointsInCurrentPolygon = 0;
    currentLinePositionsList = new ArrayList<LinePositions>();
    polygonList = new ArrayList<Polygon>();
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class UndoButton extends Button {
  UndoButton(int startX, int startY, int buttonWidth, int buttonHeight) {
    super(startX, startY, buttonWidth, buttonHeight, "Undo");
  }

  void invokePayload() {
    if (polygonInProgress || lineInProgress || polygonList.size() == 0) {
      return;
    }

    Polygon polygonToRemove = polygonList.get(polygonList.size()-1);
    polygonList.remove(polygonToRemove);

    redoPolygonList.push(polygonToRemove);
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class RedoButton extends Button {
  RedoButton(int startX, int startY, int buttonWidth, int buttonHeight) {
    super(startX, startY, buttonWidth, buttonHeight, "Redo");
  }
  void invokePayload() {
    if (redoPolygonList.size() > 0) {
      polygonList.add(redoPolygonList.pop());
    }
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class SaveButton extends Button {
  SaveButton(int startX, int startY, int buttonWidth, int buttonHeight) {
    super(startX, startY, buttonWidth, buttonHeight, "Save");
  }

  void invokePayload() {
    if(polygonList.size() == 0)
      return;
    
    try
    {
      String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
      String folder = "./TrackEditorWorkSpaces/";
      File file = new File(folder);
      
      if(!file.mkdir()){
        println("Didn't need to make folder");
    }
      
      
      PrintWriter writer = new PrintWriter(folder+timestamp+"_workspace.txt");
      //PrintWriter writer = new PrintWriter("C:/Users/User/Desktop/testing.txt");
      for (Polygon p : polygonList) {
        writer.println(p.toString());
      }
      writer.close();
      
    }
    catch(FileNotFoundException fnf) {
      System.err.println("File not found" + fnf.getMessage());
    }
    catch(Exception e) {
      System.err.println("Couldn't save file. "+ e.getMessage());
    }

    System.out.println("Saved file.");
  }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
class LoadButton extends Button {
  private PApplet pa;

  LoadButton(int startX, int startY, int buttonWidth, int buttonHeight) {
    super(startX, startY, buttonWidth, buttonHeight, "Load");
  }

  void invokePayload() {
    loadInProgress = true;
  }

  void fileSelected(File selection) {
    if (selection == null) {
      pa.println("Window was closed or the user hit cancel.");
    } else {
      pa.println("User selected " + selection.getAbsolutePath());
    }
  }
}
