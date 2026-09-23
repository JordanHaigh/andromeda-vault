using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PolygonRenderer : MonoBehaviour
{
    public MeshFilter meshFilter;
    public MeshRenderer meshRenderer;


    // Start is called before the first frame update
    void Start()
    {
       
    }


   public void createMeshFromPolygons()
    {
        Mesh mesh = new Mesh();
        //Vector3[] verticies = new Vector3[4];
        Vector3[] verticies = TrackEditorManager.Instance.polygons[0].points.ToArray();
        //verticies[0] = new Vector3(-10, 1, 0);
        //verticies[1] = new Vector3(10, 1, 0);
        //verticies[2] = new Vector3(10, 1, -10);
        //verticies[3] = new Vector3(-10, 1, -10);

        Vector2[] verts2d = new Vector2[verticies.Length-1];
        for (int i = 0; i < verts2d.Length; i++)
        {
            verts2d[i] = new Vector2(verticies[i].z, verticies[i].x);
            Debug.Log(verts2d[i]);
        }

        Vector3[] verticies2 = new Vector3[4];
        //Vector3[] verticies2 = TrackEditorManager.Instance.polygons[1].points.ToArray();
        verticies2[0] = new Vector3(-10, 1, 0);
        verticies2[1] = new Vector3(10, 1, 0);
        verticies2[2] = new Vector3(10, 1, -10);
        verticies2[3] = new Vector3(-10, 1, -10);
        var holes = new Vector2[1][];
  

        Vector2[] verts2d2 = new Vector2[verticies2.Length];
        for (int i = 0; i < verts2d2.Length; i++)
        {
            verts2d2[i] = new Vector2(verticies2[i].z, verticies2[i].x);
            Debug.Log(verts2d2[i]);
        }
        holes[0] = verts2d2;
        Sebastian.Geometry.Polygon sebPoly = new Sebastian.Geometry.Polygon(verts2d, holes);
        //Sebastian.Geometry.Polygon sebPoly2 = new Sebastian.Geometry.Polygon(verts2d2);

        var trianglulator = new Sebastian.Geometry.Triangulator(sebPoly);

        int[] triangles = trianglulator.Triangulate();

        foreach(int i in triangles){
            Debug.Log(i);
        }
        int a = -1;
        int b = -1;
        int c = -1;
        for (int i = 2; i < triangles.Length; i++)
        {
            if ((i + 1 )% 3 == 0) {
                int max = Mathf.Max(triangles[i - 2], triangles[i - 1], triangles[i]);
                int min = Mathf.Min(triangles[i - 2], triangles[i - 1], triangles[i]);
                int[] newOrder = new int[3];
                for (int j = i-2; j <= i; j++)
                {
                    if(triangles[j] == max)
                    {
                        newOrder[2] = triangles[j];
                    }else if(triangles[j] == min)
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
                    triangles[j] = newOrder[j-(i-2)];
                }
            }
        }

        foreach (int i in triangles)
        {
            Debug.Log("triangle " + i);
        }

        //triangles = new int[] { 0, 1, 3, 1, 2, 3 };

        var allPoints = sebPoly.points;
        Vector3[] points = new Vector3[allPoints.Length+1];
        for(int i = 0; i < points.Length; i++)
        {
            points[i] = new Vector3(points[i].y, 1, points[i].x);
        }
        points[points.Length - 1] = new Vector3(points[0].x,points[0].y, points[0].z);
        mesh.vertices = points;
        mesh.triangles = triangles;

        meshFilter.mesh = mesh;
    }


    // Update is called once per frame
    void Update()
    {
        
    }
}
