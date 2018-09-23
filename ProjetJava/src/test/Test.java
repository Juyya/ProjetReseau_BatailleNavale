package test;

import java.util.Scanner;

public class Test {

	private final static int DAMIER_X = 10;
	private final static int DAMIER_Y = 10;

	static boolean check_ds_damier(bateau type) {

		if (type.orient == orientation.V) {
			if (type.origine.y + type.taille > DAMIER_Y) {
				return false;
			}
		}

		if (type.origine.y > DAMIER_Y || type.origine.y <= 0) {
			return false;
		}

		if (type.orient == orientation.H) {
			if (type.origine.x + type.taille > DAMIER_X) {
				return false;
			}
		}

		if (type.origine.x > DAMIER_X || type.origine.x <= 0) {
			return false;
		}
		return true;
	}

	static boolean check_colision(bateau type, int i, bateau[] tab) {
		int j = 0;
		int xa, xb, xc, xd, ya, yb, yc, yd;
		for (j = 0; j < 5; j++) {
			if (j != i) {
				if (tab[j].origine.x != 0 && tab[j].origine.y != 0) {
					xa = xb = type.origine.x;
					ya = yb = type.origine.y;
					xc = xd = tab[j].origine.x;
					yc = yd = tab[j].origine.y;

					if (type.orient == orientation.H) {
						xb += type.taille - 1;
					}
					if (type.orient == orientation.V) {
						yb += type.taille - 1;
					}

					if (tab[j].orient == orientation.V) {
						yd += tab[j].taille - 1;
					} else if (tab[j].orient == orientation.H) {
						xd += tab[j].taille - 1;
					}

					if (xa <= xc) {
						if (xb >= xc) {
							if (ya <= yc) {
								if (yb >= yc) {
									return false;
								}
							} else {
								if (yd >= ya) {
									return false;
								}
							}
						}
					} else {
						if (xd >= xa) {
							if (ya <= yc) {
								if (yb >= yc) {
									return false;
								}
							} else {
								if (yd >= ya) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	static void demande_coord_orientation(bateau type) {
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

	static boolean check_hit(coord missile, bateau[] tab) {
		int j = 0;
		for (j = 0; j < 5; j++) {
			if (tab[j].orient == orientation.H) {
				if (missile.x >= tab[j].origine.x && missile.x <= tab[j].origine.x + tab[j].taille - 1) {
					if (missile.y == tab[j].origine.y) {
						System.out.printf("touch");
						return true;
					}
				}
			} else if (tab[j].orient == orientation.V) {
				if (missile.y >= tab[j].origine.y && missile.y <= tab[j].origine.y + tab[j].taille - 1) {
					if (missile.x == tab[j].origine.x) {
						System.out.printf("touch");
						return true;
					}
				}
			}
		}
		return false;
	}

	static void map(char[][] map_game, int l_x, int l_y) {
		int i = 0, j = 0;
		System.out.printf(" ");
		for (j = 0; j < DAMIER_X; j++) {
			System.out.printf(" %d", j + 1);

		}
		System.out.printf("\n");

		for (j = 0; j < DAMIER_Y; j++) {
			System.out.printf("%d ", j + 1);
			for (i = 0; i < DAMIER_X; i++) {
				System.out.printf("%c", map_game[i][j]);
				System.out.printf(" ");
			}
			System.out.printf("\n");
		}

	}

	static coord attack() {
		coord missile = new coord();
		Scanner sc = new Scanner(System.in);
		System.out.printf("Entrer coordonées torpille\nx: ");
		missile.x = sc.nextInt();
		System.out.printf("y: ");
		missile.y = sc.nextInt();
		return missile;
	}

	public static void main(String[] args) {
		int i = 0, j = 0;
		char[][] map_game = new char[DAMIER_X][DAMIER_Y];
		coord missile;
		bateau[] tab_bateau = new bateau[5];
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
				while ((!check_ds_damier(tab_bateau[i])) || (!check_colision(tab_bateau[i], i, tab_bateau))) {
					demande_coord_orientation(tab_bateau[i]);
				}

				break;
			case CROISEUR:
				System.out.printf("Croiseur\n");
				while ((!check_ds_damier(tab_bateau[i])) || (!check_colision(tab_bateau[i], i, tab_bateau))) {
					demande_coord_orientation(tab_bateau[i]);
				}
				break;

			case CONTRETORPILLEUR:
				System.out.printf("Contre torpilleur\n");
				while ((!check_ds_damier(tab_bateau[i])) || (!check_colision(tab_bateau[i], i, tab_bateau))) {
					demande_coord_orientation(tab_bateau[i]);
				}
				break;

			case SOUSMARIN:
				System.out.printf("Sous Marin\n");
				while ((!check_ds_damier(tab_bateau[i])) || (!check_colision(tab_bateau[i], i, tab_bateau))) {
					demande_coord_orientation(tab_bateau[i]);
				}
				break;

			case TORPILLEUR:
				System.out.printf("Torpilleur\n");
				while ((!check_ds_damier(tab_bateau[i])) || (!check_colision(tab_bateau[i], i, tab_bateau))) {
					demande_coord_orientation(tab_bateau[i]);
				}
				break;
			}
		}

		for (i = 0; i < 10; i++) {
			for (j = 0; j < 10; j++) {
				map_game[i][j] = '+';
			}
		}

		map(map_game, DAMIER_X, DAMIER_Y);
		while (true) {
			missile = attack();
			if (check_hit(missile, tab_bateau)) {
				map_game[missile.x - 1][missile.y - 1] = 'T';
			} else {
				map_game[missile.x - 1][missile.y - 1] = 'R';
			}
			map(map_game, DAMIER_X, DAMIER_Y);
		}
	}
}
