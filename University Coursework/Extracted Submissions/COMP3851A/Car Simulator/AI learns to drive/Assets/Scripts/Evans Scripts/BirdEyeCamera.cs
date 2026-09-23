using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class BirdEyeCamera : MonoBehaviour
{

    public GameObject targetCar;
    public float maxDistanceFromCam;
    private float panSpeed = 0.005f;
    public float zoomSpeed = 100;

    private bool locked = false;
    private bool dragging = false;
    private Vector2 draggedFrom;
    

    // Start is called before the first frame update
    void Start()
    {

    }

    // Update is called once per frame
    void FixedUpdate()
    {
        
        var distance = Distance(transform.position.z, transform.position.x, targetCar.transform.position.z, targetCar.transform.position.x);
        if (locked && distance > maxDistanceFromCam)
        {
            Vector3 direction = targetCar.transform.position - transform.position;
            direction.y = 0;

            float needToMove = direction.magnitude - maxDistanceFromCam;
            Vector3 directionNormalized = direction.normalized;
            transform.transform.position += directionNormalized * needToMove;


        }

        if (dragging)
        {
            float xMovement = draggedFrom.x -Input.mousePosition.x;
            float zMovement = draggedFrom.y - Input.mousePosition.y;
            draggedFrom.x -= xMovement;
            draggedFrom.y -= zMovement;

            locked = false;
            Vector3 direction = new Vector3(xMovement, 0, zMovement);

            float yPanIncrease = transform.position.y * 1/4f;

            transform.transform.position += direction * panSpeed * yPanIncrease;
        }


        float yMovement = Input.GetAxis("Mouse ScrollWheel");
        Vector3 upVector = new Vector3(0, yMovement, 0);
        transform.transform.position += upVector * -zoomSpeed;


        //if (Input.GetMouseButtonDown(2))
        //{
        //    OnMouseDown();
        //}
        //if (Input.GetMouseButtonUp(2))
        //{
        //    OnMouseUp();
        //}
        if (!Input.GetMouseButton(2)) {
            OnMouseUp();
        }
        else
        {
            if (!dragging)
            {
                OnMouseDown();
            }
        }


        if (Input.GetKeyDown(KeyCode.L))
        {
            locked = !locked;
        }

        Scene currentScene = SceneManager.GetActiveScene(); //todo resolve this - track editor cant have the mouse clicks moving the camera
        if(currentScene.name.Equals("Track Editor")) {
            if(Input.GetKey(KeyCode.UpArrow)) {
                moveCamera(0, 0, 1);
            } else if(Input.GetKey(KeyCode.DownArrow)) {
                moveCamera(0, 0, -1);
            } else if(Input.GetKey(KeyCode.LeftArrow)) {
                moveCamera(-1, 0, 0);
            } else if(Input.GetKey(KeyCode.RightArrow)) {
                moveCamera(1, 0, 0);
            } 
        }
    }

    public void moveCamera(int x, int y, int z) {
        GameObject camera = GameObject.Find("Main Camera");
        Vector3 addingVector = new Vector3(x, y, z);

        Vector3 addedVectors = camera.transform.position + addingVector;
        if(addedVectors.y < 1 || addedVectors.y > 200) //in case user goes too high or too low
            return;

        camera.transform.position += addingVector;
    }

    public void OnMouseDown()
    {
        float x = Input.mousePosition.x;
        float y = Input.mousePosition.y;
        
        if (x<Screen.width && x>0 && y<Screen.height && y>0)//if mouse is on screen
        {
            print(x, y);

            dragging = true;
            draggedFrom = Input.mousePosition;
        }
 

    }
    public void OnMouseUp()
    {
        dragging = false;
    }
    public float Distance(float x1, float y1, float x2, float y2)
    {
        return Mathf.Sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public void print(params Object[] objs)
    {
        string printString = "";
        for(int i = 0; i < objs.Length; i++)
        {
            printString += objs[i].ToString();
            if (i != objs.Length - 1)
            {
                printString += ", "; 
            }
        }

        Debug.Log(printString);

    }
    public void print(params double[] objs)
    {
        string printString = "";
        for (int i = 0; i < objs.Length; i++)
        {
            printString += objs[i].ToString();
            if (i != objs.Length - 1)
            {
                printString += ", ";
            }
        }

        Debug.Log(printString);

    }
}
