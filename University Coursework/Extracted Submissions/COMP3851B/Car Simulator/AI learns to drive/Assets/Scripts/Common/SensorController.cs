using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UnityEngine.SceneManagement;

public class SensorController : MonoBehaviour
{

    public GameObject Line;
    public GameObject carObject;

    //public float sensorLength = 10;
    public SensorInfo[] sensors;


    private float carWidth;
    private float carLength;
    private float carHeight;
    private float carAngle;
    [System.NonSerialized]
    public LineRenderer[] lines;
    private int lineAddedCounter = 0;
    [System.NonSerialized]

    public List<GameObject> lineGameObjects;

    private bool showAll = true;
    private List<SensorInfo> activeSensors;
    // Start is called before the first frame update
    void Start()
    {
        lineGameObjects = new List<GameObject>();
        MeshRenderer renderer = carObject.GetComponent(typeof(MeshRenderer)) as MeshRenderer;
        Vector3 carBounds = renderer.bounds.size;

        Vector3 inverseRotation = transform.rotation.eulerAngles;
        inverseRotation.y *= -1;
        Quaternion inverseRotationQ = Quaternion.Euler(inverseRotation);
        carBounds = inverseRotationQ * carBounds;
        carWidth = carBounds.x;
        carLength = carBounds.z;
        carHeight = carBounds.y;
        carWidth = 0.8f;
        carLength = 2.2f;
        carHeight = carBounds.y;

        carAngle = carObject.transform.rotation.eulerAngles.y;

        lines = new LineRenderer[sensors.Length];


        foreach(SensorInfo sensor in sensors)
        {
            //sensor.setLength(sensorLength);
            sensor.setPositionsBasedOnCar(carWidth, carLength, carHeight, carAngle);
            addLine(sensor);
        }

        if(!SceneManager.GetActiveScene().name.Equals("SLAM")) {
            foreach(SensorInfo sensor in sensors) {
                sensor.distanceToWall = 0;
            }
        }

    }

    // Update is called once per frame
    void FixedUpdate()
    {
        foreach(SensorInfo sensor in sensors) {

            if(!SceneManager.GetActiveScene().name.Equals("SLAM")) {
                sensor.distanceToWall = getDistanceToWall(sensor);
            } else {
                //sensor.distanceToWall = getDistanceToWall(sensor);

                //wait for message from pi
            }
        }

        //if running slam get values from pi
        for(int i = 0; i < sensors.Length; i++) {
            updateLine(lines[i], sensors[i]);
        }
    }

    public void setSensorDistance(SensorInfo sensor, float distance) {
        sensor.distanceToWall = distance;

        int sensorIndex = getSensorIndex(sensor);
        if(sensorIndex == -1)
            return;

        updateLine(lines[sensorIndex], sensors[sensorIndex]);
    }

    public int getSensorIndex(SensorInfo sensor) {
        for(int i = 0; i < sensors.Length; i++) {
            if(sensor == sensors[i]) {
                return i;
            }
        }
        return -1;
    }

    public SensorInfo getSensor(int index) {
        if(index > sensors.Length || index < 0)
            return null;

        return sensors[index];
    }

    public SensorInfo getSensor(string name) {
        for(int i = 0; i < sensors.Length; i++) {
            if(name.Equals(sensors[i].directionName)) {
                return sensors[i];
            }
        }

        return null;
    }


    //creates a line renderer object starting at xStart yStart and going in angle direciton
    //xStart and yStart are relative to the center of the car
    //xstart of 1 means that the line starts at the front of the car, -1 means the back of the car
    //angle is clockwise where 0 is forward
    private void updateLine(LineRenderer lr, SensorInfo sensor)
    {
        
        float length = 10;
        
        Vector3 vec = new Vector3(0, 0, sensor.distanceToWall);
        Quaternion rotation = Quaternion.Euler(0, sensor.angle, 0);
        vec = rotation * vec;

        //set the endPoint of the line based on the angle and the lenght of the line
        float x = sensor.xStart + vec.z;
        float y = sensor.yStart + vec.x;

        lr.SetPosition(1, new Vector3(y, 0, x));
        if(sensor.distanceToWall< sensor.length)
        {
           
            lr.enabled = true;
            lr.startColor = Color.red;
            lr.endColor = Color.red;
        
            float ratio = Mathf.Min(1,(1- sensor.distanceToWall / sensor.length)*1.5f);
            lr.material.color = new Color(1, 0, 0, ratio);
            //lr.material.SetColor("_EmissionColor", new Color(1,0,0,ratio));
            //lr.material.SetColor("_TintColor", new Color(1, 0, 0, 0.1f));
        }
        else
        {
            lr.enabled = false;
            lr.startColor = Color.white;
            lr.endColor = Color.white;
        }
    
        if (!showAll && !activeSensors.Contains(sensor))
        {
            lr.enabled = false;
        }
    }

