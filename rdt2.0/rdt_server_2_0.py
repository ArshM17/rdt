import socket
import hashlib

SERVER_PORT = 12345

def cal_checksum(data):
    hash_obj = hashlib.sha256()
    hash_obj.update(data.encode())
    return hash_obj.hexdigest()

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_socket.bind(("localhost", SERVER_PORT))
print(f"Server listening on {'localhost'}:{SERVER_PORT}")

while True:
    try:
        data, client_address = server_socket.recvfrom(1024)
        message, checksum = data.decode('utf-8').split(':')

        if cal_checksum(message) != checksum:
            print("Received corrupted message. Sending NAK.")
            server_socket.sendto("NAK".encode(), client_address)
        else:
            print(f"Received message: \"{message}\". Sending ACK.")
            server_socket.sendto("ACK".encode(), client_address)

        if message.lower() == 'exit':
            print(f"Connection terminated with {client_address}.")
            break

    except KeyboardInterrupt:
        print(f"Connection interrupted with {client_address}.")

    # Do not prompt to close the server unless the user explicitly wants to exit the server.
    # Remove the choice input.
    # choice = input("Do you want to close the server (y/n): ")
    # if choice.lower() != 'n':
    #     print("Closing the server...")
    #     break

server_socket.close()
