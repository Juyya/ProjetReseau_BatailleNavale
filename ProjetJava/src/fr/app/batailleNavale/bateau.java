package fr.app.batailleNavale;

import java.util.HashSet;
import java.util.Set;

public class bateau {
	coord origine;
	orientation orient;
	int taille;
	Set<coord> ensemble = new HashSet<>();

	boolean check_ds_damier(Damier d) {

		if (orient == orientation.V) {
			if (origine.y + taille > d.DAMIER_Y) {
				return false;
			}
		}

		if (origine.y > d.DAMIER_Y || origine.y <= 0) {
			return false;
		}

		if (orient == orientation.H) {
			if (origine.x + taille > d.DAMIER_X) {
				return false;
			}
		}

		if (origine.x > d.DAMIER_X || origine.x <= 0) {
			return false;
		}
		return true;
	}

	boolean check_colision(int i, bateau[] tab) {
		int j = 0;
		int xa, xb, xc, xd, ya, yb, yc, yd;
		for (j = 0; j < 5; j++) {
			if (j != i) {
				if (tab[j].origine.x != 0 && tab[j].origine.y != 0) {
					xa = xb = origine.x;
					ya = yb = origine.y;
					xc = xd = tab[j].origine.x;
					yc = yd = tab[j].origine.y;

					if (orient == orientation.H) {
						xb += taille - 1;
					}
					if (orient == orientation.V) {
						yb += taille - 1;
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

	public boolean t(coord missile) {
		if (ensemble.contains(missile))
			return true;
		else {
			if (orient == orientation.H) {
				if (missile.x >= origine.x && missile.x <= origine.x + taille - 1) {
					if (missile.y == origine.y) {
						ensemble.add(missile);
						return true;
					}
				}
			} else if (orient == orientation.V) {
				if (missile.y >= origine.y && missile.y <= origine.y + taille - 1) {
					if (missile.x == origine.x) {
						ensemble.add(missile);
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean destroyed() {
		return taille == ensemble.size();
	}
	
	public Set<coord> donne_coord(){
		return ensemble;
	}

}
