package se.seb.academy.maze;

import java.util.HashMap;

public abstract class ACachingMazeRunner extends AMazeRunner {

	private HashMap<String, Position> posCache = new HashMap<>();

	@Override
	protected Position goLeft(Position position) {
		Direction newDir = position.getDirection().left;
		int newX = position.getX() + newDir.dx *2;
		int newY = position.getY() + newDir.dy *2;
		return go(newX, newY, newDir);
	}

	@Override
	protected Position goRight(Position position) {
		Direction newDir = position.getDirection().right;
		int newX = position.getX() + newDir.dx * 2;
		int newY = position.getY() + newDir.dy * 2;
		return go(newX, newY, newDir);
	}

	@Override
	protected Position goForward(Position position) {
		Direction newDir = position.getDirection();
		int newX = position.getX() + newDir.dx * 2;
		int newY = position.getY() + newDir.dy * 2;
		return go(newX, newY, newDir);
	}

	@Override
	protected Position goBackward(Position position) {
		Direction newDir = position.getDirection().right.right;
		int newX = position.getX() + newDir.dx * 2;
		int newY = position.getY() + newDir.dy * 2;
		return go(newX, newY, newDir);
	}

	private Position go(int x, int y, Direction direction) {
		String key = "x" + x + "y" + y + direction.toString();
		return posCache.computeIfAbsent(key, k -> new Position(x, y, direction));
	}
}