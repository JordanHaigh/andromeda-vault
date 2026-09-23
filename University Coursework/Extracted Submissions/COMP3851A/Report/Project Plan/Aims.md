# Aims #

* Create a simulated environment which should closely resemble the real world functionality of the selected vehicle.
  * Accurately model the vehicle and its behaviour.
  * Build a track to test the vehicle in.
  * Simulate required physics to allow for an accurate simulation of the motion of the vehicle.

* Implement current algorithms in machine learning and Artificial Intelligence in the simulated environment.
  * Conditional based algorithms
  * Evolutionary algorithms
  * Q-learning
  * Deep Q-learning
  * PPO

* Compare and contrast current algorithms in machine learning and Artificial Intelligence on their performance and efficiency.
* Select the most appropriate algorithm to implement in the real world example
* Interface a raspberry pi with a selected remote control vehicle to allow intelligent control.
* Add required sensors to the raspberry pi. e.g. ultra sonic, accelerometer.
* Implement the chosen ML algorithm on the raspberry pi controlling the vehicle.
* Construct a physical track from cheap materials which is easily modified.  
* Have a remote control vehicle autonomously drive through learned behavior.


# Expected Outcomes #

Deliver a program which accurately simulates the physic and movement of the real world car. This program is to be created using unity which will allow the program to simulate the car in 3 dimensions. This simulation will also have the functionality to easily create tracks in the 3d environment to test/ train the car in, this allows us to quickly create a suite of tracks for the car to train on. A large number of training tracks will reduce the chances of the AIs overtraining on a specific track and should encourage the AIs to learn generalized behaviour which functions on any track we can give it.


Another functionality of the simulation is the ability to test a series of artificial intelligence algorithms on our problem environment. This will allow us to intelligently select the algorithm to use on the real life car and will also serve as a study comparing the positives and negatives of each algorithm. The simulation must additionally simulate the sensors which will be attached to the RC Car, this ensures the algorithms will perform similarly in the simulation and in reality since the sensory inputs will be identical.

  The first algorithm the simulation will be able to apply to the cars is simply a conditional based algorithm. This will simply check the distance to the left and right and if either is too close it will turn away. This is the most basic algorithm to be implemented and works both as a test ensuring the simulation works properly and to establish a base case for comparing the other algorithms to.

  The second algorithm to be tested is evolving an ANN to drive through the courses using the evolutionary algorithm. This algorithm will be more difficult to implement on the real car because it requires a large population to be functional and we only have one car. Its possible to test every car of a population one at a time however this would take a long time and require the car to be reset after each populant is tested, meaning this algorithm would require a lot of human intervention. All of this should be considered before selecting this algorithm.

  The third algorithm to be implemented is tabular Q-Learning. This algorithm cannot work on a near infinite input space, which the distance sensors will provide, so the input distances need to be divided into a small number of brackets in order to be able to tabulate the state space. This algorithm can be tweaked to work with the near infinite input space if we introduce Neural Networks, this algorithm is called deep Q-Learning.

  If we have the time another algorithm might be implemented. PPO is currently the state of the art reinforcement learning algorithm. However no one on the team has any experience with this algorithm so implementing it will be challenging, we might be able to use some libraries if we cannot implement it by hand.

The second part of this project is to implement the chosen algorithm on a remote control car. So we will deliver a rc car which has a raspberry pi integrated into it, allowing it to control the vehicle. The vehicle will also have multiple distance sensors attached to it allowing the algorithms to receive sensory information about its environment in order to act intelligently. The chosen ML algorithm will be implemented on the raspberry pi so the car can perform the algorithm.

A physical track made of a cheap material needs to be constructed in order to train the AI in the real word. This structure will need to be sturdy enough to withstand the car colliding with it consistently, we wish to train the car on multiple tracks so the constructed track needs to be easily modifiable.

Finally we will test the algorithm and deliver the RC car with a learned policy and a documentation of the learning process through an edited video compiling footage of the machine learning algorithm learning an optimal policy.



# team experience #

There are 2 main aspects of this project, the software/AI and the hardware. Luckily we have team members which have experience in both of these areas. Apart from PPO we have experience in implementing all of the other machine learning algorithms. The simulation will be created using unity, all team members have some experience with this game engine although some further learning will be required to create a program of this complexity, this isn't a concern because there is a lot of resources online for learning unity and we have allocated time in the timeline for team members to complete this learning.

The hardware component of this project is the most alien to the team. Although we have a team member who is familiar with raspberry pis and using it to control hardware components. This member will handle most of the integration of the pi with the rc car while the other 2 will handle building the simulation.

While some learning is required by its members, the team is confident we have the expertise required to achieve these aims and deliver the expected outcomes within the time allocated.
