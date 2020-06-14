package se.seb.academy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the maze!\n");
		System.out.println("Available API's:");
		System.out.println("1.\n" +
				"\t Input:\n" + 
				"\t\t Maze: as two dimentional array.\n" + 
				"\t\t Algorithm: name of algorithem that will be used.\n" + 
				"\t Returns:\n" + 
				"\t\t Fastest navigated path.\n" + 
				"\t\t Name of algorithm used to find the fastest path.\n");

		System.out.println("2.\n" + 
				"\t Input:\n" + 
				"\t\t Maze: as two dimentional array.\n" + 
				"\t Returns:\n" + 
				"\t\t Fastest navigated path.\n" + 
				"\t\t Time needed to find the optimal path using all algorithms.\n");

		try (Scanner scanner = new Scanner(System.in)){
			int choice = readApiSelectionInput(scanner);
			Path pathToMazeFile = readMazeFilePathInput(scanner);
			//List<String> lines = Files.readAllLines(pathToMazeFile);
		}
	}

	private static int readApiSelectionInput(Scanner scanner) {
		int choice = -1;
		List<Integer> apiList = Arrays.asList(1, 2);

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
		Path path;
		while (true) {
			System.out.print("\nPath to maze file:");
			path = Path.of(scanner.next());
			if (!Files.exists(path) || Files.isDirectory(path)) {
				System.out.println("Invalid file path");
			} else {
				return path;
			}
		}
	}
}
