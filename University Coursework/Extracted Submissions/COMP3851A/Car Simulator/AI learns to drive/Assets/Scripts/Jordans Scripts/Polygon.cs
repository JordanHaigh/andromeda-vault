using System.Collections.Generic;
using System.Text;
using UnityEngine;


public class Polygon {
    public Polygon() {
        points = new List<Vector3>();
    }

    public Polygon(List<Vector3> points, int id) {
        this.points = points;
        this.id = id;
    }

    public List<Vector3> points { get; set; }
    public int id { get; set; }

    public string ToString() {
        StringBuilder sb = new StringBuilder();
        sb.Append("Polygon").Append(id).Append("{");

        foreach(Vector3 point in points) {
            sb.Append("\n\t").Append(point.ToString());

        }

        sb.Append("\n}");

        return sb.ToString();
    }
}