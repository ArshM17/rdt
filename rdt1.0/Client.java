import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
	    Scanner scan = new Scanner(System.in);
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345;
	    while(true){
            	System.out.print("Enter a message to be sent to the server:(Q to quit)");
            	String message = scan.nextLine();
            	if(message.equals("Q") || message.equals("q")) break;
            	byte[] sendData = message.getBytes();
            	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            	socket.send(sendPacket);
            	System.out.println("Sent: " + message);
	    }
	    scan.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

