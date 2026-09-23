using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;
using System.Reflection;
using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
public class QLearningRunner : MonoBehaviour
{
    public GameObject carPrefab;

    public GameObject car { get; set; }
    public int carMovesPerThink = 1;

    public int sensorSections = 5;
    public string[] activeSensorNames;
    public float epsilon = 0.01f;


    public bool allowReverse = true;
    public bool usingCompass = true;
    private float[,] qTable;
    private int noOfStates;
    private int noOfActions = 3;

    [System.NonSerialized]
    public SensorController carSensorController;
    [System.NonSerialized]
    public CarController carController;
    [System.NonSerialized]
    public CompassController carCompassController;


    private int chosenAction = 0;

    private bool finished = false;

    private int noOfStatesFromSensors = 0;

    private int noOfStatesFromSensorsAndVelocity = 0;

    private int stepsSinceLastDied = 0;

    private TCP_Client tcp;
    public bool controllingCarOverTCP = true;

    // Start is called before the first frame update
    public void Start()
    {
        Application.runInBackground = true;
        tcp = GameObject.Find("World").GetComponent<TCP_Client>();

    }

    IEnumerator SleepBitch() {

        yield return new WaitForSeconds(10);

    }

    public void resetQLearning()
    {

        if (allowReverse)
        {
            noOfActions = 6;
        }
        else
        {
            noOfActions = 3;

            if (activeSensorNames.Length == 4)
            {
                activeSensorNames = new string[] { "RIGHT", "LEFT", "FORWARD" };
            }
        }


        carSensorController = car.GetComponent<SensorController>();
        carController = car.GetComponent<CarController>();
        carCompassController = car.GetComponent<CompassController>();
        carController.isQLearning = true;
       
        if (allowReverse)
        {     
            carSensorController.onlyShowSensors(new string[] { "FORWARD", "LEFT", "RIGHT", "BACKWARD" });
        }
        else
        {
            carSensorController.onlyShowSensors(new string[] { "FORWARD", "LEFT", "RIGHT" });
        }

        

        noOfStates = Mathf.FloorToInt(Mathf.Pow(sensorSections, activeSensorNames.Length));

        noOfStatesFromSensors = noOfStates;


        if (allowReverse)
        {
            noOfStates *= 2;//double the number of states because the car can be going forward or backwards
        }


        noOfStatesFromSensorsAndVelocity = noOfStates;


        if (usingCompass)
        {
            noOfStates *= 8; // oof 
        }

        LoadQTable();

    }

    
    // Update is called once per frame

    void FixedUpdate()
    {

        carController.moveCar();
        if (carController.justKilled)
        {
            carController.justKilled = false;
        }
        int currentState = getCurrentStateNo();

        if (UnityEngine.Random.Range(0f, 1f) < epsilon)
        {
            //choose random action
            chosenAction = Mathf.FloorToInt(UnityEngine.Random.Range(0.0f, noOfActions - 0.0001f));
        }
        else
        {
            //chose best action
            chosenAction = getBestAction(currentState);
        }

        makeAction(chosenAction, carController);


    }

    
   
    void makeAction(int action, CarController controller)
    {
        if (!controllingCarOverTCP)
        {
            switch (action)
            {
                case 0:
                    controller.accelerate();
                    controller.turnLeft();

                    break;
                case 1:
                    controller.accelerate();
                    controller.dontTurn();
                    break;
                case 2:
                    controller.accelerate();
                    controller.turnRight();
                    break;
                case 3:
                    controller.reverse();
                    controller.turnLeft();
                    break;
                case 4:
                    controller.reverse();
                    controller.dontTurn();
                    break;
                case 5:
                    controller.reverse();
                    controller.turnRight();
                    break;
            }
        }
        else
        {
            switch (action)
            {
                case 0:
                    tcp.SendMyMessage("FORWARD LEFT");

                    break;
                case 1:
                    tcp.SendMyMessage("FORWARD");
                    break;
                case 2:
                    tcp.SendMyMessage("FORWARD RIGHT");
                    break;
                case 3:
                    tcp.SendMyMessage("BACKWARD LEFT");
                    break;
                case 4:
                    tcp.SendMyMessage("BACKWARD");
                    break;
                case 5:
                    tcp.SendMyMessage("BACKWARD RIGHT");
                    break;
            }
        }
    }


    float getMaxQValue(int stateNo)
    {
        float max = qTable[stateNo, 0];

        for (int i = 1; i < qTable.GetLength(1); i++)
        {
            if (max < qTable[stateNo, i])
            {
                max = qTable[stateNo, i];

            }
        }

        return max;
    }
   
