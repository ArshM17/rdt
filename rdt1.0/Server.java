import java.io.*;
import java.net.*;

public class Server {
	private int portNumber = 4000;;
        private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private InputStream in = null;
	private OutputStream out = null;
    	Server(){
		while(true){
		try{
        		serverSocket = new ServerSocket(portNumber);
       			clientSocket = serverSocket.accept();
       			while(true){
				in = clientSocket.getInputStream();
				out = clientSocket.getOutputStream();
				byte[] buffer = new byte[1024];
				int receivedData = in.read(buffer);
				String clientMsg = new String(buffer, 0, receivedData);
				if(!clientMsg.equals("q") && !clientMsg.equals("Q")){
					out.write(("Your message was-"+clientMsg).getBytes());
					System.out.println(clientMsg);
				}else{
					serverSocket.close();
					clientSocket.close();
					break;
				}
			}
		} catch (Exception e){
			System.out.println("Some error occured");
		}
		}
    	}
	
	public static void main(String[] args){
		Server server = new Server();
	}
}

