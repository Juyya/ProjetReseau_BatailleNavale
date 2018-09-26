package fr.app.batailleNavale;

import java.util.Scanner;

public class Client {
	
	coord attack() {
		coord missile = new coord();
		Scanner sc = new Scanner(System.in);
		while((missile.x >10 || missile.x == 0) && (missile.y >10 || missile.y == 0)){
		System.out.printf("Entrer coordonées torpille\nx: ");
		missile.x = sc.nextInt();
		System.out.printf("y: ");
		missile.y = sc.nextInt();
		}
		return missile;
	}

}
