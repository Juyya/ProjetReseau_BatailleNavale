package fr.app.jeu;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//On crée la grille
				Grille g1 = new Grille(15,10);
				
				//On place le joueur
				g1.placerJoueur(3, 3, 'J');
				g1.afficher();

	}

}
