using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.RegularExpressions;
using UnityEngine;
using UnityEngine.SceneManagement;

public class TrackEditorManager : Singleton<TrackEditorManager>{

    // (Optional) Prevent non-singleton constructor use.
    protected TrackEditorManager() { }

    public int wallId = 0;
    public int polygonId = 0;
    public int carRotationAngle = 0;

    public List<Polygon> polygons = new List<Polygon>();
    public Polygon currentPolygon;

    public bool isDrawingPolygon = false;
    public bool isCarPlaced = false;
    public LineRenderer lineRenderer;

    public GameObject WallPrefab;
    public GameObject CarPrefab;
    public GameObject InitCirclePrefab;
    public float wallHeight;

    public Vector3 startPosition;


    void Awake() {
        lineRenderer = GameObject.Find("World").GetComponent<LineRenderer>();
        Debug.Log("Awake");
    }

    public static void resetWorkspace() {
        if(TrackEditorManager.Instance.isDrawingPolygon)
            return;

        //remove all polygons
        foreach(Polygon p in TrackEditorManager.Instance.polygons) {
            Destroy(GameObject.Find("Polygon(" + p.id + ")"));
        }
        TrackEditorManager.Instance.polygons.Clear();

        TrackEditorManager.Instance.polygonId = 0;
        resetDrawingVariables();

        if(TrackEditorManager.Instance.isCarPlaced) {
            TrackEditorManager.Instance.isCarPlaced = false;
            Destroy(GameObject.Find("Car"));
        }

    }

    public static void resetDrawingVariables() {
        TrackEditorManager.Instance.wallId = 0;
        TrackEditorManager.Instance.isDrawingPolygon = false;
        TrackEditorManager.Instance.currentPolygon = new Polygon();
        updateLineRenderer(new Vector3[1]);
    }

    public static void updateLineRenderer(Vector3[] vector3s) {
        TrackEditorManager.Instance.lineRenderer.positionCount = vector3s.Length;
        TrackEditorManager.Instance.lineRenderer.SetPositions(vector3s);
    }

}




public class Singleton<T> : MonoBehaviour where T : MonoBehaviour {
    //http://wiki.unity3d.com/index.php/Singleton
    /// <summary>
    /// Inherit from this base class to create a singleton.
    /// e.g. public class MyClassName : Singleton<MyClassName> {}
    /// </summary>
    /// 


    // Check to see if we're about to be destroyed.
    private static bool m_ShuttingDown = false;
    private static object m_Lock = new object();
    private static T m_Instance;

    /// <summary>
    /// Access singleton instance through this propriety.
    /// </summary>
    public static T Instance {
        get {
            if(m_ShuttingDown) {
                Debug.LogWarning("[Singleton] Instance '" + typeof(T) +
                    "' already destroyed. Returning null.");
                return null;
            }

            lock(m_Lock) {
                if(m_Instance == null) {
                    // Search for existing instance.
                    m_Instance = (T)FindObjectOfType(typeof(T));

                    // Create new instance if one doesn't already exist.
                    if(m_Instance == null) {
                        // Need to create a new GameObject to attach the singleton to.
                        var singletonObject = new GameObject();
                        m_Instance = singletonObject.AddComponent<T>();
                        singletonObject.name = typeof(T).ToString() + " (Singleton)";

                        // Make instance persistent.
                        DontDestroyOnLoad(singletonObject);
                    }
                }

                return m_Instance;
            }
        }
    }


    private void OnApplicationQuit() {
        m_ShuttingDown = true;
    }


    private void OnDestroy() {
        //m_ShuttingDown = true; //dont uncomment this! otherwise the globals wont be passed from scene to scene 
    }
}

