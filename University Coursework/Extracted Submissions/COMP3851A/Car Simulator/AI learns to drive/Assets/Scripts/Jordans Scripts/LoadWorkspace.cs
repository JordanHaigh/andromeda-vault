using System.Collections.Generic;
using System.IO;
using System.Text.RegularExpressions;
using UnityEngine;

public class LoadWorkspace : MonoBehaviour {

    public static void loadWorkspace() {
        StandaloneFileBrowser s = GameObject.Find("World").GetComponent<StandaloneFileBrowser>();

        string[] vars = s.OpenFilePanel("hello is this real", "..\\", "", false);
        if(vars.Length > 0) {
            string directory = vars[0];
            loadWorkspaceFromFile(directory);
        }
    }


    static void loadWorkspaceFromFile(string directory) {
        string text = File.ReadAllText(directory);
        text = Regex.Replace(text, "\r\n", "\n");

        string[] carAndPolygonLocations = text.Split('}');
        /////////////////////////////////
        string carLocation = carAndPolygonLocations[0];
        string[] rotationText = Regex.Split(carLocation, "Rotation:");

        Debug.Log("heyyyy heres the instance: "+ TrackEditorManager.Instance.ToString());

        TrackEditorManager.Instance.carRotationAngle = int.Parse(rotationText[1]);
        string carCoordinates = Regex.Replace(carLocation, @"[Car:{}(),]|(\w*:(\d+))", "");
        string[] split = carCoordinates.Trim().Split(' ');

        PrefabCreator.createCar(new Vector3(
            float.Parse(split[0]),
            float.Parse(split[1]),
            float.Parse(split[2])
            )
        );

        TrackEditorManager.Instance.startPosition = new Vector3(float.Parse(split[0]), float.Parse(split[1]), float.Parse(split[2]));

        ///////////////////////////
        for(int i = 1; i < carAndPolygonLocations.Length; i++) {
            string workingString = carAndPolygonLocations[i];

            if(workingString.Equals("\n")) {
                break;
            }
            string[] pointsInPolygon = workingString.Split('\n');
            string id = "";

            List<Vector3> points = new List<Vector3>();

            for(int j = 0; j < pointsInPolygon.Length; j++) {
                string point = pointsInPolygon[j];

                if(point.Equals("\n") || point.Equals(""))
                    continue;

                if(point.Contains("Polygon")) {
                    id = Regex.Replace(point, @"[Polygon{]", "");
                    continue;
                }
                string[] coordinates = Regex.Split(point, "[\t(), ]");

                float x = float.Parse(coordinates[2]);
                float y = float.Parse(coordinates[4]);
                float z = float.Parse(coordinates[6]);

                points.Add(new Vector3(x, y, z)); //todo - learn regexes properly one day
            }
            Polygon p = new Polygon(points, (int.Parse(id)));
            //Debug.Log(p.ToString());
            TrackEditorManager.Instance.polygons.Add(p);
        }

        drawAllPolygons();

    }

    public static void drawAllPolygons() {
        //can use it for rendering in workspaces
        bool firstPolygon = true;
        foreach(Polygon p in TrackEditorManager.Instance.polygons) {
            createPolygon(p, firstPolygon);
            firstPolygon = false;
        }
    }

    public static void createPolygon(Polygon p,bool firstPolygon) {
        GameObject polygonGameObject = new GameObject();
        MeshFilter meshFilter = polygonGameObject.AddComponent<MeshFilter>();
        MeshRenderer meshRenderer=polygonGameObject.AddComponent<MeshRenderer>();
        polygonGameObject.transform.parent = GameObject.Find("World").transform;
        polygonGameObject.name = "Polygon(" + TrackEditorManager.Instance.polygonId + ")";
        if (firstPolygon)
        {
            meshRenderer.material = (Material)Resources.Load("TrackMaterial", typeof(Material));
            Vector3 addingVector = new Vector3(0,-0.9f,0);
            polygonGameObject.transform.localScale = addingVector + polygonGameObject.transform.localScale;
        }
        else
        {
            meshRenderer.material = (Material)Resources.Load("GroundMaterial", typeof(Material));
        }

        Mesh mesh = getMesh(p);
        Vector3[] verts = mesh.vertices;

        if (!firstPolygon)
        {
            for(int i = 0; i< verts.Length; i++)
            {
                verts[i].y += 0.01f;
            }
        }
        mesh.vertices = verts;

        meshFilter.mesh = mesh;

        for (int i = 1; i < p.points.Count; i++) {
            PrefabCreator.createWall(
                p.points[i - 1],
                p.points[i],
                GameObject.Find("World").transform,
                "Wall-Section(" + TrackEditorManager.Instance.wallId++ + ")",
                polygonGameObject
            );
        }
        TrackEditorManager.Instance.polygonId++;
    }


    public static Mesh getMesh(Polygon p)
    {
        Mesh mesh = new Mesh();
        Vector3[] verticies = p.points.ToArray();
        Vector2[] verts2d = new Vector2[verticies.Length - 1];
        for (int i = 0; i < verts2d.Length; i++)
        {
            verts2d[i] = new Vector2(verticies[i].z, verticies[i].x);
            Debug.Log(verts2d[i]);
        }

        Sebastian.Geometry.Polygon sebPoly = new Sebastian.Geometry.Polygon(verts2d);


        var trianglulator = new Sebastian.Geometry.Triangulator(sebPoly);

        int[] triangles = trianglulator.Triangulate();


        //each triangle needs to be in order i.e. 2,1,3 is not allowed but 1,2,3 is 
        for (int i = 2; i < triangles.Length; i++)
        {
            if ((i + 1) % 3 == 0)
            {
                int max = Mathf.Max(triangles[i - 2], triangles[i - 1], triangles[i]);
                int min = Mathf.Min(triangles[i - 2], triangles[i - 1], triangles[i]);
                int[] newOrder = new int[3];
                for (int j = i - 2; j <= i; j++)
                {
                    if (triangles[j] == max)
                    {
                        newOrder[2] = triangles[j];
                    }
                    else if (triangles[j] == min)
                    {
                        newOrder[0] = triangles[j];

                    }
                    else
                    {
                        newOrder[1] = triangles[j];

                    }

                }

                for (int j = i - 2; j <= i; j++)
                {
                    triangles[j] = newOrder[j - (i - 2)];
                }
            }
        }

        mesh.vertices = verticies;
        mesh.triangles = triangles;
        return mesh;
    }
}
