import time
import pigpio

class DistanceSensor:
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
        return self.time / 1000.0
    
    def __del__(self):
        self.trig_callback.cancel()
        self.echo_callback.cancel()