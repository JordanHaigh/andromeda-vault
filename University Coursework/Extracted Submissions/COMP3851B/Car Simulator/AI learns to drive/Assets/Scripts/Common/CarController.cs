using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;



public class CarController : MonoBehaviour
{
    public float topSpeed = 30;
    public float acceleration = 5;

    public float turningAngle = 40;
    public bool controlledByPlayer = true;
    public float frictionMultiplier = 0.98f;


    public float motorState = 0; // 1:forward, 0: no acceleration, -1:backwards
    public float turnState = 0;


    private float velocity = 0;
    private List<Vector3> previousPositions = new List<Vector3>();
    private List<float> previousRotations = new List<float>();


    private bool justDied = false;


    private Vector3 startingPosition;
    private float startingRotation;

    private int switchCount = 0;//counts the number of times the car switches direction in a row, aims to prevents cars from looking at
    //the node goal and continually reverse and then accelerate


    [System.NonSerialized]
    public bool justKilled = false;
    [System.NonSerialized]
    public bool justKilledForPathFinding = false;
    [System.NonSerialized]
    public int moveCounter = 0;
    [System.NonSerialized]
    public int movesBeforeThinking = 5;

    public Vector3 lastCheckpoint = Vector3.zero;
    public bool isQLearning = false;
    private bool sendToCar = true;

 
    // Start is called before the first frame update
    void Start()
    {


        lastCheckpoint = Vector3.zero;


        //addCylinderLine(0);
        //addCylinderLine(45);
        //addCylinderLine(-45);
        startingPosition = transform.position;
        startingRotation = transform.rotation.eulerAngles.y;

        
    }

   public void setStartingPositionAndRotation(Vector3 position, float yRotation )
    {


        Quaternion rotation = Quaternion.Euler(0, yRotation, 0);
        transform.position = position;
        transform.rotation = rotation;


        startingPosition = position;
        startingRotation = yRotation;



    }


    // Update is called once per frame
    void FixedUpdate()
    {
        addPositionAndRotationHistory();
  
        if (controlledByPlayer)
        {
            turnState = Input.GetAxis("Horizontal");
            motorState = Input.GetAxis("Vertical");
            moveCar();
        }
        else {
            moveCar();

            if (switchCount > 10)
            {
                if (isQLearning && SceneManager.GetActiveScene().name.Equals("Q Learning"))
                {

                    killCarAndRewindTime();
                }
            }
        }

        
    }
    private void addPositionAndRotationHistory()
    {
        int amountOfHistoryStored = 300;
        previousPositions.Add(this.transform.position);
        previousRotations.Add(this.transform.rotation.eulerAngles.y);
        


        if(previousPositions.Count > amountOfHistoryStored)
        {
            //if the current position and rotation is close to the current position and rotation of the car then KILL THE FUCKIN CAR
            if (SceneManager.GetActiveScene().name.Equals("Q Learning") && closeToPreviousPosition(amountOfHistoryStored))
            {

                killCarAndRewindTime();
                return;
            }

            previousPositions.RemoveAt(0);
            previousRotations.RemoveAt(0);
        }
    }

    public bool closeToPreviousPosition(int amountOfHistoryStored)
    {
        float minDistanceDifference = 5;
        float minRotationDifference = 10;
        if(previousPositions.Count < amountOfHistoryStored)
        {
            return false;
        }
        if (Vector3.Distance(previousPositions[0], this.transform.position) < minDistanceDifference) {

            float rotationDifference = Mathf.Abs( this.transform.rotation.eulerAngles.y - previousRotations[0]);

            //if the difference goes around the 360 -> 0 rotation
            if (rotationDifference > 180)
            {
                rotationDifference = 360 - rotationDifference;
            }

            if(rotationDifference < minRotationDifference)
            {

                return true;
               
            }

        }



        return false;
    }

    public bool goingForward() {
        return motorState != -1;
    }

    public void moveCar()
    {

        applyRotation();
        applyVelAndAcc();
        moveCounter++;

    }

    public void setMotorState(float motorState) {
        this.motorState = motorState;
    }

