using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System.Text.RegularExpressions;
using System;

public class SLAM : MonoBehaviour {
    List<Point> points = new List<Point>();
    int pointCounter = 0;
    GameObject pointCloud;

    Material redMaterial;
    Material cyanMaterial;
    Material greenMaterial;

    public GameObject car { get; set; }



    // Start is called before the first frame update
    void Start() {
        pointCloud = new GameObject();
        pointCloud.name = "PointCloud";
        pointCloud.transform.parent = GameObject.Find("World").transform;

        redMaterial = (Material)Resources.Load("RedMaterial");
        cyanMaterial = (Material)Resources.Load("CyanMaterial");
        greenMaterial = (Material)Resources.Load("GreenMaterial");

    }

    // Update is called once per frame

    void Update() {
        GameObject car = GameObject.Find("Car");
        SensorController carSensor = car.GetComponent<SensorController>();

        List<Tuple<SensorInfo, Vector3>> wallContactPoints = carSensor.getWallContactPoints();
        foreach(Tuple<SensorInfo, Vector3> tuple in wallContactPoints) {
            SensorInfo sensor = tuple.Item1;
            Vector3 point = tuple.Item2;

            addPointToRespectivePointCloudAndDisplay(point);
            createCylinder(point, pointCloud);
        }

    }

    void addPointToRespectivePointCloudAndDisplay(Vector3 location) {
        //dumb down vector to 4dp
        float x = location.x;
        float y = location.y;
        float z = location.z;


        x = (float)Decimal.Round((Decimal)x, 2);
        y = (float)Decimal.Round((Decimal)y, 2);
        z = (float)Decimal.Round((Decimal)z, 2);

        location = new Vector3(x, y, z);

        Point point;
        point = new Point(location, pointCounter++);

        if(pointLocationAlreadyExistInList(location)) {
            //decrement the point counter we just did
            pointCounter--;
            return; //dont bother readding it to the list
        }


        points.Add(point);

        createNewPoint(point, pointCloud, cyanMaterial);
        createCylinder(location, pointCloud);

    }

    void createNewPoint(Point point, GameObject pointCloudParent) {
        PrefabCreator.createPoint(point.location, "Point(" + point.pointID + ")", pointCloudParent, cyanMaterial);
    }

    void createNewPoint(Point point, GameObject pointCloudParent, Material material) {
        PrefabCreator.createPoint(point.location, "Point(" + point.pointID + ")", pointCloudParent, material);
    }

    public void receiveMessage(string sender, string message) {
        string cleanedSender = Regex.Replace(sender, "\"", ""); //replacing quotations with nothing in the sender message 

        if(cleanedSender.Contains("Sensor")) {
            receivePointInformation(cleanedSender, message);
        } else if(cleanedSender.Contains("6Axis")) {
            updateAxisInformation(cleanedSender, message);
        } else if(cleanedSender.Contains("Dist")) {
            updateSensorDistance(cleanedSender, message);
        } else {
            Debug.Log("RECEIVED A MESSAGE BUT DON'T KNOW WHO FROM???");
        }

    }

    public bool pointLocationAlreadyExistInList(Vector3 newLocation) {

        for(int i = 0; i < points.Count; i++) {
            if(points[i].location.Equals(newLocation)) {
                return true;
            }
        }

        return false;
    }

    private void receivePointInformation(string cleanedSender, string message) {
        string cleanedMessage = Regex.Replace(message, "([\",><]+)", " ");
        string[] splitvalue = Regex.Split(cleanedMessage, " ");

        Vector3 location = new Vector3(float.Parse(splitvalue[1]), float.Parse(splitvalue[2]), float.Parse(splitvalue[3])); // ["] [x] [y] [z] ["]

        if(cleanedSender.Equals("Sensor")) {
            addPointToRespectivePointCloudAndDisplay(location);
        } else if(cleanedSender.Equals("devSensor")) {
            debug_createEnvironment(location);
        }
    }

    private void updateAxisInformation(string cleanedSender, string message) {
        string cleanedMessage = Regex.Replace(message, "([\",><]+)", " "); // ["] [ACCEL] [GRYO] ["]
        string[] splitvalue = Regex.Split(cleanedMessage, " ");

        float motorState = float.Parse(splitvalue[1]);
        float turnDegrees = float.Parse(splitvalue[2]);

        CarController cc = GameObject.Find("Car").GetComponent<CarController>();
        cc.setMotorState(motorState);

        Transform carTransform = GameObject.Find("Car").transform;
        carTransform.localEulerAngles = new Vector3(0f, turnDegrees, 0f);
    }

    private void updateSensorDistance(string cleanedSender, string message) {
        //forward backward left right
        string cleanedMessage = Regex.Replace(message, "([\",><]+)", " "); // ["] [FORWARD][BACKWARD][LEFT][RIGHT] ["]
        string[] splitvalue = Regex.Split(cleanedMessage, " ");

        float forward_distanceFromUltrasonic = float.Parse(splitvalue[1]);
        float backward_distanceFromUltrasonic = float.Parse(splitvalue[2]);
        float left_distanceFromUltrasonic = float.Parse(splitvalue[3]);
        float right_distanceFromUltrasonic = float.Parse(splitvalue[4]);



        SensorController sc = GameObject.Find("Car").GetComponent<SensorController>();

        SensorInfo forward_sensor = sc.getSensor("FORWARD");
        SensorInfo backward_sensor = sc.getSensor("BACKWARD");
        SensorInfo left_sensor = sc.getSensor("LEFT");
        SensorInfo right_sensor = sc.getSensor("RIGHT");

        if(forward_distanceFromUltrasonic >= 0)
            setSensorDistanceAndAddPoint(sc, forward_sensor, forward_distanceFromUltrasonic);

        if(backward_distanceFromUltrasonic >= 0)
            setSensorDistanceAndAddPoint(sc, backward_sensor, backward_distanceFromUltrasonic);

        if(left_distanceFromUltrasonic >= 0)
            setSensorDistanceAndAddPoint(sc, left_sensor, left_distanceFromUltrasonic);

        if(right_distanceFromUltrasonic >= 0)
            setSensorDistanceAndAddPoint(sc, right_sensor, right_distanceFromUltrasonic);

    }

    void setSensorDistanceAndAddPoint(SensorController sc, SensorInfo sensorInfo, float distanceFromUltrasonic) {
        sc.setSensorDistance(sensorInfo, distanceFromUltrasonic);
        Vector3 newPoint = sc.getVectorFromSensor(sensorInfo);

        addPointToRespectivePointCloudAndDisplay(newPoint);
    }

    void createNewWall(Vector3 a, Vector3 b, GameObject polygonParent) {
        //PrefabCreator.createWall(a, b, this.transform, "WallSection", polygonParent);
        PrefabCreator.createSlamWall(a, b, this.transform, "SlamWall", polygonParent);
    }

    void createCylinder(Vector3 point, GameObject parent) {
        Vector3 dummySecondPoint = new Vector3(point.x + 1f, point.y, point.z);
        createNewWall(point, dummySecondPoint, parent);

    }

    void debug_createEnvironment(Vector3 location) {
        //bork the right side point cloud for dev testing
        Point point = new Point(location, 0);

        if(pointLocationAlreadyExistInList(location))
            return;

        points.Add(point);

        createCylinder(point.location, pointCloud);
    }
}

