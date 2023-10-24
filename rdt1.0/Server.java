import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                socket.receive(receivePacket);
                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received: " + receivedMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

