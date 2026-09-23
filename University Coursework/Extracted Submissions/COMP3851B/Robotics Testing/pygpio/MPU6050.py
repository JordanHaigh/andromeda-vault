import pigpio
import threading

class MPU6050:
    def __init__(self, pi):
        serial_h = None
        self.yaw = 0
        try:
            serial_h = pi.serial_open("/dev/ttyACM0", 115200)
        except:
            serial_h = pi.serial_open("/dev/ttyACM1", 115200)

        def serial_loop():
            print("Started Serial Thread")
            while True:
                try:
                    (count, data) = pi.serial_read(serial_h)
                    if count > 0:
                        s = data.decode('utf-8')
                        if s.find('ypr') != -1 and s.find('\n') != -1:
                            y = s.split('\t')[1]
                            self.yaw = float(y)
                        elif s.find('aworld') != -1 and s.find('\n') != -1:
                            d = s.split('\t')
                            self.accel = (float(d[1]), float(d[2]), float(d[3]))
                except:
                    pass

        
        threading.Thread(target=serial_loop).start()
    
    def get_yaw(self):
        return self.yaw + 180.0
    
    def get_accel(self):
        return self.accel

    '''def __init__(self, pi):
        self.pi = pi
        MPUAddress = 0x68

        self.handle = self.pi.i2c_open(1, MPUAddress)

        self.pi.i2c_write_byte_data(self.handle, 0x6B, 0x00)

    def getAccelData(self):
        self.pi.i2c_write_byte(self.handle, 0x3B)

        (count, data) = self.pi.i2c_read_device(self.handle, 6)
        if count != 6:
            print("Something fucked up")

        AccX = (data[0] << 8 | data[1]) / 16384.0
        AccY = (data[2] << 8 | data[3]) / 16384.0
        AccZ = (data[4] << 8 | data[5]) / 16384.0

        return (AccX, AccY, AccZ)

    def getGyroData(self):
        self.pi.i2c_write_byte(self.handle, 0x43)

        (count, data) = self.pi.i2c_read_device(self.handle, 6)
        if count != 6:
            print("Something fucked up")

        GyroX = (data[0] << 8 | data[1]) / 131.0
        GyroY = (data[2] << 8 | data[3]) / 131.0
        GyroZ = (data[4] << 8 | data[5]) / 131.0

        return (GyroX, GyroY, GyroZ)'''