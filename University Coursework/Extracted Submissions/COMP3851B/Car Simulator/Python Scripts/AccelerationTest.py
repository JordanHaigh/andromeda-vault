# # testingaccel
import pygame as pygame
import time
import random
from tcp_client import genMessageSendToServer
import UltrasonicTest

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


def simulateAccel():
    flag = False  # The flag is essential.   Ok whatev u r not boss of me??????????
    running = True
    motorState = 0.00
    stepSize = 0.05


    while running:
        ultrasonicDistance = UltrasonicTest.runOnce()
        genMessageSendToServer('LEFT_Dist', "<{0}>".format(ultrasonicDistance))
        genMessageSendToServer('RIGHT_Dist', "<{0}>".format(ultrasonicDistance))


        motorState = round(motorState, 2)
        print("Current Motor State: ", motorState)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        pygame.event.pump()  # process event queue
        keys = pygame.key.get_pressed()  # It gets the states of all keyboard keys.
        if keys[ord('w')]:  # And if the key is K_DOWN:
            motorState += stepSize

            if(motorState > 1.00):
                motorState = 1.00

            genMessageSendToServer('Accel', "<{0}>".format(motorState))
        elif keys[ord('s')]:  # And if the key is K_DOWN:
            motorState -= stepSize

            if(motorState < -1.00):
                motorState = -1.00

            genMessageSendToServer('Accel', "<{0}>".format(motorState))

        else:
            if(motorState != -0.00 or motorState != 0.00):
                if(motorState < 0.00):
                    motorState += stepSize
                    genMessageSendToServer('Accel', "<{0}>".format(motorState))
                else:
                    motorState -= stepSize
                    genMessageSendToServer('Accel', "<{0}>".format(motorState))


        time.sleep(0.1)

# setupMockWorld()
UltrasonicTest.setup()
simulateAccel()
UltrasonicTest.cleanup()
