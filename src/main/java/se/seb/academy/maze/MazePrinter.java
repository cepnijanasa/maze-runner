package se.seb.academy.maze;

import java.util.Arrays;
import java.util.List;

public class MazePrinter {

	private MazePrinter() {
		// should not be instantiated
	}

	public static void print(char[][] maze) {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public static void printEscapePath(char[][] maze, List<Position> positionList) {
		char[][] mazeCopy = new char[maze.length][];
		for(int i = 0; i < maze.length; i++) {
			mazeCopy[i] = Arrays.copyOf(maze[i], maze[i].length);
		}

		for(Position p: positionList) {
			mazeCopy[p.getY()][p.getX()] = '*';
		}
		print(mazeCopy);
	}

}
