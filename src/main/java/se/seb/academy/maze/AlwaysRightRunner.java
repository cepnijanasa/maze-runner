package se.seb.academy.maze;

import java.util.ArrayList;
import java.util.List;

public class AlwaysRightRunner extends AMazeRunner {

	@Override
	public List<Position> escapeMaze(int[][] maze) {
		List<Position> positionList = new ArrayList<>();
		Position position = getEntry(maze);
		positionList.add(position);
		int counter = 0;

		while(!isExitFound(maze, position)) {
			if (lookRight(maze, position) == BuildingBlock.PATH) {
				position = goRight(position);
			} else if (lookForward(maze, position) == BuildingBlock.PATH) {
				position = goForward(position);
			} else if (lookLeft(maze, position) == BuildingBlock.PATH) {
				position = goLeft(position);
			} else {
				position = goBackward(position);
			}
			positionList.add(position);

			if (counter++ == 10000)
				throw new RuntimeException("Something must be wrong, couldn't escape the maze in " + counter + " moves");
		}
		return positionList;
	}
}
