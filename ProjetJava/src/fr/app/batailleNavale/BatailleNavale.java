package fr.app.batailleNavale;

import java.io.Serializable;
import java.util.ArrayList;

import fr.app.batailleNavale.domain.User;

public class BatailleNavale implements Serializable{
	
	// Compteur ID game
	private static int CPT_GAME = 0;
	
	/* Lecture au clavier de la taille du damier */
	private static final int N = 10;

	// Taille des bateaux
	private static final int N1 = 0;
	private static final int N2 = 1;
	private static final int N3 = 2;
	private static final int N4 = 1;
	private static final int N5 = 1;
	
	// Variable local
	
	// id de la partie
	private final int game_id;
	
	/* Description du plateau de jeu */
	private final int[][] tst;
	
	/* Description de la flote */
	private final boolean[][] fl;
	
	/*liste des joueurs*/
	private final ArrayList<User> joueurs;
	
	/*L'utilisateur qui à la main sur la partie à l'instant T*/
	private User joueurCourant;
	
	/*Partie commencée*/
	private boolean canStart;
	
	/*Partie finie*/
	private boolean isFinish;
	
	public BatailleNavale() {
		super();
		/* Initialisation du jeu  */
		this.game_id = CPT_GAME++;
	    this.fl = initFlote();
	    this.tst = initPlateau(fl);
	    this.joueurs = new ArrayList<>();
	    this.canStart = false;
	    this.isFinish = false;
	}
	
	/**
	 * Init les joueurs pour une partie donnée
	 */
	public void initJoueurs () {
		
	}
	
	public int getGame_id() {
		return game_id;
	}

	public int[][] getTst() {
		return tst;
	}


	public boolean[][] getFl() {
		return fl;
	}


	public ArrayList<User> getJoueurs() {
		return joueurs;
	}
	
	public boolean isCanStart() {
		return canStart;
	}

	public void setCanStart(boolean canStart) {
		this.canStart = canStart;
	}

	public boolean isFinish() {
		return isFinish;
	}

	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}

	//////////////////////////////////  Static Method ///////////////////////////////
	
	public static int[][] initPlateau(boolean[][] fl) {
		/* Generation et initialisation du tableau */
		/* des cases testees */
		int[][] tst = new int[fl.length][fl[0].length];
		for (int i = 0; i < fl.length; i++)
			for (int j = 0; j < fl[0].length; j++)
				tst[i][j] = -1;
		return (tst);
	}

	public static boolean[][] initFlote() {
		/* Generation de la flote sur le damier */
		return (genereFlote(N, N1, N2, N3, N4, N5));
	}

	public static boolean[][] genereFlote(int n, int n1, int n2, int n3, int n4, int n5) {
		boolean[][] t = new boolean[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				t[i][j] = false;
		for (int i = 0; i < n5; i++)
			placeBateau(5, t);
		for (int i = 0; i < n4; i++)
			placeBateau(4, t);
		for (int i = 0; i < n3; i++)
			placeBateau(3, t);
		for (int i = 0; i < n2; i++)
			placeBateau(2, t);
		for (int i = 0; i < n1; i++)
			placeBateau(1, t);
		return (t);
	}

	public static boolean placementPossible(boolean[][] t, int xi, int yi, int xf, int yf, int sens) {
		if (!dansTableau(t, xi, yi))
			return (false);
		if (!dansTableau(t, xf, yf))
			return (false);
		if (sens == 0) {
			for (int x = xi - 1; x <= xf + 1; x++)
				for (int y = yi - 1; y <= yi + 1; y++)
					if (dansTableau(t, x, y))
						if (t[y][x])
							return (false);
		} else {
			for (int x = xi - 1; x <= xi + 1; x++)
				for (int y = yi - 1; y <= yf + 1; y++)
					if (dansTableau(t, x, y))
						if (t[y][x])
							return (false);
		}
		return (true);
	}

	public static void place(boolean[][] t, int xi, int yi, int xf, int yf, int sens) {
		if (sens == 0) {
			for (int x = xi; x <= xf; x++)
				t[yi][x] = true;
		} else {
			for (int y = yi; y <= yf; y++)
				t[y][xi] = true;
		}
	}

	public static void placeBateau(int taille, boolean[][] t) {
		int xi, yi, xf = 0, yf = 0;
		int sens;
		do {
			xi = (int) (Math.random() * t.length);
			yi = (int) (Math.random() * t.length);
			sens = (int) (Math.random() * 4);
			switch (sens) {
			case 2:
				xf = xi;
				yf = yi + taille - 1;
				break;
			case 3:
				xf = xi;
				yf = yi;
				yi = yf - taille + 1;
				break;
			case 0:
				yf = yi;
				xf = xi + taille - 1;
				break;
			case 1:
				yf = yi;
				xf = xi;
				xi = xf - taille + 1;
				break;
			}
		} while (!placementPossible(t, xi, yi, xf, yf, sens / 2));
		place(t, xi, yi, xf, yf, sens / 2);
	}

	public static boolean dansTableau(boolean[][] t, int xi, int yi) {
		if (xi < 0)
			return (false);
		if (yi < 0)
			return (false);
		if (xi >= t.length)
			return (false);
		if (yi >= t.length)
			return (false);
		return (true);
	}
}
