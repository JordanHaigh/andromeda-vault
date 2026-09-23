using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Grid : MonoBehaviour {

    public Vector3 startingPosition;
    public GridNode[,] grid;
    public int stepSize = 10;


    // Start is called before the first frame update
    void Start() {


    }

    // Update is called once per frame
    void Update() {

    }

    public void updateVisitedNodes(Vector3 currentCarPosition) {

        for(int i = 0; i < grid.GetLength(0); i++) {
            for(int j = 0; j < grid.GetLength(1); j++) {
                if(grid[i, j].isNearPosition(currentCarPosition)) {
                    if(!grid[i, j].alreadyVisited) {
                        
                        
                        grid[i, j].alreadyVisited = true;
                        resizeGridIfNeeded();
                        
                    }                
 
                }
            }
        }
    }

    public bool pointNearUnexploredNode(Vector3 point)
    {
        for (int i = 0; i < grid.GetLength(0); i++)
        {
            for (int j = 0; j < grid.GetLength(1); j++)
            {
                if (grid[i, j].isAtPosition(point))
                {
                    if (!grid[i, j].alreadyVisited)
                    {

                        return true;
                    }

                }
            }
        }
        return false;
    }

    public void createGrid(Vector3 start) {
        startingPosition = start;

        grid = new GridNode[3, 3];
        for(int i = 0; i < grid.GetLength(0); i++) {
            for(int j = 0; j < grid.GetLength(1); j++) {

                grid[i, j] = new GridNode(start + new Vector3((j - 1) * stepSize, 0, (i - 1) * stepSize), i, j);

            }
        }
        grid[1, 1].alreadyVisited = true;
    }

    public GridNode getClosestUnvisitedNode(Vector3 currentPos) {
        float minDist = 10000;
        GridNode closest = null;

        for(int i = 0; i < grid.GetLength(0); i++) {
            for(int j = 0; j < grid.GetLength(1); j++) {

                float distToNode = Vector3.Distance(currentPos, grid[i, j].getPosition());
                if(!grid[i, j].alreadyVisited && distToNode < minDist) {
                    minDist = distToNode;
                    closest = grid[i, j];
                }
            }
        }


        return closest;



    }

    public void resizeGridIfNeeded() {

        //check top row
        for(int i = 0; i < grid.GetLength(0); i++) {
            if(grid[i, 0].alreadyVisited) {
                //add row to top
                GridNode[,] newGrid = new GridNode[grid.GetLength(0), grid.GetLength(1) + 1];
                for(int ii = 0; ii < grid.GetLength(0); ii++) {
                    for(int jj = 0; jj < grid.GetLength(1); jj++) {
                        newGrid[ii, jj + 1] = grid[ii, jj];
                    }
                }

                //create new grid nodes
                for(int ii = 0; ii < newGrid.GetLength(0); ii++) {
                    newGrid[ii, 0] = new GridNode(newGrid[ii, 1].getPosition() + new Vector3(-stepSize, 0, 0), ii, 0);
                }

                grid = newGrid;
                break;
            }
        }


        //check bottom row
        for(int i = 0; i < grid.GetLength(0); i++) {
            if(grid[i, grid.GetLength(1) - 1].alreadyVisited) {
                //add row to bottom
                GridNode[,] newGrid = new GridNode[grid.GetLength(0), grid.GetLength(1) + 1];
                for(int ii = 0; ii < grid.GetLength(0); ii++) {
                    for(int jj = 0; jj < grid.GetLength(1); jj++) {
                        newGrid[ii, jj] = grid[ii, jj];
                    }
                }
                //create new grid nodes
                for(int ii = 0; ii < newGrid.GetLength(0); ii++) {
                    newGrid[ii, newGrid.GetLength(1) - 1] = new GridNode(newGrid[ii, newGrid.GetLength(1) - 2].getPosition() + new Vector3(stepSize, 0, 0), ii, 0);
                }

                grid = newGrid;
                break;
            }
        }



        //check left col
        for(int j = 0; j < grid.GetLength(1); j++) {
            if(grid[0, j].alreadyVisited) {
                //add row to top
                GridNode[,] newGrid = new GridNode[grid.GetLength(0) + 1, grid.GetLength(1)];

                //copy the old grid into the new grid
                for(int ii = 0; ii < grid.GetLength(0); ii++) {
                    for(int jj = 0; jj < grid.GetLength(1); jj++) {
                        newGrid[ii + 1, jj] = grid[ii, jj];
                    }
                }

                //create new grid nodes
                for(int jj = 0; jj < newGrid.GetLength(1); jj++) {
                    newGrid[0, jj] = new GridNode(newGrid[1, jj].getPosition() + new Vector3(0, 0, -stepSize), 0, jj);
                }

                grid = newGrid;
                break;
            }
        }


        //check right col
        for(int j = 0; j < grid.GetLength(1); j++) {
            if(grid[grid.GetLength(0) - 1, j].alreadyVisited) {
                //add row to bottom
                GridNode[,] newGrid = new GridNode[grid.GetLength(0) + 1, grid.GetLength(1)];
                for(int ii = 0; ii < grid.GetLength(0); ii++) {
                    for(int jj = 0; jj < grid.GetLength(1); jj++) {
                        newGrid[ii, jj] = grid[ii, jj];
                    }
                }
                //create new grid nodes
                for(int jj = 0; jj < newGrid.GetLength(1); jj++) {
                    newGrid[newGrid.GetLength(0) - 1, jj] = new GridNode(newGrid[grid.GetLength(0) - 2, jj].getPosition() + new Vector3(0, 0, stepSize), 0, jj);
                }



                grid = newGrid;
                //Debug.Break();

                break;
            }
        }

    }

    public void drawGrid() {
        for(int i = 0; i < grid.GetLength(0); i++) {
            for(int j = 0; j < grid.GetLength(1); j++) {
                grid[i, j].drawNode();

            }
        }
    }
}

public class GridNode {
    Vector3 position;
    public bool alreadyVisited = false;
    int gridX;
    int gridY;
    float size = 4.9f;


    public bool isAtPosition(Vector3 pos) {

        float maxDistanceWeConsiderVisited = size;
        pos.y = 0;
        if(Vector3.Distance(pos, position) < maxDistanceWeConsiderVisited) {
            return true;
        } else {
            return false;
        }
    }


    public bool isNearPosition(Vector3 pos)
    {

        float maxDistanceWeConsiderVisited = size * 2;
        pos.y = 0;
        if (Vector3.Distance(pos, position) < maxDistanceWeConsiderVisited)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public GridNode(Vector3 pos, int gridX, int gridY) {
        position = pos;
        this.gridX = gridX;
        this.gridY = gridY;
    }


    public Vector3 getPosition() {
        return position;
    }


    public void drawNode() {
        Color color = Color.red;

        if(this.alreadyVisited) {
            color = Color.green;
        }


        Vector3 topLeft = position - new Vector3(size, 0, 0) - new Vector3(0, 0, size);
        Vector3 topRight = position + new Vector3(size, 0, 0) - new Vector3(0, 0, size);
        Vector3 bottomLeft = position - new Vector3(size, 0, 0) + new Vector3(0, 0, size);
        Vector3 bottomRight = position + new Vector3(size, 0, 0) + new Vector3(0, 0, size);


        Debug.DrawLine(topLeft, topRight, color);
        Debug.DrawLine(topRight, bottomRight, color);
        Debug.DrawLine(bottomRight, bottomLeft, color);
        Debug.DrawLine(bottomLeft, topLeft, color);




    }


}


