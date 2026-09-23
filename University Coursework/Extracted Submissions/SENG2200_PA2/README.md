SENG2200 PA2
===========

# Abstract #
This program aims to read various shape coordinates from a line (Polygon, SemiCircle, Circle), calculate the area based on the coordinates and sort all shapes by area/origin distance or other means in ascending order.

This application implements a Circular Doubly Linked List with a single Sentinel as the underlying data structure.

This application was developed as an assignment for SENG2200 -Programming Paradigms and Languages at the University of Newcastle, 2017.

Developed By:

* Jordan Haigh - 3256730

# To Compile #
## Javac ##
* Compile the application using `javac PA2a.java`

## Maven ##
* Compile the appliaction using `mvn package`

# To Run #
Note: In order for the program to run, it requires a data file. 

There are multiple ways run this application:
## Javac 
* Run the application using `java PA2a [Name of Data File, including Extension]`

## Maven
* Run the application using `javac -jar [Name of .jar file that Maven Generates]`

# Input #

Each polygon is recognised by the starting glyph "P", the number of vertices, and their coordinates. 
    
For Example, `P 3 1 2 3 4 5 0` finds `3`Vertices with coordinates `(1,2)`, `(3,4)`, `(5,0)`
The program will create an end point to close the polygon

Each Circle is recognised by the starting glyph "C", the centre point, and its radius.

For Example, `C 0 0 7` creates a circle at the origin with a radius of 7

Each SemiCircle is recognised by the starting glyph "S", the base point, and its' perpendicular point

For Example, `S 0 0 4 4` creates a semi circle at the origin with its perpendicular point at `(4,4)`


# Output #
The application will return a display for each shape found in the file containing the coordinates and its area.

It will then display the shapes in  two lists: An unsorted List from the file input, and a list sorted by increasing area order.
