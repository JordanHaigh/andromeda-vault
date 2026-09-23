# Draft Timeline

<!--                     Hardware                      -->
## ___Hardware___ ##
Sensor Testing ->Interface car with pie -> design car(Sensor Mounting, Supporting structures) -> build car

### __Sensor Testing: (Mat)__ ###
First step is to ensure that the distance sensors (USS) and the accelerometer work as expected with the raspberry pie.

### __Interface Car with pie: (Mat)__ ###
In order to control the car the raspberry pie needs to connect to the electronics in the rc car. There are 2 main ways of doing this; the first and easiest being connecting to the controller and sending directions to the receiver in the car. This would mean we don’t have to directly control the motors, instead simply telling the car to move forward, backwards, left and/or right. The second method is to connect the raspberry pie directly to the motors and control the car from there. This is far more difficult but would give us a faster response time and more precise control over the motors.

### __Design Car prototype: (Mat/Jordan)__ ###
Before using a larger remote control car we will initially create a smaller protorype which uses a cheaper rc car as its base and also contains less sensors.

The car needs to carry the raspberry pie and a breadboard and we need to figure out how we will mount the sensors to get the best result. 

### __Design required 3d models for prototype: (Evan) Second Sem__ ###
We will need to construct some structures to hold the electronics within the car, and also to mount the sensors easily. Using a simple CAD program called tinkercad we will design the necessary 3d structures.

### __Print 3D models (Evan) Second Sem__  ### 
By using the resources available to us as students we are able to use the unis 3d printer.  We have contacted James Bradley and is able to print any 3d models we require.

### __Build car: (Jordan/ Mat)__ ###
Following the design we created we will mount the sensors and all other required electronics on the car with the printed 3d models.

### __Design track: (Jordan / Mat)__ ###
We need a track for out car to test/ learn from, this track should be made of material which is cheap/sturdy and preferably easily modifiable and it would be best to train/tests the car on various different tracks.

### __Build track: (Mat/Jordan)__ ###
The materials for the track need to be purchased and the track itself needs to be assembled. And tested on with an unmodified rc car to determine robustness and track difficulty.
<!--                     Software                      -->
## ___Software___ ##
Design Car movement simulator -> build track editor -> Collision Detection -> Sensor simulation -> Code AIs(Hard Code, DFS, QLearning, DQL, other DQL methods, PPO?)

### __Design Car movement simulator: (Evan)__ ###
In order to test different machine learning algorithms easily we need to design a program which can closely simulate the motion and control of the remote control car. So the first step is coding a 2d top down simulation of the car. In order for the simulation to be accurate it needs to include drift calculations and acceleration/ turning rates similar to the rc car.

### __Build Track editor: (Jordan)__ ###
The car will learn to drive around a track and in order to increase generality and not overfit behaviour to a single track, we need a way of quickly creating new tracks. A simple track editor would be the best way to do this. Ideally the user could simply draw a path with a mouse and the track is automatically calculates the track with a certain width. Alternatively the user could just draw the edges of the track.

### __Collision Detection: (Evan)__ ###
In order for the simulation to recognise when the car has collided with the side of the track we need to include the collision detection required.

### __Sensor simulation: (Evan)__ ###
The simulation to should not only closely represent how the car moves and interacts but should also simulate the cars sensors. The Ais shouldn’t have access to any information that the ai cant receive from the sensors attached to the raspberry pie. Currently the only sensors we are using are distance sensors and an accelerometer; The accelerometer is only used for determining when the AI crashes so the only input to the raspberry pie will be the distance sensors. 

One of the main purposes of the simulation is to determine whether or not an ai can learn with such incomplete information from the environment. The ai wont know, its current speed, its current position and its drift velocity, and will only have a limited view of obstacles in its way.

There are ways to give the ai this information through additional sensors, however some of these are expensive and we don’t to buy any sensors we don’t need.

### __Code AIs: (Evan)__ ###
Using the simulation we can easily and quickly test many AI algorithms to determine which algorithms perform best in this environment. We can draw up some sexy ass graphs comparing each of the algorithms by the following criteria: time to train, best score, memory required (maybe more).
The algorithms we will test are:

___Hard Code:___
-	Simply just a bunch of if statements which will control the car. E.g. if the distance to the left is less the 10cm then turn right.
-	This will essentially be considered as the base case with all other alorithms (hopefully) performing better

___DFS/BFS:___
-	Since the simulation can look ahead into the future it would be possible to create an algorithm which would look forward a certain amount into the future and test all possible combination of moves then chose the best to perform.
-	This however wont be possible to implement on the RC car so this algorithm wont be implemented or tested

___Evolution:___
-	A combination of the genetic algorithm and neural networks will be test to see if a optimal policy can evolve from within a population. 
-	This Algorithm however cannot be replicated on the RC car in a timely manner. The simulation can emulate many cars at once making it potentially hundreds of times faster than the single RC car we will have.
-	If the simulation was accurate enough then it would be possible to evolve the Neural Network within the simulation then transfer it to the RC car, however we want the learning to take place from within the RC car.
-	Evolution will still be tested as part of our study comparing algorithms.

___QLearning:___
-	Basic tabular qlearning will be tested 
-	A tabular solution needs to have a finite input space, although the distance sensors produce an infinite(pretty much) range of possible distances.
-	The number of possible input states are (number of possible inputs from the distance sensor)^(number of distance sensors used)
-	In order to limit the input space we need to simply separate the inputs of the distance sensors into different brackets. E.g split the input into 5 brackets:
```
d<5cm
5cm<= d <=10cm
10 < d < 15
15 < d < 25
25 < d 
```

-	Thus we can reduce the input space from infinite to about 5. Very nice

___DQL:___
-	QLearning with neural networks
-	Neural networks can handle an infinite input space no problem there
-	There are many further advances in DQL such as double DQL, duelling DQL and priority replay.
-	This would require tensorflow to be installed on the raspberry pie which isn’t great

___PPO(?)___
-	I don’t really know PPO but I know that it is more probability base Qlearning
-	PPO would be interesting to add but we would need to include learning it in the scope.

### __Test AIs: (Evan)__ ###
In order to select the appropriate AI we will test the AIs against a list of criteria, including speed, performance, memory required…





<!--              Combining software and hardware              -->
## __Combining software and hardware__ ##
### __Get the basic Hard coded AI working (Jordan/Mat)__ ###
Before adding which ever advanced AI we select, we will implement the most basic IF-THEN style AI, to ensure that the car was constructed properly and that the sensors work as expected. Note: this can be done before discovering the best AI.

### __Implement Chosen AI (Evan/ Jordan/ Mat)__ ###
Which ever AI was selected will be implemented on the raspberry to interact with the RC car and the sensors.

### __Test and Tweak AI: (Evan/Jordan /Mat)__ ###
I am sure that we will not be successful on the first run, so further testing and tweaking of the car/ai will be necessary.





















