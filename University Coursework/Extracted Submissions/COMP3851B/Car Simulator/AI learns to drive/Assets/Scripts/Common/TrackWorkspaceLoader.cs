using System.Collections.Generic;
using System.IO;
using System.Text.RegularExpressions;
using UnityEngine;

public class TrackWorkspaceLoader{

    public Track loadWorkspace() {
        StandaloneFileBrowser s = GameObject.Find("World").GetComponent<StandaloneFileBrowser>();

        string[] vars = s.OpenFilePanel("Load Track Workspace", "..\\", "", false);
        if(vars.Length > 0) {
            string directory = vars[0];

            return loadWorkspaceFromFile(directory);
        }

        return null;

    }


    private Track loadWorkspaceFromFile(string directory) {
        string text = File.ReadAllText(directory);
        text = Regex.Replace(text, "\r\n", "\n");

        string[] carAndPolygonLocations = text.Split('}');
        /////////////////////////////////
        string carLocation = carAndPolygonLocations[0];
        string[] rotationText = Regex.Split(carLocation, "Rotation:");

        int carRotationAngle = int.Parse(rotationText[1]);
        string carCoordinates = Regex.Replace(carLocation, @"[Car:{}(),]|(\w*:(\d+))", "");
        string[] split = carCoordinates.Trim().Split(' ');

        Vector3 startPosition = new Vector3(float.Parse(split[0]), float.Parse(split[1]), float.Parse(split[2]));
        List<Polygon> polygons = new List<Polygon>();

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
            polygons.Add(p);
        }

        return new Track(polygons, startPosition, carRotationAngle);
    }
   
}
