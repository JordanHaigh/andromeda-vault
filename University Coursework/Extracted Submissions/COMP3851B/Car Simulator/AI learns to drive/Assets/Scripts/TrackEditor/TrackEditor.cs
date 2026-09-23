using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System;
using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TrackEditor {
    public UserMode userMode;

    public Transform transform;

    public int wallId = 0;
    public int polygonId = 0;
    public float rounding = 5f;
    public List<Vector3> rayCastHits;

    public Track track;

    public TrackEditor(Transform transform) {
        this.transform = transform;
        track = new Track(transform);

        rayCastHits = new List<Vector3>();
    }

    #region polygonAndCarGeneration
    public static RaycastHit GetRaycastHit() {
        Vector3 mouse = Input.mousePosition;
        Ray castPoint = Camera.main.ScreenPointToRay(mouse);
        RaycastHit hit;
        if(Physics.Raycast(castPoint, out hit, Mathf.Infinity))
            return hit;

        Debug.Log("Shoo. Go away. You shouldn't be here.");
        return new RaycastHit(); //but yeah dont reach here please
    }

    public void leftMouseCommands() {
        RaycastHit hit = GetRaycastHit();
        if(UnityEventSystem.isPointerOverGameObject()) { //checking to see if it clicked on the ui
            Debug.Log("Clicked on the UI");
            return;
        }

        if(userMode.Equals(UserMode.PLACE_TRACK)) {
            drawPolygon(hit);
        } else if(userMode.Equals(UserMode.PLACE_CAR)) {
            track.placeCar(hit.point, track.carRotationAngle);
        }
    }

    public bool isDrawingPolygon() {
        return rayCastHits.Count > 0;
    }

    void drawPolygon(RaycastHit hit) {
        if(!isDrawingPolygon()) {
            startPolygon(hit);
        }

        ////add point to position count, update line renderer
        Vector3 endCoord = hit.point;
        Vector3 previousCoord = rayCastHits[rayCastHits.Count - 1];
        endCoord = overrideEndCoord(previousCoord, endCoord);


        rayCastHits.Add(new Vector3(endCoord.x, 1.5f, endCoord.z));
        
        /**GameObject tempWall = GameObject.Find("TempWall");
        rayCastHits.Add(tempWall.transform.position);**/

        if(rayCastHits.Count < 2)
            return; //only need to render the newest wall - otherwise youre duplicating wall objects
        createNewWall();
    }

    private void createNewWall() {
        GameObject polygonParent = GameObject.Find("Polygon(" + polygonId + ")");
        Vector3 previousPoint = rayCastHits[rayCastHits.Count - 2];
        Vector3 currentPoint = rayCastHits[rayCastHits.Count - 1];

        //currentPoint = overrideEndCoord(previousPoint, currentPoint);
        track.createWall(previousPoint, currentPoint, wallId++, polygonParent);
    }

    private void startPolygon(RaycastHit hit) {
        Debug.Log("Starting New Polygon");
        //draw init circle at point

        Vector3 startingPosition = hit.point;
        //override starting position so it snaps to grid
        startingPosition = snapCoordToGrid(startingPosition);



        PrefabCreator.createInitCircle(startingPosition, this.transform);

        rayCastHits = new List<Vector3>();
        rayCastHits.Add(new Vector3(startingPosition.x, startingPosition.y, startingPosition.z));

        GameObject polygonGameObject = new GameObject();
        polygonGameObject.name = "Polygon(" + polygonId + ")";
    }


    public void createTempWall() {
        RaycastHit hit = GetRaycastHit();
        Vector3 startCoord = rayCastHits[rayCastHits.Count - 1];
        Vector3 endCoord = hit.point;

        endCoord = overrideEndCoord(startCoord, endCoord);

        PrefabCreator.createWall(startCoord, endCoord, this.transform, "TempWall", null);
    }

    public void completePolygon() {
        if(isDrawingPolygon() && rayCastHits.Count > 2) {
            GameObject polygonParent = GameObject.Find("Polygon(" + polygonId + ")");


            Vector3 finalCoord = rayCastHits[rayCastHits.Count - 1];
            Vector3 startCoord = rayCastHits[0];
            if((startCoord.x == finalCoord.x) || (startCoord.z == finalCoord.z)) {
                rayCastHits.Add(new Vector3(rayCastHits[0].x, 1.5f, rayCastHits[0].z)); //add start position

                createNewWall();

                GameObject.Destroy(GameObject.Find("InitCircle"));
                GameObject.Destroy(GameObject.Find("TempWall"));

                Polygon polygon = new Polygon(rayCastHits, polygonId++);
                track.polygons.Add(polygon);

                //reset variables
                resetDrawingVariables();
            }
            
        }
    }


    #endregion

    #region ui methods

    public void undo() {
        if(isDrawingPolygon()) {
            if(rayCastHits.Count == 0) //currently no points. don't do anything
                return;
            else if(rayCastHits.Count == 1) { //currently 1 point. need to remove this point, the init circle and the temp wall
                undo_removeStart();
                resetDrawingVariables();
            } else { //2 or more points. now we need to remove a single wall.
                undo_removeWall();
            }
        } else {
            //remove last polygon
            undo_removePolygon();
        }

    }

    #region undo submethods
    private void undo_removeStart() {
        GameObject.Destroy(GameObject.Find("Polygon(" + polygonId + ")"));
        GameObject.Destroy(GameObject.Find("InitCircle"));
        GameObject.Destroy(GameObject.Find("TempWall"));

        polygonId--;
        if(polygonId < 0)
            polygonId = 0;
    }

    private void undo_removeWall() {
        int wallIdToRemove = wallId - 1;

        //get last wall
        GameObject polygonGameObject = GameObject.Find("Polygon(" + polygonId + ")"); //get current polygon game object
        Transform lastWall = polygonGameObject.transform.GetChild(polygonGameObject.transform.childCount - 1); //get last wall
        GameObject.Destroy(lastWall.gameObject);

        //remove last line in line renderer
        rayCastHits.RemoveAt(rayCastHits.Count - 1);
        wallId--;
    }

    private void undo_removePolygon() {
        if(track.polygons.Count == 0)
            return;

        Polygon polygonToRemove = track.polygons[track.polygons.Count - 1];
        int pRemoveId = polygonToRemove.id;

        track.polygons.RemoveAt(track.polygons.Count - 1);

        GameObject polygonGameObjectToRemove = GameObject.Find("Polygon(" + pRemoveId + ")");
        if(polygonGameObjectToRemove != null) {
            GameObject.Destroy(polygonGameObjectToRemove);
        }

        polygonId--;
    }
    #endregion

    public void resetWorkspace() {
        if(isDrawingPolygon())
            return;
        resetDrawingVariables();

        polygonId = 0;
        wallId = 0;

        track.resetWorkspace();
    }

    public void loadWorkspace() {
        if(isDrawingPolygon())
            return;
        resetDrawingVariables();

        track.loadWorkspace();

        polygonId = track.polygons.Count;
        wallId = 0;
    }

    public void saveWorkspace() {
        if(!track.isCarPlaced || track.polygons.Count == 0) {
            Debug.Log("Cannot Save. Either missing car or no polygons");
            return;
        }

        System.DateTime dateTime = System.DateTime.Now;
        StringBuilder fileName = new StringBuilder();

        fileName.Append(@"..\\TrackWorkspaceFiles\\")
            .Append(dateTime.ToString("yyyyMMdd-HH-mm-ss"))
            .Append(".txt");

        StreamWriter sw = new StreamWriter(fileName.ToString());
        StringBuilder sb = new StringBuilder();
        GameObject car = GameObject.Find("Car");

        sb.Append("Car{(")
            .Append(car.transform.position.x)
            .Append(", ")
            .Append("0")
            .Append(", ")
            .Append(car.transform.position.z)
            .Append("), Rotation:")
            .Append(track.carRotationAngle)
            .Append("}");
        sb.Append("\n");
        foreach(Polygon p in track.polygons) {
            sb.Append(p.ToString())
                .Append("\n");
        }

        sw.Write(sb.ToString());
        Debug.Log("Saved workspace");
        sw.Close();
    }

    public void updateUI(Sprite newSprite, string newText, UserMode newUserMode) {
        Debug.Log("Changed Mode to " + newText);
        userMode = newUserMode;

        TextMeshProUGUI textMesh = GameObject.FindGameObjectWithTag("TextMesh").GetComponent<TextMeshProUGUI>();
        textMesh.SetText(newText);

        UnityEngine.UI.Image image = GameObject.Find("ModeImage").GetComponent<UnityEngine.UI.Image>();
        image.sprite = newSprite;
    }

    public void rotateCar(int angle) {
        track.rotateCar(angle);
    }

    #endregion


    public void resetDrawingVariables() {
        wallId = 0;
        rayCastHits = new List<Vector3>();
    }

    public void changeMode() {
        if(userMode.Equals(UserMode.PLACE_CAR)) {
            Sprite trackModeSprite = (Sprite)Resources.Load("PlaceTrackSymbol", typeof(Sprite));
            updateUI(trackModeSprite, "Placing Track", UserMode.PLACE_TRACK);
        } else {
            Sprite carModeSprite = (Sprite)Resources.Load("CarSymbol", typeof(Sprite));
            updateUI(carModeSprite, "Placing Car", UserMode.PLACE_CAR);
        }

    }

    private Vector3 overrideEndCoord(Vector3 startCoord, Vector3 endCoord) {
        //Making Right Angled walls
        Vector3 delta = endCoord - startCoord;

        if(Math.Abs(delta.x) >= Math.Abs(delta.z)) {
            endCoord.z = startCoord.z;
        } else {
            endCoord.x = startCoord.x;
        }


        //Making walls snap to a grid
        return snapCoordToGrid(endCoord);
    }

    private Vector3 snapCoordToGrid(Vector3 coord) {
        int magnitudeEndCoordX = (int)(coord.x / rounding);
        int magnitudeEndCoordZ = (int)(coord.z / rounding);

        coord.x = magnitudeEndCoordX * rounding;
        coord.z = magnitudeEndCoordZ * rounding;

        return coord;
    }

}