    int getBestAction(int stateNo)
    {
        float max = qTable[stateNo, 0];
        int maxIndex = 0;
        for (int i = 1; i < qTable.GetLength(1); i++)
        {
            if (max < qTable[stateNo, i])
            {
                max = qTable[stateNo, i];
                maxIndex = i;
            }
            //if(max == qTable[stateNo, i])
            //{
            //    if(UnityEngine.Random.Range(0f, 1f) < 0.3f){
            //        max = qTable[stateNo, i];
            //        maxIndex = i;
            //    }
            //}
        }
        return maxIndex;
    }

    int getCurrentStateNo()
    {

       

        SensorController sensorController = carSensorController;
        CarController controller = carController;
        CompassController compassController = carCompassController;

        //get the distances from each sensor
        float[] sensorDistances = new float[activeSensorNames.Length];
        for (int i = 0; i < sensorDistances.Length; i++)
        {
            sensorDistances[i] = sensorController.getDistanceFromSensorByName(activeSensorNames[i]);
        }

        //in order for the input space to be finite we need to split the possible distance values into sections
        //originally this was just split evenly
        //e.g. 7 sections
        //  |<-----------------------------distance range ------------------------------->|
        //from this range we can split it up into 7 categories(sections)
        //  |     0      |     1      |     2      |     3      |     4      |     5     |6 --->

        //however this was not effective because there needs to be more sections when the distances are close
        //so we need to split the sections up based on exponential growth
        //e.g
        //  |<-----------------------------distance range ------------------------->|
        //from this range we can split it up into 6 categories(sections)
        //  |-0-|--1--|----2----|--------3--------|----------------4---------------|5--->  

        //ok so the maths 

        //let n be the number of sections
        //let the d be the max distance of the sensor
        //the distance range needs to be split up into n-1 sections since one section is simply if the distance is out of range
        //the first section covers 1/((2^(n-1)) -1) *d 
        //the second is 2* the fist
        //the third is 2*the second

        //so for example
        //if n = 4;
        //1st covers 1/7*d
        //2nd covers 2/7*d
        //3rd covers 4/7*d
        //4th is all distances >=d



        int[] sectionNos = new int[activeSensorNames.Length];
        //string printString = "";
        for (int i = 0; i < sensorDistances.Length; i++)
        {
            float sensorLength = sensorController.getSensorByName(activeSensorNames[i]).length;
            float d = sensorLength;
            int n = sensorSections;
            float firstSectionSize = 1f / (Mathf.Pow(2, n - 1) - 1) * d;
            float coveredDistance = 0;
            float sectionSize = firstSectionSize;
            int sectionNo = 0;
            while (true)
            {
                coveredDistance += sectionSize;
                if (sensorDistances[i] < coveredDistance)
                {
                    sectionNos[i] = sectionNo;
                    break;
                }
                sectionSize *= 2;
                sectionNo++;
            }
        }

        int stateNo = 0;
        for (int i = 0; i < sensorDistances.Length; i++)
        {
            //printString += activeSensorNames[i] + ": " + sectionNos[i] +   "  actual distance: " + sensorDistances[i] + "   ";
            stateNo += Mathf.FloorToInt(Mathf.Pow(sensorSections, i) * sectionNos[i]);
        }

        if (allowReverse)
        {
            if (!controller.goingForward())
            {
                stateNo += noOfStatesFromSensors; //states are split into [states for going foward | states for going backwards]

            }
        }

        if (usingCompass)
        {
            float compassDirection = compassController.getRoundedCompassAngle();
            int directionNo = (int)(compassDirection / 45);
            stateNo += noOfStatesFromSensorsAndVelocity * directionNo;//states are split into [direction 0 states | direction 1 states | direction 2 states | direction 3 states | direction 4 states | direction 5 states | direction 6 states | direction 7 states ]
        }

        //Debug.Log("StateNo: " + stateNo + "  " + printString);
        return stateNo;
    }


    public void LoadQTable()
    {
        String fileName = @"..\QTableFile\table_States" + noOfStates + "_Actions" + noOfActions + ".sav";
        Debug.Log(fileName);
        if (File.Exists(fileName))
        {
            BinaryFormatter bf = new BinaryFormatter();
            FileStream file = File.Open(fileName, FileMode.Open);
            qTable = (float[,])bf.Deserialize(file);
            file.Close();
            Debug.Log("Loaded QTable File: " + fileName);
        }
        else
        {
            Debug.LogError("No file found which is called " + fileName);
        }
    }

}
