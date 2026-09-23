using System.Collections;
using System.Collections.Generic;
using System;
using UnityEngine;

using Weights = System.Collections.Generic.List<System.Collections.Generic.List<System.Collections.Generic.List<System.Double>>>;


public class NeuralNetworkScript : MonoBehaviour {

    // Start is called before the first frame update
    public int populationSize = 50;
    public float maxDuration = 1000;

    public Track track { get; set; }
    //public GameObject carPrefab { get; set; }
    private List<NNCar> cars = new List<NNCar>();

    public int generation;

    public bool reverseAllowed = false;


    void Start() {
        generation = 1;

        GameObject car = GameObject.Find("Car");
        Vector3 carPosition = car.transform.position;
        Quaternion carRotation = car.transform.rotation;

        for(int i = 0; i < populationSize; i++) {
            GameObject carClone = Instantiate(car, carPosition, carRotation, this.transform);
            carClone.name = "Car(" + i + ")";
            cars.Add(new NNCar(carClone, i, reverseAllowed));
            cars[i].carController.resetCar(track.startPosition, Quaternion.Euler(0, track.carRotationAngle, 0));
            cars[i].carController.enabled = true;
            cars[i].carSensorController.enabled = true;
        }


        BirdEyeCamera birdEyeCamera = GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>();
        birdEyeCamera.targetCar = GameObject.Find("Car(" + 0 + ")"); //set to best of previous gen
        Destroy(car);

    }

    // Update is called once per frame
    void FixedUpdate() {
        checkKeyboardInput();
        moveCars();
        
        if(allCarsAreDead()) {
            NNCar bestCarInGeneration = findBestCar();
            
            //put best car into next gen straight away
            List<NNCar> nextGenerationCars = new List<NNCar>();

            NNCar clonedCar = bestCarInGeneration.cloneCar(bestCarInGeneration.carGameObject);
            nextGenerationCars.Add(clonedCar);

            //get total fitness
            int totalFitness = 0;
            foreach(NNCar car in cars) {
                totalFitness += car.score;
            }

            //populate next generation
            nextGenerationCars = populateNextGeneration(nextGenerationCars, totalFitness);

            //delete all current gen cars and replace with next gen cars
            for(int i = 0; i < cars.Count; i++) {
                Destroy(cars[i].carGameObject);
            }
            cars.Clear();
            cars = nextGenerationCars;

            generation++;
            if(generation % 5 == 0)
                maxDuration += 500;
            Debug.Log("Starting generation" + generation);

            //reset new generation positions, and mutate brains

            for(int i = 0; i < populationSize; i++) {
                cars[i].carGameObject.name = "Car(" + i + ")";
                cars[i].id = i;
                cars[i].carController.resetCar(track.startPosition, Quaternion.Euler(0, track.carRotationAngle, 0));
                cars[i].carSensorController.resetSensors();
                if(i > 0) //the first car in the generation will be the winner from the previous generation
                    cars[i].brain.mutate();
            }

            GameObject.Find("Main Camera").GetComponent<BirdEyeCamera>().targetCar = cars[0].carGameObject;
        }

    }

    void moveCars() {
        foreach(var car in cars) {
            if(!car.carController.justKilled) {
                car.move();
                if(car.lifetime > maxDuration) {
                    car.carController.killCar();
                }
            } else {
                car.carSensorController.onlyShowSensors(new string[] { });
            }
        }
    }

    NNCar findBestCar() {
        Debug.Log("Genocide! Hurray!");
        //find best car in population
        NNCar bestCarInGeneration = cars[0];
        foreach(NNCar car in cars) {
            if(car.score > bestCarInGeneration.score)
                bestCarInGeneration = car;
        }
        Debug.Log("best score: " + bestCarInGeneration.score);
        return bestCarInGeneration;
    }

    private List<NNCar> populateNextGeneration(List<NNCar> nextGeneration, int totalFitness) {
        //see if random value is between lowerbound and upperbound
        for(int i = 1; i < populationSize; i++) {
            //get value in range of 0 to sizeOfFitness
            int randomValue = new System.Random().Next(totalFitness);

            int upperBound = 0; //initial upperbound
            foreach(NNCar car in cars) {
                upperBound += car.score;
                if(randomValue <= upperBound) {
                    nextGeneration.Add(car.cloneCar(car.carGameObject));
                    break;
                }
            }
        }

        return nextGeneration;
    }

    bool allCarsAreDead() {
        foreach(NNCar car in cars) {
            if(!car.carController.justKilled)
                return false;
        }
        return true;
    }


    #region simulationSpeedup
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
    #endregion



}