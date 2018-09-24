package fr.app;

import java.io.IOException;

import javax.swing.JFrame;

import fr.app.sockets.SocketsServer;
import fr.app.views.Login;

public class Application {
	
	private static final String HELP_MESSAGE = "";
	public static void main(String[] args) {
		System.out.println("Projet ESIEA - 2018-2019 - Bataille Navale");
		if (args.length == 1) {
			try {
				final SocketsServer server = new SocketsServer();
				server.run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(HELP_MESSAGE);
			Login login = new Login();
		}
	}

}
