import socket
import random

SERVER_ADDRESS = ("localhost", 12345)
LOSS_PROBABILITY = 0.75
SEQ_NO=0

client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

while True:
    try:
        message = input("Client: Enter your message (or 'exit' to quit): ")
        if message.lower() == 'exit':
            break
        old_message = message
        if random.random() > LOSS_PROBABILITY:
            message = "Error"

        packet = f"{message}:{SEQ_NO%2}"

        if random.random() > LOSS_PROBABILITY:
            packet = f"{message}:{int(not(SEQ_NO%2))}"                

        client_socket.sendto(packet.encode('utf-8'), SERVER_ADDRESS)
                

    except Exception as e:
        print(f"Error: {e}")

client_socket.close()
