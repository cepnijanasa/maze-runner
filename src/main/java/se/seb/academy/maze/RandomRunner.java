package se.seb.academy.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomRunner extends AMazeRunner {

	@Override
	public List<Position> escapeMaze(int[][] maze) {
		List<Position> positionList = new ArrayList<>();
		Position position = getEntry(maze);
		positionList.add(position);
		int counter = 0;
		int side, otherSide;
		Random random = new Random();

		while(!isExitFound(maze, position)) {
			// get random number 0 or 1
			side = random.nextInt(2);
			// if side = 1, then otherSide = 0; if side = 0, then otherSide = 1
			otherSide = Math.abs(side - 1);

			if (look(maze, position, side) == BuildingBlock.PATH) {
				position = go(position, side);
			} else if (lookForward(maze, position) == BuildingBlock.PATH) {
				position = goForward(position);
			} else if (look(maze, position, otherSide) == BuildingBlock.PATH) {
				position = go(position, otherSide);
			} else {
				position = goBackward(position);
			}
			positionList.add(position);

			if (counter++ == 10000)
				throw new RuntimeException("Something must be wrong, couldn't escape the maze in " + counter + " moves");
		}
		return positionList;
	}

	private BuildingBlock look(int[][] maze, Position position, int side) {
		if (side == 0) {
			return lookLeft(maze, position);
		}
		return lookRight(maze, position);
	}

	private Position go(Position position, int side) {
		if (side == 0) {
			return goLeft(position);
		}
		return goRight(position);
	}

}