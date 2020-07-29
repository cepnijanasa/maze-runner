package se.seb.academy.maze;

public class MazeRunnerFactory {

	private MazeRunnerFactory() {}

	public static IMazeRunner newMazeRunner(Algorithm algo) {
		switch (algo) {
			case ALWAYS_LEFT: return new AlwaysLeftRunner();
			case ALWAYS_RIGHT: return new AlwaysRightRunner();
			default: throw new IllegalStateException("No maze runner for algorithm '" + algo.toString() + "'");
		}
	}
}
