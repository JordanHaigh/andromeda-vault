import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)

TRIG = 23
ECHO = 24

def setup():
    print("Distance measurement in Progress")
    GPIO.setup(TRIG, GPIO.OUT)
    GPIO.setup(ECHO,GPIO.IN)

    # GPIO.output(TRIG, False)
    # print("waiting for Sensor to settle")
    # time.sleep(0.01)

def pulse():
    GPIO.output(TRIG, True)
    time.sleep(0.1)
    GPIO.output(TRIG, False)

    pulse_start = time.time()
    pulse_end = time.time()

    while GPIO.input(ECHO)==0:
        pulse_start = time.time()
    while GPIO.input(ECHO) == 1:
        pulse_end = time.time()

    pulse_duration = pulse_end - pulse_start
    distance = pulse_duration * 17150

    distance = round(distance,2)
    return distance

def cleanup():
    GPIO.cleanup()

def runInf():
    setup()
    while(True):
        distance = pulse()
        print("Distance:", distance, "cm")
    cleanup()

def runOnce():
    distance = pulse()
    print("Distance:", distance, "cm")
    return distance