package se.seb.academy.maze;

import java.util.ArrayList;
import java.util.List;

public class AlwaysRightRunner extends AMazeRunner {

	@Override
	public List<Position> escapeMaze(int[][] maze) {
		List<Position> positionList = new ArrayList<>();
		Position position = getEntry(maze);
		positionList.add(position);

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
		}
		return positionList;
	}
}
