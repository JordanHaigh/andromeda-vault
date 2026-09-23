import asyncio #make sure to sudo apt-get install this
import json

counter = 0
HOST = '192.168.1.5' #when running python and unity on separate machines (oi you need to change this to the right one. This is jordans working ip)
#HOST = '127.0.0.1' #when running both python and unity on same machine
PORT = 25565 #cheeky minecraft reference - may need port forwarding open

# Asynchronous communication with Unity 3D over TCP
async def tcp_echo_client(message, loop):
    # open connection with Unity 3D
    reader, writer = await asyncio.open_connection(HOST, PORT,
                                                   loop=None)
    print('Send: %r' % message)

    # test purposes
    global counter
    #message['welcome'] = f'Hello World {counter}!'

    # convert JSON to bytes
    message_json = json.dumps(message).encode()
    # send message
    writer.write(message_json)
    counter += 1

    # wait for data from Unity 3D
    data = await reader.read(100)
    # we expect data to be JSON formatted
    data_json = json.loads(data.decode())
    print('Received:\n%r' % data_json)

    print('Close the socket')
    writer.close()


def genMessageSendToServer(message):
    genMessageSendToServer('rasp_pi',message)

    
def genMessageSendToServer(sender, message):
    jsonMessage = {sender: message}
    loop = asyncio.new_event_loop()
    # loop = asyncio.get_event_loop()
    loop.run_until_complete(tcp_echo_client(jsonMessage, loop))
    loop.close()