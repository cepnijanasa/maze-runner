package se.seb.academy.maze;

import java.util.Arrays;
import java.util.List;

public class MatrixPrinter {

	private MatrixPrinter() {
		// should not be instantiated
	}

	public static void print(char[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

	public static void printEscapePath(char[][] matrix, List<Position> positionList) {
		char[][] matrixCopy = new char[matrix.length][];
		for(int i = 0; i < matrix.length; i++) {
			matrixCopy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
		}

		for(Position p: positionList) {
			matrixCopy[p.getY()][p.getX()] = '*';
		}
		print(matrixCopy);
	}

}
