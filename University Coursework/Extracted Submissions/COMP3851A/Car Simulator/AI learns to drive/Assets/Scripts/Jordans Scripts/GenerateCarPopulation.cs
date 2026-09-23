using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;

using Weights = System.Collections.Generic.List<System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>>;


public class GenerateCarPopulation : MonoBehaviour {
   
    // Start is called before the first frame update
    public int populationSize;
    public float maxDuration = 1000;

    public GameObject carPrefab;
    private List<Car> cars = new List<Car>();

    public int generation;
    static GameObject bestCarInGeneration = null;

    static public Weights winnerWeights;

    public bool reverseAllowed = false;
    void Start()
    {
        LoadWorkspace.loadWorkspace();
        generation = 1;

        GameObject car = GameObject.Find("Car");
        Vector3 carPosition = car.transform.position;
        Quaternion carRotation = car.transform.rotation;

        Destroy(car);


        for(int i = 0; i < populationSize; i++) {
            GameObject carClone = Instantiate(TrackEditorManager.Instance.CarPrefab, carPosition, carRotation, this.transform);
            carClone.name = "Car(" + i + ")";
            cars.Add(new Car(carClone, i, reverseAllowed));
            cars[i].carController.resetCar(TrackEditorManager.Instance.startPosition, Quaternion.Euler(0, TrackEditorManager.Instance.carRotationAngle, 0));
        }


        BirdEyeCamera birdEyeCamera = GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>();
        birdEyeCamera.targetCar = GameObject.Find("Car(" + 0 + ")"); //todo - fix this


    }

    // Update is called once per frame
    void FixedUpdate()
    {
        checkKeyboardInput();


        foreach(var car in cars) {
            if(!car.carController.justKilled) {
                car.move();
                if (car.lifetime>maxDuration)
                {
                    car.carController.killCar();
                }
            }
            else
            {
                car.carSensorController.onlyShowSensors(new string[] { }) ;
            }
        }


        if(allCarsAreDead()) {
            Debug.Log("Genocide! Hurray!");
            //find best car in population
            //put best car into next gen straight away
            Car bestCarInGeneration = cars[0];
            foreach(Car car in cars) {
                if(car.score > bestCarInGeneration.score)
                    bestCarInGeneration = car;
            }
            Debug.Log("best score: " + bestCarInGeneration.score);
            List<Car> nextGenerationCars = new List<Car>();
            nextGenerationCars.Add(bestCarInGeneration.cloneCar(carPrefab));

            //get total fitness
            int totalFitness = 0;
            foreach(Car car in cars) {
                totalFitness += car.score;
            }
           

            //see if random value is between lowerbound and upperbound
            for(int i = 1; i < populationSize; i++) {
                //get value in range of 0 to sizeOfFitness
                int randomValue = new System.Random().Next(totalFitness);

                int upperBound = 0; //initial upperbound
                foreach(Car car in cars) {
                    upperBound += car.score;
                    if(randomValue <= upperBound) {
                        nextGenerationCars.Add(car.cloneCar(carPrefab));
                        break;
                    }
                }
            }

            //now next 
            //delete all current gen cars and replace with next gen cars


            //next gen using carHit. put him in the next gen as well.
            generation++;
            Debug.Log("Starting generation" + generation);

          
            ////reinstantiate generation winner
            //if(bestCarInGeneration != null) {
            //    foreach(Car car in cars) {
            //        if(car.carGameObject == bestCarInGeneration) {
            //            winnerWeights = Util.cloneWeights(car.brain.weights);
            //            break;
            //        }
            //    }

            //    winnerOfGeneration = Instantiate(winnerOfGeneration, this.transform); //By this point winner of generation will not be null
            //} else {
            //    Debug.Log("your boy is null");
            //}



            //repopulate cars list with the carHit object
            for(int i = 0; i < cars.Count; i++) {
                Destroy(cars[i].carGameObject);
            }
            cars.Clear();
            //Debug.Break();
            cars = nextGenerationCars;


            //winnerOfGeneration.name = "Car(" + 0 + ")";
            //cars.Add(new Car(winnerOfGeneration, 0));


            for(int i = 0; i < populationSize; i++) {
                //cars[i].carGameObject = Instantiate(cars[i].carGameObject);
                cars[i].carGameObject.name = "Car(" + i + ")";
                cars[i].id = i;
                //cars[i].carGameObject.GetComponent<MyCarController>().enabled = true;
                //cars[i].carGameObject.GetComponent<SensorController>().enabled = true;
                cars[i].carController.resetCar(TrackEditorManager.Instance.startPosition, Quaternion.Euler(0, TrackEditorManager.Instance.carRotationAngle, 0));
                
                cars[i].carSensorController.resetSensors();

                //cars[i].brain.randomiseWeights();
                //cars[i].brain.weights = Util.cloneWeights(winnerWeights);

                if(i > 0)
                    cars[i].brain.mutate();
            }



            GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>().targetCar = cars[0].carGameObject;
        }

    }

    bool allCarsAreDead() {
        foreach(Car car in cars) {
            if(!car.carController.justKilled)
                return false;
        }
        return true;
    }

    void checkKeyboardInput() {
        if(Input.GetKeyDown(KeyCode.Equals)) {
            if(Time.timeScale >= 32) {
                return;
            }
            Time.timeScale *= 2f;
            Debug.Log("Time scale = " + Time.timeScale);

        }
        if(Input.GetKeyDown(KeyCode.Minus)) {
            if(Time.timeScale < 0.1) {
                return;
            }
            Time.timeScale = 1f;
            Debug.Log("Time scale = " + Time.timeScale);

        }
    }

}
