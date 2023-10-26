import socket

SERVER_PORT = 12345

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

server_socket.bind(("localhost", SERVER_PORT))
print(f"Server listening on {'localhost'}:{SERVER_PORT}")

while True:
    try:
        data, client_address = server_socket.recvfrom(1024)
        message = data.decode('utf-8').split(':')[0]

        if message.lower() == 'exit':
            print(f"Connection terminated with {client_address}.")
            break
        else: 
            print(message)

    except KeyboardInterrupt:
        print(f"Connection interrupted with {client_address}.")

server_socket.close()
