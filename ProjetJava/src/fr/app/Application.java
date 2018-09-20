package fr.app;

import java.io.IOException;

import fr.app.sockets.SocketsServer;

public class Application {

	public static void main(String[] args) {
		System.out.println("Projet ESIEA - 2018-2019 - Bataille Navale");
		try {
			final SocketsServer server = new SocketsServer();
			server.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
