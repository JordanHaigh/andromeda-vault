using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Point {
    private int v;

    public Point(Vector3 location, int v) {
        this.location = location;
        this.v = v;
    }

    public int pointID { get; set; }
    public Vector3 location { get; set; }
}
