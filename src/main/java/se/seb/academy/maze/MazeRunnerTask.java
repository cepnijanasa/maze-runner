package se.seb.academy.maze;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.Callable;

public class MazeRunnerTask implements Callable<MazeRunnerResult> {

	private int[][] maze;
	private IMazeRunner mazeRunner;

	public MazeRunnerTask(int[][] maze, IMazeRunner mazeRunner) {
		this.maze = maze;
		this.mazeRunner = mazeRunner;
	}

	@Override
	public MazeRunnerResult call() throws Exception {
		Instant start = Instant.now();
		List<Position> positionList = mazeRunner.escapeMaze(maze);
		Duration duration = Duration.between(start, Instant.now());

		return new MazeRunnerResult(positionList, duration);
	}
}
