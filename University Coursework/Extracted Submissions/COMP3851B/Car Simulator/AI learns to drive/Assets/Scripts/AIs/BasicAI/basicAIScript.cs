using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class basicAIScript : MonoBehaviour
{
    public GameObject car;
    public float emergencyTurnDistance;
    public float emergencyStopDistance;
    //public float autoDriveTurnThreshold = 1;
    public float leftAndRightDifferenceTurnThreshold = 1;
    public float miniTurnGradientThreshold = 1f;
    //public float intersectionDifferenceMinimum = 5;
    public float intersectionDistanceMinimum = 5;
    public float corridorGradientMaximum = 0.2f;

    private int miniTurnGradientWaiter = 0;
    private int miniTurnMaxWait = 3;

    public SensorController carSensorController { get; set; }
    public CarController carController { get; set; }

    // Start is called before the first frame update


    private bool autoDriving = true;
    private bool waitingForServer = false;
    private bool followingInstructions = false;
    private bool turningLeft = false;
    private bool turningRight = false;


    private float previousLeft = -1;
    private float previousRight = -1;
    private float previousTrueLeft = -1;
    private float previousTrueRight = -1;
    private float previousForward = -1;
    private float previousBackward = -1;

    private float left = -1;
    private float right = -1;
    private float trueLeft = -1;
    private float trueRight = -1;
    private float forward = -1;
    private float backward = -1;

    private float currentRotation = 0;
    private float idealRotation =0 ;

    private enum stateNames {continueStraight, turningLeft, turningRight, continueStraightUntilCorridorFound }
    public int stateNo = 0;


    public void Start()
    {
        car = GameObject.Find("Car");
        carSensorController = car.GetComponent<SensorController>();
        carController = car.GetComponent<CarController>();

        carController.controlledByPlayer = false;


    }



    // Update is called once per frame
    void FixedUpdate()
    {

        forward = carSensorController.getDistanceFromSensorByName("FORWARD");
        backward = carSensorController.getDistanceFromSensorByName("BACKWARD");
        left = carSensorController.getDistanceFromSensorByName("LEFT");
        right = carSensorController.getDistanceFromSensorByName("RIGHT");
        trueLeft = carSensorController.getDistanceFromSensorByName("TRUE_LEFT");
        trueRight = carSensorController.getDistanceFromSensorByName("TRUE_RIGHT");


        currentRotation = car.transform.rotation.eulerAngles.y;


        //functionality handled within checkForEmergeny
        if (checkForEmeregency())
        {
            return;
        }


        switch (stateNo)
        {
            case (int)stateNames.continueStraight:
                updateOnContinueStraight();
                break;

            case (int)stateNames.turningLeft:
                updateOnTurningLeft();
                break;

            case (int)stateNames.turningRight:
                updateOnTurningRight();
                break;

            case (int)stateNames.continueStraightUntilCorridorFound:

                updateOnContinueStraightUntilCorridorFound();
                break;
        }

        setPreviousValues();
    }


    private bool checkForEmeregency()
    {

        if(forward< emergencyStopDistance) {
            //Debug.Log("reverse mode needs to be done");
            carController.reverse();
            carController.dontTurn();
            return true;
        }
        if(left< emergencyTurnDistance && right < emergencyTurnDistance)
        {
            //Debug.Log("oh fuck they be some close walls");
            carController.reverse();
            carController.dontTurn();
            return true;
        }



        if(left < emergencyTurnDistance)
        {
            carController.turnRight();
            return false;
        }
        if (right < emergencyTurnDistance)
        {
            carController.turnLeft();
            return false;
        }

        return false;
    }
    /*
    in this state the car will continue to drive straight (keeping equidistant from the left and right walls) until it reaches an intersection
    An intersection is reached if the difference between the previous reading from the left or right sensors is greater than a threshold


 */
    private void updateOnContinueStraight()
    {

        miniTurnGradientWaiter ++;

        if (previousLeft == -1)
        {
            setPreviousValues();
        }


        if (trueLeft + trueRight + left + right < 4 * 30 - 1)//if not in a wide open space
        {

            // LOOKING FOR INTERSECTIONS
            if (trueLeft > intersectionDistanceMinimum)
            {
                Debug.Log("Left intersection found: current left: " + left);
                stateNo = (int)stateNames.turningLeft;
                intersectionFound();
                return;
            }
            else if (trueRight > intersectionDistanceMinimum)
            {
                Debug.Log("Right intersection foound: current right: " + trueRight);
                stateNo = (int)stateNames.turningRight;
                intersectionFound();
                return;
            }

        }



        /* old logic
        if (left - previousLeft > intersectionDifferenceMinimum)
        {
            Debug.Log("Left intersection found: current left: " + left + " previous left: " + previousLeft);
            stateNo = (int)stateNames.turningLeft;
            intersectionFound();
            
            return;
        }

        if (right - previousRight > intersectionDifferenceMinimum)
        {
            Debug.Log("Right intersection foound: current right: " + right + " previous Right: " + previousRight);
            stateNo = (int)stateNames.turningRight;
            intersectionFound();
            return;
        }
        */

        // NO INTERSECTIONS FOUND



        //ok so now we just need to keep going srtraight and not die
        carController.accelerate();
        carController.dontTurn();
        



        //first things first if we get too close to shit then turn
        if (left < emergencyTurnDistance || trueLeft < emergencyTurnDistance)
        {
            Debug.Log("--------------------------------------");
            Debug.Log("Emergency turn Right");
            Debug.Log("true right " + trueRight);
            Debug.Log("true left " + trueLeft);
            carController.turnRight();
            return;
        }
        if (right < emergencyTurnDistance || trueRight < emergencyTurnDistance)
        {
            Debug.Log("--------------------------------------");
            Debug.Log("Emergency turn left");
            Debug.Log("true right " + trueRight);
            Debug.Log("true left " + trueLeft);
            carController.turnLeft();
            return;
        }



        //keep equidistant to both walls.
        //if (Mathf.Abs(left - right) > autoDriveTurnThreshold)
        //{

        //    if (left < right)
        //    {        
        //        carController.turnRight();
        //    }
        //    else if (right < left)
        //    {
        //        carController.turnLeft();
        //    }
        //}

        if (Mathf.Abs(trueLeft - trueRight) > leftAndRightDifferenceTurnThreshold)
        {

            if (trueLeft < trueRight)
            {
                Debug.Log("--------------------------------------");
                Debug.Log("difference turn Right");
                Debug.Log("true right " + trueRight);
                Debug.Log("true left " + trueLeft);
                carController.turnRight();
            }
            else if (trueRight <= trueLeft)
            {
                Debug.Log("--------------------------------------");
                Debug.Log("difference turn Left");
                Debug.Log("true right " + trueRight);
                Debug.Log("true left " + trueLeft);
                carController.turnLeft();
                
            }
            miniTurnGradientWaiter = 0;
        }
        else            //if you are resonably equidistant from both walls then attempt to angle the car to move straight 
        {



            Debug.Log("Gradient testing");

            //get the maximum gradient
            float maxGraident = Mathf.Max(previousTrueLeft - trueLeft , previousTrueRight - trueRight);
            if (miniTurnGradientWaiter < miniTurnMaxWait)// && maxGraident < 2 * miniTurnGradientThreshold)
            {
                Debug.Log("wait");
                return;
            }

            miniTurnGradientWaiter = 0;

            if(previousTrueLeft - trueLeft > previousTrueRight - trueRight)
            {
                Debug.Log(previousTrueLeft - trueLeft);
                if(previousTrueLeft - trueLeft > miniTurnGradientThreshold)// if we are approaching the left wall then turn right
                {
                    Debug.Log("--------------------------------------");
                    Debug.Log("graident turn Right");
                    Debug.Log("true right " + trueRight);
                    Debug.Log("true left " + trueLeft);
                    carController.turnRight();
                }
            }
            else
            {
                Debug.Log(previousTrueRight - trueRight);
                if (previousTrueRight - trueRight > miniTurnGradientThreshold)//if we are approaching the right wall then turn left
                {
                    Debug.Log("--------------------------------------");
                    Debug.Log("gradient turn left");
                    Debug.Log("true right " + trueRight);
                    Debug.Log("true left " + trueLeft);
                    carController.turnLeft();
                }
            }



        }
        //else
        //{

        //    //if you are resonably equidistant from both walls then attempt to angle the car to move straight 
        //    if (previousLeft - left > previousRight - right)
        //    {
        //        if (previousLeft - left > miniTurnGradientThreshold)// if we are approaching the left wall then turn right
        //        {
        //            carController.turnRight();
        //        }
        //    }
        //    else
        //    {
        //        if (previousRight - right > miniTurnGradientThreshold)//if we are approaching the right wall then turn left
        //        {
        //            carController.turnLeft();
        //        }
        //    }
        //}


    }


    

    private void setPreviousValues()
    {
        previousLeft = left;
        previousRight = right;
        previousForward = forward;
        previousBackward = backward;
        previousTrueLeft = trueLeft;
        previousTrueRight = trueRight;
    }

    private void intersectionFound()
    {
        Debug.Break();
        if (stateNo == (int)stateNames.turningLeft)
        {
            
            carController.turnLeft();
            idealRotation = currentRotation - 90;

        }
        else
        {
            carController.turnRight();
            idealRotation = currentRotation + 90;
        }

        if (idealRotation > 360)
        {
            idealRotation -= 360;
        }
        else if (idealRotation < 0)
        {
            idealRotation += 360;
        }

        Debug.Log("current rotion :" + currentRotation + " target rotation: " + idealRotation);
    }


    private void updateOnTurningRight()
    {
        carController.accelerate();

        carController.turnRight();

        //if the car has rotated passed the ideal rotation then stop rotating 
        // note the difference has to be less that 180 to prevent issues with the 360 -> 0 wrap
        if (currentRotation >= idealRotation && currentRotation - idealRotation < 180)
        {
            stateNo = (int)stateNames.continueStraightUntilCorridorFound;
        }

    }

    private void updateOnTurningLeft()
    {

        // it probably needs to take the current angle of the car on creation of this state and subtract 90 degrees to
        // determine the angle it should finish at
        /*
         other thoughts
            could turn for about 45 degrees 
            could train a qlearning agent to turn left/right 
            could keep turning until forward is descreasing
            could wait until forward is clear 
         */
        carController.accelerate();
        if (currentRotation <= idealRotation && idealRotation - currentRotation  < 180)
        {
            stateNo = (int)stateNames.continueStraightUntilCorridorFound;
        }

        carController.turnLeft();
    }
    private void updateOnContinueStraightUntilCorridorFound()
    {

        // keep a constant distance to the closest wall until the gradient of the walls are about the same
        Debug.Log("update on conintioneunbaldkjfnasuiokas hbn");

        carController.accelerate();
        carController.dontTurn();

        float leftChange = previousTrueLeft - trueLeft;
        float rightChange = previousTrueRight - trueRight;
        //check if we are in a corridor
        //a corridor has low left and right change

        if(inCorridor())//if we found a corridor then we good
        {


            stateNo = (int)stateNames.continueStraight;
            return;
             

        }

        //keep parallel to closest wall.EDIT keep parallel to the wall with the smallest change in distance

        if (true || Mathf.Min(Mathf.Abs(leftChange), Mathf.Abs(rightChange)) > corridorGradientMaximum)
        {

            if (leftChange < rightChange)
            {


                if (leftChange > 0)// if we are approaching the left wall then turn right
                {
                    carController.turnRight();
                }
                else//if we are getting further away from the left wall then turn left
                {
                    carController.turnLeft();
                }

            }
            else//follow right wall
            {
                if (rightChange > 0)// if we are approaching the right wall then turn left
                {
                    carController.turnLeft();
                }
                else//if we are getting further away from the right wall then turn right
                {
                    carController.turnRight();
                }
            }
        }
    }

    public bool inCorridor()
    {
        float leftChange = previousTrueLeft - trueLeft;
        float rightChange = previousTrueRight - trueRight;

        if (Mathf.Max(trueLeft, trueRight) > intersectionDistanceMinimum)
        {

            return false;
        }


        if(Mathf.Max(Mathf.Abs(leftChange), Mathf.Abs(rightChange)) > corridorGradientMaximum)
        {

          
            return false;
        }

        return true;

    }
}
