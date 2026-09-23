import java.util.LinkedList;

int polygonStartX, polygonStartY;
int lineStartX, lineStartY, lineEndX, lineEndY;
int previousPointX, previousPointY;

int polygonDenominator = 200;

boolean lineInProgress;
boolean polygonInProgress;
boolean shiftToggled;
boolean isPlacingCar;
boolean editingAPolygonPoint;
boolean loadInProgress;

ArrayList<Polygon> polygonList = new ArrayList<Polygon>();
LinkedList<Polygon> redoPolygonList = new LinkedList<Polygon>();


ArrayList<LinePositions> currentLinePositionsList = new ArrayList<LinePositions>();

int numberOfPointsInCurrentPolygon = 0;

int buttonWidth = 100;
int buttonHeight  = 50;

int strokeWeight = 4;

int idGenerator = 0; //todo refactor to singleton?
ArrayList<Button> buttonList = new ArrayList<Button>();

Car car;

MouseEvents mouseEvents = new MouseEvents();
String mode = "Placing Track";
PFont f;


void setup() {
  size(1920, 1080);
  background(204);
  fill(0);
  strokeWeight(strokeWeight);
  f = createFont("Arial", 16, true);
  buttonList.add(new UndoButton( 10 * 1 + buttonWidth * 0, 1080-buttonHeight-10, buttonWidth, buttonHeight));
  buttonList.add(new RedoButton( 10 * 2 + buttonWidth * 1, 1080-buttonHeight-10, buttonWidth, buttonHeight));
  buttonList.add(new ResetButton(10 * 3 + buttonWidth * 2, 1080-buttonHeight-10, buttonWidth, buttonHeight));

  buttonList.add(new SaveButton( 1920-2*buttonWidth-2*10, 1080-buttonHeight-10, buttonWidth, buttonHeight));
  buttonList.add(new LoadButton( 1920-1*buttonWidth-1*10, 1080-buttonHeight-10, buttonWidth, buttonHeight));
}

void draw() {
  background(204);
  text(mode, 80, 20);



  if (loadInProgress) {
    loadInProgress = false; //stops draw function calling it so many bloody times
    selectInput("Select a file to process:", "fileSelected");
  }

  //render button list
  for (Button b : buttonList) {
    b.render(255, 255, 255);
  }

  //render polygons 
  fill(100, 100, 100);
  for (int i = 0; i < polygonList.size(); i++) {
    if (i > 0)
      fill(204);

    polygonList.get(i).render();
    if (shiftToggled) {
      polygonList.get(i).showPolygonPoints();
    }
  }


  if (car != null) {
    car.render();
  }
  fill(0);



  //render line to current mouse coord from start coords 
  if (lineInProgress) {
    line(lineStartX, lineStartY, mouseX, mouseY);
  }

  //render all lines currently in list
  for (LinePositions l : currentLinePositionsList) {
    line(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
  }
}

void keyPressed() {
  if (keyCode == SHIFT) {
    if (!shiftToggled) {
      shiftToggled = true;
      mode = "Editing Track";
      println("shift on");
    } else if (isPlacingCar) {
      return;
    } else {
      shiftToggled = false;
      println("shift off");
      mode = "Placing Track";
    }
  }
  if (keyCode == TAB) {
    if (shiftToggled) {
      return;
    }
    if (isPlacingCar) {
      isPlacingCar = false;
      mode = "Placing Track";
    } else {
      isPlacingCar = true;
      mode = "Placing Car";
    }
  }
  if (keyCode == 'r' && car != null) {
    println("rotating..");
    car.rotateCar();
  }
}



void mouseDragged() {
  if (shiftToggled && editingAPolygonPoint) {
    mouseEvents.manipulateShapeOfPolygon(false);
  }
}

void mouseReleased() {
  if (shiftToggled && editingAPolygonPoint)
    editingAPolygonPoint = false;
}

void mousePressed(MouseEvent evt) {
  if (mouseButton == LEFT) {
    mouseEvents.leftMousePressed(evt);
  } else if (mouseButton == RIGHT) {
    mouseEvents.rightMousePressed(evt);
  } else {
    return; //middle mouse functionality?
  }
}


void fileSelected(File selection) {
  if (selection == null) {
    println("Window was closed or the user hit cancel.");
  } else {
    String file = selection.getAbsolutePath();
    WorkspaceFileReader reader = new WorkspaceFileReader();
    try {
      polygonList = reader.readFile(file);
    }
    catch(Exception e) {
      println("Couldn't open workspace. " + e.getMessage());
    }
    println("Opened workspace!");
  }
}

Polygon findPolygonCorrespondingToSelectedPoint() {
  for (Polygon p : polygonList) {
    for (LinePositions l : p.getLines()) {
      if (checkIfMouseWithinRange(l.getStartX(), l.getStartY(), strokeWeight))
        return p;
    }
  }
  return null;
}



boolean checkIfMouseWithinRange(int xValue, int yValue, int comfortRoom) {
  return(
    (xValue - comfortRoom < mouseX && mouseX <  xValue + comfortRoom) && 
    (yValue - comfortRoom < mouseY && mouseY <  yValue + comfortRoom)
    );
}



//redo array list of lines to be array list of polygons, - done
//polygon area?
//right click - undo last line - done
//undo button - remove single polygon - done
//move individual points on polygons - done
//remove point from polygon - done
//add point to polygon
//redo button - queue that the pushes whatever the undo button does, when new polygon created and polygons in redo queue, clear queue - done
//save button - done, saves to processing.exe folder
//load button - done
//color polygon - check if one polygon is bigger than other? or always draw inside of track then outside of track? - kinda done?
//  could also have multiple polygons, need to take that into consideration 


//look into beginContour() method in pshape
//smooth polygon
