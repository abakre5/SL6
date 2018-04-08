import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Client implements Runnable{

	static Socket clientSocket = null;
	static PrintStream printStream = null;
	static DataInputStream dataInputStream = null;
	static BufferedReader bufferedReader = null;
	static boolean closed = false;
	
	public static void main(String args[]) throws Exception{
		String host = "localhost";
		int port = 9999;
		
		clientSocket = new Socket(host, port);
		printStream = new PrintStream(clientSocket.getOutputStream());
		dataInputStream = new DataInputStream(clientSocket.getInputStream());
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		if(clientSocket != null && printStream != null && dataInputStream != null){
			new Thread(new Client()).start();
			while(!closed){
				printStream.println(bufferedReader.readLine());
			}
			
			printStream.close();
			dataInputStream.close();
			bufferedReader.close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		String line;
		try {
			while((line = dataInputStream.readLine()) != null){
				System.out.println(line);
				if(line.equals("GLOBAL_COMMIT") == true || line.equals("GLOBAL_ABORT") == true)
					break;
			}
			closed = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
