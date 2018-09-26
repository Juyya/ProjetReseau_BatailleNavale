package fr.app.batailleNavale;

import java.util.Scanner;

public class Server {
	
	void demande_coord_orientation(bateau type) {
		
		int orientation_bateau;
		Scanner sc = new Scanner(System.in);
		System.out.printf("Entrer x: ");
		type.origine.x = sc.nextInt();
		System.out.printf("Entrer y: ");
		type.origine.y = sc.nextInt();
		System.out.printf("Orientation(orientation.V(1)/orientation.H(2)): ");
		orientation_bateau = sc.nextInt();
		type.orient = orientation.orientpour(orientation_bateau - 1);
	}
	
}
