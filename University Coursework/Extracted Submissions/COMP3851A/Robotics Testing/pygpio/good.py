import time
import pigpio
import threading
from evdev import InputDevice

gamepad = InputDevice("/dev/input/event2")
forward_down = False
backward_down = False
left_down = False
right_down = False

class distance_sensor:
    def __init__(self, pi, trig, echo):
        self.pi = pi
        self.trig_GPIO = trig
        self.echo_GPIO = echo
        
        self.ping = False
        self.triggered = False
        self.high = None
        self.time = None
        
        pi.set_mode(self.trig_GPIO, pigpio.OUTPUT)
        pi.set_mode(self.echo_GPIO, pigpio.INPUT)
        
        self.trig_callback = pi.callback(self.trig_GPIO, pigpio.EITHER_EDGE, self.callback_fn)
        self.echo_callback = pi.callback(self.echo_GPIO, pigpio.EITHER_EDGE, self.callback_fn)
    
    def callback_fn(self, gpio, level, tick):
        if gpio == self.trig_GPIO:
            if level == 0:
                self.triggered = True
                self.high = None
        else:
            if self.triggered:
                if level == 1:
                    self.high = tick
                else:
                    if self.high is not None:
                        self.time = tick - self.high
                        self.high = None
                        self.ping = True

    def read(self):
        self.ping = False
        self.pi.gpio_trigger(self.trig_GPIO)
        start = time.time()
        while not self.ping:
            if(time.time() - start) > 5.0:
                return 20000
            time.sleep(0.001)
        return self.time
    
    def __del__(self):
        self.trig_callback.cancel()
        self.echo_callback_cancel()

class RC:
    def __init__(self):
        print("Initializing RC")
        self.ANT_GPIO = 22

        self.pi = pigpio.pi()

        self.pi.set_mode(self.ANT_GPIO, pigpio.OUTPUT)
        
        self.left_sensor = distance_sensor(self.pi, 17, 27)
        self.right_sensor = distance_sensor(self.pi, 5, 6)

        self.pi.wave_clear()

        end_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 1500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 4
        self.pi.wave_add_generic(end_pulses)
        self.end_wave = self.pi.wave_create()

        forward_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 10
        self.pi.wave_add_generic(forward_pulses)
        self.forward_wave = self.pi.wave_create()

        backward_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 40
        self.pi.wave_add_generic(backward_pulses)
        self.backward_wave = self.pi.wave_create()

        left_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 58
        self.pi.wave_add_generic(left_pulses)
        self.left_wave = self.pi.wave_create()

        right_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 64
        self.pi.wave_add_generic(right_pulses)
        self.right_wave = self.pi.wave_create()

        turbo_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 22
        self.pi.wave_add_generic(turbo_pulses)
        self.turbo_wave = self.pi.wave_create()

        forwardleft_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 28
        self.pi.wave_add_generic(forwardleft_pulses)
        self.forwardleft_wave = self.pi.wave_create()

        forwardright_pulses = [
        pigpio.pulse(1<<self.ANT_GPIO, 0, 500),
        pigpio.pulse(0, 1<<self.ANT_GPIO, 500),
        ] * 34
        self.pi.wave_add_generic(forwardright_pulses)
        self.forwardright_wave = self.pi.wave_create()

    def __del__(self):
        self.pi.wave_clear()
        self.pi.stop()

    def forward(self):
        self.pi.wave_chain([self.end_wave, self.forward_wave])
        while self.pi.wave_tx_busy():
            pass

    def backward(self):
        self.pi.wave_chain([self.end_wave, self.backward_wave])
        while self.pi.wave_tx_busy():
            pass

    def left(self):
        self.pi.wave_chain([self.end_wave, self.left_wave])
        while self.pi.wave_tx_busy():
            pass

    def right(self):
        self.pi.wave_chain([self.end_wave, self.right_wave])
        while self.pi.wave_tx_busy():
            pass

    def forwardleft(self):
        self.pi.wave_chain([self.end_wave, self.forwardleft_wave])
        while self.pi.wave_tx_busy():
            pass

    def forwardright(self):
        self.pi.wave_chain([self.end_wave, self.forwardright_wave])
        while self.pi.wave_tx_busy():
            pass
        
    def left_distance(self):
        return round(self.left_sensor.read() / 1000000.0 * 34030, 2)
    
    def right_distance(self):
        return round(self.right_sensor.read() / 1000000.0 * 34030, 2)
    

rc = RC()

def handle_btn(code, value):
    global rc
    global forward_down
    global backward_down
    global left_down
    global right_down
    if code == 17:
        if value == -1:
            forward_down = True
        elif value == 1:
            backward_down = True
        else:
            forward_down = False
            backward_down = False
    elif code == 16:
        if value == -1:
            left_down = True
        elif value == 1:
            right_down = True
        else:
            left_down = False
            right_down = False
    else:
        print(str(code) + " : " + str(value))


def controller_interface_loop():
    for event in gamepad.read_loop():
        if event.code in (16, 17):
            handle_btn(event.code, event.value)
            
def sensor_loop():
    while True:
        #print("loop")
        #print("LEFT: " + str(rc.left_distance()))
        left = rc.left_distance()
        if left < 10:
            print("proximity left")
        time.sleep(0.1)
        #print("RIGHT: " + str(rc.right_distance()))
        right = rc.right_distance()
        if right < 10:
            print("proximity right")
        time.sleep(0.1)

threading.Thread(target=controller_interface_loop).start()
threading.Thread(target=sensor_loop).start()

while True:
    if forward_down:
        if left_down:
            rc.forwardleft()
        elif right_down:
            rc.forwardright()
        else:
            rc.forward()
    elif backward_down:
        rc.backward()
    elif left_down:
        rc.left()
    elif right_down:
        rc.right()
