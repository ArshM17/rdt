import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

	private Socket clientSocket = null;
	private InputStream in = null;
	private OutputStream out = null;
	Client(String serverIP, int serverPort){
		try{
    	    		clientSocket = new Socket(serverIP, serverPort);
			System.out.println("Connected");
	    	    	Scanner scan = new Scanner(System.in);
    	    		while(true){
    	    			System.out.print("Enter a message to be sent to the server:(Q to quit)");
    		    		String msg = scan.nextLine();
    		    		out = clientSocket.getOutputStream();
    		    		out.write(msg.getBytes());
				if(msg.equals("q") || msg.equals("Q")) break;
				in = clientSocket.getInputStream();
				byte buffer[] = new byte[1024];
				int serverMsg = in.read(buffer);
				System.out.println("Server says: "+new String(buffer, 0, serverMsg));
			}
    		    	scan.close();
    	    		clientSocket.close();
		} catch(Exception e){
			System.out.println("Some error occured: "+e);
		}
    	}
	
	public static void main(String[] args){
		if(args.length<=1){
    	    		System.out.println("Too few command line arguments, this command takes 2 parameters. 1st: IP address of server, 2nd: Server Port");
    	    		return;
    	    	}
    	    	String serverIP = args[0];
    	    	int serverPort = Integer.valueOf(args[1]);
		Client client = new Client(serverIP, serverPort);
	}
}

