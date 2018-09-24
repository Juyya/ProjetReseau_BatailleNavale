package test;

import java.util.Scanner;
import java.util.Set;

public class Game implements Runnable {

	private Damier damier;
	private bateau[] tab_bateau;

	static bateau check_hit(coord missile, bateau[] tab) {
		int j = 0;
		for (j = 0; j < 5; j++) {
			if (tab[j].t(missile))
				return tab[j];
		}
		return null;
	}

	static void map(char[][] map_game, Damier d) {
		int i = 0, j = 0;
		System.out.printf(" ");
		for (j = 0; j < d.DAMIER_X; j++) {
			System.out.printf(" %d", j + 1);

		}
		System.out.printf("\n");

		for (j = 0; j < d.DAMIER_Y; j++) {
			System.out.printf("%d ", j + 1);
			for (i = 0; i < d.DAMIER_X; i++) {
				System.out.printf("%c", map_game[i][j]);
				System.out.printf(" ");
			}
			System.out.printf("\n");
		}

	}

	@Override
	public void run() {
		int i = 0, j = 0;
		damier = new Damier();
		char[][] map_game = new char[damier.DAMIER_X][damier.DAMIER_Y];
		Server sv = new Server();
		Client cl = new Client();
		coord missile;
		tab_bateau = new bateau[5];
		for (i = 0; i < 5; i++) {
			tab_bateau[i] = new bateau();
			tab_bateau[i].origine = new coord();
			switch (nom.nompour(i)) {
			case PORTEAVION:
				tab_bateau[i].taille = 5;
				break;
			case CROISEUR:
				tab_bateau[i].taille = 4;
				break;
			case CONTRETORPILLEUR:
				tab_bateau[i].taille = 3;
				break;
			case SOUSMARIN:
				tab_bateau[i].taille = 3;
				break;
			case TORPILLEUR:
				tab_bateau[i].taille = 2;
				break;
			}
			tab_bateau[i].origine.x = 0;
			tab_bateau[i].origine.y = 0;
		}

		for (i = 0; i < 5; i++) {
			switch (nom.nompour(i)) {
			case PORTEAVION:
				System.out.printf("Porte avion\n");
				while ((!tab_bateau[i].check_ds_damier(damier)) || (!tab_bateau[i].check_colision(i, tab_bateau))) {
					sv.demande_coord_orientation(tab_bateau[i]);
				}
				break;
			case CROISEUR:
				System.out.printf("Croiseur\n");
				while ((!tab_bateau[i].check_ds_damier(damier)) || (!tab_bateau[i].check_colision(i, tab_bateau))) {
					sv.demande_coord_orientation(tab_bateau[i]);
				}
				break;

			case CONTRETORPILLEUR:
				System.out.printf("Contre torpilleur\n");
				while ((!tab_bateau[i].check_ds_damier(damier)) || (!tab_bateau[i].check_colision(i, tab_bateau))) {
					sv.demande_coord_orientation(tab_bateau[i]);
				}
				break;

			case SOUSMARIN:
				System.out.printf("Sous Marin\n");
				while ((!tab_bateau[i].check_ds_damier(damier)) || (!tab_bateau[i].check_colision(i, tab_bateau))) {
					sv.demande_coord_orientation(tab_bateau[i]);
				}
				break;

			case TORPILLEUR:
				System.out.printf("Torpilleur\n");
				while ((!tab_bateau[i].check_ds_damier(damier)) || (!tab_bateau[i].check_colision(i, tab_bateau))) {
					sv.demande_coord_orientation(tab_bateau[i]);
				}
				break;
			}
		}

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				map_game[i][j] = '+';
			}
		}

		map(map_game, damier);
		while (resteUnBateau()) {
			missile = cl.attack();
			bateau bateauTouche = check_hit(missile, tab_bateau);
			if (bateauTouche!=null) {
				map_game[missile.x - 1][missile.y - 1] = 'T';
				if(bateauTouche.destroyed())
					for(coord c: bateauTouche.donne_coord())
						map_game[c.x - 1][c.y - 1] = 'C';
			} else {
				map_game[missile.x - 1][missile.y - 1] = 'R';
			}
			map(map_game, damier);
		}

	}

	private boolean resteUnBateau() {
		for(bateau b: tab_bateau)
			if(!b.destroyed())return true;
		return false;
	}

	public static void main(String[] args) {
		Game g = new Game();
		g.run();
	}
}
