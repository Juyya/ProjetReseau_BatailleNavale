package fr.app.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.app.batailleNavale.BatailleNavale;
import fr.app.services.Authenfication;

public class SocketsServer {
	
	private final static int SOCKET_PORT = 10302;
	
	private final ServerSocket Srv;
	
	private static final Map<String, Map<BatailleNavale, List<Socket>>> OpenGames = new HashMap<String, Map<BatailleNavale, List<Socket>>>();

	public SocketsServer() throws IOException {
		super();
		this.Srv = new ServerSocket(SOCKET_PORT);
		
		System.out.println(new Date().toString() + ": Socket server is ready !");
	}
	
	public void run () throws IOException {
		while(true) {
			Socket clientsocket = this.Srv.accept();
			
			System.out.println("Connexion acceptée ! " + clientsocket.getInetAddress());
			
 			new Authenfication(this, clientsocket);
		}
	}
	
	public ServerSocket getSrv() {
		return Srv;
	}
	
	public static int getSocketPort() {
		return SOCKET_PORT;
	}
	
	//////////////////////////////////// Static Method ///////////////////////////////s

	public static Map<BatailleNavale, List<Socket>> getOpengame( String gameID) {
		return OpenGames.get(gameID);
	}
	
	/**
	 * Synchronisé la partie entre les differents joueurs
	 * @param socket
	 * @param data
	 * @throws IOException
	 */
	public static void syncGame (String gameID) throws IOException {
		String action = "GAME UPDATE";
		
		if (OpenGames.get(gameID) != null) {
			BatailleNavale game = ((List<BatailleNavale>) OpenGames.get(gameID).keySet()).get(0);
			for (Socket socket : OpenGames.get(gameID).get(game)) {
				envoyerData(socket, action, game);
			}
		} else {
			// Throw exception
		}
	}
	
	
	public static void envoyerData (Socket socket, String action, Object data) throws IOException {
		ObjectOutputStream  oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(action);
		oos.flush();
		
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(data);
		oos.flush();
	}
	
	public static <Any> Any recevoirData (Socket socket, Class<?> classObject) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		return ((Any)classObject.cast(ois.readObject()));
	}
}
