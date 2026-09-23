using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Track {
    public List<Polygon> polygons {get;set;}
    public bool isCarPlaced { get; set; }
    public Vector3 startPosition { get; set; }
    public int carRotationAngle { get; set; }
    public GameObject car { get; set; }

    public Transform transform;
    //public float wallHeight { get; set; }


    public Track(Transform transform) {
        carRotationAngle = 0;
        polygons = new List<Polygon>();

        this.transform = transform;
    }

    public Track(List<Polygon> polygons, Vector3 startPosition, int carRotationAngle) {
        this.polygons = polygons;
        this.startPosition = startPosition;
        this.carRotationAngle = carRotationAngle;
    }

    public Track(List<Polygon> polygons, Vector3 startPosition, int carRotationAngle, Transform transform) {
        this.polygons = polygons;
        this.startPosition = startPosition;
        this.carRotationAngle = carRotationAngle;
        this.transform = transform;
    }

    public void drawPolygons() {
        for(int i = 0; i < polygons.Count;i++){
            Polygon currentPolygon = polygons[i];
            GameObject polygonGameObject = new GameObject();
            polygonGameObject.name = "Polygon(" + i + ")";

            for(int j = 1; j < currentPolygon.points.Count; j++) {
                createWall(currentPolygon.points[j - 1], currentPolygon.points[j], j, polygonGameObject);
            }
        } 
    }

    public void createWall(Vector3 previousPoint, Vector3 currentPoint, int wallId, GameObject parentGameObject) {
        PrefabCreator.createWall(previousPoint, currentPoint, transform, "Wall-Section(" + wallId + ")", parentGameObject);
    }

    public void placeCar() {
        placeCar(startPosition, carRotationAngle);
    }

    public void placeCar(Vector3 position, int angle) {
        if(isCarPlaced) {
            //delete previous position
            GameObject carGameObject = GameObject.Find("Car");
            GameObject.Destroy(carGameObject);
        }

        PrefabCreator.createCar(position, angle);
        isCarPlaced = true;
        startPosition = position;
        carRotationAngle = angle;
    }

    public void rotateCar(int angle) {
        if(!isCarPlaced)
            return;

        int rotate = carRotationAngle + angle;

        while(rotate < 0) {
            rotate += 360;
        }

        carRotationAngle = rotate % 360;
        placeCar(startPosition, carRotationAngle);
    }

    public void loadWorkspace() {
        TrackWorkspaceLoader loader = new TrackWorkspaceLoader();
        Track newTrackInfo = loader.loadWorkspace();

        resetWorkspace();

        polygons = newTrackInfo.polygons;
        startPosition = newTrackInfo.startPosition;
        carRotationAngle = newTrackInfo.carRotationAngle;

        drawPolygons();
        placeCar(startPosition, carRotationAngle);

    }

    public void resetWorkspace() {
        //remove all polygons
        for(int i = 0; i < polygons.Count; i++) {
            GameObject.Destroy(GameObject.Find("Polygon(" + i+ ")"));
        }

        polygons.Clear();

        if(isCarPlaced) {
            isCarPlaced = false;
            GameObject.Destroy(GameObject.Find("Car"));
        }

    }










    public void createPolygon(Polygon p, int polygonId, bool firstPolygon) {
        GameObject polygonGameObject = new GameObject();
        MeshFilter meshFilter = polygonGameObject.AddComponent<MeshFilter>();
        MeshRenderer meshRenderer = polygonGameObject.AddComponent<MeshRenderer>();
        polygonGameObject.transform.parent = GameObject.Find("World").transform;
        polygonGameObject.name = "Polygon(" + polygonId + ")";
        if(firstPolygon) {
            meshRenderer.material = (Material)Resources.Load("TrackMaterial", typeof(Material));
            Vector3 addingVector = new Vector3(0, -0.9f, 0);
            polygonGameObject.transform.localScale = addingVector + polygonGameObject.transform.localScale;
        } else {
            meshRenderer.material = (Material)Resources.Load("GroundMaterial", typeof(Material));
        }

        Mesh mesh = getMesh(p);
        Vector3[] verts = mesh.vertices;

        if(!firstPolygon) {
            for(int i = 0; i < verts.Length; i++) {
                verts[i].y += 0.01f;
            }
        }
        mesh.vertices = verts;

        meshFilter.mesh = mesh;

        int wallId = 1;
        for(int i = 1; i < p.points.Count; i++) {
            PrefabCreator.createWall(
                p.points[i - 1],
                p.points[i],
                GameObject.Find("World").transform,
                "Wall-Section(" + wallId++ + ")",
                polygonGameObject
            );
        }
    }


    public Mesh getMesh(Polygon p) {
        Mesh mesh = new Mesh();
        Vector3[] verticies = p.points.ToArray();
        Vector2[] verts2d = new Vector2[verticies.Length - 1];
        for(int i = 0; i < verts2d.Length; i++) {
            verts2d[i] = new Vector2(verticies[i].z, verticies[i].x);
            Debug.Log(verts2d[i]);
        }

        Sebastian.Geometry.Polygon sebPoly = new Sebastian.Geometry.Polygon(verts2d);


        var trianglulator = new Sebastian.Geometry.Triangulator(sebPoly);

        int[] triangles = trianglulator.Triangulate();


        //each triangle needs to be in order i.e. 2,1,3 is not allowed but 1,2,3 is 
        for(int i = 2; i < triangles.Length; i++) {
            if((i + 1) % 3 == 0) {
                int max = Mathf.Max(triangles[i - 2], triangles[i - 1], triangles[i]);
                int min = Mathf.Min(triangles[i - 2], triangles[i - 1], triangles[i]);
                int[] newOrder = new int[3];
                for(int j = i - 2; j <= i; j++) {
                    if(triangles[j] == max) {
                        newOrder[2] = triangles[j];
                    } else if(triangles[j] == min) {
                        newOrder[0] = triangles[j];

                    } else {
                        newOrder[1] = triangles[j];

                    }

                }

                for(int j = i - 2; j <= i; j++) {
                    triangles[j] = newOrder[j - (i - 2)];
                }
            }
        }

        mesh.vertices = verticies;
        mesh.triangles = triangles;
        return mesh;
    }

}
