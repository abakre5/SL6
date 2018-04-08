import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{
	
	boolean closed = false, inputFromAll = false;
	List<ClientThread> clientThreadList;
	List<String> data;
	
	Server(){
		clientThreadList = new ArrayList<ClientThread>();
		data = new ArrayList<String>();
	}
	
	public static void main(String[] args) throws Exception {
			Server server = new Server();
			Socket clSocket = null;
			ServerSocket serverSocket = null;
			int port = 9999;
			
			serverSocket = new ServerSocket(port);
			
			while(!server.closed){
				clSocket = serverSocket.accept();
				ClientThread clientThread = new ClientThread(server, clSocket);
				server.clientThreadList.add(clientThread);
				System.out.println("Total Number of CLient " + server.clientThreadList.size());
				server.data.add("NOT_SENT");
				clientThread.start();
			}
			serverSocket.close();
	}
	
}