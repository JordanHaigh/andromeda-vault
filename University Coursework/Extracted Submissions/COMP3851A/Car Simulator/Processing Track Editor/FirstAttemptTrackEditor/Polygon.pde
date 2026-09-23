class Polygon {
  private ArrayList<LinePositions> lines = new ArrayList<LinePositions>();
  private int id;

  public Polygon(ArrayList<LinePositions> lines, int id) {
    this.lines = lines;
    this.id = id;
  }


  public ArrayList<LinePositions> getLines() { 
    return lines;
  }
  public int getID() {
    return id;
  }

  void render() {
    beginShape();
    for (LinePositions l : lines) {
      vertex(l.getStartX(), l.getStartY());
    }
    endShape(CLOSE);
  }

  void showPolygonPoints() {
    stroke(255);
    for (LinePositions l : lines) {
      circle(l.getStartX(), l.getStartY(), 2*strokeWeight);
    }
    stroke(0);
  }

  LinePositions findLineCorrespondingToSelectedPoint() {
    for (LinePositions l : lines) {
      if (checkIfMouseWithinRange(l.getStartX(), l.getStartY(), 2*strokeWeight))
        return l;
    }
    return null;
  }

  void manipulateShape(boolean removingPoint) {

    LinePositions l = findLineCorrespondingToSelectedPoint();
    if (l != null) {
      if (removingPoint) {
        polygonList.set(polygonList.indexOf(this), removePoint(l));
      } else {
        polygonList.set(polygonList.indexOf(this), rebuildPolygonWhileDraggingMouse(l));
      }
    }
  }


  Polygon rebuildPolygonWhileDraggingMouse(LinePositions l) {

    int currentPointIndex = lines.indexOf(l); //index of startX and startY line
    int previousPointIndex = currentPointIndex -1;

    if (previousPointIndex == -1) //if editing the initial point, make the previous index the final point of polygon 
      previousPointIndex = lines.size()-1;

    //update previous index's endX, endY
    LinePositions updatedPreviousLine = lines.get(previousPointIndex);
    updatedPreviousLine.setEndX(mouseX);
    updatedPreviousLine.setEndY(mouseY);

    //update currents startX, startY
    l.setStartX(mouseX);
    l.setStartY(mouseY);

    //update into polygon
    lines.set(previousPointIndex, updatedPreviousLine);
    lines.set(currentPointIndex, l);

    return this;
  }

  Polygon removePoint(LinePositions l) {

    //get previous point - check if argument is first point, if it is, make the previous index the final point of polygon
    int previousIndex = lines.indexOf(l) -1;
    if (previousIndex == -1) {
      previousIndex = lines.size()-1;
    }
    //get next point - check if argument is the final point, if it is, make the next index the initial point of the polygon
    int nextIndex = lines.indexOf(l)+1;
    if (nextIndex == lines.size()) {
      nextIndex = 0;
    }

    //attach previous endXY to next startXY

    lines.get(previousIndex).setEndX(lines.get(nextIndex).getStartX());
    lines.get(previousIndex).setEndY(lines.get(nextIndex).getStartY());


    //remove old point
    lines.remove(l);

    //return new poly
    return this;
  }


  //public float getArea()
  //{
  //  int initialX = lines.get(0).getStartX();
  //  int initialY = lines.get(0).getStartY();

  //  float xHalf = 0;
  //  float yHalf = 0;
  //  float sum = 0;

  //  for (int i = 0; i < lines.size()-1; i++)
  //  {
  //    xHalf = lines.get(i+1).getStartX()-initialX + lines.get(i).getStartX()-initialX;
  //    yHalf = lines.get(i+1).getStartY()-initialY - lines.get(i).getStartY()-initialY;
  //    sum += xHalf * yHalf;
  //  }

  //  return 0.5 * Math.abs(sum);
  //}

  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Polygon")
    .append(id)
    .append("{");
    
    for(LinePositions l : lines){
      sb.append("\n")
      .append(l.toString());
    }
    
    sb.append("\n}");
    
    return sb.toString();
        
  }
}
