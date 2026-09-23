SENG2200 PA1
===========

# Abstract #
This program aims to read various polygon coordinates from a line, calculate the area based on the coordinates and sort the polygons by area in ascending order.

This application implements a Circular Doubly Linked List with a single Sentinel as the underlying data structure.

This application was developed as an assignment for SENG2200 -Programming Paradigms and Languages at the University of Newcastle, 2017.

Developed By:

* Jordan Haigh - 3256730

# To Compile #
## Javac ##
* Compile the application using `javac PA1.java`

## Maven ##
* Compile the appliaction using `mvn package`

# To Run #
Note: In order for the program to run, it requires a data file. 

There are multiple ways run this application:
## Javac 
* Run the application using `java PA1 [Name of Data File, including Extension]`

## Maven
* Run the application using `javac -jar [Name of .jar file that Maven Generates]`

# Input #

Each polygon is recognised by the starting glyph "P", the number of vertices, and their coordinates. 
    
For example: `P 3 1 2 3 4 5 0` finds `3`Vertices with coordinates `(1,2)`, `(3,4)`, `(5,0)`

The program will create an end point to close the polygon

# Output #
The application will return a String for each Polygon found in the file containing the coordinates and its area.

It will then display the polygons as two lists: An unsorted List from the file input, and a list sorted by increasing area order.
