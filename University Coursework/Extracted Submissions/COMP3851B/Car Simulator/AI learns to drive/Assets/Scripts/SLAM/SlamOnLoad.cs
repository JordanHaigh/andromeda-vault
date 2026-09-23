using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SlamOnLoad : MonoBehaviour
{
    // Start is called before the first frame update

    Track track = null;
    GameObject carGameObject = null;

    void Start()
    {
        Application.runInBackground = true;
        track = new Track(transform);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///DUMBSHIT
        track.startPosition = new Vector3(0f, 1.5f, 0f);
        track.carRotationAngle = 0;
        track.placeCar();
        carGameObject = GameObject.Find("Car");
        carGameObject.GetComponent<CarController>().controlledByPlayer = false;
        carGameObject.GetComponent<CarController>().frictionMultiplier= 0.8f;

        TCPServer server = GameObject.Find("World").GetComponent<TCPServer>();
        server.enabled = true;
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///SAM
        //GameObject wallPrefab = (GameObject)Resources.Load("WallPrefab");
        //wallPrefab.GetComponent<MeshRenderer>().enabled = false;
        //track.loadWorkspace();
        //carGameObject = GameObject.Find("Car");
        //carGameObject.GetComponent<CarController>().controlledByPlayer = true;
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        ///Q LEARNING AI HOOKUP
        carGameObject.GetComponent<CarController>().controlledByPlayer = false;
        QLearningRunner qlearningRunner = GameObject.Find("World").GetComponent<QLearningRunner>();
        qlearningRunner.enabled = true;

        qlearningRunner.car = carGameObject;
        qlearningRunner.resetQLearning();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////





        BirdEyeCamera birdEyeCamera = GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>();
        birdEyeCamera.targetCar = carGameObject;

        SLAM slamScript = GameObject.Find("World").GetComponent<SLAM>();
        slamScript.car = carGameObject;
    }
}
