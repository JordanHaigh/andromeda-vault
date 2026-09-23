using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEditor;
using System.Reflection;
using System;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

public class QLearning : MonoBehaviour {
    public GameObject carPrefab;
    public GameObject car { get; set; }
    public int populationSize = 0;
    public GameObject[] cars;

    public int carMovesPerThink = 5;

    public int sensorSections = 5;
    public string[] activeSensorNames;
    public float learningRate = 0.2f;
    public float discountRate = 0.95f;
    public int maxTrainingEpisodes = 1000;
    public float maxEpsilon = 0.95f;
    public float minEpsilon = 0.01f;
    public float epsilonDecayRate = 0.0025f;
    public float epsilon;

    public float rewardForDeath = -1000;
    //public float rewardForNotDeath = 0.1f;
    public float rewardForMovingForward;
    public float rewardForMovingBackwards;


    //---------------compass rewards

    public float rewardForMovingWithCompass;
    public float rewardForReversingWhenUsingCompass;


    public bool allowReverse = true;
    public bool usingCompass = true;
    private float[,] qTable;
    private int noOfStates;
    private int noOfActions = 3;
    private int episodeNo = 0;

    public bool training = true;
    public bool newQTable = true;

    public SensorController carSensorController;
    public CarController carController;
    public CompassController carCompassController;

    public SensorController[] carSensorControllers;
    public CarController[] carControllers;
    public CompassController[] carCompassControllers;
    //private int previousState = -1;
    //private int previousChosenAction = 0;
    private int chosenAction = 0;
    private bool finished = false;

    private int[] previousStates;
    private int[] previousChosenActions;

    private int noOfStatesFromSensors = 0;

    private int noOfStatesFromSensorsAndVelocity = 0;

    private int stepsSinceLastDied = 0;

    


    // Start is called before the first frame update
    public void Start() {
        Application.runInBackground = true;
        if(cars == null) {
            return;
        }

        //carSensorController = car.GetComponent<SensorController>();
        //carController = car.GetComponent<MyCarController>();
        //carController.isQLearning = true;
        ////carController.controlledByPlayer = false;

        //noOfStates = Mathf.FloorToInt(Mathf.Pow(sensorSections, activeSensorNames.Length));
        //qTable = new float[noOfStates, noOfActions];
        //for (int i = 0; i < qTable.GetLength(0); i++)
        //{
        //    for (int j = 0; j < qTable.GetLength(1); j++)
        //    {
        //        qTable[i, j] = 100;
        //    }
        //}

        //epsilon = maxEpsilon;

    }

    public void resetQLearning() {

        if(allowReverse) {
            noOfActions = 6;
        } else {
            noOfActions = 3;
            
            if(activeSensorNames.Length == 4) {
                activeSensorNames = new string[] { "RIGHT", "LEFT", "FORWARD" };
            }
        }


        cars = new GameObject[populationSize];

        carControllers = new CarController[populationSize];
        carSensorControllers = new SensorController[populationSize];
        carCompassControllers = new CompassController[populationSize];

        previousStates = new int[populationSize];
        previousChosenActions = new int[populationSize];

        carSensorController = car.GetComponent<SensorController>();
        carController = car.GetComponent<CarController>();
        carCompassController = car.GetComponent<CompassController>();

        cars[0] = car;
        carController.isQLearning = true;
        carSensorControllers[0] = carSensorController;
        carControllers[0] = carController;
        carCompassControllers[0] = carCompassController;

        for(int i = 1; i < populationSize; i++) {
            cars[i] = Instantiate(carPrefab);


            carControllers[i] = cars[i].GetComponent<CarController>();
            carControllers[i].setStartingPositionAndRotation(car.transform.position, car.transform.rotation.eulerAngles.y);

            carControllers[i].isQLearning = true;
            carControllers[i].controlledByPlayer = false;

            carSensorControllers[i] = cars[i].GetComponent<SensorController>();

            carCompassControllers[i] = cars[i].GetComponent<CompassController>();


            previousStates[i] = -1;
            previousChosenActions[i] = 0;
        }

        foreach(SensorController sc in carSensorControllers) {
            if(allowReverse) {

                sc.onlyShowSensors(new string[] { "FORWARD", "LEFT", "RIGHT", "BACKWARD" });
            } else {

                sc.onlyShowSensors(new string[] { "FORWARD", "LEFT", "RIGHT" });
            }
            //sc.onlyShowSensors(new string[] { });
        }

        noOfStates = Mathf.FloorToInt(Mathf.Pow(sensorSections, activeSensorNames.Length));

        noOfStatesFromSensors = noOfStates;


        if (allowReverse) {
            noOfStates *= 2;//double the number of states because the car can be going forward or backwards
        }


        noOfStatesFromSensorsAndVelocity = noOfStates;


        if (usingCompass)
        {
            noOfStates *= 8; // oof 
        }


        LoadQTable();

        epsilon = maxEpsilon;
    }

   
    public void destroyCarsObjects() {
        for(int i = 0; i < cars.Length; i++) {
            Destroy(cars[i]);
        }

    }
    // Update is called once per frame

