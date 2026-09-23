import RPi.GPIO as GPIO
import time
from evdev import InputDevice

GPIO.setmode(GPIO.BCM)

GPIO_TRIGGER = 18
GPIO_ECHO = 24

GPIO_FORWARD = 17
GPIO_BACKWARD = 27
GPIO_LEFT = 5
GPIO_RIGHT = 6

GPIO_LIGHT = 13

GPIO.setup(GPIO_TRIGGER, GPIO.OUT)
GPIO.setup(GPIO_ECHO, GPIO.IN)

GPIO.setup(GPIO_FORWARD, GPIO.OUT)
GPIO.setup(GPIO_BACKWARD, GPIO.OUT)
GPIO.setup(GPIO_LEFT, GPIO.OUT)
GPIO.setup(GPIO_RIGHT, GPIO.OUT)

GPIO.setup(GPIO_LIGHT, GPIO.OUT)

gamepad = InputDevice("/dev/input/event2")

def distance():
    #send out pulse for a microsecond
    GPIO.output(GPIO_TRIGGER, True)
    time.sleep(0.00001)
    GPIO.output(GPIO_TRIGGER, False)
    
    #start timer
    StartTime = time.time()
    StopTime = time.time()
    
    #while haven't received echo, update start time
    while GPIO.input(GPIO_ECHO) == 0:
        StartTime = time.time()
    
    #while we are receiving signal, update stop time
    while GPIO.input(GPIO_ECHO) == 1:
        StopTime = time.time()
    
    #calc time between signals
    TimeElapsed = StopTime - StartTime
    
    distance = (TimeElapsed * 34300) / 2
    
    return distance

def handle_btn(code, value):
    pass
    if(code == 17):
        if(value == -1):
            GPIO.output(GPIO_FORWARD, True)
        elif(value == 1):
            GPIO.output(GPIO_BACKWARD, True)
        else:
            GPIO.output(GPIO_FORWARD, False)
            GPIO.output(GPIO_BACKWARD, False)
    if(code == 16):
        if(value == -1):
            GPIO.output(GPIO_LEFT, True)
        elif(value == 1):
            GPIO.output(GPIO_RIGHT, True)
        else:
            GPIO.output(GPIO_LEFT, False)
            GPIO.output(GPIO_RIGHT, False)

if __name__ == "__main__":
    try:
        GPIO.output(GPIO_FORWARD, False)
        GPIO.output(GPIO_BACKWARD, False)
        GPIO.output(GPIO_LEFT, False)
        GPIO.output(GPIO_RIGHT, False)
        GPIO.output(GPIO_LIGHT, True)
        while True:
            #dist = distance()
            #GPIO.output(GPIO_FORWARD, dist < 10)
            #print("Distance = %.1f cm" % dist)
            time.sleep(0.1)
            for event in gamepad.read_loop():
                if event.code in (16, 17):
                    print(event.value)
                    handle_btn(event.code, event.value)
            
    except KeyboardInterrupt:
        print("Stopped by user")
        GPIO.cleanup()
