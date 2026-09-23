using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class ChangeScene : MonoBehaviour {
    // Update is called once per frame
    public void ChangeToScene(int scene) {
        SceneManager.LoadScene(scene);
    }
    public void closeProgram() {
        Application.Quit(); //ignored in editor. will close in actual build (i think)
    }
}
