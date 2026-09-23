import pigpio
from RCcar import RCcar
from MPU6050 import MPU6050
from DistanceSensor import DistanceSensor
import time
import threading
from tcp_client import genMessageSendToServer

from tkinter import *
import random

running = False

def main():
    global running
    pi = pigpio.pi()
    rc = RCcar(pi)
    imu = MPU6050(pi)
    fs = DistanceSensor(pi, 13, 26)
    bs = DistanceSensor(pi, 6, 26)
    ls = DistanceSensor(pi, 5, 26)
    rs = DistanceSensor(pi, 12, 26)

    root = Tk()
    running = True

    def onclose():
        global running
        running = False
        root.destroy()
    
    def exit(e):
        global running
        running = False
        root.destroy()

    def keydown(e):
        if e.char == 'w':
            rc.forward()
        elif e.char == 's':
            rc.backward()
        elif e.char == 'a':
            rc.left()
        elif e.char == 'd':
            rc.right()
    
    def keyup(e):
        if e.char == 'w':
            rc.stop()
        elif e.char == 's':
            rc.stop()
        elif e.char == 'a':
            rc.straight()
        elif e.char == 'd':
            rc.straight()

    def sensor_loop():
        global running
        while running:
            msg = "<{0},{1},{2},{3}>".format(fs.read(), bs.read(), ls.read(), rs.read())
            genMessageSendToServer('Dist', msg)

    threading.Thread(target=sensor_loop).start()

    def send_loop():
        global running
        while running:
            msg = "<{0},{1}>".format(rc.value, imu.get_yaw())
            genMessageSendToServer('6Axis', msg)
    
    threading.Thread(target=send_loop).start()

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

    #setupMockWorld()
    #threading.Thread(target=setupMockWorld).start()

    root.protocol("WM_DELETE_WINDOW", onclose)
    frame = Frame(root, width=100, height=100)
    frame.bind("<KeyPress>", keydown)
    frame.bind("<KeyRelease>", keyup)
    frame.bind("<Escape>", exit)
    frame.pack()
    frame.focus_set()
    root.mainloop()    

if __name__ == "__main__":
    main()