package se.seb.academy.maze;

public abstract class AMazeRunner implements IMazeRunner {

	protected Position getEntry(int[][] maze) {
		int y = 1;
		int x = -1;
		int[] firstLine = maze[0];
		for(int i = 0; i < firstLine.length; i++) {
			if (firstLine[i] == BuildingBlock.ENTRY.getValue()) {
				x = i;
				break;
			}
		}
		if (x == -1) throw new IllegalStateException("Maze has no entry");
		return new Position(x, y, Direction.SOUTH);
	}

	protected boolean isExitFound(int[][] maze, Position position) {
		boolean isExitFound = false;
		if (lookLeft(maze, position) == BuildingBlock.EXIT
				|| lookForward(maze, position) == BuildingBlock.EXIT
				|| lookRight(maze, position) == BuildingBlock.EXIT) {
			isExitFound = true;
		}
		return isExitFound;
	}

	protected BuildingBlock lookLeft(int[][] maze, Position position) {
		int i = maze[position.getY() + position.getDirection().left.dy][position.getX() + position.getDirection().left.dx];
		return BuildingBlock.of(i);
	}

	protected BuildingBlock lookRight(int[][] maze, Position position) {
		int i = maze[position.getY() + position.getDirection().right.dy][position.getX() + position.getDirection().right.dx];
		return BuildingBlock.of(i);
	}

	protected BuildingBlock lookForward(int[][] maze, Position position) {
		int i = maze[position.getY() + position.getDirection().dy][position.getX() + position.getDirection().dx];
		return BuildingBlock.of(i);
	}

	protected Position goLeft(Position position) {
		Direction direction = position.getDirection();
		return new Position(position.getX() + direction.left.dx *2, position.getY() + direction.left.dy *2, direction.left);
	}

	protected Position goRight(Position position) {
		Direction direction = position.getDirection();
		return new Position(position.getX() + direction.right.dx *2, position.getY() + direction.right.dy *2, direction.right);
	}

	protected Position goForward(Position position) {
		Direction direction = position.getDirection();
		return new Position(position.getX() + direction.dx *2, position.getY() + direction.dy *2, direction);
	}

	protected Position goBackward(Position position) {
		Direction direction = position.getDirection();
		return new Position(position.getX() + direction.right.right.dx *2, position.getY() + direction.right.right.dy *2, direction.right.right);
	}
}
