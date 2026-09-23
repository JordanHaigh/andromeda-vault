# # testingaccel
import pygame as pygame
import time
import random
from tcp_client import genMessageSendToServer
# import UltrasonicTest

import os
import pygame.display

screen = pygame.display.set_mode((50, 50))   # 1180, 216

def setupMockWorld():
    for y in range(5):
        maxDist = 100
        for x in range(100):
            pointX = random.randrange(-maxDist, maxDist, 1)
            pointZ = random.randrange(-maxDist, maxDist, 1)
            point = [pointX, 1, pointZ]
            genMessageSendToServer('devSensor', "<{0},{1},{2}>".format(point[0],point[1],point[2]))
            genMessageSendToServer('devSensor', "<{0},{1},{2}>".format(point[0]+0.1,point[1],point[2]+0.1))
        maxDist = maxDist + 100


def run():
    flag = False  # The flag is essential.   Ok whatev u r not boss of me??????????
    running = True
    sendingMessagesToServer = True

    motorState = 0.00
    motorStateStepSize = 1
    turnState = 0.00
    turnDegrees = 0
    turnDegreesStepSize = 15


    while running:
        # ultrasonicDistance = UltrasonicTest.runOnce()
        # genMessageSendToServer('LEFT_Dist', "<{0}>".format(ultrasonicDistance))
        # genMessageSendToServer('RIGHT_Dist', "<{0}>".format(ultrasonicDistance))
        motorState = round(motorState, 2)
        print("Current Motor State: ", motorState)
        print('GyroDegrees ', "<{0}>".format(turnDegrees))

        if(sendingMessagesToServer):
            genMessageSendToServer('6Axis', "<{0},{1}>".format(motorState, turnDegrees))

        pygame.event.pump()  # process event queue
        keys = pygame.key.get_pressed()  # It gets the states of all keyboard keys.


        ###########################################################################################
        

        if keys[ord('w')]:  # And if the key is K_DOWN:
            motorState += motorStateStepSize

            if(motorState > 1.00):
                motorState = 1.00

        elif keys[ord('s')]:  # And if the key is K_DOWN:
            motorState -= motorStateStepSize

            if(motorState < -1.00):
                motorState = -1.00

        else:
            if(motorState != -0.00 or motorState != 0.00):
                if(motorState < 0.00):
                    motorState += motorStateStepSize

                else:
                    motorState -= motorStateStepSize

        ###########################################################################################


        if keys[ord('a')]:  # And if the key is K_DOWN:
            turnState -= turnDegreesStepSize

            if(turnState < -1.00):
                turnState = -1.00

            turnDegrees -= turnDegreesStepSize
            if(turnDegrees % 360 == 0):
                turnDegrees = 0

        elif keys[ord('d')]:  # And if the key is K_DOWN:
            turnState -= turnDegreesStepSize

            if(turnState > 1.00):
                turnState = 1.00

            turnDegrees += turnDegreesStepSize
            if(turnDegrees % 360 == 0):
                turnDegrees = 0

        else:
            if(turnState != -0.00 or turnState != 0.00):
                if(turnState < 0.00):
                    turnState += turnDegreesStepSize
                else:
                    turnState -= turnDegreesStepSize
        ###########################################################################################


        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        
        
        time.sleep(0.1)



# setupMockWorld()
# UltrasonicTest.setup()
run()
# UltrasonicTest.cleanup()
