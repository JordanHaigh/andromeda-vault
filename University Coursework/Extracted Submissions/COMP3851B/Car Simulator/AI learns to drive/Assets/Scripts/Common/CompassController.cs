using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CompassController : MonoBehaviour
{

    public GameObject Line;
    public GameObject carObject;
    // Start is called before the first frame update


    private float carWidth;
    private float carLength;
    private float carHeight;
    private float carAngle;

    private Vector3 target;
    private Vector2 currentPosition ;

    void Start()
    {
        target = new Vector3(-60, 0, 0);
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        //create a ray cast and set it to the mouses cursor position in game
        //Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);
        //RaycastHit hit;
        //if (Physics.Raycast(ray, out hit))
        //{
        //    target = hit.point;

        //}
        //Vector3 endPoint = new Vector3(0, 0, Vector3.Distance(this.transform.position,target));
        Vector3 endPoint = new Vector3(0, 0, 10);
        Quaternion rotation = Quaternion.Euler(0,this.transform.rotation.eulerAngles.y + getRoundedCompassAngle(), 0);        
        endPoint = rotation * endPoint;
  
        endPoint = endPoint + this.transform.position;       
        //Debug.DrawLine(this.transform.position + new Vector3(0,1,0), endPoint + new Vector3(0, 1, 0));


    }

    //returns the unit vector from the car in the direction of the compass 
    public Vector3 getCompassVectorWorldSpace()
    {
        Vector3 direction = new Vector3(0, 0, 1);
        Quaternion rotation = Quaternion.Euler(0, this.transform.rotation.eulerAngles.y + getRoundedCompassAngle(), 0);
        direction = rotation * direction;
        return direction;
    }


    //returns the unit vector from the car in the direction of the compass relative to the car
    public Vector3 getCompassVectorRelToCar()
    {
        Vector3 direction = new Vector3(0, 0, 1);
        Quaternion rotation = Quaternion.Euler(0, getRoundedCompassAngle(), 0);
        direction = rotation * direction;
        return direction;
    }

    //returns a value between -1 and 1 representing how well the car is following the compass
    public float getComponentOfCarsVelocityFollowingCompass()
    {
        Vector3 compassDirection = getCompassVectorWorldSpace();

        Vector3 forward = new Vector3(0, 0, 1);
        Quaternion rotation = this.transform.rotation;

        Vector3 carDirection = rotation * forward;


        //should return a value between -1 and 1 
        return Vector3.Dot(carDirection, compassDirection);

    }

    public float getCompassAngle()
    {
        //get the vector from the car to the target
        Vector3 fromTo = new Vector3(target.x - this.transform.position.x, target.y - this.transform.position.y, target.z - this.transform.position.z);

        //get a vector representing the angle of the car
        Vector3 forward = new Vector3(0, 0, 1);
        Quaternion rotation = this.transform.rotation;
        forward = rotation * forward;


        //get the angle
        float relativeAngle = Vector3.SignedAngle(forward, fromTo ,new Vector3(0,1,0));

        if (relativeAngle < 0)
        {
            relativeAngle += 360;
        }

        return relativeAngle;
    }

    public int getRoundedCompassAngle()
    {

        int roundedValue =  (int)((getCompassAngle()+22.5)/45)*45;//logic to round it to the nearest 45
        if(roundedValue == 360)
        {
            roundedValue = 0;
        }
        return roundedValue;
    }

    public Vector3 getTarget()
    {
        return target;
    }

    public void setTarget(Vector3 newTarget)
    {
        target = newTarget;
    }

    public void setTarget(float x,float y, float z)
    {
        target = new Vector2(x, y);
    }

    private void updateCurrentPos()
    {


    }
}
