import pigpio
import time

PIN = 19

pi = pigpio.pi()

pi.set_mode(PIN, pigpio.OUTPUT)

pi.wave_clear()

'''end_pulses = []

for i in range(4):
    end_pulses.append(pigpio.pulse(1 << PIN, 0, 2667))
    end_pulses.append(pigpio.pulse(0, 1 << PIN, 8000))

pi.wave_add_generic(end_pulses)

end_wave = pi.wave_create()

pi.wave_clear()

code_pulses = []
for i in range(10):
    code_pulses.append(pigpio.pulse(0, (1 << PIN), 2000))
    code_pulses.append(pigpio.pulse((1 << PIN), 0, 2000))

pi.wave_add_generic(code_pulses)

code_wave = pi.wave_create()'''

'''for x in range(100):
    for i in range(4):
        pi.wave_send_once(end_wave)

    for i in range(10):
        pi.wave_send_once(code_wave)'''

code = []

for i in range(4):
    code.append(pigpio.pulse(1 << PIN, 0, 375))
    code.append(pigpio.pulse(0, 1 << PIN, 125))

for i in range(10):
    code.append(pigpio.pulse(0, (1 << PIN), 500))
    code.append(pigpio.pulse((1 << PIN), 0, 500))

pi.wave_add_generic(code)
code_wave = pi.wave_create()
pi.wave_send_repeat(code_wave)




pi.stop()
