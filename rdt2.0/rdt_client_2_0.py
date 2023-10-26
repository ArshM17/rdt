import socket
import hashlib
import random

def cal_checksum(data):
    hash_obj = hashlib.sha256()
    hash_obj.update(data.encode())
    return hash_obj.hexdigest()

SERVER_ADDRESS = ("localhost", 12345)
LOSS_PROBABILITY = 0.75

client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

while True:
    try:
        message = input("Client: Enter your message (or 'exit' to quit): ")
        if message.lower() == 'exit':
            break
        checksum = cal_checksum(message)
        old_message = message
        if random.random() > LOSS_PROBABILITY:
            message = "Error"
        packet = f"{message}:{checksum}"

        client_socket.sendto(packet.encode('utf-8'), SERVER_ADDRESS)

        ack_received = False
        while not ack_received:
            response, server_addr = client_socket.recvfrom(1024)
            response=response.decode('utf-8')
            if response == "ACK":
                print("Received ACK")
                ack_received = True
            else:
                print("Received NAK, resending the message...")
                if(random.random() < LOSS_PROBABILITY):
                    message = old_message
                    packet = f"{message}:{checksum}"
                client_socket.sendto(packet.encode('utf-8'), SERVER_ADDRESS)

    except Exception as e:
        print(f"Error: {e}")

client_socket.close()
