package se.seb.academy.maze;

public class Position {

	private int x;
	private int y;
	private Direction direction;

	public Position(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + x;
		result = 31 * result + y;
		result = 31 * result + direction.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof Position)) {
			return false;
		}
		Position pos = (Position) obj;
		return pos.x == x && pos.y == y && pos.direction == direction;
	}

	@Override
	public String toString() {
		return "Position {"
				+ "x=" + x
				+ ", y=" + y
				+ ", direction=" + direction.toString()
				+ "}";
	}
}
