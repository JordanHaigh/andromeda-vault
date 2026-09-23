using UnityEngine;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading;

public class TCPServer : MonoBehaviour {
    //i'd kiss this man if i could - https://github.com/NumesSanguis/Basic-Unity3D-Python-server
    private TcpListener tcpListener;
    private Thread tcpListenerThread;
    private TcpClient connectedTcpClient;

    int counter = 0;

    // Use this for initialization
    void Start() {
        // Start TcpServer background thread 		
        tcpListenerThread = new Thread(new ThreadStart(ListenForIncommingRequests));
        tcpListenerThread.IsBackground = true;
        tcpListenerThread.Start();
    }

    private void ListenForIncommingRequests() {
        try {
            // Create listener on localhost port 25565		
            tcpListener = new TcpListener(IPAddress.Parse("192.168.1.5"), 25565);
            tcpListener.Start();
            Debug.Log("Server is listening");
            Byte[] bytes = new Byte[1024];
            while(true) {
                using(connectedTcpClient = tcpListener.AcceptTcpClient()) {
                    // Get a stream object for reading 					
                    using(NetworkStream stream = connectedTcpClient.GetStream()) {
                        int length;
                        // Read incomming stream into byte arrary. 						
                        while((length = stream.Read(bytes, 0, bytes.Length)) != 0) {
                            var incommingData = new byte[length];
                            Array.Copy(bytes, 0, incommingData, 0, length);
                            // Convert byte array to JSON message. 							
                            String clientMessage = Encoding.UTF8.GetString(incommingData);
                            // Added: convert string to JSON
                            JSONObject clientMessage_json = new JSONObject(clientMessage);
                            Debug.Log("client message received as: " + clientMessage_json);
                            RequestBlendshapes(clientMessage_json);
                        }
                    }
                }
            }
        } catch(SocketException socketException) {
            Debug.Log("SocketException " + socketException.ToString());
        }
    }


    // Use JSON message to set facial expressions - huh?????????
    public void RequestBlendshapes(JSONObject blendJson) {
        //Debug.Log("Changing blendshapes");
        foreach(string key in blendJson.keys) {
            //Debug.Log("key" + key); //it would just print the "rasp_pi" section from the json
            //Debug.Log(blendJson[key]); 
            string sender = key;
            string messageFromRaspPi = blendJson[key].ToString();//this is the payload we are after
            //Debug.Log(messageFromRaspPi);

            sendMessageToSLAM(sender, messageFromRaspPi);
        }

        // Tell the python client we received the message
        SendMessage(string.Format("Unity sends its regards {0}", counter++));
    }

    private void sendMessageToSLAM(string sender, string messageFromRaspPi) {
        //todo fix

        Dispatcher.Current.BeginInvoke(() =>
        {
            SLAM slam = GameObject.Find("World").GetComponent<SLAM>();
            slam.receiveMessage(sender, messageFromRaspPi);
        });
        
    }


    public void SendMessage(string messageToRaspPi) {
        if(connectedTcpClient == null) {
            return;
        }

        try {
            // Get a stream object for writing. 			
            NetworkStream stream = connectedTcpClient.GetStream();
            if(stream.CanWrite) {
                // Added: create dict to be a JSON object
                Dictionary<string, string> serverMessage = new Dictionary<string, string>();
                serverMessage["unity"] = messageToRaspPi;
                JSONObject serverMessage_json = new JSONObject(serverMessage);
                String serverMessage_string = serverMessage_json.ToString();
                // Convert string message to byte array.                 
                byte[] serverMessageAsByteArray = Encoding.UTF8.GetBytes(serverMessage_string);  // serverMessage				
                                                                                                 // Write byte array to socketConnection stream.               
                stream.Write(serverMessageAsByteArray, 0, serverMessageAsByteArray.Length);
                Debug.Log("Server sent his message - should be received by client - message: " + messageToRaspPi);
            }
        } catch(SocketException socketException) {
            Debug.Log("Socket exception: " + socketException);
        }
    }
}