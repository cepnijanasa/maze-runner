package se.seb.academy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import se.seb.academy.maze.Algorithm;
import se.seb.academy.maze.IMazeRunner;
import se.seb.academy.maze.MatrixPrinter;
import se.seb.academy.maze.MazeRunnerFactory;
import se.seb.academy.maze.Position;
import se.seb.academy.maze.SourceConverter;

public class App {

	private static final int API_A = 1;
	private static final int API_B = 2;

	private static int readApiSelectionInput(Scanner scanner) {
		int choice = -1;
		List<Integer> apiList = Arrays.asList(API_A, API_B);

		while(true) {
			System.out.print("\nChoose API:");
			try {
				choice = Integer.parseInt(scanner.next());
				if (!apiList.contains(choice)) {
					System.out.println("Not a valid selection");
				} else {
					return choice;
				}
			} catch (NumberFormatException e) {
				System.out.println("Not an integer");
			}
		}
	}

	private static Path readMazeFilePathInput(Scanner scanner) {
		// TODO temporary for testing purposes
		return Path.of("C:\\Users\\s6812b\\src\\maze\\maze_to_use.csv");

		/*
		 * Path path; while (true) { System.out.print("\nPath to maze file:"); path =
		 * Path.of(scanner.next()); if (!Files.exists(path) || Files.isDirectory(path))
		 * { System.out.println("Invalid file path"); } else { return path; } }
		 */
	}

	private static Algorithm readAlgoSelectionInput(Scanner scanner) {
		System.out.println("\nAvailable algorithms:");
		List<String> algorithmList = Arrays.stream(Algorithm.values()).map(a -> a.toString()).collect(Collectors.toList());
		algorithmList.stream().forEach(a -> System.out.println(a));
		String choice;
		while (true) {
			System.out.print("Enter your choice:");
			choice = scanner.next();
			if (!algorithmList.contains(choice)) {
				System.out.println("No such algorithm");
			} else {
				return Algorithm.valueOf(choice);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the maze!\n");
		System.out.println("Available API's:");
		System.out.println(API_A + ".\n" +
				"\t Input:\n" +
				"\t\t Maze: as two dimentional array.\n" +
				"\t\t Algorithm: name of algorithem that will be used.\n" +
				"\t Returns:\n" +
				"\t\t Fastest navigated path.\n" +
				"\t\t Name of algorithm used to find the fastest path.\n");

		System.out.println(API_B + ".\n" +
				"\t Input:\n" +
				"\t\t Maze: as two dimentional array.\n" +
				"\t Returns:\n" +
				"\t\t Fastest navigated path.\n" +
				"\t\t Time needed to find the optimal path using all algorithms.\n");

		try (Scanner scanner = new Scanner(System.in)){
			int api = readApiSelectionInput(scanner);

			Path pathToMazeFile = readMazeFilePathInput(scanner);
			int[][] matrix = SourceConverter.toMatrix(Files.readAllLines(pathToMazeFile));

			Algorithm algorithm = (api == API_A) ? readAlgoSelectionInput(scanner) : Algorithm.getDefault();
			System.out.println("Using algorithm: " + algorithm.toString());

			MatrixPrinter.print(matrix);

			//Maze maze = new Maze(matrix);

			IMazeRunner mazeRunner = MazeRunnerFactory.getMazeRunner(algorithm);
			List<Position> positionList = mazeRunner.escapeMaze(matrix);

			positionList.stream().forEach(p -> System.out.println(p));

			System.out.println("Bye bye");
		}
	}
}