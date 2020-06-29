package se.seb.academy.maze;

enum BuildingBlock {
	WALL(0),
	PATH(1),
	ENTRY(2),
	EXIT(3);

	BuildingBlock(int val) {
		value = val;
	}

	private int value;

	public int getValue() {
		return value;
	}
}
