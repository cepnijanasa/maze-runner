package se.seb.academy.maze;

public class MatrixPrinter {

	private MatrixPrinter() {
		// should not be instantiated
	}

	public static void print(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}
