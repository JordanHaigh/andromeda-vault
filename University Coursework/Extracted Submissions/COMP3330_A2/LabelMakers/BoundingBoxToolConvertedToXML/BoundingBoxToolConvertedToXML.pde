int startingImageNumber = 0;
int numberOfImages = 10;
int endingNumber = startingImageNumber + numberOfImages -1;
int upTo = 0;
PrintWriter output;
boolean topLeft = true;
boolean next = false;
PVector start = new PVector(0, 0);
PVector fin  = new PVector(0, 0);
PImage currentImage;
int scaleFactor =0;
String[] fileNames;
void setup() {
  fullScreen();

  fileNames = listFileNames("C:/Users/evang/OneDrive/Documents/Snagit/UNI/2018/COMP3330/Evans stuff/HWA2/COMP3330_A2/LabelMakers/BoundingBoxTool/BoundingBoxTool_pde/BoundingBoxToolConvertedToXML/data");//<<<<<<<<<<< change this to the directory of your pictures
  
  for(int i = 0 ; i< fileNames.length;i++){
    fileNames[i] = fileNames[i].substring(0, fileNames[i].length() - 4);
    print(fileNames[i]);
  }
  output = createWriter("data/" + fileNames[0] + ".xml");
  currentImage = loadImage(fileNames[0]+".jpg");

  scaleFactor = floor(max(currentImage.width/width, currentImage.height/height)+1);
  stroke(255, 0, 0);
}


void draw() {


  background(255);
  image(currentImage, 0, 0, currentImage.width/scaleFactor, currentImage.height/scaleFactor);

  if (!topLeft) {
    noFill();
    strokeWeight(2);
    stroke(255, 0, 0);
    rectMode(CORNERS);
    if (next) {
      rect(start.x, start.y, fin.x, fin.y);
    } else {
      rect(start.x, start.y, mouseX, mouseY);
    }
  }
  strokeWeight(0.5);
  line(mouseX, mouseY-200, mouseX, mouseY+200);
  line(mouseX-200, mouseY, mouseX+200, mouseY);
}

void mousePressed() {

  if (!topLeft) {
    if (next) {
      if (mouseButton == RIGHT) {
        topLeft = true;
        next = false;
      } else {


        switchBoxes();
        output.print("<annotation>");
        output.print("<folder>SmallerImages</folder>");
        output.print("<filename>" + fileNames[upTo] +".jpg</filename>");
        output.print("<path>C:/Users/Jordan/Desktop/MIImagesXML/SmallerImages/SmallPicture (1).jpg</path>");
        output.print("<source>");
        output.print("<database>Unknown</database>");
        output.print("</source>");
        output.print("<size>");
        output.print("<width>"+ currentImage.width +"</width>");
        output.print("<height>" + currentImage.height + "</height>");
        output.print("<depth>3</depth>");
        output.print("</size>");
        output.print("<segmented>0</segmented>");
        output.print("<object>");
        output.print("<name>key</name>");
        output.print("<pose>Unspecified</pose>");
        output.print("<truncated>0</truncated>");
        output.print("<difficult>0</difficult>");
        output.print("<bndbox>");
        output.print("<xmin>"+ floor(start.x)*scaleFactor +"</xmin>");
        output.print("<ymin>"+ floor(start.y)*scaleFactor +"</ymin>");
        output.print("<xmax>"+ floor(fin.x)*scaleFactor +"</xmax>");
        output.print("<ymax>"+ floor(fin.x)*scaleFactor +"</ymax>");
        output.print("</bndbox>");
        output.print("</object>");
        output.print("</annotation>");

        output.flush(); // Writes the remaining data to the file
        output.close(); // Finishes the file


        upTo ++;

        if (upTo >= fileNames.length) {

          exit(); // Stops the program
          return;
        }
        currentImage =  loadImage(fileNames[upTo] + ".jpg");
        output = createWriter("data/" + fileNames[upTo] + ".xml");


        scaleFactor = floor(max(currentImage.width/width, currentImage.height/height)+1);
        next = false;
        topLeft = true;
      }
    } else {
      //output.print(mouseX*scaleFactor);
      //output.print(", ");
      //output.print(mouseY*scaleFactor);
      next = true;
      fin = new PVector(mouseX, mouseY);
    }
  } else {
    //output.println();
    //output.print(mouseX*scaleFactor);
    //output.print(", ");
    //output.print(mouseY*scaleFactor);
    //output.print(", ");
    start = new PVector(mouseX, mouseY);
    topLeft = false;
  }
}


void switchBoxes() {
  if (start.x > fin.x) {
    float temp = start.x;
    start.x = fin.x;
    fin.x = temp;
  }
  if (start.y > fin.y) {
    float temp = start.y;
    start.y = fin.y;
    fin.y = temp;
  }
}

// This function returns all the files in a directory as an array of Strings  
String[] listFileNames(String dir) {
  File file = new File(dir);
  if (file.isDirectory()) {
    String names[] = file.list();
    return names;
  } else {
    // If it's not a directory
    return null;
  }
}