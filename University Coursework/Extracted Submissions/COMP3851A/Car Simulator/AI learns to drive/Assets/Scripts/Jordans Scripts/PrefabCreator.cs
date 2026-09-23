using UnityEngine;

public class PrefabCreator : MonoBehaviour {

    public static void createCar(Vector3 point) {
        Debug.Log("inside create car: rotation angle is: " + TrackEditorManager.Instance.carRotationAngle);
        //Debug.Log(GameObject.Find("World").transform);
        createPrefab(TrackEditorManager.Instance.CarPrefab, point, TrackEditorManager.Instance.carRotationAngle, GameObject.Find("World").transform, "Car");
        TrackEditorManager.Instance.isCarPlaced = true;
    }

    public static float angleBetweenVectors(Vector2 a, Vector2 b) {
        return Mathf.Atan2(b.y - a.y, b.x - a.x) * Mathf.Rad2Deg;
    }

    public static void createWall(Vector3 startCoord, Vector3 endCoord, Transform transform, string name, GameObject polygonParent) {
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
        GameObject newWall = Instantiate(TrackEditorManager.Instance.WallPrefab, position, rotation, transform);
        newWall.name = name;
        if(polygonParent != null) {
            //GameObject polygonGameObject = GameObject.Find("Polygon(" + TrackEditorManager.Instance.polygonId + ")");
            newWall.transform.parent = polygonParent.transform;
            newWall.tag = "Wall";

        }
        newWall.transform.localScale = Vector3.Scale(newWall.transform.localScale, new Vector3(distance, 1, 1));
    }

    public static void createPrefab(GameObject prefab, Vector3 position, int rotation, Transform transform, string name) {
        Quaternion rotationQ = Quaternion.Euler(0, rotation, 0);
        Debug.Log("inside create prefab: rotation angle is: " + TrackEditorManager.Instance.carRotationAngle);
        GameObject prefabGO = Instantiate(prefab, position, rotationQ, transform);
        prefabGO.name = name;
    }
}
