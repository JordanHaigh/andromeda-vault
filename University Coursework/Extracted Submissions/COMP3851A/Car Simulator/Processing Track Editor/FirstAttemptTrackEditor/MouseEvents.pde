class MouseEvents {


  // ______ _       _     _                                    
  // | ___ (_)     | |   | |                                   
  // | |_/ /_  __ _| |__ | |_   _ __ ___   ___  _   _ ___  ___ 
  // |    /| |/ _` | '_ \| __| | '_ ` _ \ / _ \| | | / __|/ _ \
  // | |\ \| | (_| | | | | |_  | | | | | | (_) | |_| \__ \  __/
  // \_| \_|_|\__, |_| |_|\__| |_| |_| |_|\___/ \__,_|___/\___|
  //           __/ |                                           
  //          |___/                                      

  void rightMousePressed(MouseEvent evt) {
    //Check if there are polygon points to remove first
    //check 0 - do nothing
    //check 1 - reset polygon
    //check n - remove point from iterator and list

    if (polygonInProgress) {
      removePolygonPointWhilstInProgress();
    } else if (shiftToggled) {
      //find out which polygon and point that mouse is over
      Polygon p = findPolygonCorrespondingToSelectedPoint();

      if (p != null) {
        if (p.getLines().size() == 3) {
          polygonList.remove(polygonList.indexOf(p));//remove entire polygon
          return;
        } 

        p.manipulateShape(true);
      }
    }
  }


  void removePolygonPointWhilstInProgress() {
    if (numberOfPointsInCurrentPolygon == 0) {
      return;
    } else if (numberOfPointsInCurrentPolygon == 1) {
      lineInProgress = false;
      polygonInProgress = false;
      numberOfPointsInCurrentPolygon--;
    } else {
      removeLastPolygonPointDuringCreation();
    }
  }

  void removeLastPolygonPointDuringCreation() {
    //remove final point
    //change lineStart to the point before

    numberOfPointsInCurrentPolygon--;

    currentLinePositionsList.remove(currentLinePositionsList.size()-1);

    if (numberOfPointsInCurrentPolygon > 1) {
      lineStartX = currentLinePositionsList.get(currentLinePositionsList.size()-1).getEndX();
      lineStartY = currentLinePositionsList.get(currentLinePositionsList.size()-1).getEndY();
    } else {
      lineStartX = polygonStartX;
      lineStartY = polygonStartY;
    }

    previousPointX = lineStartX;
    previousPointY = lineStartY;
  }



  //  _           __ _                                    
  // | |         / _| |                                   
  // | |     ___| |_| |_   _ __ ___   ___  _   _ ___  ___ 
  // | |    / _ \  _| __| | '_ ` _ \ / _ \| | | / __|/ _ \
  // | |___|  __/ | | |_  | | | | | | (_) | |_| \__ \  __/
  // \_____/\___|_|  \__| |_| |_| |_|\___/ \__,_|___/\___|



  void leftMousePressed(MouseEvent evt) {
    Button button = checkIfButtonBeingClicked();

    if (button != null) {
      button.invokePayload();
      return;
    }

    if(isPlacingCar){
      car = new Car(mouseX, mouseY);
      return;
    }
    
    if (shiftToggled) {
      Polygon p = findPolygonCorrespondingToSelectedPoint();
      if (p != null) {

        //find what the point being held is
        LinePositions l = p.findLineCorrespondingToSelectedPoint();
        if (l != null) {
          //rebuild polygon whilst dragging mouse
          editingAPolygonPoint = true; //used in mouseDragged function
        }
        return;
      }
      
      return;
    }


    //Making a polygon
    if (!polygonInProgress) {
      polygonInProgress = true;
      polygonStartX = mouseX;
      polygonStartY = mouseY;
    }

    if (evt.getCount() == 2 && numberOfPointsInCurrentPolygon >= 3) {
      completePolygon();
      return;
    }

    if (!lineInProgress) {
      startDrawingPolygon();
    } else {
      continueDrawingPolygon();
    }
  }

  Button checkIfButtonBeingClicked() {
    for (Button b : buttonList) {
      if (b.isMouseOverButton())
        return b;
    }
    return null;
  }


  void startDrawingPolygon() {
    lineInProgress = true;
    lineStartX = mouseX;
    lineStartY = mouseY;

    previousPointX = mouseX;
    previousPointY = mouseY;

    numberOfPointsInCurrentPolygon++;
    println("Number of points: "+ numberOfPointsInCurrentPolygon);
  }

  void continueDrawingPolygon() {
    if (mouseX == previousPointX && mouseY == previousPointY) //clicking in the same spot
      return;

    if (checkIfMouseWithinRange(polygonStartX, polygonStartY, width/polygonDenominator) && numberOfPointsInCurrentPolygon < 3) //if close to start and not valid polygon yet
      return;


    lineEndX = mouseX;
    lineEndY = mouseY;

    numberOfPointsInCurrentPolygon++;
    println("Number of points: "+ numberOfPointsInCurrentPolygon);


    if (!checkIfMouseWithinRange(polygonStartX, polygonStartY, width/polygonDenominator)) {
      currentLinePositionsList.add(new LinePositions(lineStartX, lineStartY, lineEndX, lineEndY));
      println("New Line: \n\t StartX: " + lineStartX + "\n\t StartY: " + lineStartY + "\n\t EndX: " + lineEndX + "\n\t EndY: " + lineEndY);
      //Restart lineStarts for next section of polygon
      lineStartX = mouseX;
      lineStartY = mouseY;
    } else {
      completePolygon();
      return;
    }
  }

  void completePolygon() {
    currentLinePositionsList.add(new LinePositions(lineStartX, lineStartY, polygonStartX, polygonStartY)); //complete polygon
    Polygon p = new Polygon(currentLinePositionsList, idGenerator++);

    polygonList.add(p);

    resetVariablesAfterNewPolygon();

    println("Polygon Closed. ID is: " + p.getID());
    
    redoPolygonList = new LinkedList<Polygon>();
    //println("Area: "+ p.getArea());
  }

  void resetVariablesAfterNewPolygon() {
    polygonInProgress = false;
    lineInProgress = false;
    numberOfPointsInCurrentPolygon = 0;
    currentLinePositionsList = new ArrayList<LinePositions>();
  }


  void manipulateShapeOfPolygon(boolean removingPoint) {
    Polygon p = findPolygonCorrespondingToSelectedPoint();
    if (p != null)
      p.manipulateShape(removingPoint);
  }



  // ___  ____     _     _ _                                       
  // |  \/  (_)   | |   | | |                                      
  // | .  . |_  __| | __| | | ___   _ __ ___   ___  _   _ ___  ___ 
  // | |\/| | |/ _` |/ _` | |/ _ \ | '_ ` _ \ / _ \| | | / __|/ _ \
  // | |  | | | (_| | (_| | |  __/ | | | | | | (_) | |_| \__ \  __/
  // \_|  |_/_|\__,_|\__,_|_|\___| |_| |_| |_|\___/ \__,_|___/\___|
}
