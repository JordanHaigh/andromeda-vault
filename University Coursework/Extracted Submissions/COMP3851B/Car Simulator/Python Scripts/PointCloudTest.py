#dummy creates the message and sends using tcp_client
import random
import time
from tcp_client import genMessageSendToServer;

# message = 'Hello I am boris'
#genMessageSendToServer(message)


#generate square points and send to server
# for x in range(10):
#     for y in range(10):
#         string = "<{0},0,{1}>".format(x,y)
#         genMessageSendToServer(string)



#Simulate two sets of sensors sending points to unity (left side of car and right side of car)
carPos = [2.5, 0, 4.5]

#bottom walls
for x in range(100):
    leftOfCarPos = [carPos[0] , carPos[1], carPos[2] + random.uniform(4.9,5)]
    genMessageSendToServer('leftSensor', "<{0},{1},{2}>".format(leftOfCarPos[0],leftOfCarPos[1],leftOfCarPos[2]))
   
    rightOfCarPos = [carPos[0], carPos[1], carPos[2] - random.uniform(4.9,5) ]
    genMessageSendToServer('rightSensor', "<{0},{1},{2}>".format(rightOfCarPos[0],rightOfCarPos[1],rightOfCarPos[2]))

    #increment carPos X position because its going to the right
    carPos[0] = carPos[0]+1

#bottom right corner
leftOfCarPos = [carPos[0] , carPos[1], carPos[2] + random.uniform(4.9,5)]
genMessageSendToServer('leftSensor', "<{0},{1},{2}>".format(leftOfCarPos[0],leftOfCarPos[1],leftOfCarPos[2]))

for x in range(10):
    rightOfCarPos = [carPos[0], carPos[1], carPos[2] - random.uniform(4.9,5)]
    genMessageSendToServer('rightSensor', "<{0},{1},{2}>".format(rightOfCarPos[0],rightOfCarPos[1],rightOfCarPos[2]))
    carPos[0] = carPos[0]+1
for x in range(11):
    rightOfCarPos = [carPos[0], carPos[1], carPos[2]-random.uniform(4.9,5)]
    genMessageSendToServer('rightSensor', "<{0},{1},{2}>".format(rightOfCarPos[0],rightOfCarPos[1],rightOfCarPos[2]))

    #increment carPos X position because its going to the right
    carPos[2] = carPos[2]+1

carPos[0] = carPos[0]-5 #reset to middle
carPos[2] = carPos[2]-5 #reset to middle

#right wall
for x in range(50):
    leftOfCarPos = [carPos[0] - random.uniform(4.9,5) , carPos[1], carPos[2] ]
    genMessageSendToServer('leftSensor', "<{0},{1},{2}>".format(leftOfCarPos[0],leftOfCarPos[1],leftOfCarPos[2]))
   
    rightOfCarPos = [carPos[0] + random.uniform(4.9,5), carPos[1], carPos[2]  ]
    genMessageSendToServer('rightSensor', "<{0},{1},{2}>".format(rightOfCarPos[0],rightOfCarPos[1],rightOfCarPos[2]))

    #increment carPos X position because its going to the right
    carPos[2] = carPos[2]+1



#top right corner
carPos[0] = carPos[0]+5
for x in range(10):
    rightOfCarPos = [carPos[0], carPos[1], carPos[2] + random.uniform(4.9,5)]
    genMessageSendToServer('rightSensor', "<{0},{1},{2}>".format(rightOfCarPos[0],rightOfCarPos[1],rightOfCarPos[2]))
    carPos[2] = carPos[2]+1
for x in range(11):
    rightOfCarPos = [carPos[0] - random.uniform(4.9,5), carPos[1], carPos[2]]
    genMessageSendToServer('rightSensor', "<{0},{1},{2}>".format(rightOfCarPos[0],rightOfCarPos[1],rightOfCarPos[2]))
    carPos[0] = carPos[0]-1