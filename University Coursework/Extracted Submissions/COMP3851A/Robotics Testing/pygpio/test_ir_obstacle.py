from gpiozero import *
from time import sleep

sensor = GPIODevice(27)

while True:
    sleep(1)
    print(sensor.value)