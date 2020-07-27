package se.seb.academy.maze;

public enum Direction {
	NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);
	Direction left;
	Direction right;
	int dx;
	int dy;

	static {
		NORTH.left = WEST;
		EAST.left = NORTH;
		SOUTH.left = EAST;
		WEST.left = SOUTH;
		NORTH.right = EAST;
		EAST.right = SOUTH;
		SOUTH.right = WEST;
		WEST.right = NORTH;
	}

	Direction(int deltaX, int deltaY) {
		dx = deltaX;
		dy = deltaY;
	}
}