    public List<Tuple<SensorInfo, Vector3>> getWallContactPoints() {
        //List<Vector3> hits = new List<Vector3>();
        List<Tuple<SensorInfo, Vector3>> hitsFromSensor = new List<Tuple<SensorInfo, Vector3>>();

        foreach(SensorInfo sensor in sensors) {
            if(sensorSeesWall(sensor)) {
                Vector3 wallPoint = getWallPoint(sensor);
                if(wallPoint.y >= -1)
                    hitsFromSensor.Add(new Tuple<SensorInfo, Vector3>(sensor, wallPoint));
                    //hits.Add(wallPoint); //ignore adding point if it is below zero. (this will be in the event that the car is currently inside a wall or the sensor JUST loses sight of the wall
            }
        }

        return hitsFromSensor;

    }

    public bool sensorSeesWall(SensorInfo sensor) {
        return sensor.distanceToWall < sensor.length;
    }

    private void addLine(SensorInfo sensor)
    {
       

        //create line      
        GameObject line = Instantiate(Line);
        line.transform.SetParent(this.transform.Find("Lines"));
        line.transform.localPosition = new Vector3(0, carHeight / 2f, 0);
        line.transform.localRotation = new Quaternion();


        LineRenderer lr = line.GetComponent(typeof(LineRenderer)) as LineRenderer;
        //set the endPoint of the line based on the angle and the lenght of the line
     

        lr.SetPosition(0, new Vector3(sensor.yStart, 0, sensor.xStart));
        lr.SetPosition(1, new Vector3(sensor.yEnd, 0, sensor.xEnd));
        lines[lineAddedCounter] = lr;
        lineAddedCounter++;
        lineGameObjects.Add(line);
    }

    public float getDistanceFromSensorByName(string sensorDirection)
    {
        SensorInfo sensor = getSensorByName(sensorDirection);
        if(sensor == null)
        {
            return -1;
        }
        else
        {
            return sensor.distanceToWall;
            //return getDistanceToWall(sensor); TODO investigate implications of changing this
        }
    }

    public SensorInfo  getSensorByName(string sensorDirection)
    {
        foreach (SensorInfo sensor in sensors)
        {
            if (sensor.directionName.Equals(sensorDirection))
            {
                return sensor;
            }
        }

        return null;
    }

    //public void onlyShowSensors(params string[] names)
    //{
    //    onlyShowSensors(names);
    //}

    public void onlyShowSensors(string[] names)
    {
        activeSensors = new List<SensorInfo>();
        foreach (string name in names)
        {
            SensorInfo s = getSensorByName(name);
            if (s != null)
            {
                activeSensors.Add(s);
            }
        }

        if (activeSensors.Count != sensors.Length)
        {
            showAll = false;
        }

    }

    public Vector3 getVectorFromSensor(SensorInfo sensor) {
        Vector3 pos = new Vector3(sensor.yStart, 0, sensor.xStart);
        pos = this.transform.rotation * pos;
        Vector3 startingPosition = this.transform.position + pos;


        Vector3 direction = new Vector3(0, 0, sensor.length);

        //rotate the direction based on the angle of the sensor
        Quaternion rotation = Quaternion.Euler(0, sensor.angle, 0);
        direction = rotation * direction;

        //rotate the direction based on the angle of the car
        direction = this.transform.rotation * direction;

        direction = direction.normalized;

        Ray sensorRay = new Ray(startingPosition, direction);
        Vector3 point = sensorRay.GetPoint(sensor.distanceToWall);

        Debug.Log("Making a point at: " + point);
        return point;

    }

    public float getDistanceToWall(SensorInfo sensor)
    {
        RaycastHit hit;

        Vector3 pos = new Vector3(sensor.yStart, 0, sensor.xStart);
        pos = this.transform.rotation * pos;
        Vector3 startingPosition = this.transform.position + pos;


        Vector3 direction = new Vector3(0, 0, sensor.length);

        //rotate the direction based on the angle of the sensor
        Quaternion rotation = Quaternion.Euler(0, sensor.angle, 0);
        direction = rotation * direction;

        //rotate the direction based on the angle of the car
        direction = this.transform.rotation * direction;

        direction = direction.normalized;

        Ray sensorRay = new Ray(startingPosition, direction);

        var hitObjects = Physics.RaycastAll(sensorRay, sensor.length);

        //for(int i = 0; i < hitObjects.Length; i++)
        //{
        //    if(hitObjects[i].collider.name.Contains("Wall")) {

        //        return hitObjects[i].distance;
        //    }
        //}

        List<Tuple<Vector3, float>> vectorsAndDistances = new List<Tuple<Vector3, float>>();

        for(int i = 0; i < hitObjects.Length; i++) {
            if(hitObjects[i].collider.name.Contains("Wall")) {
                vectorsAndDistances.Add(new Tuple<Vector3, float>(hitObjects[i].point, hitObjects[i].distance));
            }
        }

        if(vectorsAndDistances.Count == 0) { //When the car goes through a wall or when the sensor JUST loses sight of a wall, the list size will be zero
            return sensor.length; //todo temp fix. the point cloud will need to ignore a point if the Y value is below zero.
        }


        Tuple<Vector3, float> closestVectorAndDistance = vectorsAndDistances[0];
        for(int i = 1; i < vectorsAndDistances.Count; i++) {
            if(vectorsAndDistances[i].Item2 < closestVectorAndDistance.Item2) {
                closestVectorAndDistance = vectorsAndDistances[i];
            }
        }

        return closestVectorAndDistance.Item2;



        ////Debug.DrawRay(startingPosition, direction * sensor.length, Color.white);
        //if (Physics.Raycast(sensorRay, out hit, sensor.length))
        //{

        //    //Debug.DrawRay(startingPosition, direction * hit.distance, Color.red);
        //    return hit.distance;
        //}

        //return sensor.length;
    }

