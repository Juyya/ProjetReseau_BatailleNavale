package fr.app.sockets;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.app.batailleNavale.domain.User;

public class SocketClient {
	
	///Pattern :: Singleton => Une instance de connexion socket par client
	private static SocketClient instance;
	
	private static User connected;
	
	private final Socket socket;
	
	public SocketClient () throws UnknownHostException, IOException {
		this.socket = new Socket("Localhost", SocketsServer.getSocketPort());
		instance = this;
	}
	
	public Socket getSocket() {
		return socket;
	}

	
	///////////////// Static Method /////////////
	
	public static User getConnected() {
		return connected;
	}



	public static void setConnected(User connected) {
		SocketClient.connected = connected;
	}

	public static SocketClient getInstance() throws UnknownHostException, IOException {
		if (instance == null) {
			return new SocketClient();
		}
		
		return instance;
	}
	
	/**
	 * Synchronisé la partie entre les differents joueurs
	 * @param socket
	 * @param data
	 * @throws IOException
	 
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
	*/
	
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
