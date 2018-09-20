package fr.app.services;

import java.net.Socket;

import fr.app.sockets.SocketsServer;

public class Authenfication extends AService {
	
	private static final String ACTION = "AUTHENTIFICATION";
	
	// Besoin d'étre authentifié pour déclanché un evenement
	private static final Boolean NEED_TO_CONNECT = false;
	
	public Authenfication(SocketsServer server, Socket socket) {
		super(ACTION, server, socket, NEED_TO_CONNECT);
		this.start();
	}	
	
	@Override
	public void run() {
		
	}
}
