using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CarPathFinding : MonoBehaviour
{
    public PathFindingPath path;
    private CompassController compassController;
    private CarController carController;
    public static float nodeSize = 8f;

    private Vector3 target;

    private GridNode targetNode = new GridNode(new Vector3(), 0, 0);
    private Grid grid;

    public bool active = false;
    // Start is called before the first frame update
    void Start()
    {
        if(!active) {
            return;
        }
        generateNewRandomPath();

        //generateNewPathAStar(new Vector3(37,0,-20));
        //path.drawPath(this.transform.position);

        compassController = GetComponent<CompassController>();
        carController = GetComponent<CarController>();

        grid = new Grid();
        grid.createGrid(this.transform.position);



        //targetNode = grid.getClosestUnvisitedNode(this.transform.position);
        //if (targetNode != null)
        //{
        //    generateNewPathAStar(targetNode.getPosition());
        //}
        createNewBFSPath();
    }




    // Update is called once per frame
    void FixedUpdate()
    {
        if(!active) {
            return;
        }
        if (Input.GetMouseButtonDown(0))
        {
            OnMouseDown();
        }
        path.updatePath(this.transform.position);
        if (path.isEmpty() || carController.justKilledForPathFinding)
        {

            carController.justKilledForPathFinding = false;
            targetNode.alreadyVisited = true;
            grid.resizeGridIfNeeded();
            createNewBFSPath();

        }
        //    targetNode = grid.getClosestUnvisitedNode(this.transform.position);
        //    if(targetNode != null) {

        //        while (!generateNewPathAStar(targetNode.getPosition())) {
        //            targetNode.alreadyVisited = true;
        //            targetNode = grid.getClosestUnvisitedNode(this.transform.position);
        //            if(targetNode == null) {
        //                generateNewRandomPath();
        //                break;
        //            }
        //        }
        //    } else {
        //        generateNewRandomPath();
        //    }

        //}
        grid.updateVisitedNodes(this.transform.position);



        if (!path.visionToNextNode(this.transform.position))
        {
            Debug.Log("Cannot See next Node");
            createNewBFSPath();
        }






        compassController.setTarget(path.getNextNode().getPosition());
        //path.drawPath(this.transform.position);
        path.drawPath(this.transform.position);
        //grid.drawGrid();
    }


    private void createNewBFSPath()
    {

        if (generateNewBFSPathToUnexploredNode())
        {
            targetNode = grid.getClosestUnvisitedNode(path.getFinalNode().getPosition());
        }
        else
        {
            Debug.Log("No unexplored nodes are available");
            Debug.Break();
        }
    }

    private void OnMouseDown()
    {

        Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        RaycastHit hit;
        if (Physics.Raycast(ray, out hit))
        {
            target = hit.point;
            generateNewPathAStar(target);

        }

    }


    public void generateNewRandomPath()
    {
        path = new PathFindingPath();
        path.addNode(new PathNode(this.transform.position));
        int numberOfSteps = UnityEngine.Random.Range(10, 20);


        //reduce the step distance if no paths are found


        int[] stepDistances = { 40, 30, 20, 10, 5 };
        int stepDistance;


        Vector3 previousDirection = new Vector3(0, 0, 0);


        for (int i = 0; i < numberOfSteps; i++)
        {

            bool foundDirection = false;
            for (int stepDistanceNo = 0; stepDistanceNo < stepDistances.Length; stepDistanceNo++)
            {
                stepDistance = stepDistances[stepDistanceNo];
                Vector3[] possibleDirections = { new Vector3(0, 0, stepDistance), new Vector3(0, 0, -stepDistance), new Vector3(-stepDistance, 0, 0), new Vector3(stepDistance, 0, 0) };
                //shuffle the array by switching random elements
                for (int j = 0; j < 10; j++)
                {
                    int rand1 = UnityEngine.Random.Range(0, 4);
                    int rand2 = UnityEngine.Random.Range(0, 4);

                    Vector3 temp = possibleDirections[rand1];
                    possibleDirections[rand1] = possibleDirections[rand2];
                    possibleDirections[rand2] = temp;
                }

                foundDirection = false;
                for (int j = 0; j < 4; j++)
                {

                    //dont turn around
                    if (possibleDirections[j].normalized + previousDirection.normalized == new Vector3(0, 0, 0))
                    {
                        continue;

                    }

                    if (path.isDirectionClear(possibleDirections[j]))
                    {
                        //if the previous and next nodes are in the same direction then instead of having 2 inline simply move the final one to be further
                        if (possibleDirections[j].normalized == previousDirection.normalized)
                        {
                            path.moveFinalNode(possibleDirections[j]);
                        }
                        else
                        {
                            path.moveInDirection(possibleDirections[j]);

                        }
                        previousDirection = possibleDirections[j];
                        foundDirection = true;
                        break;
                    }

                }
                //if we found a direction then we dont need to try smaller step sizes
                if (foundDirection)
                {
                    break;
                }

            }

            if (!foundDirection)
            {
                //dead end
                //finish path

                i = numberOfSteps;
            }
        }






    }


    public void setTarget(Vector3 newTarget)
    {
        target = newTarget;
    }

    public bool generateNewBFSPathToUnexploredNode()
    {
        int stepDistance = 7;
        int maxSteps = 500;

        List<PathNode> allNodes = new List<PathNode>();
        allNodes.Add(new PathNode(this.transform.position));

        PathFindingPath startingPath = new PathFindingPath();
        startingPath.addNode(allNodes[0]);


        List<PathFindingPath> allPaths = new List<PathFindingPath>();
        allPaths.Add(startingPath);


        while (true)
        {
            //if there are no more paths then set winning path to current path
            if (allPaths.Count == 0)
            {

                //Debug.LogError("No Path Found to Target");
                return false;


            }

            //pop front path
            PathFindingPath currentPath = allPaths[0];
            allPaths.RemoveAt(0);
            //if this path is longer than the max then ignore it
            if (currentPath.getLength() > maxSteps)
            {
                continue;
            }


            //if the current path has reached the target
            if (currentPath.finalNodeIsNearUnexploredNode(grid))
            {
                //if the current path is shorter than the current 
                path = currentPath.clone();
                return true;
            }


            PathNode finalNode = currentPath.getFinalNode();

            //if the final node of the current path hasn't been visited yet\
            if (!finalNode.alreadyVisited)
            {

                //this is the shortest found path to this point
                finalNode.alreadyVisited = true;

                //now we need to add all the paths possible from this node to the allPaths list

                List<PathNode> edges = finalNode.getEdges(allNodes, stepDistance);

                foreach (PathNode n in edges)
                {
                    PathFindingPath p = currentPath.clone();
                    p.addNode(n);
                    if (p.getFinalNode().alreadyVisited)
                    {
                        continue;
                    }

                    //add p to the end of the list

                    allPaths.Add(p);
                }
            }
        }
    }


    public bool generateNewPathAStar(Vector3 newTarget)
    {

        int stepDistance = 7;
        int maxSteps = 50;

        setTarget(newTarget);

        List<PathNode> allNodes = new List<PathNode>();
        allNodes.Add(new PathNode(this.transform.position));
        //allNodes[0].setEdges(allNodes, stepDistance);
        PathFindingPath startingPath = new PathFindingPath();
        startingPath.addNode(allNodes[0]);


        List<PathFindingPath> allPaths = new List<PathFindingPath>();
        allPaths.Add(startingPath);

        PathFindingPath winningPath = null;

        while (true)
        {
            //if there are no more paths then set winning path to current path
            if (allPaths.Count == 0)
            {
                if (winningPath != null)
                {
                    path = winningPath;
                    return true;//we done
                }
                else
                {
                    //Debug.LogError("No Path Found to Target");
                    return false;
                }

            }

            //pop front path
            PathFindingPath currentPath = allPaths[0];
            allPaths.RemoveAt(0);
            //if this path is longer than the max then ignore it
            if (currentPath.getLength() > maxSteps)
            {
                continue;
            }


            //if this path is already longer than the best path weve found
            if (winningPath != null && currentPath.getLength() >= winningPath.getLength())
            {
                continue;//go to next path
            }


            //if the current path has reached the target
            if (currentPath.distanceToTarget(target) < stepDistance)
            {
                //if the current path is shorter than the current best
                if (winningPath == null || currentPath.getLength() < winningPath.getLength())
                {
                    winningPath = currentPath.clone();//set the winning path as the current path
                    continue;//go to next path
                }

            }


            PathNode finalNode = currentPath.getFinalNode();

            //if the final node of the current path either hasn't been visited yet or is the shortest path to that point
            if (!finalNode.alreadyVisited || finalNode.shortestDistanceToThisPoint > currentPath.getLength())
            {

                //this is the shortest found path to this point
                finalNode.alreadyVisited = true;
                finalNode.shortestDistanceToThisPoint = currentPath.getLength();


                //now we need to add all the paths possible from this node to the allPaths list

                List<PathNode> edges = finalNode.getEdges(allNodes, stepDistance);

                foreach (PathNode n in edges)
                {
                    PathFindingPath p = currentPath.clone();
                    p.addNode(n);
                    if (p.getFinalNode().alreadyVisited && p.getLength() > p.getFinalNode().shortestDistanceToThisPoint)
                    {
                        continue;
                    }
                    allPaths.Add(p);
                }
            }


            //sort paths minimising pathDistance + distance to target

            List<PathFindingPath> oldAllPaths = allPaths;
            allPaths = new List<PathFindingPath>();

            while (oldAllPaths.Count > 0)
            {
                float min = 10000;
                int minIndex = 0;

                for (int i = 0; i < oldAllPaths.Count; i++)
                {
                    if (oldAllPaths[i].distanceToTarget(target) + oldAllPaths[i].getLength() * stepDistance < min)
                    {
                        min = oldAllPaths[i].distanceToTarget(target) + oldAllPaths[i].getLength() * stepDistance;
                        minIndex = i;
                    }
                }
                allPaths.Add(oldAllPaths[minIndex]);
                oldAllPaths.RemoveAt(minIndex);
            }

        }

    }





    public class PathNode
    {
        Vector3 position;
        public List<PathNode> edges = new List<PathNode>();
        public bool alreadyVisited = false;
        public float shortestDistanceToThisPoint = 100000;
        int gridX;
        int gridY;

        public void setEdges(List<PathNode> allNodes, float stepDistance)
        {
            Vector3[] possibleDirections = { new Vector3(0, 0, stepDistance), new Vector3(0, 0, -stepDistance), new Vector3(-stepDistance, 0, 0), new Vector3(stepDistance, 0, 0) };

            for (int j = 0; j < 4; j++)
            {
                if (isDirectionClear(possibleDirections[j]))
                {
                    Vector3 edgeNodePosition = position + possibleDirections[j];

                    bool edgeFound = false;
                    foreach (PathNode n in allNodes)
                    {
                        if (n.isAtPosition(edgeNodePosition))
                        {
                            edgeFound = true;
                            n.addEdge(this);
                            addEdge(n);
                        }


                    }

                    if (!edgeFound)
                    {
                        PathNode edgeNode = new PathNode(edgeNodePosition);
                        edgeNode.addEdge(this);
                        addEdge(edgeNode);
                        allNodes.Add(edgeNode);
                        edgeNode.setEdges(allNodes, stepDistance);
                    }

                }
            }
        }

        public List<PathNode> getEdges(List<PathNode> allNodes, float stepDistance)
        {
            //if the edges are already calculated then just return that
            if (edges.Count != 0)
            {
                return edges;
            }

            Vector3[] possibleDirections = { new Vector3(0, 0, stepDistance), new Vector3(0, 0, -stepDistance), new Vector3(-stepDistance, 0, 0), new Vector3(stepDistance, 0, 0) };

            for (int j = 0; j < 4; j++)
            {
                if (isDirectionClear(possibleDirections[j]))
                {
                    Vector3 edgeNodePosition = position + possibleDirections[j];

                    bool edgeFound = false;
                    foreach (PathNode n in allNodes)
                    {
                        if (n.isAtPosition(edgeNodePosition))
                        {
                            edgeFound = true;
                            addEdge(n);
                        }
                    }

                    if (!edgeFound)
                    {
                        PathNode edgeNode = new PathNode(edgeNodePosition);
                        addEdge(edgeNode);
                        allNodes.Add(edgeNode);
                    }

                }
            }

            return edges;
        }


        public void addEdge(PathNode edge)
        {
            if (!edges.Contains(edge))
            {
                edges.Add(edge);
            }
        }

        public bool isAtPosition(Vector3 pos)
        {
            if (Vector3.Distance(pos, position) < 0.1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        public PathNode(Vector3 pos)
        {
            position = pos;
        }

        public Vector3 getPosition()
        {
            return position;
        }

        public void moveNode(Vector3 direction)
        {
            position += direction;
        }


        public bool isDirectionClear(Vector3 direction)
        {
            float lineWidth = 2f;
            Quaternion rotation = Quaternion.Euler(0, 90, 0);
            Vector3 normal = rotation * direction;
            normal = normal.normalized;
            normal = normal * lineWidth;
            normal = normal * 0.5f;

            Vector3 testPoint1 = position + normal;
            Vector3 testPoint2 = position - normal;

            List<Vector3> testingStartPoints = new List<Vector3>();
            testingStartPoints.Add(this.position);
            testingStartPoints.Add(testPoint1);
            testingStartPoints.Add(testPoint2);

            foreach(Vector3 startPoint in testingStartPoints)
            {
                Ray ray = new Ray(startPoint, direction.normalized);
                var hitObjects = Physics.RaycastAll(ray, direction.magnitude);
                for (int i = 0; i < hitObjects.Length; i++)
                {
                    if (hitObjects[i].collider.name.Contains("SlamWall"))
                    {
                        return false;
                    }
                }
            }


         
            return true;



        }
    }

    public class PathFindingPath
    {
        private List<PathNode> nodes;

        public PathFindingPath()
        {
            nodes = new List<PathNode>();
        }

        public void addNode(PathNode n)
        {
            nodes.Add(n);
        }

        public bool isDirectionClear(Vector3 direction)
        {
            float lineWidth = 2f;


            Vector3 currentFinalPos = nodes[nodes.Count - 1].getPosition();



            Quaternion rotation = Quaternion.EulerAngles(0, 90, 0);
            Vector3 normal = rotation * direction;
            normal = normal.normalized;
            normal = normal * lineWidth;
            normal = normal * 0.5f;

            Vector3 testPoint1 = currentFinalPos + normal;
            Vector3 testPoint2 = currentFinalPos - normal;



            Ray ray = new Ray(currentFinalPos, direction.normalized);
            var hitObjects = Physics.RaycastAll(ray, direction.magnitude);
            for (int i = 0; i < hitObjects.Length; i++)
            {
                if (hitObjects[i].collider.name.Contains("SlamWall"))
                {
                    return false;
                }
            }

            //test another point to simulate width
            ray = new Ray(testPoint1, direction.normalized);
            hitObjects = Physics.RaycastAll(ray, direction.magnitude);
            for (int i = 0; i < hitObjects.Length; i++)
            {
                if (hitObjects[i].collider.name.Contains("SlamWall"))
                {
                    return false;
                }
            }

            //test third point to simulate other end of line distancee
            ray = new Ray(testPoint2, direction.normalized);
            hitObjects = Physics.RaycastAll(ray, direction.magnitude);
            for (int i = 0; i < hitObjects.Length; i++)
            {
                if (hitObjects[i].collider.name.Contains("SlamWall"))
                {
                    return false;
                }
            }


            return true;
        }

        public void moveInDirection(Vector3 direction)
        {
            Vector3 currentFinalPos = nodes[nodes.Count - 1].getPosition();
            nodes.Add(new PathNode(currentFinalPos + direction));

        }

        public void moveFinalNode(Vector3 direction)
        {

            nodes[nodes.Count - 1].moveNode(direction);


        }

        public void drawPath(Vector3 currentCarPos)
        {
            if (nodes.Count > 0)
            {
                Debug.DrawLine(currentCarPos, nodes[0].getPosition(), Color.green);
            }
            for (int i = 0; i < nodes.Count - 1; i++)
            {
                Debug.DrawLine(nodes[i].getPosition(), nodes[i + 1].getPosition(), Color.green);
            }

            //for (int i = 0; i < nodes.Count; i++)
            //{
            //    Debug.DrawRay(nodes[i].getPosition(), new Vector3(CarPathFinding.nodeSize, 0, 0), Color.yellow);
            //    Debug.DrawRay(nodes[i].getPosition(), new Vector3(-CarPathFinding.nodeSize, 0, 0), Color.yellow);
            //    Debug.DrawRay(nodes[i].getPosition(), new Vector3(0, 0, CarPathFinding.nodeSize), Color.yellow);
            //    Debug.DrawRay(nodes[i].getPosition(), new Vector3(0, 0, -CarPathFinding.nodeSize), Color.yellow);

            //}

        }

        public void drawTarget()
        {
            Vector3 targetPosition = getFinalNode().getPosition();

            Debug.DrawRay(targetPosition, new Vector3(CarPathFinding.nodeSize, 0, 0), Color.yellow);
            Debug.DrawRay(targetPosition, new Vector3(-CarPathFinding.nodeSize, 0, 0), Color.yellow);
            Debug.DrawRay(targetPosition, new Vector3(0, 0, CarPathFinding.nodeSize), Color.yellow);
            Debug.DrawRay(targetPosition, new Vector3(0, 0, -CarPathFinding.nodeSize), Color.yellow);


        }
        public void updatePath(Vector3 currentCarPosition)
        {
            if (Vector3.Distance(nodes[0].getPosition(), currentCarPosition) < CarPathFinding.nodeSize)
            {
                nodes.RemoveAt(0);
            }

        }

        public bool isEmpty()
        {
            return nodes.Count == 0;

        }

        public PathNode getNextNode()
        {
            return nodes[0];
        }

        public bool visionToNextNode(Vector3 currentCarPos)
        {
            Vector3 currentNextPos = nodes[0].getPosition();
            Vector3 direction = currentNextPos - currentCarPos;


            Ray ray = new Ray(currentCarPos, direction.normalized);
            var hitObjects = Physics.RaycastAll(ray, direction.magnitude);
            for (int i = 0; i < hitObjects.Length; i++)
            {
                if (hitObjects[i].collider.name.Contains("SlamWall"))
                {
                    return false;
                }
            }
            return true;

        }

        public int getLength()
        {
            return nodes.Count - 1;
        }

        public float distanceToTarget(Vector3 target)
        {
            return Vector3.Distance(getFinalNode().getPosition(), target);
        }

        public bool finalNodeIsNearUnexploredNode(Grid grid)
        {
            if (grid.pointNearUnexploredNode(getFinalNode().getPosition()))
            {
                return true;
            }
            return false;
        }

        public PathNode getFinalNode()
        {
            return nodes[nodes.Count - 1];
        }

        public PathFindingPath clone()
        {
            PathFindingPath clone = new PathFindingPath();
            foreach (PathNode n in nodes)
            {
                clone.addNode(n);
            }

            return clone;

        }
    }
}