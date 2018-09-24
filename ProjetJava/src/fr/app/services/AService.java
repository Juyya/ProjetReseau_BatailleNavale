package fr.app.services;

import java.net.Socket;
import java.util.List;

import fr.app.batailleNavale.BatailleNavale;
import fr.app.sockets.SocketsServer;

public abstract class AService extends Thread{
	
	private final String action;
	
	private final SocketsServer server;
	
	private BatailleNavale game;
	
	private final Socket socket;

	private final boolean needToConnect;
    
	public AService(String action, SocketsServer server, Socket socket, boolean needToConnect) {
		super();
		this.action = action;
		this.server = server;
		this.socket = socket;
		this.needToConnect = needToConnect;
	}

	public String getAction() {
		return action;
	}

	public SocketsServer getServer() {
		return server;
	}
	
	/*
	 * Cherche, init et met à jour une partie à partir de son ID
	 */
	public void UpdateGame(String gameId) {
		if (gameId == null || gameId.length() == 0) {
			return;
		}
		this.game = ((List<BatailleNavale>) SocketsServer.getOpengame(gameId).keySet()).get(0);
	}
	
	public Socket getSocket() {
		return socket;
	}

	public boolean isNeedToConnect() {
		return needToConnect;
	}
}
