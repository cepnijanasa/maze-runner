package se.seb.academy.maze;

import java.util.List;

public class MazeConverter {

	private MazeConverter() {}

	public static char[][] toCharMaze(List<String> sourceFileRows) {
		char[][] result = new char[sourceFileRows.size()][];
		String processed;

		for(int i = 0; i < sourceFileRows.size(); i++) {
			processed = sourceFileRows.get(i).replace(",", "")
					.replace("+--", "+-")
					.replace("+  ", "+ ")
					.replace("|  ", "| ")
					.replace("   ", "  ")
					.replace("\r", "")
					.replace("\n", "");

			result[i] = processed.toCharArray();
		}
		return result;
	}

	public static int[][] toIntMaze(char[][] charMaze) {
		int[][] result = new int[charMaze.length][charMaze[0].length];
		int path;

		for(int i = 0; i < charMaze.length; i++) {
			path = (i == 0) ? BuildingBlock.ENTRY.getValue() : (i == charMaze.length - 1) ? BuildingBlock.EXIT.getValue() : BuildingBlock.PATH.getValue();

			for (int j = 0; j < charMaze[i].length; j++) {
				result[i][j] = (charMaze[i][j] == ' ') ? path : BuildingBlock.WALL.getValue();
			}
		}
		return result;
	}
}
