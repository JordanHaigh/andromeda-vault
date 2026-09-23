using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Runtime.InteropServices;
using System.Text;
using System;
using TMPro;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

class UnityEventSystem : MonoBehaviour {

    public static bool isPointerOverGameObject() {
        return EventSystem.current.IsPointerOverGameObject(); 
    }

}
