using System.Collections.Generic;
using UnityEngine;

public class Car {
    

    
    public GameObject carGameObject;
    public int id;
    public MyCarController carController;
    public SensorController carSensorController;
    static System.Random random = new System.Random();
    public NN brain;

    public int score = 0;
    public int lifetime = 0;
    private bool allowReverse = false;
    private List<string> sensorsUsed;

    public Car(GameObject carGameObject, int id, bool allowReverse) {
        this.carGameObject = carGameObject;
        this.allowReverse = allowReverse;

        sensorsUsed = new List<string> { "FORWARD", "LEFT", "RIGHT" };
      
        if (allowReverse)
        {
            sensorsUsed.Add("BACKWARD");
        }
  
        

        GameObject linesChild = this.carGameObject.transform.GetChild(3).gameObject;
        int childcount = linesChild.transform.childCount;
        for(int i = 0; i < childcount; i++) {
            GameObject.Destroy(linesChild.transform.GetChild(0).gameObject);
        }
        this.id = id;
        carController = carGameObject.GetComponent<MyCarController>();
        carSensorController = carGameObject.GetComponent<SensorController>();
        carController.controlledByPlayer = false;

        carSensorController.onlyShowSensors(sensorsUsed.ToArray());
     

        int noOfOutputs = (allowReverse) ? 6 : 3;
        int[] topology = new int[] { sensorsUsed.Count, 10, noOfOutputs };
        brain = new NN(topology);

        if (carSensorController.sensors[0].xStart == 0)
        {
            Debug.Log("haa");
        }
    }

    public Car cloneCar(GameObject carPrefab) {
        Car clone = new Car(GameObject.Instantiate(carPrefab), id,allowReverse);

        clone.brain = brain.clone();
        return clone;
    }

    public void move() {
        //semi working fitness funciton - evan give us some pointers.
        ////every 1 second/s
        //if(Time.realtimeSinceStartup % 1.0 < 0.01) {
        //    // if distance from last checkpoint is greater than value

        //    if(Vector3.Distance(carController.lastCheckpoint, carController.transform.position) < 5) {
        //        // we've been here too long
        //        carController.killCar();
        //        return;
        //    } else {
        //        //update checkpoint
        //        carController.lastCheckpoint = carController.transform.position;
        //    }
        //}


        var input = new List<double>();
        foreach(string sensorName in sensorsUsed)
        {
            input.Add(carSensorController.getDistanceFromSensorByName(sensorName));
        }

        
        var output = brain.predict(input);

        double record = 0.0;
        int number = 0;

        for(int i = 0; i < output.Count; i++) {
            if(output[i] > record) {
                record = output[i];
                number = i;
            }
        }

        lifetime++;

        //if the car is allowed to reverse then it has 6 outputs therefore the cases 3-5 can only be used if it is allowed to reverse
        switch (number)
        {
          
            case 0:
                carController.accelerate();
                carController.turnRight();
                score += 1;
                break;
            case 1:
                carController.accelerate();
                carController.dontTurn();
                score += 1;
                break;
            case 2:
                carController.accelerate();
                carController.turnLeft();
                score += 1;
                break;
            case 3:
                carController.reverse();
                carController.turnRight();
                score = Mathf.Max(score - 1, 1);
                break;
            case 4:
                carController.reverse();
                carController.dontTurn();
                score = Mathf.Max(score - 1, 1);
                break;
            case 5:
                carController.reverse();
                carController.turnLeft();
                score = Mathf.Max(score - 1, 1);
                break;
        }

        //switch (number) {
        //    case 0:
        //        carController.accelerate();
                
        //        score += 1;
        //        break;
        //    case 1:
        //        carController.accelerate();
        //        carController.turnLeft();
        //        score += 1;
        //        break;
        //    case 2:
        //        carController.accelerate();
        //        carController.turnRight();
        //        score += 1;
        //        break;
        //}

    }

    public void destroy() {
        GameObject.Destroy(carGameObject);
        //LineRenderer[] lines = carSensorController.lines;


        foreach(GameObject line in carSensorController.lineGameObjects) {
            Debug.Log(line);
            GameObject.Destroy(line);
        }
        
    }
}
