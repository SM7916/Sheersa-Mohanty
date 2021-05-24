import socket
from datetime import datetime
from psutil import process_iter
from signal import SIGTERM # or SIGKILL

# x=[]
# # with open('C:\\OpenPortNumber.txt', 'r') as txtfile:
# #     xreader = txtfile.read().splitlines()
# #     for ports in xreader:
# x.append('25333')
# print(x)
def port_killer(port):
    #port='25333'
    remoteServer    = "localhost" #it can be a remote server machine also
    remoteServerIP  = socket.gethostbyname(remoteServer)

# print ("-" * 100)
# print("Please wait, scanning host", remoteServerIP)
# print ("-" * 100)

# Check what time the scan started
    t1 = datetime.now()

# for port in x: #this range can be changed as per the requirement
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    result = sock.connect_ex((remoteServerIP, int(port)))
    if result == 0:
            print ("Port {}: 	 Open".format(port))
            for proc in process_iter():
                for conns in proc.connections(kind='inet'):
                    if conns.laddr.port == int(port):
                        print("Killing a process..PID:==>")
                        print(proc.pid)
                        proc.send_signal(SIGTERM)  # or SIGKILL
                        print("Killed!!")
                        break

    sock.close()




