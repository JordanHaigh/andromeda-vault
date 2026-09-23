import time
import pigpio

GPIO = 22

pi = pigpio.pi()

pi.set_mode(GPIO, pigpio.OUTPUT)

pi.wave_clear()

end_wave = [
    pigpio.pulse(1<<GPIO, 0, 1500),
    pigpio.pulse(0, 1<<GPIO, 500),
] * 4

pi.wave_add_generic(end_wave)
end_wid = pi.wave_create()

forward_wave = [
    pigpio.pulse(1<<GPIO, 0, 500),
    pigpio.pulse(0, 1<<GPIO, 500),
] * 10
pi.wave_add_generic(forward_wave)
forward_wid = pi.wave_create()

backward_wave = [
    pigpio.pulse(1<<GPIO, 0, 500),
    pigpio.pulse(0, 1<<GPIO, 500),
] * 40
pi.wave_add_generic(backward_wave)
backward_wid = pi.wave_create()

left_wave = [
    pigpio.pulse(1<<GPIO, 0, 500),
    pigpio.pulse(0, 1<<GPIO, 500),
] * 58
pi.wave_add_generic(left_wave)
left_wid = pi.wave_create()

right_wave = [
    pigpio.pulse(1<<GPIO, 0, 500),
    pigpio.pulse(0, 1<<GPIO, 500),
] * 64
pi.wave_add_generic(right_wave)
right_wid = pi.wave_create()

while True:
    pi.wave_chain([end_wid, forward_wid])
    while pi.wave_tx_busy():
        pass
        #time.sleep(0.001)
    
pi.wave_chain([end_wid, forward_wid])
#pi.wave_send_once(end_wid)
#pi.wave_send_once(forward_wid)

while pi.wave_tx_busy():
    time.sleep(0.1)

pi.wave_clear()
pi.stop()
print("done")