    public int updateCounter;
    void FixedUpdate() {
        checkKeyboardInput();


        for(int i = 0; i < populationSize; i++) {

            carControllers[i].moveCar();
            if(carControllers[i].moveCounter >= carMovesPerThink) {

                carControllers[i].moveCounter = 0;

                if(training) {
                    train(i);
                } else {
                    testAndTrain(i);
                }

            }

        }

        updateCounter++;
        if(updateCounter % 100 == 0) {
            SaveQTable();
        }


    }



    private int resetQTableCounter = 0;

    private float randomMoves = 0;
    private float nonRandomMoves = 0;
    void train(int carNo)//MyCarController controller, SensorController sensorCont, int previousState, int previousChosenAction)
    {
        CarController controller = carControllers[carNo];
        SensorController sensorCont = carSensorControllers[carNo];
        int previousState = previousStates[carNo];
        int previousChosenAction = previousChosenActions[carNo];


        bool carDied = controller.justKilled;
        if(carDied) {
            newEpisode();
            controller.justKilled = false;

        }

        int currentState = getCurrentStateNo(carNo);

        if(previousState != -1) {
            float reward = getReward(carNo);
            updateQTable(previousState, reward, previousChosenAction, currentState, carDied);

            //Debug.Log("Reward: " + reward + " previous State: " + previousState + " currentState: " + currentState);

        }


        //choose action based on epsilon greedy
        if(UnityEngine.Random.Range(0f, 1f) < epsilon) {
            //choose random action
            chosenAction = Mathf.FloorToInt(UnityEngine.Random.Range(0.0f, noOfActions - 0.0001f));

            randomMoves++;
        } else {
            //chose best action
            chosenAction = getBestAction(currentState);
            //Debug.Log("AHHHHHHH");
            nonRandomMoves++;
        }
        //Debug.Log(UnityEngine.Random.Range(0.0f, 1f));
        //Debug.Log(UnityEngine.Random.Range(0, 1));


        previousStates[carNo] = currentState;
        previousChosenActions[carNo] = chosenAction;
        makeAction(chosenAction, controller);


    }



    void test(int carNo)//MyCarController controller, SensorController sensorCont, int previousState, int previousChosenAction)
    {
        CarController controller = carControllers[carNo];
        SensorController sensorCont = carSensorControllers[carNo];
        int previousState = previousStates[carNo];
        int previousChosenAction = previousChosenActions[carNo];

        bool carDied = controller.justKilled;
        controller.justKilled = false;


        int currentState = getCurrentStateNo(carNo);

        //choose action based on epsilon greedy
        //if (UnityEngine.Random.Range(0f, 1f) < minEpsilon)
        //{
        //    //choose random action
        //    chosenAction = Mathf.FloorToInt(UnityEngine.Random.Range(0f, noOfActions - 0.00001f));

        //}
        //else
        //{
        //chose best action
        chosenAction = getBestAction(currentState);

        //}
        makeAction(chosenAction, controller);
    }


