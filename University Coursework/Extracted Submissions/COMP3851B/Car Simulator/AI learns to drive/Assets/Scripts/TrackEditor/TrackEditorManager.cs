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

public class TrackEditorManager : MonoBehaviour
{
   
    public TrackEditor trackEditor;
    void Start() {
        trackEditor = new TrackEditor(this.transform);
    }

    void Update() {
        if(Input.GetMouseButtonDown(0)) {
            trackEditor.leftMouseCommands();
        } else if(Input.GetMouseButtonDown(1)) {
            trackEditor.completePolygon();
        } else if(Input.GetKeyDown(KeyCode.Z)) {
            trackEditor.undo();
        } else if(Input.GetKeyDown(KeyCode.R)) {
            trackEditor.rotateCar(45);
        } else if(Input.GetKeyDown(KeyCode.E)) {
            trackEditor.rotateCar(-45);
        } else if(Input.GetKeyDown(KeyCode.Tab)) {
            trackEditor.changeMode();
        }

        if(trackEditor.isDrawingPolygon()) {
            if(GameObject.Find("TempWall") != null) {
                Destroy(GameObject.Find("TempWall"));
            }
            trackEditor.createTempWall();
        }
    }




    public void UI_resetWorkspace() {
        //I hate unity.
        trackEditor.resetWorkspace();
    }

    public void UI_loadWorkspace() {
        //Did I say how much I hate Unity yet?
        trackEditor.loadWorkspace();
    }

    public void UI_saveWorkspace() {
        //Did I say how much I hate Unity yet?
        trackEditor.saveWorkspace();
    }
    public void UI_undo() {
        //Did I say how much I hate Unity yet?
        trackEditor.undo();
    }













}
