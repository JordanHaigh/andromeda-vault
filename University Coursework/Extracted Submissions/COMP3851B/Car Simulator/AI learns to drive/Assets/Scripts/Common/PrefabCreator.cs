using UnityEngine;
using UnityEditor;

public class PrefabCreator : MonoBehaviour {

    public static void createPoint(Vector3 position, string id, GameObject parentObject) {
        createPoint(position, id, parentObject, null);
    }

    public static void createPoint(Vector3 position, string id, GameObject parentObject, Material material) {
        GameObject PointPrefab = (GameObject)Resources.Load("Point", typeof(GameObject));

        GameObject pointGO = createPrefab(PointPrefab, position, 0, GameObject.Find("World").transform, id);

        if(material != null) {
            pointGO.GetComponent<Renderer>().material = material;
        }
        pointGO.transform.parent = parentObject.transform;
    }

    public static void createCar(Vector3 point, int carRotationAngle) {
        //Debug.Log("inside create car: rotation angle is: " + carRotationAngle);
        //Debug.Log(GameObject.Find("World").transform);
        GameObject CarPrefab = (GameObject)Resources.Load("EvansCar", typeof(GameObject));
        createPrefab(CarPrefab, point, carRotationAngle, GameObject.Find("World").transform, "Car");
    }

    public static float angleBetweenVectors(Vector2 a, Vector2 b) {
        return Mathf.Atan2(b.y - a.y, b.x - a.x) * Mathf.Rad2Deg;
    }

    public static void createSlamWall(Vector3 startCoord, Vector3 endCoord, Transform transform, string name, GameObject polygonParent) {
        GameObject SlamWallPrefab = (GameObject)Resources.Load("SlamWall", typeof(GameObject));
        createWallFromPrefab(startCoord, endCoord, transform, name, polygonParent, SlamWallPrefab);
    }

    public static void createWall(Vector3 startCoord, Vector3 endCoord, Transform transform, string name, GameObject polygonParent) {
        GameObject WallPrefab = (GameObject)Resources.Load("WallPrefab", typeof(GameObject));
        createWallFromPrefab(startCoord, endCoord, transform, name, polygonParent, WallPrefab);
    }

    private static void createWallFromPrefab(Vector3 startCoord, Vector3 endCoord, Transform transform, string name, GameObject polygonParent, GameObject WallPrefab) {
        Vector2 previousPoint = new Vector2(startCoord.x, startCoord.z);
        Vector2 currentPoint = new Vector2(endCoord.x, endCoord.z);


        // get distance
        float distance = Vector2.Distance(previousPoint, currentPoint);

        // set position to midpoint
        Vector3 position = new Vector3((previousPoint.x + currentPoint.x) / 2, 1.5f, (previousPoint.y + currentPoint.y) / 2);

        // get angle between current and last points
        float rotationAngle = angleBetweenVectors(previousPoint, currentPoint);
        Quaternion rotation = Quaternion.Euler(0, -rotationAngle, 0);

        // add object to world
        GameObject newWall = Instantiate(WallPrefab, position, rotation, transform);
        newWall.name = name;

        if(polygonParent != null) {
            //GameObject polygonGameObject = GameObject.Find("Polygon(" + TrackEditorManager.Instance.polygonId + ")");
            newWall.transform.parent = polygonParent.transform;
            newWall.tag = "Wall";

        }
        newWall.transform.localScale = Vector3.Scale(newWall.transform.localScale, new Vector3(distance, 1, 1));
    }

    public static void createInitCircle(Vector3 position, Transform transform) {
        GameObject InitCirclePrefab = (GameObject)Resources.Load("InitCircle", typeof(GameObject));
        Debug.Log(InitCirclePrefab);
        createPrefab(InitCirclePrefab, position, 0, transform, "InitCircle");
    }

    public static GameObject createPrefab(GameObject prefab, Vector3 position, int rotation, Transform transform, string name) {
        Quaternion rotationQ = Quaternion.Euler(0, rotation, 0);
        //Debug.Log("inside create prefab: rotation angle is: " + TrackEditorManager.Instance.carRotationAngle);

        GameObject prefabGO = Instantiate(prefab, position, rotationQ, transform);
        prefabGO.name = name;

        return prefabGO;

    }
}