    void testAndTrain(int carNo)//MyCarController controller, SensorController sensorCont, int previousState, int previousChosenAction)
    {
        CarController controller = carControllers[carNo];
        SensorController sensorCont = carSensorControllers[carNo];
        int previousState = previousStates[carNo];
        int previousChosenAction = previousChosenActions[carNo];

        bool carDied = controller.justKilled;
        if(carDied) {
            newEpisode();
            controller.justKilled = false;
        }

        int currentState = getCurrentStateNo(carNo);

        if(previousState != -1) {
            float reward = getReward(carNo);
            updateQTable(previousState, reward, previousChosenAction, currentState, carDied);

        }



        //chose best action
        chosenAction = getBestAction(currentState);


        previousStates[carNo] = currentState;
        previousChosenActions[carNo] = chosenAction;
        makeAction(chosenAction, controller);


    }
    void newEpisode() {
        episodeNo++;
        float e = 2.71828f;
        epsilon = (float)(minEpsilon + (maxEpsilon - minEpsilon) * Mathf.Pow(e, -epsilonDecayRate * episodeNo));


    }
    void makeAction(int action, CarController controller) {

        switch(action) {
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


    void updateQTable(int fromState, float reward, int action, int toState, bool carDied) {
        float currentQValue = qTable[fromState, action];
        if(carDied) {
            qTable[fromState, action] = currentQValue + learningRate * (reward - currentQValue);

            //Debug.Log("Died from State: " + fromState + " action: " + action);

            //printDetailsFromStateNo(fromState);

        } else {
            float newQValue = currentQValue + learningRate * (reward + discountRate * getMaxQValue(toState) - currentQValue);
            qTable[fromState, action] = newQValue;
        }
    }


    float getMaxQValue(int stateNo) {
        float max = qTable[stateNo, 0];

        for(int i = 1; i < qTable.GetLength(1); i++) {
            if(max < qTable[stateNo, i]) {
                max = qTable[stateNo, i];

            }
        }

        return max;
    }
    float getReward(int carNo) {

        bool carDied = carControllers[carNo].justKilled;
        int previousAction = previousChosenActions[carNo];


        //get total sensor distance
        SensorController sensorController = carSensorControllers[carNo];


        float totalDistanceFromWalls = 0;
        float totalPossibleDistance = 0;
        float minDistanceToWall = 10000;
        float minPossibleDistanceToWall = 10000;
        for (int i = 0; i < activeSensorNames.Length; i++)
        {
           
            float distanceFromWall = sensorController.getDistanceFromSensorByName(activeSensorNames[i]);
            if (minDistanceToWall > distanceFromWall)
            {
                minDistanceToWall = distanceFromWall;
            }


            float possibleDistance = sensorController.getSensorByName(activeSensorNames[i]).length;
            if (minPossibleDistanceToWall > possibleDistance)
            {
                minPossibleDistanceToWall = possibleDistance;
            }
            //totalPossibleDistance = sensorController.getSensorByName(activeSensorNames[i]).length;
        }


        float wallDistanceRewardAddition = 2 * minDistanceToWall / minPossibleDistanceToWall;
       


        if(carDied) {
            return rewardForDeath;
        } else {
            if (usingCompass)
            {

                if (previousAction < 3)
                {//if moving Forward
                    float componentWithCompass = carCompassControllers[carNo].getComponentOfCarsVelocityFollowingCompass();
                    return rewardForMovingWithCompass * componentWithCompass + wallDistanceRewardAddition;


                }
                else
                {
                    return rewardForReversingWhenUsingCompass + wallDistanceRewardAddition;
                }

            }
            else
            {
                if (previousAction < 3)
                {//if moving Forward
                    return rewardForMovingForward + wallDistanceRewardAddition;
                }
                else
                {
                    return rewardForMovingBackwards + wallDistanceRewardAddition;
                }
            }

        }
    }

    int getBestAction(int stateNo) {
        float max = qTable[stateNo, 0];
        int maxIndex = 0;
        for(int i = 1; i < qTable.GetLength(1); i++) {
            if(max < qTable[stateNo, i]) {
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

    int getCurrentStateNo(int carNo){



            SensorController sensorController = carSensorControllers[carNo];
            CarController controller = carControllers[carNo];
            CompassController compassController = carCompassControllers[carNo];

        //get the distances from each sensor
        float[] sensorDistances = new float[activeSensorNames.Length];
        for(int i = 0; i < sensorDistances.Length; i++) {
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
        for(int i = 0; i < sensorDistances.Length; i++) {
            float sensorLength = sensorController.getSensorByName(activeSensorNames[i]).length;
            float d = sensorLength;
            int n = sensorSections;
            float firstSectionSize = 1f / (Mathf.Pow(2, n - 1) - 1) * d;
            float coveredDistance = 0;
            float sectionSize = firstSectionSize;
            int sectionNo = 0;
            while(true) {
                coveredDistance += sectionSize;
                if(sensorDistances[i] < coveredDistance) {
                    sectionNos[i] = sectionNo;
                    break;
                }
                sectionSize *= 2;
                sectionNo++;
            }
        }

        int stateNo = 0;
        for(int i = 0; i < sensorDistances.Length; i++) {
            //printString += activeSensorNames[i] + ": " + sectionNos[i] +   "  actual distance: " + sensorDistances[i] + "   ";
            stateNo += Mathf.FloorToInt(Mathf.Pow(sensorSections, i) * sectionNos[i]);
        }

        if(allowReverse) {
            if(!controller.goingForward()) {
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



    void printDetailsFromStateNo(int stateNo) {//this needs updating
        //get section nos from state no
        int[] sectionNos = new int[activeSensorNames.Length];
        int temp = stateNo;
        for(int i = activeSensorNames.Length - 1; i >= 0; i--) {
            int sectionNoForI = Mathf.FloorToInt(temp / Mathf.Pow(sensorSections, i));
            sectionNos[i] = sectionNoForI;
            temp -= Mathf.FloorToInt(sectionNoForI * Mathf.Pow(sensorSections, i));
        }
        if(temp != 0) {
            Debug.Log("FUCK");

        }
        string printString = "State No: " + stateNo + " [ ";


        for(int i = 0; i < qTable.GetLength(1); i++) {
            printString += qTable[stateNo, i] + " ";
        }
        printString += "]";
        printString += "\n\n Section numbers for sensors";

        for(int i = 0; i < activeSensorNames.Length; i++) {
            printString += "\n " + activeSensorNames[i] + ": " + sectionNos[i];

        }

        printString += "\n\n";
        Debug.Log(printString);





    }

    void clearDebuggerAndPrintQTable() {
        //Utils.ClearLogConsole();
        for(int i = 0; i < qTable.GetLength(0); i++) {
            printDetailsFromStateNo(i);
        }
    }
    void checkKeyboardInput() {
        if(Input.GetKeyDown(KeyCode.Equals)) {
            if(Time.timeScale >= 32) {
                return;
            }
            Time.timeScale *= 2f;
            Debug.Log("Time scale = " + Time.timeScale);

        } else if(Input.GetKeyDown(KeyCode.Minus)) {
            if(Time.timeScale < 0.1) {
                return;
            }
            Time.timeScale = 1f;
            Debug.Log("Time scale = " + Time.timeScale);

        } else if(Input.GetKeyDown(KeyCode.T)) {
            training = !training;
            Debug.Log("Training = " + training);


        }
    }


    public void SaveQTable() {


        String fileName = @"..\QTableFile\table_States" + noOfStates + "_Actions" + noOfActions + ".sav";
        BinaryFormatter bf = new BinaryFormatter();
        FileStream file = File.Create(fileName);
        bf.Serialize(file, qTable);
        file.Close();
        Debug.Log("Saved QTable");


    }
    public void LoadQTable() {
        String fileName = @"..\QTableFile\table_States" + noOfStates + "_Actions" + noOfActions + ".sav";
        Debug.Log(fileName);
        if(!newQTable && File.Exists(fileName)) {
            BinaryFormatter bf = new BinaryFormatter();
            FileStream file = File.Open(fileName, FileMode.Open);
            qTable = (float[,])bf.Deserialize(file);
            file.Close();
            Debug.Log("Loaded QTable File: " + fileName);
        } else {
            qTable = new float[noOfStates, noOfActions];
            for(int i = 0; i < qTable.GetLength(0); i++) {
                for(int j = 0; j < qTable.GetLength(1); j++) {
                    qTable[i, j] = 100;
                }
            }
        }
    }


    

}









//public static class Utils
//{
//    static MethodInfo _clearConsoleMethod;
//    static MethodInfo clearConsoleMethod
//    {
//        get
//        {
//            if (_clearConsoleMethod == null)
//            {
//                Assembly assembly = Assembly.GetAssembly(typeof(SceneView));
//                Type logEntries = assembly.GetType("UnityEditor.LogEntries");
//                _clearConsoleMethod = logEntries.GetMethod("Clear");
//            }
//            return _clearConsoleMethod;
//        }
//    }

//    public static void ClearLogConsole()
//    {
//        clearConsoleMethod.Invoke(new object(), null);
//    }
//}







    
    /*
    ok so heres the plan chief
    we need a way of controlling the cars using QLearning, at the moment they are just trying to survive. this isn't good enough.
    So the plan is to add a Compass to the AIs, this is actually going to be really cool. 
    The compass needs to be distinct (i.e. it cannot have an infinite ammount of the directions) so we are going to round the compass to the nearest 45 degrees
    this will multiply the size of the Qtable by 8, however it will be worth it.

    To train them to follow the compass we will give the car a reward based on the component of the their movement which is parallel with the compass.
    

    The training will not be easy. I will need to create a progression of steps.

    hmm i hope this will work

    ahh thats all I think. 
    how are we going to handle reversing, since we want the cars to turn around instead of reversing. 
    we could only reward the car for following the compass if they are going forward. thats accutally fine

    Do we need different states for currently reversing?   it would probably be good.


    ok I'm ready.







    
    Todo 
    - add a compass to the cars. DONE
    - display this compass DONE
    - incorporate the compass into the qtable DONE
    - testing compass encorportation DONE
    - do Reward logic DONE
    - create first training environment, it will probably be a box filled with obsticles, where each car gets a random direction. DONE








     */