    private void applyVelAndAcc()
    {
        velocity += acceleration * motorState;

        velocity *= frictionMultiplier;
        if (velocity > topSpeed)
        {
            velocity = topSpeed;
        }
        if (velocity < -topSpeed)
        {
            velocity = -topSpeed;
        }
        Vector3 forwardVector = this.transform.forward;
        this.transform.position += forwardVector * velocity;
    }


    private void applyRotation()
    {
        float speedRatio = velocity / topSpeed;
        this.transform.Rotate(0, turnState * turningAngle * speedRatio, 0);
    }

    public void reverse()
    {
        if (motorState == 1)
        {
            switchCount++;
        }
        else
        {
            switchCount = 0;
        }
        motorState = -1;
    }

    public void accelerate()
    {
        if(motorState == -1)
        {
            switchCount++;
        }
        else
        {
            switchCount = 0;
        }
        motorState = 1;
    }
    public void dontAccelerate()
    {
        motorState = 0;
    }

    public void turnLeft()
    {
        turnState = -1;
    }

    public void turnRight()
    {
        turnState = 1;
    }

    public void dontTurn()
    {
        turnState = 0;
    }

    void OnCollisionEnter(Collision collision)
    {
        if(collision.gameObject.tag.Contains("Wall-Section"))
        {
            if(isQLearning) {
                Debug.Log("oof1");
                killCarAndRewindTime();
            } else if(SceneManager.GetActiveScene().name.Equals("Neural Network")) {
                killCar();
            } else {
                //Either manual control, slammin or basic ai (but basic ai shouldnt be able to hit walls)
                dontAccelerate();
                dontTurn();
                velocity = 0;
            }         
        }
       
    }

    void OnTriggerEnter(Collider collider)
    {
        //use this one for wall collision
        if(collider.gameObject.tag.Contains("Wall-Section"))
        {
            if (isQLearning)
            {

                killCarAndRewindTime();
            } else if(SceneManager.GetActiveScene().name.Equals("Neural Network")) {
                killCar();
            } else {
                //Either manual control, slammin or basic ai (but basic ai shouldnt be able to hit walls)
                dontAccelerate();
                dontTurn();
                velocity = 0;
            }
        }
    }

    public void killCar()
    {
        Debug.Log("oof");
        justKilled = true;
        justKilledForPathFinding = true;
        velocity = 0;
        dontAccelerate();
        dontTurn();
       
    }

    public void killCarAndRewindTime()
    {
        justKilled = true;
        justKilledForPathFinding = true;
        velocity = 0;
        dontAccelerate();
        dontTurn();

        rewindTime();
    }

    public void rewindTime()
    {
        Debug.Log("REwind");
        int rewindAmount = 5;
        if (previousPositions.Count == 0)
        {
            resetCar();
            return;
        }

        int rewindToPoint = Mathf.Max(0, previousPositions.Count - rewindAmount);
        
        transform.position = previousPositions[rewindToPoint];
        transform.rotation = Quaternion.Euler(0, previousRotations[rewindToPoint], 0);

        for (int i = previousPositions.Count - 1; i > rewindToPoint; i--)
        {
            previousPositions.RemoveAt(i);
            previousRotations.RemoveAt(i);
        }

        //previousPositions = new List<Vector3>();
        //previousRotations = new List<float>();

        motorState = 0;
        turnState = 0;
        velocity = 0;
        moveCounter = 0;
        switchCount = 0;

      
    }
    public void killAndResetCar()
    {

        justKilled = true;
        justKilledForPathFinding = true;
        velocity = 0;
        dontAccelerate();
        dontTurn();
        resetCar(); 
    }

    public void resetCar()
    {

        transform.position = startingPosition;
        transform.rotation = Quaternion.Euler(0, startingRotation, 0);
        motorState = 0;
        turnState = 0;
        velocity = 0;
        switchCount = 0;
        moveCounter = 0;

    }

    //this ones used for Neural Network stuff
    public void resetCar(Vector3 position, Quaternion rotation) {
        transform.position = position;
        transform.rotation = rotation;
        motorState = 0;
        turnState = 0;
        velocity = 0;
        moveCounter = 0;
     
        justKilled = false;
    }

}
