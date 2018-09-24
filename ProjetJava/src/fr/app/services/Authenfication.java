package fr.app.services;

import java.io.IOException;
import java.net.Socket;

import fr.app.batailleNavale.domain.User;
import fr.app.sockets.SocketsServer;
import fr.app.utils.Database;

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
		try {
			if(SocketsServer.recevoirData(this.getSocket(), String.class).toString().equalsIgnoreCase(ACTION)) {
				String input = SocketsServer.recevoirData(this.getSocket(), String.class).toString();
				
				String login = input.split("///")[0];
				String password = input.split("///")[1];
				
				User u = Database.findUser(login, password);
				
				SocketsServer.envoyerData(this.getSocket(), ACTION, u);
				
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
