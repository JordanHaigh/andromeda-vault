using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class MyCarController : MonoBehaviour
{
    public float topSpeed = 30;
    public float acceleration = 5;

    public float turningAngle = 40;
    public bool controlledByPlayer = true;
    public float frictionMultiplier = 0.98f;


    public float motorState = 0; // 1:forward, 0: no acceleration, -1:backwards
    public float turnState = 0;
    private float velocity = 0;



    private bool justDied = false;

    private Vector3 startingPosition;
    private float startingRotation;


    //[System.NonSerialized]
    public bool justKilled = false;
    [System.NonSerialized]
    public int moveCounter = 0;
    [System.NonSerialized]
    public int movesBeforeThinking = 5;

    //sorry evan, we had to put it here.
    public Vector3 lastCheckpoint = Vector3.zero;
    public bool isQLearning = false;
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
        if (controlledByPlayer)
        {
            turnState = Input.GetAxis("Horizontal");
            motorState = Input.GetAxis("Vertical");
            moveCar();
        }
        else {
            moveCar();
        }
    }


    public void moveCar()
    {
        applyRotation();
        applyVelAndAcc();
        moveCounter++;

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
        motorState = -1;
    }

    public void accelerate()
    {
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
        if(collision.gameObject.tag.Contains("Wall"))
        {
            if (isQLearning)
            {
                Debug.Log("oof1");
                killAndResetCar();
            }
            else
            {
                killCar();
            }         
        }
       
    }

    void OnTriggerEnter(Collider collider)
    {
        //use this one for wall collision
        if(collider.gameObject.tag.Contains("Wall"))
        {
            if (isQLearning)
            {

                killAndResetCar();
            }
            else
            {
                killCar();
            }
        }
    }

    public void killCar()
    {
        //Debug.Log("oof");
        justKilled = true;
        velocity = 0;
        dontAccelerate();
        dontTurn();
       
        //resetCar(); //todo evan you might be using this
    }

    public void killAndResetCar()
    {
        justKilled = true;
        velocity = 0;
        dontAccelerate();
        dontTurn();
 
        resetCar(); //todo evan you might be using this
    }

    public void resetCar()
    {
        transform.position = startingPosition;
        transform.rotation = Quaternion.Euler(0, startingRotation, 0);
        motorState = 0;
        turnState = 0;
        velocity = 0;
        moveCounter = 0;

    }
    public void resetCar(Vector3 position, Quaternion rotation) {
        transform.position = position;
        transform.rotation = rotation;
        motorState = 0;
        turnState = 0;
        velocity = 0;
        moveCounter = 0;
        justKilled = false;
    }

    public bool goingForward()
    {
        return motorState != -1;
    }

}
