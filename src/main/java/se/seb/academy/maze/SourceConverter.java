package se.seb.academy.maze;

import java.util.List;

public class SourceConverter {

	private SourceConverter() {}

	private static int[] convertRowToMatrix(String source, int wall, int path) {

		String sourceProcessed = source.replace(",", "")
				.replace("+--", "+-")
				.replace("+  ", "+ ")
				.replace("|  ", "| ")
				.replace("   ", "  ")
				.replace("\r", "")
				.replace("\n", "");

		System.out.println(sourceProcessed);

		int[] result = new int[sourceProcessed.length()];

		for (int i = 0; i < result.length; i++) {
			result[i] = (sourceProcessed.charAt(i) == ' ') ? path : wall;
		}
		return result;
	}

	public static int[][] toMatrix(List<String> sourceFileRows) {
		int[][] result = new int[sourceFileRows.size()][];
		int path;

		for(int i = 0; i < sourceFileRows.size(); i++) {
			path = (i == 0) ? BuildingBlock.ENTRY.getValue() : (i == sourceFileRows.size() - 1) ? BuildingBlock.EXIT.getValue() : BuildingBlock.PATH.getValue();
			result[i] = convertRowToMatrix(sourceFileRows.get(i), BuildingBlock.WALL.getValue(), path);
		}
		return result;
	}
}
