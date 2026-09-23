using System.Collections.Generic;
using UnityEngine;

public class NNCar {
    
    public GameObject carGameObject;
    public int id;
    public CarController carController;
    public SensorController carSensorController;
    static System.Random random = new System.Random();
    public NN brain;

    public int score = 0;
    public int lifetime = 0;
    private bool allowReverse = false;
    private List<string> sensorsUsed;

    public NNCar(GameObject carGameObject, int id, bool allowReverse) {
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
        carController = carGameObject.GetComponent<CarController>();
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

    public NNCar cloneCar(GameObject carPrefab) {
        NNCar clone = new NNCar(GameObject.Instantiate(carPrefab), id,allowReverse);

        clone.brain = brain.clone();
        return clone;
    }

    public void move() {
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