    public Vector3 getWallPoint(SensorInfo sensor) {
        Vector3 pos = new Vector3(sensor.yStart, 0, sensor.xStart);
        pos = this.transform.rotation * pos;
        Vector3 startingPosition = this.transform.position + pos;


        Vector3 direction = new Vector3(0, 0, sensor.length);

        //rotate the direction based on the angle of the sensor
        Quaternion rotation = Quaternion.Euler(0, sensor.angle, 0);
        direction = rotation * direction;

        //rotate the direction based on the angle of the car
        direction = this.transform.rotation * direction;

        direction = direction.normalized;

        Ray sensorRay = new Ray(startingPosition, direction);

        var hitObjects = Physics.RaycastAll(sensorRay, sensor.length);

        //The raycastall can penetrate two walls that are close together
        //It will return a list of 600+ object that it found when casting the raycast all
        //You would assume that if you iterated through these 600 objects, the closer wall will be lower in the list compared to the farther wall
        //Apparently thats not the case
        //What we need to do it generate a list of vectors and distances to walls
        //Then we can iterate through that new list and find the closest distance

        List<Tuple<Vector3, float>> vectorsAndDistances = new List<Tuple<Vector3, float>>();

        for(int i = 0; i < hitObjects.Length; i++) {
            if(hitObjects[i].collider.name.Contains("Wall-Section")) {
                vectorsAndDistances.Add(new Tuple<Vector3, float>(hitObjects[i].point, hitObjects[i].distance));
            }
        }

        if(vectorsAndDistances.Count == 0) { //When the car goes through a wall or when the sensor JUST loses sight of a wall, the list size will be zero
            return new Vector3(0,-10,0); //todo temp fix. the point cloud will need to ignore a point if the Y value is below zero.
        }


        Tuple<Vector3, float> closestVectorAndDistance = vectorsAndDistances[0];
        for(int i = 1; i < vectorsAndDistances.Count;i++) {
            if(vectorsAndDistances[i].Item2 < closestVectorAndDistance.Item2) {
                closestVectorAndDistance = vectorsAndDistances[i];
            }
        }

        return closestVectorAndDistance.Item1;

    }


    public void resetSensors()
    {

        foreach (SensorInfo sensor in sensors)
        {

            sensor.setPositionsBasedOnCar(carWidth, carLength, carHeight, carAngle);
            Debug.Log(carWidth);
            Debug.Log(carLength);
            Debug.Log(carHeight);
            Debug.Log(carAngle);
        }
    }
}

[System.Serializable]
public class SensorInfo : System.Object 
{

    public float xStart;
    public float yStart;
    private float originalXStart = 1000;
    private float originalYStart = 1000;
    public float angle;
    //[System.NonSerialized]
    public float length = 10;
    public string directionName;

    [System.NonSerialized]
    public float distanceToWall = 10;

    [System.NonSerialized]
    public float xEnd;
    [System.NonSerialized]
    public float yEnd;

    public SensorInfo(Boolean runningSlam) {
        if(runningSlam) {
            length = 0;
        }
    }


    public void setLength(float len)
    {
        length = len;
    }

    public void setPositionsBasedOnCar(float carWidth, float carLength,float carHeight, float carAngle)
    {
        if(originalXStart == 1000)
        {
            originalXStart = xStart;
            originalYStart = yStart;
        }
        carAngle = 0;
        Vector3 vec = new Vector3(0, 0, length);
        Quaternion rotation = Quaternion.Euler(0, angle, 0);
        vec = rotation * vec;

        Vector3 startPoint = new Vector3(originalYStart * carWidth / 2f,0, originalXStart * carLength / 2f);
        Quaternion carRotation = Quaternion.Euler(0, carAngle, 0);
        startPoint = carRotation * startPoint;

        xStart = startPoint.z;
        yStart = startPoint.x;
        //set XStart and YStart to be the absolute positions
        //xStart *= carLength / 2f;
        //yStart *= carWidth / 2f;

        vec = carRotation * vec;
        //set the endPoint of the sensor based on the angle and the lenght
        xEnd = xStart + vec.z;
        yEnd = yStart + vec.x;



    }



}
