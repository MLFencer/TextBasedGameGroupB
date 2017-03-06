import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class ServerMain {

	public static ArrayList<ServerThreads> threads = new ArrayList<ServerThreads>();

	public static void main(String[] args) throws IOException {
		Other.generate();



		String port="25565";

		int portNumber = Integer.parseInt(port);
		boolean listening = true;

		try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
			while (listening) {
				threads.add(new ServerThreads(serverSocket.accept()));
				threads.get(threads.size()-1).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}
}