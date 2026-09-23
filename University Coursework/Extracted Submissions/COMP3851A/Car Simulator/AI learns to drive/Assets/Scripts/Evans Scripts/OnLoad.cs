using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.RegularExpressions;
using UnityEngine;
using UnityEngine.SceneManagement;



public class OnLoad : MonoBehaviour
{
    private GameObject car;

    // Start is called before the first frame update
    void Start()
    {
        LoadWorkspace.loadWorkspace();

        car = GameObject.Find("Car");
        MyCarController myCarController = car.GetComponent<MyCarController>();
        myCarController.controlledByPlayer = false;
        BirdEyeCamera birdEyeCamera = GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>();
        birdEyeCamera.targetCar = car;

        if (SceneManager.GetActiveScene().name == "Q Learning")
        {
            QLearning qlearning= GameObject.Find("Q Learning").GetComponent<QLearning>();
            qlearning.car = car;
            qlearning.resetQLearning();
        }
        else
        {
            basic_AI_Script basic_AI_Script = GameObject.Find("Basic AI").GetComponent<basic_AI_Script>();
            basic_AI_Script.car = car;
        }


        


        //EHC ehc_script = GameObject.Find("Basic AI").GetComponent<EHC>();
        //ehc_script.car = car;


    } 

    void setCarTransform(Vector3 point, float carRotation)
    {
        Vector3 position = new Vector3(point.x, 0f, point.z);
        car.GetComponent<MyCarController>().setStartingPositionAndRotation(position, carRotation);
    }

}
