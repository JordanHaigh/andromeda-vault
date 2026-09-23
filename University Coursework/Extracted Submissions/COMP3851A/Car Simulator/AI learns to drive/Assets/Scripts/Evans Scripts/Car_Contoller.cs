using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class Car_Controller : MonoBehaviour
{
    public float topSpeed = 30;
    public float acceleration = 5;

    public float turningAngle = 40;
    public bool controlledByPlayer = true;
    public float frictionMultiplier = 0.98f;


    private float motorState = 0; // 1:forward, 0: no acceleration, -1:backwards
    private float turnState = 0;
    private float velocity = 0;
    // Start is called before the first frame update
    void Start()
    {
        //addCylinderLine(0);
        //addCylinderLine(45);
        //addCylinderLine(-45);


    }


    private void addCylinderLine(float angle)
    {
        float length = 100;

        float xMidpoint = length / 2f * Mathf.Cos(angle) * 1.2f;
        float yMidpoint = length / 2f * Mathf.Sin(angle) * 0.84f;
        Debug.Log(angle + ",  " + xMidpoint + ", " + yMidpoint);



        GameObject cylinder = GameObject.CreatePrimitive(PrimitiveType.Cylinder);
        cylinder.transform.SetParent(this.transform);
        cylinder.transform.localPosition = new Vector3(yMidpoint, 0, xMidpoint);
        cylinder.transform.Rotate(90f, angle, 0);
        cylinder.transform.localScale = new Vector3(0.1f, length / 2f, 0.1f);
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        if (controlledByPlayer)
        {
            turnState = Input.GetAxis("Horizontal");
            motorState = Input.GetAxis("Vertical");
        }

        applyRotation();
        applyVelAndAcc();



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
}
