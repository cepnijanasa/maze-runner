package se.seb.academy.maze;

import java.util.List;

public class SourceConverter {

	private SourceConverter() {}

	public static char[][] toCharMatrix(List<String> sourceFileRows) {
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

	public static int[][] toIntMatrix(char[][] charMatrix) {
		int[][] result = new int[charMatrix.length][charMatrix[0].length];
		int path;

		for(int i = 0; i < charMatrix.length; i++) {
			path = (i == 0) ? BuildingBlock.ENTRY.getValue() : (i == charMatrix.length - 1) ? BuildingBlock.EXIT.getValue() : BuildingBlock.PATH.getValue();

			for (int j = 0; j < charMatrix[i].length; j++) {
				result[i][j] = (charMatrix[i][j] == ' ') ? path : BuildingBlock.WALL.getValue();
			}
		}
		return result;
	}
}
