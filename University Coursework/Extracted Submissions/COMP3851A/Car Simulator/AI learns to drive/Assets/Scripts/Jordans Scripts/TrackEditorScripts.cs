using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class TrackEditorScripts : MonoBehaviour
{
    public enum UserMode { PLACE_TRACK, PLACE_CAR, EDIT_WALL}

    UserMode userMode;
    public Sprite carModeSprite;
    public Sprite trackModeSprite;

    void Start() {
        userMode = UserMode.PLACE_TRACK;
    }

    void Update() {
        if(Input.GetMouseButtonDown(0)) {
            leftMouseCommands();
        } else if(Input.GetMouseButtonDown(1)) {
            //right click - finish polygon if its currently going
            completePolygon();
        } else if(Input.GetKeyDown(KeyCode.Z)) {
            undoKeyCommands();
        } else if(Input.GetKeyDown(KeyCode.X)) {
            TrackEditorManager.resetWorkspace();
        } else if(Input.GetKeyDown(KeyCode.C)) {
            Debug.Log("Changed Mode to Place Car");
            userMode = UserMode.PLACE_CAR;
            TextMeshProUGUI textMesh = GameObject.FindGameObjectWithTag("TextMesh").GetComponent<TextMeshProUGUI>();
            textMesh.SetText("Placing Car");
            UnityEngine.UI.Image image = GameObject.Find("ModeImage").GetComponent<UnityEngine.UI.Image>();
            image.sprite = carModeSprite;

        } else if(Input.GetKeyDown(KeyCode.V)) {
            Debug.Log("Changed Mode to Place Track");
            userMode = UserMode.PLACE_TRACK;
            TextMeshProUGUI textMesh = GameObject.FindGameObjectWithTag("TextMesh").GetComponent<TextMeshProUGUI>();
            textMesh.SetText("Placing Track");
            UnityEngine.UI.Image image = GameObject.Find("ModeImage").GetComponent<UnityEngine.UI.Image>();
            image.sprite = trackModeSprite;
        } else if(Input.GetKeyDown(KeyCode.R)) {
            rotateCar(45);
        } else if(Input.GetKeyDown(KeyCode.E)) {
            rotateCar(-45);
        } else if(Input.GetKeyDown(KeyCode.S)) {
            saveWorkspace();
        } else if(Input.GetKeyDown(KeyCode.L)) {
            if(TrackEditorManager.Instance.isDrawingPolygon)
                return;
            TrackEditorManager.resetWorkspace();
            LoadWorkspace.loadWorkspace();

        } else if(Input.GetKey(KeyCode.Return)) {
            if(TrackEditorManager.Instance.isDrawingPolygon)
                return;
            TrackEditorManager.resetWorkspace();
            SceneManager.LoadScene("Basic AI", LoadSceneMode.Single);
        } else if(Input.GetKeyDown(KeyCode.Tab)) {
            if(userMode.Equals(UserMode.PLACE_CAR)) {
                Debug.Log("Changed Mode to Place Track");
                userMode = UserMode.PLACE_TRACK;
                TextMeshProUGUI textMesh = GameObject.FindGameObjectWithTag("TextMesh").GetComponent<TextMeshProUGUI>();
                textMesh.SetText("Placing Track");
                UnityEngine.UI.Image image = GameObject.Find("ModeImage").GetComponent<UnityEngine.UI.Image>();
                image.sprite = trackModeSprite;
            } else {
                Debug.Log("Changed Mode to Place Car");
                userMode = UserMode.PLACE_CAR;
                TextMeshProUGUI textMesh = GameObject.FindGameObjectWithTag("TextMesh").GetComponent<TextMeshProUGUI>();
                textMesh.SetText("Placing Car");
                UnityEngine.UI.Image image = GameObject.Find("ModeImage").GetComponent<UnityEngine.UI.Image>();
                image.sprite = carModeSprite;
            }
        }


        if(TrackEditorManager.Instance.isDrawingPolygon) {
            if(GameObject.Find("TempWall") != null) {
                Destroy(GameObject.Find("TempWall"));
            }
            createTempWall();
        }
    }

    void createTempWall() {
        RaycastHit hit = GetRaycastHit();
        PrefabCreator.createWall(TrackEditorManager.Instance.lineRenderer.GetPosition(TrackEditorManager.Instance.lineRenderer.positionCount - 1), hit.point, this.transform,"TempWall", null);
    }

    public static RaycastHit GetRaycastHit() {
        Vector3 mouse = Input.mousePosition;
        Ray castPoint = Camera.main.ScreenPointToRay(mouse);
        RaycastHit hit;
        if(Physics.Raycast(castPoint, out hit, Mathf.Infinity))
            return hit;

        Debug.Log("Never should've come here..."); //yes I'm allowed to have skyrim references in my scripts
        return new RaycastHit(); //but yeah dont reach here please
    }

    void leftMouseCommands() {
        RaycastHit hit = GetRaycastHit();
        if(EventSystem.current.IsPointerOverGameObject()) {
            Debug.Log("Clicked on the UI");
            return;
        }

        if(userMode.Equals(UserMode.PLACE_TRACK)) {
            leftMouseCommands_PlaceTrack(hit);
        }
        else if(userMode.Equals(UserMode.PLACE_CAR)) {
            leftMouseCommands_PlaceCar(hit);
        }
    }

    void leftMouseCommands_PlaceTrack(RaycastHit hit) {
        if(!TrackEditorManager.Instance.isDrawingPolygon) {
            Debug.Log("Starting New Polygon");
            //draw init circle at point
            PrefabCreator.createPrefab(TrackEditorManager.Instance.InitCirclePrefab, hit.point, 0, this.transform, "InitCircle");

            TrackEditorManager.Instance.isDrawingPolygon = true;

            TrackEditorManager.Instance.currentPolygon = new Polygon(new List<Vector3>(), TrackEditorManager.Instance.polygonId);

            GameObject polygonGameObject = new GameObject();
            polygonGameObject.name = "Polygon(" + TrackEditorManager.Instance.polygonId + ")";

        } else {
            //check if length is greater than 2 and point is close to start
            //todo - to do
        }

        //add point to position count, update line renderer
        TrackEditorManager.Instance.currentPolygon.points.Add(new Vector3(hit.point.x, TrackEditorManager.Instance.wallHeight, hit.point.z));
        TrackEditorManager.updateLineRenderer(TrackEditorManager.Instance.currentPolygon.points.ToArray());


        if(TrackEditorManager.Instance.currentPolygon.points.Count < 2)
            return; //only need to render the newest wall - otherwise youre duplicating wall objects

        Vector3 previousPoint = TrackEditorManager.Instance.lineRenderer.GetPosition(TrackEditorManager.Instance.lineRenderer.positionCount - 2);
        Vector3 currentPoint = TrackEditorManager.Instance.lineRenderer.GetPosition(TrackEditorManager.Instance.lineRenderer.positionCount - 1);

        GameObject polygonParent = GameObject.Find("Polygon(" + TrackEditorManager.Instance.polygonId + ")");

        PrefabCreator.createWall(previousPoint, currentPoint, this.transform,"Wall-Section(" + TrackEditorManager.Instance.wallId++ + ")", polygonParent);
    }

    

    void leftMouseCommands_PlaceCar(RaycastHit hit) {
        if(TrackEditorManager.Instance.isCarPlaced) {
            //delete previous position
            GameObject carGameObject = GameObject.Find("Car");
            Destroy(carGameObject);
        }
        PrefabCreator.createCar(hit.point);
        TrackEditorManager.Instance.isCarPlaced = true;
    }

    public void resetWorkspace() {
        //I hate unity.
        TrackEditorManager.resetWorkspace();
    }

    public void loadWorkspace() {
        //Did I say how much I hate Unity yet?
        resetWorkspace();
        LoadWorkspace.loadWorkspace();
    }

    public void undoKeyCommands() {
        if(TrackEditorManager.Instance.isDrawingPolygon) {

            if(TrackEditorManager.Instance.lineRenderer.positionCount == 0)
                return;
            else if(TrackEditorManager.Instance.lineRenderer.positionCount == 1) {
                Destroy(GameObject.Find("Polygon("+ TrackEditorManager.Instance.polygonId +")"));
                Destroy(GameObject.Find("InitCircle"));
                Destroy(GameObject.Find("TempWall"));
                TrackEditorManager.Instance.polygonId--;
                TrackEditorManager.resetDrawingVariables();
            } else {
                int wallIdToRemove = TrackEditorManager.Instance.wallId - 1;

                //get last wall
                //remove last wall
                GameObject polygonGameObject = GameObject.Find("Polygon(" + TrackEditorManager.Instance.polygonId + ")");
                Transform lastWall = polygonGameObject.transform.GetChild(polygonGameObject.transform.childCount - 1);
                Destroy(lastWall.gameObject);

                //remove last line in line renderer
                TrackEditorManager.Instance.currentPolygon.points.RemoveAt(TrackEditorManager.Instance.currentPolygon.points.Count - 1);
                TrackEditorManager.updateLineRenderer(TrackEditorManager.Instance.currentPolygon.points.ToArray());
            }
        } else {
            //remove last polygon
            if(TrackEditorManager.Instance.polygons.Count == 0)
                return;

            Polygon polygonToRemove = TrackEditorManager.Instance.polygons[TrackEditorManager.Instance.polygons.Count - 1];
            int pRemoveId = polygonToRemove.id;

            TrackEditorManager.Instance.polygons.RemoveAt(TrackEditorManager.Instance.polygons.Count - 1);

            GameObject polygonGameObjectToRemove = GameObject.Find("Polygon(" + pRemoveId + ")");
            if(polygonGameObjectToRemove != null) {
                Destroy(polygonGameObjectToRemove);
            }

            TrackEditorManager.Instance.polygonId--;
        }

    }

    

    void rotateCar(int angle) {
        if(!TrackEditorManager.Instance.isCarPlaced)
            return;

        int rotate = TrackEditorManager.Instance.carRotationAngle + angle;

        while(rotate < 0) {
            rotate += 360;
        }

        TrackEditorManager.Instance.carRotationAngle = rotate % 360;
        

        //replace car;
        GameObject car = GameObject.Find("Car");
        Vector3 point = car.transform.position;
        Destroy(car);

        PrefabCreator.createCar(point);
    }

   

    public void saveWorkspace() {
        if(!TrackEditorManager.Instance.isCarPlaced || TrackEditorManager.Instance.polygons.Count == 0) {
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
            .Append(TrackEditorManager.Instance.carRotationAngle)
            .Append("}");
        sb.Append("\n");
        foreach(Polygon p in TrackEditorManager.Instance.polygons) {
            sb.Append(p.ToString())
                .Append("\n");
        }

        sw.Write(sb.ToString());
        Debug.Log("Saved workspace");
        sw.Close();
    }

   
    public void completePolygon() {
        if(TrackEditorManager.Instance.isDrawingPolygon && TrackEditorManager.Instance.currentPolygon.points.Count > 2) {
            GameObject polygonParent = GameObject.Find("Polygon(" + TrackEditorManager.Instance.polygonId + ")");

            PrefabCreator.createWall(
                TrackEditorManager.Instance.lineRenderer.GetPosition(TrackEditorManager.Instance.lineRenderer.positionCount-1),
                TrackEditorManager.Instance.currentPolygon.points[0], 
                this.transform,
                "Wall-Section(" + TrackEditorManager.Instance.wallId++ + ")",
                polygonParent
            );

            TrackEditorManager.Instance.polygonId++;

            Destroy(GameObject.Find("InitCircle"));

            Vector3 startPoint = TrackEditorManager.Instance.lineRenderer.GetPosition(0);

            TrackEditorManager.Instance.currentPolygon.points.Add(new Vector3(startPoint.x, TrackEditorManager.Instance.wallHeight, startPoint.z));
            TrackEditorManager.Instance.polygons.Add(TrackEditorManager.Instance.currentPolygon);

            //reset variables
            TrackEditorManager.resetDrawingVariables();
            Destroy(GameObject.Find("TempWall"));
        }
    }

   

   

    

}
