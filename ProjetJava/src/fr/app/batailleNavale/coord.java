package test;

public class coord {
	int x;
	int y;

	@Override
	public boolean equals(Object arg0) {
		if (!(arg0 instanceof coord))
			return false;
		return ((coord) arg0).x == x && ((coord) arg0).y == y;
	}
}