using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System.Text.RegularExpressions;
using UnityEngine;
using UnityEngine.SceneManagement;



public class AISceneOnLoad : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        Track track = null;
        GameObject carGameObject = null;

        track = new Track(transform);
        track.loadWorkspace();

        carGameObject = GameObject.Find("Car");
        CarController carController = carGameObject.GetComponent<CarController>();
        carController.controlledByPlayer = false;

        BirdEyeCamera birdEyeCamera = GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>();
        birdEyeCamera.targetCar = carGameObject;

        if(SceneManager.GetActiveScene().name.Equals("Q Learning")) {
            QLearning qlearning = GameObject.Find("World").GetComponent<QLearning>();
            qlearning.car = carGameObject;
            qlearning.resetQLearning();
        } else if (SceneManager.GetActiveScene().name.Equals("Run QLearning Agent"))
        {
            QLearningRunner qlearningRunner = GameObject.Find("World").GetComponent<QLearningRunner>();
            qlearningRunner.car = carGameObject;
            qlearningRunner.resetQLearning();
            Debug.Log("nice");
        }
        else if(SceneManager.GetActiveScene().name.Equals("Basic AI")) {
            basicAIScript basicAIScript = GameObject.Find("World").GetComponent<basicAIScript>();
            basicAIScript.car = carGameObject;
            basicAIScript.carController = carGameObject.GetComponent<CarController>();
            basicAIScript.carSensorController = carGameObject.GetComponent<SensorController>();

        } else if(SceneManager.GetActiveScene().name.Equals("Neural Network")) {
            NeuralNetworkScript neuralNetworkScript = GameObject.Find("World").GetComponent<NeuralNetworkScript>();
            neuralNetworkScript.track = track;
        } 
    } 
}
