You need to put the tcp_client.py on the Raspberry Pi.
Theres comments inside that file to tell you what to do

* Need to install 'asyncio' (sudo apt-get install asynchio) on the raspberry pi
* If you're developing, use '127.0.0.1' for the HOST address, if not, use a respective IP on the network
* Make sure the 25565 port is open for forwarding