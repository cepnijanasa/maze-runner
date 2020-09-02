package se.seb.academy.maze;

import java.util.HashMap;

public abstract class ACachingMazeRunner extends AMazeRunner {

	private HashMap<String, Position> posCache = new HashMap<>();

	@Override
	protected Position go(Position position, Direction direction) {
		int newX = position.getX() + direction.dx * 2;
		int newY = position.getY() + direction.dy * 2;
		String key = "x" + newX + "y" + newY + direction.toString();
		return posCache.computeIfAbsent(key, k -> new Position(newX, newY, direction));
	}
}