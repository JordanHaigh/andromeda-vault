using System.Collections;
using System.Collections.Generic;
using UnityEngine;

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
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        
        foreach (SensorInfo sensor in sensors)
        {
            sensor.distanceToWall = getDistanceToWall(sensor);
        }
        
        for(int i = 0; i < sensors.Length; i++)
        {
            updateLine(lines[i], sensors[i]);
        }
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

        for(int i = 0; i < hitObjects.Length; i++)
        {
            if(hitObjects[i].collider.name.Contains("Wall")) {

                return hitObjects[i].distance;
            }
        }

        ////Debug.DrawRay(startingPosition, direction * sensor.length, Color.white);
        //if (Physics.Raycast(sensorRay, out hit, sensor.length))
        //{
            
        //    //Debug.DrawRay(startingPosition, direction * hit.distance, Color.red);
        //    return hit.distance;
        //}

        return sensor.length;
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
