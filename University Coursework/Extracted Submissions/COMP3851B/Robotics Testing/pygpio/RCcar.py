import pigpio
import time

class RCcar:
    def __init__(self, pi):
        print("Initialising RC car")
        print("Please turn on")

        self.pi = pi

        self.h1 = self.pi.spi_open(1, 15000000, 0)
        self.h2 = self.pi.spi_open(0, 15000000, 0)

        self.pi.spi_write(self.h1, b'\x00\x3C')
        self.pi.spi_write(self.h2, b'\x00\x3C')

        self.value = 0

        time.sleep(5)
        print("RC car ready")


    def shutdown(self):
        self.pi.spi_close(self.h1)
        self.pi.spi_close(self.h2)

    def forward(self):
        self.pi.spi_write(self.h1, b'\x00\x76')
        self.value = 1

    def backward(self):
        self.pi.spi_write(self.h1, b'\x00\x00')
        self.value = -1

    def stop(self):
        self.pi.spi_write(self.h1, b'\x00\x3C')
        self.value = 0
    
    def left(self):
        self.pi.spi_write(self.h2, b'\x00\x76')

    def right(self):
        self.pi.spi_write(self.h2, b'\x00\x00')

    def straight(self):
        self.pi.spi_write(self.h2, b'\x00\x3C')