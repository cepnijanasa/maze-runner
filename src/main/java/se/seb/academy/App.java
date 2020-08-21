package se.seb.academy;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import se.seb.academy.maze.Algorithm;
import se.seb.academy.maze.MazeConverter;
import se.seb.academy.maze.MazePrinter;
import se.seb.academy.maze.MazeRunnerResult;
import se.seb.academy.maze.MazeRunnerTask;

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

	public static void main(String[] args) throws Exception {
		System.out.println("Welcome to the maze!\n");
		System.out.println("Available API's:");
		System.out.println(API_A + ".\n" +
				"\t Input:\n" +
				"\t\t Maze: as two dimentional array.\n" +
				"\t\t Algorithm: name of algorithm that will be used.\n" +
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

			char[][] charMaze = MazeConverter.toCharMaze(Files.readAllLines(pathToMazeFile));
			//MatrixPrinter.print(charMatrix);
			int[][] intMaze = MazeConverter.toIntMaze(charMaze);

			List<Algorithm> algoList = new ArrayList<>();
			if (api == API_A) {
				algoList.add(readAlgoSelectionInput(scanner));
			} else {
				algoList.addAll(Arrays.asList(Algorithm.values()));
			}

			ExecutorService executor = Executors.newFixedThreadPool(algoList.size());
			Map<Algorithm, Future<MazeRunnerResult>> futuresMap = new HashMap<>();

			for (Algorithm algo: algoList) {
				futuresMap.put(algo, executor.submit(new MazeRunnerTask(intMaze, algo.newMazeRunner())));
			}

			MazeRunnerResult optimalResult = null;
			Algorithm optimalAlgorithm = null;
			for (Map.Entry<Algorithm, Future<MazeRunnerResult>> entry : futuresMap.entrySet()) {
				MazeRunnerResult result = entry.getValue().get();
				if (optimalResult == null || optimalResult.getPositionList().size() > result.getPositionList().size()) {
					optimalAlgorithm = entry.getKey();
					optimalResult = result;
				}
			}
			executor.shutdown();

			System.out.println("------------------------------------------------");
			System.out.println("Using algorithm: " + optimalAlgorithm.toString());
			System.out.println("Time spent: " + optimalResult.getDuration().toMillis() + " ms (" + optimalResult.getDuration().toNanos() + " ns)");
			System.out.println("Number of steps performed: " + optimalResult.getPositionList().size());
			MazePrinter.printEscapePath(charMaze, optimalResult.getPositionList());
			optimalResult.getPositionList().stream().forEach(System.out::println);

			System.out.println("Bye bye");
		}
	}
}