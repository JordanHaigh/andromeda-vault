import RPi.GPIO as GPIO
import time
from tkinter import *

class RC:
    def __init__(self):
        GPIO.setmode(GPIO.BCM)

        self.LEFT_TRIG = 0
        self.LEFT_ECHO = 0

        self.RIGHT_TRIG = 0
        self.RIGHT_ECHO = 0

        self.ANTENNA_PIN = 17

        self.COMMAND_FORWARD = 10
        self.COMMAND_BACKWARD = 40
        self.COMMAND_LEFT = 58
        self.COMMAND_RIGHT = 64

        GPIO.setup(self.ANTENNA_PIN, GPIO.OUT)
        self.p = GPIO.PWM(19, 500)

        GPIO.setup(self.LEFT_TRIG, GPIO.OUT)
        GPIO.setup(self.LEFT_ECHO, GPIO.IN)
        GPIO.setup(self.RIGHT_TRIG, GPIO.OUT)
        GPIO.setup(self.RIGHT_ECHO, GPIO.OUT)

        GPIO.output(self.LEFT_TRIG, False)
        GPIO.output(self.RIGHT_TRIG, False)
        self.reset()
    
    def forward(self):
        self.send_command(self.COMMAND_FORWARD)
        #GPIO.output(self.FORWARD_PIN, True)
    
    def backward(self):
        self.send_command(self.COMMAND_BACKWARD)
        #GPIO.output(self.BACKWARD_PIN, True)

    def left(self):
        self.send_command(self.COMMAND_LEFT)
        #GPIO.output(self.LEFT_PIN, True)

    def right(self):
        self.send_command(self.COMMAND_RIGHT)
        #GPIO.output(self.RIGHT_PIN, True)

    def reset(self):
        pass
        '''GPIO.output(self.FORWARD_PIN, False)
        GPIO.output(self.BACKWARD_PIN, False)
        GPIO.output(self.LEFT_PIN, False)
        GPIO.output(self.RIGHT_PIN, False)'''
    
    def cleanup(self):
        GPIO.cleanup()
    
    def left_distance(self):
        GPIO.output(self.LEFT_TRIG, True)
        time.sleep(0.00001)
        GPIO.output(self.LEFT_TRIG, False)

        while GPIO.input(self.LEFT_ECHO) == 0:
            pulse_start = time.time()
        
        while GPIO.input(self.LEFT_ECHO) == 1:
            pulse_end = time.time()
        
        pulse_duration = pulse_end - pulse_start

        return pulse_duration * 17150
    
    def right_distance(self):
        GPIO.output(self.RIGHT_TRIG, True)
        time.sleep(0.00001)
        GPIO.output(self.RIGHT_TRIG, False)

        while GPIO.input(self.RIGHT_ECHO) == 0:
            pulse_start = time.time()
        
        while GPIO.input(self.RIGHT_ECHO) == 1:
            pulse_end = time.time()
        
        pulse_duration = pulse_end - pulse_start

        return pulse_duration * 17150
    
    def send_command(self, command):
        for w2 in range(4):
            GPIO.output(self.ANTENNA_PIN, True)
            time.sleep(0.001460)
            GPIO.output(self.ANTENNA_PIN, False)
            time.sleep(0.000488)
        
        for w1 in range(command):
            GPIO.output(self.ANTENNA_PIN, True)
            time.sleep(0.000488)
            GPIO.output(self.ANTENNA_PIN, False)
            time.sleep(0.000488)

rc = RC()

def keydown(e):
    if e.char == 'w':
        print("forward go")
        rc.forward()
    elif e.char == 's':
        print("backward go")
        rc.backward()
    elif e.char == 'a':
        print("left go")
        rc.left()
    elif e.char == 'd':
        print("right go")
        rc.right()
        
root = Tk()
running = True

def onclosing():
    running = False
    root.destroy()

#if __name__ == '__main__':
root.protocol("WM_DELETE_WINDOW", onclosing)
frame = Frame(root, width=100, height=100)
frame.bind("<KeyPress>", keydown)
#frame.bind("<KeyRelease>", keyup)
frame.pack()
frame.focus_set()
root.mainloop()

while running:
    time.sleep(0.01)

rc.cleanup()