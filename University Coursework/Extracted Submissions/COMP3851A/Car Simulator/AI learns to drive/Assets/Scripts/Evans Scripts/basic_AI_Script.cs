using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class basic_AI_Script : MonoBehaviour
{

    public GameObject car;
    public float turningDistance;
    public float breakingDistance;

    private SensorController carSensorController;
    private MyCarController carController;
    // Start is called before the first frame update
    public void Start()
    {
        carSensorController = car.GetComponent<SensorController>();
        carController = car.GetComponent<MyCarController>();
        carController.controlledByPlayer = false;
  
    }


    // Update is called once per frame
    void FixedUpdate()
    {
        float forward = carSensorController.getDistanceFromSensorByName("FORWARD");
        float backward = carSensorController.getDistanceFromSensorByName("BACKWARD");
        float left = carSensorController.getDistanceFromSensorByName("LEFT");
        float right = carSensorController.getDistanceFromSensorByName("RIGHT");

        if (left < right && left < turningDistance)
        {
            carController.turnRight();
        }else if(right < left && right < turningDistance)
        {
            carController.turnLeft();
        }
        else
        {
            carController.dontTurn();
        }


        if (forward < backward && forward < breakingDistance)
        {
            carController.reverse();
        }
        else if (backward < forward && backward < breakingDistance)
        {
            carController.accelerate();
        }
        else if(forward >= turningDistance)
        {
            carController.accelerate();
        }

        //carController.moveCar();
    }

}
