import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;


public class ClientThread extends Thread{
	
	Server server = null;
	Socket clSocket = null;
	PrintStream printStream;
	DataInputStream dataInputStream;
	String line, name;
	
	public ClientThread(Server server, Socket clSocket){
		this.server = server;
		this.clSocket = clSocket;
	}
	
	@SuppressWarnings("deprecation")
	public void run(){
		try {
			dataInputStream = new DataInputStream(clSocket.getInputStream());
			printStream = new PrintStream(clSocket.getOutputStream());
			printStream.println("Enter name :: ");
			name = dataInputStream.readLine();
			printStream.println("Welcome " + name + " to this 2 Phase Application.\nYou will receive a vote Request now...");
			printStream.println("VOTE_REQUEST\nPlease enter COMMIT or ABORT to proceed : ");
			
			for(int i = 0;i < server.clientThreadList.size();i++){
				if(server.clientThreadList.get(i) != this)
					server.clientThreadList.get(i).printStream.println("---A new user " + name + " entered the Appilcation");
			}
			
			while(true){
				line = dataInputStream.readLine();
				if(line.equals("ABORT")){
					System.out.println("Client " + name + " ABORTED");
					for(int i = 0;i < server.clientThreadList.size();i++){
						server.clientThreadList.get(i).printStream.println("GLOBAL_ABORT");
						server.clientThreadList.get(i).printStream.close();
						server.clientThreadList.get(i).dataInputStream.close();
					}
					break;
				}
				else if(line.equals("COMMIT")){
					System.out.println("Client " + name + "Commited");
					if(server.clientThreadList.contains(this)){
						server.data.set(server.clientThreadList.indexOf(this), "COMMIT");
						for(int i = 0;i <  server.clientThreadList.size();i++){
							if(!server.data.get(i).equals("NOT_SENT")){
								server.inputFromAll = true;
								continue;
							}
							else {
								server.inputFromAll = false;
								break;
							}
						}
						
						if(server.inputFromAll){
							System.out.println("Committed");
							for(int i = 0;i < server.clientThreadList.size();i++){
								server.clientThreadList.get(i).printStream.println("GLOBAL_COMMIT");
								server.clientThreadList.get(i).printStream.close();
								server.clientThreadList.get(i).dataInputStream.close();
							}
							break;
						}
						
					}
				}
			}
			server.closed = true;
			clSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
